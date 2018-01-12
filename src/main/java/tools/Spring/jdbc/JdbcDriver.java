package tools.Spring.jdbc;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class JdbcDriver {
	
	@Autowired
	public JdbcTemplate jdbcTemplate;
	
	public void setDataSource(DataSource dataSource){
		jdbcTemplate.setDataSource(dataSource);
	}

}
