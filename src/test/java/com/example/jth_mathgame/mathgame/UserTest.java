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

    @Test
    void equals_andHashCode_respectUserId() {
        User u1 = new User(1, "Pogba", "Buffon ", "c@x.com", "0433");
        User u2 = new User(1, "Dybala", "Hazard", "c@x.com", "0433");
        User u3 = new User(2, "Rashford", "Bale", "d@x.com", "0444");

        assertEquals(u1, u2);
        assertEquals(u1.hashCode(), u2.hashCode());
        assertNotEquals(u1, u3);
    }
}