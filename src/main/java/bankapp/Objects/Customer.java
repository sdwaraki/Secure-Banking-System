package bankapp.Objects;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;


public class Customer extends BankUser
{

	String customerID;
	String CustomerType;
	@Valid
	List<Account> accounts;
	public List<Account> getAccounts() {
		return accounts;
	}
	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}
	public String getCustomerID() {
		return customerID;
	}
	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}
	public String getCustomerType() {
		return CustomerType;
	}
	public void setCustomerType(String customerType) {
		CustomerType = customerType;
	}
	public Customer()
	{
		accounts= new ArrayList<Account>();
	}
	public Customer(String userName)
	{
		super(userName);
		Customer temp=ApplicationContextUtils.getCustomerJDBCTemplate().getCustomerByUserName(userName);
		this.customerID=temp.getCustomerID();

		this.CustomerType=temp.getCustomerType();

		this.setAccounts(temp.getAccounts());

	}
	public String toString() {
		String string=super.toString();
		string = "CustomerID: "+getCustomerID()+"Customer Type:"+getCustomerType();
		return string;
	}
}
