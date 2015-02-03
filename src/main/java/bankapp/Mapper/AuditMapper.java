package bankapp.Mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.jdbc.core.RowMapper;

import bankapp.Objects.Audit;


public class AuditMapper implements RowMapper<Audit> {

	@Override
	public Audit mapRow(ResultSet rs, int rowNum) throws SQLException {
		DateFormat df = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
		Audit audit = new Audit();
		try {
			audit.setAuditID(rs.getString("auditID"));
			audit.setAuditType(rs.getString("auditType"));
			audit.setAuditTrail(rs.getString("auditTrail"));
			audit.setUserID(rs.getString("userID"));
			audit.setTimeOfAudit(df.parse(rs.getString("timeOfAudit")));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return audit;
	}

}
