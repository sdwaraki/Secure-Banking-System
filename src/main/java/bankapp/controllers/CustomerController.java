package bankapp.controllers;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.List;

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
import org.springframework.web.servlet.ModelAndView;

import bankapp.Objects.Account;
import bankapp.Objects.ApplicationContextUtils;
import bankapp.Objects.Audit;
import bankapp.Objects.BankUser;
import bankapp.Objects.Constants;
import bankapp.Objects.Customer;
import bankapp.Objects.MailMail;
import bankapp.Objects.Request;
import bankapp.Objects.Role;
import bankapp.Objects.Transaction;
import bankapp.backbean.CreateEmployeeBackBean;
import bankapp.backbean.FundTransferBackBean;
import bankapp.backbean.PaymentBackBean;

@Controller
public class CustomerController {
	
	private List<Account> owner_accountList;
	
	 @RequestMapping(value = "/customer/CreateCustomer", method = RequestMethod.GET)
	 public ModelAndView CreateCustomerView(Model model) 
	 {
		 List<Role> roleList = ApplicationContextUtils.getRoleJDBCTemplate().getRoleByRoleType(Constants.CUSTOMER_TYPE);
		 List<String> customerTypes= new ArrayList<String>();
		 for(Role r:roleList)
		 {
			 customerTypes.add(r.getName());
		 }
		 model.addAttribute("customerTypes", customerTypes);
		 return new ModelAndView("/customer/CreateCustomer", "Customer", new Customer());
	 }

	 
	 @RequestMapping(value = "/customer/CreateCustomer", method = RequestMethod.POST)
	 public ModelAndView addCustomer(@ModelAttribute("Customer") @Valid Customer customer,BindingResult result,ModelMap model) throws NoSuchAlgorithmException, InvalidKeySpecException 
	 {	
		 Boolean auditStatus=false;
		 List<String> customerTypes= new ArrayList<String>();
		 List<Role> roleList = ApplicationContextUtils.getRoleJDBCTemplate().getRoleByRoleType(Constants.CUSTOMER_TYPE);
		 //List<String> customerTypes= new ArrayList<String>();
		 for(Role r:roleList)
		 {
			 customerTypes.add(r.getName());
		 }
		 try 
		 {
			if(result.hasErrors())	
			{
			 		
					 model.addAttribute("customerTypes", customerTypes);
					 return new ModelAndView("/customer/CreateCustomer","Customer",customer);
			 }
			if(ApplicationContextUtils.getBankUserJDBCTemplate().checkUserReturned(customer.getAddress().getEmailID()))
			 {
				 model.addAttribute("customerTypes", customerTypes);
				// result.
				 result.rejectValue("address.emailID", "email.AlreadyExsist","Email Already Exsist!");
				 return new ModelAndView("/customer/CreateCustomer","Customer",customer);
				 	
			 }
			 customer.getRoles().add(new Role(customer.getCustomerType()));
			 customer.setCreadtedBy(ApplicationContextUtils.getLoggedInUser().getUserId());
			 customer.setUserId(ApplicationContextUtils.getBankUserJDBCTemplate().createBankUser(customer));
			 ApplicationContextUtils.getCustomerJDBCTemplate().createCustomer(customer);
			 auditStatus=true;
			 String currentUserRole = ApplicationContextUtils.getLoggedInUser().getRoles().get(0).getName();
				
				if(currentUserRole.equals(Constants.SALES_MANAGER))
				{
						model.addAttribute("Msg","A request is sent to the Admin, Please check My Request Screen to monitor the status.");
				}
				else
				{
					model.addAttribute("Msg","A request is sent to your department Manger,Please check My Request Screen to monitor the status. ");
				}
		} catch (Exception e) 
		{
			e.printStackTrace();
			e.getMessage();
		} finally 
		{
			if(auditStatus)
			{
				String auditTrail=customer.toString()+"AUDIT STATUS: "+auditStatus;
				Audit audit=ApplicationContextUtils.getAuditJDBCTemplate().setAuditRecord(Constants.ADD_CUST_REQUEST, auditTrail, ApplicationContextUtils.getLoggedInUser().getUserId());
				ApplicationContextUtils.getAuditJDBCTemplate().createAudit(audit);
		
			}
		}
		 return  new ModelAndView("results","Customer",customer);
	 }
	 
