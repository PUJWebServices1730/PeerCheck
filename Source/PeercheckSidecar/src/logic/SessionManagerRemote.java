/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import entities.Articles;
import entities.Events;
import entities.Reviews;
import entities.TrannyFile;
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
    
    List<Articles> getAllArticles();

    boolean addArticle(Articles article, TrannyFile file);

    boolean addReview(Reviews review);

    double calculateFinalGradeToArticle(Articles article);

    public List<Users> getAllUsers();
    
    public List<Users> findUsersByEmail(List<String> emails);
    
    public List<Users> findUsersByRole(String role);
    
    public TrannyFile getArticleFile(Articles article);
    
    public List<Reviews> getReviewsByReviewer(Users reviewer);

    public boolean updateReview(Reviews review);

    public List<Articles> getArticlesByAuthor(Users author);

    public List<Reviews> getReviewsByArticle(Articles article);
    
    boolean addEvent(Events event);

    public List<Events> getAllEvents();

	public void addReviewerToArticle(long id_reviewer, long id_article);

	public void updateReviewAtArticle(long articleId, int reviewId, Reviews review);
}
