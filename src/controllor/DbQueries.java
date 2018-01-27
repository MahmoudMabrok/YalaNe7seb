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
    public  static int  rowCount = 0 ;

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
                    " description BLOB  , price integer  ,date TEXT  )");
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
            Statement st = DbConnection.getConnection().createStatement() ;
            String date = st.executeQuery("SELECT date('now')").getString(1) ;

            PreparedStatement ps =DbConnection.getConnection().prepareStatement
                    ("insert into ITEM VALUES ( ?,?, ?,?  , ?)") ;
            ps.setInt(1,id);
            ps.setString(2 ,name );
            ps.setString(3 ,desc );
            ps.setDouble(4 , price);
            ps.setString(5 ,date );
            System.out.println(ps.executeUpdate() + " ,, " ) ;
            ps.close();

            rowCount++ ;

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
                        rs.getInt("id"), rs.getString("date") )) ;
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

    public static void deleteUser (String name ){
        try
        {
            statement = DbConnection.getConnection().createStatement() ;
            statement.executeQuery("DELETE  from USER where name = '"+name + "'") ;
            statement.close();
            Home.status.setText("delete user " + name + "  successfully ");
        }catch(SQLException ex)
        {
            ex.printStackTrace();
            Home.status.setText(ex.toString());

        }

    }
    public static void deleteItem (int id ){
        try
        {

            statement = DbConnection.getConnection().createStatement() ;

            if (id >= 0 ){
                statement.executeQuery("DELETE  FROM  ITEM where id = " + id) ;
                Home.status.setText("delete item  whose id " + id + "successfully ");

            }else
            {
                statement.executeQuery("DELETE  from ITEM "); // delete all
            }
            statement.close();
            rowCount -- ;
        }catch (SQLException ex)
        {
            Home.status.setText(ex.toString());
        }

    }

    public static void updateItem (String name ,int id , String  desc  ,double price){

        try
        {
            statement = DbConnection.getConnection().createStatement() ;

        }catch (SQLException ex ){
            Home.status.setText(ex.toString());
        }


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