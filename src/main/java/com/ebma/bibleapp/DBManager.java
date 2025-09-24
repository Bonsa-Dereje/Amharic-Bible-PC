package com.ebma.bibleapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBManager {
    private static final String DB_URL = "jdbc:sqlite:highlights.db";

    static {
        // Ensure table exists when class is loaded
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute("""
                CREATE TABLE IF NOT EXISTS highlights (
                    bookIndex INTEGER,
                    chapterIndex INTEGER,
                    line INTEGER,
                    wordDistIndex INTEGER,
                    text TEXT,
                    colorR INTEGER,
                    colorG INTEGER,
                    colorB INTEGER
                )
            """);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }
}
