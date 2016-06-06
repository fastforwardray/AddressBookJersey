/**
 * 
 */
package mocks.valueObjects;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.intuit.addressbook.valueObjects.Address;
import edu.intuit.addressbook.valueObjects.Contact;
import edu.intuit.addressbook.valueObjects.Name;

/**
 * @author Harsha.Ramakrishnapp
 *
 */
public class Contacts {
	
	public List<Contact> getMockContacts() {
		Name name1 = new Name("Harsha", "Ram", "R");
		//Address address1 = new Address("Lane 12", "Optional lane", "Belleview", "FL", "12345");
		Map<String, Address> address1 = new HashMap<String, Address>();
		address1.put("HOME", new Address("Lane 12", "Optional lane", "Belleview", "FL", "USA", "12345"));
		address1.put("WORK", new Address("Trial 2", "Nowhere", "Ocala", "FL", "USA", "54321"));
		
		Map<String, String> details1 = new HashMap<String, String>();
		details1.put("MOBILE", "654123526");
		details1.put("MOBILE2", "6541876526");
		Contact c1 = new Contact(1, name1, new Date(88, 4, 13), address1, details1, null); 
		
		Name name2 = new Name("Pri", "Ram", "R");
		
		Map<String, Address> address2 = new HashMap<String, Address>();
		address2.put("HOME", new Address("Lane 54", "Optional lane", "Belleview", "FL", "USA", "12345"));
		address2.put("WORK", new Address("Trial 4", "Nowhere", "Ocala", "FL", "USA", "54321"));
		
		Map<String, String> details2 = new HashMap<String, String>();
		details2.put("MOBILE", "873426");
		details2.put("EMAIL", "no@no.com");
		Contact c2 = new Contact(1, name2, new Date(88, 4, 3), address2, details2, null); 
		
		List<Contact> contacts = new ArrayList<Contact>();
		contacts.add(c1);
		contacts.add(c2);
		return contacts;
	}
}
