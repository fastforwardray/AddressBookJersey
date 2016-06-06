/**
 * 
 */
package edu.intuit.addressbook.validation;

import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.validator.EmailValidator;

import edu.intuit.addressbook.interfaces.Validator;


/**
 * @author Harsha.Ramakrishnapp
 *
 */
public class DetailsValidator implements Validator<Map<String, String>> {

	/* (non-Javadoc)
	 * @see edu.intuit.addressbook.interfaces.Validator#isValid(java.lang.Object)
	 */
	@Override
	public boolean isValid(Map<String, String> obj) {
		for(Entry<String, String> detail: obj.entrySet()) {
			if (detail.getKey().equals("MOBILE") 
					|| detail.getKey().equals("MOBILE2") 
					|| detail.getKey().equals("PHONE") 
					|| detail.getKey().equals("PHONE2") 
					|| detail.getKey().equals("HOME")) {
				// http://www.journaldev.com/641/regular-expression-phone-number-validation-in-java
				// Adopted from the link above.
				if (detail.getValue().matches("\\d{10}")) continue;
		        //validating phone number with -, . or spaces
		        else if(detail.getValue().matches("\\d{3}[-\\.\\s]\\d{3}[-\\.\\s]\\d{4}")) continue;
		        //validating phone number with extension length from 3 to 5
		        else if(detail.getValue().matches("\\d{3}-\\d{3}-\\d{4}\\s(x|(ext))\\d{3,5}")) continue;
		        //validating phone number where area code is in braces ()
		        else if(detail.getValue().matches("\\(\\d{3}\\)-\\d{3}-\\d{4}")) continue;
		        //return false if nothing matches the input
		        else return false;
			} else if (detail.getKey().equals("EMAIL")) {
				// adopted from http://stackoverflow.com/a/16058059
				String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
				java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
				java.util.regex.Matcher m = p.matcher(detail.getValue());
				if (m.matches()) {
					continue;
				} else {
					return false;
				}
			} else {
				continue;
			}
		}
		return true;
		
	}

}
