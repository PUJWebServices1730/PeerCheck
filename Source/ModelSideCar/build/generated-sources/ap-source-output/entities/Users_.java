package entities;

import entities.Articles;
import entities.Events;
import entities.Reviews;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-10-12T11:16:43")
@StaticMetamodel(Users.class)
public class Users_ { 

    public static volatile CollectionAttribute<Users, Articles> articlesCollection1;
    public static volatile SingularAttribute<Users, String> password;
    public static volatile SingularAttribute<Users, Date> birthdate;
    public static volatile SingularAttribute<Users, String> role;
    public static volatile CollectionAttribute<Users, Articles> articlesCollection;
    public static volatile SingularAttribute<Users, String> name;
    public static volatile CollectionAttribute<Users, Events> eventsCollection;
    public static volatile SingularAttribute<Users, Integer> id;
    public static volatile CollectionAttribute<Users, Reviews> reviewsCollection;
    public static volatile SingularAttribute<Users, String> email;
    public static volatile SingularAttribute<Users, String> username;

}