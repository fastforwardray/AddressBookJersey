/**
 * 
 */
package edu.intuit.addressbook.helpers;

import java.util.Date;
import java.util.List;
import java.util.Map;

import edu.intuit.addressbook.valueObjects.Address;
import edu.intuit.addressbook.valueObjects.Contact;
import edu.intuit.addressbook.valueObjects.Name;


/**
 * @author Harsha.Ramakrishnapp
 *
 */
public class ContactBuilder implements Builder<Contact> {
	public static ContactBuilder newContactBuilder() {
		return new ContactBuilder();
	}
	
	private Integer ID;
	private Name name;
	private java.util.Date dateOfBirth;
	private Map<String, Address> address;
	private Map<String, String> details;
	private String[] tags;
	
	@Override
	public Contact buildEntity() {
		Contact contact = new Contact(ID, name, dateOfBirth, address, details, tags);
		
		return contact;
	}
	
	public ContactBuilder withID(Integer ID) {
		this.ID = ID;
		return this;
	}
	
	public ContactBuilder withName(Name name) {
		this.name = name;
		return this;
	}
	
	public ContactBuilder withDateOfBirth(Date dob) {
		this.dateOfBirth = dob;
		return this;
	}
	public ContactBuilder withAddressMap(Map<String, Address> address) {
		this.address = address;
		return this;
	}
	public ContactBuilder withDetails(Map<String, String> details) {
		this.details = details;
		return this;
	}
	public ContactBuilder withTags(String[] tags) {
		this.tags = tags;
		return this;
	}
}
