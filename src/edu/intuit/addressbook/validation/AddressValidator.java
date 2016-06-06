/**
 * 
 */
package edu.intuit.addressbook.validation;

import edu.intuit.addressbook.interfaces.Validator;
import edu.intuit.addressbook.valueObjects.Address;

/**
 * @author Harsha.Ramakrishnapp
 *
 */
public class AddressValidator implements Validator<Address> {

	@Override
	public boolean isValid(Address obj) {
		if (obj.getLine1() == null
				|| obj.getCity() == null 
				|| obj.getState() == null
				) {
			return false;
		} else {
			return true;
		}
	}

}
