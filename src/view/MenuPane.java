package view;

import controllor.DbConnection;
import controllor.DbQueries;
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

        Menu options = new Menu("_Options") ;
        MenuItem deleteAllItem = new MenuItem("Delete Items    ctrl+i ");
        MenuItem deleteAllUser = new MenuItem("Delete Users   ctrl+u ");
        MenuItem refresh = new MenuItem("Refresh ctrl+r");


        options.getItems().addAll(deleteAllItem , deleteAllUser ,refresh) ;

        main.getMenus().addAll(file  , options, helpMenu) ;
        getChildren().addAll(main) ;



        //actions
        exit.setOnAction(e->{
            DbConnection.disconnect();
            Platform.exit();
        });
        controlUser.setOnAction(e->{
            ControlUser.control();
        });

        deleteAllItem.setOnAction(e->{
            DbQueries.deleteItem(-1);
            Home.status.setText("delete All item from DataBase");
            refresh();
        });

        refresh.setOnAction(e->{
            refresh();
            Home.status.setText("Refresh ");
        });

        deleteAllUser.setOnAction(e->{
            DbQueries.deleteUser("all");
            refresh();
        });
        statics.setOnAction(e->{
            StatisticPane.showStatisticStage();
        });
    }

    public static  void refresh(){
        RightPane.tableView.getItems().setAll(DbQueries.getAllItems()) ;
        Home.lp.cbUser.getItems().setAll(DbQueries.getAllUser()) ;
        System.out.println("DbQueries.getAllUser() = " + DbQueries.getAllUser());
       // Home.lp.cbUser.setValue("");
        Home.lp.cbUser.setPromptText("Enter User");
        Home.lp.setAllBlank();

    }
}
