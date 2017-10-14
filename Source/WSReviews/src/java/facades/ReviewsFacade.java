/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entities.Articles;
import entities.Reviews;
import entities.Users;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author sebas
 */
@Stateless
public class ReviewsFacade extends AbstractFacade<Reviews> {

    @PersistenceContext(unitName = "WSReviewsPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ReviewsFacade() {
        super(Reviews.class);
    }
    
    /**
     * Crear una revisión (caso de uso Asignar Revisor a Artículo)
     * Se inicializa con una calificaión de 0 y un estado "Asignado"
     * @param review
     * @param reviewer
     * @param article 
     */
    public void create(Reviews review, Users reviewer, Articles article){
        review.setGrade(0);
        review.setStatus("Assigned");
        review.setReviewerId(reviewer);
        review.setArticleId(article);
        em.persist(review);
    }
    
    /**
     * Editar una revisión (caso de uso Enviar Evaluación de Artículo)
     * Actualizar la calificaión, el mensaje y el estado de la revisión
     * @param id
     * @param grade
     * @param message
     * @return 
     */
    public Reviews edit(Object id, int grade, String message) {
        Reviews review = super.find(id);
        review.setStatus("Completed");
        review.setGrade(grade);
        review.setMessage(message);
        super.edit(review);
        return review;
    }
}
