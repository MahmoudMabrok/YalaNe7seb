/**
 * this is
 * Created by mo3tamed on 1/21/18.
 */

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class YalaNe7seb extends Application {


    ObservableList<Item> items = FXCollections.observableArrayList() ;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        HBox root = new HBox(20);
        root.setPadding(new Insets(15));

        VBox leftPane = new VBox(15) ;
        leftPane.setAlignment(Pos.CENTER);

        HBox addUserPane  = new HBox(5);
        TextField user_name = new TextField() ;
        Button btnAdd = new Button("Add user ") ;
        addUserPane.getChildren().addAll(user_name , btnAdd ) ;

        VBox addItemPane = new VBox(9) ;
        TextField tfDescription = new TextField() ;
        tfDescription.setPromptText("Items");
        TextField tfPrice  = new TextField() ;
        tfPrice.setPromptText("Price");
        TextField tfUser = new TextField() ;
        tfUser.setPromptText("User");
        Button btnAddItem = new Button("Add");
        btnAddItem.setAlignment(Pos.CENTER);
        addItemPane.getChildren().addAll(tfDescription , tfPrice ,tfUser ,btnAddItem );

        leftPane.getChildren().addAll(addUserPane ,addItemPane) ;
        root.getChildren().add(leftPane) ;

        Pane rightPane  = new Pane() ;
        TableView<Item> tableView  ;

        TableColumn< Item, Integer > idCol = new TableColumn<>("id") ;
        TableColumn< Item, String  > userCol = new TableColumn<>("User") ;
        TableColumn< Item, String  > descCol = new TableColumn<>("Description") ;
        TableColumn< Item, Double > priceCol = new TableColumn<>("Price") ;

        idCol.setMinWidth(50);
        idCol.setCellValueFactory(new PropertyValueFactory<Item, Integer>("id"));
        userCol.setMinWidth(100);
        userCol.setCellValueFactory(new PropertyValueFactory<Item, String>("user"));
        descCol.setMinWidth(180);
        descCol.setCellValueFactory(new PropertyValueFactory<Item, String>("descrption"));
        priceCol.setMinWidth(50);
        priceCol.setCellValueFactory(new PropertyValueFactory<Item, Double>("price"));

        tableView = new TableView<>() ;
        tableView.setItems(items);
        tableView.getColumns().addAll(idCol , userCol , descCol , priceCol ) ;

        rightPane.getChildren().add(tableView ) ;
        root.getChildren().add(rightPane ) ;



        //controls
        btnAddItem.setOnAction(e->{
            Item item = new Item (tfDescription.getText() , Double.parseDouble(tfPrice.getText()) , tfUser.getText()  , items.size()) ;
            items.add(item ) ;
        });


        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("YalaNe7seb");
        primaryStage.show();

    }
}

    
