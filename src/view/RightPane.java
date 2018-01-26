package view;

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


    TableView<Item> tableView  = new TableView<>() ;
    TableColumn< Item, Integer > idCol = new TableColumn<>("Id") ;
    TableColumn<Item, String  > userCol = new TableColumn<>("User") ;
    TableColumn<Item, String  > descCol = new TableColumn<>("Description") ;
    TableColumn<Item, Double > priceCol = new TableColumn<>("Price") ;

    public RightPane() {
        // idCol.setMinWidth(50);
        idCol.setCellValueFactory(new PropertyValueFactory<Item, Integer>("id"));
        // userCol.setMinWidth(100);
        userCol.setCellValueFactory(new PropertyValueFactory<Item, String>("user"));
        // descCol.setMinWidth(180);
        descCol.setCellValueFactory(new PropertyValueFactory<Item, String>("descrption"));
        // priceCol.setMinWidth(50);
        priceCol.setCellValueFactory(new PropertyValueFactory<Item, Double>("price"));
        idCol.prefWidthProperty().bind(tableView.widthProperty().divide(4));
        userCol.prefWidthProperty().bind(tableView.widthProperty().divide(4));
        descCol.prefWidthProperty().bind(tableView.widthProperty().divide(4));
        priceCol.prefWidthProperty().bind(tableView.widthProperty().divide(4));

        tableView.getColumns().addAll(idCol , userCol , descCol , priceCol ) ;
        getChildren().add(tableView ) ;

    }
}
