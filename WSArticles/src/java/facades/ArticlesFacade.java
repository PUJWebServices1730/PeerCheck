/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entities.Articles;
import entities.Files;
import integration.users.UsersService_Service;
import java.util.Collection;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.xml.ws.WebServiceRef;

/**
 *
 * @author davlad
 */
@Stateless
public class ArticlesFacade extends AbstractFacade<Articles> {

	@WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/UsersService/UsersService.wsdl")
	private UsersService_Service service;

	@PersistenceContext(unitName = "WSArticlesPU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public ArticlesFacade() {
		super(Articles.class);
	}

	public void create(Articles article, List<String> emails) {
		article.setUsersCollection((Collection) findUsersByEmails(emails));
		//article.setFilesCollection1(new Collection<Files>());
		em.persist(article);
	}

	private java.util.List<integration.users.Users> findUsersByEmails(java.util.List<java.lang.String> emails) {
		// Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
		// If the calling of port operations may lead to race condition some synchronization is required.
		integration.users.UsersService port = service.getUsersServicePort();
		return port.findUsersByEmails(emails);
	}
	
}
