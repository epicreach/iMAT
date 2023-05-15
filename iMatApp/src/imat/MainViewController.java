
package imat;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.awt.event.ActionEvent;
import java.awt.geom.RoundRectangle2D;
import java.io.File;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import se.chalmers.cse.dat216.project.IMatDataHandler;
import se.chalmers.cse.dat216.project.Product;
import javafx.scene.paint.Color;


public class MainViewController implements Initializable {

    @FXML
    ImageView imatLogo;
    @FXML
    Button kundvagnbutton;

    @FXML
    ImageView profilepicture;
    
    @FXML
    BorderPane mainID;

    @FXML
    ImageView pictureID;

    @FXML
    Label productName;

    @FXML
    Button nextItem;

    @FXML
    Button tidigarekopbutton;

    @FXML
    Button favoriterbutton;

    @FXML
    Button profilbutton;
    @FXML
    HBox categoryTemplate;
    @FXML
    Button kategoriknapp;

    @FXML
    Button jamforkopvagnbutton;

    @FXML
    Button tillkassanbutton;

    int currentIndex = 1;

    IMatDataHandler iMatDataHandler = IMatDataHandler.getInstance();


    public void initialize(URL url, ResourceBundle rb) {

        String iMatDirectory = iMatDataHandler.imatDirectory();
        
        

        fontSetter();

        imageSetter();

        List<Product> products = iMatDataHandler.getProducts();
        refresh_item(1,products);

        //categoryTemplate.setSpacing(50);

        //Template setOnAction-funktion
        nextItem.setOnAction(event -> {
            currentIndex = (currentIndex + 1) % products.size();
            refresh_item(currentIndex, products);
        });

        
    }

    public void refresh_item(int num, List<Product> products){
            //Hämtar in en produkt på ett index
            Product item = iMatDataHandler.getProduct(num);
            //Laddar in bilden
            Image image = iMatDataHandler.getFXImage(item);
            //Sätter bilden till den inladdade
            pictureID.setImage(image);
            //Sätter texten till produktens namn
            String text = item.getName();
            productName.setText(text);

        
    }

    public void fontSetter(){
        Font font = Font.font("Verdana Pro Black", FontWeight.BOLD, 20);
        tidigarekopbutton.setFont(font);
        favoriterbutton.setFont(font);
        profilbutton.setFont(font);
        kategoriknapp.setFont(font);

    }
    
    /*Lägger till iMAT logon och ikonen i Kundvagnsknappen */
    public void imageSetter(){
        File file = new File("iMatApp/src/imat/resources/iMAT.png");
        Image image = new Image(file.toURI().toString());
        imatLogo.setImage(image);


       /*  File profilefile = new File("iMatApp/src/imat/resources/papasmurf.png");
        Image profilepic = new Image(profilefile.toURI().toString());
        profilepicture.setImage(profilepic); */
        
        File iconfile = new File("iMatApp/src/imat/resources/263142.png");
        Image iconimage = new Image(iconfile.toURI().toString());
        ImageView imageView = new ImageView(iconimage);
        imageView.setFitHeight(25);
        imageView.setFitWidth(25);
    
        Label label = new Label("Kundvagn");
        label.setStyle("-fx-font-weight: bold;");
    
        HBox hbox = new HBox(label, imageView);
        hbox.setAlignment(Pos.CENTER);
    
        kundvagnbutton.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        kundvagnbutton.setGraphic(hbox);
    }

}