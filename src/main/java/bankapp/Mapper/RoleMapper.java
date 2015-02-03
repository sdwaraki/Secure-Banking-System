package bankapp.Mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import bankapp.Objects.Role;

public class RoleMapper implements RowMapper<Role> {

	@Override
	public Role mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		Role role= new Role();
		role.setId(rs.getString("roleID"));
		role.setName(rs.getString("roleName"));
		role.setRoleType(rs.getString("roleType"));
		return role;
	}
	

}
