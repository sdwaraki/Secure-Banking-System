package bankapp.Mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.jdbc.core.RowMapper;

import bankapp.Objects.BankUser;

public class BankUserMapper  implements RowMapper<BankUser>  
{

	@Override
	public BankUser mapRow(ResultSet rs, int rowNum) throws SQLException 
	{
		BankUser bankUser= new BankUser();
		DateFormat df = new SimpleDateFormat("yyyy-mm-dd");
		//bankUser.setAddress(ApplicationContextUtils.getAddressJDBCTemplate().getAddressbyAddressId(rs.getString("addressID")));
		bankUser.setCreadtedBy(rs.getString("createdBy"));
		bankUser.setCreationTime(rs.getString("creationTimeStamp"));
		try 
		{
			bankUser.setDob((df.parse(rs.getString("DoB"))));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		bankUser.setPassword(rs.getString("password"));
		bankUser.setEnabled(rs.getBoolean("enabled"));
		bankUser.setFirstName(rs.getString("fName"));
		bankUser.setLastName(rs.getString("lName"));
		bankUser.setSsn(rs.getString("SSN"));
		bankUser.setUserId(rs.getString("userID"));
		bankUser.setUserName(rs.getString("userName"));
		bankUser.setUserType(rs.getString("userType"));
		return bankUser;
	}
	

}
