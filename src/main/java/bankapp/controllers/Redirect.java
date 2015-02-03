package bankapp.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import bankapp.Objects.ApplicationContextUtils;
import bankapp.Objects.BankUser;
import bankapp.Objects.Constants;
import bankapp.Objects.Customer;
import bankapp.Objects.Employee;
import bankapp.Objects.MailMail;
import bankapp.Objects.Role;
import bankapp.backbean.CreateEmployeeBackBean;


@Controller
public class Redirect {
	
	
	
	      @RequestMapping(value="/404error")
	      public String handlePageNotFoundException(Exception ex,
	          HttpServletResponse response) {
	    	  
	    	/*  String Messgae= org.apache.commons.lang.exception.ExceptionUtils.getStackTrace(ex);
			  MailMail mm = (MailMail) ApplicationContextUtils.getCtx().getBean("mailMail");
			   mm.sendMail("thebankapplication@gmail.com","darshankpbhat@gmail.com","Erro!", Messgae);*/
			   ex.printStackTrace();
	     return "404error";
	      }
	      
	      
	      @RequestMapping(value = "/Customer/CustomerLogin", method = RequestMethod.GET)
	  	public ModelAndView  redirecttoPage(HttpServletRequest request) 
	  	 {
	    	 return redirectPage(request);
	  	 }
	
	@RequestMapping(value = "/auth/RedirectPage", method = RequestMethod.GET)
	public ModelAndView redirectPage(HttpServletRequest request) 
	 {
	
		String viewName="";
		String modelName="";
		Object modelObject=null;

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication.getPrincipal() instanceof User)
		{
			//modelObject.equals("");
			 User user = (User)authentication.getPrincipal();
			BankUser b= new BankUser(user.getUsername());
			 for(Role r:b.getRoles())
			 {
	
				 if(r.getRoleType().equals(Constants.EMPLOYEE_TYPE))
				 {
					 Employee e = new Employee(user.getUsername());
					 System.out.println();
					 viewName=Constants.pageMap.get(r.getName());
					 modelObject=e;
					 modelName="Employee";
					 break;
					 
				 }
				 else if(r.getRoleType().equals(Constants.CUSTOMER_TYPE))
				 {
					 
					 Customer c= new Customer(user.getUsername());
					 viewName=Constants.pageMap.get(r.getName());
					 modelObject=c;
					 modelName="Customer";
					 break;
				 }				
			 }
			 request.getSession().setAttribute("UserName", user.getUsername());
			 request.getSession().setAttribute("User", modelObject);
			return new ModelAndView("/home/Home",modelName,modelObject);
		}
		else
		{
			return new ModelAndView("login",modelName,modelObject);
		}
	 }
	@RequestMapping(value = "/auth/denied", method = RequestMethod.GET)
 	public String getDeniedPage() {
	
		return "/auth/deniedpage";
	}
		/*
	@RequestMapping(value = "index", method = RequestMethod.GET)
 	public String gotoHome() {
	
		return "../index";
	}*/

	@RequestMapping(value = "MyAccount", method = RequestMethod.GET)
 	public ModelAndView gotoMyAccount() {
		
	BankUser b= ApplicationContextUtils.getLoggedInUser();
	//BankUser cart = (BankUser)session.setAttribute("BankUser",b);
	return new ModelAndView("MyAccount","BankUser",b);
	}

	
	@RequestMapping(value = "HrRequestForCreateEmployee", method = RequestMethod.GET)
 	public ModelAndView gotoHome1() {
		
	
		//return "/home/HrRequestForCreateEmployee";
		return new ModelAndView("/home/HrRequestForCreateEmployee","CreateEmployeeBackBean",new CreateEmployeeBackBean());	
	
	}
	
	@RequestMapping(value = "HrRequestForDeleteEmployee", method = RequestMethod.GET)
 	public ModelAndView gotoHome3() {
		
	
		//return "/home/HrRequestForCreateEmployee";
		return new ModelAndView("/home/HrRequestForDeleteEmployee","CreateEmployeeBackBean",new CreateEmployeeBackBean());	
	
	}
	

	
	@RequestMapping(value = "ChangePasswordForm", method = RequestMethod.GET)
	public ModelAndView gotoHome7() 
	{
		
		return new ModelAndView("/auth/ChangePasswordForm","BankUser",new BankUser());	
	
	}

	

	
	@RequestMapping(value = "HrManagerMyRequests", method = RequestMethod.GET)
	public ModelAndView HrManagerMyRequests() {
		
		return new ModelAndView("/home/HrManagerMyRequests","BankUser",new BankUser());	
	
	}
	
	@RequestMapping(value = "AboutUs", method = RequestMethod.GET)
 	public String gotoHome10() {
	
		return "AboutUs";
	}	
	
	@RequestMapping(value = "ContactUs", method = RequestMethod.GET)
 	public String gotoHome11() {
	
		return "ContactUs";
	}
	
	
}
