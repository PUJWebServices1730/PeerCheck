/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entities.Users;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author davlad
 */
@Stateless
public class UsersFacade extends AbstractFacade<Users> {

	@PersistenceContext(unitName = "WSUsersPU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public UsersFacade() {
		super(Users.class);
	}
	
	/**
	 * Convierte y guarda en la base de datos al usuario con el id dado al rol Reviewer.
	 * @param id
	 * @return el usuario modificado.
	 */
	public Users convertToReviewer(Object id) {
		Users user = super.find(id);
		user.setRole("Reviewer");
		super.edit(user);
		return user;
	}
	
	/**
	 * Encuentra al usuario con el correo dado en la base de datos.
	 * @param email
	 * @return el usuario encontrado.
	 */
	public Users findByEmail(String email) {
		Query q = em.createNamedQuery("Users.findByEmail", Users.class);
		try {
			return (Users) q.setParameter("email", email).getSingleResult();
		} catch(Exception e) {
			return null;
		}
	}
	
	/**
	 * Encuentra a los usuarios con los correos dados en la base de datos.
	 * @param emails
	 * @return los usuarios encontrados.
	 */
	public List<Users> findAllByEmail(List<String> emails) {
		List<Users> users = new ArrayList<>(emails.size());
		for (int i = 0; i < emails.size(); i++) {
			String email = emails.get(i);
			users.set(i, findByEmail(email));
		}
		return users;
	}
}
