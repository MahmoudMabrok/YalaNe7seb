import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * this is
 * Created by mo3tamed on 1/21/18.
 */

public class DataBase {

   private Connection con  ;
   private  Statement st  ;
    public DataBase() {

        createDB();
    }

    public void  connectDb ()
   {

       try {

           con = DriverManager.getConnection("jdbc:sqlite:Data.db");
           System.out.println("comnnected ");

       }catch (SQLException s ){
           System.out.println(s);
       }
   }
   public void disconnect (){
       try {
           con.close();
       }catch (SQLException s){
           System.out.println(s);
       }

   }

    public void createDB() {
        try {
            con = DriverManager.getConnection("jdbc:sqlite:Data.db");
            st = con.createStatement();
            //create a table if it not exist
            st.executeUpdate("create table if not exists  USER ( name TEXT  PRIMARY KEY  NOT NULL  )");
            st.executeUpdate("create table if not exists  ITEM (id integer  PRIMARY KEY  , name TEXT  " +
                    " , " +
                    " description BLOB  , price integer   )");
            System.out.println("created");
            st.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }


   public void  addUser (String name ){

       try {
          // connectDb();

           st  = con.createStatement();
           st.executeUpdate("insert into USER VALUES ('" + name +"')" ) ;
           st.close();
          // disconnect();
       }catch (SQLException s)
       {
           System.out.println(s);
       }
   }

   public void  addItem (String name ,int id , String  desc  ,double price ){


       try {
           PreparedStatement ps =con.prepareStatement("insert into ITEM VALUES ( ?,?, ?,? )") ;
           ps.setInt(1,id);
           ps.setString(2 ,name );
           ps.setString(3 ,desc );
           ps.setDouble(4 , price);
           System.out.println(ps.executeUpdate() + ",, " ) ;
           ps.close();

          // disconnect();
       }catch (SQLException s)
       {
           s.printStackTrace();
       }
   }

  /* public void addAll (ObservableList<Item> items ){
       items.clear();
       try {
       st = con.createStatement() ;
       ResultSet rs  = st.executeQuery("select * from ITEM ") ;

       while (rs.next())
       {
           items.add(new Item( rs.getString("description"),
                   rs.getDouble("price"),
                   rs.getString("name"),
                   rs.getInt("id") )) ;
       }
       rs.close();
       st.close();
           System.out.println("addAll");
       }catch (SQLException s)
       {
           System.out.println(s);
       }
   }*/
   public ArrayList<String> getUsers (){
       ArrayList<String> us = new ArrayList<>() ;

       try {
           st = con.createStatement() ;
           ResultSet rs  = st.executeQuery("select * from USER ") ;

           while (rs.next())
           {
              us.add(rs.getString("name"));
           }
           rs.close();
           st.close();
           System.out.println("getUsers");
       }catch (NullPointerException s ){
           System.out.println(s);
       }
       catch (SQLException s)
       {
           System.out.println(s);
       }

       return us ;
   }


}
