package bankapp.JDBCTemplates;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.UUID;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import bankapp.DAO.BankUserDAO;
import bankapp.Mapper.BankUserMapper;
import bankapp.Objects.ApplicationContextUtils;
import bankapp.Objects.BankUser;
import bankapp.Objects.Constants;
import bankapp.Objects.MailMail;
import bankapp.Objects.Role;

public class BankUserJDBCTemplate extends JDBCTemplateClass implements BankUserDAO 
{
	//NOTE:- BankUser hass Roles and Address, Has to be populated Spearately
	@Override
	public BankUser getUserByUserName(String userName) 
	{
		String SQL="select * from user where userName = ?";

		BankUser bankuser=jdbcTemplateObject.queryForObject(SQL, 
                new Object[]{userName}, new BankUserMapper());
		
		
		populateRoleAndAddress(bankuser);
		
		
		//jdbcTemplateObject.update(SQL,userName);
		return bankuser;
	}
	 public boolean isDefaultUser(String userName)
	 {
		 String SQL="select defaultemployee  from user where userName = ?";
		Long i= jdbcTemplateObject.queryForLong(SQL,new Object[]{userName});
		if(i==1)
		{
			return true;
		}
		else
			return false;
	 }
	//@SuppressWarnings("unused")
	@Override
	public Boolean checkUserReturned(String userName){
		
		String SQL="select count(*) from user where userName = ?";

		Long l = jdbcTemplateObject.queryForLong(SQL, new Object[]{userName});
		if (l>0) return true;
		return false;
	}
	
	public String checkCustomerUserReturned(String userName)
	{
		/* GET User Object If Avaialable*/
		String SQL="select count(*) from user,user_role where userName = ? and enabled='1'";

		Long l = jdbcTemplateObject.queryForLong(SQL, new Object[]{userName});
		if(l>0)
		{
			BankUser b= new  BankUser(userName);
			if(b.getRoles().get(0).getName().equals(Constants.INDIVIDUAL_CUSTOMER) || b.getRoles().get(0).getName().equals(Constants.MERCHANT_CUSTOMER))
				return "";
			else
				return "Reciepeint is not a Customer";
		
		}
		return "Reciepeint Does not exsist";
	}
	private void populateRoleAndAddress(BankUser bankuser) {
		bankuser.setAddress(ApplicationContextUtils.getAddressJDBCTemplate().getAddressbyUserId(bankuser.getUserId()));
		bankuser.setRoles(ApplicationContextUtils.getRoleJDBCTemplate().getRoleByUserId(bankuser.getUserId()));
	}
	
	

	@Override
	public BankUser getUserByUserId(String UserId) 
	{
	
		String SQL="select * from user where userID = ?";
		BankUser bankuser=jdbcTemplateObject.queryForObject(SQL, 
                new Object[]{UserId}, new BankUserMapper());
		//jdbcTemplateObject.update(SQL,userName);
		populateRoleAndAddress(bankuser);
		return bankuser;
	}

	@Override
	public String createBankUser(BankUser user) throws NoSuchAlgorithmException, InvalidKeySpecException 
	{
			
			/*Update  USER Object*/
			String SQL = "insert into user (userID, userName,fName,lName,SSN,DoB,enabled,createdBy,creationTimeStamp,userType,password) values (?,?,?,?,?,?,?,?,?,?,?)";
			String id=UUID.randomUUID().toString();	
			user.setUserId(id);
			
			/*CREATE RANDOM PASSWORD*/
			String Password=  new BigInteger(130, new SecureRandom()).toString(32);
		
			
			user.setPassword(Password);
			user.setUserName(user.getAddress().getEmailID());
						
			
			/*SQL TO CREATE USER*/
			 Object[] params = new Object[] { id,user.getUserName(),user.getFirstName(),user.getLastName(),user.getSsn(),user.getDob(),Constants.INACTIVE,user.getCreadtedBy(),user.getCreationTime(),user.getUserType() ,Password};
			 jdbcTemplateObject.update(SQL, params);
			 ApplicationContextUtils.getAddressJDBCTemplate().addAddress(user);
			 
	    	  SQL= "insert into user_role (userID,roleID) values (?,?)";
	    	  for(Role r:user.getRoles())
	    	  {
	    		  jdbcTemplateObject.update(SQL,user.getUserId(),r.getId());
	    	  }
	    	  
	      return id;
		
	}

	@Override
	public void deleteUserByUserName(String UserName) 
	{
		String SQL = "Delete from user where userName = ?";
		jdbcTemplateObject.update(SQL, UserName);
		
	}

	@Override
	public void deleteUserByUserId(String UserId)
	{
		String SQL = "Delete from user where UserId = ?";
		jdbcTemplateObject.update(SQL, UserId);
		
	}
	@Override
	public void updateUser(BankUser u)
	{
		ApplicationContextUtils.getAddressJDBCTemplate().updateAddress(u.getAddress());
		String SQL = "update user set fName=?,lName=?,SSN=?,DoB=?";
		jdbcTemplateObject.update(SQL, new Object[]{u.getFirstName(),u.getLastName(),u.getSsn(),u.getDob()});
	}
	@Override
	public String getPasswordByUserID(String userId) 
	{
		String SQL="select password from user where userID=?";
		return  (String)jdbcTemplateObject.queryForObject(
				SQL, new Object[] { userId }, String.class);
	}
	@Override
	public void updatePasswordByUserIDAndPassword(String UserId, String password) {
		// TODO Auto-generated method stub
		
		String SQL="update user set password=? where userID=?";
		jdbcTemplateObject.update(SQL,new Object[]{password,UserId});		
	}

	@Override
	public String getUserIDByUserName(String userName) {
		// TODO Auto-generated method stub
		String SQL= "Select userID from user where userName=?";
		return jdbcTemplateObject.queryForObject(SQL,new Object[]{userName}, String.class);
	}

}
