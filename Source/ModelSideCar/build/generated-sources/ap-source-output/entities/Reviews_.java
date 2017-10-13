package entities;

import entities.Articles;
import entities.Users;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-10-13T16:07:21")
@StaticMetamodel(Reviews.class)
public class Reviews_ { 

    public static volatile SingularAttribute<Reviews, Date> date;
    public static volatile SingularAttribute<Reviews, Users> reviewerId;
    public static volatile SingularAttribute<Reviews, Integer> grade;
    public static volatile SingularAttribute<Reviews, Articles> articleId;
    public static volatile SingularAttribute<Reviews, Integer> id;
    public static volatile SingularAttribute<Reviews, String> message;
    public static volatile SingularAttribute<Reviews, String> status;

}