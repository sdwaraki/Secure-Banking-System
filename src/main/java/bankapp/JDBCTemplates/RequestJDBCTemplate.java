package bankapp.JDBCTemplates;

import java.util.List;
import java.util.UUID;

import org.springframework.context.ApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import bankapp.DAO.RequestDAO;
import bankapp.Mapper.RequestMapper;
import bankapp.Objects.ApplicationContextUtils;
import bankapp.Objects.BankUser;
import bankapp.Objects.Constants;
import bankapp.Objects.Customer;
import bankapp.Objects.Employee;
import bankapp.Objects.MailMail;
import bankapp.Objects.Request;
import bankapp.Objects.Role;
import bankapp.Objects.Transaction;
import bankapp.Objects.Audit;

public class RequestJDBCTemplate extends JDBCTemplateClass implements RequestDAO
{
	private enum requestTypes
	{
		CREATE_EMPLOYEE,DELETE_EMPLOYEE,CREATE_TROUBLESHOOT,DELETE_CUSTOMER,CREATE_CUSTOMER,APPROVE_TRANSACTION, ADD_PAYEE
	}
	@Override
	public List<Request> getPendingRequestFrom(String id){
		String SQL="select * from request where requestFrom=? and requestStatus=?";
		return jdbcTemplateObject.query(SQL, new Object[]{id,Constants.PENDING}, new RequestMapper());		
	}
	@Override
	public List<Request> getPendingRequestBy(String id) {
		String SQL="select * from request where requestTo=? and requestStatus=?";
		return jdbcTemplateObject.query(SQL, new Object[]{id,Constants.PENDING}, new RequestMapper());

	}
	public List<Request> getAllRequestByTo(String id) {
		String SQL="select * from request where requestTo=?";
		return jdbcTemplateObject.query(SQL, new Object[]{id}, new RequestMapper());

	}
	public List<Request> getAllRequestByFrom(String id) {
		String SQL="select * from request where requestFrom=?";
		return jdbcTemplateObject.query(SQL, new Object[]{id}, new RequestMapper());

	}
	@Override
	public void createRequestToManager(Request r)
	{
	
		String currentUserRole = ApplicationContextUtils.getLoggedInUser().getRoles().get(0).getName();
		if(currentUserRole.equals(Constants.HR_MANAGER) || currentUserRole.equals(Constants.SALES_MANAGER))
		{
			
			r.setRequestStatus(Constants.APPROVED);
			String adminUserId= ApplicationContextUtils.getEmployeeJDBCTemplate().getAdmin().getUserId();
			r.setRequestTo(adminUserId);
		}
		else
		{
			List<Role> roles =ApplicationContextUtils.getRoleJDBCTemplate().getRoleByUserId(r.getRequestFrom());
			String managerType=getManagerType(roles.get(0).getName());
			Employee e=ApplicationContextUtils.getEmployeeJDBCTemplate().getEmployeeForRole(managerType);
		
			r.setRequestTo(e.getUserId());
			r.setRequestStatus(Constants.PENDING);
		}
			createRequest(r);
	}
	
	@Override
	public void createRequestToCustomer(String customerid, String customername){
		Request r = new Request();
		r.setRequestFrom(ApplicationContextUtils.getLoggedInUser().getUserId());
		r.setRequestTo(customerid);
		r.setRequestType("ADD_PAYEE");
		r.setRequestStatus("PENDING");
		r.setId(customerid);
		r.setRequestID(ApplicationContextUtils.getLoggedInUser().getUserId());
		createRequest(r);
	}
	
