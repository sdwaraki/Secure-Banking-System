package bankapp.controllers;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import bankapp.Objects.ApplicationContextUtils;
import bankapp.Objects.Audit;
import bankapp.Objects.BankUser;
import bankapp.Objects.Constants;
import bankapp.Objects.Customer;
import bankapp.Objects.Employee;
import bankapp.Objects.Request;
import bankapp.Objects.Role;
import bankapp.backbean.CreateEmployeeBackBean;

@Controller

public class EmployeeController 
{
	/*@InitBinder
	public void initBinder(WebDataBinder webDataBinder) 
	{
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		dateFormat.setLenient(false);
		webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}*/
	List<Role> rolesList;
	 Map<String,String> roleIdNameMap ;
	 @RequestMapping(value = "/employee/CreateEmployee", method = RequestMethod.GET)
	 public String addEmployeeView(Model model) 
	 {
		 rolesList =  ApplicationContextUtils.getRoleJDBCTemplate().getRoleByRoleType(Constants.EMPLOYEE_TYPE);	
         model.addAttribute("rolesList", rolesList);		 
		 roleIdNameMap = new HashMap<String,String>();		 		 
		 for(Role role:rolesList)
		 		roleIdNameMap.put(role.getId(), role.getName());	
		 model.addAttribute("roleIdNameMap",roleIdNameMap);
		model.addAttribute("CreateEmployeeBackBean",new CreateEmployeeBackBean());
	     return "/employee/CreateEmployee";
	 }
	 
	 @RequestMapping(value = "/employee/CreateEmployee", method = RequestMethod.POST)
	 public ModelAndView addEmployee(@ModelAttribute("CreateEmployeeBackBean") @Valid  CreateEmployeeBackBean employee, BindingResult result,ModelMap model) throws NoSuchAlgorithmException, InvalidKeySpecException 
	 {
		 Boolean auditStatus=false;
		 try {
			if(result.hasErrors())
			 {
				 model.addAttribute("roleIdNameMap",roleIdNameMap);
				  return new ModelAndView("/employee/CreateEmployee","CreateEmployeeBackBean",employee);
			 }
			 if(ApplicationContextUtils.getBankUserJDBCTemplate().checkUserReturned(employee.getAddress().getEmailID()))
			 {
				 model.addAttribute("roleIdNameMap",roleIdNameMap);
				// result.
				 result.rejectValue("address.emailID", "email.AlreadyExsist","Email Already Exsist!");
				  return new ModelAndView("/employee/CreateEmployee","CreateEmployeeBackBean",employee);
				 	
			 }
			 employee.getRoles().add(new Role(employee.getRoleid()));
			 employee.setCreadtedBy(ApplicationContextUtils.getLoggedInUser().getUserId());
			 String userId=ApplicationContextUtils.getBankUserJDBCTemplate().createBankUser(employee);
			 employee.setUserId(userId);		 
			 ApplicationContextUtils.getEmployeeJDBCTemplate().createEmployee(employee);
			 auditStatus=true;
			 	String currentUserRole = ApplicationContextUtils.getLoggedInUser().getRoles().get(0).getName();
				
				if(currentUserRole.equals(Constants.HR_MANAGER))
				{
						model.addAttribute("Msg","A request is sent to the Admin, Please check My Request Screen to monitor the status.");
				}
				else
				{
					model.addAttribute("Msg","A request is sent to your department Manger,Please check My Request Screen to monitor the status. ");
				}
			// ApplicationContextUtils.getRequestJDBCTemplate().createRequestToManager(r);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.getMessage();
			e.printStackTrace();
		} finally 
		{
			if(auditStatus)
			{
			String auditTrail=(ApplicationContextUtils.getBankUserJDBCTemplate().getUserByUserName(employee.getUserName()))+"AUDIT STATUS: "+auditStatus;
			Audit audit=ApplicationContextUtils.getAuditJDBCTemplate().setAuditRecord(Constants.ADD_EMP_REQUEST, auditTrail, ApplicationContextUtils.getLoggedInUser().getUserId());
			ApplicationContextUtils.getAuditJDBCTemplate().createAudit(audit);
			}
		}
	      return new ModelAndView("results","CreateEmployeeBackBean",employee);
	   }
	 
