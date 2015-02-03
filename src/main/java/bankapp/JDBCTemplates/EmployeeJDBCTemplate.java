package bankapp.JDBCTemplates;

import java.util.UUID;

import bankapp.DAO.EmployeeDAO;
import bankapp.Mapper.EmployeeMapper;
import bankapp.Objects.ApplicationContextUtils;
import bankapp.Objects.Constants;
import bankapp.Objects.Department;
import bankapp.Objects.Employee;
import bankapp.Objects.Request;

public class EmployeeJDBCTemplate extends JDBCTemplateClass implements EmployeeDAO
{
	
	//NOTE:- Salary and Departments has to be populated Separately
	@Override
	public Employee getEmployeeByEmployeeName(String employeeName) {
		
		return null;
	}
	@Override
	public Employee getEmployeeByEmployeeId(String employeeId) {
	
		return null;
	}
	@Override
	public void createEmployee(Employee e) 
	{
		
		// salaryid= ApplicationContextUtils.getSalaryJDBCTemplate().create(e.getSalary());
		String salaryid=UUID.randomUUID().toString();
		e.getSalary().setId(salaryid);
		String SQL = "insert into employee (employeeID, userID, DOJ, position,salaryID) values (?,?,?,?,?)";
		String id=UUID.randomUUID().toString();
        jdbcTemplateObject.update( SQL, id, e.getUserId(), e.getDoj(), e.getPosition(),e.getSalary().getId());
        e.setEmployeeId(id);
        SQL="insert into employee_department (employeeID,deptID) values (?,?)";
		for(Department d:e.getDepartments())
		{
			jdbcTemplateObject.update(SQL,e.getEmployeeId(),d.getId());
		}
		ApplicationContextUtils.getSalaryJDBCTemplate().create(e.getSalary());
		 Request r=new Request();
			r.setRequestType(Constants.CREATE_EMPLOYEE_REQUEST);
			r.setRequestFrom(ApplicationContextUtils.getLoggedInUser().getUserId());
			r.setId(e.getUserId());
			ApplicationContextUtils.getRequestJDBCTemplate().createRequestToManager(r);
	}
	@Override
	public void deleteEmployeeByEmployeeId(String employeeId) {
		String SQL = "delete from employee where employeeID=?";
		jdbcTemplateObject.update( SQL,employeeId);
	}
	@Override
	public void transferEmployee(String userID, String Id) {
		System.out.println("Update DB");
		
			String SQL = "update user_role set roleID=? where userID=?";
			jdbcTemplateObject.update(SQL, new Object[] {Id, userID});
		
	}
	@Override
	public Employee getEmployeeByUserId(String userId) 
	{
		String SQL = "Select * from employee where userID=?";
		Employee e= jdbcTemplateObject.queryForObject(SQL,new Object[]{userId},new EmployeeMapper());
		populateSalaryAndDepartments(e);
		return e;		
	}
	private void populateSalaryAndDepartments(Employee e) {
		e.setSalary(ApplicationContextUtils.getSalaryJDBCTemplate().getSalaryByEmployeeId(e.getEmployeeId()));
		e.setDepartments(ApplicationContextUtils.getDepartmentJDBCTemplate().getDepartmentByEmployeeId(e.getEmployeeId()));
	}
	@Override
	public void populateEmployee(Employee e, String userId) {
		// TODO Auto-generated method stub
		e= this.getEmployeeByUserId(userId);
		
	}
	@Override
	public Employee getAdmin() {
		return getEmployeeForRole(Constants.ADMIN);
	}
	@Override
	public Employee getITManager() {
		return getEmployeeForRole(Constants.IT_MANAGER);
	}
	@Override
	public Employee getITEmployee() {
		return getEmployeeForRole(Constants.IT_EMPLOYEE);
	}
	@Override
	public Employee getSalesManager() 
	{
		return getEmployeeForRole(Constants.SALES_MANAGER);
	}
	@Override
	public Employee getSalesEmployee() 
	{
		return getEmployeeForRole(Constants.SALES_EMPLOYEE);	
	}
	@Override
	public Employee getHrManager()
	{
		return getEmployeeForRole(Constants.HR_MANAGER);
	}
	@Override
	public Employee getHrEmployee() 
	{
		return getEmployeeForRole(Constants.HR_EMPLOYEE);
	}
	@Override
	public Employee getTransactionManager() 
	{
		return getEmployeeForRole(Constants.TRANSACTION_MANAGER);
	}
	@Override
	public Employee getCorporateEmployee() 
	{
		return getEmployeeForRole(Constants.CORPORATE_EMPLOYEE);
	}
	@Override
	public Employee getTransactionEmployee()
	{
		return getEmployeeForRole(Constants.TRANSACTION_EMPLOYEE);
	}
	
	
	public Employee getEmployeeForRole(String Department_role)
	{
		
		
		String EmployeeIDQuery= "Select * from employee where userID=(Select userID from user where userID = (Select userID from user_role where roleID =(Select roleId from role where roleName= ?) and enabled='1' LIMIT 1)) LIMIT 1";
		Employee e=jdbcTemplateObject.queryForObject(EmployeeIDQuery,new Object[]{Department_role},new EmployeeMapper());
		String SQL="Select userID from employee where employeeID=?";
		String userId = jdbcTemplateObject.queryForObject(SQL, new Object[]{e.getEmployeeId()},String.class);
		e.setUserId(userId);
		return e;
	}
}
