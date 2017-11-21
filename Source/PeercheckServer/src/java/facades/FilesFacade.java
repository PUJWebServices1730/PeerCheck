/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entities.Articles;
import entities.Files;
import entities.TrannyFile;
import java.io.File;
import java.nio.file.Paths;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.Path;

/**
 *
 * @author juanm
 */
@Stateless
public class FilesFacade extends AbstractFacade<Files> implements FilesFacadeRemote {

    @PersistenceContext(unitName = "PeercheckServerPU")
    private EntityManager em;

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    public FilesFacade() {
        super(Files.class);
    }

    @Override
    public TrannyFile findByArticleId(Articles article) {
        Query query = em.createNamedQuery("Files.findByArticleId", Files.class);
        try {
            Files file = (Files) query.setParameter("articleId", article).getSingleResult();
            byte[] array = java.nio.file.Files.readAllBytes(new File(file.getUrl()).toPath());            
            TrannyFile trannyFile = new TrannyFile(file.getTitle(), array);
            return trannyFile;
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
