package bankapp.Mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import bankapp.Objects.Request;

public class RequestMapper implements RowMapper<Request>{
	
	@Override
	public Request mapRow(ResultSet rs, int rowNum) throws SQLException 
	{
		
		Request request= new Request();
		request.setId(rs.getString("id"));
		request.setRequestID(rs.getString("requestID"));
		request.setRequestType(rs.getString("requestType"));
		request.setRequestFrom(rs.getString("requestFrom"));
		request.setRequestTo(rs.getString("requestTo"));
		request.setRequestStatus(rs.getString("requestStatus"));
		request.setRequest(rs.getString("requestDesc"));
		return request;
	}
}