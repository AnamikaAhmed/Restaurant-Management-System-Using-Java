package restaurant.model.utilities;

import restaurant.controller.utilities.ColumnType;

import java.util.Map;

import java.sql.ResultSet;

public interface IDatabase {
    void connect(String hostName, String databaseName, String userName, String passWord);
    Map<Integer, Map<String, Object>> retrieve(String query, Map<String, ColumnType> tableStructure);
    int insertAndGetId(String query);
    int insert(String query);
    ResultSet retrieve(String query);
    void update(String query);
}
