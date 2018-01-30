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
        try
        {
            statement = DbConnection.getConnection().createStatement();
            //create a table if it not exist
            statement.executeUpdate("create table if not exists  USER ( name TEXT  PRIMARY KEY  NOT NULL  )");
            statement.executeUpdate("create table if not exists  ITEM (id integer  PRIMARY KEY  , name TEXT  " +
                    " , " +
                    " description BLOB  , price integer  ,itemdate TEXT  )");
            System.out.println("created");
            statement.close();
            Home.status.setText("create database successfully ^_^ " );

            //get number of items to
            getLastId();


        }catch (SQLException ex){
            ex.printStackTrace();
        }

    }
    public static int  getLastId(){
        int n =0;
        try
        {

            Statement st= DbConnection.getConnection().createStatement() ;
            n = st.executeQuery("select max(id) from ITEM ").getInt(1) ;
            DbQueries.rowCount = n ;
            System.out.println("get n " + n );
            st.close();
        }catch (SQLException ex){
            Home.status.setText("error" + ex.toString());
        }
        return  n ;
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
                        rs.getInt("id"),
                        rs.getString("itemdate") )) ;
            }
            rs.close();
            statement.close();
            System.out.println("add All items   succussfuly ");
        }catch (SQLException s)
        {
            System.out.println(s);
        }
    return  items ;

    }

    public static ObservableList<Item>  itemsByUser(String name){

        ObservableList<Item> items = FXCollections.observableArrayList() ;
        try {

            statement = DbConnection.getConnection().createStatement() ;
           ResultSet rs =  statement.executeQuery("select * from ITEM where name = '" + name+"'" ) ;

           while(rs.next()){
               items.add(new Item(rs.getString("description") ,
                       rs.getDouble("price"),
                       rs.getString("name"),
                       rs.getInt("id"),
                       rs.getString("itemdate")));
           }

           statement.close();
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return items ;
    }
    public static void deleteItemByUser(String name) {

        try
        {
            statement = DbConnection.getConnection().createStatement() ;
            statement.executeUpdate("DELETE  FROM  ITEM where name = '" + name + "'") ;
            statement.close();
        }catch ( SQLException ex){
            ex.printStackTrace();

        }    }
    public static void deleteUser (String name ){
        try
        {
            statement = DbConnection.getConnection().createStatement() ;
            if (name == ""){  // empty string mean delete all users
                statement.executeUpdate("DELETE  from USER ");
            }else {
                statement.executeUpdate("DELETE  from USER where name = '" + name + "'");
            }
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
                statement.executeUpdate("DELETE  FROM  ITEM where id = " + id) ;
                System.out.println(id + " after  sql delete " );
                Home.status.setText("delete item  whose id " + id + "successfully ");
                statement.close();
                updateAllId(id);
            }else
            {
                statement.executeQuery("DELETE  from ITEM "); // delete all
                statement.close();
            }

        }catch (SQLException ex)
        {
            Home.status.setText(ex.toString());
        }
    }
    public static void  updateAllId(int id ){

        try
        {
            System.out.println("before  update all id");
            statement = DbConnection.getConnection().createStatement() ;
            statement.executeUpdate("update ITEM SET id = id - 1  where id >  "+id );
            System.out.println("id " + id  +"  after update fro m all id ");
            statement.close();
        }catch (SQLException ex)
        {
            ex.printStackTrace();
        }

    }

    public static void updateItem (String name ,int id , String  desc  ,double price){
        try
        {
           PreparedStatement ps = DbConnection.getConnection().
                   prepareStatement("update ITEM set  description = ? "+
                   " ,price = ? ,name = ?  where id = "+id) ;
            ps.setString( 1, desc );
           ps.setDouble(2 , price);
           ps.setString( 3 , name);
            System.out.println( ps.executeUpdate() );
            ps.close();
        }catch (SQLException ex ){
            System.out.println("uuuuu "+id );
            Home.status.setText(ex.toString());
        }
    }

    /**
     * get sum of all costs
     * @param code that specify if sum to
     *            (all) all items
     *             (user)all to specific user
     *             (month)all by month
     * @param key  a user or month
     * @return
     */
    public static  double  getSumOfPrices(String code , String key ){
        try
        {
            statement = DbConnection.getConnection().createStatement();
            if (code == "all" ){
                return  statement.executeQuery("select sum(price) from ITEM ;").getDouble(1) ;
            }else if(code == "user"){
                return statement.executeQuery("SELECT sum(price) from ITEM where name =  '" + key+"'")
                        .getDouble(1);
            }else if(code == "month "){
                return  statement.executeQuery("SELECT  sum(price) from ITEM where itemdate like '%-"+key+"-%'")
                        .getDouble(1);
            }
            statement.close();

        }catch (SQLException ex){
            ex.printStackTrace();
        }

        return  0 ;
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
            System.out.println("add All user  succussfuly ");
        }catch (SQLException s)
        {
            System.out.println(s);
        }
    return  users ;
    }
}
