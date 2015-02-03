package bankapp.Objects;

import java.util.Date;

public class Transaction {
	String transactionID;
	String transactionType;
	String accountID;
	String userID;
	Date timeOfTansaction;
	String toAccountID;
	Long amount;
	String status;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Long getAmount() {
		return amount;
	}
	public void setAmount(Long amount) {
		this.amount = amount;
	}
	public String getToAccountID() {
		return toAccountID;
	}
	public void setToAccountID(String toAccountID) {
		this.toAccountID = toAccountID;
	}
	public Date getTimeOfTansaction() {
		return timeOfTansaction;
	}
	public void setTimeOfTansaction(Date timeOfTansaction) {
		this.timeOfTansaction = timeOfTansaction;
	}
	public String getTransactionID() {
		return transactionID;
	}
	public void setTransactionID(String transactionID) {
		this.transactionID = transactionID;
	}
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	public String getAccountID() {
		return accountID;
	}
	public void setAccountID(String accountID) {
		this.accountID = accountID;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	
}
