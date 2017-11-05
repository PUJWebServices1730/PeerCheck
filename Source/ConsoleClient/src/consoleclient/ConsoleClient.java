/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package consoleclient;

import integration.users.Users;
import integration.articles.Articles;
import integration.authentication.AuthenticationService_Service;
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
    static Users currentUser = null;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        menu();
    }
    
    public static void menu(){
        int n = -1;
        while(n != 0){
            System.out.println("\n-------\n"
                + "0. Salir"
            );
            if(currentUser == null) {
                System.out.println(
                    "1. Crear usuario\n"
                    + "2. Iniciar sesión\n"
                    + "3. Buscar articulo"
                );
            }
            if(currentUser != null) {
                if(currentUser.getRole().equals("Author")){
                    System.out.println(
                    "3. Buscar articulo\n"
                    + "4. Crear artículo\n"
                    + "5. Obtener calificacion final\n"
                    + "9. Cerrar sesión\n"
                );
                } else if(currentUser.getRole().equals("Reviewer")){
                    System.out.println(
                    "3. Buscar articulo\n"
                    + "4. Crear artículo\n"
                    + "6. Enviar evaluación de artículo\n"
                    + "9. Cerrar sesión\n"
                );
                } else if(currentUser.getRole().equals("Editor")){
                    System.out.println(
                    "3. Buscar articulo\n"
                    + "7. Convertir usuario a revisor"
                    + "8. Asignar revisor a articulo\n"
                    + "9. Cerrar sesión\n");
                }
                
            }  
            System.out.println("-------\n");
            n = input.nextInt();
            input.nextLine();
            switch(n){
                case 1:
                    createUser();
                    break;
                case 2:
                    authenticate();
                    break;
                case 3:
                    searchArticles();
                    break;
                case 4:
                    createArticle();
                    break;
                case 5:
                    getGradeAverage();
                    break;
                case 6:
                    sendEvaluation();
                    break;
                case 7:
                    createReviewer();
                    break;
                case 8:
                    assignReviewer();
                    break;
                case 9:
                    currentUser = null;
                    break;
            }
        }
        System.exit(0);
    }
    
    public static void createUser(){
        System.out.print("name email password: ");
        String[] args = input.nextLine().split(" ");
        String name = args[0];
        String email = args[1];
        String password = args[2];
        if(callFindUserByEmail(email) != null) {
            System.out.println("El correo ya está registrado");
        }
        else {
            Users user = ClassAdapter.initUser(new Date(), email, name, password);
            callCreateUser(user);
            System.out.println("Usuario creado");
        }
    }
    
    private static void callCreateUser(Users entity) {
        integration.users.UsersService_Service service = new integration.users.UsersService_Service();
        integration.users.UsersService port = service.getUsersServicePort();
        port.create(entity);
    }
    
    private static Users callFindUserByEmail(java.lang.String email) {
        integration.users.UsersService_Service service = new integration.users.UsersService_Service();
        integration.users.UsersService port = service.getUsersServicePort();
        return port.findUserByEmail(email);
    }
    
    public static void authenticate(){
        System.out.print("email password: ");
        String[] args = input.nextLine().split(" ");
        String email = args[0];
        String password = args[1];
        integration.authentication.Users user = callAuthenticate(email, password);
        if(user != null) {
            currentUser = new Users();
            ClassAdapter.copyObject(user, currentUser);
            System.out.println(String.format("%s autenticado", currentUser.getEmail()));
        }
        else {
            System.out.println("El usuario o contraseña son incorrectos");
        }
    }
    
    private static integration.authentication.Users callAuthenticate(java.lang.String email, java.lang.String password) {
        AuthenticationService_Service service = new integration.authentication.AuthenticationService_Service();
        integration.authentication.AuthenticationService port = service.getAuthenticationServicePort();
        return port.authenticate(email, password);
    }
    
    public static void searchArticles(){
        System.out.print("filtro valor: ");
        String[] args = input.nextLine().split(" ");
        String type = args[0];
        String param = args[1];
        List<integration.articles.Articles> articles = callSearchArticles(type, param);
        for(integration.articles.Articles article : articles){
            System.out.println(String.format("%s %s %s %s", article.getTitle(), article.getAbstract1(), article.getCategory(), article.getKeywords()));
        }
    }

    private static java.util.List<integration.articles.Articles> callSearchArticles(java.lang.String type, java.lang.String param) {
        integration.articles.ArticlesService_Service service = new integration.articles.ArticlesService_Service();
        integration.articles.ArticlesService port = service.getArticlesServicePort();
        return port.search(type, param);
    }
    
    public static void createArticle(){
        int n, i = 0, j;
        List<Events> events = callFindAllEvents();
        List<Integer> articleEventsIds = new ArrayList<Integer>();
        System.out.print("titulo abstract categoria palabras_clave: ");
        String[] args = input.nextLine().split(" ");
        String title = args[0];
        String abstract1 = args[1];
        String category = args[2];
        String keywords = args[3];
        System.out.println("\nEventos:");
        for(Events event : events){
           System.out.println(String.format("%d. %s %s", i, event.getName(), event.getDescription()));
           i++; 
        }
        System.out.print("Ingresar eventos: ");
        String eventsString = input.nextLine();
        String[] eventsNumbers = eventsString.split(",");
        for(j = 0; j < eventsNumbers.length; j++){
            articleEventsIds.add(events.get(Integer.parseInt(eventsNumbers[j])).getId());
        }
        System.out.print("Ingresar correos: ");
        List<String> emails = Arrays.asList(input.nextLine().split(","));
        Articles article = ClassAdapter.initArticle(abstract1, category, keywords, title, currentUser);
        create1(article, emails, articleEventsIds);
    }
    
    
    
    private static java.util.List<integration.events.Events> callFindAllEvents() {
        integration.events.EventsService_Service service = new integration.events.EventsService_Service();
        integration.events.EventsService port = service.getEventsServicePort();
        return port.findAll();
    }
    
    public static void createReviewer(){
        int n, i = 0;
        List<Users> authors = callFindUsersByRole("Author");
        System.out.println("Autores:");
        for(Users user : authors){
            System.out.println(String.format("%d. %s %s %s", i, user.getName(), user.getEmail(), user.getRole()));
            i++;
        }
        System.out.print("\n¿Autor a cambiar?: ");
        n = input.nextInt();
        input.nextLine();
        callConvertToReviewer(authors.get(n));
        System.out.println(authors.get(n).getName() + " promovido a revisor");
    }
    
    private static Users callConvertToReviewer(Users entity) {
        integration.users.UsersService_Service service = new integration.users.UsersService_Service();
        integration.users.UsersService port = service.getUsersServicePort();
        return port.convertToReviewer(entity);
    }
    
    private static List<Users> callFindUsersByRole(java.lang.String role) {
        integration.users.UsersService_Service service = new integration.users.UsersService_Service();
        integration.users.UsersService port = service.getUsersServicePort();
        return port.findUsersByRole(role);
    }

    private static void getGradeAverage() {
        int n, i = 0;
        List<Articles> articles = callFindAllArticles();
        for(Articles article: articles) {
            System.out.println(String.format("%d. %s %s %s", article.getId(), article.getTitle(), article.getCategory(), article.getAbstract1()));
            i++;
        }
        System.out.print("\n¿Articulo a revisar?: ");
        n = input.nextInt();
        input.nextLine();
        System.out.println("Promedio de revisión de: " + calculateAverage(n));
    }

    private static java.util.List<integration.articles.Articles> callFindAllArticles() {
        integration.articles.ArticlesService_Service service = new integration.articles.ArticlesService_Service();
        integration.articles.ArticlesService port = service.getArticlesServicePort();
        return port.findAll();
    }
    
    private static Float calculateAverage(int id) {
        integration.articles.ArticlesService_Service service = new integration.articles.ArticlesService_Service();
        integration.articles.ArticlesService port = service.getArticlesServicePort();
        return port.calculateAverage(id);
    }
    
    public static void assignReviewer(){
        
    }
    
    public static void sendEvaluation(){
        
    }

    private static java.util.List<java.lang.String> create1(integration.articles.Articles article, java.util.List<java.lang.String> authorsEmails, java.util.List<java.lang.Integer> eventsIds) {
        integration.articles.ArticlesService_Service service = new integration.articles.ArticlesService_Service();
        integration.articles.ArticlesService port = service.getArticlesServicePort();
        return port.create1(article, authorsEmails, eventsIds);
    }
}
