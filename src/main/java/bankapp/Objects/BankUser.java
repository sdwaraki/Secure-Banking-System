package bankapp.Objects;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;


public class BankUser
{

	String userName;
	String Password;
	String userId;
	@NotEmpty(message="First Name Cannot Be Empty")
	@NotBlank(message="First Name Cannot Be Empty")
	String  firstName;
	@NotEmpty(message="Last Name Cannot Be Empty")
	@NotBlank(message="Last Name Cannot Be Empty")
	String lastName;
	
	
	@DateTimeFormat(pattern= "mm/dd/yyyy")
	@Past(message = "Date of Birth must be the past")
	Date dob;
	@NotEmpty(message= "SSN Cannot be empty")
	 @Size(min=10, max=10, message="Not a valid SSN") 
	String ssn;
	
	Boolean enabled;
	String creadtedBy;
	String creationTime;
	@Valid
	Address address;
	String userType;
	@Valid
	List<Role> roles;

	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public String getSsn() {
		return ssn;
	}
	public void setSsn(String ssn) {
		this.ssn = ssn;
	}
	public String getCreadtedBy() {
		return creadtedBy;
	}
	public void setCreadtedBy(String creadtedBy) {
		this.creadtedBy = creadtedBy;
	}
	public String getCreationTime() {
		return creationTime;
	}
	public void setCreationTime(String creationTime) {
		this.creationTime = creationTime;
	}

	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	
	public Boolean getEnabled() {
		return enabled;
	}
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public List<Role> getRoles() {
		return roles;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	public BankUser()
	{
		roles= new ArrayList<Role>();
		address = new Address();
	}
	public BankUser(String userName)
	{

		BankUser temp=ApplicationContextUtils.getBankUserJDBCTemplate().getUserByUserName(userName);

		this.setAddress(temp.getAddress());
		this.setCreadtedBy(temp.getCreadtedBy());
		this.setCreationTime(temp.getCreationTime());
		this.setDob(temp.getDob());
		this.setEnabled(temp.getEnabled());
		this.setFirstName(temp.getFirstName());
		this.setLastName(temp.getLastName());
		this.setRoles(temp.getRoles());
		this.setSsn(temp.getSsn());
		this.setUserId(temp.getUserId());
		this.setUserName(temp.getUserName());
		//this.setUserType(temp.getUserType());
	}
	public String toString() {
		String string="UserID: "+getUserId()+"User Name:"+getUserName()+"First Name: "+getFirstName()+"Last Name: "+getLastName();
		return string;
	}

}
