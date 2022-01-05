package restaurant.controller.fooddelivery;

import java.util.Map;

/**
 * Group 1
 * @author: Milan Ganesh Acharya
 * @description: Parent Interface for accessing the food item tables
 */
public interface IFood {
    Map<Integer, Map<String, Object>> getTable();
    void setTable(Map<Integer, Map<String, Object>> table);
}
