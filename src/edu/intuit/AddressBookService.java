/**
 * 
 */
package edu.intuit;

import java.io.IOException;
import java.net.URI;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import edu.intuit.addressbook.helpers.AddressBuilder;
import edu.intuit.addressbook.helpers.ContactBuilder;
import edu.intuit.addressbook.helpers.NameBuilder;
import edu.intuit.addressbook.validation.DetailsValidator;
import edu.intuit.addressbook.valueObjects.Address;
import edu.intuit.addressbook.valueObjects.AutoComplete;
import edu.intuit.addressbook.valueObjects.Contact;
import edu.intuit.addressbook.valueObjects.Name;
import edu.intuit.dataAccess.ContactData;

/**
 * @author Harsha.Ramakrishnapp
 *
 */
@Path("/addressBook")
public class AddressBookService {
	// List<Contact> contacts;

	// Allows to insert contextual objects into the class,
	// e.g. ServletContext, Request, Response, UriInfo
	@Context
	UriInfo uriInfo;
	@Context
	Request request;

	final static Logger log = Logger.getLogger(AddressBookService.class);

	// Returns a list of contacts to the browser
	@GET
	@Produces(MediaType.TEXT_XML)
	public List<Contact> getContactsText() {
		log.debug("Getting contacts as XML for browser");
		List<Contact> contacts = new ContactData().getContact(null);
		return contacts;
	}

	// Return the list of contacts in XML or JSON format.
	@GET
	@Path("/{param}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public List<Contact> getContacts(@PathParam("param") String ID) {
		int contactID;

		try {
			contactID = Integer.parseInt(ID);
		} catch (NumberFormatException ex) {
			log.error("Passed input ID is not a valid number.");
			return null;
		}

		List<Contact> contacts = new ContactData().getContact(contactID);
		return contacts;

	}
	
	/*
	 * Returns the Hateoas compliant response object for contacts.
	 * Same data as GetContacts() but different format. Purely illustration purposes only.
	 */

	@GET
	@Path("hateoas")
    @Produces(MediaType.APPLICATION_JSON)
    public JSONArray getContactsHateoas() throws JSONException {
        
		JSONObject contacts = new ContactData().getContactHateoas(uriInfo);
		JSONArray array = new JSONArray();
		array.put(contacts);
        
        /* Needs J2EE SDK. I have J2SE
        JSONContacts = Json.createObjectBuilder()
      		  .add("employees", Json.createArrayBuilder()
      		    .add(Json.createObjectBuilder()
      		      .add("firstName", "John")
      		      .add("lastName", "Doe")))
      		  .build();
        */
        
        return array;
    }
	// Gets the total count of current contacts
	@GET
	@Path("count")
	@Produces(MediaType.TEXT_PLAIN)
	public String getCount() {
		return String.valueOf(new ContactData().getCount());
	}

	// Gets the total count of current contacts
	@GET
	@Path("autocomplete")
	@Produces(MediaType.APPLICATION_XML)
	public List<AutoComplete> getAutoCompleteData() {
		List<AutoComplete> autoComplete = new ContactData().getAutoCompleteList();
		
		return autoComplete;
	}
	
	// deletes contact. Soft delete only.
	@DELETE
	@Path("/{param}")
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteContact(@PathParam("param") String ID) {
		int contactID;

		try {
			contactID = Integer.parseInt(ID);
		} catch (NumberFormatException ex) {
			log.error("Passed input ID is not a valid number.");
			return null;
		}

		return String.valueOf(new ContactData().deleteContact(contactID));
	}

	// Based on the value of ID, Insert or update of contact. If ID == null => create new
	// Put is not implemented but post handles put's function too. Complex code in the DB but easier to maintain.
	@POST
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String newContact(@FormParam("id") String id, @FormParam("firstName") String firstName,
			@FormParam("lastName") String lastName, @FormParam("middleInitial") String middleInitial,
			@FormParam("detailType") String detailType, @FormParam("detailValue") String detailValue,
			@FormParam("addressType") String addressType, @FormParam("addressValue") String addressValue,
			@FormParam("dateOfBirth") String dateOfBirth, @Context HttpServletResponse servletResponse)
			throws IOException, SQLException {

		Integer contactID = null;

		try {
			if (id != null && !id.equals(""))
				contactID = Integer.parseInt(id);
		} catch (NumberFormatException ex) {
			return "Invalid ID format.";
		}
		Address address;
		String[] add = addressValue.split("\\|");
		if (add.length == 6) {
			address = new AddressBuilder()
				.withLine1(add[0])
				.withLine2(add[1])
				.withCity(add[2])
				.withState(add[3])
				.withCountry(add[4])
				.withZipCode(add[5]).buildEntity();

		} else {
			
			address = new AddressBuilder()
					.withLine1(add[0])
					.withCity(add[1])
					.withState(add[2])
					.withCountry(add[3])
					.withZipCode(add[4]).buildEntity();

		}

		Map<String, Address> addressMap = new HashMap<String, Address>();
		addressMap.put(addressType, address);

		Map<String, String> details = new HashMap<String, String>();
		details.put(detailType, detailValue);
		
		boolean isValidDetailsMap = new DetailsValidator().isValid(details);
		
		if (!isValidDetailsMap) {
			return "Invalid contact details format.";
			// Instead of all of these returns statements, I could create a custom exception and throw it and catch in calling function.
			// I can assign the message and have a stack trace to debug.
		}

		SimpleDateFormat format = new SimpleDateFormat("mm/dd/yyyy");
		Date dob;
		try {
			dob = format.parse(dateOfBirth);
		} catch (ParseException e) {
			return "Invalid date format. Please enter in mm/dd/yyyy.";
		}
		Contact contact = new ContactBuilder()
				.withID(contactID)
				.withName(new NameBuilder().withFirstName(firstName).withLastName(lastName).withMiddleInitial(middleInitial).buildEntity())
				.withAddressMap(addressMap)
				.withDetails(details)
				.withTags(null)
				.withDateOfBirth(dob)
				.buildEntity();
				
				int returnedID = new ContactData().saveContact(contact);
				if (returnedID == 0) {
					return "Contact with ID " + id + " updated.";
				} else {
					return "Contact with ID " + returnedID + " created.";
				}
		
	}
	
}
