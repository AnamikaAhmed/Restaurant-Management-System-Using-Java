package restaurant.controller.orderhistory;

import java.util.Map;

/**
 * Group 1
 * @author: Milan Ganesh Acharya
 * @description: Interface for accessing the overall history menu
 */
public interface IOrderHistory {
    void setTable(Map<Integer, Map<String, Object>> table);
    Map<Integer, Map<String, Object>> getTable();
    void setOrder(Map<Integer, Map<String, Object>> order);
    Map<Integer, Map<String, Object>> getOrder();
    void historyMenu(String userID);
}
