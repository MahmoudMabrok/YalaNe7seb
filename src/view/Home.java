package view;

import controllor.DbQueries;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * this is
 * Created by mo3tamed on 1/26/18.
 */
public class Home extends Application  {

  public   static   LeftPane lp  ; // public to be seen outside package
   public  static RightPane rp  ;

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
        VBox root = new VBox(5) ;

        HBox panes = new HBox(25) ;
        panes.getChildren().addAll(lp ,rp  ) ;

        root.getChildren().addAll(panes , status ) ;
        Scene scene =new Scene(root) ;
        primaryStage.setScene(scene);
        primaryStage.setTitle("YalaNe7eb");
        primaryStage.show();

        lp.prefWidthProperty().bind(scene.widthProperty().multiply(0.35));
        rp.prefWidthProperty().bind(scene.widthProperty().multiply(0.65));

    }
}
