package bankapp.JDBCTemplates;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;
import java.util.UUID;

import bankapp.DAO.AccountDAO;
import bankapp.Mapper.AccountMapper;
import bankapp.Objects.Account;
import bankapp.Objects.Constants;

public class AccountJDBCTemplate extends JDBCTemplateClass implements AccountDAO {

	@Override
	public Account getAccountByAccountID(String accountId) 
	{
		String SQL = "Select * from account where accountID=?";	
		return jdbcTemplateObject.queryForObject(SQL,new Object[]{accountId},new AccountMapper());
	
	}



	@Override
	public void createAccount(Account a) throws NoSuchAlgorithmException,
			InvalidKeySpecException {
		String SQL = "insert into Account (accountID, accountType, customerID, balance ) values (?,?,?,?)";
        jdbcTemplateObject.update( SQL, a.getAccountID(), a.getAccountType(), a.getCustomer().getCustomerID(), a.getBalance());
	}

	@Override
	public void deleteAccountByAccountId(String accountId) {
		String SQL = "delete from Account where accountID=?";
		jdbcTemplateObject.update( SQL, accountId);
		
	}



	@Override
	public List<Account> getAccountsByCustomerID(String customerId) 
	{
		String SQL = "Select * from account where customerID=?";
				
		return jdbcTemplateObject.query(SQL,new Object[]{customerId},new AccountMapper());
	}
	
	@Override
	public void updateCheckingBalance(String customerId, Long balance){
		
		String SQL = "update account set balance=? where (customerID=? AND accountType=?) ";
		Object[] params = new Object[]{balance,customerId,Constants.CHECKINGS};
		jdbcTemplateObject.update(SQL, params);
		
	}
	

	public void updateCheckingBalanceByAccount(String accountId, Long balance){
		
		String SQL = "update account set balance=? where accountID=? ";
		Object[] params = new Object[]{balance,accountId};
		jdbcTemplateObject.update(SQL, params);
		
	}
	@Override
	public void updateBalance(String customerId, Long balance,String accountID){
		
		String SQL = "update account set balance=? where customerID=? and accountID=? ";
		Object[] params = new Object[]{balance,customerId,accountID};
		jdbcTemplateObject.update(SQL, params);
		
	}

}
