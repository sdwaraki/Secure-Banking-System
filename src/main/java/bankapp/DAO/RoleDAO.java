package bankapp.DAO;

import java.util.List;

import bankapp.Objects.Role;

public interface RoleDAO 
{
	public Role getRoleById(String Id);
	public List<Role> getRoleByUserId(String userId);
	public Role getRoleByRoleNameOrRoleId(String roleNameOrRoleId);
	public Role getRoleByName(String roleName);
	public List<Role> getAllRoles() ;
	public List<Role> getRoleByRoleType(String roletype);
}
