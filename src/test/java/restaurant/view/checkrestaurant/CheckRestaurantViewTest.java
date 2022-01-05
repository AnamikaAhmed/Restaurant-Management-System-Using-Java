package restaurant.view.checkrestaurant;

import org.junit.jupiter.api.Test;
import restaurant.view.filterrestaurants.FilterRestaurantsView;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

class CheckRestaurantViewTest {

    @Test
    void readFromConsole_True() {
        CheckRestaurantView checkRestaurantView = new CheckRestaurantView();
        InputStream backup = System.in;
        ByteArrayInputStream in = new ByteArrayInputStream("5".getBytes());
        System.setIn(in);
        assertEquals("5", checkRestaurantView.readFromConsole(), "Input does not match");
        System.setIn(backup);
    }

    @Test
    void readFromConsole_False() {
        CheckRestaurantView checkRestaurantView = new CheckRestaurantView();
        InputStream backup = System.in;
        ByteArrayInputStream in = new ByteArrayInputStream("5".getBytes());
        System.setIn(in);
        assertNotEquals("7", checkRestaurantView.readFromConsole(), "Input match");
        System.setIn(backup);
    }
}