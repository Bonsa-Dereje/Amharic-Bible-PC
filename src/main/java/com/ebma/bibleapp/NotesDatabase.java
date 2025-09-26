package com.ebma.bibleapp;

import java.sql.*;

public class NotesDatabase {

    private static final String DB_URL = "jdbc:sqlite:notesNJournals.db";


    /**
     * Check if a note exists for the given bookChapterIndex
     * @param bookIndex currentBookIndex
     * @param chapterIndex currentChapterIndex
     * @return true if exists, false otherwise
     */
    public static boolean noteExists(int bookIndex, int chapterIndex) {
        int bookChapterIndex = Integer.parseInt("" + bookIndex + chapterIndex); // combine indices
        String query = "SELECT COUNT(*) FROM notesReflections WHERE bookChapterIndex = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, bookChapterIndex);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0; // >0 means note exists
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Load note text if exists
     */
    public static String loadNote(int bookIndex, int chapterIndex) {
        int bookChapterIndex = Integer.parseInt("" + bookIndex + chapterIndex);
        String query = "SELECT note FROM notesReflections WHERE bookChapterIndex = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, bookChapterIndex);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getString("note");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ""; // empty if not found
    }
}