	 @RequestMapping(value = "/employee/TransferEmployee", method = RequestMethod.GET)
	 public String transferEmployeeEmployeeView(Model model) 
	 {
		 rolesList =  ApplicationContextUtils.getRoleJDBCTemplate().getRoleByRoleType(Constants.EMPLOYEE_TYPE);
		 List<String> rolenames= new ArrayList<String>();
		 for(Role r:rolesList)
		 {
			 rolenames.add(r.getName());
		 }
         model.addAttribute("rolesList", rolenames);		 
		/* roleIdNameMap = new HashMap<String,String>();		 		 
		 for(Role role:rolesList)
		 		roleIdNameMap.put(role.getId(), role.getName());*/	
		 /*model.addAttribute("roleIdNameMap",roleIdNameMap);*/
		 model.addAttribute("employee",new Employee());
	     return "/employee/TransferEmployee";
	 }
	 
	 @RequestMapping(value = "/employee/TransferEmployee", method = RequestMethod.POST)
	 public ModelAndView transferEmployee(HttpServletRequest request,HttpServletResponse response,ModelMap model) throws NoSuchAlgorithmException, InvalidKeySpecException 
	 {
		 Boolean auditStatus=false;
		 try 
		 {
			 String employeeUserName= request.getParameter("Email");
			 					 
			 String employeeRole= request.getParameter("role");

		 	if(!ApplicationContextUtils.getBankUserJDBCTemplate().checkUserReturned(employeeUserName)) {
		 		rolesList =  ApplicationContextUtils.getRoleJDBCTemplate().getAllRoles();	
				 List<String> rolenames= new ArrayList<String>();
				 for(Role r:rolesList)
				 {
					 rolenames.add(r.getName());
				 }
		 		model.addAttribute("rolesList", rolenames);
				model.addAttribute("employee",new Employee());
				model.addAttribute("Msg","Email Does Not Exist");
				return new ModelAndView("results","CreateEmployeeBackBean",new CreateEmployeeBackBean());				 	
			 }
		 	
		 	 BankUser user=ApplicationContextUtils.getBankUserJDBCTemplate().getUserByUserName(employeeUserName);
		 	 Role role=ApplicationContextUtils.getRoleJDBCTemplate().getRoleByName(employeeRole);
		 	 String currentRole =user.getRoles().get(0).getName();
		 	 String currentLoggedInRole=ApplicationContextUtils.getLoggedInUser().getRoles().get(0).getName();
		 	 if ( (currentLoggedInRole.equals(Constants.SALES_MANAGER)||currentLoggedInRole.equals(Constants.HR_MANAGER)||currentLoggedInRole.equals(Constants.IT_MANAGER)||currentLoggedInRole.equals(Constants.TRANSACTION_MANAGER))) {
			 
		 		 System.out.println("emps manager " + ApplicationContextUtils.getRequestJDBCTemplate().getManagerType(user.getRoles().get(0).getName()));
			 
		 		 System.out.println("manager"+ApplicationContextUtils.getLoggedInUser().getRoles().get(0).getName());
			
		 		
		 		 if((ApplicationContextUtils.getRequestJDBCTemplate().getManagerType(currentRole).equals(ApplicationContextUtils.getLoggedInUser().getRoles().get(0).getName()))) {
		 			 ApplicationContextUtils.getEmployeeJDBCTemplate().transferEmployee(user.getUserId(), role.getId());
		 			 auditStatus=true;
		 			 model.addAttribute("Msg","Employee Transfered");
		 		 }
		 	  else {
		 		model.addAttribute("Msg","You do not have sufficient permissions to transfer this employee");
		 	 }
		 	 }
		 	 } catch (Exception e) {
		 		 e.printStackTrace();
		 		 e.getMessage();
		 	 } finally {
	/*		String auditTrail=(ApplicationContextUtils.getBankUserJDBCTemplate().getUserByUserName(employee.getUserName()))+"AUDIT STATUS: "+auditStatus;
			Audit audit=ApplicationContextUtils.getAuditJDBCTemplate().setAuditRecord(Constants.TRANSFER_EMPLOYEE, auditTrail, ApplicationContextUtils.getLoggedInUser().getUserId());
			ApplicationContextUtils.getAuditJDBCTemplate().createAudit(audit);
	*/		}
		 	return new ModelAndView("results","CreateEmployeeBackBean",new CreateEmployeeBackBean());
	   }
	 
	 
	 
