/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entities.Users;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author juanm
 */
@Remote
public interface UsersFacadeRemote {

    void create(Users users);

    void edit(Users users);

    void remove(Users users);

    Users find(Object id);

    List<Users> findAll();

    List<Users> findRange(int[] range);

    int count();

    public Users findByEmail(String email);
    
    public List<Users> getAllUsers();

    public List<Users> findByRole(String role);
    
}
