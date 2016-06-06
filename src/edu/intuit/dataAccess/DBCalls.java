/**
 * 
 */
package edu.intuit.dataAccess;

/**
 * @author Harsha.Ramakrishnapp
 *
 */
public class DBCalls {

	public static final String GET_CONTACT = "{call   harshar.int_address_book_pkg.get_contact(?,?,?,?)}";
	public static final String SAVE_CONTACT = "{call   harshar.int_address_book_pkg.ups_contact(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
	public static final String GET_DETAILS = "{call   harshar.int_address_book_pkg.get_details(?,?)}";
	public static final String DELETE_PERSON = "{call   harshar.int_address_book_pkg.delete_person(?,?)}";
	public static final String DELETE_DETAIL = "{call   harshar.int_address_book_pkg.delete_contact_detail(?,?,?,?)}";
	public static final String SAVE_DETAIL = "{call   harshar.int_address_book_pkg.add_detail(?,?,?)}";
	public static final String SEARCH_CONTACT = "{call   harshar.int_address_book_pkg.search_contact(?,?)}";
	public static final String GET_COUNT = "{call   harshar.int_address_book_pkg.get_contacts_count(?)}";
	public static final String GET_AUTO_COMPLETE_LIST = "{call   harshar.int_address_book_pkg.get_list_for_auto_complete(?)}";
	 
}
