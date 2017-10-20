/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package consoleclient;

import java.util.Date;
import java.util.GregorianCalendar;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import integration.users.Users;
import integration.articles.Articles;
import java.lang.reflect.Field;

/**
 *
 * @author sebas
 */
public class ClassAdapter {
    public static Users initUser(Date birthdate, String email, String name, String password) {
		Users user = new Users();
		try {
			GregorianCalendar c = new GregorianCalendar();
			c.setTime(birthdate);
			user.setBirthdate(DatatypeFactory.newInstance().newXMLGregorianCalendar(c));
		} catch (DatatypeConfigurationException ex) {
		}
		user.setEmail(email);
		user.setName(name);
		user.setPassword(password);
		user.setRole("Author");
		return user;
	}
    
    public static void copyObject(Object source, Object destination){
        for(Field field : source.getClass().getDeclaredFields()){
            try{
                field.setAccessible(true);
                Field f = destination.getClass().getDeclaredField(field.getName());
                f.setAccessible(true);
                f.set(destination, field.get(source));
            }
            catch(IllegalAccessException | NoSuchFieldException | SecurityException ex){
                ex.printStackTrace();
            }
        }
    }
    
    public static Articles initArticle(String abstract1, String category, String keywords, String title, Users mainAuthor){
        Articles article = new Articles();
        article.setAbstract1(abstract1);
        article.setCategory(category);
        article.setKeywords(keywords);
        article.setTitle(title);
        integration.articles.Users author = new integration.articles.Users();
        copyObject(mainAuthor, author);
        article.setMainAuthorId(author);
        return article;
    }

}
