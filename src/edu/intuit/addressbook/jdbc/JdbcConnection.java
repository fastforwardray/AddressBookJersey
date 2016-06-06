/**
 * 
 */
/**
 * 
 */
package edu.intuit.addressbook.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Harsha.Ramakrishnapp
 * 
 */
public class JdbcConnection {
	private static JdbcConnection instance;

	private static Connection connection;
	
	
	private JdbcConnection() {

	}

	

	public static Connection getConnection() {
		
		if (instance == null) {
			instance = new JdbcConnection();
			connection = null;
			try {

				Class.forName("oracle.jdbc.driver.OracleDriver");

			} catch (Exception e) {

				System.out.println("Oracle JDBC Driver has gone AWOL");
				e.printStackTrace();
				return null;

			}

			try {

				connection = DriverManager
						.getConnection(
								"jdbc:oracle:thin:@ods.db.development.local:1521/ODSDHA",
								"harshar", "harshar");

			} catch (SQLException e) {

				System.out.println("Connection Failed! Check output console");
				e.printStackTrace();
				return null;

			}
			
		}
		return connection;
	}
}
