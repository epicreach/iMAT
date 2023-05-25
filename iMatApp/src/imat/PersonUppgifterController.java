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
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import se.chalmers.cse.dat216.project.Customer;
import se.chalmers.cse.dat216.project.IMatDataHandler;

public class PersonUppgifterController implements Initializable{

    @FXML
    Button hembutton;
    @FXML
    Button frgndbutton;
    @FXML
    Button nastasida;
    @FXML
    TextField namnTextField;
    @FXML
    TextField adressTextField;
    @FXML
    TextField postTextField;
    @FXML
    TextField emailTextField;
    @FXML
    TextField mobilTextField;
    
    IMatDataHandler iMatDataHandler = IMatDataHandler.getInstance();
    Customer customer = iMatDataHandler.getCustomer();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        namnTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            customer.setFirstName(newValue);
        });
        adressTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            customer.setAddress(newValue);
        });

        postTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            customer.setPostAddress(newValue);
        });

        emailTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            customer.setEmail(newValue);
        });

        mobilTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            customer.setMobilePhoneNumber(newValue);
        });

        namnTextField.setText(customer.getFirstName());
        adressTextField.setText(customer.getAddress());
        postTextField.setText(customer.getPostAddress());
        emailTextField.setText(customer.getEmail());
        mobilTextField.setText(customer.getMobilePhoneNumber());
    }
    @FXML
    private void openNewPage() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Betalningssätt.fxml"));
            Parent newPageRoot = loader.load();

            Stage stage = (Stage) nastasida.getScene().getWindow();
            stage.setScene(new Scene(newPageRoot));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void goBack(ActionEvent event) {
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

  

  
    

}