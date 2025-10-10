package com.ebma.bibleapp;

import java.sql.*;

public class NotesDatabase {

    private static final String DB_URL = "jdbc:sqlite:notesNJournals.db";

    /**
     * Check if a note exists for the given bookChapterIndex
     */
    public static boolean noteExists(String bookChapterIndex) {
        String query = "SELECT COUNT(*) FROM notesReflections WHERE bookChapterIndex = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, bookChapterIndex);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) return rs.getInt(1) > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Load note text if exists
     */
    public static String loadNote(String bookChapterIndex) {
        String query = "SELECT note FROM notesReflections WHERE bookChapterIndex = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, bookChapterIndex);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) return rs.getString("note");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ""; // empty if not found
    }

    /**
     * Save or update a note
     */
    public static void saveNote(String bookChapterIndex, String noteText) {
        String sql = "INSERT INTO notesReflections(bookChapterIndex, note) " +
                     "VALUES (?, ?) " +
                     "ON CONFLICT(bookChapterIndex) DO UPDATE SET note = excluded.note";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, bookChapterIndex);
            stmt.setString(2, noteText);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
