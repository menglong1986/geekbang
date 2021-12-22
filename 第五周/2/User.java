package com.example.week5.two;

public class User {

    private String username;
    private Integer password;

    public User() {
    }

    public User(String username, Integer password ){
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getPassword() {
        return password;
    }

    public void setPassword(Integer password) {
        this.password = password;
    }
}
