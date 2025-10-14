package com.ebma.bibleapp;

import java.io.*;
import java.util.*;
import com.google.gson.*;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.commons.io.FileUtils;

public class nlser {

    private static final String[] allBooksEnglish = {
        "Genesis", "Exodus", "Leviticus", "Numbers", "Deuteronomy",
        "Joshua", "Judges", "Ruth", "1 Samuel", "2 Samuel",
        "1 Kings", "2 Kings", "1 Chronicles", "2 Chronicles", "Ezra",
        "Nehemiah", "Esther", "Job", "Psalms", "Proverbs",
        "Ecclesiastes", "Song of Solomon", "Isaiah", "Jeremiah",
        "Lamentations", "Ezekiel", "Daniel", "Hosea", "Joel",
        "Amos", "Obadiah", "Jonah", "Micah", "Nahum",
        "Habakkuk", "Zephaniah", "Haggai", "Zechariah", "Malachi",
        "Matthew", "Mark", "Luke", "John", "Acts",
        "Romans", "1 Corinthians", "2 Corinthians", "Galatians",
        "Ephesians", "Philippians", "Colossians", "1 Thessalonians",
        "2 Thessalonians", "1 Timothy", "2 Timothy", "Titus",
        "Philemon", "Hebrews", "James", "1 Peter", "2 Peter",
        "1 John", "2 John", "3 John", "Jude", "Revelation"
    };

    public static void main(String[] args) {
        // Source of all numbered folders
        String sourceBase = "src\\main\\resources\\files\\books\\eng";
        // Destination folder for JSON
        String outputBase = "nlser";

        File sourceDir = new File(sourceBase);
        File outputDir = new File(outputBase);
        if (!outputDir.exists()) outputDir.mkdirs();

        List<Map<String, String>> allChapters = new ArrayList<>();

        try {
            File[] bookFolders = sourceDir.listFiles(File::isDirectory);
            if (bookFolders == null) {
                System.out.println(" No folders found in: " + sourceBase);
                return;
            }

            // Sort folders numerically (in case folder names are 1, 2, 3...)
            Arrays.sort(bookFolders, Comparator.comparing(File::getName, Comparator.naturalOrder()));

            for (int i = 0; i < bookFolders.length && i < allBooksEnglish.length; i++) {
                File folder = bookFolders[i];
                String bookName = allBooksEnglish[i];
                System.out.println(" Processing " + bookName + " (" + folder.getName() + ")");

                List<Map<String, String>> bookChapters = new ArrayList<>();

                for (File pdf : FileUtils.listFiles(folder, new String[]{"pdf"}, true)) {
                    String chapterName = pdf.getName().replace(".pdf", "");
                    String text = extractTextFromPDF(pdf);

                    Map<String, String> entry = new LinkedHashMap<>();
                    entry.put("book", bookName);
                    entry.put("chapter", chapterName);
                    entry.put("text", text);
                    bookChapters.add(entry);
                    allChapters.add(entry);
                }

                // Save individual book JSON
                saveBookJson(bookChapters, bookName, outputDir);
            }

            // Save combined JSON (all books)
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            File allFile = new File(outputDir, "contentExtr.json");
            try (FileWriter writer = new FileWriter(allFile)) {
                gson.toJson(allChapters, writer);
            }

            System.out.println("\n All done! JSONs saved to: " + outputDir.getAbsolutePath());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String extractTextFromPDF(File file) {
        try (PDDocument doc = PDDocument.load(file)) {
            PDFTextStripper stripper = new PDFTextStripper();
            return stripper.getText(doc).trim();
        } catch (Exception e) {
            System.err.println("⚠️ Error reading " + file.getName() + ": " + e.getMessage());
            return "";
        }
    }

    private static void saveBookJson(List<Map<String, String>> bookData, String bookName, File outputDir) {
        try {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            File outFile = new File(outputDir, bookName.replace(" ", "_") + ".json");
            try (FileWriter writer = new FileWriter(outFile)) {
                gson.toJson(bookData, writer);
            }
            System.out.println("    Saved " + bookName + ".json");
        } catch (Exception e) {
            System.err.println(" Failed to save JSON for " + bookName);
        }
    }
}
