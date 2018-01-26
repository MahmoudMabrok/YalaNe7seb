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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class YalaNe7seb extends Application {


    ObservableList<Item> items = FXCollections.observableArrayList() ;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        DataBase db= new DataBase() ;
        db.addAll(items);
        HBox root = new HBox(20);
        root.setPadding(new Insets(15));

        VBox leftPane = new VBox(15) ;
        leftPane.setAlignment(Pos.CENTER);

        HBox addUserPane  = new HBox(5);
        TextField user_name = new TextField() ;
        Button btnAddUser = new Button("Add user ") ;
        addUserPane.getChildren().addAll(user_name , btnAddUser ) ;

        VBox addItemPane = new VBox(9) ;
        TextField tfDescription = new TextField() ;
        tfDescription.setPromptText("Items");
        TextField tfPrice  = new TextField() ;
        tfPrice.setPromptText("Price");
        ComboBox<String> cbUser = new ComboBox<>() ;
        cbUser.setValue("Users");
        cbUser.setMinWidth(150);
        cbUser.getItems().addAll(db.getUsers()) ;

        Button btnAddItem = new Button("AddItem ");
        btnAddItem.setAlignment(Pos.CENTER);
        addItemPane.getChildren().addAll(tfDescription , tfPrice ,cbUser ,btnAddItem );

        leftPane.getChildren().addAll(addUserPane ,addItemPane) ;
        root.getChildren().add(leftPane) ;

        Pane rightPane  = new Pane() ;
        TableView<Item> tableView  = new TableView<>() ;

        TableColumn< Item, Integer > idCol = new TableColumn<>("Id") ;
        TableColumn< Item, String  > userCol = new TableColumn<>("User") ;
        TableColumn< Item, String  > descCol = new TableColumn<>("Description") ;
        TableColumn< Item, Double > priceCol = new TableColumn<>("Price") ;

       // idCol.setMinWidth(50);
        idCol.setCellValueFactory(new PropertyValueFactory<Item, Integer>("id"));
       // userCol.setMinWidth(100);
        userCol.setCellValueFactory(new PropertyValueFactory<Item, String>("user"));
       // descCol.setMinWidth(180);
        descCol.setCellValueFactory(new PropertyValueFactory<Item, String>("descrption"));
       // priceCol.setMinWidth(50);
        priceCol.setCellValueFactory(new PropertyValueFactory<Item, Double>("price"));

       /* idCol.prefWidthProperty().bind(tableView.widthProperty().divide(4));
        userCol.prefWidthProperty().bind(tableView.widthProperty().divide(4));
        descCol.prefWidthProperty().bind(tableView.widthProperty().divide(4));
        priceCol.prefWidthProperty().bind(tableView.widthProperty().divide(4));*/


        tableView.getColumns().addAll(idCol , userCol , descCol , priceCol ) ;
        tableView.setItems(items);

        rightPane.getChildren().add(tableView ) ;
        root.getChildren().add(rightPane ) ;

        //controls
        btnAddItem.setOnAction(e->{
            db.addItem( cbUser.getValue(),items.size()+1 ,tfDescription.getText(),   Double.parseDouble(tfPrice.getText())) ;
            db.addAll(items);
        });
        btnAddUser.setOnAction(e->{
            db.addUser(user_name.getText());
            cbUser.getItems().add(user_name.getText());
        });


        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        //fit panes
        rightPane.prefWidthProperty().bind(scene.widthProperty().multiply(0.65));
        leftPane.prefWidthProperty().bind(scene.widthProperty().multiply(0.35));

       // primaryStage.setMinWidth(760);
       // primaryStage.setMinHeight(200);
        primaryStage.setTitle("YalaNe7seb");
        primaryStage.show();

        //close connection
        primaryStage.setOnCloseRequest(e->db.disconnect());
    }

}

    
