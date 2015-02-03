package bankapp.JDBCTemplates;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import bankapp.DAO.AuditDAO;
import bankapp.Mapper.AuditMapper;
import bankapp.Objects.ApplicationContextUtils;
import bankapp.Objects.Audit;

public class AuditJDBCTemplate extends JDBCTemplateClass implements AuditDAO {

	@Override
	public List<Audit> getAuditAll() {
		String SQL="select * from audit";
		return jdbcTemplateObject.query(SQL,new AuditMapper());
		
	}

	@Override
	public void createAudit(Audit a) {
		String SQL = "insert into audit (auditID, auditType, auditTrail, userID, timeOfAudit) values (?,?,?,?,?)";
		String id=UUID.randomUUID().toString();
		
        jdbcTemplateObject.update( SQL, id,a.getAuditType(), a.getAuditTrail(), a.getUserID(), a.getTimeOfAudit());
	}
	
	@Override
	public Audit setAuditRecord(String auditType, String auditTrail, String userID) {
		Audit audit=new Audit();
		audit.setAuditID(UUID.randomUUID().toString());
		audit.setAuditType(auditType);
		audit.setAuditTrail(auditTrail);
		audit.setUserID(ApplicationContextUtils.getLoggedInUser().getUserId());
		audit.setTimeOfAudit(new Date());
		return audit;
	}

}
