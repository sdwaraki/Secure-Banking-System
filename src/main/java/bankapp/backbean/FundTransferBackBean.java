package bankapp.backbean;

import java.util.ArrayList;
import java.util.List;

import bankapp.Objects.Account;
import bankapp.Objects.ApplicationContextUtils;
import bankapp.Objects.Customer;

public class FundTransferBackBean 
{
	
	List<Account> accounts;
	public List<Account> getAccounts() {
		return accounts;
	}
	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}
	public List<Customer> getRecipeint() {
		return recipeint;
	}
	public void setRecipeint(List<Customer> recipeint) {
		this.recipeint = recipeint;
	}
	public Customer getC() {
		return c;
	}
	public void setC(Customer c) {
		this.c = c;
	}
	public Customer getChosenrecipeint() {
		return Chosenrecipeint;
	}
	public void setChosenrecipeint(Customer chosenrecipeint) {
		Chosenrecipeint = chosenrecipeint;
	}
	public Account getChosenAccount() {
		return ChosenAccount;
	}
	public void setChosenAccount(Account chosenAccount) {
		ChosenAccount = chosenAccount;
	}
	public Long getAmount() {
		return Amount;
	}
	public void setAmount(Long amount) {
		Amount = amount;
	}
	List<Customer> recipeint;
	Customer c;
	Customer Chosenrecipeint;
	Account ChosenAccount;
	Long Amount;
	public FundTransferBackBean()
	{
		accounts = new ArrayList<Account>();
		recipeint= new ArrayList<Customer>();
		c= new Customer();
		ChosenAccount= new Account();
		Chosenrecipeint= new Customer();
	}
	public FundTransferBackBean(Customer c)
	{
		c= ApplicationContextUtils.getLoggedInUserAsCustomer();
		accounts= ApplicationContextUtils.getAccountJDBCTemplate().getAccountsByCustomerID(c.getCustomerID());
		recipeint= ApplicationContextUtils.getRecipientJDBCTemplate().getRecipientAsCustomer(c.getCustomerID());
	}
	
}
