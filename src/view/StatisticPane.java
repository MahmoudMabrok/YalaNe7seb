package view;

import controllor.DbQueries;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.List;

/**
 * this is
 * Created by mo3tamed on 1/30/18.
 */
public class StatisticPane {


    public static void showStatisticStage() {

        Stage stage = new Stage();
        VBox main = new VBox(15);
        main.setAlignment(Pos.CENTER);
        main.setPadding(new Insets(15));
        main.setStyle("-fx-background-color: aquamarine;" +
                "-fx-border-color: burlywood;" +
                "-fx-border-width:8px");

        String[] options = new String[]{"Total Cost", "cost of User",
                "cost of month", "cost from date",
                "Number of items"};
        ComboBox<String> choices = new ComboBox<>();
        choices.getItems().setAll(Arrays.asList(options));
        Text welcome = new Text("Welecome to Statistic Page ^_^");
        Text hint = new Text("Enter user/month");
        TextField input = new TextField();
        TextArea area = new TextArea();
        area.setEditable(false);
        ScrollPane scrol = new ScrollPane(area);
        main.getChildren().addAll(welcome, choices, hint, input, scrol);

        //actions
        choices.setOnAction(e -> {
            switch (choices.getValue()) {
                case "Total Cost":
                    double sum = DbQueries.getSumOfPrices("all", "");
                    //int count = DbQueries.getAllUser().size();
                    input.setVisible(false);
                    hint.setVisible(false);
                    area.setText(allPayments(sum));
                    /*
                    area.setText("Total Cost is   ::\t\t " + sum + "\n  " +
                            "each user of  " + count + " users  should pay  ::  " + (sum / count));
                    */
                    break;
                case "cost of User":
                    input.setVisible(true);
                    hint.setVisible(true);
                    hint.setText("Now enter user  then ENTER ");
                    input.setOnAction(ev -> {
                        area.setText("all Payment  of User " +
                                input.getText() + " is \t\t" +
                                DbQueries.getSumOfPrices("user", input.getText()));
                    });
                    break;
                case  "" :


                default:
                    area.setText("");
            }

        });

        stage.setScene(new Scene(main));
        stage.setTitle("Statistics  ");
        stage.setResizable(false);
        stage.show();
    }

    /**
     * return a string contain  Total payment
     * and each user payment and how mush he should pay
     * @param sum  sum of all payment
     * @return this string
     */
    public static String allPayments(double sum) {
        StringBuilder sb = new StringBuilder();
        sb.append("Total Payment is " + sum + "\n") ;
        double userPay =0 ;
        List<String> users = DbQueries.getAllUser(); //get list of users
        for (String e : users) {
            userPay =  DbQueries.getSumOfPrices("user" , e) ;  //get Payment of this user
            //sb.append(e + "\t");
            sb.append(String.format( "%-18s%-10s %5.2f" ,e , "pay" , userPay ));
            //sb.append("has paid  " + userPay) ;
            //sb.append(String.format("%-15s%f" , "pays" , userPay)) ;
            sb.append( " so he will pay  " +(int)( ((sum /users.size()) - userPay  )*100)/100.00 +
                    "  ^_^ \n") ; //calculate
        }
        return sb.toString();
    }
}
