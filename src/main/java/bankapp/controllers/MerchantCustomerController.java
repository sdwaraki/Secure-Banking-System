package bankapp.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import bankapp.Objects.Account;
import bankapp.Objects.ApplicationContextUtils;
import bankapp.Objects.BankUser;
import bankapp.Objects.Constants;
import bankapp.Objects.Customer;
import bankapp.Objects.Payee;
import bankapp.Objects.Request;
import bankapp.Objects.Transaction;
import bankapp.backbean.PaymentBackBean;

@Controller
public class MerchantCustomerController {
	
	String cust_name;
	 String customer_userid;
	 String customerid;
	
	 @RequestMapping(value = "/merchant/addpayee", method = RequestMethod.GET)
	 public String addPayeeForm(Model model)
	 {
		 model.addAttribute("Customer", new Customer());
	
		 return "/merchant/addpayee";
	 }
	 
	 @RequestMapping(value = "/merchant/addpayee", method = RequestMethod.POST)
	 public String addPayee(@ModelAttribute("Customer")Customer customer, Model model)
	 {
		 customerid = null;
		 customer_userid = null;
		 cust_name = customer.getUserName();
		 String result;
		 
		if(!cust_name.equals(ApplicationContextUtils.getLoggedInUser().getUserName()))
		 {
			if(ApplicationContextUtils.getBankUserJDBCTemplate().checkUserReturned(customer.getUserName()))
			{
				Customer c = ApplicationContextUtils.getCustomerByUserName(customer.getUserName());
				customerid = c.getCustomerID();
				BankUser user = ApplicationContextUtils.getBankUserJDBCTemplate().getUserByUserName(customer.getUserName());
				customer_userid = user.getUserId();
			}
		
			 result = ApplicationContextUtils.getBankUserJDBCTemplate().checkCustomerUserReturned(customer.getUserName());
	
			 if(result.equals(""))
			 {	
				 if(!ApplicationContextUtils.getPayeeJDBCTemplate().checkReciepeintAlreadyExsist(customer.getUserName(), customerid))
				 {

					 ApplicationContextUtils.getRequestJDBCTemplate().createRequestToCustomer(customer_userid,cust_name);
				 }
				 
				 else
				 {
					 model.addAttribute("Msg", "Payee already added");
					 return "/results";
				 }
			 }
		 }
		else
		{
			model.addAttribute("Msg","Sorry!!! You are trying to add yourself as payee" );
			return "/results";
		}
		
		 model.addAttribute("Msg", "request is sent, wait till customer approves the request!!!");
		
		 
		 return "/results";
	 }
	 
	 @RequestMapping(value = "/merchant/payment", method = RequestMethod.GET)
	 public String paymentForm(Model model)
	 {
		List<String> payeeList = new ArrayList<String>(); 
		List<Account> accountList = new ArrayList<Account>();
		
		
		//populatePaymentBackBeanLists(payeeList, accountList);
		
		 List<Payee> payeeDBlist  = ApplicationContextUtils.getPayeeJDBCTemplate().getPayees(ApplicationContextUtils.getLoggedInUser().getUserId());
		 accountList = ApplicationContextUtils.getAccountJDBCTemplate().getAccountsByCustomerID(ApplicationContextUtils.getLoggedInUserAsCustomer().getCustomerID());
		 for(Payee p: payeeDBlist)
			{
				payeeList.add(p.getUserName());
			}
		
		
		model.addAttribute("payeeList", payeeList);
		model.addAttribute("accountList", accountList);
		model.addAttribute("PaymentBackBean", new PaymentBackBean());
		
		 return "/merchant/payment";
	 }
	 
