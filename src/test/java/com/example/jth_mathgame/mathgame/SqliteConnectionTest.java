package com.example.jth_mathgame.mathgame;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

class SqliteConnectionTest {

    @Test
    void getInstance_returnsSameOpenConnection() throws Exception {
        Connection c1 = SqliteConnection.getInstance();
        Connection c2 = SqliteConnection.getInstance();

        assertSame(c1, c2, "Should return the same singleton connection");
        assertFalse(c1.isClosed(), "Connection should be open");
    }

    @Test
    void connection_canExecuteSimpleSelect() throws Exception {
        try (Connection c = SqliteConnection.getInstance();
             Statement st = c.createStatement();
             ResultSet rs = st.executeQuery("SELECT 1")) {

            assertTrue(rs.next(), "Result set should have one row");
            assertEquals(1, rs.getInt(1));
        }
    }

    @Test
    void canCreateTempTable_insertAndQuery() throws Exception {
        try (Connection c = SqliteConnection.getInstance();
             Statement st = c.createStatement()) {

            st.execute("CREATE TEMP TABLE t(x INTEGER)");
            st.execute("INSERT INTO t(x) VALUES (42)");
            try (ResultSet rs = st.executeQuery("SELECT x FROM t")) {
                assertTrue(rs.next());
                assertEquals(42, rs.getInt(1));
            }
        }
    }
    @Test
    void canInsertMultipleRows_andQueryCount() throws Exception {
        try (Connection c = SqliteConnection.getInstance();
             Statement st = c.createStatement()) {

            st.execute("CREATE TEMP TABLE numbers(val INTEGER)");
            st.execute("INSERT INTO numbers(val) VALUES (1), (2), (3)");

            try (ResultSet rs = st.executeQuery("SELECT COUNT(*) FROM numbers")) {
                assertTrue(rs.next());
                assertEquals(3, rs.getInt(1), "Should have inserted 3 rows");
            }
        }
    }

    @Test
    void invalidQuery_throwsSQLException() {
        assertThrows(Exception.class, () -> {
            try (Connection c = SqliteConnection.getInstance();
                 Statement st = c.createStatement()) {
                st.executeQuery("SELECT * FROM non_existent_table");
            }
        });
    }
}
