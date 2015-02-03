package bankapp.JDBCTemplates;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import bankapp.DAO.RecipientDAO;
import bankapp.Mapper.BankUserMapper;
import bankapp.Objects.BankUser;
import bankapp.Objects.Customer;

public class RecipientJDBCTemplate extends JDBCTemplateClass implements RecipientDAO {

	@Override
	public void addReciepient(String username,String customerid) {
		
		Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
		String SQL = "insert into recipient(username,customerid,createdBy,creationTimeStamp) values (?,?,?,?)";
		Object[] params = new Object[]{username,customerid,customerid,date};
		jdbcTemplateObject.update(SQL, params);
		
	}

	 public boolean checkReciepeintAlreadyExsist(String username,
			String customerid) {
		 String SQL="Select Count(*) from recipient where username=? and customerid=?";
		 Long l=jdbcTemplateObject.queryForLong(SQL,new Object[]{username,customerid});
		 if (l>0)
			 return true;
		 else
			 return false;
	}

	@Override 
	public List<BankUser> getRecipient(String customerid)
	{
		String SQL = "select * from user where userName IN (select username from recipient where customerid=?);";
		List<BankUser> recipientlist = jdbcTemplateObject.query(SQL, new Object[]{customerid}, new BankUserMapper());
		return recipientlist;
	}

	@Override
	public List<Customer> getRecipientAsCustomer(String customerid)
	{
		List<Customer> cust= new ArrayList<Customer>();
		String SQL = "select userName from user where userName IN (select username from recipient where customerid=?);";
		List<String> recipientlist = jdbcTemplateObject.queryForList(SQL, new Object[]{customerid},String.class);
		for(String uname:recipientlist)
		{
			cust.add(new Customer(uname));
		}
		return cust;
	}

}
