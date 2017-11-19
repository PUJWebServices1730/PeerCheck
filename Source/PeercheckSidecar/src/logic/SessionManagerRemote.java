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
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author juanm
 */
@Remote
public interface SessionManagerRemote {

    Users login(String email, String password);

    Users signup(Users user);

    boolean changeRol(Users user, UserRole role);

    List<Articles> findArticleBy(ArticleCriteria criteria, String value);

    boolean addArticle(Articles article, List<String> emails);

    boolean assignReviewerToArticle(Users user, Articles article);

    boolean addReview(Reviews review);

    double calculateFinalGradeToArticle(Articles article);

    public List<Users> getAllUsers();
    
    public List<Users> findUsersByEmail(List<String> emails);
    
}
