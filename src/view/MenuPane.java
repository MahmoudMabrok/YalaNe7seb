package view;

import controllor.DbConnection;
import controllor.DbQueries;
import javafx.application.Platform;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;


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
        file.getItems().addAll(controlUser , new SeparatorMenuItem(),statics,
                new SeparatorMenuItem(), exit) ;

        Menu helpMenu = new Menu("_Help") ;
        MenuItem  about = new MenuItem("About") ;
        MenuItem help = new MenuItem("help") ;
        helpMenu.getItems().addAll( about,help ) ;

        Menu options = new Menu("_Options") ;
        MenuItem deleteAllItem = new MenuItem("Delete Items");
        MenuItem deleteAllUser = new MenuItem("Delete Users");
        MenuItem refresh = new MenuItem("Refresh");

        options.getItems().addAll(deleteAllItem , deleteAllUser ,refresh) ;

        main.getMenus().addAll(file  , options, helpMenu) ;
        getChildren().addAll(main) ;

        Dialog dialog = new Alert(Alert.AlertType.INFORMATION);
        dialog.setHeaderText("Welcome !!! ");
        DialogPane dpane =dialog.getDialogPane();
       // dpane.getChildren().remove(dpane.getChildren().size()-1);

        //actions
        exit.setAccelerator(KeyCombination.keyCombination("Ctrl+X"));
        exit.setOnAction(e->{
            DbConnection.disconnect();
            Platform.exit();
        });

        controlUser.setAccelerator(KeyCombination.keyCombination("Ctrl+A"));
        controlUser.setOnAction(e->{
            ControlUser.control();
        });

        deleteAllItem.setAccelerator(KeyCombination.keyCombination("Ctrl+I"));
        deleteAllItem.setOnAction(e->{
            DbQueries.deleteItem(-1);
            Home.status.setText("delete All item from DataBase");
            refresh();
        });

        refresh.setAccelerator(KeyCombination.keyCombination("Ctrl+R"));
        refresh.setOnAction(e->{
            refresh();
            Home.status.setText("Refresh ");
        });

        deleteAllUser.setAccelerator(KeyCombination.keyCombination("Ctrl+U"));
        deleteAllUser.setOnAction(e->{
            DbQueries.deleteUser("all");
            refresh();
        });
        statics.setAccelerator(KeyCombination.keyCombination("Ctrl+S"));
        statics.setOnAction(e->{
            StatisticPane.showStatisticStage();
        });
        about.setAccelerator(KeyCombination.keyCombination("Ctrl+B"));
        about.setOnAction(e->{
            dialog.setTitle("About");
            dialog.setContentText(infoPane.about);
            dialog.showAndWait();
        });
        help.setAccelerator(KeyCombination.keyCombination("Ctrl+H"));
        help.setOnAction(e->{
            dialog.setTitle("Help");
            dialog.setContentText(infoPane.help);
            dialog.showAndWait();
        });
    }

    public static  void refresh(){
        RightPane.tableView.getItems().setAll(DbQueries.getAllItems()) ;
        Home.lp.cbUser.getItems().setAll(DbQueries.getAllUser()) ;
        System.out.println("DbQueries.getAllUser() = " + DbQueries.getAllUser());
       // Home.lp.cbUser.setPromptText("Enter User");
        Home.lp.setAllBlank();

    }
}
