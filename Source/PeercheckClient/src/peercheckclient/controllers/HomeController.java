/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package peercheckclient.controllers;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXSnackbar;
import com.jfoenix.controls.JFXTextField;
import controllers.PeercheckSOAPController;
import integration.peercheck.ArticleCriteria;
import integration.peercheck.Articles;
import integration.peercheck.Users;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import peercheckclient.PeercheckClient;
import session.UserSession;

/**
 * FXML Controller class
 *
 * @author juanm
 */
public class HomeController implements Initializable {

    @FXML
    private Label articleFoundAutor;

    @FXML
    private Label articleFoundKeywords;

    @FXML
    private Label labelCorreo;

    @FXML
    private Label articleFoundID;

    @FXML
    private Label articleFoundName;

    @FXML
    private Label labelRol;

    @FXML
    private Label articleFoundCetegory;

    @FXML
    private JFXTextField searchArticle;

    @FXML
    private Label labelNombre;
    
    @FXML
    private TableView<Articles> articleFoundTable;
    
    @FXML
    private TableView<Users> changeRoleUsersTable;
    
    @FXML
    private TableColumn idColumn;
    
    @FXML
    private TableColumn nameColumn;

    @FXML
    private JFXComboBox<ArticleCriteria> comboArticleFilter;

    @FXML
    public void logout(ActionEvent event) {
        openLogin(event);
        UserSession.user = null;
    }
    
    @FXML
    void findArticle(ActionEvent event) {
        String query = searchArticle.getText();
        ArticleCriteria criteria = comboArticleFilter.getValue();
        System.out.println(criteria + " - " + query);
        List<Articles> articles = PeercheckSOAPController.findArticleBy(criteria, query);
        if(articles.size() > 0) {
            System.out.println(articles.size());
            articleFoundTable.getItems().setAll(articles);
            articleFoundAutor.setText(articles.get(0).getMainAuthorId().getName());
            articleFoundCetegory.setText(articles.get(0).getCategory());
            articleFoundID.setText(articles.get(0).getId() + "");
            articleFoundKeywords.setText(articles.get(0).getKeywords());
            articleFoundName.setText(articles.get(0).getTitle());
        } else {
            articleFoundID.setText("No encontrado");
        }
    }
    
    @FXML
    void getAllUsers(ActionEvent event) {
        List<Users> users = PeercheckSOAPController.getAllUsers();
        System.out.println(users.size());
        
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));        
//changeRoleUsersTable.getColumns().addAll(idColumn, nameColumn);
        changeRoleUsersTable.getItems().addAll(users);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle resources) {
        labelCorreo.setText(UserSession.user.getEmail());
        labelRol.setText(UserSession.user.getRole());
        labelNombre.setText(UserSession.user.getName());
        
        ObservableList<ArticleCriteria> articleFilter = FXCollections.observableArrayList (
            ArticleCriteria.NOMBRE,
            ArticleCriteria.CATEGORIA
        );
        comboArticleFilter.setItems(articleFilter);
        comboArticleFilter.setValue(ArticleCriteria.NOMBRE);
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
}
