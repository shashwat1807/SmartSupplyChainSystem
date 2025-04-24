package com.supplychain.services;


import java.util.ArrayList;
import java.util.List;

public class AuthService {
    private final List<UserCredential> userStore = new ArrayList<>();
    /*
     * Registers a new user.
     * @param email    unique email
     * @param password plaintext password
     * @throws AuthenticationException if email already exists
     */
    // Simple holder for one user’s credentials

    /**
     * “Encrypt” by shifting each char in the password up by (email.length + password.length)
     */
    public void register(String email, String password) throws AuthenticationException {
        // ensure email is unique
        for (UserCredential uc : userStore) {
            if (uc.email.equalsIgnoreCase(email)) {
                throw new AuthenticationException("Email already registered");
            }
        }
        // encrypt and store
        String encrypted = encrypt(email, password);
        userStore.add(new UserCredential(email, encrypted));
        System.out.printf("New user %s registered successfully!", email);
        System.out.printf("FOR DEBUG USAGE ONLY encrypted credentials: %s", encrypted);
    }
    public boolean authenticate(String email, String password) throws AuthenticationException {
        for (UserCredential uc : userStore) {
            if (uc.email.equalsIgnoreCase(email)) {
                String attempt = encrypt(email, password);
                if (uc.passwordEncrypted.equals(attempt)) {
                    System.out.printf("FOR DEBUG USAGE ONLY Email: %s, Password:%s is equal to %s and is authenticated successfully", email, password, attempt);
                    return true;
                }
                break;  // email found but password mismatch
            }
        }
        throw new AuthenticationException("Invalid email or password");
    }

    private String encrypt(String email, String password) {
        int shift = email.length() + password.length();
        char[] chars = password.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            chars[i] = (char)(chars[i] + shift);
        }
        return new String(chars);
    }

    private static class UserCredential {
        final String email;
        final String passwordEncrypted;
        UserCredential(String email, String passwordEncrypted) {
            this.email = email;
            this.passwordEncrypted = passwordEncrypted;
        }
    }
    public static class AuthenticationException extends Exception {
        public AuthenticationException(String message) {
            super(message);
        }
    }
}
