package bankapp.Objects;

import org.hibernate.validator.constraints.NotEmpty;

public class Salary {
	
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public String getAmount() {
		return Amount;
	}
	public void setAmount(String amount) {
		Amount = amount;
	}
	String Id;
	@NotEmpty(message="Salary should not be blank")
	String Amount;
	
	
}
