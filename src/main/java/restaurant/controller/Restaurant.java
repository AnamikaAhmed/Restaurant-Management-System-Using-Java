package restaurant.controller;

import java.util.Objects;

/**
 * Group 1
 * @author: Pavan Abburi
 * @description: Class that consists of all
 *               details of a restaurant.
 */
public class Restaurant {
    private int id;                 //Restaurant ID
    private String restName;        //Restaurant Name
    private String address;         //Restaurant Address
    private int pincode;            //Restaurant PinCode
    private double rating;          //Restaurant Rating

    public Restaurant(int id, String restName, String address, int pincode, double rating) {
        this.id = id;
        this.restName = restName;
        this.address = address;
        this.pincode = pincode;
        this.rating = rating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Restaurant that = (Restaurant) o;
        return id == that.id && restName.equals(that.restName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, restName);
    }

    /*
     Setters and Getters Methods for a
     restaurant with its details in them.
     @params: String: Restaurant Name,Address
              Int:  Pincode
              Double: Rating
     @return: String: Restaurant Name,Address
              Int:  Pincode
              Double: Rating
     */
    public String getRestName() {
        return restName;
    }

    public void setRestName(String restName) {
        this.restName = restName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPincode() {
        return pincode;
    }

    public void setPincode(int pincode) {
        this.pincode = pincode;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /*
     Method to return a restaurant
     with all of its details.
     @params: none
     @return: String: Restaurant
     */
    @Override
    public String toString() {
        String text = String.format("%1d  %-30s  %-90s  %-10d  %-15f",id,restName,address.substring(0,address.length()<90?address.length()-1:89),pincode,rating);
        return text;
    }
}
