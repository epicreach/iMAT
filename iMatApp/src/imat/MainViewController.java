
package imat;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.io.File;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
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
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import se.chalmers.cse.dat216.project.IMatDataHandler;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ProductCategory;
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
  StackPane kategorifilter;

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
    StackPane kundvagnsidebar;
    @FXML
    Button profilbutton;
    @FXML
    HBox categoryTemplate;
    @FXML
    Button kategoriknapp;
    @FXML
    Pane graybackground;
    @FXML 
    StackPane tidigarekoppopup;

    @FXML
    Button jamforkopvagnbutton;

    @FXML
    Button tillkassanbutton;

    @FXML
    FlowPane cartContainer;

    @FXML
    Text summa;

    @FXML
    Button b1;

    @FXML
    Button b2;

    @FXML
    Button b3;

    @FXML
    Button b4;

    @FXML
    Button b5;

    @FXML
    Button b6;

    @FXML
    Button b7;

    @FXML
    Button b8;

    @FXML
    Button b9;

    @FXML
    Button b11;



    int currentIndex = 1;
    String lastFilter;

    IMatDataHandler iMatDataHandler = IMatDataHandler.getInstance();


    public void initialize(URL url, ResourceBundle rb) {

        String iMatDirectory = iMatDataHandler.imatDirectory();
        
        
        
        fontSetter();
        
        imageSetter();
        load_items();
        display_shoppingcart();
        init_filter();
        //filter_items(lastFilter);

    }


    public void load_items() {
        categoryContainer.getChildren().clear();
        //Kör igenom 7 objekt för tillfället, ska ändras när vi får ordning på kategorierna med filter.
        List<Product> prolist = iMatDataHandler.getProducts();
        for (Product p : prolist){
        //for (int i = 1; i < 155; i++) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("produkt_template.fxml"));
            try {
                AnchorPane loadedPane = fxmlLoader.load();
                FlowPane loadedContainer = new FlowPane(loadedPane);
               
                categoryContainer.getChildren().add(loadedContainer);
        
                // Set alignment of the loadedContainer to center
                loadedContainer.setAlignment(Pos.TOP_LEFT);
                loadedContainer.setPrefWidth(200);
                
                // Hittar rätt fxid med hjälp av .lookup.
                ImageView produktbild = (ImageView) loadedPane.lookup("#produktbild");
                Label produktnamn = (Label) loadedPane.lookup("#produktnamn");
                Text produktpris = (Text) loadedPane.lookup("#produktpris");
                
                // Hämtar datan om produkt
                Product item = p;
                Image image = iMatDataHandler.getFXImage(item);
                String text = item.getName();
                Double price = item.getPrice();

                ShoppingCart shoppingCart = iMatDataHandler.getShoppingCart();

                double sum = shoppingCart.getTotal();
                Text numberofitems = (Text) loadedPane.lookup("#numberofitems");
                summa.setText(String.valueOf(sum) + " kr");
                for (ShoppingItem product1 : shoppingCart.getItems()) {
                    if(product1.getProduct() == item){
                    numberofitems.setText(String.valueOf((int)product1.getAmount()));
                }
                
            }
                
                produktpris.setText(price.toString() + " kr");
                produktnamn.setText(text);
                produktbild.setImage(image);
                
                Button itemButton = (Button) loadedPane.lookup("#laggtillknaop");
                Button itemButton3 = (Button) loadedPane.lookup("#tabortknapp");
                // Eventhanterare för klicka köp.
                int itemIndex = item.getProductId(); // Sparar nuvarande index
                itemButton.setOnAction(event -> {
                    handleItemButtonClick(itemIndex); //Funktion för vad som händer vid klick
                    if(categoryContainer.getChildren().size() >= 150){
                        load_items();
                    }
                });
                itemButton3.setOnAction(event ->{
                    handleItemButtonClick3(itemIndex);
                    if(categoryContainer.getChildren().size() >= 150){
                        load_items();
                    }
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
                loadedContainer.setPrefWidth(201);
                


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
                numberofitems.setText(String.valueOf((int)product1.getAmount()));


                Button itemButton = (Button) loadedPane.lookup("#laggtillknaop");
                Button itemButton3 = (Button) loadedPane.lookup("#tabortknapp");
                // Eventhanterare för klicka köp.
                int itemIndex = item.getProductId();

                itemButton.setOnAction(event -> {
                    handleItemButtonClick(itemIndex); //Funktion för vad som händer vid klick
                    if(categoryContainer.getChildren().size() < 150){
                        filter_items(lastFilter);
                    }else{
                        load_items();
                    }
                    
                });
                itemButton3.setOnAction(event ->{
                    handleItemButtonClick3(itemIndex);
                    if(categoryContainer.getChildren().size() < 150){
                        filter_items(lastFilter);
                    }else{
                        load_items();
                    }
                });

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //System.out.println(null);
    }

    public List<ProductCategory> convert_productcategory(String input){
        lastFilter = input;
        List<ProductCategory> list = new ArrayList<>();
        if(input.equals("B1")){
            list.add(ProductCategory.FRUIT);
            list.add(ProductCategory.CITRUS_FRUIT);
            list.add(ProductCategory.EXOTIC_FRUIT);

        }
        else if(input.equals("B2")){
            list.add(ProductCategory.BREAD);
            list.add(ProductCategory.PASTA);

        }
        else if(input.equals("B3")){
            list.add(ProductCategory.DAIRIES);
        }
        else if(input.equals("B4")){
            list.add(ProductCategory.MEAT);
        }
        else if(input.equals("B5")){
            list.add(ProductCategory.FISH);
        }
        else if(input.equals("B6")){
            list.add(ProductCategory.COLD_DRINKS);
        }
        else if(input.equals("B7")){
            list.add(ProductCategory.CABBAGE);
            list.add(ProductCategory.HERB);
        }
        else if(input.equals("B8")){
            list.add(ProductCategory.NUTS_AND_SEEDS);
        }
        else if(input.equals("B9")){
            list.add(ProductCategory.SWEET);
        }
        else if(input.equals("B11")){
            ProductCategory[] all = ProductCategory.values();
            for(ProductCategory pro : all){
            list.add(pro);
        }
        }
        return(list);
        //return null;
    }

    public void filter_items(String input) {
        categoryContainer.getChildren().clear();
        List<ProductCategory> procat = convert_productcategory(input);
        List<Product> items = new ArrayList<>();
        for(ProductCategory productcat : procat){
            for(Product pro : iMatDataHandler.getProducts(productcat)){
                items.add(pro);
            }
        }
        for (Product p : items){
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("produkt_template.fxml"));
            try {
                AnchorPane loadedPane = fxmlLoader.load();
                FlowPane loadedContainer = new FlowPane(loadedPane);
               
                categoryContainer.getChildren().add(loadedContainer);
        
                // Set alignment of the loadedContainer to center
                loadedContainer.setAlignment(Pos.TOP_LEFT);
                loadedContainer.setPrefWidth(200);
                
                // Hittar rätt fxid med hjälp av .lookup.
                ImageView produktbild = (ImageView) loadedPane.lookup("#produktbild");
                Label produktnamn = (Label) loadedPane.lookup("#produktnamn");
                Text produktpris = (Text) loadedPane.lookup("#produktpris");
                
                // Hämtar datan om produkt
                Product item = p;
                Image image = iMatDataHandler.getFXImage(item);
                String text = item.getName();
                Double price = item.getPrice();

                ShoppingCart shoppingCart = iMatDataHandler.getShoppingCart();

                double sum = shoppingCart.getTotal();
                Text numberofitems = (Text) loadedPane.lookup("#numberofitems");
                summa.setText(String.valueOf(sum) + " kr");
                for (ShoppingItem product1 : shoppingCart.getItems()) {
                    if(product1.getProduct() == item){
                    numberofitems.setText(String.valueOf((int)product1.getAmount()));
                }
                
            }
                
                produktpris.setText(price.toString() + " kr");
                produktnamn.setText(text);
                produktbild.setImage(image);
                
                Button itemButton = (Button) loadedPane.lookup("#laggtillknaop");
                Button itemButton3 = (Button) loadedPane.lookup("#tabortknapp");
                // Eventhanterare för klicka köp.
                int itemIndex = item.getProductId(); // Sparar nuvarande index
                itemButton.setOnAction(event -> {
                    handleItemButtonClick(itemIndex); //Funktion för vad som händer vid klick
                    filter_items(input);
                });
                itemButton3.setOnAction(event ->{
                    handleItemButtonClick3(itemIndex);
                    filter_items(input);
                });


            } catch (IOException exception) {
                throw new RuntimeException(exception);
            }



            }
    }

    public void init_filter(){
        b1.setOnAction(event -> handleCategoryButtonClick("B1"));
        b2.setOnAction(event -> handleCategoryButtonClick("B2"));
        b3.setOnAction(event -> handleCategoryButtonClick("B3"));
        b4.setOnAction(event -> handleCategoryButtonClick("B4"));
        b5.setOnAction(event -> handleCategoryButtonClick("B5"));
        b6.setOnAction(event -> handleCategoryButtonClick("B6"));
        b7.setOnAction(event -> handleCategoryButtonClick("B7"));
        b8.setOnAction(event -> handleCategoryButtonClick("B8"));
        b9.setOnAction(event -> handleCategoryButtonClick("B9"));
        b11.setOnAction(event -> handleCategoryButtonClick("B11"));
    }


    
    private void handleItemButtonClick3(int itemIndex) {
        ShoppingCart shoppingCart = iMatDataHandler.getShoppingCart();
        Product item = iMatDataHandler.getProduct(itemIndex);
        double amount = 0;
    
        for (ShoppingItem product1 : shoppingCart.getItems()) {
            if (product1.getProduct() == item) {
                amount = product1.getAmount() - 1;
                if (amount <= 0) {
                    amount = 0; // Tar bort negativa värden
                    shoppingCart.removeItem(product1);
                } else {
                    product1.setAmount(amount);
                }
                break;
            }
        }
    
        FlowPane loadedPane = null;
        for (Node child : categoryContainer.getChildren()) {
            if (child instanceof FlowPane) {
                Product childProduct = (Product) child.getUserData();
                if (childProduct != null && childProduct == item) {
                    loadedPane = (FlowPane) child;
                    break;
                }
            }
        }
    
        if (loadedPane != null) {
            Text numberLabel = (Text) loadedPane.lookup("#numberofitems");
            numberLabel.setText(String.valueOf((int) amount));
        }
    
        display_shoppingcart();
    }

    private void handleItemButtonClick(int itemIndex) {
        ShoppingCart shoppingCart = iMatDataHandler.getShoppingCart();
        Product item = iMatDataHandler.getProduct(itemIndex);
        double amount = 0;
        
        for (ShoppingItem product1 : shoppingCart.getItems()) {
            if (product1.getProduct() == item) {
                amount = product1.getAmount() + 1;
            }
        }
        
        if (amount == 0) {
            amount = 1;
        }
        
        FlowPane loadedPane = null;
        for (Node child : categoryContainer.getChildren()) {
            if (child instanceof FlowPane) {
                Product childProduct = (Product) child.getUserData();
                if (childProduct != null && childProduct == item) {
                    loadedPane = (FlowPane) child;
                    break;
                }
            }
        }
        
        if (loadedPane != null) {
            Text numberLabel = (Text) loadedPane.lookup("#numberofitems");
            numberLabel.setText(String.valueOf((int) amount));
        }
        
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

    @FXML void handleCategoryButtonClick(String category){
        System.out.println(category);
        filter_items(category);
    }


    @FXML
    private void clearShopping() {
        //Tar bort allt i varukorgen
        load_items();
        List<Product> arr = iMatDataHandler.getProducts();
        for (int i = 1; i < arr.size()-1; i++) {
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
        checkbackground();

    }
    @FXML
    private void toggleSidebar() {
        kundvagnsidebar.setVisible(!kundvagnsidebar.isVisible());
        checkbackground();
    }

    @FXML
    private void toggleTidigarekop() {
        tidigarekoppopup.setVisible(!tidigarekoppopup.isVisible());
        checkbackground();
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
    void checkbackground(){
        if (kundvagnsidebar.isVisible() || tidigarekoppopup.isVisible() || kategorifilter.isVisible() ){
            graybackground.setVisible(true);
        }
        else{
            graybackground.setVisible(false);
        }
    }
public void resetToStart(){
iMatDataHandler.reset();
 }

}
