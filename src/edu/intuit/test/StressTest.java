/**
 * 
 */
package edu.intuit.test;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import edu.intuit.addressbook.helpers.AddressBuilder;
import edu.intuit.addressbook.helpers.ContactBuilder;
import edu.intuit.addressbook.helpers.NameBuilder;
import edu.intuit.addressbook.valueObjects.Address;
import edu.intuit.addressbook.valueObjects.Contact;
import edu.intuit.addressbook.valueObjects.Name;
import edu.intuit.dataAccess.ContactData;

/**
 * @author Harsha.Ramakrishnapp
 *
 */

public class StressTest implements Runnable{
	@Override
	public void run() {
		String firstName = "Har";
		String lastName = "Car";
		String middleInitial = "Cain";

		Name name = new NameBuilder()
				.withFirstName(firstName)
				.withLastName(lastName)
				.withMiddleInitial(middleInitial)
				.buildEntity();
		Address address = new AddressBuilder()
				.withLine1("342 Hostess lane")
				.withLine2("")
				.withCity("Belmont")
				.withState("NE")
				.withCountry("USA")
				.withZipCode("14782").buildEntity();

		Map<String, Address> addressMap = new HashMap<String, Address>();
		addressMap.put("HOME", address);

		Map<String, String> details = new HashMap<String, String>();
		details.put("HOME", "1234567890");

		SimpleDateFormat format = new SimpleDateFormat("mm/dd/yyyy");
		Date dob = null;
		try {
			dob = format.parse("06/06/16");
		} catch (ParseException e) {
			Assert.assertFalse(true);
		}

		Contact contact = new ContactBuilder()
				.withID(null)
				.withName(name)
				.withAddressMap(addressMap)
				.withDetails(details)
				.withTags(null)
				.withDateOfBirth(dob)
				.buildEntity();

		int returnedID = 0;
		try {
			returnedID = new ContactData().saveContact(contact);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			new ContactData().deleteContact(returnedID);
		} catch (NumberFormatException ex) {
			
			ex.printStackTrace();
		}
	}



@Test
public void stressTest() {

	Thread threads = new Thread(this);


	}

}
