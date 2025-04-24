package com.supplychain;

import com.supplychain.services.AuthService;


public class Main {
    public static void main(String[] args) {
        System.out.println("Smart Supply Chain System Starting...");

        AuthService auth = new AuthService();
        try {
            auth.register("alice@example.com", "myp@ssw0rd");
            boolean ok = auth.authenticate("alice@example.com", "myp@ssw0rd");
            System.out.println("Login success? " + ok);  // prints true
        } catch (AuthService.AuthenticationException e) {
            System.err.println("Auth failed: " + e.getMessage());
        }
    }
}
