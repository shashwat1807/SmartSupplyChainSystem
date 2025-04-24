package com.supplychain.models;
import java.util.*;

public abstract class User {
  protected Integer id;
  protected String name;
  protected String role;
  protected boolean loggedIn = false;

  public User(Integer id, String name, String role) {
    this.id = id;
    this.name = name;
    this.role = role;
  }
  public void login() {
    loggedIn = true;
    System.out.println(name + " logged in.");
  }
  public void logout() {
    loggedIn = false;
    System.out.println(name + " logged out.");
  }

  public boolean isLoggedIn() {
    return loggedIn;
  }
}
