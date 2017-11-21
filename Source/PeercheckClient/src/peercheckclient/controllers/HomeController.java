/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package peercheckclient.controllers;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import controllers.PeercheckSOAPController;
import integration.peercheck.ArticleCriteria;
import integration.peercheck.Articles;
import integration.peercheck.TrannyFile;
import integration.peercheck.UserRole;
import integration.peercheck.Users;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
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
import javafx.scene.layout.Region;
import javafx.stage.FileChooser;
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
    private Label changeRoleNameLabel, changeRoleRoleLabel;

    @FXML
    private JFXComboBox changeRoleCombo;

    // ------------- Add article components -------------
    @FXML
    private JFXTextField createArticleTitleTextFiled, createArticleKeywordsTextField, createArticleCategoryTextField, createArticleAuthorsTextField;

    @FXML
    private JFXTextArea createArticleAbstractTextArea;

    @FXML
    private Label createFilePathLabel;

    private TrannyFile fileToUpload;

    // ------------- Assign reviewer components -------------
    @FXML
    private TitledPane paneAssignReviewer;
    
    @FXML
    private TableView<Articles> assignReviewerArticlesTable;

    @FXML
    private TableView<Users> assignReviewerUsersTable;
    
    @FXML
    private TableColumn assignReviewerArticlesTableTitleColumn, assignReviewerArticlesTableCategoryColumn, assignReviewerArticlesTableKeyWordsColumn, assignReviewerUsersTableIdColumn, assignReviewerUsersTableNameColumn;
    
    @Override
    public void initialize(URL url, ResourceBundle resources) {

        ObservableList<ArticleCriteria> articleFilter = FXCollections.observableArrayList(
                ArticleCriteria.NOMBRE,
                ArticleCriteria.CATEGORIA
        );
        ObservableList<UserRole> userRole = FXCollections.observableArrayList(
                UserRole.AUTOR,
                UserRole.REVISOR,
                UserRole.EDITOR
        );

        searchArticleFilterCombo.setItems(articleFilter);
        searchArticleFilterCombo.setValue(ArticleCriteria.NOMBRE);
        changeRoleCombo.setItems(userRole);
        changeRoleCombo.setValue(UserRole.REVISOR);

        initComponents();
    }

    public void initTableColumns() {
        //Search articles table columns
        searchArticleTableTitleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        searchArticleTableTitleColumn.setPrefWidth(200);
        
        searchArticleTableCategoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
        searchArticleTableCategoryColumn.setPrefWidth(75);
        
        searchArticleTableKeyWordsColumn.setCellValueFactory(new PropertyValueFactory<>("keywords"));
        searchArticleTableKeyWordsColumn.prefWidthProperty().bind(searchArticlesTable.widthProperty().subtract(277));
        
        //Change role table columns
        changeRoleTableIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        changeRoleTableIdColumn.setPrefWidth(50);
        
        changeRoleTableNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        changeRoleTableNameColumn.prefWidthProperty().bind(changeRoleUsersTable.widthProperty().subtract(125).subtract(2));
        
        changeRoleTableRoleColumn.setCellValueFactory(new PropertyValueFactory<>("role"));
        changeRoleTableRoleColumn.setPrefWidth(75);
        
        //Assign reviewer table columns
        assignReviewerArticlesTableTitleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        assignReviewerArticlesTableTitleColumn.setPrefWidth(200);
        
        assignReviewerArticlesTableCategoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
        assignReviewerArticlesTableCategoryColumn.setPrefWidth(75);
        
        assignReviewerArticlesTableKeyWordsColumn.setCellValueFactory(new PropertyValueFactory<>("keywords"));
        assignReviewerArticlesTableKeyWordsColumn.prefWidthProperty().bind(assignReviewerArticlesTable.widthProperty().subtract(277));
        
        assignReviewerUsersTableIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        assignReviewerUsersTableIdColumn.setPrefWidth(50);
        
        assignReviewerUsersTableNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        assignReviewerUsersTableNameColumn.prefWidthProperty().bind(assignReviewerUsersTable.widthProperty().subtract(50).subtract(2));   
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

        //Actualizar tablas del panel change role al expandir
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

        //Actualizar tablas del panel assign reviewer al expandir
        paneAssignReviewer.expandedProperty().addListener((obs, wasExpanded, isNowExpanded) -> {
            if (isNowExpanded) {
                fillTable(assignReviewerArticlesTable, PeercheckSOAPController.getAllArticles());
                fillTable(assignReviewerUsersTable, PeercheckSOAPController.findUsersByRole("REVISOR"));
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

    //Actualizar labels de información del user seleccionado en el panel de Change Role
    public void updateSelectedUserLabels(Users user) {
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
    void downloadFile() {
        if (searchArticlesTable.getSelectionModel().getSelectedItem() != null) {

            FileOutputStream outstream = null;
            try {
                TrannyFile trannyFile = PeercheckSOAPController.getArticleFile(searchArticlesTable.getSelectionModel().getSelectedItem());
                outstream = new FileOutputStream(trannyFile.getName());
                outstream.write(trannyFile.getContent());
                outstream.close();
                Desktop.getDesktop().open(new File(trannyFile.getName()));
            } catch (FileNotFoundException ex) {
                Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            displayAlertDialog("Selecciona un artículo");
        }
    }

    @FXML
    void changeRole() {
        if (UserSession.user.getRole().equals("EDITOR")) {
            UserRole newRole = (UserRole) changeRoleCombo.getValue();
            Users selectedUser = changeRoleUsersTable.getSelectionModel().getSelectedItem();
            PeercheckSOAPController.changeRol(selectedUser, newRole);
            fillTable(changeRoleUsersTable, PeercheckSOAPController.getAllUsers());
        } else {
            displayAlertDialog("No tienes permisos para acceder a esta acción");
        }
    }

    @FXML
    void createArticle() {
        Articles article = new Articles();
        article.setTitle(createArticleTitleTextFiled.getText());
        article.setAbstract1(createArticleAbstractTextArea.getText());
        article.setCategory(createArticleCategoryTextField.getText());
        article.setKeywords(createArticleKeywordsTextField.getText());
        article.setMainAuthorId(UserSession.user);

        if (fileToUpload == null) {
            displayAlertDialog("Por favor selecciona un archivo");
            return;
        }
        //List<String> authorsEMails = Arrays.asList(createArticleAuthorsTextField.getText().split(","));
        PeercheckSOAPController.addArticle(article, fileToUpload);
        displayAlertDialog("El artículo ha sido creado");
    }

    @FXML
    void loadFile() throws FileNotFoundException, IOException {
        FileChooser fileChooser = new FileChooser();
        File choosenFile = fileChooser.showOpenDialog(new Stage());

        fileToUpload = new TrannyFile();
        fileToUpload.setContent(Files.readAllBytes(Paths.get(choosenFile.getAbsolutePath())));
        fileToUpload.setName(choosenFile.getName());

        createFilePathLabel.setText(fileToUpload.getName());
    }
    
    @FXML
    void assignReviewer() {
        Articles selectedArticle = assignReviewerArticlesTable.getSelectionModel().getSelectedItem();
        Users selectedUser = assignReviewerUsersTable.getSelectionModel().getSelectedItem();
        if(selectedArticle == null || selectedUser == null) {
            displayAlertDialog("Por favor selecciona un articulo y un revisor");
            return;
        }
        PeercheckSOAPController.assignReviewerToArticle(selectedUser, selectedArticle);
        displayAlertDialog("El artículo ha sido asociado");
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
