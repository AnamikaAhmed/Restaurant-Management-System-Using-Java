package user.view;

/***
 * This exception will be thrown when a user inputs an invalid input
 */
public class InvalidInputException extends Exception{
  public InvalidInputException(String message) {
    super(message);
  }
}
