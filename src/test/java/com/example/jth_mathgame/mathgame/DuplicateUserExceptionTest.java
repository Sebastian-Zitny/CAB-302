package com.example.jth_mathgame.mathgame;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DuplicateUserExceptionTest {

    @Test
    void noArgConstructor_createsInstance() {
        DuplicateUserException ex = new DuplicateUserException();
        assertNotNull(ex);
    }

    @Test
    void cause_isPreserved_viaInitCause() {
        RuntimeException cause = new RuntimeException("sql failed");
        DuplicateUserException ex = new DuplicateUserException();
        ex.initCause(cause);
        assertSame(cause, ex.getCause());
    }
}