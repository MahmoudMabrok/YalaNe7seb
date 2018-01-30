package view;

import controllor.DbConnection;
import controllor.DbQueries;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
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
        System.out.println(DbQueries.getSumOfPrices("month" , "01"));

        HBox panes = new HBox(25) ;
        panes.getChildren().addAll(lp ,rp  ) ;
        panes.setStyle("-fx-background-color: antiquewhite");
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
                    RightPane.tableView.getItems().setAll(DbQueries.getAllItems());
                }
            }


        });

        lp.prefWidthProperty().bind(primaryStage.widthProperty().multiply(0.4));
        rp.prefWidthProperty().bind(primaryStage.widthProperty().multiply(0.6));

        primaryStage.setOnCloseRequest(e->{
            DbConnection.disconnect();
        });

        main.requestFocus();

    }
}
