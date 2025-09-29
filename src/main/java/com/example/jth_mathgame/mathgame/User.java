package com.example.jth_mathgame.mathgame;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;

public class User {
    private int id;
    private String username;
    private String password;
    private String email;
    private String phone;
    private int score;
    private StringProperty usernameProperty;
    private IntegerProperty scoreProperty;
    /**
     * Represents a user in the database
     * @param id The user's id
     * @param username The user's username
     * @param password The user's password
     * @param email The user's email address
     * @param phone The user's phone
     * @param score The user's score
     */
    public User(int id, String username, String password, String email, String phone,int score) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.score = score;
        this.usernameProperty = new SimpleStringProperty(username);
        this.scoreProperty = new SimpleIntegerProperty(score);
    }

    /**
     * Creates a new user
     * @param username The user's username
     * @param password The user's password
     * @param email The user's email address
     * @param phone The user's phone
     */
    public User(String username, String password, String email, String phone, int score) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.score = score;
        this.usernameProperty = new SimpleStringProperty(username);
        this.scoreProperty = new SimpleIntegerProperty(score);
    }

    public User(int userID, String username, String email, String phone) {
        this.id = userID;
        this.username = username;
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

    public int getScore() {return score;}

    public void setScore(int score) {this.score = score;}


    public StringProperty getUsernameProperty() {
        return usernameProperty;
    }

    public void setUsernameProperty() {
        this.usernameProperty.set(username);
    }

    public IntegerProperty getScoreProperty() {
        return scoreProperty;
    }

    public void setScoreProperty() {
        this.scoreProperty.set(score);
    }
}