package bankapp.DAO;

import java.util.List;

import bankapp.Objects.Department;

public interface DepartmentDAO 
{
	public Department getDepartment(String Id);
	public List<Department> getDepartmentByEmployeeId(String employeeId);
	public List<Department> getAllDepartments();

}
