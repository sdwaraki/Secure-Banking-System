package bankapp.JDBCTemplates;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import bankapp.DAO.TransactionDAO;
import bankapp.Mapper.TransactionMapper;
import bankapp.Objects.ApplicationContextUtils;
import bankapp.Objects.Constants;
import bankapp.Objects.Transaction;

public class TransactionJDBCTemplate extends JDBCTemplateClass implements TransactionDAO
{
	@Override
	public String createTransaction(Transaction t) 
	{
		String SQL = "insert into transaction (transactionID, transactionType, transactionTimeStamp, accountID,amount,status,toaccountID) values (?,?,?,?,?,?,?)";
		String id;
		if(t.getTransactionID()== null ||t.getTransactionID().equals(""))
			id=UUID.randomUUID().toString();
		else
			id=t.getTransactionID();
		
		Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
        jdbcTemplateObject.update( SQL, id, t.getTransactionType(), date, t.getAccountID(),t.getAmount(),t.getStatus(),t.getToAccountID());
        return id;
	}
	@Override
	public List<Transaction> getTransationsByAccountId(String accountId) {
		String SQL="select * from transaction where accountID=? or toaccountID=?";
		return jdbcTemplateObject.query(SQL,new Object[]{accountId,accountId},new TransactionMapper());
	}
	@Override
	public void updateTransactionStatus(Transaction a) 
	{
		String SQL="update transaction set status=?";
		if(a.getStatus().equals(Constants.APPROVED))
		{
			System.out.println("--------------------in updateTransactionStatus");
			 Transaction creditTransaction= new Transaction();
			 creditTransaction.setAccountID(a.getAccountID());
			 creditTransaction.setToAccountID(a.getToAccountID());
			 creditTransaction.setAmount(a.getAmount());
			 creditTransaction.setStatus(Constants.COMPLETE);
			 creditTransaction.setTransactionType(Constants.DEBIT);
			 creditTransaction.setUserID(a.getUserID());
			Long balance= ApplicationContextUtils.getAccountJDBCTemplate().getAccountByAccountID(a.getToAccountID()).getBalance();
			 ApplicationContextUtils.getAccountJDBCTemplate().updateCheckingBalanceByAccount(a.getToAccountID(),balance+a.getAmount() );
		}
		else if(a.getStatus().equals(Constants.DENIED))
		{
			Long balance= ApplicationContextUtils.getAccountJDBCTemplate().getAccountByAccountID(a.getAccountID()).getBalance();
			 ApplicationContextUtils.getAccountJDBCTemplate().updateCheckingBalanceByAccount(a.getAccountID(),balance+a.getAmount() );
		}
		jdbcTemplateObject.update(SQL,new Object[]{a.getStatus()});
	}
	@Override
	public Transaction getTransactionById(String id) 
	{
		String sql="select * from transaction where transactionID=?";
		return jdbcTemplateObject.queryForObject(sql, new Object[]{id},new TransactionMapper());
	}
	
	
	
	
}
