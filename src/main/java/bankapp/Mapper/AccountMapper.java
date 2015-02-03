package bankapp.Mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import bankapp.Objects.Account;
import bankapp.Objects.ApplicationContextUtils;

public class AccountMapper implements RowMapper<Account> {

	@Override
	public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
		Account account = new Account();
		account.setAccountID(rs.getString("accountID"));
		account.setAccountType(rs.getString("accountType"));
		account.setCustomer(ApplicationContextUtils.getCustomerJDBCTemplate().getCustomerById(rs.getString("customerID")));
		account.setBalance(Long.parseLong(rs.getString("balance")));
		return account;
	}
	
}
