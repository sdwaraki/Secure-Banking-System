package bankapp.Mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import bankapp.Objects.Transaction;;

public class TransactionMapper implements RowMapper<Transaction> {

	@Override
	public Transaction mapRow(ResultSet rs, int rowNum) throws SQLException {
		DateFormat df = new SimpleDateFormat("HH:mm:ss");
		Transaction trans = new Transaction();
		try {
			trans.setTransactionID(rs.getString("transactionID"));
			trans.setTransactionType(rs.getString("transactionType"));
			trans.setAccountID(rs.getString("accountID"));
			trans.setTimeOfTansaction(df.parse(rs.getString("transactionTimeStamp")));
			trans.setToAccountID(rs.getString("toaccountID"));
			trans.setAmount(Long.parseLong(rs.getString("amount")));
			trans.setStatus(rs.getString("status"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return trans;
	}
}
