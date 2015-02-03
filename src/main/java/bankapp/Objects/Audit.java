package bankapp.Objects;

import java.util.Date;

public class Audit {
	String auditID;
	String auditType;
	String auditTrail;
	String userID;
	Date   timeOfAudit;
	public String getAuditID() {
		return auditID;
	}
	public void setAuditID(String auditID) {
		this.auditID = auditID;
	}
	public String getAuditTrail() {
		return auditTrail;
	}
	public void setAuditTrail(String auditTrail) {
		this.auditTrail = auditTrail;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public Date getTimeOfAudit() {
		return timeOfAudit;
	}
	public void setTimeOfAudit(Date timeOfAudit) {
		this.timeOfAudit = timeOfAudit;
	}
	public String getAuditType() {
		return auditType;
	}
	public void setAuditType(String auditType) {
		this.auditType = auditType;
	}
	
}
