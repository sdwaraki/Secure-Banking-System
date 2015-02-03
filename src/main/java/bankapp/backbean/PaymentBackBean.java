package bankapp.backbean;

import java.util.List;

import bankapp.Objects.Account;
import bankapp.Objects.Payee;


public class PaymentBackBean {
	
	Payee payee;
	Account account;
	Long amount;
	
	public Long getAmount() {
		return amount;
	}
	public void setAmount(Long amount) {
		this.amount = amount;
	}
	
	public Payee getPayee() {
		return payee;
	}
	public void setPayee(Payee payee) {
		this.payee = payee;
	}
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	
	
	
	List<Account> accountList;
	/*List<Payee> payeeList;
	
	Account chosenAccount;
	Payee payee;
	
	public List<Account> getAccountList() {
		return accountList;
	}
	public void setAccountList(List<Account> accountList) {
		this.accountList = accountList;
	}
	
	public Account getChosenAccount() {
		return chosenAccount;
	}
	public void setChosenAccount(Account chosenAccount) {
		this.chosenAccount = chosenAccount;
	}
	public Customer getPayee() {
		return payee;
	}
	
	String userName;
	String accountName;
	String amount;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	
	public PaymentBackBean(){
		
		accountList = new ArrayList<Account>();
		payeeList = new ArrayList<Payee>();
		chosenAccount = new Account();
		payee = new Payee();
	}
	
	public PaymentBackBean(BankUser){
		
		accountList = ApplicationContextUtils.getAccountJDBCTemplate().getAccountsByCustomerID(c.getCustomerID());
		payeeList = ApplicationContextUtils.getPayeeJDBCTemplate().getPayees(c.getCustomerID());
		
		
		
		
	}
	public List<Payee> getPayeeList() {
		return payeeList;
	}
	public void setPayeeList(List<Payee> payeeList) {
		this.payeeList = payeeList;
	}
	public void setPayee(Payee payee) {
		this.payee = payee;
	}*/

}
