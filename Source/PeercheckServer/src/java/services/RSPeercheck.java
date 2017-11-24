/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Articles;
import entities.Events;
import entities.Reviews;
import entities.TrannyFile;
import entities.Users;
import enums.ArticleCriteria;
import enums.UserRole;
import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import logic.SessionManagerRemote;
import model.ArticleWithFile;

/**
 *
 * @author davlad
 */
@Path("peercheck")
public class RSPeercheck {
	@EJB
    private SessionManagerRemote ejbRef;
	
	@POST
    @Path("login")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Users login(Users user) {
		return ejbRef.login(user.getEmail(), user.getPassword());
	}
	
	@PUT
    @Path("signup")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Users signup(Users user) {
		return ejbRef.signup(user);
	}
	
	@GET
    @Path("users")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Users> getAllUsers(@QueryParam("emails") List<String> emails) {
		if (emails == null || emails.isEmpty()) {
			return ejbRef.getAllUsers();
		}
		return ejbRef.findUsersByEmail(emails);
	}
	
	@POST
    @Path("users/{user_id}")
    public void changeRol(@PathParam("user_id") int userId, @QueryParam("role") String role) {
		ejbRef.changeRol(userId, role);
	}
	
	@GET
    @Path("users/{role}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public List<Users> getAllReviewers(@PathParam("role") String role) {
		UserRole userRole;
		try {
			userRole = UserRole.valueOf(role);
		} catch (IllegalArgumentException | NullPointerException ex) {
			return ejbRef.getAllUsers();
		}
		return ejbRef.findUsersByRole(userRole.toString());
	}
	
	@GET
    @Path("users/reviewers/{id}/reviews")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public List<Reviews> findReviewsByReviewer(@PathParam("id") int id) {
		return ejbRef.findReviewsByReviewer(id);
	}
	
	@GET
	@Path("users/authors/{id}/articles")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public List<Articles> findArticlesByAuthor(@PathParam("id") int id) {
		Users author = ejbRef.getUser(id);
		if (author == null) {
			return null;
		}
		return ejbRef.getArticlesByAuthor(author);
	}
	
	@PUT
	@Path("articles")
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public void addArticle(ArticleWithFile article) {
		ejbRef.addArticle(article.getArticle(), article.getFile());
	}
	
	@GET
	@Path("articles")
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public List<Articles> findArticlesBy(@QueryParam("criteria") String criteria, @QueryParam("query") String query) {
		if (criteria == null || criteria.equals("")) {
			return ejbRef.getAllArticles();
		}
		if (query == null || query.equals("")) {
			return ejbRef.getAllArticles();
		}
		ArticleCriteria articleCriteria;
		try {
			articleCriteria = ArticleCriteria.valueOf(criteria);
		} catch (IllegalArgumentException | NullPointerException ex) {
			return ejbRef.getAllArticles();
		}
		return ejbRef.findArticleBy(articleCriteria, query);
	}
	
	@GET
	@Path("articles/{id}/grade")
	@Produces({MediaType.TEXT_PLAIN})
	public Double calculateFinalGradeToArticle(@PathParam("id") int id) {
		Articles article = ejbRef.getArticle(id);
		Double res = -1d;
		if (article == null) {
			return res;
		}
		res = ejbRef.calculateFinalGradeToArticle(article);
		return res;
	}
	
	@GET
	@Path("articles/{id}/file")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public TrannyFile getArticleFile(@PathParam("id") int id) {
		Articles article = ejbRef.getArticle(id);
		if (article == null) {
			return null;
		}
		return ejbRef.getArticleFile(article);
	}
	
	@GET
	@Path("articles/{id}/reviews")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public List<Reviews> findReviewsByArticle(@PathParam("id") int id) {
		Articles article = ejbRef.getArticle(id);
		if (article == null) {
			return null;
		}
		return ejbRef.getReviewsByArticle(article);
	}
	
	@PUT
	@Path("articles/{article_id}/reviewers/{reviewer_id}")
	public void addReviewerToArticle(@PathParam("article_id") long articleId, @PathParam("reviewer_id") long reviewerId) {
		ejbRef.addReviewerToArticle(articleId, reviewerId);
	}
	
	@PUT
	@Path("articles/{article_id}/reviews")
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public void addReview(@PathParam("article_id") int articleId, Reviews review) {
		Articles article = ejbRef.getArticle(articleId);
		if (article == null) {
			return;
		}
		review.setArticleId(article);
		ejbRef.addReview(review);
	}
	
	@POST
	@Path("reviews/{review_id}")
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public void updateReview(@PathParam("review_id") int reviewId, Reviews review) {
		review.setId(reviewId);
		ejbRef.updateReview(review);
	}
	
	@PUT
	@Path("reviews")
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public void addReview(Reviews review) {
		ejbRef.updateReview(review);
	}
	
	@PUT
	@Path("events")
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public void addEvent(Events event) {
		ejbRef.addEvent(event);
	}
	
	@GET
	@Path("events")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public List<Events> getAllEvents() {
		return ejbRef.getAllEvents();
	}
	
	@GET
	@Path("events/{event_id}/articles")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public List<Articles> findArticlesInEventByTitle(@PathParam("event_id") int eventId, @QueryParam("name") String title) {
		if (title == null || title.equals("")) {
			return ejbRef.getAllArticlesInEvent(eventId);
		}
		return ejbRef.findArticlesInEventByTitle(eventId, title);
	}
}
