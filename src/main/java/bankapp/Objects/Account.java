package bankapp.Objects;

import java.util.UUID;

import javax.validation.constraints.NotNull;

public class Account 
{
	String accountID;
	String accountType;
	Customer customer;
	@NotNull
	Long balance;
	
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public String getAccountID() {
		return accountID;
	}
	public void setAccountID(String accountID) {
		this.accountID = accountID;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public Long getBalance() {
		return balance;
	}
	public void setBalance(Long balance) {
		this.balance = balance;
	}
	public Account()
	{
		accountID= UUID.randomUUID().toString();
	}
	public String toString() {
		String string;
		System.out.println("------------getAccountID:"+getAccountID());
		System.out.println("------------getCustomer().getCustomerID():"+getCustomer().getCustomerID());
		System.out.println("------------getAccountType:"+getAccountType());
		System.out.println("------------getBalance:"+getBalance());


		string="AccountID: "+getAccountID()+"CustomerID: "+getCustomer().getCustomerID()+"AccountType: "+getAccountType()+"Balance: "+getBalance();
		
		return string;
	}
	
}
