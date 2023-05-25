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
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import se.chalmers.cse.dat216.project.CreditCard;
import se.chalmers.cse.dat216.project.Customer;
import se.chalmers.cse.dat216.project.IMatDataHandler;

public class BetalningController implements Initializable{
    @FXML
    Pane kortswish;
    @FXML
    TextField cardOwnerTextField;
    @FXML
    TextField cvvTextField;
    @FXML
    TextField cardnrTextField;
    @FXML
    TextField expyearTextField;
    @FXML
    TextField expmonthTextField;
    @FXML
    Pane kortPane;
    @FXML
    Pane swishPane;
    @FXML
    TextField swishmobileTextField;


    
   
    @FXML
    Button föregåendeButton;
    @FXML
    ComboBox kortcombobox;
    IMatDataHandler iMatDataHandler = IMatDataHandler.getInstance();
    Customer customer = iMatDataHandler.getCustomer();
    CreditCard creditcard = iMatDataHandler.getCreditCard();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        kortcombobox.getItems().addAll("Kort", "Swish");
        kortcombobox.setValue("Kort");
        togglekort();
        kortcombobox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                if (newValue.equals("Kort")) {
                    togglekort();;
                } else if (newValue.equals("Swish")) {
                    toggleswish();
                } 
            }
        });

        cardOwnerTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            creditcard.setHoldersName(newValue);
        });
        cvvTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.isEmpty()) {
                try {
                    int cvv = Integer.parseInt(newValue);
                    creditcard.setVerificationCode(cvv);
                } catch (NumberFormatException e) {
                    
                }
            }
        });
        cardnrTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            creditcard.setCardNumber(newValue);
        });
        expyearTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.isEmpty()) {
                try {
                    int year = Integer.parseInt(newValue);
                    creditcard.setValidYear(year);
                } catch (NumberFormatException e) {
                    
                }
            }
        });
        expmonthTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.isEmpty()) {
                try {
                    int month = Integer.parseInt(newValue);
                    creditcard.setValidMonth(month);
                } catch (NumberFormatException e) {
                   
                }
            }
        });
        cardOwnerTextField.setText(creditcard.getHoldersName());
        cvvTextField.setText(Integer.toString(creditcard.getVerificationCode()));
        cardnrTextField.setText(creditcard.getCardNumber());
        expmonthTextField.setText(Integer.toString(creditcard.getValidMonth()));
        expyearTextField.setText(Integer.toString(creditcard.getValidYear()));
        swishmobileTextField.setText(customer.getMobilePhoneNumber());
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
    private void goLeverans(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Leveransdetaljer.fxml"));
            Parent mainViewRoot = loader.load();

            Stage stage = (Stage) ((Node) event.getTarget()).getScene().getWindow();
            stage.setScene(new Scene(mainViewRoot));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void togglekort() {
        swishPane.setVisible(false);
        kortPane.setVisible(!kortPane.isVisible());
    }
    @FXML
    private void toggleswish() {
        kortPane.setVisible(false);
        swishPane.setVisible(!swishPane.isVisible());
    }
}