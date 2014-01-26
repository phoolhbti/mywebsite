package com.pc.test.cqutil.jcrquery;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConnectionHelper {
	/** Default log. */
	protected final Logger log = LoggerFactory.getLogger(this.getClass());
	private String url;
	private static ConnectionHelper instance;

	private ConnectionHelper() {
		url = "jdbc:mysql://localhost:3306/CQ";
	}

	public static Connection getConnection() throws SQLException {

		if (instance == null) {
			instance = new ConnectionHelper();
		}
		try {
			return DriverManager.getConnection(instance.url, "root", "root");
		} catch (SQLException e) {
			throw e;
		}
	}

	public static void close(Connection connection) {
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}