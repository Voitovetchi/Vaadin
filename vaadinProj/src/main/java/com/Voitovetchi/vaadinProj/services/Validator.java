package com.Voitovetchi.vaadinProj.services;

public class Validator {

    public static boolean isbnIsValid(String isbn) {
        try {
            if (isbn.length() == 10 && Long.parseLong(isbn)>0) {
                return true;
            } else {
                return false;
            }
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean priceIsValid(String price) {
        try {
            if (Double.parseDouble(price)>0) {
                return true;
            } else {
                return false;
            }
        } catch (NumberFormatException e) {
            return false;
        }
    }

}
