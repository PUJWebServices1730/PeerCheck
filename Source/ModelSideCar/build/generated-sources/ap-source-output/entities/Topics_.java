package entities;

import entities.Events;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-10-12T00:27:36")
@StaticMetamodel(Topics.class)
public class Topics_ { 

    public static volatile SingularAttribute<Topics, String> name;
    public static volatile CollectionAttribute<Topics, Events> eventsCollection;
    public static volatile SingularAttribute<Topics, Integer> id;

}