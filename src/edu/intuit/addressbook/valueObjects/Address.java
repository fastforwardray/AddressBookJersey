/**
 * 
 */
package edu.intuit.addressbook.valueObjects;

/**
 * @author Harsha.Ramakrishnapp
 *
 */
public class Address {
	public Address() {
		super();
	}
	/**
	 * @param line1
	 * @param line2
	 * @param city
	 * @param state
	 * @param zipCode
	 */
	public Address(String line1, String line2, String city, String state, String country, String zipCode) {
		super();
		this.line1 = line1;
		this.line2 = line2;
		this.city = city;
		this.state = state;
		this.country = country;
		this.zipCode = zipCode;
	}
	private String line1;
	private String line2;
	private String city;
	private String state;
	private String country;
	private String zipCode;
	public String getLine1() {
		return line1;
	}
	public void setLine1(String line1) {
		this.line1 = line1;
	}
	public String getLine2() {
		return line2;
	}
	public void setLine2(String line2) {
		this.line2 = line2;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	@Override
	public String toString() {
		return line1 + ", " + line2 + ", " + city + ", " + state + ", " + country + ", " + zipCode ;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
}
