package view;

import controllor.DbQueries;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import model.Item;

/**
 * this is
 * Created by mo3tamed on 1/26/18.
 */
public class RightPane extends VBox {


   static TableView<Item> tableView  = new TableView<>() ;
  // static TextArea data = new TextArea();
     public  static   Text  data = new Text("") ;

    TableColumn< Item, Integer > idCol = new TableColumn<>("Id") ;
    TableColumn<Item, String  > userCol = new TableColumn<>("User") ;
    TableColumn<Item, String  > descCol = new TableColumn<>("Description") ;
    TableColumn<Item, Double > priceCol = new TableColumn<>("Price") ;
    TableColumn<Item, String > dateCol = new TableColumn<>("Date") ;

     public    ObservableList<Item> items   ;

    public RightPane() {
        Text hint = new Text("Statistics ") ;
        hint.setStyle("-fx-background-color: blanchedalmond;-fx-text-fill: black");
        setSpacing(10);
        setStyle("-fx-border-color:aquamarine");
       // ScrollPane dataPane = new ScrollPane(data) ;
      //  data.setEditable(false);

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

        //set pref width of column
        idCol.prefWidthProperty().bind(tableView.widthProperty().divide(5));
        userCol.prefWidthProperty().bind(tableView.widthProperty().divide(5));
        descCol.prefWidthProperty().bind(tableView.widthProperty().multiply(0.4));//make it double of other
        priceCol.prefWidthProperty().bind(tableView.widthProperty().divide(5));
        dateCol.prefWidthProperty().bind(tableView.widthProperty().divide(5));


        tableView.prefWidthProperty().bind(this.widthProperty());


       // dataPane.prefHeightProperty().bind(this.heightProperty().multiply(0.3));
   //     dataPane.prefWidthProperty().bind(this.widthProperty());


        tableView.getColumns().addAll(idCol , userCol , descCol , priceCol ,dateCol ) ;
        items.setAll(DbQueries.getAllItems()) ;
        tableView.getItems().setAll(items) ;
        getChildren().addAll( tableView , hint , data ) ;

        tableView.setOnMousePressed (e->{

            Item i =  tableView.getSelectionModel().getSelectedItem() ;
           if( i != null ) {
               Home.lp.cbUser.setValue(i.getUser());
               Home.lp.descriptionInput.setText(i.getDescrption());
               Home.lp.tfPrice.setText("" + i.getPrice());
           }else
               Home.lp.setAllBlank();
        });






    }
}
