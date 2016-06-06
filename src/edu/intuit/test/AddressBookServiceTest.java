/**
 * 
 */
package edu.intuit.test;

import java.sql.Connection;
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
import edu.intuit.addressbook.jdbc.*;
import edu.intuit.addressbook.valueObjects.*;
/**
 * @author Harsha.Ramakrishnapp
 *
 */
public class AddressBookServiceTest {

	@Test
	public void checkDatabaseConnection() {
		Connection conn = JdbcConnection.getConnection();
		Assert.assertTrue(conn!= null);
	}
	
	@Test
	public void buildValidName() {
		String firstName = "Har";
		String lastName = "Car";
		String middleInitial = "C";
		
		Name name = new NameBuilder()
						.withFirstName(firstName)
						.withLastName(lastName)
						.withMiddleInitial(middleInitial)
						.buildEntity();
		Assert.assertTrue(name != null);
	}
	
	@Test
	public void buildValidNameWithLongMiddle() {
		String firstName = "Har";
		String lastName = "Car";
		String middleInitial = "Cain";
		
		Name name = new NameBuilder()
						.withFirstName(firstName)
						.withLastName(lastName)
						.withMiddleInitial(middleInitial)
						.buildEntity();
		Assert.assertTrue(name.getMiddleInitial().length() == 1);
	}
	
	@Test
	public void buildValidAddress() {
		Address address = new AddressBuilder()
				.withLine1("342 Hostess lane")
				.withLine2("")
				.withCity("Belmont")
				.withState("NE")
				.withCountry("USA")
				.withZipCode("14782").buildEntity();
		
		Assert.assertTrue(address != null);
	}
	
	@Test
	public void buildValidContact() {
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
		
		Assert.assertTrue(contact != null);
	}
	

}
