
package imat;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Rectangle;
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

    int currentIndex = 1;

    IMatDataHandler iMatDataHandler = IMatDataHandler.getInstance();

    public void initialize(URL url, ResourceBundle rb) {

        String iMatDirectory = iMatDataHandler.imatDirectory();
        
        List<Product> products = iMatDataHandler.getProducts();
        refresh_item(1,products);


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

}