package view;

import controllor.DbQueries;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import model.Item;

/**
 * this is
 * Created by mo3tamed on 1/26/18.
 */
public class RightPane extends HBox {


   static TableView<Item> tableView  = new TableView<>() ;
    TableColumn< Item, Integer > idCol = new TableColumn<>("Id") ;
    TableColumn<Item, String  > userCol = new TableColumn<>("User") ;
    TableColumn<Item, String  > descCol = new TableColumn<>("Description") ;
    TableColumn<Item, Double > priceCol = new TableColumn<>("Price") ;
    TableColumn<Item, String > dateCol = new TableColumn<>("Date") ;

     public    ObservableList<Item> items   ;

    public RightPane() {
        setStyle("-fx-border-color:aquamarine");

        items  = FXCollections.observableArrayList() ;
        idCol.setMinWidth(40);
        idCol.setCellValueFactory(new PropertyValueFactory<Item, Integer>("id"));
         userCol.setMinWidth(100);
        userCol.setCellValueFactory(new PropertyValueFactory<Item, String>("user"));
        // descCol.setMinWidth(180);
        descCol.setCellValueFactory(new PropertyValueFactory<Item, String>("descrption"));
        // priceCol.setMinWidth(50);
        priceCol.setCellValueFactory(new PropertyValueFactory<Item, Double>("price"));

        dateCol.setCellValueFactory(new PropertyValueFactory<Item, String>("itemdate"));

    /*    idCol.prefWidthProperty().bind(tableView.widthProperty().divide(4));
        userCol.prefWidthProperty().bind(tableView.widthProperty().divide(4));
        descCol.prefWidthProperty().bind(tableView.widthProperty().divide(4));
        priceCol.prefWidthProperty().bind(tableView.widthProperty().divide(4));*/

        tableView.getColumns().addAll(idCol , userCol , descCol , priceCol ,dateCol ) ;
        items.setAll(DbQueries.getAllItems()) ;
        tableView.getItems().setAll(items) ;
        getChildren().add(tableView ) ;

        tableView.setOnMousePressed (e->{

            Item i =  tableView.getSelectionModel().getSelectedItem() ;
           if( i != null ) {
               Home.lp.cbUser.setValue(i.getUser());
               Home.lp.tfDescription.setText(i.getDescrption());
               Home.lp.tfPrice.setText("" + i.getPrice());
           }else
               Home.lp.setAllBlank();
        });

    }
}
