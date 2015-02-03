package bankapp.Objects;

public class Role 
{
	
	String Id;
	String Name;
	String roleType;
	public String getRoleType() {
		return roleType;
	}
	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public Role()
	{
		
	}
	public Role(String IdOrName)
	{
		Role r= ApplicationContextUtils.getRoleJDBCTemplate().getRoleByRoleNameOrRoleId(IdOrName);
		this.Name=r.getName();
		this.Id=r.getId();
		this.roleType= r.getRoleType();
		
	}
}
