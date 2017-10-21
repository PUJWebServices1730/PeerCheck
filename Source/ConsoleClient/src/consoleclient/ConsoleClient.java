/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package consoleclient;

import integration.users.Users;
import integration.articles.Articles;
import integration.events.Events;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author sebas
 */
public class ConsoleClient {
    
    static Scanner input = new Scanner(System.in);
    static Users currentUser = new Users();
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        menu();
    }
    
    public static void menu(){
        int n = -1;
        while(n != 0){
            System.out.println("\n------\n"
                    + "1. Crear usuario\n"
                    + "2. Convertir usuario a revisor\n"
                    + "3. Listar usuarios\n"
                    + "4. Autenticar / Iniciar sesión\n"
                    + "5. Crear artículo\n"
                    + "6. Obtener calificacion final\n"
                    + "7. Buscar articulos"
                    + "------\n");
            n = input.nextInt();
            input.nextLine();
            switch(n){
                case 1:
                    createUser();
                    break;
                case 2:
                    createReviewer();
                    break;
                case 3:
                    listUsers();
                    break;
                case 4:
                    authenticate();
                    break;
                case 5:
                    createArticle();
                    break;
                case 6:
                    getGradeAverage();
                    break;
                case 7:
                    searchArticles();
                    break;
            }
        }
        System.exit(0);
    }
    
    public static void authenticate(){
        String[] args = input.nextLine().split(" ");
        String email = args[0];
        String password = args[1];
        ClassAdapter.copyObject(callAuthenticate(email, password), currentUser);
        System.out.println(String.format(" %s authenticated", currentUser.getEmail()));
    }
    
    private static integration.authentication.Users callAuthenticate(java.lang.String email, java.lang.String password) {
        integration.authentication.AuthenticationService_Service service = new integration.authentication.AuthenticationService_Service();
        integration.authentication.AuthenticationService port = service.getAuthenticationServicePort();
        return port.authenticate(email, password);
    }
    
    public static void createUser(){
        String[] args = input.nextLine().split(" ");
        String name = args[0];
        String mail = args[1];
        String pass = args[2];
        Users user = ClassAdapter.initUser(new Date(), mail, name, pass);
        callCreateUser(user);

    }
    
    private static void callCreateUser(Users entity) {
        integration.users.UsersService_Service service = new integration.users.UsersService_Service();
        integration.users.UsersService port = service.getUsersServicePort();
        port.create(entity);
    }
    
    public static void createReviewer(){
        int n, i = 0;
        List<Users> authors = callFindUsersByRole("Author");
        
        System.out.println("Autores:");
        for(Users user : authors){
            System.out.println(String.format("  %d. %s %s %s", i, user.getName(), user.getEmail(), user.getRole()));
            i++;
        }
        
        System.out.print("\n ¿Autor a cambiar?: ");
        n = input.nextInt();
        input.nextLine();
        callConvertToReviewer(authors.get(n));
    }
    
    private static List<Users> callFindUsersByRole(java.lang.String role) {
        integration.users.UsersService_Service service = new integration.users.UsersService_Service();
        integration.users.UsersService port = service.getUsersServicePort();
        return port.findUsersByRole(role);
    }
    
    private static Users callConvertToReviewer(Users entity) {
        integration.users.UsersService_Service service = new integration.users.UsersService_Service();
        integration.users.UsersService port = service.getUsersServicePort();
        return port.convertToReviewer(entity);
    }
    
    public static void listUsers(){
        List<Users> users = callFindAllUsers();
        
        for(Users user : users){
            System.out.println(String.format("  - %s %s %s", user.getName(), user.getEmail(), user.getRole()));
        }
    }
    
    private static List<Users> callFindAllUsers() {
        integration.users.UsersService_Service service = new integration.users.UsersService_Service();
        integration.users.UsersService port = service.getUsersServicePort();
        return port.findAll();
    }
    
    public static void createArticle(){
        int n, i = 0, j;
        List<Events> events = callFindAllEvents();
        List<integration.articles.Events> articleEvents = new ArrayList<integration.articles.Events>();
        
        System.out.println("Eventos:");
        for(Events event : events){
           System.out.println(String.format("  %d. %s %s", i, event.getName(), event.getDescription()));
           i++; 
        }
        
        String[] args = input.nextLine().split(" ");
        String title = args[0];
        String abstract1 = args[1];
        String category = args[2];
        String keywords = args[3];
        
        System.out.print("Ingresar eventos: ");
        String eventsString = input.nextLine();
        
        String[] eventsNumbers = eventsString.split(",");
        for(j = 0; j < eventsNumbers.length; j++){
            integration.articles.Events nEvent = new integration.articles.Events();
            ClassAdapter.copyObject(events.get(Integer.parseInt(eventsNumbers[j])), nEvent);
            articleEvents.add(nEvent);
        }
        
        System.out.print("Ingresar correos: ");
        List<String> emails = Arrays.asList(input.nextLine().split(","));
        
        Articles article = ClassAdapter.initArticle(abstract1, category, keywords, title, currentUser);
        callCreateArticle(article, emails, articleEvents);
    }

    private static java.util.List<java.lang.String> callCreateArticle(integration.articles.Articles article, java.util.List<java.lang.String> authorsEmails, java.util.List<integration.articles.Events> eventsIds) {
        integration.articles.ArticlesService_Service service = new integration.articles.ArticlesService_Service();
        integration.articles.ArticlesService port = service.getArticlesServicePort();
        return port.create1(article, authorsEmails, eventsIds);
    }
    

    private static java.util.List<integration.events.Events> callFindAllEvents() {
        integration.events.EventsService_Service service = new integration.events.EventsService_Service();
        integration.events.EventsService port = service.getEventsServicePort();
        return port.findAll();
    }

    private static void getGradeAverage() {
        Scanner input = new Scanner(System.in);
        String line = "";
        line = input.nextLine();
        int n = Integer.parseInt(line);
        System.out.println(calculateAverage(n));
    }

    private static Float calculateAverage(int id) {
        integration.articles.ArticlesService_Service service = new integration.articles.ArticlesService_Service();
        integration.articles.ArticlesService port = service.getArticlesServicePort();
        return port.calculateAverage(id);
    }
    
    public static void searchArticles(){
        String[] args = input.nextLine().split(" ");
        String type = args[0];
        String param = args[1];
        List<integration.articles.Articles> articles = callSearchArticles(type, param);
        for(integration.articles.Articles article : articles){
            System.out.println(String.format(" %s %s %s %s", article.getTitle(), article.getAbstract1(), article.getCategory(), article.getKeywords()));
        }
    }

    private static java.util.List<integration.articles.Articles> callSearchArticles(java.lang.String type, java.lang.String param) {
        integration.articles.ArticlesService_Service service = new integration.articles.ArticlesService_Service();
        integration.articles.ArticlesService port = service.getArticlesServicePort();
        return port.search(type, param);
    }
}
