/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entities.Files;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
    protected EntityManager getEntityManager() {
        return em;
    }

    public FilesFacade() {
        super(Files.class);
    }
    
}
