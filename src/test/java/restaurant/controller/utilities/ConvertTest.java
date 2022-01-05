package restaurant.controller.utilities;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import restaurant.model.utilities.IDatabase;
import restaurant.model.utilities.SQLDatabase;

import static org.junit.jupiter.api.Assertions.*;

public class ConvertTest {
    IDatabase database;

    public ConvertTest() {
        database = new SQLDatabase();
        database.connect("db-5308.cs.dal.ca:3306", "CSCI5308_1_TEST", "CSCI5308_1_TEST_USER", "Uu4mw8bk3qR");
    }

    @Test
    public void convertResultSetTest() {
        Map<String, ColumnType> tableStructure = new HashMap<>();
        tableStructure.put("ServiceID", ColumnType.Integer);
        tableStructure.put("Type", ColumnType.String);

        Map<Integer, Map<String, Object>> table = new HashMap<>();
        Map<String, Object> row = new HashMap<>();
        row.put("ServiceID", 1);
        row.put("Type", "Take-Out");
        table.put(1, row);

        row = new HashMap<>();
        row.put("ServiceID", 2);
        row.put("Type", "Delivery");
        table.put(2, row);

        row = new HashMap<>();
        row.put("ServiceID", 3);
        row.put("Type", "Dine-In");
        table.put(3, row);

        for (Map.Entry<Integer, Map<String, Object>> entry : database.retrieve("select * from available_service",
                tableStructure).entrySet()) {
            assertEquals(entry.getValue().get("ServiceID"), table.get(entry.getKey()).get("ServiceID"),
                    "Service ID does not match");
            assertEquals(entry.getValue().get("Type"), table.get(entry.getKey()).get("Type"),
                    "Type does not match");
        }
    }
}
