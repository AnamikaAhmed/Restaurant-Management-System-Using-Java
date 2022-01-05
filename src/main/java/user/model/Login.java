package user.model;

import sql.Connect;
import sql.Operations;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 * Group 1
 * @author: Aditya Arora
 * @description: Model class for User Login
 */
public class Login {
  /***
   * This is Data Layer method for logging in a user
   * @param email Email passed by controller to be verified
   * @param password Password associated with the email id sent by controller
   * @return Returns the email id as username
   * @throws InvalidCredentialsException
   */
  public String login(String email,String password) throws InvalidCredentialsException {
    ResultSet resultSet;
    String userName = null;
    String tableName = "user";
    Connect sqlConnection = new Connect();
    try {
      Statement statement = sqlConnection.openConnection(System.getenv("DOMAIN_NAME"),System.getenv("USER_NAME"),System.getenv("PASSWORD"));
      // Statement Local and JUNIT Testing
//      Statement statement = sqlConnection.openConnection("jdbc:mysql://db-5308.cs.dal.ca:3306/CSCI5308_1_DEVINT", "CSCI5308_1_DEVINT_USER", "B6D4tje9aCR");
//      Statement statement = sqlConnection.openConnection("jdbc:mariadb://35.200.231.231/restaurant", "root", "Sobha@09");
      String selectQuery = "SELECT userName FROM " + tableName + " WHERE email = \"" + email + "\" and userPassword = \"" + password + "\";";
      Operations sqlOperations = new Operations();
      resultSet = sqlOperations.SelectQueryExecuteQuery(selectQuery, statement);
      if (resultSet.next()) {
        userName = resultSet.getString("userName");
      }
      sqlConnection.closeConnection();
    } catch (SQLException | ClassNotFoundException e){
      throw new InvalidCredentialsException("Incorrect Email ID or Password "+e);
    }

    return userName;
  }

}
