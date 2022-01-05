package restaurant.model.fooddelivery;

import restaurant.model.utilities.IDatabase;
import org.junit.jupiter.api.Test;
import restaurant.model.utilities.SQLDatabase;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;

public class FoodChargesModelTest {
    FoodChargesModel charges;
    IDatabase database;

    public FoodChargesModelTest() {
        charges = new FoodChargesModel();
        database = new SQLDatabase();
        database.connect("db-5308.cs.dal.ca:3306", "CSCI5308_1_TEST", "CSCI5308_1_TEST_USER", "Uu4mw8bk3qR");
    }

    @Test
    public void getChargesTest() {
        Map<String, Object> row = new HashMap<>();
        row.put("Takeaway", (float) 1.50);
        row.put("Delivery", (float) 2.00);
        row.put("Tax", (float) 5.00);

        charges.getCharges(1, database);
        Map<Integer, Map<String, Object>> Table = charges.getTable();
        for (Map.Entry<Integer, Map<String, Object>> entry : Table.entrySet()) {
            assertEquals(entry.getValue().get("Takeaway"), row.get("Takeaway"),
                    "Takeaway cost does not match");
            assertEquals(entry.getValue().get("Delivery"), row.get("Delivery"),
                    "Delivery cost does not match");
            assertEquals(entry.getValue().get("Tax"), row.get("Tax"),
                    "Tax % does not match");
        }
    }
}