	 @RequestMapping(value = "/customer/DeleteCustomer", method = RequestMethod.GET)
	 public ModelAndView DeleteCustomerView() 
	 {
		 Customer c=new Customer();
	     return new ModelAndView("/customer/DeleteCustomer", "Customer", c);
	 }

	 
	 @RequestMapping(value = "/customer/DeleteCustomer", method = RequestMethod.POST)
	 public String deleteCustomer(@ModelAttribute Customer customer,ModelMap model) throws NoSuchAlgorithmException, InvalidKeySpecException 
	 {		 
		  	Boolean auditStatus=false;
		 	try 
			{
		 		if(ApplicationContextUtils.getBankUserJDBCTemplate().checkUserReturned(customer.getUserName()))
		 		{
		 			Customer c= ApplicationContextUtils.getCustomerByUserName(customer.getUserName());
		 			Request r=new Request();
					r.setRequestType(Constants.DELETE_CUSTOMER_REQUEST);
					r.setRequestFrom(ApplicationContextUtils.getLoggedInUser().getUserId());
					r.setId(c.getUserId());
					ApplicationContextUtils.getRequestJDBCTemplate().createRequestToManager(r);
					auditStatus=true;
					String currentUserRole = ApplicationContextUtils.getLoggedInUser().getRoles().get(0).getName();
					if(currentUserRole.equals(Constants.SALES_MANAGER) )
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
					model.addAttribute("Msg","Invalid user!!");
				}
		 	
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.getMessage();
			} finally {
				String auditTrail=	(ApplicationContextUtils.getBankUserJDBCTemplate().getUserByUserName(customer.getUserName())).toString()+"AUDIT STATUS: "+auditStatus;
				Audit audit=ApplicationContextUtils.getAuditJDBCTemplate().setAuditRecord(Constants.DELETE_CUST_REQUEST, auditTrail, ApplicationContextUtils.getLoggedInUser().getUserId());
				ApplicationContextUtils.getAuditJDBCTemplate().createAudit(audit);
			}
	     return "results";
	 }
	 
	 @RequestMapping(value="/customer/addreciepient", method = RequestMethod.GET)
	 public String addRecipientView(Model model)
	 {
		 model.addAttribute("Add", 1);
		 model.addAttribute("Customer", ApplicationContextUtils.getLoggedInUserAsCustomer());
		 return "/customer/AddReceipient";
	 }
	 
	 
	 @RequestMapping(value="/customer/confirmPasscode", method = RequestMethod.POST)
	 public String confirmPassCode(@ModelAttribute("Customer") Customer customer,Model model,HttpServletRequest request,HttpServletResponse response)
	 {
		 Boolean auditStatus=false;
		 try {
			String passCode= request.getParameter("passCode");
			 String validHashPassCode= (String)request.getSession().getAttribute("passCode");
				BCryptPasswordEncoder bc= new BCryptPasswordEncoder();
				 String customerid = ApplicationContextUtils.getLoggedInUserAsCustomer().getCustomerID();
				if(bc.matches(passCode, validHashPassCode))
				{
					String username= (String)request.getSession().getAttribute("userName");
					ApplicationContextUtils.getRecipientJDBCTemplate().addReciepient(username, customerid);
					auditStatus=true;
					model.addAttribute("success", 1);
					model.addAttribute("Add", 1);
					model.addAttribute("message","Recipeint Added!");
				}
				else
				{
					model.addAttribute("success", 1);
					model.addAttribute("Add", 0);
					model.addAttribute("message","Invalid Passcode !");
				}
				 List<BankUser> recipientList = ApplicationContextUtils.getRecipientJDBCTemplate().getRecipient(customerid);
				 model.addAttribute("recipientList", recipientList);
				 model.addAttribute("customer", customer);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.getMessage();
		}finally {
			String auditTrail=ApplicationContextUtils.getLoggedInUserAsCustomer().toString()+"RECEPIENT: "+(String)request.getSession().getAttribute("userName")+"AUDIT STATUS: "+auditStatus;
			Audit audit=ApplicationContextUtils.getAuditJDBCTemplate().setAuditRecord(Constants.ADD_RECEIPENT, auditTrail, ApplicationContextUtils.getLoggedInUser().getUserId());
			ApplicationContextUtils.getAuditJDBCTemplate().createAudit(audit);
		}
		 
		 return "/customer/AddReceipient";
			 
	 }
	 
