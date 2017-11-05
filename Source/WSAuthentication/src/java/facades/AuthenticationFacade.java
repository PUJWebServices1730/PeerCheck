/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import integration.users.Users;
import javax.ejb.Stateless;

/**
 *
 * @author Sebas
 */
@Stateless
public class AuthenticationFacade {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public boolean authenticate(Users user, java.lang.Object password){
        return user.getPassword().equals(password);
    }
}
