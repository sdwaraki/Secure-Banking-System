package bankapp.Mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import bankapp.Objects.Salary;

public class SalaryMapper implements RowMapper<Salary>{

	@Override
	public Salary mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		
		Salary salary= new Salary();
		salary.setId(rs.getString("salaryID"));
		salary.setAmount(rs.getString("salary"));
		
		return salary;
	}
	
	

}
