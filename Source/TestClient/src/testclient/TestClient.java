/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testclient;

import integration.articles.Articles;
import integration.users.Users;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author sebas
 */
public class TestClient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
		/**
		 * Crear un usuario.
		 */
		create(ClassAdapter.initUser(new Date(), "email@mail.com", "Carlos", "pass"));
		/**
		 * Volver reviewer a un usuario.
		 */
		convertToReviewer(1);
		/**
		 * Crear artículo
		 */
		Articles article = new Articles();
		article.setAbstract1("Lorem ipsum dolor sit amet...");
		article.setCategory("Engineering");
		article.setKeywords("test, proyect, web services");
		article.setTitle("Test Article");
		create1(article, "email@email.com", new ArrayList<>(), new ArrayList<>());
    }

	private static void create(integration.users.Users entity) {
		integration.users.UsersService_Service service = new integration.users.UsersService_Service();
		integration.users.UsersService port = service.getUsersServicePort();
		port.create(entity);
	}

	private static Users convertToReviewer(java.lang.Object id) {
		integration.users.UsersService_Service service = new integration.users.UsersService_Service();
		integration.users.UsersService port = service.getUsersServicePort();
		return port.convertToReviewer(id);
	}

	private static java.util.List<java.lang.String> create1(integration.articles.Articles article, java.lang.String mainAuthorEmail, java.util.List<java.lang.String> authorsEmails, java.util.List<java.lang.Integer> eventsIds) {
		integration.articles.ArticlesService_Service service = new integration.articles.ArticlesService_Service();
		integration.articles.ArticlesService port = service.getArticlesServicePort();
		return port.create1(article, mainAuthorEmail, authorsEmails, eventsIds);
	}
	
	
}
