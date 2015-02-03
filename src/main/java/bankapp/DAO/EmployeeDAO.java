package bankapp.DAO;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import bankapp.Objects.Employee;

public interface EmployeeDAO {
	public Employee getEmployeeByEmployeeName(String employeeName);
	public Employee getEmployeeByUserId(String userId);
	//public Employee getEmployeeByUserId(String userId);
	public Employee getEmployeeByEmployeeId(String employeeId);
	public void createEmployee(Employee e) throws NoSuchAlgorithmException, InvalidKeySpecException;
	//public void deleteEmployeeByEmployeeName(String employeeName);
	public void deleteEmployeeByEmployeeId(String employeeId);
	public void populateEmployee(Employee e, String userId);
	public void transferEmployee(String userID, String RoleID);
	public Employee getAdmin();
	public Employee getITManager();
	public Employee getITEmployee();
	public Employee getSalesManager();
	public Employee getSalesEmployee();
	public Employee getHrManager();
	public Employee getHrEmployee();
	public Employee getTransactionManager();
	public Employee getTransactionEmployee();
	public Employee getCorporateEmployee();
}
