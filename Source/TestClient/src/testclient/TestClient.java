/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testclient;

import integration.users.Users;
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
}
