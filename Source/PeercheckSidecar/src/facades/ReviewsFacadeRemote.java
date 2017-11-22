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
import javax.ejb.Remote;

/**
 *
 * @author juanm
 */
@Remote
public interface ReviewsFacadeRemote {

    void create(Reviews reviews);

    void edit(Reviews reviews);

    void remove(Reviews reviews);

    Reviews find(Object id);

    List<Reviews> findAll();

    List<Reviews> findRange(int[] range);

    int count();
    
    public List<Reviews> findByReviewerId(Users reviewer);
    
    public List<Reviews> findByArticleId(Articles article);
    
}
