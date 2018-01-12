package tools.Spring.jdbc;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Value;

import com.mysql.jdbc.Connection;

public class ResultSetService {

	@Value("${db.username:}")	
	private String userName = "root";
	
	@Value("${db.password:}")	
	private String password;
	
	@Value("${db.url:}")	
	private String url = "jdbc:mysql://localhost:8080/JXM";

	public ResultSetService(String url, String username, String password) {
		this.userName = username;
		this.password = password;
		this.url = url;
	}

	public ResultSetService() {
	}

	public String[][] query(String query)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection conn = (Connection) DriverManager.getConnection(url, userName, password);
		ResultSet rs = conn.createStatement().executeQuery(query);
		if (!rs.next()) {
			System.out.println(rs.getWarnings().getMessage());
			return null;
		}
		int columnCount = rs.getMetaData().getColumnCount();
		String[] columns = new String[columnCount];
		for (int i = 1; i <= columnCount; i++) {
			columns[i - 1] = rs.getMetaData().getColumnName(i);
		}
		ArrayList<String[]> rows = new ArrayList<>();
		rows.add(columns);
		do {
			String[] row = new String[columnCount];
			for (int i = 0; i < columnCount;) {
				row[i] = rs.getString(++i);
			}
			rows.add(row);
		} while (rs.next());
		String[][] matrix = new String[rows.size()][];
		for (int j = 0; j < rows.size(); j++) {
			matrix[j] = rows.get(j);
		}
		return matrix;
	}

	public void resultSetConnection()
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {

		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection conn = (Connection) DriverManager.getConnection(url, userName, password);
		ResultSet rs = conn.createStatement().executeQuery("select * from USERS");
		int columnCount = rs.getMetaData().getColumnCount();
		String value = "";
		for (int i = 1; i < columnCount; i++) {
			value += rs.getMetaData().getColumnName(i) + "\t| ";
		}
		System.out.println(value);
		System.out.println("-------------------------------------------------------------");
		while (rs.next()) {
			value = "";
			for (int i = 1; i < columnCount; i++) {
				value += rs.getString(i) + " \t| ";
			}
			System.out.println(value);
		}
		System.out.println("-------------------------------------------------------------");
	}
}
