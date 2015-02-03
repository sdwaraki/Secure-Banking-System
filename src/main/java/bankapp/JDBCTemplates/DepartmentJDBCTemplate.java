package bankapp.JDBCTemplates;

import java.util.List;

import bankapp.DAO.DepartmentDAO;
import bankapp.Mapper.DepartmentMapper;
import bankapp.Objects.Department;


public class DepartmentJDBCTemplate extends JDBCTemplateClass implements DepartmentDAO
{

	@Override
	public Department getDepartment(String Id) 
	{
		String SQL = "select * from department where deptID = ?";
	      Department department = jdbcTemplateObject.queryForObject(SQL, 
	                        new Object[]{Id}, new DepartmentMapper());
	      return department;
		
	}

	@Override
	public List<Department> getDepartmentByEmployeeId(String employeeId) {
		// TODO Auto-generated method stub
		
		String SQL = "select * from department where deptID in (Select deptID from employee_department where employeeID = ?) ";
		List<Department> departments= jdbcTemplateObject.query(SQL,new Object[]{employeeId},new DepartmentMapper());
		return departments;
	}

	@Override
	public List<Department> getAllDepartments() {

		String SQL = "select distinct deptName,deptID from department";
		
		return jdbcTemplateObject.query(SQL, new DepartmentMapper());

	}
	
	
}
