package entities;

import entities.Articles;
import entities.Topics;
import entities.Users;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-10-12T00:27:36")
@StaticMetamodel(Events.class)
public class Events_ { 

    public static volatile SingularAttribute<Events, Date> date;
    public static volatile SingularAttribute<Events, Users> editorId;
    public static volatile SingularAttribute<Events, String> website;
    public static volatile CollectionAttribute<Events, Articles> articlesCollection;
    public static volatile SingularAttribute<Events, Date> submissionstart;
    public static volatile SingularAttribute<Events, String> name;
    public static volatile SingularAttribute<Events, String> description;
    public static volatile SingularAttribute<Events, String> location;
    public static volatile CollectionAttribute<Events, Topics> topicsCollection;
    public static volatile SingularAttribute<Events, Integer> id;
    public static volatile SingularAttribute<Events, Date> deadline;

}