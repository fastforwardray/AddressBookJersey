/**
 * 
 */
package edu.intuit.addressbook.valueObjects;
//import java.sql.Date;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Harsha.Ramakrishnapp
 *
 */
@XmlRootElement
public class Contact {
	/**
	 * 
	 */
	public Contact() {
		super();
	}
	/**
	 * @param iD
	 * @param name
	 * @param dateOfBirth
	 * @param address
	 * @param details
	 * @param tags
	 */
	public Contact(Integer iD, Name name, Date dateOfBirth, Map<String, Address> address, Map<String, String> details,
			String[] tags) {
		super();
		ID = iD;
		this.name = name;
		this.dateOfBirth = dateOfBirth;
		this.address = address;
		this.details = details;
		this.tags = tags;
	}
	public Integer getID() {
		return ID;
	}
	public void setID(Integer iD) {
		ID = iD;
	}
	public Name getName() {
		return name;
	}
	public void setName(Name name) {
		this.name = name;
	}
	public Map<String, Address> getAddress() {
		return address;
	}
	public void setAddress(Map<String, Address> address) {
		this.address = address;
	}
	public Map<String, String> getDetails() {
		return details;
	}
	public void setDetails(Map<String, String> details) {
		this.details = details;
	}
	public String[] getTags() {
		return tags;
	}
	
	public String getTagsAsString() {
		if (tags == null) return null;
		StringBuilder sb = new StringBuilder();
		for(String tag: tags) {
			sb.append(tag+",");
		}
	
	return sb.toString();
	}
	public void setTags(String[] tags) {
		this.tags = tags;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(java.util.Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
	public void printContact() {
		System.out.println("Name: " + name.toString() + " Date of birth: " + getDateOfBirth());
		for(Entry<String, Address> adr : address.entrySet()) {
			System.out.println(adr.getKey() + " : " + adr.getValue().toString());
		}
		for(Entry<String, String> adr : details.entrySet()) {
			System.out.println(adr.getKey() + " : " + adr.getValue());
		}
		System.out.print("Tags : ");
		for(String tag: tags) {
			System.out.print(tag + ", ");
		}
		System.out.println();
    }
	
	public String getAddressTypes() {
		StringBuilder sb = new StringBuilder();
		for(String s: address.keySet()) {
			sb.append(s);
			sb.append("|");
		}
		
		return sb.toString();
	}
	
	public String getDetailsTypes() {
		StringBuilder sb = new StringBuilder();
		for(String s: details.keySet()) {
			sb.append(s);
			sb.append("|");
		}
		
		return sb.toString();
	}
	
	public String getDetailsValues() {
		StringBuilder sb = new StringBuilder();
		for(String s: details.values()) {
			sb.append(s);
			sb.append("|");
		}
		
		return sb.toString();
	}
	
	public String[] getAddressValues() {
		StringBuilder[] sb = new StringBuilder[6];
		int i = 0;
		/*
		 * i = 0 for line1
		 * i = 1 for line 2
		 * i = 2 for city
		 * i = 3 for state
		 * i = 4 for country
		 * i = 5 for zip code
		 */
		for(Address adr: address.values()) {
			i = 0;
			sb[i].append(adr.getLine1().replaceAll("|", ""));
			sb[i].append("|");
			i++;
			sb[i].append(adr.getLine2().replaceAll("|", ""));
			sb[i].append("|");
			i++;
			sb[i].append(adr.getCity().replaceAll("|", ""));
			sb[i].append("|");
			i++;
			sb[i].append(adr.getState().replaceAll("|", ""));
			sb[i].append("|");
			i++;
			sb[i].append(adr.getCountry().replaceAll("|", ""));
			sb[i].append("|");
			i++;
			sb[i].append(adr.getState().replaceAll("|", ""));
			sb[i].append("|");
		}
		
		return new String[] {sb[0].toString(), 
				sb[1].toString(),
				sb[2].toString(),
				sb[3].toString(),
				sb[4].toString(),
				sb[5].toString(),};
	}
	
	private Integer ID;
	private Name name;
	private java.util.Date dateOfBirth;
	private Map<String, Address> address;
	private Map<String, String> details;
	private String[] tags;
	//private image
}
