package uitox.eric.ch12;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.sql.DataSource;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mchange.v2.c3p0.DataSources;

import static org.junit.Assert.*;

public class DbConnectionTest {
	private static final Logger logger = LoggerFactory
			.getLogger(DbConnectionTest.class);
	private final String url = "jdbc:mysql://localhost:3306/jpa_exercise";
	private final String h2Url = "jdbc:h2:mem:test;MODE=MySQL";
	private final String user = "root";
	private final String password = "";

	public void connectToDb() throws SQLException {

		final Connection connection = DriverManager.getConnection(url, user,
				password);

		assertFalse(connection.isClosed());
		connection.close();
		assertTrue(connection.isClosed());
	}

	public void retrieveRows() throws SQLException {
		final Connection connection = DriverManager.getConnection(url, user,
				password);

		final Statement stmt = connection.createStatement();

		final String sql = "SELECT * FROM DOG d";
		final ResultSet rs = stmt.executeQuery(sql);

		final Map<Integer, String> dogMap = new HashMap<Integer, String>();

		while (rs.next()) {
			final int dogNumber = rs.getInt("id");
			final String dogName = rs.getString("name");

			dogMap.put(dogNumber, dogName);
		}

		Iterator<Entry<Integer, String>> it = dogMap.entrySet().iterator();

		while (it.hasNext()) {
			Map.Entry<Integer, String> pair = (Map.Entry<Integer, String>) it
					.next();
			System.out.println(pair.getKey() + " = " + pair.getValue());
			it.remove();
		}

		rs.close();
		stmt.close();
		connection.close();
	}

	public void queryParameters() throws SQLException {
		final Connection connection = DriverManager.getConnection(url, user,
				password);
		final String sql = "INSERT INTO DOG values (?,?)";
		final PreparedStatement pStmt = connection.prepareStatement(sql);

		pStmt.setInt(1, 3);
		pStmt.setString(2, "barry");
		pStmt.execute();
		pStmt.close();

		final Statement stmt = connection.createStatement();
		final String querySql = "SELECT COUNT(*) FROM DOG";
		final ResultSet rs = stmt.executeQuery(querySql);

		assertTrue(rs.next());
		assertEquals(3, rs.getInt(1));

		rs.close();
		stmt.close();
		connection.close();
	}

	public void callStoredProc() throws SQLException {
		final Connection connection = DriverManager.getConnection(url, user,
				password);
		final CallableStatement stmt = connection
				.prepareCall("{call annual_salary_raise(?,?)}");

		stmt.setInt(1, 4);
		stmt.registerOutParameter(2, Types.INTEGER);

		stmt.execute();

		int updatedEmployees = stmt.getInt(2);
		logger.info("{} record has been updated", updatedEmployees);

		stmt.close();
		connection.close();
	}

	public void connectionPools() throws SQLException {
		final ComboPooledDataSource cpds = new ComboPooledDataSource();
		cpds.setJdbcUrl(url);
		cpds.setUser(user);
		cpds.setPassword(password);

		final String sql = "SELECT COUNT(*) FROM DOG";
		final Connection conn = cpds.getConnection();
		final Statement stmt = conn.createStatement();
		final ResultSet rs = stmt.executeQuery(sql);

		while (rs.next()) {
			logger.info("Totoal count is {}", rs.getInt(1));
		}
		
		DataSources.destroy(cpds);
	}
	
	@Test
	public void connectToH2() throws SQLException {
		final ComboPooledDataSource cpds = new ComboPooledDataSource();
		cpds.setJdbcUrl(h2Url);
		cpds.setUser(user);
		cpds.setPassword(password);
		
		final Connection conn = cpds.getConnection();
		final Statement stmt = conn.createStatement();
		stmt.executeUpdate("create table teams (id INTEGER, name VARCHAR(100))");
		stmt.executeUpdate("insert into teams values (1, 'Red Team')");
		stmt.executeUpdate("insert into teams values (2, 'Blue Team')");
		stmt.executeUpdate("insert into teams values (3, 'Green Team')");
		
		final ResultSet rs = stmt.executeQuery("select count(*) from teams");
		
		while (rs.next()) {
			logger.info("Totoal count is {}", rs.getInt(1));
		}
	}
}
