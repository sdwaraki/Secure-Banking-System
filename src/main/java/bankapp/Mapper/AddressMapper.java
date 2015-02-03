package bankapp.Mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import bankapp.Objects.Address;

public class AddressMapper implements RowMapper<Address> {

	@Override
	public Address mapRow(ResultSet rs, int rowNum) throws SQLException {
		Address address = new Address();
		address.setAddressID(rs.getString("addressID"));
		address.setAddressLine(rs.getString("addressLine"));
		address.setCity(rs.getString("city"));
		address.setZip(Integer.parseInt(rs.getString("zip")));
		address.setPhoneNumber(rs.getString("phoneNumber"));
		address.setEmailID(rs.getString("emailID"));
		return address;
	}

}
