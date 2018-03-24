package view;

import controllor.DbConnection;
import controllor.DbQueries;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Item;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

/**
 * this is
 * Created by mo3tamed on 1/26/18.
 */
public class Home extends Application  {

     static   LeftPane lp  ; // public to be seen outside package
    static RightPane rp  ;
     MenuPane menuPane = new MenuPane() ;


 public static Text status  ;

    public Home() {
        lp  = new LeftPane() ;
        rp = new RightPane() ;
        status = new Text() ;

    }

    public static void main(String[] args) {
            launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        DbQueries.creatDb(); //create db

        BorderPane main = new BorderPane() ;
      //  VBox root = new VBox(10) ;
        System.out.println(DbQueries.getSumOfPrices("month" , "01"));

        HBox panes = new HBox() ;
        panes.getChildren().addAll(lp ,rp  ) ;
        panes.setStyle("-fx-background-color: antiquewhite");
      //  Pane statusPane = new Pane(status) ;

       /*statusPane.setMaxHeight(30);
       statusPane.setMinHeight(10);*/


      //  root.getChildren().addAll(menuPane , panes , statusPane) ;

        main.setCenter(panes);
        main.setBottom(status);
        main.setTop(menuPane);

        Scene scene =new Scene(main) ;
        primaryStage.setScene(scene);
        primaryStage.setTitle("YalaNe7eb");
        primaryStage.show();

        //action on shortcuts

        main.setOnKeyPressed(e->{
            if (e.getCode() == KeyCode.DELETE ){
                int id  =  RightPane.tableView.getSelectionModel().getSelectedItem().getId();
                if (id > -1 ){
                    DbQueries.deleteItem(id);
                }
            }
        });

       // menuPane.prefHeightProperty().bind(root.heightProperty().multiply(0.2));
      //  statusPane.prefHeightProperty().bind(root.heightProperty().multiply(0.2));
       // panes.prefHeightProperty().bind(main.heightProperty().subtract(100) );


        lp.prefWidthProperty().bind(panes.widthProperty().multiply(0.2));
        rp.prefWidthProperty().bind(panes.widthProperty().multiply(0.8));

        primaryStage.setOnCloseRequest(e->{
            DbConnection.disconnect();
            Platform.exit();
        });
        main.requestFocus();

        //setIcon
        primaryStage.getIcons().add(new Image("reources/cart-1956097_960_720.png")) ;


    }
}
