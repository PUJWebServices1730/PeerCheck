/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entities.Events;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author sebas
 */
@Stateless
public class EventsFacade extends AbstractFacade<Events> {

    @PersistenceContext(unitName = "WSEventsPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EventsFacade() {
        super(Events.class);
    }
    
    public List<Events> findEventsByIds(List<Integer> ids){
        List<Events> events = new ArrayList<Events>();
        for(int id : ids){
            events.add(super.find(id));
        }
        return events;
    }
}
