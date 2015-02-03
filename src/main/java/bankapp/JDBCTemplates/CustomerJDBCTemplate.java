package bankapp.JDBCTemplates;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.UUID;

import org.springframework.dao.DataAccessException;

import bankapp.DAO.CustomerDAO;
import bankapp.Mapper.CustomerMapper;
import bankapp.Objects.Account;
import bankapp.Objects.ApplicationContextUtils;
import bankapp.Objects.Audit;
import bankapp.Objects.Constants;
import bankapp.Objects.Customer;
import bankapp.Objects.Request;

public class CustomerJDBCTemplate extends JDBCTemplateClass implements CustomerDAO
{

	@Override
	public Customer getCustomerById(String Id) 
	{
		
		String SQL = "select * from customer where customerID =?";
		return jdbcTemplateObject.queryForObject(SQL, new Object[]{Id},new CustomerMapper());
		//return null;
	}
	
	@Override
	public Customer getCustomerbyUserId(String userid){
		
		String SQL = "select * from customer where userID=?";
		
		return jdbcTemplateObject.queryForObject(SQL, new Object[]{userid},new CustomerMapper());
		
	}

	@Override
	public void createCustomer(Customer c) 
	{
		Boolean auditStatus = false;
		String auditTrail=new String();
		try {
			String SQL = "insert into customer (customerID, userID, customerType) values (?,?,?)";
			String id=UUID.randomUUID().toString();
			jdbcTemplateObject.update( SQL, id, c.getUserId(), c.getCustomerType());
			auditTrail=(ApplicationContextUtils.getBankUserJDBCTemplate().getUserByUserId(c.getUserId())).toString();
			/*CREATTING CHECKINGS ACCOUNT*/
			c.setCustomerID(id);
			Account checkings= new Account();
			checkings.setAccountType(Constants.CHECKINGS);
			checkings.setCustomer(c);
			checkings.setBalance( c.getAccounts().get(0).getBalance());
			
			ApplicationContextUtils.getAccountJDBCTemplate().createAccount(checkings);
			auditTrail+=checkings.toString();	
 
			/*CREATING SAVINGS ACCOUNT*/
			Account savings= new Account();
			savings.setAccountType(Constants.SAVINGS);
			savings.setCustomer(c);
			savings.setBalance( c.getAccounts().get(1).getBalance());
   
				
			ApplicationContextUtils.getAccountJDBCTemplate().createAccount(savings);
			auditTrail+=savings.toString();
			auditStatus = true;
		} catch (Exception e) 
		{
			e.getMessage();
		} finally {
			auditTrail+=" AUDIT STATUS: "+auditStatus;
			Audit audit=ApplicationContextUtils.getAuditJDBCTemplate().setAuditRecord(Constants.ADD_CUSTOMER_AND_ACCOUNTS, auditTrail, ApplicationContextUtils.getLoggedInUser().getUserId());
			ApplicationContextUtils.getAuditJDBCTemplate().createAudit(audit);
		}
        
 
        /*RAISING A REQUEST TO ADMIN*/
        Request r=new Request();
		r.setRequestType(Constants.ADD_CUSTOMER_REQUEST);
		r.setRequestFrom(ApplicationContextUtils.getLoggedInUser().getUserId());
		r.setId(c.getUserId());
		ApplicationContextUtils.getRequestJDBCTemplate().createRequestToManager(r);
		
	}

	@Override
	public void deleteCustomer(Customer c) {
		//String SQL="select userID from customer where customerID=?";
		
		String SQL = "delete from customer where customerID=?";
		jdbcTemplateObject.update( SQL, c.getCustomerID());
	}
/*
	@Override
	public void modifyCustomer(Customer c) {
		String SQL = "select  from customer where customerID=?";
		BankUser bankUser=jdbcTemplateObject.queryForObject(SQL,new Object[]{c.getCustomerID()}, new BankUserMapper());
		
		//SQL = "select addressID from user where userID=?";
		//Address address=jdbcTemplateObject.queryForObject(SQL, new Object[]{bankUser.getAddress().getAddressID()}, new AddressMapper());

		SQL = "insert into address (addressLine, city, zip, phoneNumber, emailID) values (?,?,?,?,?) where addressID=?";
		jdbcTemplateObject.update( SQL, c.getAddress().getAddressLine(), c.getAddress().getCity(),
				c.getAddress().getZip(), c.getAddress().getPhoneNumber(), c.getAddress().getEmailID(), 
				bankUser.getAddress().getAddressID());
		
		
	}
	*/

	@Override
	public Customer getCustomerByUserName(String userName) 
	{
		String SQL = "select * from customer where userID = (select userID from user where userName = ?)";
		Customer c= jdbcTemplateObject.queryForObject(SQL, new Object[]{userName},new CustomerMapper());
		c.setAccounts(ApplicationContextUtils.getAccountJDBCTemplate().getAccountsByCustomerID(c.getCustomerID()));
		return c;
	}
	
	
}
