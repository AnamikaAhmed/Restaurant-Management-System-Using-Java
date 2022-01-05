package user.model;

/***
 * This Exception will thrown when a user inputs invalid credentials
 */
public class InvalidCredentialsException extends Exception{
  public InvalidCredentialsException(String message) {
    super(message);
  }
}
