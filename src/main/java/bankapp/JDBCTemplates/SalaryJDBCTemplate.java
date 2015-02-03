package bankapp.JDBCTemplates;



import bankapp.DAO.SalaryDAO;
import bankapp.Mapper.SalaryMapper;
import bankapp.Objects.Salary;


public class SalaryJDBCTemplate extends JDBCTemplateClass implements SalaryDAO 
{
	
	@Override
	public void create(Salary salary) {
		String SQL = "insert into salary (salaryID, salary) values (?, ?)";
	      jdbcTemplateObject.update( SQL, salary.getId(), salary.getAmount());
	}

	@Override
	public Salary getSalary(String Id) {
		// TODO Auto-generated method stub
		
		
		String SQL = "select * from salary where salaryID = ?";
	      Salary student = jdbcTemplateObject.queryForObject(SQL, 
	                        new Object[]{Id}, new SalaryMapper());
	      return student;
	//	return null;
	}

	@Override
	public void delete(String id) {
		
		String SQL = "Delete from salary where salaryID = ?";
		jdbcTemplateObject.update(SQL, id);
		// TODO Auto-generated method stub
		
	}

	@Override
	public Salary getSalaryByEmployeeId(String employeeId) 
	{
		String SQL= "select * from salary where salaryID=(select salaryId from employee where employeeID=?)";
		return jdbcTemplateObject.queryForObject(SQL, new Object[]{employeeId}, new SalaryMapper());
	}
	

}
