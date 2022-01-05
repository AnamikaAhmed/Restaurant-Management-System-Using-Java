package restaurant.controller.utilities;

/**
 * Group 1
 * @author: Milan Ganesh Acharya
 * @description: This utility class is used to convert the String value to numeric value
 */
public class Parse {
    /*
     Method converts String to Integer. Method returns null if string is not an integer
     @params: String: the number in string format, that needs to be converted
     @return: Integer: the converted number
     */
    public static Integer tryParseInt(String number) {
        try {
            return Integer.parseInt(number);
        } catch (NumberFormatException ex) {
            return null;
        }
    }
}
