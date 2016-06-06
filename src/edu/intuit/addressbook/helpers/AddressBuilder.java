/**
 * 
 */
package edu.intuit.addressbook.helpers;

import edu.intuit.addressbook.valueObjects.Address;

/**
 * @author Harsha.Ramakrishnapp
 *
 */
public class AddressBuilder implements Builder<Address> {

	@Override
	public Address buildEntity() {
		Address address = new Address(line1, line2, city, state, country, zipCode);
		return address;
	}

	private String line1;
	private String line2;
	private String city;
	private String state;
	private String country;
	private String zipCode;
	
	public static AddressBuilder newAddressBuilder() {
		return new AddressBuilder();
	}
	
	public AddressBuilder withLine1(String line1) {
		this.line1 = line1;
		return this;
	}
	
	public AddressBuilder withLine2(String line2) {
		this.line2 = line2;
		return this;
	}
	
	public AddressBuilder withCity(String city) {
		this.city = city;
		return this;
	}
	
	public AddressBuilder withState(String state) {
		this.state = state;
		return this;
	}
	
	public AddressBuilder withCountry(String country) {
		this.country = country;
		return this;
	}
	
	public AddressBuilder withZipCode(String zipCode) {
		this.zipCode = zipCode;
		return this;
	}
	
	public AddressBuilder withCompleteAddress(String addressValue) {
		String[] add = addressValue.split("/");
		if (add.length == 5) {
			this.line1 = add[0];
			this.line2 = add[1];
			this.city = add[2];
			this.state = add[3];
			this.zipCode = add[4];
		} else {
			this.line1 = add[0];
			this.city = add[1];
			this.state = add[2];
			this.zipCode = add[3];
		}
		
		return this;
		
	}
}
