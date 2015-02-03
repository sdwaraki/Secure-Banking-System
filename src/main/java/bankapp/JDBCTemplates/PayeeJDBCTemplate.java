package bankapp.JDBCTemplates;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import bankapp.DAO.PayeeDAO;
import bankapp.Mapper.BankUserMapper;
import bankapp.Mapper.PayeeMapper;
import bankapp.Objects.ApplicationContextUtils;
import bankapp.Objects.BankUser;
import bankapp.Objects.Customer;
import bankapp.Objects.Payee;

public class PayeeJDBCTemplate extends JDBCTemplateClass implements PayeeDAO {

	@Override
	public void addPayee(String username, String customerid, String merchantid) {
			
		System.out.println("=================="+merchantid);
		Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
		String SQL = "insert into payee(customername,customerid,merchantid,createdBy,creationTimeStamp) values (?,?,?,?,?)";
		Object[] params = new Object[]{username,customerid,merchantid,merchantid,date};
		jdbcTemplateObject.update(SQL, params);
		
	}

	@Override
	public List<BankUser> getPayee(String customerid) {
		String SQL = "select * from user where userName IN (select username from payee where customerid=?);";
		List<BankUser> payeelist = jdbcTemplateObject.query(SQL, new Object[]{customerid}, new BankUserMapper());
		return payeelist;
	
	}

	@Override
	public List<Customer> getPayeeAsCustomer(String customerid) {
		List<Customer> cust= new ArrayList<Customer>();
		String SQL = "select userName from user where userName IN (select username from payee where customerid=?);";
		List<String> recipientlist = jdbcTemplateObject.queryForList(SQL, new Object[]{customerid},String.class);
		for(String uname:recipientlist)
		{
			cust.add(new Customer(uname));
		}
		return cust;

	}

	 public boolean checkReciepeintAlreadyExsist(String username,
				String customerid) {
			 String SQL="Select Count(*) from payee where customername=? and customerid=?";
			 Long l=jdbcTemplateObject.queryForLong(SQL,new Object[]{username,customerid});
			 if (l>0)
				 return true;
			 else
				 return false;
		}
	 
	
	 public List<Payee> getPayees(String merchantid){
		 
		 String SQL = "select * from payee where merchantid=?";
		 List<Payee> payeelist = jdbcTemplateObject.query(SQL, new Object[]{merchantid}, new PayeeMapper());
		
		 return payeelist;
	 }

	public Long checkPayeeAlreadyExists(String payeename, String merchantid){
		
		String SQL = "select count(*) from payee where customername=? and merchantid=?";
		Long l = jdbcTemplateObject.queryForLong(SQL, new Object[]{payeename,merchantid});
		
		return l;
	}
	
	public String getPayeeCustomerID(String payee_username)
	{
		String SQL = "select customerid from payee where customername=?";
		return (String)jdbcTemplateObject.queryForObject(SQL, new Object[]{payee_username},String.class);
		
	}

}
