package com.example.jth_mathgame.mathgame;

import java.util.ArrayList;
import java.util.List;

public class MockUserDAO implements IUserDAO {
    /**
     * A static list of users to be used as a mock database.
     */
    private static final ArrayList<User> Users = new ArrayList<>();
    private static int autoIncrementedId = 0;

    public MockUserDAO() {
        // Add some initial users to the mock database
        addUser(new User("John", "Doe", "johndoe@example.com", "0423423423"));
        addUser(new User("Jane", "Doe",  "janedoe@example.com", "0423423424"));
        addUser(new User("Jay", "Doe",  "jaydoe@example.com", "0423423425"));
    }

    @Override
    public void addUser(User user) {
        user.setId(autoIncrementedId);
        autoIncrementedId++;
        Users.add(user);
    }

    @Override
    public User getUser(int id) {
        for (User user : Users) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        return new ArrayList<>(Users);
    }
}