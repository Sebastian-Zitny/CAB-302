package com.example.jth_mathgame.mathgame;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.assertThrows;

class UserDAODuplicateAddTest {

    private UserDAO dao;

    @BeforeEach
    void resetDb() throws Exception {
        Connection c = SqliteConnection.getInstance();
        try (Statement st = c.createStatement()) {
            st.execute("""
                CREATE TABLE IF NOT EXISTS users (
                  UserID   INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE,
                  Username TEXT NOT NULL UNIQUE,
                  Password TEXT NOT NULL,
                  Email    TEXT,
                  Phone    TEXT
                )
            """);
            st.execute("DELETE FROM users");
        }
        dao = new UserDAO();
    }

    @Test
    void addingSameUsernameTwice_throwsDuplicateUserException() throws Exception {
        dao.addUser(new User(0, "dupe", "p1", "a@x.com", "0401"));

        assertThrows(DuplicateUserException.class, () ->
                dao.addUser(new User(0, "dupe", "p2", "b@x.com", "0402"))
        );
    }
}