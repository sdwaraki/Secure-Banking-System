package bankapp.JDBCTemplates;

import java.util.UUID;

import bankapp.DAO.AddressDAO;
import bankapp.Mapper.AddressMapper;
import bankapp.Objects.Address;
import bankapp.Objects.BankUser;

public class AddressJDBCTemplate extends JDBCTemplateClass implements AddressDAO {

	@Override
	public Address getAddressbyAddressId(String addressID) {
		String SQL = "select * from address where addressID = ?";
	    Address address = jdbcTemplateObject.queryForObject(SQL, 
	                        new Object[]{addressID}, new AddressMapper());
	    return address;
	}

	@Override
	public String addAddress(BankUser user) {
		Address a=user.getAddress();
		String SQL = "insert into address (addressID, addressLine, city, zip, phoneNumber, emailID,userID) values (?,?,?,?,?,?,?)";
		String id=UUID.randomUUID().toString();
		jdbcTemplateObject.update( SQL, id, a.getAddressLine(), a.getCity(), a.getZip(), a.getPhoneNumber(), a.getEmailID(),user.getUserId());

		SQL="update user set addressID= ? where userID= ?";
		jdbcTemplateObject.update( SQL, id,user.getUserId());
		return id;
	}

	@Override
	public void deleteAddressbyAddressId(String addressID) {
		String SQL = "delete from address where addressID=?";
		jdbcTemplateObject.update( SQL, addressID);
	}

	@Override
	public Address getAddressbyUserId(String userId) 
	{
		String SQL = "select * from address where addressID = (select addressID from user where userId = ?)";
	    Address address = jdbcTemplateObject.queryForObject(SQL, 
	                        new Object[]{userId}, new AddressMapper());
	    return address;
		//return null;
	}

	@Override
	public void updateAddress(Address a) 
	{
		String SQL= "update address set addressLine=?,city=?,zip=?,phoneNumber=? where addressID=?";
		jdbcTemplateObject.update(SQL, 
                 new Object[]{a.getAddressLine(),a.getCity(),a.getZip(),a.getPhoneNumber(),a.getAddressID()});
	}
	
	
	
}
