/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entities.Events;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author juanm
 */
@Remote
public interface EventsFacadeRemote {

    void create(Events events);

    void edit(Events events);

    void remove(Events events);

    Events find(Object id);

    List<Events> findAll();

    List<Events> findRange(int[] range);

    int count();
    
}
