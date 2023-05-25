
package imat;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
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
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import se.chalmers.cse.dat216.project.IMatDataHandler;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ShoppingCart;
import se.chalmers.cse.dat216.project.ShoppingItem;


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
  AnchorPane kategorifilter;

    @FXML
    Label productName;

    @FXML
    Button nextItem;

    @FXML
    FlowPane categoryContainer;

    @FXML
    Button tidigarekopbutton;
    
    @FXML
    Button favoriterbutton;
    @FXML
    Pane kundvagnsidebar;
    @FXML
    Button profilbutton;
    @FXML
    HBox categoryTemplate;
    @FXML
    Button kategoriknapp;
@FXML 
Pane tidigarekoppopup;

    @FXML
    Button jamforkopvagnbutton;

    @FXML
    Button tillkassanbutton;

    @FXML
    FlowPane cartContainer;

    @FXML
    Text summa;



    int currentIndex = 1;

    IMatDataHandler iMatDataHandler = IMatDataHandler.getInstance();


    public void initialize(URL url, ResourceBundle rb) {

        String iMatDirectory = iMatDataHandler.imatDirectory();
        
        
        
        fontSetter();
        
        imageSetter();
        load_items();

        List<Product> products = iMatDataHandler.getProducts();
       
        
    }



    public void load_items() {
        categoryContainer.getChildren().clear();
        //Kör igenom 7 objekt för tillfället, ska ändras när vi får ordning på kategorierna med filter.
        for (int i = 1; i < 155; i++) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("produkt_template.fxml"));
            try {
                AnchorPane loadedPane = fxmlLoader.load();
                FlowPane loadedContainer = new FlowPane(loadedPane);
               
                categoryContainer.getChildren().add(loadedContainer);
        
                // Set alignment of the loadedContainer to center
                loadedContainer.setAlignment(Pos.TOP_LEFT);
                loadedContainer.setPrefWidth(200);
                loadedContainer.setHgap(3);
                // Hittar rätt fxid med hjälp av .lookup.
                ImageView produktbild = (ImageView) loadedPane.lookup("#produktbild");
                Label produktnamn = (Label) loadedPane.lookup("#produktnamn");
                Text produktpris = (Text) loadedPane.lookup("#produktpris");
                
                // Hämtar datan om produkt
                Product item = iMatDataHandler.getProduct(i);
                Image image = iMatDataHandler.getFXImage(item);
                String text = item.getName();
                Double price = item.getPrice();
                
                produktpris.setText(price.toString() + " kr");
                produktnamn.setText(text);
                produktbild.setImage(image);
                
                Button itemButton = (Button) loadedPane.lookup("#laggtillknaop");
                // Eventhanterare för klicka köp.
                int itemIndex = i; // Sparar nuvarande index
                itemButton.setOnAction(event -> {
                    handleItemButtonClick(itemIndex); //Funktion för vad som händer vid klick
                });


            } catch (IOException exception) {
                throw new RuntimeException(exception);
            }
        }
        
        
    }


    private void display_shoppingcart() {
        cartContainer.getChildren().clear();
        ShoppingCart shoppingCart = iMatDataHandler.getShoppingCart();
        int i = 1;
        double sum = shoppingCart.getTotal();
        summa.setText(String.valueOf(sum) + " kr");
        for (ShoppingItem product1 : shoppingCart.getItems()) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("produkt_template.fxml"));
            try {
                i += 1;
                AnchorPane loadedPane = fxmlLoader.load();

                
                FlowPane loadedContainer = new FlowPane(loadedPane);
                loadedContainer.setAlignment(Pos.TOP_LEFT);
                loadedContainer.setPrefWidth(200);
                loadedContainer.setHgap(3);


                cartContainer.getChildren().add(loadedContainer);
    
                // Hittar rätt fxid med hjälp av .lookup.
                ImageView produktbild = (ImageView) loadedPane.lookup("#produktbild");
                Label produktnamn = (Label) loadedPane.lookup("#produktnamn");
                Text produktpris = (Text) loadedPane.lookup("#produktpris");
                Text numberofitems = (Text) loadedPane.lookup("#numberofitems");
                // Hämtar datan om produkt
                Product item = product1.getProduct();
                Image image = iMatDataHandler.getFXImage(item);
                String text = item.getName();
                Double price = item.getPrice();
                produktpris.setText(price.toString() + " kr");
                produktnamn.setText(text);
                produktbild.setImage(image);
                numberofitems.setText(String.valueOf(product1.getAmount()));


                Button itemButton = (Button) loadedPane.lookup("#laggtillknaop");
                // Eventhanterare för klicka köp.
                int itemIndex = i; // Sparar nuvarande index
                itemButton.setOnAction(event -> {
                    handleItemButtonClick(itemIndex); //Funktion för vad som händer vid klick
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



    private void handleItemButtonClick(int itemIndex) {
        ShoppingCart shoppingCart = iMatDataHandler.getShoppingCart();
        Product item = iMatDataHandler.getProduct(itemIndex);
        double amount = 0;
        for (ShoppingItem product1 : shoppingCart.getItems()){
            if (product1.getProduct() == item){
                amount = product1.getAmount() + 1;
            }
        }
        FlowPane loadedPane = (FlowPane) categoryContainer.getChildren().get(itemIndex - 1);
        Text numberLabel = (Text) loadedPane.lookup("#numberofitems");
        numberLabel.setText(String.valueOf(amount));
        iMatDataHandler.getShoppingCart().addProduct(item);
        display_shoppingcart();
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

    @FXML
    private void clearShopping() {
        //Tar bort allt i varukorgen
        List<Product> arr = iMatDataHandler.getProducts();
        for (int i = 1; i < arr.size(); i++) {
            FlowPane loadedPane = (FlowPane) categoryContainer.getChildren().get(i - 1);
            Text numberLabel = (Text) loadedPane.lookup("#numberofitems");
            numberLabel.setText(String.valueOf(0));
        }
        iMatDataHandler.getShoppingCart().clear();
        display_shoppingcart();
    }
   
    @FXML
    private void togglekategori() {
        kategorifilter.setVisible(!kategorifilter.isVisible());
    }
    @FXML
    private void toggleSidebar() {
        kundvagnsidebar.setVisible(!kundvagnsidebar.isVisible());
    }
    @FXML
    private void toggleTidigarekop() {
        tidigarekoppopup.setVisible(!tidigarekoppopup.isVisible());
    }
    @FXML
    private void openNewPage() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("profile_scene.fxml"));
            Parent newPageRoot = loader.load();

            Stage stage = (Stage) profilbutton.getScene().getWindow();
            stage.setScene(new Scene(newPageRoot));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void openPayment() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Person_uppgifter.fxml"));
            Parent newPageRoot = loader.load();

            Stage stage = (Stage) profilbutton.getScene().getWindow();
            stage.setScene(new Scene(newPageRoot));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
public void resetToStart(){
iMatDataHandler.reset();
 }

}
