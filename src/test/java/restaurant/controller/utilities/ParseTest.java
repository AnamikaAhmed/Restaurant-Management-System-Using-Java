package restaurant.controller.utilities;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ParseTest {
    @Test
    public void tryParseIntTest() {
        assertEquals(1, Parse.tryParseInt("1"), "failed test for positive integer");
        assertEquals(-1, Parse.tryParseInt("-1"), "failed test for negative integer");
        assertNull(Parse.tryParseInt("1.00"), "failed test for real number");
        assertNull(Parse.tryParseInt("a"), "failed test for any other value");
    }
}
