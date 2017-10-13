/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Users;
import facades.UsersFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.jws.WebService;
import javax.ejb.Stateless;
import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author davlad
 */
@WebService(serviceName = "UsersService")
@Stateless()
public class UsersService {

	@EJB
	private UsersFacade ejbRef;// Add business logic below. (Right-click in editor and choose
	// "Insert Code > Add Web Service Operation")

	@WebMethod(operationName = "create")
    @Oneway
	public void create(@WebParam(name = "entity") Users entity) {
		ejbRef.create(entity);
	}

	@WebMethod(operationName = "edit")
    @Oneway
	public void edit(@WebParam(name = "entity") Users entity) {
		ejbRef.edit(entity);
	}

	@WebMethod(operationName = "remove")
    @Oneway
	public void remove(@WebParam(name = "entity") Users entity) {
		ejbRef.remove(entity);
	}

	@WebMethod(operationName = "find")
	public Users find(@WebParam(name = "id") Object id) {
		return ejbRef.find(id);
	}

	@WebMethod(operationName = "findAll")
	public List<Users> findAll() {
		return ejbRef.findAll();
	}

	@WebMethod(operationName = "findRange")
	public List<Users> findRange(@WebParam(name = "range") int[] range) {
		return ejbRef.findRange(range);
	}

	@WebMethod(operationName = "count")
	public int count() {
		return ejbRef.count();
	}

	/**
	 * Web service operation
	 */
	@WebMethod(operationName = "editAuthorRole")
	public Users editAuthorRole(@WebParam(name = "id") Object id) {
		return ejbRef.editAuthorRole(id);
	}
	
	/**
	 * Web service operation
	 */
	@WebMethod(operationName = "findUsersByEmails")
	public List<Users> findUsersByEmails(@WebParam(name = "emails") List<String> emails) {
		return ejbRef.findByEmail(emails);
	}
	
	
}
