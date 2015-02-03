package bankapp.DAO;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

import bankapp.Objects.Account;

public interface AccountDAO {
	public Account getAccountByAccountID(String accountId);
	public List<Account> getAccountsByCustomerID(String customerId);
	public void createAccount(Account a) throws NoSuchAlgorithmException, InvalidKeySpecException;
	public void deleteAccountByAccountId(String accountId);
	public void updateCheckingBalance(String customerId, Long balance);
	public void updateBalance(String customerId, Long balance,String accountId);
}
