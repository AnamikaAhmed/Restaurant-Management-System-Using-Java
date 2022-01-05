package restaurant.controller.fooddelivery;

import restaurant.model.fooddelivery.FoodServiceModel;
import restaurant.model.fooddelivery.IFoodServiceModel;
import restaurant.model.utilities.IDatabase;
import restaurant.model.utilities.SQLDatabase;
import restaurant.view.fooddelivery.FoodServiceView;
import restaurant.view.fooddelivery.IFoodServiceView;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class FoodServiceTest {
    IFoodService service;
    IFoodServiceModel serviceModel;
    IFoodServiceView serviceView;
    IDatabase database;

    public FoodServiceTest() {
        service = new FoodService();
        serviceModel = new FoodServiceModel();
        serviceView = new FoodServiceView();
        database = new SQLDatabase();
        database.connect("db-5308.cs.dal.ca:3306", "CSCI5308_1_TEST", "CSCI5308_1_TEST_USER", "Uu4mw8bk3qR");
    }

    @Test
    public void selectFoodServiceTest() {
        serviceModel.getFoodService(1, service, database);

        InputStream backup = System.in;
        ByteArrayInputStream in = new ByteArrayInputStream("1".getBytes());
        System.setIn(in);
        service.selectFoodService(serviceView);
        System.setIn(backup);

        assertEquals(1, service.getServiceID(), "Service ID does not match");
    }
}
