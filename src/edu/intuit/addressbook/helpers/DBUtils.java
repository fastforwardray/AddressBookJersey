/**
 * 
 */
package edu.intuit.addressbook.helpers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Harsha.Ramakrishnapp
 *
 */
public class DBUtils {
	public static void cleanup(Connection conn, Statement stmt) {
		try {
			//ret.close();
			stmt.close();
			conn.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
}
