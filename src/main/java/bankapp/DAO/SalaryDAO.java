package bankapp.DAO;

import bankapp.Objects.Salary;

public interface SalaryDAO {
	
	public void create(Salary slaray);
	public Salary getSalary(String Id);
	public void delete(String id);
	public Salary getSalaryByEmployeeId(String employeeId);
}
