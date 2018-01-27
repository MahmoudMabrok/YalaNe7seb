package controllor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * this is
 * Created by mo3tamed on 1/26/18.
 */
public class DbConnection {

    private static Connection  con ; //static to call it from  getConnection
    private  DbConnection (){ //private to prevent make object
    }

    public static  Connection getConnection (){
        if (con == null ) // first time to connect
        {
            try{
                con = DriverManager.getConnection("jdbc:sqlite:Data.db");
            }catch (SQLException ex){
                ex.printStackTrace();
            }
        }
        return  con ;
    }
    public static  void disconnect ()
    {
        if (con != null )
            con = null ;
    }



}
