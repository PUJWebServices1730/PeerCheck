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
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TitledPane;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
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
    private Label currentUserNameLabel, currentUserMailLabel, currentUserRoleLabel;

    // ------------- Search article components -------------
    @FXML
    private TitledPane paneSearchArticle;

    @FXML
    private JFXTextField searchArticleQueryTextField;

    @FXML
    private JFXComboBox searchArticleFilterCombo;

    @FXML
    private TableView<Articles> searchArticlesTable;

    @FXML
    private TableColumn searchArticleTableTitleColumn, searchArticleTableCategoryColumn, searchArticleTableKeyWordsColumn;

    @FXML
    private Label searchArticleTitleLabel, searchArticleAuthorLabel, searchArticleKeywordsLabel, searchArticleAbstractLabel;

    // ------------- Change role components -------------
    @FXML
    private TitledPane paneChangeRole;

    @FXML
    private TableView<Users> changeRoleUsersTable;

    @FXML
    private TableColumn changeRoleTableIdColumn, changeRoleTableNameColumn, changeRoleTableRoleColumn;

    @FXML
    private Label changeRoleIdLabel, changeRoleNameLabel, changeRoleRoleLabel;

    @Override
    public void initialize(URL url, ResourceBundle resources) {

        ObservableList<ArticleCriteria> articleFilter = FXCollections.observableArrayList(
                ArticleCriteria.NOMBRE,
                ArticleCriteria.CATEGORIA
        );

        searchArticleFilterCombo.setItems(articleFilter);
        searchArticleFilterCombo.setValue(ArticleCriteria.NOMBRE);

        initComponents();
    }

    public void initTableColumns() {
        //Search articles table columns
        searchArticleTableTitleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        searchArticleTableCategoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
        searchArticleTableKeyWordsColumn.setCellValueFactory(new PropertyValueFactory<>("keywords"));

        //Change role table columns
        changeRoleTableIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        changeRoleTableNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        changeRoleTableRoleColumn.setCellValueFactory(new PropertyValueFactory<>("role"));
    }

    public void setUpListeners() {
        //Actualizar lables con el artículo seleccionado de la tabla
        searchArticlesTable.setOnMouseClicked((MouseEvent event) -> {
            if (event.getButton().equals(MouseButton.PRIMARY)) {
                if (searchArticlesTable.getSelectionModel().getSelectedItem() != null) {
                    updateSelectedArticleLabels(searchArticlesTable.getSelectionModel().getSelectedItem());
                }
            }
        });

        //Actualizar lista de usuarios al expandir el panel
        paneChangeRole.expandedProperty().addListener((obs, wasExpanded, isNowExpanded) -> {
            if (isNowExpanded) {
                fillTable(changeRoleUsersTable, PeercheckSOAPController.getAllUsers());
            }
        });

        //Actualizar lables con el usuario seleccionado de la tabla
        changeRoleUsersTable.setOnMouseClicked((MouseEvent event) -> {
            if (event.getButton().equals(MouseButton.PRIMARY)) {
                if (changeRoleUsersTable.getSelectionModel().getSelectedItem() != null) {
                    updateSelectedUserLabels(changeRoleUsersTable.getSelectionModel().getSelectedItem());
                }
            }
        });

    }

    //Actualizar labels de información del artículo seleciconado en el panel de Search Article
    public void updateSelectedArticleLabels(Articles article) {
        searchArticleTitleLabel.setText(article.getTitle());
        searchArticleAuthorLabel.setText(article.getMainAuthorId().getName());
        searchArticleKeywordsLabel.setText(article.getKeywords());
        searchArticleAbstractLabel.setText(article.getAbstract1());
    }

    //Actualizar labels de información del user seleciconado en el panel de Change Role
    public void updateSelectedUserLabels(Users user) {
        changeRoleIdLabel.setText(user.getId().toString());
        changeRoleNameLabel.setText(user.getName());
        changeRoleRoleLabel.setText(user.getRole());
    }

    public void setCurrentUserInfo() {
        currentUserNameLabel.setText(UserSession.user.getName());
        currentUserMailLabel.setText(UserSession.user.getEmail());
        currentUserRoleLabel.setText(UserSession.user.getRole());
    }

    public void initComponents() {
        setCurrentUserInfo();
        initTableColumns();
        setUpListeners();
    }

    public void fillTable(TableView table, List data) {
        clearTable(table);
        table.getItems().addAll(data);
    }

    public void clearTable(TableView table) {
        table.getItems().clear();
    }

    @FXML
    public void logout(ActionEvent event) {
        openLogin(event);
        UserSession.user = null;
    }

    @FXML
    void findArticle(ActionEvent event) {
        String query = searchArticleQueryTextField.getText();
        ArticleCriteria criteria = (ArticleCriteria) searchArticleFilterCombo.getValue();
        List<Articles> articles = PeercheckSOAPController.findArticleBy(criteria, query);

        if (articles.isEmpty()) {
            clearTable(searchArticlesTable);
            displayAlertDialog("No se encontraron artículos");
        } else {
            fillTable(searchArticlesTable, articles);
        }
    }

    @FXML
    void getAllUsers(ActionEvent event) {
        //List<Users> users = PeercheckSOAPController.getAllUsers();
        //changeRoleUsersTable.getItems().addAll(users);
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
            ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void displayAlertDialog(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, message, ButtonType.OK);
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        alert.show();
    }
}
