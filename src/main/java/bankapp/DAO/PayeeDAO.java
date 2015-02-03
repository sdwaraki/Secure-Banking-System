package bankapp.DAO;

import java.util.List;

import bankapp.Objects.BankUser;
import bankapp.Objects.Customer;
import bankapp.Objects.Payee;

public interface PayeeDAO {
	
	public void addPayee(String username,String customerid, String merchantid);
	public List<BankUser> getPayee(String customerid);
	public List<Customer> getPayeeAsCustomer(String customerid);


}
