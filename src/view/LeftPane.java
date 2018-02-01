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
public class LeftPane extends VBox {

    HBox addUserPane = new HBox(5);
    TextField tfDescription;
    TextField tfPrice;
    ComboBox<String> cbUser;
    Button btnAddItem;
    Button btnUpdateItem;
    Button btnDeleteItem;
    //static int tempId ;

    public LeftPane() {

        setStyle("-fx-background-color: chartreuse");
        btnUpdateItem = new Button("Update user ");
        btnDeleteItem = new Button("Delete  item ");

        tfDescription = new TextField();
        tfPrice = new TextField();
        cbUser = new ComboBox<>();
        btnAddItem = new Button("Add Item ");
        btnUpdateItem = new Button("Update Item ");

        setSpacing(15);
        setPadding(new Insets(15));
        setAlignment(Pos.CENTER);
        btnAddItem.setAlignment(Pos.CENTER);

        tfDescription.setPromptText("Items");
        tfPrice.setPromptText("Price");
        //cbUser.setValue("Users");
        cbUser.setPromptText("Enter User");
        cbUser.setMinWidth(150);
        cbUser.getItems().setAll(DbQueries.getAllUser());
        cbUser.setMinWidth(150);

        getChildren().addAll(tfDescription, tfPrice, cbUser,
                btnAddItem, btnUpdateItem, btnDeleteItem);

        //actions

        btnAddItem.setOnAction(e -> {

            try {
                String user = cbUser.getValue().toString();
                if (user.length() > 0 ) {
                    String description = tfDescription.getText().toString();
                    double price = Double.parseDouble(tfPrice.getText().toString());
                    DbQueries.addItem(user, DbQueries.getLastId() + 1, description, price);
                    Home.lp.setAllBlank();
                }
            }catch (NullPointerException ex ){
                Home.status.setText("Error!! ,  you should choose a user ");
            }
            catch (NumberFormatException ex) {
                Home.status.setText("Error in format please provide correct data ");
            }
        });
        btnUpdateItem.setOnAction(e -> {
            int id = RightPane.tableView.getSelectionModel().getSelectedItem().getId();
            DbQueries.updateItem(cbUser.getValue().toString(),
                    id, tfDescription.getText(),
                    Double.parseDouble(tfPrice.getText()));
            Home.lp.setAllBlank();

        });
        btnDeleteItem.setOnAction(e -> {
            int id = RightPane.tableView.getSelectionModel().getSelectedItem().getId();
            DbQueries.deleteItem(id);
            Home.lp.setAllBlank();
        });

    }

    public void setAllBlank() {
        tfDescription.setText("");
        tfPrice.setText("");
        cbUser.setValue("");


    }

}
