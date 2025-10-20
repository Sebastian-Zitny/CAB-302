package com.example.jth_mathgame.mathgame;

import java.io.File;

import static org.junit.jupiter.api.Assertions.fail;

public class TestUtils {
    /**
     * Renames database so it is not modified during testing
     */
    public static void setup() {
        File originalFileName = new File("users.db");
        File tempFileName = new File("users_protect.db");

        if (originalFileName.exists()) {
            // Rename database so it is not overwritten
            if (!originalFileName.renameTo(tempFileName)) {
                // Stop testing if database cannot be renamed
                fail("Cannot rename database. Testing aborted.");
            }
        }
    }

    static void teardown() {
        File originalFileName = new File("users.db");
        File tempFileName = new File("users_protect.db");
        // Attempt to delete test database
        if (!originalFileName.setWritable(true)) {
            fail("Cannot set temporary database as writable");
        }
        if (!originalFileName.delete()) {
            fail("Cannot delete temporary database.");
        }
        // Attempt to restore
        if (!tempFileName.renameTo(originalFileName)) {
            fail("Cannot restore database.");
        }
    }
}
