package bankapp.Objects;

import org.hibernate.validator.constraints.NotEmpty;

public class Department 
{
	
	String Id;
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
	@NotEmpty(message ="Department Name should not be blank")
	String Name;
public Department()
{
	
}
public Department(String id,String name)
{
	Id=id;
	Name=name;
}
}
