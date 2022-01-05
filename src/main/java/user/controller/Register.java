package user.controller;

import user.model.User;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 * Group 1
 * @author: Aditya Arora
 * @description: Controller class for User Registration
 */
public class Register {

    public final ArrayList<User> users= new ArrayList<>();
    /***
     *
     * @param userName userName of the user
     * @param address user's address
     * @param email user's Email id
     * @param contactNumber user's contact number
     * @param password user's password
     * @return email as primary key to be used for further operations
     * @throws NoSuchAlgorithmException
     */
    public String register(String userName, String address,
                           String email, String contactNumber, String password)
            throws NoSuchAlgorithmException, SQLException, ClassNotFoundException {

        user.model.Register registerData = new user.model.Register();
        if(!registerData.userExist(email)){
            // Hashing the password before storing
            HashPassword hp = new HashPassword();
            String hashedPassword = hp.hashPassword(password);
            registerData.register(userName,address,email,contactNumber,hashedPassword);  // Store data into DB
        }

        return email;
    }

}
