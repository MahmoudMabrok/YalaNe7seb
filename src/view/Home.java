package view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * this is
 * Created by mo3tamed on 1/26/18.
 */
public class Home extends Application  {

    LeftPane lp  = new LeftPane();
    RightPane rp  = new RightPane() ;


    public static void main(String[] args) {
            launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        HBox panes = new HBox(25) ;
        panes.getChildren().addAll(lp ,rp) ;

        Scene scene =new Scene(panes) ;
        primaryStage.setScene(scene);
        primaryStage.setTitle("YalaNe7eb");
        primaryStage.show();

        lp.prefWidthProperty().bind(scene.widthProperty().multiply(0.35));
        rp.prefWidthProperty().bind(scene.widthProperty().multiply(0.65));

    }
}
