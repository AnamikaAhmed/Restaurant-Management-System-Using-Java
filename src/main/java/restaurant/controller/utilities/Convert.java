package restaurant.controller.utilities;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

/**
 * Group 1
 * @author: Milan Ganesh Acharya
 * @description: This utility class is used to convert the Result Set to a Map, for easier access to each data item
 */
public class Convert {
    /*
     Method to get the restaurant service table
     @params: ResultSet: reference to the results from the database query
              Map<String, ColumnType>: the table structure of the ResultSet
     @return: Map<Integer, Map<String, Object>>: the result table in a Map format
     */
    public static Map<Integer, Map<String, Object>> convertResultSet(ResultSet results,
                                                                     Map<String, ColumnType> tableStructure) {
        Map<Integer, Map<String, Object>> table;    // Map to hold the converted table
        try {
            int i = 1;  // Variable used as row number
            table = new HashMap<>();
            while (results.next()) {
                HashMap<String, Object> row = new HashMap<>();  // Map holds each row of the table
                for (Map.Entry<String, ColumnType> entry : tableStructure.entrySet()) {
                    switch (entry.getValue()) {
                        case Integer:
                            String intValue = entry.getKey();               // Get name of the column
                            row.put(intValue, results.getInt(intValue));    // Get the value and add the item to the row
                            break;
                        case Float:
                            String floatValue = entry.getKey();
                            row.put(floatValue, results.getFloat(floatValue));
                            break;
                        case String:
                            String stringValue = entry.getKey();
                            row.put(stringValue, results.getString(stringValue));
                            break;
                    }
                }
                table.put(i++, row);    // Add the row to the table
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            table = null;
        }
        return table;
    }
}
