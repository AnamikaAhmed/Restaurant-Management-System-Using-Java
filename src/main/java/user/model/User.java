package user.model;

/***
 *  * Group 1
 *  * @author: Aditya Arora
 *  * @description: Model class for User
 * This is the user model to store User details.
 */
public class User implements IUser{
    private final String userName;
    private final String emailId;
    private String address;
    private String contactNumber;
    private String password;

    public void setAddress(String address) {
        this.address = address;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmailId() {
        return emailId;
    }

    public String getAddress() {
        return address;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public String getPassword() {
        return password;
    }

    public User(String userName, String emailId, String address, String contactNumber, String password) {
        this.userName = userName;
        this.emailId = emailId;
        this.address = address;
        this.contactNumber = contactNumber;
        this.password = password;
    }
}
