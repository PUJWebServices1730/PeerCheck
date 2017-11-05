/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entities.Articles;
import entities.Events;
import entities.Reviews;
import entities.Users;
import integration.events.EventsService_Service;
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
import javax.jws.WebParam;
import mappers.UsersMapper;
import javax.persistence.Query;
import mappers.EventsMapper;

/**
 *
 * @author davlad
 */
@Stateless
public class ArticlesFacade extends AbstractFacade<Articles> {

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/EventsService/EventsService.wsdl")
    private EventsService_Service service_1;

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
	 * @param authorsEmails
         * @param ids
	 * @return una lista con los correos que no se encontraron, o null si el mainAuthor o alguno de los eventsIds no se encontró.
	 */
	public List<String> create(Articles article, List<String> authorsEmails, List<Integer> ids) {
		// Obtener usuarios a partir de los emails
		List<integration.users.Users> usersDtos = findUsersByEmails(authorsEmails);
		List<integration.events.Events> eventsDtos = findEventsById(ids);
                
                List<Users> users = new ArrayList<>(usersDtos.size());
                List<Events> events = new ArrayList<>(eventsDtos.size());
		
                
		for (int i = 0; i < usersDtos.size(); i++) {
			integration.users.Users uDto = usersDtos.get(i);
			users.add(UsersMapper.INSTANCE.usersDtoToUsers(uDto));
		}
                for (int i = 0; i < eventsDtos.size(); i++) {
			integration.events.Events eDto = eventsDtos.get(i);
			events.add(EventsMapper.INSTANCE.eventsDtoToEvents(eDto));
		}
                
		// Obtener emails que no se encontraron
		List<String> missing = new ArrayList<>();
		for (int i = users.size() - 1; i >= 0; --i) {
			if (users.get(i) == null) {
				missing.add(authorsEmails.get(i));
				users.remove(i);
			}
		}
		
                for (Users u : users) {
                    if(u.getArticlesList() == null){
                        u.setArticlesList(new ArrayList<>());
                    }
                    u.getArticlesList().add(article);
                    em.merge(u);
		}
                
                for (Events e : events) {
                    if(e.getArticlesList() == null){
                        e.setArticlesList(new ArrayList<Articles>());
                    }
                    e.getArticlesList().add(article);
                    em.merge(e);
		}
                
		// Agregar evento al artículo
		article.setEventsList(events);
		
		// Agregar autores al artículo
		article.setUsersList(users);
                
                // Agregar artículo a la base de datos
		em.persist(article);
                
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
	
	public Float calculateAverage(@WebParam(name = "id") int id) {
            float sum = 0;
            Articles article = find(id);
            List<Reviews> reviewsList = article.getReviewsList();
            for(Reviews review : reviewsList) {
                sum += review.getGrade();
            }
            return sum / reviewsList.size();
        }
				
	public List<Articles> search(String type, String param){
            List<Articles> articles;
            String queryName = "";
            Query q;
            if(type.equals("title")){
                queryName = "Articles.findInTitle";
            } else if(type.equals("category")){
                queryName = "Articles.findInCategory";
            }
            q = em.createNamedQuery(queryName, Articles.class);
            articles = q.setParameter("param", param).getResultList();
            return articles;
        }

    private java.util.List<integration.events.Events> findEventsById(java.util.List<java.lang.Integer> ids) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        integration.events.EventsService port = service_1.getEventsServicePort();
        return port.findEventsById(ids);
    }
}
