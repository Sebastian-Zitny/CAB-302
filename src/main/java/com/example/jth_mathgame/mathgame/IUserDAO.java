package com.example.jth_mathgame.mathgame;

import java.util.List;

public interface IUserDAO {
    void addUser(User user);
    User getUser(String username);
    String[] getUserPasswordArray(String username);
    List<User> getAllUsers();
}