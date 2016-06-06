/**
 * 
 */
package edu.intuit.addressbook.valueObjects;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Harsha.Ramakrishnapp
 *
 */
@XmlRootElement
public class AutoComplete {
	private Integer ID;
	private String firstName;
	public Integer getID() {
		return ID;
	}
	public void setID(Integer iD) {
		ID = iD;
	}
	private String lastName;
	private String detail;
	public AutoComplete(Integer ID, String firstName, String lastName, String detail, String type) {
		super();
		this.ID = ID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.detail = detail;
		this.type = type;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public AutoComplete() {
		super();
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	private String type;
}
