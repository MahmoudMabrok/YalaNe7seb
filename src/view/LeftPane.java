package view;

import controllor.DbQueries;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * this is
 * Created by mo3tamed on 1/26/18.
 */
public class LeftPane  extends VBox {

    HBox addUserPane  = new HBox(5);
    TextField user_name = new TextField("") ;
    Button btnAddUser ;
    TextField tfDescription ;
    TextField tfPrice ;
    ComboBox<String> cbUser ;
    Button btnAddItem ;


    public LeftPane() {

       // user_name = new TextField("") ;
        btnAddUser = new Button("Add user ") ;
        addUserPane.getChildren().addAll(user_name , btnAddUser ) ;

        tfDescription = new TextField() ;
        tfPrice = new TextField() ;
        cbUser = new ComboBox<>() ;
        btnAddItem = new Button("Add Item ") ;

        setSpacing(15);
        setPadding(new Insets(15));
        setAlignment(Pos.CENTER);
        btnAddItem.setAlignment(Pos.CENTER);


        tfDescription.setPromptText("Items");
        tfPrice.setPromptText("Price");
        cbUser.setValue("Users");
        cbUser.setMinWidth(150);
        cbUser.getItems().setAll(DbQueries.getAllUser()) ;
        cbUser.setMinWidth(150);



        getChildren().add(addUserPane);
        getChildren().addAll(tfDescription , tfPrice ,cbUser ,btnAddItem );
        btnAddUser.setOnAction(e->{
            String name = user_name.getText().toString() ;
            DbQueries.addUser(name);
            System.out.println("added");
            cbUser.getItems().setAll(DbQueries.getAllUser()) ;
        });

    }
}
