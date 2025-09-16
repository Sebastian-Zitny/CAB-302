package com.example.jth_mathgame.mathgame;

import java.util.List;

public interface IUserDAO {
    void addUser(User user);
    User getUser(String username);
    List<User> getAllUsers();
    void setScore(int Id, int score);
    int getScore(int Id);
}