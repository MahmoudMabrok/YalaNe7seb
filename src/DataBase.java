import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * this is
 * Created by mo3tamed on 1/21/18.
 */
public class DataBase {

   private Connection con  ;

   public void  connectDb ()
   {

       try {

           con = DriverManager.getConnection("jdbc:Data.db");
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


   public void  addUser (String name ){

       try {
           connectDb();

           Statement st = con.createStatement();
           st.executeQuery("insert into USERS VALUES ('" + name +"')" ) ;
           st.close();
           disconnect();
       }catch (SQLException s)
       {
           System.out.println(s);
       }
   }


}
