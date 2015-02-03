package bankapp.Mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import bankapp.Objects.Department;

public class DepartmentMapper  implements RowMapper<Department>
{

	@Override
	public Department mapRow(ResultSet rs, int rowNum) throws SQLException 
	{
		
		Department department= new Department();
		department.setId(rs.getString("deptID"));
		department.setName(rs.getString("deptName"));
		
		return department;
	}

}
