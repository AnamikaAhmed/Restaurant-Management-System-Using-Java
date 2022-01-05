package user.model;

import sql.Connect;
import sql.Operations;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 * Group 1
 * @author: Aditya Arora
 * @description: Model class for User Registration
 */

public class Register implements IRegister {
  String tableName = "user";
  ResultSet resultSet = null;
  String userName = null;

  /***
   * Data Layer method for Registering User
   * @param userName Name of the user
   * @param address Address of our user
   * @param email Email of the user
   * @param contactNumber User's Contact Number
   * @param password Password provided by the user
   * @return Status of registration
   * @throws SQLException
   * @throws ClassNotFoundException
   */
  public Boolean register(String userName, String address,
                          String email, String contactNumber, String password) throws SQLException, ClassNotFoundException {
      Connect sqlConnection = new Connect();
    Statement statement = sqlConnection.openConnection(System.getenv("DOMAIN_NAME"),System.getenv("USER_NAME"),System.getenv("PASSWORD"));
    // Statement Local and JUNIT Testing
//    Statement statement = sqlConnection.openConnection("jdbc:mysql://db-5308.cs.dal.ca:3306/CSCI5308_1_DEVINT", "CSCI5308_1_DEVINT_USER", "B6D4tje9aCR");
//    Statement statement = sqlConnection.openConnection("jdbc:mysql://35.200.231.231/restaurant", "root", "Sobha@09");
      String insertQuery = "INSERT INTO " + tableName + " VALUES(\"" + email + "\",\"" + userName + "\",\"" + password + "\",\"" + address + "\",\"" + contactNumber + "\");";
      Operations sqlOperations = new Operations();
      sqlOperations.insertQueryExecute(insertQuery, statement);
      sqlConnection.closeConnection();

      return true;
  }

  /***
   * This Method checks if user Exist in our Database or not
   * @param email Email ID of the user
   * @return True if user exist in out database
   * @throws SQLException
   */
  public Boolean userExist(String email) throws SQLException {

    Connect sqlConnection = new Connect();
    try {
      Statement statement = sqlConnection.openConnection(System.getenv("DOMAIN_NAME"),System.getenv("USER_NAME"),System.getenv("PASSWORD"));
      // Statement Local and JUNIT Testing
//      Statement statement = sqlConnection.openConnection("jdbc:mysql://db-5308.cs.dal.ca:3306/CSCI5308_1_DEVINT", "CSCI5308_1_DEVINT_USER", "B6D4tje9aCR");
//      Statement statement = sqlConnection.openConnection("jdbc:mysql://35.200.231.231/restaurant", "root", "Sobha@09");
      String selectQuery = "SELECT userName FROM " + tableName + " WHERE email = \"" + email + "\";";
      Operations sqlOperations = new Operations();
      resultSet = sqlOperations.SelectQueryExecuteQuery(selectQuery, statement);

      while (resultSet.next()) {
        userName = resultSet.getString("userName");
      }
      sqlConnection.closeConnection();
    } catch (Exception e){
      throw new SQLException("Error Connecting to SQL "+ e);
    }

    return userName != null;
  }
}
