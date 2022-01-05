package user.model;

import sql.Connect;
import sql.Operations;
import java.sql.SQLException;
import java.sql.Statement;
/**
 * Group 1
 * @author: Aditya Arora
 * @description: Model class for User Password Reset
 */

public class Reset implements IReset{
  String tableName = "user";

  /***
   * replacePassword will replace old password with new password
   * @param email email of which we have to change the password
   * @param oldPassword oldPassword to be replaced
   * @param newPassword newPassword to be replaced with
   * @return true if operations is successful
   * @throws SQLException
   */
  public Boolean replacePassword(String email, String oldPassword, String newPassword) throws SQLException {
    Connect sqlConnection = new Connect();
    try{
      Statement statement = sqlConnection.openConnection(System.getenv("DOMAIN_NAME"),System.getenv("USER_NAME"),System.getenv("PASSWORD"));
      // Statement Local and JUNIT Testing
//      Statement statement = sqlConnection.openConnection("jdbc:mysql://db-5308.cs.dal.ca:3306/CSCI5308_1_DEVINT", "CSCI5308_1_DEVINT_USER", "B6D4tje9aCR");
//      Statement statement = sqlConnection.openConnection("jdbc:mysql://35.200.231.231/restaurant", "root", "Sobha@09");
      String updateQuery = "UPDATE " + tableName + " SET userPassword = \"" + newPassword + "\" WHERE userPassword = \"" + oldPassword + "\" AND email = \"" + email + "\";";
      Operations sqlOperations = new Operations();
      sqlOperations.insertQueryExecute(updateQuery, statement);
      sqlConnection.closeConnection();
    } catch (SQLException | ClassNotFoundException throwable) {
      throw new SQLException("Error replacing password " + throwable);
    }

    return true;
  }

  /***
   * changePassword will change the password of the given email
   * @param email email of which we have to change the password
   * @param newPassword newPassword to be replaced with
   * @return true if operations is successful
   * @throws SQLException
   */
  public Boolean changePassword(String email, String newPassword) throws SQLException {
    Connect sqlConnection = new Connect();
    try{
      Statement statement = sqlConnection.openConnection(System.getenv("DOMAIN_NAME"),System.getenv("USER_NAME"),System.getenv("PASSWORD"));
      // Statement Local and JUNIT Testing
//      Statement statement = sqlConnection.openConnection("jdbc:mysql://db-5308.cs.dal.ca:3306/CSCI5308_1_DEVINT", "CSCI5308_1_DEVINT_USER", "B6D4tje9aCR");
//      Statement statement = sqlConnection.openConnection("jdbc:mysql://35.200.231.231/restaurant", "root", "Sobha@09");
      String updateQuery = "UPDATE " + tableName + " SET userPassword = \"" + newPassword + "\" WHERE email = \"" + email + "\";";
      Operations sqlOperations = new Operations();
      sqlOperations.insertQueryExecute(updateQuery, statement);
      sqlConnection.closeConnection();
    } catch (SQLException | ClassNotFoundException throwable) {
      throw new SQLException("Error in changing password " + throwable);
    }

    return true;
  }
}
