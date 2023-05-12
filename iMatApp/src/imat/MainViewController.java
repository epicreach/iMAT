
package imat;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.awt.event.ActionEvent;
import java.awt.geom.RoundRectangle2D;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
    ImageView kundvagnicon;

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
    Button leveranstiderbutton;

    @FXML
    HBox categoryTemplate;

    @FXML
    Button jamforkopvagnbutton;

    @FXML
    Button tillkassanbutton;

    int currentIndex = 1;

    IMatDataHandler iMatDataHandler = IMatDataHandler.getInstance();


    public void initialize(URL url, ResourceBundle rb) {

        String iMatDirectory = iMatDataHandler.imatDirectory();
        
        

        fontSetter();


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
        leveranstiderbutton.setFont(font);
        favoriterbutton.setFont(font);
        profilbutton.setFont(font);

    }

   

}