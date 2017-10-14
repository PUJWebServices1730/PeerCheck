/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Articles;
import facades.ArticlesFacade;
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
 * @author davlad
 */
@WebService(serviceName = "ArticlesService")
@Stateless()
public class ArticlesService {

	@EJB
	private ArticlesFacade ejbRef;// Add business logic below. (Right-click in editor and choose
	// "Insert Code > Add Web Service Operation")

	@WebMethod(operationName = "create")
    @Oneway
	public void create(@WebParam(name = "entity") Articles entity) {
		ejbRef.create(entity);
	}

	@WebMethod(operationName = "edit")
    @Oneway
	public void edit(@WebParam(name = "entity") Articles entity) {
		ejbRef.edit(entity);
	}

	@WebMethod(operationName = "remove")
    @Oneway
	public void remove(@WebParam(name = "entity") Articles entity) {
		ejbRef.remove(entity);
	}

	@WebMethod(operationName = "find")
	public Articles find(@WebParam(name = "id") Object id) {
		return ejbRef.find(id);
	}

	@WebMethod(operationName = "findAll")
	public List<Articles> findAll() {
		return ejbRef.findAll();
	}

	@WebMethod(operationName = "findRange")
	public List<Articles> findRange(@WebParam(name = "range") int[] range) {
		return ejbRef.findRange(range);
	}

	@WebMethod(operationName = "count")
	public int count() {
		return ejbRef.count();
	}

	@WebMethod(operationName = "create_1")
    @RequestWrapper(className = "create_1")
    @ResponseWrapper(className = "create_1Response")
	public List<String> create(@WebParam(name = "article") Articles article, @WebParam(name = "mainAuthorEmail") String mainAuthorEmail, @WebParam(name = "authorsEmails") List<String> authorsEmails, @WebParam(name = "eventsIds") List<Integer> eventsIds) {
		return ejbRef.create(article, mainAuthorEmail, authorsEmails, eventsIds);
	}
	
}
