package imat;

import java.awt.Button;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class BetalningController implements Initializable{
    @FXML
    Pane kortswish;

    @FXML

    Button föregåendeButton;
   

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }

    @FXML
    private void goBackBetala(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Person_uppgifter.fxml"));
            Parent mainViewRoot = loader.load();

            Stage stage = (Stage) ((Node) event.getTarget()).getScene().getWindow();
            stage.setScene(new Scene(mainViewRoot));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void goBackiMat(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("imat_app.fxml"));
            Parent mainViewRoot = loader.load();

            Stage stage = (Stage) ((Node) event.getTarget()).getScene().getWindow();
            stage.setScene(new Scene(mainViewRoot));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void togglekortswish() {
        kortswish.setVisible(!kortswish.isVisible());
}
}