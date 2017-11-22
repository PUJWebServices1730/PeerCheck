/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entities.Articles;
import entities.Users;
import java.util.List;
import javax.ejb.Remote;
import javax.persistence.EntityManager;

/**
 *
 * @author juanm
 */
@Remote
public interface ArticlesFacadeRemote {
    
    void create(Articles articles);

    void edit(Articles articles);

    void remove(Articles articles);

    Articles find(Object id);

    List<Articles> findAll();

    List<Articles> findRange(int[] range);

    int count();
    
    List<Articles> findByName(String name);
    
    List<Articles> findByCategory(String category);

    public List<Articles> getAllArticles();
    
    public List<Articles> findByAuthor(Users author);
}
