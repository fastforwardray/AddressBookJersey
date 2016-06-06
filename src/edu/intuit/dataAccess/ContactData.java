/**
 * 
 */
package edu.intuit.dataAccess;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.intuit.addressbook.helpers.DBUtils;
import edu.intuit.addressbook.jdbc.JdbcConnection;
import edu.intuit.addressbook.valueObjects.Address;
import edu.intuit.addressbook.valueObjects.Contact;
import edu.intuit.addressbook.valueObjects.Name;
import oracle.jdbc.OracleTypes;

/**
 * @author Harsha.Ramakrishnapp
 *
 */
public class ContactData extends BaseData{
	public List<Contact> getContact(Integer ID) {
		ResultSet retMainCursor;
		List<Contact> contacts = new ArrayList<Contact>();
		try {
			conn = JdbcConnection.getConnection();
			stmt = conn.prepareCall(DBCalls.GET_CONTACT);
			if (ID == null) {
				stmt.setNull(1, OracleTypes.INTEGER);
			} else {
				stmt.setInt(1, ID);
			}
			stmt.registerOutParameter(2, OracleTypes.CURSOR);
			stmt.registerOutParameter(3, OracleTypes.CURSOR);
			stmt.registerOutParameter(4, OracleTypes.CURSOR);
			
			//stmt.registerOutParameter(3, OracleTypes.VARCHAR);
	        stmt.execute();
	        retMainCursor = (ResultSet) stmt.getObject(2);
	        ResultSet retDetailsCursor = (ResultSet) stmt.getObject(3);
	        ResultSet retAddressCursor = (ResultSet) stmt.getObject(4);
	        
	        while (retMainCursor.next()) {
	        	
	        	Contact contact = new Contact();
	        	Map<String, String> details = new HashMap<String, String>();
	        	Map<String, Address> address = new HashMap<String, Address>();
	        	
	        	contact.setName(
	        			new Name(retMainCursor.getString("first_name"),
	        					retMainCursor.getString("last_name"),
	        					retMainCursor.getString("middle_name")
	        					)
	        			);
	        	
	        	contact.setID(retMainCursor.getInt("p_id"));
	        	contact.setDateOfBirth(retMainCursor.getDate("dob"));
	        	if (retMainCursor.getString("tags") != null) {
	        		contact.setTags(retMainCursor.getString("tags").split(","));
	        	} else {
	        		contact.setTags(null);
	        	}
	        	
	        	while(retDetailsCursor.next()) {
	        		if (retDetailsCursor.getInt("p_id") == contact.getID()) {
	        			details.put(retDetailsCursor.getString("contact_type_fk"), retDetailsCursor.getString("details"));
	        		}
	        	}
	        	
	        	while(retAddressCursor.next()) {
	        		if (retAddressCursor.getInt("p_id") == contact.getID()) {
	        			address.put(retAddressCursor.getString("address_type_fk"),
	        					new Address(
	        							retAddressCursor.getString("address_line1"),
	        							retAddressCursor.getString("address_line2"),
	        							retAddressCursor.getString("city_nm"),
	        							retAddressCursor.getString("state_cd"),
	        							retAddressCursor.getString("country"),
	        							retAddressCursor.getString("zip_cd"))
	        					);
	        			
	        		}
	        	}
	        	contact.setDetails(details);
	        	contact.setAddress(address);
	        	contacts.add(contact);
	        }
	        
	        
		} catch (Exception ex) {
	        //LOGGER.error("Error extracting ", e);
			ex.printStackTrace();
	    } finally {
	        //DBUtils.cleanup(conn.getConnection(), stmt);
	    }
		return contacts;
	}
	
