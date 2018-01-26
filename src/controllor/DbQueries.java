package controllor;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Item;
import model.User;
import view.Home;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * this is
 * Created by mo3tamed on 1/26/18.
 */
public class DbQueries {

  private static    Statement statement ;

    public static void creatDb ()
    {
        System.out.println("before created ");
        try
        {
            statement = DbConnection.getConnection().createStatement();
            //create a table if it not exist
            statement.executeUpdate("create table if not exists  USER ( name TEXT  PRIMARY KEY  NOT NULL  )");
            statement.executeUpdate("create table if not exists  ITEM (id integer  PRIMARY KEY  , name TEXT  " +
                    " , " +
                    " description BLOB  , price integer   )");
            System.out.println("created");
            statement.close();
            Home.status.setText("create database successfully ^_^ " );


        }catch (SQLException ex){
            ex.printStackTrace();
        }

    }

    public static  void addUser(String name ){
        try {

            statement   = DbConnection.getConnection().createStatement();
            statement.executeUpdate("insert into USER VALUES ('" + name +"')" ) ;
            statement.close();

            Home.status.setText("add user  -> " +name + "  to database successfully ^_^ " );
        }catch (SQLException ex){
            ex.printStackTrace();
        }


    }
    public static void addItem (String name ,int id , String  desc  ,double price ) {


        try {
            PreparedStatement ps =DbConnection.getConnection().prepareStatement("insert into ITEM VALUES ( ?,?, ?,? )") ;
            ps.setInt(1,id);
            ps.setString(2 ,name );
            ps.setString(3 ,desc );
            ps.setDouble(4 , price);
            System.out.println(ps.executeUpdate() + ",, " ) ;
            ps.close();

            Home.status.setText("add item whose id    -> " +id + "  to database successfully ^_^ " );

        }catch (SQLException s)
        {
            s.printStackTrace();
        }
    }
    public static ObservableList<Item> getAllItems ()
    {
        ObservableList<Item> items = FXCollections.observableArrayList();
        try {
            statement  = DbConnection.getConnection().createStatement() ;
            ResultSet rs  = statement.executeQuery("select * from ITEM ") ;
            while (rs.next()) //as there is data to read
            {
                items.add(new Item( rs.getString("description"),
                        rs.getDouble("price"),
                        rs.getString("name"),
                        rs.getInt("id") )) ;
            }
            rs.close();
            statement.close();
            System.out.println("addAll succussfuly ");
        }catch (SQLException s)
        {
            System.out.println(s);
        }
    return  items ;

    }
    public static ObservableList<String> getAllUser ()
    {
        ObservableList<String > users = FXCollections.observableArrayList();
        try {
            statement  = DbConnection.getConnection().createStatement() ;
            ResultSet rs  = statement.executeQuery("select * from USER ") ;
            while (rs.next()) //as there is data to read
            {
                users.add(rs.getString("name")) ;
            }
            rs.close();
            statement.close();
            System.out.println("addAll succussfuly ");
        }catch (SQLException s)
        {
            System.out.println(s);
        }
    return  users ;

    }

}
