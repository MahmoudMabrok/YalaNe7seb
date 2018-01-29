package view;

import controllor.DbQueries;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * this is
 * Created by mo3tamed on 1/29/18.
 */
public class ControlUser {

    public static  void  control() {
        Stage stage = new Stage() ;
        VBox main = new VBox(15) ;
        main.setAlignment(Pos.CENTER);
        main.setPadding(new Insets(15));
        main.setStyle("-fx-background-color: aquamarine;-fx-border-color: brown");
        TextField user_name = new TextField() ;
        user_name.setPromptText("enter name of user ");


        Button add = new Button("Add") ;
        Button delete  = new Button("Delete") ;
        main.getChildren().addAll(user_name ,add ,delete ) ;

        add.setOnAction(e->{
            String name = user_name.getText().toString();
            DbQueries.addUser(name);
            Home.lp.cbUser.getItems().setAll(DbQueries.getAllUser());
            user_name.setText("");
        });
        delete.setOnAction(e->{
            String name = user_name.getText().toString();
            DbQueries.deleteUser(name);
            Home.lp.cbUser.getItems().setAll(DbQueries.getAllUser());
            user_name.setText("");
        });

        stage.setScene(new Scene(main));
        stage.setTitle("Control Users");
        stage.setResizable(false);
        stage.show();
    }
}
