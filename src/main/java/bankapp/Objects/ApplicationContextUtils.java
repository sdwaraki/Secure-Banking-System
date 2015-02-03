package bankapp.Objects;


import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import bankapp.JDBCTemplates.AccountJDBCTemplate;
import bankapp.JDBCTemplates.AddressJDBCTemplate;
import bankapp.JDBCTemplates.AuditJDBCTemplate;
import bankapp.JDBCTemplates.BankUserJDBCTemplate;
import bankapp.JDBCTemplates.CustomerJDBCTemplate;
import bankapp.JDBCTemplates.DepartmentJDBCTemplate;
import bankapp.JDBCTemplates.EmployeeJDBCTemplate;
import bankapp.JDBCTemplates.PayeeJDBCTemplate;
import bankapp.JDBCTemplates.RecipientJDBCTemplate;
import bankapp.JDBCTemplates.RequestJDBCTemplate;
import bankapp.JDBCTemplates.RoleJDBCTemplate;
import bankapp.JDBCTemplates.SalaryJDBCTemplate;
import bankapp.JDBCTemplates.TransactionJDBCTemplate;
 
public class ApplicationContextUtils implements ApplicationContextAware 
{ 
  private static ApplicationContext ctx;
  public static String getLoggedInUserName()
  {
	  Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	  User user = (User)authentication.getPrincipal();
	  return user.getUsername();
  }

  public static Customer getLoggedInUserAsCustomer() 
  {
	  Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	  User user = (User)authentication.getPrincipal();
	  return getCustomerByUserName(user.getUsername());
  }
public static Customer getCustomerByUserName(String userName)
{
	  BankUser bankUser= new BankUser(userName);
	  Customer loggedInUser=null;
	 for(Role r:bankUser.getRoles())
	 {
 
		if(r.getRoleType().equals(Constants.CUSTOMER_TYPE))
			 {
				 Customer c= new Customer(bankUser.getUserName());
				 loggedInUser =c;
				 break;
			 }
	 }
	return loggedInUser;

}
public static Employee getEmployeeByUserName(String userName)
{
	BankUser bankUser= new BankUser(userName);
	  Employee loggedInUser=null;
		 for(Role r:bankUser.getRoles())
		 {
	 
				 if(r.getRoleType().equals(Constants.EMPLOYEE_TYPE))
				 {
					 Employee e = new Employee(bankUser.getUserName());
					 System.out.println();
					 	loggedInUser=e;
					 break;
				 }
			
		 }
		return loggedInUser;

}
  public static Employee getLoggedInUserAsEmployee() 
  {
	  Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	  User user = (User)authentication.getPrincipal();
	  return getEmployeeByUserName(user.getUsername());
	  }
  public static BankUser getLoggedInUser() 
  {
	  Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	  User user = (User)authentication.getPrincipal();
	  BankUser bankUser= new BankUser(user.getUsername());
	  BankUser loggedInUser=null;
	 for(Role r:bankUser.getRoles())
	 {
 
			 if(r.getRoleType().equals(Constants.EMPLOYEE_TYPE))
			 {
				 Employee e = new Employee(bankUser.getUserName());
				 System.out.println();
				 	loggedInUser=e;
				 break;
			 }
			 else if(r.getRoleType().equals(Constants.CUSTOMER_TYPE))
			 {
				 Customer c= new Customer(bankUser.getUserName());
				 loggedInUser =c;
				 break;
			 }
	 }
	return loggedInUser;
  }

  
  public static boolean isNumeric(String str)
	{
	  return str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
	}
  public static AccountJDBCTemplate getAccountJDBCTemplate() 
  {
	  return ctx.getBean(AccountJDBCTemplate.class);
  }

public static AddressJDBCTemplate getAddressJDBCTemplate() 
{
	return ctx.getBean(AddressJDBCTemplate.class);
}

public static AuditJDBCTemplate getAuditJDBCTemplate() 
{
	return ctx.getBean(AuditJDBCTemplate.class);
}

public static BankUserJDBCTemplate getBankUserJDBCTemplate() 
{
	return ctx.getBean(BankUserJDBCTemplate.class);
}

public static CustomerJDBCTemplate getCustomerJDBCTemplate() 
{
	return ctx.getBean(CustomerJDBCTemplate.class);
}

public static DepartmentJDBCTemplate getDepartmentJDBCTemplate()
{
	return ctx.getBean(DepartmentJDBCTemplate.class);
}

public static EmployeeJDBCTemplate getEmployeeJDBCTemplate() 
{
	return ctx.getBean(EmployeeJDBCTemplate.class);
}

public static RoleJDBCTemplate getRoleJDBCTemplate() 
{
	return ctx.getBean(RoleJDBCTemplate.class);
}

public static SalaryJDBCTemplate getSalaryJDBCTemplate()
{
	return ctx.getBean(SalaryJDBCTemplate.class);
}

public static RequestJDBCTemplate getRequestJDBCTemplate() 
{
	return ctx.getBean(RequestJDBCTemplate.class);
}
  public static RecipientJDBCTemplate getRecipientJDBCTemplate()
  {  
	  return ctx.getBean(RecipientJDBCTemplate.class);
  }

 public static TransactionJDBCTemplate getTransactionJDBCTemplate()
 {
	 return ctx.getBean(TransactionJDBCTemplate.class);
 }
 
 public static PayeeJDBCTemplate getPayeeJDBCTemplate()
 {
	 return ctx.getBean(PayeeJDBCTemplate.class);
 }
  public static ApplicationContext getCtx() {
	return ctx;
}


@Override
  public void setApplicationContext(ApplicationContext appContext)
      throws BeansException {
    ctx = appContext;
 
  }
 
  public static ApplicationContext getApplicationContext() {
    return ctx;
  }
  public static Object getbean(String bean)
  {
	  return getApplicationContext().getBean(bean);
  }
}