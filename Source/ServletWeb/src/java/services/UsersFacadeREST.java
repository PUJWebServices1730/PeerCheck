/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Users;
import integration.authentication.AuthenticationService_Service;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.xml.ws.WebServiceRef;
import mappers.DTOMapper;

/**
 *
 * @author davlad
 */
@Stateless
@Transactional
@Path("entities.users")
public class UsersFacadeREST extends AbstractFacade<Users> {

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/AuthenticationService/AuthenticationService.wsdl")
    private AuthenticationService_Service service;

    @PersistenceContext(unitName = "ServletWebPU")
    private EntityManager em;

    public UsersFacadeREST() {
        super(Users.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Users entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Users entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Users find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Users> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Users> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }
    
    @GET
    @Path("login")
    @Produces(MediaType.APPLICATION_JSON)
    public Users exists(@QueryParam("email") String email, @QueryParam("password") String password) {
        integration.authentication.Users iauser = authenticate(email, password);
        Users user = DTOMapper.INSTANCE.usersDtoToUsers(iauser);
        return user;
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    private integration.authentication.Users authenticate(java.lang.String email, java.lang.String password) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        AuthenticationService_Service service_s = new integration.authentication.AuthenticationService_Service();
        integration.authentication.AuthenticationService port = service_s.getAuthenticationServicePort();
        return port.authenticate(email, password);
    }

    
    
    

}
