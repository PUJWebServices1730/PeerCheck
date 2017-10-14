/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import facades.AuthenticationFacade;
import integration.users.Users;
import integration.users.UsersService_Service;
import javax.ejb.EJB;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.ejb.Stateless;
import javax.xml.ws.WebServiceRef;

/**
 *
 * @author Sebas
 */
@WebService(serviceName = "AuthenticationService")
@Stateless()
public class AuthenticationService {
    
    @EJB
    private AuthenticationFacade ejbRef;
    
    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/UsersService/UsersService.wsdl")
    private UsersService_Service service;
    
    @WebMethod(operationName = "authenticate")
    public Users authenticate(@WebParam(name = "email") String email, @WebParam(name = "password") String password) {
        integration.users.UsersService port = service.getUsersServicePort();
        Users user = port.findUserByEmail(email);
        if(ejbRef.authenticate(user, password)){
            return user;
        }
        return null;
    }
    
    
}