	 @RequestMapping(value = "/employee/DeleteEmployee", method = RequestMethod.GET)
	 public ModelAndView DeleteEmployeeView() throws Exception 
	 {
		 ApplicationContextUtils.getLoggedInUser();
		 Employee e=new Employee();
	     return new ModelAndView("/employee/DeleteEmployee", "Employee", e);
	 }

	 
	 @RequestMapping(value = "/employee/DeleteEmployee", method = RequestMethod.POST)
	 public String deleteEmployee(@ModelAttribute Employee employee,ModelMap model) throws NoSuchAlgorithmException, InvalidKeySpecException 
	 {		
		 Boolean auditStatus=false;
		try {
			/* ApplicationContextUtils.getBankUserJDBCTemplate().deleteUserByUserName(employee.getUserName());
			 model.addAttribute("Msg","Deleted Succesfully");*/
			if(ApplicationContextUtils.getBankUserJDBCTemplate().checkUserReturned(employee.getUserName())) 
			{
				if(!ApplicationContextUtils.getBankUserJDBCTemplate().isDefaultUser(employee.getUserName()))
				{
					Employee c= ApplicationContextUtils.getEmployeeByUserName(employee.getUserName());
					Request r=new Request();
					r.setRequestType(Constants.DELETE_EMPLOYEE_REQUEST);
					r.setRequestFrom(ApplicationContextUtils.getLoggedInUser().getUserId());
					r.setId(c.getUserId());
					ApplicationContextUtils.getRequestJDBCTemplate().createRequestToManager(r);
					auditStatus=true;
				// ApplicationContextUtils.getBankUserJDBCTemplate().deleteUserByUserName(customer.getUserName());
					String currentUserRole = ApplicationContextUtils.getLoggedInUser().getRoles().get(0).getName();
					
					if(currentUserRole.equals(Constants.HR_MANAGER))
					{
							model.addAttribute("Msg","A request is sent to the Admin, Please check My Request Screen to monitor the status.");
					}
					else
					{
						model.addAttribute("Msg","A request is sent to your department Manger,Please check My Request Screen to monitor the status. ");
					}
				}
				else
				{
					model.addAttribute("Msg","Cannot Delete a Default Employee");
				}
			}
			else
			{
				model.addAttribute("Msg","Invalid UserName");
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			e.getMessage();
		} finally {
			if(auditStatus)
			{	
				
				String auditTrail=(ApplicationContextUtils.getBankUserJDBCTemplate().getUserByUserName(employee.getUserName()))+"AUDIT STATUS: "+auditStatus;
				Audit audit=ApplicationContextUtils.getAuditJDBCTemplate().setAuditRecord(Constants.DEL_EMP_REQUEST, auditTrail, ApplicationContextUtils.getLoggedInUser().getUserId());
				ApplicationContextUtils.getAuditJDBCTemplate().createAudit(audit);
			}
		}
	      return "results";
	 }
//	private boolean checkEmailAlreadyExsist(String emailID)
//	{
//		if(ApplicationContextUtils.getBankUserJDBCTemplate().checkUserReturned(emailID) >0)
//		return true;
//		else
//			return false;
//	}
}
