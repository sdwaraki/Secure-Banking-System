package bankapp.DAO;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import bankapp.Objects.BankUser;


public interface BankUserDAO 
{
	public BankUser getUserByUserName(String userName);
	public BankUser getUserByUserId(String UserId);
	public String createBankUser(BankUser u) throws NoSuchAlgorithmException, InvalidKeySpecException;
	public void deleteUserByUserName(String UserName);
	public void updateUser(BankUser u);
	public void deleteUserByUserId(String UserId);
	public String getPasswordByUserID(String UserId);
	public void updatePasswordByUserIDAndPassword(String UserId,String password);
	public Boolean checkUserReturned(String userName);
	public String getUserIDByUserName(String userName);


}
