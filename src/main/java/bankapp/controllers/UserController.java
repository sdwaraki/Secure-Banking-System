package bankapp.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import bankapp.Objects.ApplicationContextUtils;
import bankapp.Objects.Audit;
import bankapp.Objects.BankUser;
import bankapp.Objects.Role;



@Controller
@SessionAttributes("BankUser")
public class UserController 
{	
	
	List<Role> rolesList;
	Map<String,String> deptIdNameMap = new HashMap<String,String>();
	 @RequestMapping(value = "user", method = RequestMethod.GET)
	 public ModelAndView user() 
	 {
	      return new ModelAndView("user", "User", new BankUser());
	 }
	 
	 @RequestMapping(value = "ChangePassword", method = RequestMethod.GET)
	 public ModelAndView changePassword(Model model) 
	 {
		 model.addAttribute("Success", "");
		 model.addAttribute("Error","");
	      return new ModelAndView("/auth/ChangePasswordForm", "User",ApplicationContextUtils.getLoggedInUser());
	 }
	 
	 @RequestMapping(value = "processChangePWD", method = RequestMethod.POST)
	 public ModelAndView changePasswordProcess(HttpServletRequest request, 
		        HttpServletResponse response,Model model) 
	 {
		 String UserId=ApplicationContextUtils.getLoggedInUser().getUserId();
		 String oldPassword=ApplicationContextUtils.getBankUserJDBCTemplate().getPasswordByUserID(UserId);
		 String OldPassword = request.getParameter("OldPassword");
			String Newpass = request.getParameter("newpassword");
			String conpass = request.getParameter("conpassword");
			
			if(OldPassword.equals("") || Newpass.equals("")||conpass.equals(""))
			{
				model.addAttribute("Error", "One Of the Fields are Empty!");
			}
			else if(Newpass.length()<6)
			{
				model.addAttribute("Error", "password length must be greater that 6!");
					
			}
			else
			{
				BCryptPasswordEncoder bc = new BCryptPasswordEncoder();				
				 if(bc.matches(OldPassword, oldPassword) && Newpass.equals(conpass))
				 {
					 
					 if(bc.matches(Newpass, oldPassword))
					{
						 model.addAttribute("Success", "");
						 model.addAttribute("Error", "Same Password Not Allowed");
					}
					 else
					 {
						 ApplicationContextUtils.getBankUserJDBCTemplate().updatePasswordByUserIDAndPassword(UserId,bc.encode(Newpass));
						 model.addAttribute("Success", "Password Sucessfully Updated!!");
					 }
				}
				 else
				 {
					 model.addAttribute("Error", "Either Old Password or the New and Confirmation Password Dint match!!");
				 }
			}
			 return new ModelAndView("/auth/ChangePasswordForm", "User",ApplicationContextUtils.getLoggedInUser());
	 }
	
	 
	 @RequestMapping(value = "/system/ViewSyslog", method = RequestMethod.GET)
	 public ModelAndView ViewSyslog(Model model) 
	 {
		 List<Audit> audits= new ArrayList<Audit>();
		 audits=ApplicationContextUtils.getAuditJDBCTemplate().getAuditAll();
		 model.addAttribute("audits",audits);
	     return new ModelAndView("/system/ViewSyslog", "Audit", audits);
	 }
		@RequestMapping(value = "UpdateUser", method = RequestMethod.GET)
		public String gotoHome6(ModelMap modle) 
		{
			
			modle.addAttribute("BankUser",ApplicationContextUtils.getLoggedInUser());
			return "UpdateUser";
		
		}
	 @RequestMapping(value = "UpdateUser", method = RequestMethod.POST)
	 public ModelAndView updateUser(@ModelAttribute("BankUser") @Valid BankUser bankuser, BindingResult result,Model model) 
	 {
		 	if(result.hasErrors())
		 	{
		 	//	model.addAttribute("BankUser",bankuser);
				return  new ModelAndView("UpdateUser","BankUser",bankuser);
		 	}
		 	else
		 	{
		 		 ApplicationContextUtils.getBankUserJDBCTemplate().updateUser(bankuser);
				 model.addAttribute("Msg", "Form successfully submitted");
				return new ModelAndView("results","BankUser",ApplicationContextUtils.getLoggedInUser());

		 	}
	 }

}
	 

	
		
