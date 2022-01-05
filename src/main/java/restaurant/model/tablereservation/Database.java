package restaurant.model.tablereservation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Group 1
 *
 * @author: Anamika Ahmed
 * @description: Class for SQL operations
 */


public class Database implements IDatabase {

  public Connection connection = null;


  @Override
  protected void finalize() throws Throwable {
    super.finalize();
    connection.close();
  }

    /*
     Method to connect to the database
     @params: String hostName: the name of the host
              String databaseName: the name of the database
              String userName: the username for authentication
              String passWord: the password for authentication
     @return: none
     */

  @Override
  public void connect(String hostName, String databaseName,
      String userName,
      String passWord) {
    try {
      connection = DriverManager.getConnection("jdbc:mysql://" + hostName + "/" + databaseName,
          userName, passWord);
    } catch (SQLException ex) {
      ex.printStackTrace();
    }
  }

    /*
     Method to retrieve data from the database
     @params: String query: the query string to be executed
     @return: ResultSet result: the result set of the executed query
     */

  @Override
  public ResultSet retrieve(String query) {
    ResultSet result = null;
    try {
      result = connection.createStatement().executeQuery(query);
    } catch (SQLException ex) {
      ex.printStackTrace();
      System.out.println("You have an error");
    }
    return result;
  }

    /*
     Method to update the database
     @params: String query: the query string to be executed
     @return: no return
     */

  @Override
  public void update(String query) {
    try {
      connection.createStatement().executeUpdate(query);
      ;
    } catch (SQLException ex) {
      ex.printStackTrace();
      System.out.println("Error");
    }
  }
}
