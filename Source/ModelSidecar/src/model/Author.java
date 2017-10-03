/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;

/**
 *
 * @author davl3232
 */
public class Author extends User {
	
	public Author(Date birthDate, String email, String name, String password) {
		super(birthDate, email, name, password);
	}
	
}
