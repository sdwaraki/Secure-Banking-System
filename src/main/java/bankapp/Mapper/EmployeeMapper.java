package bankapp.Mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.jdbc.core.RowMapper;

import bankapp.Objects.Employee;

public class EmployeeMapper implements RowMapper<Employee> {

	@Override
	public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		DateFormat df = new SimpleDateFormat("yyyy-mm-dd");
		Employee employee= new Employee();
		try {
			employee.setEmployeeId(rs.getString("employeeId"));
			employee.setDoj(df.parse(rs.getString("DOJ")));
			employee.setUserId(rs.getString("userID"));
			//employee.setSalary(ApplicationContextUtils.getSalaryJDBCTemplate().getSalary(rs.getString("salaryID")));
			//employee.setAddress(ApplicationContextUtils.getAddressJDBCTemplate().get);
			employee.setPosition(rs.getString("Position"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return employee;
	}

}
