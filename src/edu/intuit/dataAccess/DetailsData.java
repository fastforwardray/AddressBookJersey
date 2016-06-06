/**
 * 
 */
package edu.intuit.dataAccess;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;


import edu.intuit.addressbook.jdbc.JdbcConnection;
import oracle.jdbc.OracleTypes;

/**
 * @author Harsha.Ramakrishnapp
 *
 */
public class DetailsData extends BaseData {
	public Map<String, String> getContact(int personID) {
		ResultSet retMainCursor;
		Map<String, String> details = new HashMap<String, String>();
		try {
			conn = JdbcConnection.getConnection();
			stmt = conn.prepareCall(DBCalls.GET_DETAILS);
			stmt.setInt(1, personID);
			stmt.registerOutParameter(2, OracleTypes.CURSOR);
			
			//stmt.registerOutParameter(3, OracleTypes.VARCHAR);
	        stmt.execute();
	        retMainCursor = (ResultSet) stmt.getObject(2);
	        
	        while (retMainCursor.next()) {
	        	details.put(retMainCursor.getString("contact_type_fk"), retMainCursor.getString("details"));
	        }
		} catch (Exception ex) {
	        //LOGGER.error("Error extracting ", e);
	    } finally {
	        //DBUtils.cleanup(conn.getConnection(), stmt);
	    }
		return details;
	}
	
	public int updateDetails(int personID, String DetailType, String Value) {
		ResultSet retMainCursor;
		
		try {
			conn = JdbcConnection.getConnection();
			stmt = conn.prepareCall(DBCalls.GET_DETAILS);
			stmt.setInt(1, personID);
			stmt.setString(2, DetailType);
			stmt.setString(3, Value);
			
	        stmt.execute();
	        
		} catch (Exception ex) {
	        //LOGGER.error("Error extracting ", e);
	    } finally {
	        //DBUtils.cleanup(conn.getConnection(), stmt);
	    }
		return 1;
	}
	
	public int saveDetails(int personID, String DetailType, String Value) {
		ResultSet retMainCursor;
		
		try {
			conn = JdbcConnection.getConnection();
			stmt = conn.prepareCall(DBCalls.SAVE_DETAIL);
			stmt.setInt(1, personID);
			stmt.setString(2, DetailType);
			stmt.setString(3, Value);
			
	        stmt.execute();
	        
		} catch (Exception ex) {
	        //LOGGER.error("Error extracting ", e);
	    } finally {
	        //DBUtils.cleanup(conn.getConnection(), stmt);
	    }
		return 1;
	}
	
	public String deleteDetail(int personID, String DetailType, String Value) {
		String ret = null;
		
		try {
			conn = JdbcConnection.getConnection();
			stmt = conn.prepareCall(DBCalls.DELETE_DETAIL);
			stmt.setInt(1, personID);
			stmt.setString(2, DetailType);
			stmt.setString(3, Value);
			stmt.registerOutParameter(4, OracleTypes.VARCHAR);
	        stmt.execute();
	        
	        ret = stmt.getString(4);
		} catch (Exception ex) {
	        //LOGGER.error("Error extracting ", e);
	    } finally {
	        //DBUtils.cleanup(conn.getConnection(), stmt);
	    }
		return ret;
	}
}
