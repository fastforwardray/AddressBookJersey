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
		this.firstName = firstName;
		return this;
	}
	
	public NameBuilder withLastName(String lastName) {
		this.lastName = lastName;
		return this;
	}
	
	public NameBuilder withMiddleInitial(String middleInitial) {
		this.middleInitial = middleInitial;
		return this;
	}

}
