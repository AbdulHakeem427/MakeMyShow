package com.example.bookmyshowcb.utils;

import com.example.bookmyshowcb.exceptions.PasswordTooShortException;

public class UserUtils {
    public static void validatePassword(String password) throws PasswordTooShortException {
        if (password.length() < 6) {
            throw new PasswordTooShortException("Password length is short");
        }
    }
}
