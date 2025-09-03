package com.example.jth_mathgame.mathgame;

public class User {
    private int id;
    private String username;
    private String password;
    private String email;
    private String phone;

    /**
     * Represents a user in the database
     * @param id The user's id
     * @param username The user's username
     * @param password The user's password
     * @param email The user's email address
     * @param phone The user's phone
     */
    public User(int id, String username, String password, String email, String phone) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
    }

    /**
     * Creates a new user
     * @param username The user's username
     * @param password The user's password
     * @param email The user's email address
     * @param phone The user's phone
     */
    public User(String username, String password, String email, String phone) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String usersName) {
        this.username = usersName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String lastName) {
        this.password = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}