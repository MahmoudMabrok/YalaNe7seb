package view;


import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * this is
 * Created by mo3tamed on 2/7/18.
 */
public class infoPane {

  static   Text mainText = new Text();
  static  Stage mainStage  ;

  static  Font fHont = Font.font( "sanserif"  , FontWeight.NORMAL,FontPosture.REGULAR ,20  ) ;

    public static void setUpStage (String name ){

        mainStage = new Stage() ;
        HBox root = new HBox() ;
        root.getChildren().add(mainText) ;

        Scene sc = new Scene(root , 750 , 500 ) ;

        mainStage.setScene(sc );
        mainStage.setTitle(name);
        mainStage.show();
    }

    public static void aboutStage(){

        String about = "YalaNe7seb program to store daily purchase\n with capabilities" +
                " (sum of all costs , sum payments of a user\n , get each amount of money should every one" +
                " pay\n " +
                "by :: @ Mahmoud Mabrok ,Java Developer  ) " ;

        setUpStage("about");
        mainText.setText(about);
        mainText.setFont(fHont);

    }
    public static void helpStage(){

        String help = "Getting started :: \n " +
                "Welcome to YalaNe7seb app \n " +
                "first add users to app by using Menu File ->controluser -> then add user \n" +
                "you can use some Keyboard shortcuts :: \n" +
                "ctrl+r   to refresh all app \n" +
                "ctrl+d   to delete selected item from database \n" +
                "ctrl+i    delete all item from database \n" +
                "ctrl+u   delete all user from database  \n" ;

        setUpStage("help");
        mainText.setText(help);
        mainText.setFont(fHont);
    }
}