	 @RequestMapping(value = "/merchant/payment", method = RequestMethod.POST)
	 public String payment(@ModelAttribute("PaymentBackBean")PaymentBackBean paymentBackBean, Model model){
		 
		List<String> payeeList = new ArrayList<String>(); 
		List<Account> accountList = new ArrayList<Account>();
			
			
		//populatePaymentBackBeanLists(payeeList, accountList);
		
		 List<Payee> payeeDBlist  = ApplicationContextUtils.getPayeeJDBCTemplate().getPayees(ApplicationContextUtils.getLoggedInUser().getUserId());
		 accountList = ApplicationContextUtils.getAccountJDBCTemplate().getAccountsByCustomerID(ApplicationContextUtils.getLoggedInUserAsCustomer().getCustomerID());
		 for(Payee p: payeeDBlist)
			{
				payeeList.add(p.getUserName());
			}

		model.addAttribute("payeeList", payeeList);
		model.addAttribute("accountList", accountList);
		model.addAttribute("PaymentBackBean", paymentBackBean);

		 
		String merchantAccountID = paymentBackBean.getAccount().getAccountID();
		String username = paymentBackBean.getPayee().getUserName();
		Long amount = paymentBackBean.getAmount();
		
		
		 if(ApplicationContextUtils.getPayeeJDBCTemplate().checkPayeeAlreadyExists(username, ApplicationContextUtils.getLoggedInUser().getUserId()) > 0)
		 {
			 
			 
			 List<Account> merchantAccounts = ApplicationContextUtils.getAccountJDBCTemplate().getAccountsByCustomerID(ApplicationContextUtils.getLoggedInUserAsCustomer().getCustomerID());
			Long merchantBalance = null;
			Account merchantAccount = null;
			for(Account c: merchantAccounts)
			{
				if(c.getAccountType().equals(Constants.CHECKINGS))
				{
					merchantAccount = c;
					merchantBalance = c.getBalance();
				}
			}
			
			 String payee_userid= null;
			 Customer customer = null;
			 payee_userid = ApplicationContextUtils.getPayeeJDBCTemplate().getPayeeCustomerID(username);
			 customer = ApplicationContextUtils.getCustomerJDBCTemplate().getCustomerbyUserId(payee_userid);
			
			
			List<Account> payeeAccounts = ApplicationContextUtils.getAccountJDBCTemplate().getAccountsByCustomerID(customer.getCustomerID());
			Account payeeAccount = null;
			Long payeeAccBalance = null;
			for(Account c: payeeAccounts)
			{
				if(c.getAccountType().equals(Constants.CHECKINGS))
				{
					payeeAccount = c;
					payeeAccBalance = c.getBalance();
					break;
				}
			}
			
			if(payeeAccBalance > amount)
			{
				if(amount < 5000)
				{
					ApplicationContextUtils.getAccountJDBCTemplate().updateCheckingBalance(customer.getCustomerID(),payeeAccBalance-amount );
					ApplicationContextUtils.getAccountJDBCTemplate().updateBalance(ApplicationContextUtils.getLoggedInUserAsCustomer().getCustomerID(), merchantBalance+amount, merchantAccountID);
				
					//CREDIT TRANSACTION
					 Transaction creditTransaction= new Transaction();
					 creditTransaction.setAccountID(payeeAccount.getAccountID());
					 creditTransaction.setToAccountID(merchantAccountID);
					 creditTransaction.setAmount(merchantBalance);
					 creditTransaction.setStatus(Constants.COMPLETE);
					 creditTransaction.setTransactionType(Constants.CREDIT);
					 creditTransaction.setUserID(payeeAccount.getAccountID());
				
					 //DEBIT TRANSACTION
					 Transaction debitTransaction= new Transaction();
					 debitTransaction.setTransactionType(Constants.DEBIT);
					 debitTransaction.setStatus(Constants.COMPLETE);
					 debitTransaction.setAmount(amount);
					 debitTransaction.setAccountID(payeeAccount.getAccountID());
					 debitTransaction.setToAccountID(merchantAccountID);
					 debitTransaction.setUserID(payeeAccount.getAccountID());
					 
					 ApplicationContextUtils.getTransactionJDBCTemplate().createTransaction(debitTransaction);
					 ApplicationContextUtils.getTransactionJDBCTemplate().createTransaction(creditTransaction);
					 
					 model.addAttribute("success", 1);
					 model.addAttribute("Msg", "funds transferred successfully");
				
				}
				else if(amount >= 5000 && amount < 50000)
				{

					Request r = new Request();
					r.setRequestType(Constants.APPROVE_TRANSACTION);
					r.setRequestFrom(ApplicationContextUtils.getLoggedInUser().getUserId());
				
					r.setRequestTo(ApplicationContextUtils.getEmployeeJDBCTemplate().getTransactionEmployee().getUserId());
					r.setRequestStatus(Constants.PENDING);
					r.setRequest("Transaction Amount "+amount+"To "+ApplicationContextUtils.getLoggedInUser().getUserName());
					
					//CREDIT TRANSACTION
					Transaction debitTransaction= new Transaction();
					 debitTransaction.setTransactionType(Constants.DEBIT);
					 debitTransaction.setStatus(Constants.PENDING);
					 debitTransaction.setAmount(amount);
					 debitTransaction.setAccountID(payeeAccount.getAccountID());
					 debitTransaction.setToAccountID(merchantAccountID);
					 debitTransaction.setUserID(payeeAccount.getAccountID());
					 
					r.setId( ApplicationContextUtils.getTransactionJDBCTemplate().createTransaction(debitTransaction));

					ApplicationContextUtils.getAccountJDBCTemplate().updateBalance(customer.getCustomerID(), payeeAccBalance-amount, payeeAccount.getAccountID());
					ApplicationContextUtils.getRequestJDBCTemplate().createRequest(r);

					 model.addAttribute("NeedConfirmation", 1);
					 model.addAttribute("Msg", "Need Approval from Transaction Team Member");	
				}
				
				else if (amount >= 50000 && amount < 100000)
				{
					System.out.println("--------------50000");
					 Request r = new Request();
					 r.setRequestType(Constants.APPROVE_TRANSACTION);
					 r.setRequestFrom(ApplicationContextUtils.getLoggedInUser().getUserId());
					 r.setRequestTo(payee_userid);
					 r.setRequestStatus(Constants.PENDING);
					 r.setRequest("Transaction Amount "+amount+"To "+ApplicationContextUtils.getLoggedInUser().getUserName());
					 
					 //CREDIT TRANSACTION
					 Transaction debitTransaction= new Transaction();
					 debitTransaction.setTransactionType(Constants.DEBIT);
					 debitTransaction.setStatus(Constants.PENDING);
					 debitTransaction.setAmount(amount);
					 debitTransaction.setAccountID(payeeAccount.getAccountID());
					 debitTransaction.setToAccountID(merchantAccountID);
					 debitTransaction.setUserID(payeeAccount.getAccountID());
					 
					 r.setId( ApplicationContextUtils.getTransactionJDBCTemplate().createTransaction(debitTransaction));

					 ApplicationContextUtils.getAccountJDBCTemplate().updateBalance(customer.getCustomerID(), payeeAccBalance-amount, payeeAccount.getAccountID());
					 ApplicationContextUtils.getRequestJDBCTemplate().createRequest(r);
					 
					 model.addAttribute("NeedConfirmation", 1);
					 model.addAttribute("Msg", "Need Approval from Transaction Manager");
				
				}
				
				 else if(amount >= 100000)
				 {

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
		 		model.addAttribute("Msg", "The Payee Does Not seem to be in your payee List!");

		 }
		return "/results";
	 
	}
	 
	
	 
	 void populatePaymentBackBeanLists(List<String> payeeList, List<Account> accountList ){
		 
		 List<Payee> payeeDBlist  = ApplicationContextUtils.getPayeeJDBCTemplate().getPayees(ApplicationContextUtils.getLoggedInUser().getUserId());
		 accountList = ApplicationContextUtils.getAccountJDBCTemplate().getAccountsByCustomerID(ApplicationContextUtils.getLoggedInUserAsCustomer().getCustomerID());
		 for(Payee p: payeeDBlist)
			{
				payeeList.add(p.getUserName());
			}
	 }
	 
	 
	 @RequestMapping(value = "/merchant/viewTransaction", method = RequestMethod.GET)
	 public String viewTransaction(@ModelAttribute("PaymentBackBean")PaymentBackBean paymentBackBean, Model model){
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
		// List<Transaction> transactions = ApplicationContextUtils.getTransactionJDBCTemplate().getTransationsByAccountId(paymentBackBean.getAccount().getAccountID());
			
		
		 return "/merchant/viewTransaction";
	 }	 
	 
}
}
