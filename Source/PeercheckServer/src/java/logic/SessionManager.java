/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import entities.Articles;
import entities.Reviews;
import entities.Users;
import enums.ArticleCriteria;
import enums.UserRole;
import facades.ArticlesFacadeRemote;
import facades.ReviewsFacadeRemote;
import facades.UsersFacade;
import facades.UsersFacadeRemote;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author juanm
 */
@Singleton
@Path("peercheck")
public class SessionManager implements SessionManagerRemote {

    @EJB
    private ReviewsFacadeRemote reviewsFacade;

    @EJB
    private ArticlesFacadeRemote articlesFacade;

    @EJB
    private final UsersFacadeRemote usersFacade;
    
    public SessionManager() {
        usersFacade = new UsersFacade();
    }
    
    @GET
    @Override
    @Path("login/{email}/{password}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Users login(@PathParam("email") String email, @PathParam("password") String password) {
        Users user = usersFacade.findByEmail(email);
        if(user != null) {
            if(user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    @PUT
    @Override
    @Path("signup")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Users signup(Users user) {
        Users other = usersFacade.findByEmail(user.getEmail());
        if(other == null) {
            usersFacade.create(user);
            return usersFacade.findByEmail(user.getEmail());
        }
        return null;
    }
    
    @Override
    public List<Users> getAllUsers(){
        return usersFacade.getAllUsers();
    }
    
    @Override
    public boolean changeRol(Users user, UserRole role) {
        user.setRole(role.toString());
        usersFacade.edit(user);
        return true;
    }

    @Override
    public List<Articles> findArticleBy(ArticleCriteria criteria, String value) {
        List<Articles> articles = null;
        if(criteria.equals(ArticleCriteria.NOMBRE)) {
            articles = articlesFacade.findByName(value);
        } else if(criteria.equals(ArticleCriteria.CATEGORIA)) {
            articles = articlesFacade.findByCategory(value);
        }
        return articles;
    }

    @Override
    public boolean addArticle(Articles article, List<String> emails) {
        List<Users> authors = findUsersByEmail(emails);
        article.setUsersList(authors);
        articlesFacade.create(article);
        return true;
    }

    @Override
    public boolean assignReviewerToArticle(Users user, Articles article) {
        return false;
    }

    @Override
    public boolean addReview(Reviews review) {
        reviewsFacade.create(review);
        return false;
    }

    @Override
    public double calculateFinalGradeToArticle(Articles article) {
        article = articlesFacade.find(article);
        List<Reviews> reviewsList = article.getReviewsList();
        double grade = 0.0;
        for (Reviews reviews : reviewsList) {
            grade += reviews.getGrade();
        }
        grade /= (double)reviewsList.size();
        return grade;
    }

    @Override
    public List<Users> findUsersByEmail(List<String> emails) {
        List<Users> users = new ArrayList<>();
        for(String email : emails){
            users.add(usersFacade.findByEmail(email));
        }
        return users;
    }
}
