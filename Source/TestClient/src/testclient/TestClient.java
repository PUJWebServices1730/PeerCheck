/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testclient;

import integration.users.Users;

/**
 *
 * @author sebas
 */
public class TestClient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println(authenticate("test", "pass"));
    }

    private static integration.authentication.Users authenticate(java.lang.Object username, java.lang.Object password) {
        integration.authentication.AuthenticationService_Service service = new integration.authentication.AuthenticationService_Service();
        integration.authentication.AuthenticationService port = service.getAuthenticationServicePort();
        return port.authenticate(username, password);
    }
}
