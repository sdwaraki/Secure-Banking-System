package bankapp.JDBCTemplates;

import java.util.List;

import bankapp.DAO.RoleDAO;
import bankapp.Mapper.RoleMapper;
import bankapp.Objects.ApplicationContextUtils;
import bankapp.Objects.Role;

public class RoleJDBCTemplate extends JDBCTemplateClass implements RoleDAO
{
	@Override
	public Role getRoleById(String Id) 
	{
		String SQL= "select * from role where roleID=?";
		 Role role = jdbcTemplateObject.queryForObject(SQL,new Object[]{Id}, new RoleMapper());
		 return role;
	}
	
	@Override
	public List<Role> getRoleByUserId(String userId) 
	{
		String SQL="select * from role where roleID in (Select roleID from user_role where userID = ?)";
		List<Role> roles= jdbcTemplateObject.query(SQL, new Object[]{userId}, new RoleMapper());	
		return roles;
	}

	@Override
	public Role getRoleByRoleNameOrRoleId(String roleNameOrRoleId) 
	{
		if(ApplicationContextUtils.isNumeric(roleNameOrRoleId))
			return getRoleById(roleNameOrRoleId);
		else
			return getRoleByName(roleNameOrRoleId);
	}

	@Override
	public Role getRoleByName(String roleName) 
	{
			
		String SQL= "select * from role where roleName=?";
		 Role role = jdbcTemplateObject.queryForObject(SQL, new Object[]{roleName}, new RoleMapper());
		 return role;		
	}
	@Override
	public List<Role> getAllRoles() {

	String SQL = "select * from role";

	return jdbcTemplateObject.query(SQL, new RoleMapper());

	}
	
	@Override
	public List<Role> getRoleByRoleType(String roletype)
	{
		String SQL = "select * from role where roletype=?";
		List<Role> roles = jdbcTemplateObject.query(SQL, new RoleMapper(),new Object[]{roletype} );
		return roles;
	}

}
