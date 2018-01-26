package controllor;

import view.Home;

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


        }catch (SQLException ex){
            ex.printStackTrace();
        }

    }

    public static  void addUser(String name ){
        try {

            statement   = DbConnection.getConnection().createStatement();
            statement.executeUpdate("insert into USER VALUES ('" + name +"')" ) ;
            statement.close();
        }catch (SQLException ex){
            ex.printStackTrace();
        }


    }
}
