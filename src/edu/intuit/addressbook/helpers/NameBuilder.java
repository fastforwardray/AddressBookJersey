/**
 * 
 */
package edu.intuit.addressbook.helpers;

import edu.intuit.addressbook.valueObjects.Name;

/**
 * @author Harsha.Ramakrishnapp
 *
 */
public class NameBuilder implements Builder<Name> {

	private String firstName;
	private String lastName;
	private String middleInitial;
	
	public static NameBuilder newNameBuilder() {
		return new NameBuilder();
	}

	@Override
	public Name buildEntity() {
		Name name = new Name();
		name.setFirstName(firstName);
		name.setLastName(lastName);
		name.setMiddleInitial(middleInitial);
		return name;
	}
	
	public NameBuilder withFirstName(String firstName) {
		this.firstName = firstName.replaceAll("[^a-zA-Z]+","");
		return this;
	}
	
	public NameBuilder withLastName(String lastName) {
		this.lastName = lastName.replaceAll("[^a-zA-Z]+","");
		return this;
	}
	
	public NameBuilder withMiddleInitial(String middleInitial) {
		this.middleInitial = middleInitial.replaceAll("[^a-zA-Z]+","");
		this.middleInitial = this.middleInitial.substring(0,  1).toUpperCase();
		return this;
	}

}
