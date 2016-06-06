/**
 * 
 */
package edu.intuit.addressbook.valueObjects;

/**
 * @author Harsha.Ramakrishnapp
 *
 */
public class Name {
	public Name() {
		super();
	}
	public Name(String firstName, String lastName, String middleInitial) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.middleInitial = middleInitial;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getMiddleInitial() {
		return middleInitial;
	}
	public void setMiddleInitial(String middleInitial) {
		this.middleInitial = middleInitial;
	}
	
	
	@Override
	public String toString() {
		return firstName + " " +  lastName + " " + middleInitial ;
	}


	private String firstName;
	private String lastName;
	private String middleInitial;
	
}
