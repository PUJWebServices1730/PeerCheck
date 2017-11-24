/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import entities.Articles;
import entities.Events;
import entities.Reviews;
import entities.TrannyFile;
import entities.Users;
import enums.ArticleCriteria;
import enums.UserRole;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import model.ArticleWithFile;

public class PeercheckRESTController {
    
	public static String SERVER_URL = "http://server.peercheck.com:8080/peercheck";
	public static WebTarget baseWebTarget;
	public static Client client;
	
	public static void init() {
		System.out.println(PeercheckRESTController.SERVER_URL);
		PeercheckRESTController.client = ClientBuilder.newClient();
		PeercheckRESTController.client.register(JacksonJaxbJsonProvider.class);
		PeercheckRESTController.baseWebTarget = PeercheckRESTController.client.target(PeercheckRESTController.SERVER_URL);
	}
    
    public static Users login(String email, String password) {
		// http://server.peercheck.com:8080/peercheck/login
		WebTarget webTarget = PeercheckRESTController.baseWebTarget.path("login");
		System.out.println("Requesting from server URI: " + webTarget.getUri());
		
		// Concatena servidor y el path al recurso
		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_XML);
		
		Users user = new Users();
		user.setEmail(email);
		user.setPassword(password);
		
		Response response = invocationBuilder.post(Entity.xml(user));
		user = response.readEntity(Users.class);
		System.out.println("Response from server code: " + response.getStatus() + " - " + response.getStatusInfo());
		user = null;
		if (response.getLength() > 0) {
			System.out.println("Media type: " + response.getMediaType().toString());
			user = response.readEntity(Users.class);
			System.out.println("Content: " + user);
		}
		return user;
    }

    public static Users signup(Users user) {
        // http://server.peercheck.com:8080/peercheck/signup
		WebTarget webTarget = PeercheckRESTController.baseWebTarget.path("signup");
		System.out.println("Requesting from server URI: " + webTarget.getUri());
		
		// Concatena servidor y el path al recurso
		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_XML);
		
		Response response = invocationBuilder.post(Entity.xml(user));
		user = response.readEntity(Users.class);
		System.out.println("Response from server code: " + response.getStatus() + " - " + response.getStatusInfo());
		user = null;
		if (response.getLength() > 0) {
			System.out.println("Media type: " + response.getMediaType().toString());
			user = response.readEntity(Users.class);
			System.out.println("Content: " + user);
		}
		return user;
    }

    public static List<Articles> findArticleBy(ArticleCriteria criteria, String value) {
        // http://server.peercheck.com:8080/peercheck/articles?criteria=CATEGORIA&query=Arquitectura
		WebTarget webTarget = PeercheckRESTController.baseWebTarget.path("articles");
		webTarget.queryParam("criteria", criteria.toString());
		webTarget.queryParam("query", value);
		System.out.println("Requesting from server URI: " + webTarget.getUri());
		
		// Concatena servidor y el path al recurso
		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_XML);
		
		Response response = invocationBuilder.get();
		System.out.println("Response from server code: " + response.getStatus() + " - " + response.getStatusInfo());
		
		List<Articles> articles = new ArrayList<>();
		if (response.getLength() > 0) {
			System.out.println("Media type: " + response.getMediaType().toString());
			articles = response.readEntity(new GenericType<List<Articles>>(){});
			System.out.println("Content: " + articles);
		}
		return articles;
    }

    public static void changeRol(Users user, UserRole role) {
        // http://server.peercheck.com:8080/peercheck/users/2?role=REVISOR
		WebTarget webTarget = PeercheckRESTController.baseWebTarget.path("articles/" + user.getId());
		webTarget.queryParam("role", role.toString());
		System.out.println("Requesting from server URI: " + webTarget.getUri());
		
		// Concatena servidor y el path al recurso
		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_XML);
		
		Response response = invocationBuilder.post(Entity.xml(null));
		System.out.println("Response from server code: " + response.getStatus() + " - " + response.getStatusInfo());
    }

    public static double calculateFinalGradeToArticle(Articles article) {
        // http://server.peercheck.com:8080/peercheck/articles/2/grade
		WebTarget webTarget = PeercheckRESTController.baseWebTarget.path("articles/" + article.getId() + "/grade");
		System.out.println("Requesting from server URI: " + webTarget.getUri());
		
		// Concatena servidor y el path al recurso
		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_XML);
		
		Response response = invocationBuilder.get();
		System.out.println("Response from server code: " + response.getStatus() + " - " + response.getStatusInfo());
		
		double res = -1;
		
		if (response.getLength() > 0) {
			System.out.println("Media type: " + response.getMediaType().toString());
			res = response.readEntity(Double.class);
			System.out.println("Content: " + res);
		}
		return res;
    }

    public static void addReview(Reviews review) {
        // http://server.peercheck.com:8080/peercheck/reviews
		WebTarget webTarget = PeercheckRESTController.baseWebTarget.path("reviews");
		System.out.println("Requesting from server URI: " + webTarget.getUri());
		
		// Concatena servidor y el path al recurso
		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_XML);
		
		Response response = invocationBuilder.put(Entity.xml(review));
		System.out.println("Response from server code: " + response.getStatus() + " - " + response.getStatusInfo());
    }
    
    public static List<Users> getAllUsers() {
        // http://server.peercheck.com:8080/peercheck/users
		WebTarget webTarget = PeercheckRESTController.baseWebTarget.path("users");
		System.out.println("Requesting from server URI: " + webTarget.getUri());
		
		// Concatena servidor y el path al recurso
		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_XML);
		
		Response response = invocationBuilder.get();
		System.out.println("Response from server code: " + response.getStatus() + " - " + response.getStatusInfo());
		
		List<Users> users = new ArrayList<>();
		if (response.getLength() > 0) {
			System.out.println("Media type: " + response.getMediaType().toString());
			users = response.readEntity(new GenericType<List<Users>>(){});
			System.out.println("Content: " + users);
		}
		return users;
    }

    public static List<Users> findUsersByEmail(List<String> emails) {
        // http://server.peercheck.com:8080/peercheck/users?emails={email1@mail.com,email2@mail.com,..}
		WebTarget webTarget = PeercheckRESTController.baseWebTarget.path("users");
		webTarget.queryParam("emails", emails.toArray());
		System.out.println("Requesting from server URI: " + webTarget.getUri());
		
		// Concatena servidor y el path al recurso
		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_XML);
		
		Response response = invocationBuilder.get();
		System.out.println("Response from server code: " + response.getStatus() + " - " + response.getStatusInfo());
		
		List<Users> users = new ArrayList<>();
		if (response.getLength() > 0) {
			System.out.println("Media type: " + response.getMediaType().toString());
			users = response.readEntity(new GenericType<List<Users>>(){});
			System.out.println("Content: " + users);
		}
		return users;
    }

    public static void addArticle(Articles article, TrannyFile file) {
        // http://server.peercheck.com:8080/peercheck/articles
		WebTarget webTarget = PeercheckRESTController.baseWebTarget.path("articles");
		System.out.println("Requesting from server URI: " + webTarget.getUri());
		
		// Concatena servidor y el path al recurso
		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_XML);
		
		ArticleWithFile articleWithFile = new ArticleWithFile(article, file);
		
		Response response = invocationBuilder.put(Entity.xml(articleWithFile));
		System.out.println("Response from server code: " + response.getStatus() + " - " + response.getStatusInfo());
    }

    public static TrannyFile getArticleFile(Articles article) {
        // http://server.peercheck.com:8080/peercheck/articles/2/file
		WebTarget webTarget = PeercheckRESTController.baseWebTarget.path("articles/" + article.getId() + "/file");
		System.out.println("Requesting from server URI: " + webTarget.getUri());
		
		// Concatena servidor y el path al recurso
		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_XML);
		
		Response response = invocationBuilder.get();
		System.out.println("Response from server code: " + response.getStatus() + " - " + response.getStatusInfo());
		
		TrannyFile file = null;
		
		if (response.getLength() > 0) {
			System.out.println("Media type: " + response.getMediaType().toString());
			file = response.readEntity(TrannyFile.class);
			System.out.println("Content: " + file.getName());
		}
		return file;
    }

    public static List<Articles> getAllArticles() {
        // http://server.peercheck.com:8080/peercheck/articles
		WebTarget webTarget = PeercheckRESTController.baseWebTarget.path("articles");
		System.out.println("Requesting from server URI: " + webTarget.getUri());
		
		// Concatena servidor y el path al recurso
		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_XML);
		
		Response response = invocationBuilder.get();
		System.out.println("Response from server code: " + response.getStatus() + " - " + response.getStatusInfo());
		
		List<Articles> articles = new ArrayList<>();
		if (response.getLength() > 0) {
			System.out.println("Media type: " + response.getMediaType().toString());
			articles = response.readEntity(new GenericType<List<Articles>>(){});
			System.out.println("Content: " + articles);
		}
		return articles;
    }

    public static List<Users> findUsersByRole(String role) {
        // http://server.peercheck.com:8080/peercheck/users/REVISOR
		WebTarget webTarget = PeercheckRESTController.baseWebTarget.path("users/" + role);
		System.out.println("Requesting from server URI: " + webTarget.getUri());
		
		// Concatena servidor y el path al recurso
		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_XML);
		
		Response response = invocationBuilder.get();
		System.out.println("Response from server code: " + response.getStatus() + " - " + response.getStatusInfo());
		
		List<Users> users = new ArrayList<>();
		if (response.getLength() > 0) {
			System.out.println("Media type: " + response.getMediaType().toString());
			users = response.readEntity(new GenericType<List<Users>>(){});
			System.out.println("Content: " + users);
		}
		return users;
    }

    public static List<Reviews> getReviewsByReviewer(Users reviewer) {
        // http://server.peercheck.com:8080/peercheck/users/reviewers/2/reviews
		WebTarget webTarget = PeercheckRESTController.baseWebTarget.path("users/reviewers/" + reviewer.getId() + "/reviews");
		System.out.println("Requesting from server URI: " + webTarget.getUri());
		
		// Concatena servidor y el path al recurso
		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_XML);
		
		Response response = invocationBuilder.get();
		System.out.println("Response from server code: " + response.getStatus() + " - " + response.getStatusInfo());
		
		List<Reviews> reviews = new ArrayList<>();
		if (response.getLength() > 0) {
			System.out.println("Media type: " + response.getMediaType().toString());
			reviews = response.readEntity(new GenericType<List<Reviews>>(){});
			System.out.println("Content: " + reviews);
		}
		return reviews;
    }

    public static void updateReview(Reviews review) {
        // http://server.peercheck.com:8080/peercheck/reviews/{review_id}
		WebTarget webTarget = PeercheckRESTController.baseWebTarget.path("reviews/" + review.getId());
		System.out.println("Requesting from server URI: " + webTarget.getUri());
		
		// Concatena servidor y el path al recurso
		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_XML);
		
		Response response = invocationBuilder.post(Entity.xml(review));
		System.out.println("Response from server code: " + response.getStatus() + " - " + response.getStatusInfo());
    }

    public static List<Articles> getArticlesByAuthor(Users author) {
        // http://server.peercheck.com:8080/peercheck/users/authors/2/articles
		WebTarget webTarget = PeercheckRESTController.baseWebTarget.path("users/authors/" + author.getId() + "/articles");
		System.out.println("Requesting from server URI: " + webTarget.getUri());
		
		// Concatena servidor y el path al recurso
		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_XML);
		
		Response response = invocationBuilder.get();
		System.out.println("Response from server code: " + response.getStatus() + " - " + response.getStatusInfo());
		
		List<Articles> articles = new ArrayList<>();
		if (response.getLength() > 0) {
			System.out.println("Media type: " + response.getMediaType().toString());
			articles = response.readEntity(new GenericType<List<Articles>>(){});
			System.out.println("Content: " + articles);
		}
		return articles;
    }

    public static List<Reviews> getReviewsByArticle(Articles article) {
        // http://server.peercheck.com:8080/peercheck/articles/2/reviews
		WebTarget webTarget = PeercheckRESTController.baseWebTarget.path("articles/" + article.getId() + "/reviews");
		System.out.println("Requesting from server URI: " + webTarget.getUri());
		
		// Concatena servidor y el path al recurso
		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_XML);
		
		Response response = invocationBuilder.get();
		System.out.println("Response from server code: " + response.getStatus() + " - " + response.getStatusInfo());
		
		List<Reviews> reviews = new ArrayList<>();
		if (response.getLength() > 0) {
			System.out.println("Media type: " + response.getMediaType().toString());
			reviews = response.readEntity(new GenericType<List<Reviews>>(){});
			System.out.println("Content: " + reviews);
		}
		return reviews;
    }

    public static void addEvent(Events event) {
        // http://server.peercheck.com:8080/peercheck/events
		WebTarget webTarget = PeercheckRESTController.baseWebTarget.path("events");
		System.out.println("Requesting from server URI: " + webTarget.getUri());
		
		// Concatena servidor y el path al recurso
		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_XML);
		
		Response response = invocationBuilder.put(Entity.xml(event));
		System.out.println("Response from server code: " + response.getStatus() + " - " + response.getStatusInfo());
    }

    public static List<Events> getAllEvents() {
        // http://server.peercheck.com:8080/peercheck/events
		WebTarget webTarget = PeercheckRESTController.baseWebTarget.path("events");
		System.out.println("Requesting from server URI: " + webTarget.getUri());
		
		// Concatena servidor y el path al recurso
		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_XML);
		
		Response response = invocationBuilder.get();
		System.out.println("Response from server code: " + response.getStatus() + " - " + response.getStatusInfo());
		
		List<Events> events = new ArrayList<>();
		if (response.getLength() > 0) {
			System.out.println("Media type: " + response.getMediaType().toString());
			events = response.readEntity(new GenericType<List<Events>>(){});
			System.out.println("Content: " + events);
		}
		return events;
    }
}
