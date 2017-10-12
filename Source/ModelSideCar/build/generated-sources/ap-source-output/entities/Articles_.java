package entities;

import entities.Events;
import entities.Files;
import entities.Reviews;
import entities.Users;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-10-12T00:27:36")
@StaticMetamodel(Articles.class)
public class Articles_ { 

    public static volatile CollectionAttribute<Articles, Files> filesCollection;
    public static volatile SingularAttribute<Articles, String> keywords;
    public static volatile CollectionAttribute<Articles, Events> eventsCollection;
    public static volatile SingularAttribute<Articles, Users> mainAuthorId;
    public static volatile SingularAttribute<Articles, Integer> id;
    public static volatile SingularAttribute<Articles, String> abstract1;
    public static volatile SingularAttribute<Articles, String> category;
    public static volatile CollectionAttribute<Articles, Users> usersCollection;
    public static volatile CollectionAttribute<Articles, Reviews> reviewsCollection;
    public static volatile SingularAttribute<Articles, Files> fileId;

}