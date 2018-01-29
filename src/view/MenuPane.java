package view;

import javafx.application.Platform;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.layout.Pane;

/**
 * this is
 * Created by mo3tamed on 1/29/18.
 */
public class MenuPane  extends Pane {

    public MenuPane() {

        MenuBar main = new MenuBar() ;

        Menu file = new Menu("_File");

        MenuItem controlUser = new MenuItem("controlUsers") ;
        MenuItem statics = new MenuItem("Statics") ;
        MenuItem exit = new MenuItem("exit") ;
        file.getItems().addAll(controlUser , new SeparatorMenuItem(),statics, new SeparatorMenuItem(), exit) ;

        Menu helpMenu = new Menu("_Help") ;
        MenuItem  about = new MenuItem("About") ;
        MenuItem help = new MenuItem("help") ;
        helpMenu.getItems().addAll( about,help ) ;

        main.getMenus().addAll(file , helpMenu) ;
        getChildren().addAll(main) ;



        //actions
        exit.setOnAction(e->{
            Platform.exit();
        });
        controlUser.setOnAction(e->{
            ControlUser.control();
        });
    }
}
