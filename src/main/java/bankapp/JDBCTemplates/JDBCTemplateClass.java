package bankapp.JDBCTemplates;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

public class JDBCTemplateClass 
{
	public DataSource dataSource;
	public JdbcTemplate jdbcTemplateObject;
	
	public void setDataSource(DataSource ds) 
	{
		dataSource=ds;
		jdbcTemplateObject= new JdbcTemplate(ds);
	}

}
