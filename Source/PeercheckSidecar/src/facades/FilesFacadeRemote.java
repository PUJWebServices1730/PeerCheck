/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entities.Articles;
import entities.Files;
import entities.TrannyFile;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author juanm
 */
@Remote
public interface FilesFacadeRemote {

    void create(Files files);

    void edit(Files files);

    void remove(Files files);

    Files find(Object id);

    List<Files> findAll();

    List<Files> findRange(int[] range);

    int count();

    public TrannyFile findByArticleId(Articles article);
    
}
