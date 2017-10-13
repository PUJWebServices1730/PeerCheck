package entities;

import entities.Articles;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-10-12T19:40:13")
@StaticMetamodel(Files.class)
public class Files_ { 

    public static volatile SingularAttribute<Files, Articles> articleId;
    public static volatile SingularAttribute<Files, String> description;
    public static volatile SingularAttribute<Files, Integer> id;
    public static volatile SingularAttribute<Files, String> title;
    public static volatile SingularAttribute<Files, String> url;

}