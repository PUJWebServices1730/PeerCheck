/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package peercheckclient.controllers;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import controllers.PeercheckSOAPController;
import integration.peercheck.Users;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javax.swing.Icon;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import peercheckclient.PeercheckClient;
import session.UserSession;

public class SignupController implements Initializable {
    
    @FXML
    private JFXTextField inputEmail;
    
    @FXML
    private JFXTextField inputNames;
    
    @FXML
    private JFXTextField inputSurnames;

    @FXML
    private JFXPasswordField inputPassword;

    @FXML
    private JFXComboBox<String> comboRol;
    
    @FXML
    private Label labelResult;

    @FXML
    void signup(ActionEvent event) {
        String email = inputEmail.getText().toLowerCase();
        String password = inputPassword.getText();
        String names = inputNames.getText();
        String surnames = inputSurnames.getText();
        String name = (names + " " + surnames).trim();
        String rol = comboRol.getValue();
        try {
            Users user = new Users();
            user.setEmail(email);
            user.setName(name);
            user.setPassword(password);
            user.setBirthdate(DatatypeFactory.newInstance().newXMLGregorianCalendar(new GregorianCalendar()));
            user.setRole(rol);
            UserSession.user = PeercheckSOAPController.signup(user);
            if(UserSession.user != null) {
                openHome(event);
            } else {
                labelResult.setText("Ya existe un usuario con este correo");
            }
        } catch (DatatypeConfigurationException ex) {
            Logger.getLogger(SignupController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void openLogin(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(PeercheckClient.class.getResource("views/LoginView.fxml"));
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
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> options = FXCollections.observableArrayList (
            "Autor",
            "Editor",
            "Revisor"
        );
        comboRol.setItems(options);
        comboRol.setValue("Autor");
    }
    
    private void openHome(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(PeercheckClient.class.getResource("views/HomeView.fxml"));
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
