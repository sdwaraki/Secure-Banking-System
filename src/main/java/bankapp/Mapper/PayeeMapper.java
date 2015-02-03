package bankapp.Mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import bankapp.Objects.Payee;

public class PayeeMapper implements RowMapper<Payee> {

	@Override
	public Payee mapRow(ResultSet rs, int rowNum) throws SQLException {
		Payee payee = new Payee();
		payee.setCustomerid(rs.getString("customerid"));
		payee.setUserName(rs.getString("customername"));
		payee.setMerchantid(rs.getString("merchantid"));
		
		return payee;
	}

	
}
