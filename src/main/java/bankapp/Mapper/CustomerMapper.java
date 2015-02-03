package bankapp.Mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import bankapp.Objects.Customer;

public class CustomerMapper implements RowMapper<Customer> {

	@Override
	public Customer mapRow(ResultSet rs, int rowNum) throws SQLException 
	{
		Customer c= new Customer();
		c.setCustomerID(rs.getString("customerID"));
		c.setCustomerType(rs.getString("customerType"));
		c.setUserId(rs.getString("userID"));
		return c;
	}
	

}
