/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Events;
import facades.EventsFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.jws.WebService;
import javax.ejb.Stateless;
import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author sebas
 */
@WebService(serviceName = "EventsService")
@Stateless()
public class EventsService {

    @EJB
    private EventsFacade ejbRef;// Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Web Service Operation")

    @WebMethod(operationName = "create")
    @Oneway
    public void create(@WebParam(name = "entity") Events entity) {
        ejbRef.create(entity);
    }

    @WebMethod(operationName = "find")
    public Events find(@WebParam(name = "id") Object id) {
        return ejbRef.find(id);
    }

    @WebMethod(operationName = "findAll")
    public List<Events> findAll() {
        return ejbRef.findAll();
    }
    
    @WebMethod(operationName = "findEventsById")
    public List<Events> findEventsById(@WebParam(name="ids") List<Integer> ids) {
        return ejbRef.findEventsByIds(ids);
    }
}
