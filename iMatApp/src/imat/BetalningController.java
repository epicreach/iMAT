package imat;

import java.awt.Button;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import se.chalmers.cse.dat216.project.IMatDataHandler;

public class BetalningController implements Initializable{
    @FXML
    Pane kortswish;

    @FXML

    Button föregåendeButton;
    @FXML
    ComboBox kortcombobox;
    IMatDataHandler iMatDataHandler = IMatDataHandler.getInstance();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        kortcombobox.setItems(FXCollections.observableArrayList("Kort","Swish"));
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