package view;

import controllor.DbQueries;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
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
        main.setStyle("-fx-background-color: aquamarine;-fx-border-color: burlywood;-fx-border-width:15px");
        Text tx = new Text("Enter user name  (\"all\" to mean all users )") ;
        TextField user_name = new TextField() ;
        user_name.setPromptText("enter name of user ");


        Button add = new Button("Add") ;
        Button delete  = new Button("Delete") ;
        main.getChildren().addAll( tx, user_name ,add ,delete ) ;

        add.setOnAction(e->{
            String name = user_name.getText();
            if(name.trim().length() > 0 && Character.isLetter(name.charAt(0)) ) {
                System.out.println("a"+name+"5");
            DbQueries.addUser(name);
            user_name.setText("");
            }
            else
            {
                Home.status.setText("Please enter an userName ");
            }
        });
        delete.setOnAction(e->{
            String name = user_name.getText().toString();
            if (name.trim().length() > 0 ) {
                DbQueries.deleteUser(name);
                user_name.setText("");
            }
            else
            {
                Home.status.setText("Error!!,  please enter an user name");
            }
        });

        stage.setScene(new Scene(main));
        stage.setTitle("Control Users");
        stage.setResizable(false);
        stage.show();
    }
}
