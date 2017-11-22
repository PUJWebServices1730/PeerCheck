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
import integration.peercheck.Events;
import integration.peercheck.Reviews;
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
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TitledPane;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
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

    // ------------- Send review components -------------
    @FXML
    private TitledPane paneSendReview;

    @FXML
    private TableView<Reviews> sendReviewReviewsTable;

    @FXML
    private TableColumn sendReviewTableTitleColumn, sendReviewTableAuthorColumn;

    @FXML
    private JFXTextArea sendReviewCommentTextArea;

    @FXML
    private JFXTextField sendReviewGradeTexftField;

    // ------------- View grade components -------------
    @FXML
    private TitledPane paneViewGrade;

    @FXML
    private TableView<Articles> viewGradeArticlesTable;

    @FXML
    private TableView<Reviews> viewGradeReviewsTable;

    @FXML
    private Label viewGradeLabel;

    @FXML
    private TableColumn viewGradeTableTitleColumn, viewGradeTableCategoryColumn, viewGradeTableCommentColumn, viewGradeTableGradeColumn;

    // ------------- Create event components -------------
    @FXML
    private TitledPane paneCreateEvent;

    @FXML
    private JFXTextField createEventNameTextField, createEventLocationTextField, createEventWebsiteTextField;

    @FXML
    private JFXTextArea createEventDescriptionTextArea;

    @FXML
    private DatePicker createEventDateDatepicker, createEventDeadlineDatepicker;

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
        searchArticleTableTitleColumn.prefWidthProperty().bind(searchArticlesTable.widthProperty().multiply(0.5));

        searchArticleTableCategoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
        searchArticleTableCategoryColumn.prefWidthProperty().bind(searchArticlesTable.widthProperty().multiply(0.25));

        searchArticleTableKeyWordsColumn.setCellValueFactory(new PropertyValueFactory<>("keywords"));
        searchArticleTableKeyWordsColumn.prefWidthProperty().bind(searchArticlesTable.widthProperty().multiply(0.25));

        //Change role table columns
        changeRoleTableIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        changeRoleTableIdColumn.prefWidthProperty().bind(changeRoleUsersTable.widthProperty().multiply(0.2));

        changeRoleTableNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        changeRoleTableNameColumn.prefWidthProperty().bind(changeRoleUsersTable.widthProperty().multiply(0.5));

        changeRoleTableRoleColumn.setCellValueFactory(new PropertyValueFactory<>("role"));
        changeRoleTableRoleColumn.prefWidthProperty().bind(changeRoleUsersTable.widthProperty().multiply(0.3));

        //Assign reviewer table columns
        assignReviewerArticlesTableTitleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        assignReviewerArticlesTableTitleColumn.prefWidthProperty().bind(assignReviewerArticlesTable.widthProperty().multiply(0.5));

        assignReviewerArticlesTableCategoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
        assignReviewerArticlesTableCategoryColumn.prefWidthProperty().bind(assignReviewerArticlesTable.widthProperty().multiply(0.25));

        assignReviewerArticlesTableKeyWordsColumn.setCellValueFactory(new PropertyValueFactory<>("keywords"));
        assignReviewerArticlesTableKeyWordsColumn.prefWidthProperty().bind(assignReviewerArticlesTable.widthProperty().multiply(0.25));

        assignReviewerUsersTableIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        assignReviewerUsersTableIdColumn.prefWidthProperty().bind(assignReviewerUsersTable.widthProperty().multiply(0.3));

        assignReviewerUsersTableNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        assignReviewerUsersTableNameColumn.prefWidthProperty().bind(assignReviewerUsersTable.widthProperty().multiply(0.7));

        //Send review table columns
        sendReviewTableTitleColumn.setCellValueFactory(new Callback<CellDataFeatures<Reviews, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(CellDataFeatures<Reviews, String> data) {
                return new SimpleObjectProperty<>(data.getValue().getArticleId().getTitle());
            }
        });
        sendReviewTableTitleColumn.prefWidthProperty().bind(sendReviewReviewsTable.widthProperty().multiply(0.5));

        sendReviewTableAuthorColumn.setCellValueFactory(new Callback<CellDataFeatures<Reviews, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(CellDataFeatures<Reviews, String> data) {
                return new SimpleObjectProperty<>(data.getValue().getArticleId().getMainAuthorId().getName());
            }
        });
        sendReviewTableAuthorColumn.prefWidthProperty().bind(sendReviewReviewsTable.widthProperty().multiply(0.5));

        //View grade table columns
        viewGradeTableTitleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        viewGradeTableTitleColumn.prefWidthProperty().bind(viewGradeArticlesTable.widthProperty().multiply(0.7));

        viewGradeTableCategoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
        viewGradeTableCategoryColumn.prefWidthProperty().bind(viewGradeArticlesTable.widthProperty().multiply(0.3));

        viewGradeTableCommentColumn.setCellValueFactory(new PropertyValueFactory<>("message"));
        viewGradeTableCommentColumn.prefWidthProperty().bind(viewGradeReviewsTable.widthProperty().multiply(0.8));

        viewGradeTableGradeColumn.setCellValueFactory(new PropertyValueFactory<>("grade"));
        viewGradeTableGradeColumn.prefWidthProperty().bind(viewGradeReviewsTable.widthProperty().multiply(0.2));
        
        //Create event table columns
        
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

        //Actualizar tablas del panel send review al expandir
        paneSendReview.expandedProperty().addListener((obs, wasExpanded, isNowExpanded) -> {
            if (isNowExpanded) {
                fillTable(sendReviewReviewsTable, PeercheckSOAPController.getReviewsByReviewer(UserSession.user));
            }
        });

        //Actualizar tablas del panel view grade al expandir
        paneViewGrade.expandedProperty().addListener((obs, wasExpanded, isNowExpanded) -> {
            if (isNowExpanded) {
                fillTable(viewGradeArticlesTable, PeercheckSOAPController.getArticlesByAuthor(UserSession.user));
            }
        });

        //Actualizar tables con el artículo seleccionado de la tabla
        viewGradeArticlesTable.setOnMouseClicked((MouseEvent event) -> {
            if (event.getButton().equals(MouseButton.PRIMARY)) {
                if (viewGradeArticlesTable.getSelectionModel().getSelectedItem() != null) {
                    //Actualizar tabla de revisiones asociadas al artículo
                    fillTable(viewGradeReviewsTable, PeercheckSOAPController.getReviewsByArticle(viewGradeArticlesTable.getSelectionModel().getSelectedItem()));
                    //Actualizar promedio de calificación
                    viewGradeLabel.setText(String.format("%1.2f", PeercheckSOAPController.calculateFinalGradeToArticle(viewGradeArticlesTable.getSelectionModel().getSelectedItem())));
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
                if (trannyFile != null) {
                    outstream = new FileOutputStream(trannyFile.getName());
                    outstream.write(trannyFile.getContent());
                    outstream.close();
                    Desktop.getDesktop().open(new File(trannyFile.getName()));
                } else {
                    displayAlertDialog("Error descargando archivo");
                }

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
        if (selectedArticle == null || selectedUser == null) {
            displayAlertDialog("Por favor selecciona un articulo y un revisor");
            return;
        }
        try {
            Reviews newReview = new Reviews();
            newReview.setArticleId(selectedArticle);
            newReview.setReviewerId(selectedUser);
            newReview.setDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(new GregorianCalendar()));
            newReview.setStatus("ASIGNADA");
            newReview.setMessage("");
            newReview.setGrade(-1);

            PeercheckSOAPController.addReview(newReview);
            displayAlertDialog("La revisión fue asignada");

        } catch (DatatypeConfigurationException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    void sendReview() {
        if (!UserSession.user.getRole().equals("REVISOR")) {
            displayAlertDialog("No tienes permisos para acceder a esta acción");
            return;
        }
        try {
            Reviews review = sendReviewReviewsTable.getSelectionModel().getSelectedItem();

            if (review != null) {
                review.setMessage(sendReviewCommentTextArea.getText());
                review.setGrade(Integer.parseInt(sendReviewGradeTexftField.getText()));
                review.setStatus("COMPLETADA");
                review.setDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(new GregorianCalendar()));

                PeercheckSOAPController.updateReview(review);
                displayAlertDialog("El artículo ha sido calificado");
            } else {
                displayAlertDialog("Selecciona un artículo");
            }

        } catch (DatatypeConfigurationException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void createEvent() {
        Events event = new Events();
        if (!UserSession.user.getRole().equals("EDITOR")) {
            displayAlertDialog("No tienes permisos para acceder a esta acción");
            return;
        }
        try {
            event.setName(createEventNameTextField.getText());
            event.setDescription(createEventDescriptionTextArea.getText());
            event.setLocation(createEventLocationTextField.getText());
            event.setWebsite(createEventWebsiteTextField.getText());
            LocalDate localDate = createEventDateDatepicker.getValue();
            Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
            Date date = Date.from(instant);
            GregorianCalendar c = new GregorianCalendar();
            c.setTime(date);
            event.setDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(c));
            localDate = createEventDeadlineDatepicker.getValue();
            instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
            date = Date.from(instant);
            c.setTime(date);
            event.setDeadline(DatatypeFactory.newInstance().newXMLGregorianCalendar(c));
            event.setEditorId(UserSession.user);
            c.setTime(date);
            event.setSubmissionstart(DatatypeFactory.newInstance().newXMLGregorianCalendar(new GregorianCalendar()));
            
            PeercheckSOAPController.addEvent(event);
            displayAlertDialog("El evento ha sido creado");
        } catch (DatatypeConfigurationException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
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
