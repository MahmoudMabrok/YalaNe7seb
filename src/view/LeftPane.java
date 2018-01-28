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
    Button btnUpdateItem ;
    Button btnDeleteItem ;
    //static int tempId ;

    public LeftPane() {

        setStyle("-fx-background-color: chartreuse");
        btnAddUser = new Button("Add user ") ;
        btnUpdateItem = new Button("Update user ") ;
        btnDeleteItem = new Button("Delete  item ") ;
        addUserPane.getChildren().addAll(user_name , btnAddUser  ) ;

        tfDescription = new TextField() ;
        tfPrice = new TextField() ;
        cbUser = new ComboBox<>() ;
        btnAddItem = new Button("Add Item ") ;
        btnUpdateItem = new Button("Update Item ") ;

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
        getChildren().addAll(tfDescription , tfPrice ,cbUser ,btnAddItem ,btnUpdateItem ,btnDeleteItem );

        //actions
        btnAddUser.setOnAction(e->{
            String name = user_name.getText().toString() ;
            DbQueries.addUser(name);
            cbUser.getItems().setAll(DbQueries.getAllUser()) ;
            user_name.setText("");
        });
        btnAddItem.setOnAction(e->{

            String user = cbUser.getValue().toString() ;
            String description = tfDescription.getText().toString() ;
            double price  = Double.parseDouble(tfPrice.getText().toString()) ;
            DbQueries.addItem(user , DbQueries.getLastId()+1 , description , price);
            RightPane.tableView.getItems().setAll(DbQueries.getAllItems() ) ;
            Home.lp.setAllBlank();
        });
        btnUpdateItem.setOnAction(e->{
          int id = RightPane.tableView.getSelectionModel().getSelectedItem().getId() ;
          DbQueries.updateItem(cbUser.getValue().toString(),
                  id,tfDescription.getText(),
                  Double.parseDouble(tfPrice.getText()));
            //RightPane.items.setAll(DbQueries.getAllItems());
            RightPane.tableView.getItems().setAll(DbQueries.getAllItems()) ;
            Home.lp.setAllBlank();

        });
        btnDeleteItem.setOnAction(e->{
            int id = RightPane.tableView.getSelectionModel().getSelectedItem().getId() ;
            System.out.println(id + " aaaaaaaaaaaaaaaaaa ");
            DbQueries.deleteItem(id);
            RightPane.tableView.getItems().setAll(DbQueries.getAllItems()) ;
            Home.lp.setAllBlank();
        });

    }
    public void setAllBlank(){
        tfDescription.setText("");
        tfPrice.setText("");
        cbUser.setPromptText("choose a user ");

    }

}
