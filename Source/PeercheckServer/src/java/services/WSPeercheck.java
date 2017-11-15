/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Articles;
import entities.Reviews;
import entities.Users;
import enums.ArticleCriteria;
import enums.UserRole;
import java.util.List;
import javax.ejb.EJB;
import javax.jws.WebService;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import logic.SessionManagerRemote;

/**
 *
 * @author juanm
 */
@WebService(serviceName = "WSPeercheck")
@Stateless()
public class WSPeercheck {

    @EJB
    private SessionManagerRemote ejbRef;// Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Web Service Operation")

    @WebMethod(operationName = "login")
    public Users login(@WebParam(name = "email") String email, @WebParam(name = "password") String password) {
        return ejbRef.login(email, password);
    }

    @WebMethod(operationName = "signup")
    public Users signup(@WebParam(name = "user") Users user) {
        return ejbRef.signup(user);
    }
    
    @WebMethod
    public List<Users> getAllUsers() {
        return ejbRef.getAllUsers();
    }
    
    @WebMethod(operationName = "changeRol")
    public boolean changeRol(@WebParam(name = "user") Users user, @WebParam(name = "role") UserRole role) {
        return ejbRef.changeRol(user, role);
    }

    @WebMethod(operationName = "findArticleBy")
    public List<Articles> findArticleBy(@WebParam(name = "criteria") ArticleCriteria criteria, @WebParam(name = "value") String value) {
        return ejbRef.findArticleBy(criteria, value);
    }

    @WebMethod(operationName = "addArticle")
    public boolean addArticle(@WebParam(name = "article") Articles article) {
        return ejbRef.addArticle(article);
    }

    @WebMethod(operationName = "assignReviewerToArticle")
    public boolean assignReviewerToArticle(@WebParam(name = "user") Users user, @WebParam(name = "article") Articles article) {
        return ejbRef.assignReviewerToArticle(user, article);
    }

    @WebMethod(operationName = "addReview")
    public boolean addReview(@WebParam(name = "review") Reviews review) {
        return ejbRef.addReview(review);
    }

    @WebMethod(operationName = "calculateFinalGradeToArticle")
    public double calculateFinalGradeToArticle(@WebParam(name = "article") Articles article) {
        return ejbRef.calculateFinalGradeToArticle(article);
    }
    
}
