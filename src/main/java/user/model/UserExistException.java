package user.model;

/***
 * UserExistException will be thrown when a user already exist in database
 */
public class UserExistException extends Exception{
    public UserExistException(String message) {
        super(message);
    }
}
