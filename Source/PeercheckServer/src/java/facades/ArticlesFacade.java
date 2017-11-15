/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entities.Articles;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.Path;

@Stateless
public class ArticlesFacade extends AbstractFacade<Articles> implements ArticlesFacadeRemote {

    @PersistenceContext(unitName = "PeercheckServerPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ArticlesFacade() {
        super(Articles.class);
    }

    @Override
    public List<Articles> findByName(String name) {
        Query query = em.createNamedQuery("Articles.findByTitle", Articles.class);
        try {
            return (List<Articles>)query.setParameter("title", name).getResultList();
        } catch(Exception e) {
            return null;
        }
    }

    @Override
    public List<Articles> findByCategory(String category) {
        Query query = em.createNamedQuery("Articles.findByCategory", Articles.class);
        try {
            return (List<Articles>)query.setParameter("category", category).getResultList();
        } catch(Exception e) {
            return null;
        }
    }
    
}