	 @RequestMapping(value="/customer/addreciepient", method = RequestMethod.POST)
	 public String addRecipient(@ModelAttribute("Customer") Customer customer,Model model,HttpServletRequest request,HttpServletResponse response)
	 {
		 model.addAttribute("Add", 1);
		 if(!(customer.getUserName().equals(ApplicationContextUtils.getLoggedInUser().getUserName())) )
		 {
			
			// Long no_of_users = ApplicationContextUtils.getBankUserJDBCTemplate().checkUserReturned(customer.getUserName());
		
			String isUserPresentAndIsCustomer = ApplicationContextUtils.getBankUserJDBCTemplate().checkCustomerUserReturned(customer.getUserName());

			 if(isUserPresentAndIsCustomer.equals(""))
			 {
				 String username = customer.getUserName();
				 Customer c= ApplicationContextUtils.getLoggedInUserAsCustomer();
				 String customerid = c.getCustomerID();
				 if(!ApplicationContextUtils.getRecipientJDBCTemplate().checkReciepeintAlreadyExsist(username, customerid))
				 {
					
					 model.addAttribute("success", 1);
					 model.addAttribute("Add", 0);
					 
					 model.addAttribute("message", "One Time Password has been sent to your Email.\n Please enter and validate.");
					 List<BankUser> recipientList = ApplicationContextUtils.getRecipientJDBCTemplate().getRecipient(customerid);
					
					 model.addAttribute("customer", customer);
					 String passCode=  new BigInteger(130, new SecureRandom()).toString(32);
						BCryptPasswordEncoder bc= new BCryptPasswordEncoder();
						String hashPassCode= bc.encode(passCode);
						request.getSession().setAttribute("passCode", hashPassCode);
						request.getSession().setAttribute("userName", username);
					 String Messgae=" Hi ,\n If you want to add the recipent, please paste the following code in the application.\n Passcode:- "+passCode;
					 MailMail mm = (MailMail) ApplicationContextUtils.getCtx().getBean("mailMail");
					   mm.sendMail("thebankapplication@gmail.com",c.getAddress().getEmailID(),"One Time Password", Messgae);
				 }
				 else
				 {
					 model.addAttribute("success", 0);
					 model.addAttribute("message", "recipient Already Exsist"); 
					 
				 }
			}
			 else
			 {
				 model.addAttribute("success", 0);
				 model.addAttribute("message", isUserPresentAndIsCustomer); 
			 }
		 }
		 else
		 {
			 //model.addAttribute("invalid", 1);
			 //model.addAttribute("message", "invalid user");
			 model.addAttribute("Msg", "sorry!!!! you can't add yourself as recipient");
			 return "/results";
		 }
		 return "/customer/AddReceipient";
	 }
	 
	 @RequestMapping(value="/transfer/fundtransfer", method = RequestMethod.GET)
	 public ModelAndView transferBalanceFormGet(ModelMap m)
	 {
		 FundTransferBackBean f = new FundTransferBackBean(ApplicationContextUtils.getLoggedInUserAsCustomer());
		 List<Customer> res= f.getRecipeint();
		 List<Account> accs= f.getAccounts();
		 m.addAttribute("recipeints",res);
		 m.addAttribute("accounts",accs);
		 return new ModelAndView("/customer/TransferFunds","FundTransferBackBean",f);
	 }
	 @RequestMapping(value="/customer/transfer", method = RequestMethod.POST)
	 public String transferBalance(@ModelAttribute("FundTransferBackBean") FundTransferBackBean f,Model model)
	 
