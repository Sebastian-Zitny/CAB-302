package com.example.jth_mathgame.mathgame;

import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserDAOFileDbTest {
    private UserDAO dao;

    @BeforeEach
    void cleanDb() throws Exception {
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

    @AfterAll
    static void optionalDeleteFileDb() throws Exception {
    }

    private static User mk(String u, String p, String e, String ph) {
        return new User(0, u, p, e, ph);
    }

    @Test
    void add_and_getUser() {
        dao.addUser(mk("alice", "pw", "a@x.com", "0401"));
        User u = dao.getUser("alice");
        assertNotNull(u);
        assertEquals("alice", u.getUsername());
        assertEquals("pw", u.getPassword());
    }

    @Test
    void getUser_notFound_returnsNull() {
        assertNull(dao.getUser("nope"));
    }

    @Test
    void getAllUsers_returnsAllInserted() {
        dao.addUser(mk("u1", "p1", "u1@x.com", "0401"));
        dao.addUser(mk("u2", "p2", "u2@x.com", "0402"));
        dao.addUser(mk("u3", "p3", "u3@x.com", "0403"));

        List<User> all = dao.getAllUsers();
        assertEquals(3, all.size());
        assertTrue(all.stream().anyMatch(u -> "u1".equals(u.getUsername())));
        assertTrue(all.stream().anyMatch(u -> "u2".equals(u.getUsername())));
        assertTrue(all.stream().anyMatch(u -> "u3".equals(u.getUsername())));
    }
}