package com.example.laptop.Unit;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Data {
    public static boolean checkEmailForValidity(String email) {

        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";
        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();

    }
    public static String emailStringChecker(String email) {
        return null;
    }

}
