package user.model;

public interface IUser {
  void setAddress(String address);
  void setContactNumber(String contactNumber);
  void setPassword(String password);
  String getUserName();
  String getEmailId();
  String getAddress();
  String getContactNumber();
  String getPassword();
}
