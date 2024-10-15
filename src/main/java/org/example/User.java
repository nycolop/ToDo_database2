package org.example;

public class User {
  private int id;
  private String name;
  private String lastName;
  private String nickName;
  private String password;

  public User(int id, String name, String lastName, String nickName, String password) {
    this.id = id;
    this.name = name;
    this.lastName = lastName;
    this.nickName = nickName;
    this.password = password;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getLastName() {
    return lastName;
  }

  public String getNickName() {
    return nickName;
  }

  public String getPassword() {
    return password;
  }
}
