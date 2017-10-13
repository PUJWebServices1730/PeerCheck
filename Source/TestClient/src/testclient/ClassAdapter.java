/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testclient;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;

/**
 *
 * @author davlad
 */
public class ClassAdapter {
	
	public static integration.users.Users initUser(Date birthdate, String email, String name, String password) {
		integration.users.Users user = new integration.users.Users();
		try {
			GregorianCalendar c = new GregorianCalendar();
			c.setTime(birthdate);
			user.setBirthdate(DatatypeFactory.newInstance().newXMLGregorianCalendar(c));
		} catch (DatatypeConfigurationException ex) {
			Logger.getLogger(ClassAdapter.class.getName()).log(Level.SEVERE, null, ex);
		}
		user.setEmail(email);
		user.setName(name);
		user.setPassword(password);
		user.setRole("Author");
		return user;
	}
	
}
