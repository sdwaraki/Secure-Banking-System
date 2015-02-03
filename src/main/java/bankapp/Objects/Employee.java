package bankapp.Objects;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Future;

public class Employee extends BankUser{

	
	
	String employeeId;
	@Future(message="Date of Joining must be in the future")
	Date   doj;
	String position;
	@Valid
	Salary salary;
	@Valid
	List<Department> departments;
	public Salary getSalary() {
		return salary;
	}
	public void setSalary(Salary salary) {
		this.salary = salary;
	}

	public List<Department> getDepartments() {
		return departments;
	}
	public void setDepartments(List<Department> departments) {
		this.departments = departments;
	}
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public Date getDoj() {
		return doj;
	}
	public void setDoj(Date doj) {
		this.doj = doj;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public Employee()
	{
		departments= new ArrayList<Department>();
		salary = new Salary();
	}
	public Employee(String userName)
	{
		super(userName);
		Employee temp= ApplicationContextUtils.getEmployeeJDBCTemplate().getEmployeeByUserId(this.getUserId());
		this.setDoj(temp.getDoj());
		this.setPosition(temp.getPosition());
		this.setSalary(temp.getSalary());
		this.setEmployeeId(temp.getEmployeeId());
		this.setDepartments(temp.getDepartments());
//		ApplicationContextUtils.getEmployeeJDBCTemplate().populateEmployee(this, this.getUserId());
	}
	@Override
	public String toString() {
		String string;
		string=super.toString();
		string+="EmployeeID: "+getEmployeeId()+"DOJ:"+getDoj()+"Position: "+getPosition();
		string+="Department(s): ";
		for (Iterator<Department> dept=getDepartments().iterator(); dept.hasNext();) {
			string+=dept.toString()+", ";
		}
		return string;
	}
	
}
