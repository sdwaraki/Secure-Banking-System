package bankapp.DAO;

import java.util.List;

import bankapp.Objects.BankUser;
import bankapp.Objects.Customer;

public interface RecipientDAO {
	
	public void addReciepient(String username,String customerid);
	public List<BankUser> getRecipient(String customerid);
	public List<Customer> getRecipientAsCustomer(String customerid);
}
