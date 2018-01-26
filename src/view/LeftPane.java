package view;

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
    TextField user_name = new TextField() ;
    Button btnAddUser = new Button("Add user ") ;
    TextField tfDescription = new TextField() ;
    TextField tfPrice  = new TextField() ;
    ComboBox<String> cbUser = new ComboBox<>() ;
    Button btnAddItem = new Button("AddItem ");




    public LeftPane() {
        setSpacing(15);
        setPadding(new Insets(15));
        setAlignment(Pos.CENTER);
        btnAddItem.setAlignment(Pos.CENTER);


        tfDescription.setPromptText("Items");
        tfPrice.setPromptText("Price");
        cbUser.setValue("Users");
        cbUser.setMinWidth(150);
      //  cbUser.getItems().addAll() ;
        cbUser.setMinWidth(150);


        addUserPane.getChildren().addAll(user_name,btnAddUser) ;
        getChildren().add(addUserPane);
        getChildren().addAll(tfDescription , tfPrice ,cbUser ,btnAddItem );

    }
}
