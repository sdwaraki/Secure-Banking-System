package bankapp.DAO;

import bankapp.Objects.Audit;

import java.util.List;

public interface AuditDAO {
	public List<Audit> getAuditAll();
	public void createAudit(Audit a);
	public Audit setAuditRecord(String auditType, String auditTrail, String userID);
}
