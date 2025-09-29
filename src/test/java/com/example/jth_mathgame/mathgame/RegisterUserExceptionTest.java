package com.example.jth_mathgame.mathgame;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RegisterUserExceptionTest {

    @Test
    void message_isPreserved() {
        RegisterUserException ex = new RegisterUserException("bad input");
        assertEquals("bad input", ex.getMessage());
    }

    @Test
    void cause_isPreserved_viaInitCause() {
        IllegalArgumentException cause = new IllegalArgumentException("missing email");
        RegisterUserException ex = new RegisterUserException("wrap");
        ex.initCause(cause);
        assertSame(cause, ex.getCause());
    }
}