	public String getManagerType(String name) 
	{
		String SQL="Select manager_role from manager_employee_mapping where employee_role=?";
		return jdbcTemplateObject.queryForObject(SQL, new Object[]{name}, String.class);
	}
	public String getEmployeeType(String name) 
	{
		String SQL="Select employee_role from manager_employee_mapping where manager_role=?";
		return jdbcTemplateObject.queryForObject(SQL, new Object[]{name}, String.class);
	}
	@Override
	public void updateRequestToAdmin(Request r)
	{
		String adminUserId= ApplicationContextUtils.getEmployeeJDBCTemplate().getAdmin().getUserId();
	//	r.setRequestID(UUID.randomUUID().toString());
		r.setRequestTo(adminUserId);
		String SQL="update  request set requestTo=? where requestID=?";
		jdbcTemplateObject.update( SQL,r.getRequestTo(), r.getRequestID());
	//	createRequest(r);
	}
	public List<Request> getAllApprovedRequestTo(String userId)
	{
		System.out.println("USer ID========"+userId);
		String SQL="select * from request where requestTo=? and requestStatus=?";
		return jdbcTemplateObject.query(SQL, new Object[]{userId,Constants.APPROVED}, new RequestMapper());
	}
	@Override
	public String changeStatus(String requestID, String status) 
	{
		Request r=getRequestById(requestID);
		requestTypes reqType=requestTypes.valueOf(r.getRequestType());

		try {
			switch(reqType)
			{
					case CREATE_CUSTOMER:
											if(status.equals(Constants.APPROVED))
											{
												r.setRequestStatus(Constants.APPROVED);
												updateRequestToAdmin(r);
											}
											else if (status.equals(Constants.COMPLETE))
											{
												enableUser(r.getId());
											}
											else if(status.equals(Constants.DENIED))
											{
												deleteUser(r.getId());
											}
											break;
					case CREATE_EMPLOYEE:
											if(status.equals(Constants.APPROVED))
											{
												r.setRequestStatus(Constants.APPROVED);
												updateRequestToAdmin(r);
											}
											else if (status.equals(Constants.COMPLETE))
											{
												enableUser(r.getId());
											}
											else if(status.equals(Constants.DENIED))
											{
												deleteUser(r.getId());
											}
											break;
										
					case CREATE_TROUBLESHOOT:
											if (status.equals(Constants.COMPLETE))
											{
												/*Do Noting*/
											}										
						
									break;
					case DELETE_CUSTOMER:
											if(status.equals(Constants.APPROVED))
											{
												r.setRequestStatus(Constants.APPROVED);
												updateRequestToAdmin(r);
											}
											else if (status.equals(Constants.COMPLETE))
											{
												if (status.equals(Constants.COMPLETE)) {
													String auditTrail=(ApplicationContextUtils.getBankUserJDBCTemplate().getUserByUserId(r.getId())).toString()+"AUDIT STATUS: "+true;
													Audit audit=ApplicationContextUtils.getAuditJDBCTemplate().setAuditRecord(r.getRequestType(), auditTrail, ApplicationContextUtils.getLoggedInUser().getUserId() );
													ApplicationContextUtils.getAuditJDBCTemplate().createAudit(audit);
												}
												deleteUser(r.getId());
											}
											else if(status.equals(Constants.DENIED))
											{
												//deleteUser(r.getId());
											}
											break;
											
					case DELETE_EMPLOYEE:
											if(status.equals(Constants.APPROVED))
											{
												r.setRequestStatus(Constants.APPROVED);
												updateRequestToAdmin(r);
											}
											else if (status.equals(Constants.COMPLETE))
											{
												if (status.equals(Constants.COMPLETE)) {
													String auditTrail=(ApplicationContextUtils.getBankUserJDBCTemplate().getUserByUserId(r.getId())).toString()+"AUDIT STATUS: "+true;
													Audit audit=ApplicationContextUtils.getAuditJDBCTemplate().setAuditRecord(r.getRequestType(), auditTrail, ApplicationContextUtils.getLoggedInUser().getUserId() );
													ApplicationContextUtils.getAuditJDBCTemplate().createAudit(audit);
												}
												deleteUser(r.getId());
											}
											else if(status.equals(Constants.DENIED))
											{
											//	deleteUser(r.getId());
											}
										//	break;
											break;
					case APPROVE_TRANSACTION: 								
													Transaction a= ApplicationContextUtils.getTransactionJDBCTemplate().getTransactionById(r.getId());
													a.setStatus(status);
													ApplicationContextUtils.getTransactionJDBCTemplate().updateTransactionStatus(a);
									
											break;
					case ADD_PAYEE:		if(status.equals(Constants.COMPLETE))
					{
						r.setRequestStatus(Constants.COMPLETE);
						String customerid = r.getId();
						BankUser user = ApplicationContextUtils.getBankUserJDBCTemplate().getUserByUserId(customerid);
						String payeeUserName = user.getUserName();
						String merchantid = r.getRequestFrom();
						ApplicationContextUtils.getPayeeJDBCTemplate().addPayee(payeeUserName, customerid, merchantid);
						
					}
						
			}
			
			String SQL="update request set requestStatus=? where requestID=? ";
			jdbcTemplateObject.update(SQL, new Object[] {status,requestID});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			e.getMessage();
		} finally {
			if( (reqType.equals(requestTypes.CREATE_CUSTOMER)) || (reqType.equals(requestTypes.CREATE_EMPLOYEE)) )
			if (status.equals(Constants.COMPLETE)) {
				String auditTrail=(ApplicationContextUtils.getBankUserJDBCTemplate().getUserByUserId(r.getId())).toString()+"AUDIT STATUS: "+true;
				Audit audit=ApplicationContextUtils.getAuditJDBCTemplate().setAuditRecord(r.getRequestType(), auditTrail, ApplicationContextUtils.getLoggedInUser().getUserId() );
				ApplicationContextUtils.getAuditJDBCTemplate().createAudit(audit);
			}
			if (status.equals(Constants.DENIED)) {
				String auditTrail=(ApplicationContextUtils.getBankUserJDBCTemplate().getUserByUserId(r.getId())).toString()+"AUDIT STATUS: "+false;
				Audit audit=ApplicationContextUtils.getAuditJDBCTemplate().setAuditRecord(r.getRequestType(), auditTrail, ApplicationContextUtils.getLoggedInUser().getUserId() );
				ApplicationContextUtils.getAuditJDBCTemplate().createAudit(audit);
			}
			}
		return status; 
	}
	
	
	@Override
	public void createTroubleShootRequest(Request r) 
	{
		Employee e=ApplicationContextUtils.getEmployeeJDBCTemplate().getITEmployee();
		if(e!=null)
		{
			r.setRequestType(Constants.CREATE_TROUBLESHOOT_REQUEST);
			r.setRequestStatus(Constants.PENDING);
			r.setRequestTo(e.getUserId());
			createRequest(r);
		}
		
	}

