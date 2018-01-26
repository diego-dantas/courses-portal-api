package com.course.portal.api.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
/**
* Class to encoded e validate of password
* */
public class PasswordSecurity {

    public static String getPasswod(String password){

        if(password == null) return password;

        BCryptPasswordEncoder bCryptPassword = new BCryptPasswordEncoder();
        return bCryptPassword.encode(password);
    }

    public static boolean validatePassword(String password, String passwordEncoded){

        if(password == null || passwordEncoded == null) return false;

        BCryptPasswordEncoder bCryptPassword = new BCryptPasswordEncoder();
        return bCryptPassword.matches(password, passwordEncoded);
    }
}
