/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entities.Articles;
import entities.Reviews;
import entities.Users;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author juanm
 */
@Stateless
public class ReviewsFacade extends AbstractFacade<Reviews> implements ReviewsFacadeRemote {

    @PersistenceContext(unitName = "PeercheckServerPU")
    private EntityManager em;

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    public ReviewsFacade() {
        super(Reviews.class);
    }

    @Override
    public List<Reviews> findByReviewerId(Users reviewer) {
        Query query = em.createNamedQuery("Reviews.findByReviewer", Reviews.class);
        List<Reviews> reviews = (List<Reviews>) query.setParameter("reviewerId", reviewer).setParameter("status", "ASIGNADA").getResultList();
        return reviews;
    }

    @Override
    public List<Reviews> findByArticleId(Articles article) {
        Query query = em.createNamedQuery("Reviews.findByArticle", Articles.class);
        List<Reviews> reviews = (List<Reviews>) query.setParameter("articleId", article).setParameter("status", "COMPLETADA").getResultList();
        return reviews;
    }
}