	 {
		  Customer c= ApplicationContextUtils.getLoggedInUserAsCustomer();
		 String recipientUserName= f.getChosenrecipeint().getUserName();
		 String accountID = f.getChosenAccount().getAccountID();
		 String owner_custid = c.getCustomerID();
		 Long amount= f.getAmount();
		 Long recipentBalanace=null;
		 Account recipentAccount=null;
		 
		 
		 if(ApplicationContextUtils.getRecipientJDBCTemplate().checkReciepeintAlreadyExsist(recipientUserName, owner_custid))
		 {
			 Account a= ApplicationContextUtils.getAccountJDBCTemplate().getAccountByAccountID(accountID);
			 if(a.getBalance() >= amount)
			 {
				 Customer recipentCustomer= new Customer(recipientUserName);
				 for(Account acc:recipentCustomer.getAccounts())
				 {
					 if(acc.getAccountType().equals(Constants.CHECKINGS))
					 {
						 recipentAccount=acc;
						 recipentBalanace=acc.getBalance();
						 break;
					 }
				 }
				 if(amount <5000)
				 {
					 ApplicationContextUtils.getAccountJDBCTemplate().updateCheckingBalance(recipentCustomer.getCustomerID(),recipentBalanace+amount );
					 ApplicationContextUtils.getAccountJDBCTemplate().updateBalance(c.getCustomerID(),a.getBalance()-amount ,a.getAccountID());
					 
					
					 /*CREDIT TRANSACTION*/
					 Transaction creditTransaction= new Transaction();
					 creditTransaction.setAccountID(a.getAccountID());
					 creditTransaction.setToAccountID(recipentAccount.getAccountID());
					 creditTransaction.setAmount(amount);
					 creditTransaction.setStatus(Constants.COMPLETE);
					 creditTransaction.setTransactionType(Constants.CREDIT);
					 creditTransaction.setUserID(c.getUserId());
					
					 /*DEBIT TRANSACTION*/
					 Transaction debitTransaction= new Transaction();
					 debitTransaction.setTransactionType(Constants.DEBIT);
					 debitTransaction.setStatus(Constants.COMPLETE);
					 debitTransaction.setAmount(amount);
					 debitTransaction.setAccountID(a.getAccountID());
					 debitTransaction.setToAccountID(recipentAccount.getAccountID());
					 debitTransaction.setUserID(c.getUserId());
					 ApplicationContextUtils.getTransactionJDBCTemplate().createTransaction(creditTransaction);
					 ApplicationContextUtils.getTransactionJDBCTemplate().createTransaction(debitTransaction);

					 model.addAttribute("success", 1);
					 model.addAttribute("Msg", "funds transferred successfully");
				 }
				 else if(amount >= 5000)
				 {
						Request r = new Request();
						r.setRequestType(Constants.APPROVE_TRANSACTION);
						r.setRequestFrom(ApplicationContextUtils.getLoggedInUser().getUserId());
					
						r.setRequestTo(ApplicationContextUtils.getEmployeeJDBCTemplate().getTransactionEmployee().getUserId());
						r.setRequestStatus(Constants.PENDING);
						r.setRequest("Transaction Amount "+amount+"To "+recipentCustomer.getUserName());
/*						ApplicationContextUtils.getRequestJDBCTemplate().createRequest(r);*/
						
						 /*CREDIT TRANSACTION*/
						 Transaction debitTransaction= new Transaction();
						 debitTransaction.setTransactionType(Constants.DEBIT);
						 debitTransaction.setStatus(Constants.PENDING);
						 debitTransaction.setAmount(amount);
						 debitTransaction.setAccountID(a.getAccountID());
						 debitTransaction.setToAccountID(recipentAccount.getAccountID());
						 debitTransaction.setUserID(c.getUserId());
						 
						r.setId( ApplicationContextUtils.getTransactionJDBCTemplate().createTransaction(debitTransaction));
						 ApplicationContextUtils.getAccountJDBCTemplate().updateBalance(c.getCustomerID(),a.getBalance()-amount ,a.getAccountID());
						 ApplicationContextUtils.getRequestJDBCTemplate().createRequest(r);
						 
					
					 model.addAttribute("NeedConfirmation", 1);
					 model.addAttribute("Msg", "Need Approval from Transaction Team Member");
				 }
				 else if (amount >=50000)
				 {
					 	Request r = new Request();
						r.setRequestType(Constants.APPROVE_TRANSACTION);
						r.setRequestFrom(ApplicationContextUtils.getLoggedInUser().getUserId());
					
						r.setRequestTo(ApplicationContextUtils.getEmployeeJDBCTemplate().getTransactionManager().getUserId());
						r.setRequestStatus(Constants.PENDING);
						r.setRequest("Transaction Amount "+amount+"To "+recipentCustomer.getUserName());
/*						ApplicationContextUtils.getRequestJDBCTemplate().createRequest(r);*/
						
						 /*CREDIT TRANSACTION*/
						 Transaction debitTransaction= new Transaction();
						 debitTransaction.setTransactionType(Constants.DEBIT);
						 debitTransaction.setStatus(Constants.PENDING);
						 debitTransaction.setAmount(amount);
						 debitTransaction.setAccountID(a.getAccountID());
						 debitTransaction.setToAccountID(recipentAccount.getAccountID());
						 debitTransaction.setUserID(c.getUserId());
						 
						r.setId( ApplicationContextUtils.getTransactionJDBCTemplate().createTransaction(debitTransaction));
						 ApplicationContextUtils.getAccountJDBCTemplate().updateBalance(c.getCustomerID(),a.getBalance()-amount ,a.getAccountID());
						 ApplicationContextUtils.getRequestJDBCTemplate().createRequest(r);
					 
					 model.addAttribute("NeedConfirmation", 1);
					 model.addAttribute("Msg", "Need Approval from Transaction Manager");
				 }
				 else if(amount >= 100000)
				 {
						Request r = new Request();
						r.setRequestType(Constants.APPROVE_TRANSACTION);
						r.setRequestFrom(ApplicationContextUtils.getLoggedInUser().getUserId());
					
						r.setRequestTo(ApplicationContextUtils.getEmployeeJDBCTemplate().getCorporateEmployee().getUserId());
						r.setRequestStatus(Constants.PENDING);
						r.setRequest("Transaction Amount "+amount+"To "+recipentCustomer.getUserName());
/*						ApplicationContextUtils.getRequestJDBCTemplate().createRequest(r);*/
						
						 /*CREDIT TRANSACTION*/
						 Transaction debitTransaction= new Transaction();
						 debitTransaction.setTransactionType(Constants.DEBIT);
						 debitTransaction.setStatus(Constants.PENDING);
						 debitTransaction.setAmount(amount);
						 debitTransaction.setAccountID(a.getAccountID());
						 debitTransaction.setToAccountID(recipentAccount.getAccountID());
						 debitTransaction.setUserID(c.getUserId());
						 
						r.setId( ApplicationContextUtils.getTransactionJDBCTemplate().createTransaction(debitTransaction));
						 ApplicationContextUtils.getAccountJDBCTemplate().updateBalance(c.getCustomerID(),a.getBalance()-amount ,a.getAccountID());
						 ApplicationContextUtils.getRequestJDBCTemplate().createRequest(r);
					 
					 model.addAttribute("NeedConfirmation", 1);
					 model.addAttribute("Msg", "Need Approval from Corporate Team");
				 }
			 }
			 else
			 {
				 model.addAttribute("Msg", "Sorry!!!,you dont't have sufficient funds");
			 }
		 }
		 else
		 {
			 model.addAttribute("Msg", "The Recipent Does Not seem to be in your reciepent List!");
		 }
		 return "results";
	 }	
	@RequestMapping(value="/customer/viewbalance",method=RequestMethod.GET)
	public String viewBalance(Principal principal, Model model)
	{		
		 owner_accountList = ApplicationContextUtils.getAccountJDBCTemplate().getAccountsByCustomerID(((Customer)ApplicationContextUtils.getLoggedInUser()).getCustomerID());
		model.addAttribute("accountlist", owner_accountList);
		return "/customer/viewbalance";
	}
	
	@RequestMapping(value = "/customer/viewTransaction", method = RequestMethod.GET)
	 public String viewTransaction(Model model){
	 {
		List<Transaction> allTransactions = new ArrayList<Transaction>();
		 List<Account> accountList = ApplicationContextUtils.getAccountJDBCTemplate().getAccountsByCustomerID(ApplicationContextUtils.getLoggedInUserAsCustomer().getCustomerID());
		
		for(Account account: accountList)
		{
			List<Transaction> accountTypeTrans = ApplicationContextUtils.getTransactionJDBCTemplate().getTransationsByAccountId(account.getAccountID());
			for(Transaction trans : accountTypeTrans)
			{
				allTransactions.add(trans);
			}
		}
		
		
		 model.addAttribute("allTransactions", allTransactions);
			
		
		 return "/customer/viewTransaction";
	 }	 
	 
	}
}
