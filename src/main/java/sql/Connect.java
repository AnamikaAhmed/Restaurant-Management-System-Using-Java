package sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Connect implements IConnect{
  public Connection connect=null;
  Statement statement=null;
  String driverSQL = System.getenv("JDBC_DRIVER");

  // Kept for Local Testing
//  String driverSQL = "org.mariadb.jdbc.Driver";

  /***
   * openConnection will open a new connection to sql
   * @param domainName domain of our sql server
   * @param username user to log in our sql server
   * @param password password of the user
   * @return Statement to be passed for SQL Operations
   * @throws SQLException
   * @throws ClassNotFoundException
   */
  public Statement openConnection(String domainName, String username, String password) throws SQLException, ClassNotFoundException {
    try {
      Class.forName(driverSQL);
    } catch (ClassNotFoundException ex) {
      throw new ClassNotFoundException("Error connecting to jdbc " + ex);
    }
    try {
      connect = DriverManager.getConnection(domainName,username,password);
      statement = connect.createStatement();
      return statement;
    } catch (SQLException ex) {
      throw new SQLException("Error connecting to domainName: "+domainName);
    }
  }

  public void closeConnection() throws SQLException {
    connect.close();
  }
}
