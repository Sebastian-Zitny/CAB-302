package com.example.jth_mathgame.mathgame;

import java.util.ArrayList;
import java.util.List;

public interface IUserDAO {
    void addUser(User user) throws DuplicateUserException;
    User getUser(String username);
    ArrayList<byte[]> getUserPasswordArray(String username);
    List<User> getAllUsers();
}