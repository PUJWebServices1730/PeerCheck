package model;


import java.util.Date;

/**
 * Represents a User of the PeerCheck system.
 * @author davl3232
 */
public class User {
	Date birthDate;
	String email;
	String name;
	String password;

	public User(Date birthDate, String email, String name, String password) {
		this.birthDate = birthDate;
		this.email = email;
		this.name = name;
		this.password = password;
	}
}
