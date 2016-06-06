/**
 * 
 */
package edu.intuit.addressbook.valueObjects;

import java.util.List;

/**
 * @author Harsha.Ramakrishnapp
 *
 */
public class AddressBook {
	List<Contact> contacts;

	public List<Contact> getContacts() {
		return contacts;
	}

	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}
	
}
