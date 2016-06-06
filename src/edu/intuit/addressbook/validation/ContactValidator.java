/**
 * 
 */
package edu.intuit.addressbook.validation;
import edu.intuit.addressbook.interfaces.Validator;
import edu.intuit.addressbook.valueObjects.Contact;

/**
 * @author Harsha.Ramakrishnapp
 *
 */
public class ContactValidator implements Validator<Contact>{

	@Override
	public boolean isValid(Contact obj) {
		Contact contact = (Contact) obj;
		if (contact.getName() == null
			|| contact.getDateOfBirth() == null ) { 
			return false;
		} else {
			return true;
		}
	}

}
