/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package peercheckclient;

import controllers.PeercheckRESTController;
import java.util.HashSet;
import java.util.Set;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author juanm
 */
public class PeercheckClient extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("LoginView.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.setTitle("Peercheck");
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
		PeercheckRESTController.init();
        launch(args);
    }
    
}
