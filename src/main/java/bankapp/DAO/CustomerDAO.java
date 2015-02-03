package bankapp.DAO;

import bankapp.Objects.Customer;

public interface CustomerDAO 
{
	public Customer getCustomerById(String Id);
	public void createCustomer(Customer c);
	public void deleteCustomer(Customer c);
	public Customer getCustomerByUserName(String Id);
	public Customer getCustomerbyUserId(String userid);
	//public void modifyCustomer(Customer c);

}
