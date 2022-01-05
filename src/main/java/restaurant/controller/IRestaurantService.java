package restaurant.controller;

import java.sql.SQLException;

public interface IRestaurantService {
    void service(String emailID) throws SQLException;
}
