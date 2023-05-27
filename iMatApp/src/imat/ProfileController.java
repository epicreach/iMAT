package imat;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import se.chalmers.cse.dat216.project.Customer;
import se.chalmers.cse.dat216.project.IMatDataHandler;
import javafx.scene.Node;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;



public class ProfileController implements Initializable {

    @FXML
    private Button kategoriknapp;
    @FXML
    private Button kundvagnbutton;
    @FXML
    private Button tidigarekopbutton;
    @FXML
    private ImageView profilepicture;
    @FXML
    ImageView imatLogo;
    @FXML
    TextField fornamnText;
    @FXML
    TextField efternamnText;
    @FXML
    TextField mejlText;
    @FXML
    TextField adressText;
    @FXML
    TextField postText;
    @FXML
    TextField mobilText;

    IMatDataHandler iMatDataHandler = IMatDataHandler.getInstance();
    Customer customer = iMatDataHandler.getCustomer();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        imageSetter();
        imatLogo.setOnMouseClicked(event -> goBack(event));
        fornamnText.textProperty().addListener((observable, oldValue, newValue) -> {
            customer.setFirstName(newValue);
        });
        efternamnText.textProperty().addListener((observable, oldValue, newValue) -> {
            customer.setLastName(newValue);
        });
        adressText.textProperty().addListener((observable, oldValue, newValue) -> {
            customer.setAddress(newValue);
        });

        postText.textProperty().addListener((observable, oldValue, newValue) -> {
            customer.setPostAddress(newValue);
        });

        mejlText.textProperty().addListener((observable, oldValue, newValue) -> {
            customer.setEmail(newValue);
        });

        mobilText.textProperty().addListener((observable, oldValue, newValue) -> {
            customer.setMobilePhoneNumber(newValue);
        });

        fornamnText.setText(customer.getFirstName());
       efternamnText.setText(customer.getLastName());
        adressText.setText(customer.getAddress());
        postText.setText(customer.getPostAddress());
        mejlText.setText(customer.getEmail());
        mobilText.setText(customer.getMobilePhoneNumber());
    }

    
    
    
    
    @FXML
    private void goBack(MouseEvent event) {
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
    
    void imageSetter(){
        File file = new File("iMatApp/src/imat/resources/papasmurf.png");
        Image image = new Image(file.toURI().toString());
        profilepicture.setImage(image);


        File iconfile = new File("iMatApp/src/imat/resources/263142.png");
        Image iconimage = new Image(iconfile.toURI().toString());
        ImageView imageView = new ImageView(iconimage);
        imageView.setFitHeight(25);
        imageView.setFitWidth(25);
    
        Label label = new Label("Kundvagn");
        label.setStyle("-fx-font-weight: bold;");
    }

}