	@Override
	public Request getRequestById(String id) {
		String SQL="select * from request where requestID=?";
		return jdbcTemplateObject.queryForObject(SQL, new Object[]{id}, new RequestMapper());

	}
	public void enableUser(String userId)
	{
		String SQL="update user set enabled=? where userID=?";
		jdbcTemplateObject.update(SQL, new Object[] { Constants.ACTIVE, userId});
		sendCreatUserEmail(userId);    
	}
	public void deleteUser(String userId)
	{
		ApplicationContextUtils.getBankUserJDBCTemplate().deleteUserByUserId(userId);
	}
	
	private void sendCreatUserEmail(String userId) {
		System.out.println("USERID Send Email:-"+userId);
		BankUser user=ApplicationContextUtils.getBankUserJDBCTemplate().getUserByUserId(userId);
		  /*Send Email*/
		
		  String Messgae= "Hi,\n Your Account has been Created!\n UserName:\t"+user.getUserName()+" \n Password:\t"+user.getPassword();
		  MailMail mm = (MailMail) ApplicationContextUtils.getCtx().getBean("mailMail");
		   mm.sendMail("thebankapplication@gmail.com",user.getAddress().getEmailID(),"New User Created", Messgae);
		   
			BCryptPasswordEncoder bc= new BCryptPasswordEncoder();
			String hashPassword= bc.encode(user.getPassword());
			ApplicationContextUtils.getBankUserJDBCTemplate().updatePasswordByUserIDAndPassword(user.getUserId(), hashPassword);
	}
	@Override
	public boolean CheckRequestBelongsToEmployee(Request r) 
	{
			String employeeUsStringID= ApplicationContextUtils.getBankUserJDBCTemplate().getUserIDByUserName(ApplicationContextUtils.getLoggedInUserName());
			String SQL= "Select count(*) request where requestID=? and requestTo=?";
			Long S = jdbcTemplateObject.queryForLong(SQL,new Object[]{r.getRequestID(),employeeUsStringID});
			if(S>0)
				return true;
			else
				return false;
	}
	@Override
	public List<Request> getAllMyEmployeeRequest() 
	{
		String department_roles=getEmployeeType(ApplicationContextUtils.getLoggedInUser().getRoles().get(0).getName());
		String SQL="Select * from request where requestFrom in (Select userID from user where userID in (Select userID from user_role where roleId =(Select roleId from role where roleName =?)))";
		return jdbcTemplateObject.query(SQL, new Object[]{department_roles},new RequestMapper());
		
	}
	@Override
	public void createRequest(Request r) 
	{
		String SQL="insert into request (requestID, requestType, requestFrom, requestTo, requestStatus, requestDesc,id) values (?,?,?,?,?,?,?)";
		jdbcTemplateObject.update( SQL, r.getRequestID(), r.getRequestType(), r.getRequestFrom(), r.getRequestTo(), r.getRequestStatus(), r.getRequest(),r.getId());
		
	}
	
	@Override
	public String createPayeeRequest(Request r){
		
		String SQL="insert into request (requestID, requestType, requestFrom, requestTo, requestStatus, requestDesc, id) values (?,?,?,?,?,?,?)";
		String id=UUID.randomUUID().toString();
		r.setRequestType("AddPayee");
		r.setRequest("addition of payee");
	

		jdbcTemplateObject.update(SQL, id, r.getRequestType(), r.getRequestFrom(), r.getRequestTo(), Constants.PENDING, r.getRequest(), r.getId() );
		return id;
				
	}
	
	@Override
	public void updateRequest(Request r){
		
		String SQL="update request set requestStatus=?";
		jdbcTemplateObject.update(SQL,new Object[]{r.getRequestStatus()});
		
	}
	
}
