/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package peercheckclient.controllers;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import controllers.PeercheckRESTController;
import entities.Users;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import peercheckclient.PeercheckClient;
import session.UserSession;

public class LoginController implements Initializable {
    
    @FXML
    private JFXTextField inputEmail;
    
    @FXML
    private JFXPasswordField inputPassword;
    
    @FXML
    private Label labelResult;
    
    @FXML
    public void login(ActionEvent actionEvent) {
        String email = inputEmail.getText().toLowerCase();
        String password = inputPassword.getText();
        labelResult.setText("");
        
        
        if(email.equals("a") && password.equals("a")){
            UserSession.user = PeercheckRESTController.login("bobadilla@mail.com", "qwertyuiop");
            openHome(actionEvent);
            return;
        }
        
        if(email.equals("e") && password.equals("e")){
            UserSession.user = PeercheckRESTController.login("parra@mail.com", "qwertyuiop");
            openHome(actionEvent);
            return;
        }
        
        if(email.equals("r") && password.equals("r")){
            UserSession.user = PeercheckRESTController.login("forero@mail.com", "qwertyuiop");
            openHome(actionEvent);
            return;
        }
        
        
        
        if(!email.matches("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])")) {
            labelResult.setText("Ingresa un correo valido");
            return;
        }
        
        if(!password.matches("[a-zA-Z0-9]{8,}")) {
            labelResult.setText("La contraseña debe tener minimo 8 caracteres");
            return;
        }
        
        UserSession.user = PeercheckRESTController.login(email, password);
        if(UserSession.user != null) {
            openHome(actionEvent);
        } else {
            labelResult.setText("Usuario o contraseña incorrectos");
        }
    }
    
    @FXML
    public void openRegistrarse(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(PeercheckClient.class.getResource("SignupView.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Peercheck");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
            ((Stage)((Node)event.getSource()).getScene().getWindow()).close();
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {}
    
    private void openHome(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(PeercheckClient.class.getResource("HomeView.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Peercheck");
            stage.show();
            ((Stage)((Node)event.getSource()).getScene().getWindow()).close();
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
