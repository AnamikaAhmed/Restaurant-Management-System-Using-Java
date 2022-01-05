package restaurant.model.tablereservation;

import java.sql.ResultSet;

/**
 * Group 1
 *
 * @author: Anamika Ahmed
 * @description: Parent Interface for accessing teh database methods/functions
 */

public interface IDatabase {

  void connect(String hostName, String databaseName, String userName,
      String passWord);

  ResultSet retrieve(String query);

  void update(String query);
}

