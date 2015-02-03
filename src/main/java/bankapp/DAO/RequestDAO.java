package bankapp.DAO;

import java.util.List;

import bankapp.Objects.Request;

public interface RequestDAO {
	public void updateRequestToAdmin(Request r);
	public void createTroubleShootRequest(Request r);
	public List<Request> getPendingRequestFrom(String id);
	public List<Request> getPendingRequestBy(String id);
	public List<Request> getAllRequestByTo(String id);
	public List<Request> getAllMyEmployeeRequest();
	public Request getRequestById(String id);
	public String changeStatus(String requestID, String status);
	public boolean CheckRequestBelongsToEmployee(Request r);
	public void createRequest(Request r);
	void createRequestToManager(Request r);
	public void createRequestToCustomer(String customerid, String customername);
	
	
	public String createPayeeRequest(Request r);
	public void updateRequest(Request r);
}
