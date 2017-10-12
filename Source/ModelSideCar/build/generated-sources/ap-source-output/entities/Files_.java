package entities;

import entities.Articles;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-10-12T00:27:36")
@StaticMetamodel(Files.class)
public class Files_ { 

    public static volatile CollectionAttribute<Files, Articles> articlesCollection1;
    public static volatile SingularAttribute<Files, String> path;
    public static volatile CollectionAttribute<Files, Articles> articlesCollection;
    public static volatile SingularAttribute<Files, Integer> id;

}