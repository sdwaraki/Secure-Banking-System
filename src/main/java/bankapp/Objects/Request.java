package bankapp.Objects;

import java.util.UUID;

public class Request {
	String requestID;
	String requestType;
	String requestFrom;
	String requestTo;
	String requestStatus;
	String request;
	String Id;
	
	public Request()
	{
		requestID= UUID.randomUUID().toString();
		Id=ApplicationContextUtils.getLoggedInUser().getUserId();
		requestFrom=ApplicationContextUtils.getLoggedInUser().getUserId();
	}
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public String getRequestID() {
		return requestID;
	}
	public void setRequestID(String requestID) {
		this.requestID = requestID;
	}
	public String getRequestType() {
		return requestType;
	}
	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}
	public String getRequestFrom() {
		return requestFrom;
	}
	public void setRequestFrom(String requestFrom) {
		this.requestFrom = requestFrom;
	}
	public String getRequestTo() {
		return requestTo;
	}
	public void setRequestTo(String requestTo) {
		this.requestTo = requestTo;
	}
	public String getRequestStatus() {
		return requestStatus;
	}
	public void setRequestStatus(String requestStatus) {
		this.requestStatus = requestStatus;
	}
	public String getRequest() {
		return request;
	}
	public void setRequest(String request) {
		this.request = request;
	}
	public String toString() {
		String string="Request ID: "+getRequestID()+"Request Type: "+getRequestType()+"Request From: "+getRequestFrom()+"Request To: "+getRequestTo();
		string+="Request Satus: "+getRequestStatus()+"Request: "+getRequest();
		return string;
	}
	

}