	public int saveContact(Contact contact) throws SQLException {
		ResultSet ret;
		int newID = 0;
		//List<Contact> contacts = new ArrayList<Contact>();
		try {
			conn = JdbcConnection.getConnection();
			stmt = conn.prepareCall(DBCalls.SAVE_CONTACT);
			if (contact.getID() == null) {
				stmt.setNull(1, OracleTypes.INTEGER);
			} else {
				stmt.setInt(1, contact.getID());
			}
			
			try {
				String firstName = contact.getName().getFirstName();
				stmt.setString(2, firstName);
			} catch (Exception ex){
				stmt.setNull(2, OracleTypes.VARCHAR);
			}
			
			try {
				String middleName = contact.getName().getMiddleInitial();
				stmt.setString(3, middleName);
			} catch (Exception ex){
				stmt.setNull(2, OracleTypes.VARCHAR);
			}
			
			try {
				String lastName = contact.getName().getLastName();
				stmt.setString(4, lastName);
			} catch (Exception ex){
				stmt.setNull(4, OracleTypes.VARCHAR);
			}
			stmt.setDate(5, new java.sql.Date(contact.getDateOfBirth().getTime()));
			stmt.setString(6, contact.getTagsAsString());
			
			try {
				String detailTypes = contact.getDetailsTypes();
				stmt.setString(7, detailTypes);
			} catch (Exception ex){
				stmt.setNull(7, OracleTypes.VARCHAR);
			}

			try {
				String detailValue = contact.getDetailsValues();
				stmt.setString(8, detailValue);
			} catch (Exception ex){
				stmt.setNull(8, OracleTypes.VARCHAR);
			}

			try {
				String addresstype = contact.getAddressTypes();
				stmt.setString(9, addresstype);
			} catch (Exception ex){
				stmt.setNull(9, OracleTypes.VARCHAR);
			}
			
			try {
				String addr = contact.getAddressValues()[0];
				stmt.setString(10, addr);
			} catch (Exception ex){
				stmt.setNull(10, OracleTypes.VARCHAR);
			}
			try {
				String addr = contact.getAddressValues()[1];
				stmt.setString(11, addr);
			} catch (Exception ex){
				stmt.setNull(11, OracleTypes.VARCHAR);
			}
			try {
				String addr = contact.getAddressValues()[2];
				stmt.setString(12, addr);
			} catch (Exception ex){
				stmt.setNull(12, OracleTypes.VARCHAR);
			}
			try {
				String addr = contact.getAddressValues()[3];
				stmt.setString(13, addr);
			} catch (Exception ex){
				stmt.setNull(13, OracleTypes.VARCHAR);
			}
			try {
				String addr = contact.getAddressValues()[4];
				stmt.setString(14, addr);
			} catch (Exception ex){
				stmt.setNull(14, OracleTypes.VARCHAR);
			}
			try {
				String addr = contact.getAddressValues()[5];
				stmt.setString(15, addr);
			} catch (Exception ex){
				stmt.setNull(15, OracleTypes.VARCHAR);
			}
			
			stmt.registerOutParameter(16, OracleTypes.INTEGER);
			
	        stmt.execute();
	        
	        newID = stmt.getInt(16);
	        
	        
		} catch (Exception ex) {
			ex.printStackTrace();
			
		} finally {
			//DBUtils.cleanup(conn.getConnection(), stmt);
		}
		return newID;
	}
	
	public int getCount() {
		ResultSet ret;
		int count = 0;
		try {
			conn = JdbcConnection.getConnection();
			stmt = conn.prepareCall(DBCalls.GET_COUNT);
			stmt.registerOutParameter(1, OracleTypes.INTEGER);
			
			stmt.execute();
	        
			count = stmt.getInt(1);
	        
		}catch (Exception ex) {
			//LOGGER.error("Error extracting ", e);
		} finally {
			//DBUtils.cleanup(conn.getConnection(), stmt);
		}
		return count;
	}
	
	public String deleteContact(Integer personID) {
		String ret = null;
		
		try {
			conn = JdbcConnection.getConnection();
			stmt = conn.prepareCall(DBCalls.DELETE_PERSON);
			stmt.setInt(1, personID);
			stmt.registerOutParameter(2, OracleTypes.VARCHAR);
	        stmt.execute();
	        ret = stmt.getString(2);
		} catch (Exception ex) {
	        //LOGGER.error("Error extracting ", e);
	    } finally {
	        //DBUtils.cleanup(conn.getConnection(), stmt);
	    }
		return ret;
		
		
	}
}

