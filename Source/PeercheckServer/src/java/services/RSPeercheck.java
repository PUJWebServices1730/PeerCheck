/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Events;
import entities.Reviews;
import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import logic.SessionManagerRemote;
import model.ArticleWithFile;

/**
 *
 * @author davlad
 */
@Path("Peercheck")
public class RSPeercheck {
	@EJB
    private SessionManagerRemote ejbRef;
	
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
	
	@PUT
	@Path("articles")
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public void addArticle(ArticleWithFile article) {
		ejbRef.addArticle(article.getArticle(), article.getFile());
	}
	
	@PUT
	@Path("articles/{article_id}/reviewers/{reviewer_id}")
	public void addReviewerToArticle(@PathParam("article_id") long articleId, @PathParam("reviewer_id") long reviewerId) {
		ejbRef.addReviewerToArticle(articleId, reviewerId);
	}
	
	@POST
	@Path("articles/{article_id}/reviews/{review_id}")
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public void updateReview(@PathParam("article_id") long articleId, @PathParam("review_id") int reviewId, Reviews review) {
		ejbRef.updateReviewAtArticle(articleId, reviewId, review);
	}
}
