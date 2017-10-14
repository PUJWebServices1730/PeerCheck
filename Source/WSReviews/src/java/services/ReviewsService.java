/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Articles;
import entities.Reviews;
import entities.Users;
import facades.ReviewsFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.jws.WebService;
import javax.ejb.Stateless;
import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 *
 * @author sebas
 */
@WebService(serviceName = "ReviewsService")
@Stateless()
public class ReviewsService {

    @EJB
    private ReviewsFacade ejbRef;// Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Web Service Operation")

    
    @WebMethod(operationName = "create")
    @Oneway
    public void create(@WebParam(name = "entity") Reviews entity) {
        ejbRef.create(entity);
    }
    
    @WebMethod(operationName = "edit")
    @Oneway
    public void edit(@WebParam(name = "entity") Reviews entity) {
        ejbRef.edit(entity);
    }

    @WebMethod(operationName = "remove")
    @Oneway
    public void remove(@WebParam(name = "entity") Reviews entity) {
        ejbRef.remove(entity);
    }

    @WebMethod(operationName = "find")
    public Reviews find(@WebParam(name = "id") Object id) {
        return ejbRef.find(id);
    }

    @WebMethod(operationName = "findAll")
    public List<Reviews> findAll() {
        return ejbRef.findAll();
    }

    @WebMethod(operationName = "count")
    public int count() {
        return ejbRef.count();
    }
    
    /**
     * Crear una revisión (caso de uso Asignar Revisor)
     * @param entity
     * @param reviewer
     * @param article 
     */
    @WebMethod(operationName = "create_review")
    @RequestWrapper(className = "create_review")
    @Oneway
    public void create(@WebParam(name = "review") Reviews review, @WebParam(name = "reviewer") Users reviewer, @WebParam(name = "article") Articles article) {
        ejbRef.create(review, reviewer, article);
    }
    
    /**
     * Editar una revisión (caso de uso Enviar Evaluación)
     * @param id
     * @param grade
     * @param message 
     */
    @WebMethod(operationName = "edit_review")
    @RequestWrapper(className = "edit_review")
    @ResponseWrapper(className = "edit_reviewResponse")
    public Reviews edit(@WebParam(name = "id") Object id, @WebParam(name = "grade") int grade, @WebParam(name = "message") String message) {
        return ejbRef.edit(id, grade, message);
    }
    
}
