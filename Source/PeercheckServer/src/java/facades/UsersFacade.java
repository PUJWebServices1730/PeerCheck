/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entities.Users;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.Path;

/**
 *
 * @author juanm
 */
@Stateless
public class UsersFacade extends AbstractFacade<Users> implements UsersFacadeRemote {

    @PersistenceContext(unitName = "PeercheckServerPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsersFacade() {
        super(Users.class);
    }

    @Override
    public Users findByEmail(String email) {
        Query query = em.createNamedQuery("Users.findByEmail", Users.class);
        try {
            return (Users)query.setParameter("email", email).getSingleResult();
        } catch(Exception e) {
            return null;
        }
    }

    @Override
    public List<Users> getAllUsers() {
        Query query = em.createNamedQuery("Users.findAll", Users.class);
        try {
            return (List<Users>)query.getResultList();
        } catch(Exception e) {
            return null;
        }
    }
}
