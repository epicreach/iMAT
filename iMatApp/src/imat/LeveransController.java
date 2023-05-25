package imat;

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
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;



public class LeveransController implements Initializable {
    @FXML
    ComboBox leveranstidcombo;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        leveranstidcombo.getItems().addAll("11:00", "11:30","12:00", "12:30", "13:00", "13:30", "14:00", "14:30", "15:00", "15:30", "16:00", "16:30", "17:00");
    }
    @FXML
    private void goBack(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Betalningssätt.fxml"));
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
private void movetoconfirm(ActionEvent event) {
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("confirmpage.fxml"));
        Parent mainViewRoot = loader.load();
        
        Stage stage = (Stage) ((Node) event.getTarget()).getScene().getWindow();
        stage.setScene(new Scene(mainViewRoot));
        stage.show();
    } catch (IOException e) {
        e.printStackTrace();
    }
}
}