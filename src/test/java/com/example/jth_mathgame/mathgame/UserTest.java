package com.example.jth_mathgame.mathgame;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void constructorAndGetters_workCorrectly() {
        User u = new User(1, "Harold", "password123", "a@x.com", "0400");

        assertEquals(1, u.getId());
        assertEquals("Harold", u.getUsername());
        assertEquals("password123", u.getPassword());
        assertEquals("a@x.com", u.getEmail());
        assertEquals("0400", u.getPhone());
    }

    @Test
    void setters_updateValues() {
        User u = new User(1, "Ronaldo", "Messi", "b@x.com", "0411");

        u.setUsername("Ronaldo");
        u.setPassword("Messi");
        u.setEmail("bob@new.com");
        u.setPhone("0422");

        assertEquals("Ronaldo", u.getUsername());
        assertEquals("Messi", u.getPassword());
        assertEquals("bob@new.com", u.getEmail());
        assertEquals("0422", u.getPhone());
    }
}