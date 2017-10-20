/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entities.Articles;
import entities.Events;
import entities.Users;
import integration.users.UsersService_Service;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.xml.ws.WebServiceRef;
import javax.xml.namespace.QName;
import javax.xml.transform.Source;
import javax.xml.ws.Dispatch;
import javax.xml.transform.stream.StreamSource;
import javax.xml.ws.Service;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import mappers.UsersMapper;

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
	
	/**
	 * Crea una nuevo artículo en la base de datos con los autores con los correos dados, y los eventos con los ids dados.
	 * @param article
	 * @param mainAuthorEmail
	 * @param authorsEmails
	 * @param eventsIds
	 * @return una lista con los correos que no se encontraron, o null si el mainAuthor o alguno de los eventsIds no se encontró.
	 */
	public List<String> create(Articles article, List<String> authorsEmails, List<Events> events) {
		// Obtener usuarios a partir de los emails
		List<integration.users.Users> usersDtos = findUsersByEmails(authorsEmails);
		List<Users> users = new ArrayList<>(usersDtos.size());
		
                
		for (int i = 0; i < usersDtos.size(); i++) {
			integration.users.Users uDto = usersDtos.get(i);
			users.set(i, UsersMapper.INSTANCE.usersDtoToUsers(uDto));
		}
		
		// Obtener emails que no se encontraron
		List<String> missing = new ArrayList<>();
		for (int i = users.size() - 1; i >= 0; --i) {
			if (users.get(i) == null) {
				missing.add(authorsEmails.get(i));
				users.remove(i);
			}
		}
		
		// Agregar artículo a la base de datos
		em.persist(article);
		
		// Agregar evento al artículo
		article.getEventsList().addAll(events);
		
		// Agregar autores al artículo
		article.setUsersList(users);
		
		// Agregar el artículo a sus autores
		//user.getArticlesList1().add(article);
		for (Users u : users) {
			u.getArticlesList().add(article);
		}
		
		return missing;
	}

	private java.util.List<integration.users.Users> findUsersByEmails(java.util.List<java.lang.String> emails) {
		// Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
		// If the calling of port operations may lead to race condition some synchronization is required.
		integration.users.UsersService port = service.getUsersServicePort();
		return port.findUsersByEmails(emails);
	}

	private integration.users.Users findUserByEmail(java.lang.String email) {
		// Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
		// If the calling of port operations may lead to race condition some synchronization is required.
		integration.users.UsersService port = service.getUsersServicePort();
		return port.findUserByEmail(email);
	}
	
	
}
