package bankapp.Objects;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

public class Address {

	
	String addressID;
	@NotEmpty(message= "addressLine cannot be empty")
	String addressLine;
	
	@NotEmpty(message= "city cannot be empty")
	String city;
	@NotNull(message= "zip cannot be empty")
	Integer zip;
	@NotEmpty(message= "phoneNumber cannot be empty")
	 @Size(min=10,max=10,message= "phoneNumbershould be of length 10")
	@NumberFormat(style = Style.NUMBER)
	
	String phoneNumber;
	@NotEmpty(message= "Email cannot be empty")
	
	@Email(message= "Email is not correct")
	@Pattern(regexp=Constants.EMAIL_PATTERN,message ="Not a valid Email")
	String emailID;
	
	String userID;
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getAddressID() {
		return addressID;
	}
	public void setAddressID(String addressID) {
		this.addressID = addressID;
	}
	public String getAddressLine() {
		return addressLine;
	}
	public void setAddressLine(String addressLine) {
		this.addressLine = addressLine;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public Integer getZip() {
		return zip;
	}
	public void setZip(Integer zip) {
		this.zip = zip;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getEmailID() {
		return emailID;
	}
	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}
	
}
