

package com.ebma.bibleapp;



import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import java.awt.*;

import javax.swing.text.Highlighter;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.BadLocationException;

import java.io.*;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;


import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.Set;
import java.util.TreeSet;

import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.event.DocumentListener;
import javax.swing.event.DocumentEvent;

import java.nio.file.Files;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.InputEvent;

import javax.swing.text.BadLocationException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import java.util.Map;
import java.util.HashMap;


import com.ebma.bibleapp.DBManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Collections;
import java.util.Comparator;

import java.util.HashSet;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;

import java.net.URL;

import java.sql.DriverManager;
import java.util.Random;
import java.sql.Statement;
import java.util.LinkedHashSet;


import java.util.stream.Collectors;

import javax.swing.JEditorPane;



import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import java.awt.image.BufferedImage;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;



public class mainWindow extends javax.swing.JFrame {

    private boolean isBoldActive = true;
    
    private boolean highlighterActive = false;  
    private Color currentHighlightColor = null;  
    
    
    private String[] allBooks = {
        "ኦሪት ዘፍጥረት", "ኦሪት ዘጸአት", "ኦሪት ዘሌዋውያን", "ኦሪት ዘኍልቍ", "ኦሪት ዘዳግም", 
        "መጽሐፈ ኢያሱ ወልደ ነዌ", "መጽሐፈ መሣፍንት", "መጽሐፈ ሩት", "መጽሐፈ ሳሙኤል ቀዳማዊ", 
        "መጽሐፈ ሳሙኤል ካልዕ", "መጽሐፈ ነገሥት ቀዳማዊ", "መጽሐፈ ነገሥት ካልዕ", 
        "መጽሐፈ ዜና መዋዕል ቀዳማዊ", "መጽሐፈ ዜና መዋዕል ካልዕ", "መጽሐፈ ዕዝራ", 
        "መጽሐፈ ነህምያ", "መጽሐፈ አስቴር", "መጽሐፈ ኢዮብ", "መዝሙረ ዳዊት", "መጽሐፈ ምሳሌ", 
        "መጽሐፈ መክብብ", "መኃልየ መኃልይ ዘሰሎሞን", "ትንቢተ ኢሳይያስ", "ትንቢተ ኤርምያስ", 
        "ሰቆቃው ኤርምያስ", "ትንቢተ ሕዝቅኤል", "ትንቢተ ዳንኤል", "ትንቢተ ሆሴዕ", "ትንቢተ አሞጽ", 
        "ትንቢተ ሚክያስ", "ትንቢተ ኢዮኤል", "ትንቢተ አብድዩ", "ትንቢተ ዮናስ", "ትንቢተ ናሆም", 
        "ትንቢተ ዕንባቆም", "ትንቢተ ሶፎንያስ", "ትንቢተ ሐጌ", "ትንቢተ ዘካርያስ", "ትንቢተ ሚልክያስ", 
        "የማቴዎስ ወንጌል", "የማርቆስ ወንጌል", "የሉቃስ ወንጌል", "የዮሐንስ ወንጌል", "የሐዋርያት ሥራ", 
        "ወደ ሮሜ ሰዎች", "1ኛ ወደ ቆሮንቶስ ሰዎች", "2ኛ ወደ ቆሮንቶስ ሰዎች", "ወደ ገላትያ ሰዎች", 
        "ወደ ኤፌሶን ሰዎች", "ወደ ፊልጵስዩስ ሰዎች", "ወደ ቆላስይስ ሰዎች", "1ኛ ወደ ተሰሎንቄ ሰዎች", 
        "2ኛ ወደ ተሰሎንቄ ሰዎች", "1ኛ ወደ ጢሞቴዎስ", "2ኛ ወደ ጢሞቴዎስ", "ወደ ቲቶ", "ወደ ፊልሞና", 
        "ወደ ዕብራውያን", "1ኛ የጴጥሮስ መልእክት", "2ኛ የጴጥሮስ መልእክት", "1ኛ የዮሐንስ መልእክት", 
        "2ኛ የዮሐንስ መልእክት", "3ኛ የዮሐንስ መልእክት", "የያዕቆብ መልእክት", "የይሁዳ መልእክት", "የዮሐንስ ራእይ"

    };
    
    private boolean verseChooserInitialized = false;
    
    public int currentBookIndex;
    public int currentChapterIndex;
    private List<Integer> randomNums = get12RandomBookNumbers();
    
    private int currentSelected;
    
    
    private int loadedBook;
    
    
    private int currentScrollState = 0;
    
    private int overhead;
    private boolean firstScrollWrite = true;
    
    // Declare this at the top of your class (outside the method)
    private int itr = 1;
    private Clip cafeClip;
    private Clip treeClip;
    private Clip rainClip;
    private Clip lastPlayedClip = null; // remembers the last active ambience
    private long lastClipPosition = 0;  // remembers frame position for resume
    private String lastTheme = "";  
    //private int baseFontSize = 20;
    //private float volumePercent = 50; 
    private boolean isDarkMode = false;

    
    public mainWindow() {
        
        setUndecorated(true);  
        initComponents();
        
         
        setLocationRelativeTo(null);
        
        boldBtn.setBackground(Color.LIGHT_GRAY);
        
        testamentChooser.addActionListener(e -> updateBookChooser());
        bookChooser.addActionListener(e -> updateChapterChooser());

        

        testamentChooser.setSelectedIndex(0);
        bookChooser.setSelectedIndex(0);
        chapterChooser.setSelectedIndex(0);
        updateBookChooser();
        updateVerseChooser();
        
        selectedBookUnderline1.setContentAreaFilled(false);
        selectedBookUnderline2.setContentAreaFilled(false);
        selectedBookUnderline3.setContentAreaFilled(false);
        selectedBookUnderline4.setContentAreaFilled(false);
        selectedBookUnderline5.setContentAreaFilled(false);
        selectedBookUnderline6.setContentAreaFilled(false);
        selectedBookUnderline7.setContentAreaFilled(false);
        selectedBookUnderline8.setContentAreaFilled(false);
        selectedBookUnderline9.setContentAreaFilled(false);
        selectedBookUnderline10.setContentAreaFilled(false);
        selectedBookUnderline11.setContentAreaFilled(false);
        selectedBookUnderline12.setContentAreaFilled(false);
        
        bookmarkSavedNotif.setVisible(false);

        unmute.setVisible(false);
        mute.setVisible(false);
        
    

      
    }
    
        private void updateBookChooser() {
        int selectedTestament = testamentChooser.getSelectedIndex();
        int start, end;

        if (selectedTestament == 0) { 
            start = 0;
            end = 39;
        } else { 
            start = 39;
            end = allBooks.length;
        }

        String[] subset = java.util.Arrays.copyOfRange(allBooks, start, end);
        bookChooser.setModel(new javax.swing.DefaultComboBoxModel<>(subset));
    }


     
        private void updateChapterChooser() {
            int selectedBookIndex = bookChooser.getSelectedIndex(); 
            if (selectedBookIndex < 0) return;
            
            int selectedTestamentIndex = testamentChooser.getSelectedIndex();

            // Adjust for New Testament starting after 39 books
            int folderNumber;
            if (selectedTestamentIndex == 1) {
                folderNumber = selectedBookIndex + 40; // since NT starts at book #40 (index 39)
            } else {
                folderNumber = selectedBookIndex + 1;  // OT starts at book #1
            }

            File bookFolder = new File(
                    "C:\\Users\\boni\\Desktop\\Files\\The Bible Project\\BibleApp\\src\\main\\resources\\files\\books",
                    String.valueOf(folderNumber)
            );

            if (!bookFolder.exists() || !bookFolder.isDirectory()) {
                chapterChooser.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"1"}));
                return;
            }

            // Count all PDF files in the folder
            File[] chapterFiles = bookFolder.listFiles((dir, name) -> name.toLowerCase().endsWith(".pdf"));
            int chapterCount = (chapterFiles != null) ? chapterFiles.length : 0;

            // Populate chapterChooser with numbers 1 .. chapterCount
            String[] chapters = new String[chapterCount];
            for (int i = 0; i < chapterCount; i++) {
                chapters[i] = String.valueOf(i + 1); // start from 1
            }

            chapterChooser.setModel(new javax.swing.DefaultComboBoxModel<>(chapters));
            chapterChooser.setSelectedIndex(0); // default to first chapter
        }


        private void updateVerseChooser() {
            String text = mainTextArea.getText();
            if (text == null || text.isEmpty()) return;

            Pattern pattern = Pattern.compile("\\b\\d+\\b");
            Matcher matcher = pattern.matcher(text);

            Set<Integer> verseNumbers = new TreeSet<>();

            while (matcher.find()) {
                try {
                    int num = Integer.parseInt(matcher.group());
                    verseNumbers.add(num);
                } catch (NumberFormatException ignored) {}
            }

            // Always include 1
            verseNumbers.add(1);

            // Convert to String array
            String[] verses = verseNumbers.stream()
                    .map(String::valueOf)
                    .toArray(String[]::new);

            verseChooser.setModel(new javax.swing.DefaultComboBoxModel<>(verses));

            if (verses.length > 0) {
                verseChooser.setSelectedIndex(0); // default first verse
            }

            // Reset flag so first selection is ignored
            verseChooserInitialized = false;
        }



        private void scrollToAndHighlightVerse(int verseNumber) {
            if (verseNumber == 1) return; // ignore verse 1

            String text = mainTextArea.getText();
            if (text == null || text.isEmpty()) return;

            // Regex: find numbers at the start of lines
            Pattern pattern = Pattern.compile("(?m)^(\\d+)\\b");
            Matcher matcher = pattern.matcher(text);

            int start = -1;
            int end = text.length(); // default: highlight to end of text

            while (matcher.find()) {
                int foundNum = Integer.parseInt(matcher.group(1));

                // Skip if it’s not the selected verse
                if (foundNum != verseNumber) continue;

                // Check if the word before the number is "ምዕራፍ"
                int lineStart = matcher.start(1);
                String lineText = text.substring(0, lineStart); // text before number
                String[] words = lineText.split("\\s+");
                if (words.length > 0 && words[words.length - 1].equals("ምዕራፍ")) {
                    return; // skip highlighting if previous word is ምዕራፍ
                }

                start = matcher.start(1);

                if (matcher.find()) {
                    end = matcher.start(1); // up to next number
                }
                break;
            }

            if (start != -1) {
                // Scroll so the verse is at the top
                try {
                    Rectangle viewRect = mainTextArea.modelToView(start);
                    if (viewRect != null) {
                        JViewport viewport = (JViewport) mainTextArea.getParent();
                        viewRect.y -= 5; // small offset
                        viewport.setViewPosition(viewRect.getLocation());
                    }
                } catch (Exception ignored) {}

                // Highlight
                Highlighter highlighter = mainTextArea.getHighlighter();
                highlighter.removeAllHighlights();
                try {
                    final Object tag = highlighter.addHighlight(start, end,
                            new DefaultHighlighter.DefaultHighlightPainter(new Color(173, 216, 230, 128))); // light blue

                    // Remove after 3s
                    Timer timer = new Timer(3000, e -> highlighter.removeHighlight(tag));
                    timer.setRepeats(false);
                    timer.start();

                } catch (BadLocationException ex) {
                    ex.printStackTrace();
                }
            }
        }
        
        
        
        private void saveHighlight(String text, Color color) {
            try (Connection conn = DBManager.getConnection()) {

                int selectionStart = mainTextArea.getSelectionStart();
                int selectionEnd = mainTextArea.getSelectionEnd();

                String insert = """
                        INSERT INTO highlights 
                        (bookIndex, chapterIndex, line, wordDistIndex, text, colorR, colorG, colorB, postSpaceLength) 
                        VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
                        """;

                try (PreparedStatement ps = conn.prepareStatement(insert)) {

                    int startLine = mainTextArea.getLineOfOffset(selectionStart);
                    int endLine   = mainTextArea.getLineOfOffset(selectionEnd);

                    for (int line = startLine; line <= endLine; line++) {
                        int lineStartOffset = mainTextArea.getLineStartOffset(line);
                        int lineEndOffset   = mainTextArea.getLineEndOffset(line);

                        // Clamp selection to this line
                        int lineSelStart = Math.max(selectionStart, lineStartOffset);
                        int lineSelEnd   = Math.min(selectionEnd, lineEndOffset);

                        // Offset within line
                        int selectionStartInLine = lineSelStart - lineStartOffset;

                        // Grab text for this line
                        String lineText = mainTextArea.getText(lineSelStart, lineSelEnd - lineSelStart);

                        for (int i = 0; i < lineText.length(); i++) {
                            char c = lineText.charAt(i);

                            // Remove existing character at same position if exists
                            String delete = """
                                    DELETE FROM highlights 
                                    WHERE bookIndex=? AND chapterIndex=? AND line=? AND wordDistIndex=?
                                    """;
                            try (PreparedStatement del = conn.prepareStatement(delete)) {
                                del.setInt(1, currentBookIndex);
                                del.setInt(2, currentChapterIndex);
                                del.setInt(3, line);
                                del.setInt(4, selectionStartInLine + i);
                                del.executeUpdate();
                            }

                            // Store highlight
                            ps.setInt(1, currentBookIndex);
                            ps.setInt(2, currentChapterIndex);
                            ps.setInt(3, line);
                            ps.setInt(4, selectionStartInLine + i);
                            ps.setString(5, String.valueOf(c));
                            ps.setInt(6, color.getRed());
                            ps.setInt(7, color.getGreen());
                            ps.setInt(8, color.getBlue());
                            ps.setInt(9, 0); // no post-space
                            ps.addBatch();
                        }
                    }

                    ps.executeBatch();
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }



        private void restoreHighlights() {
                if (tabs.getSelectedIndex() != 0) {
                    return; 
                }
            try (Connection conn = DBManager.getConnection()) {

                // Remove old highlights
                mainTextArea.getHighlighter().removeAllHighlights();

                // Fetch all highlights for the current book and chapter
                String query = """
                    SELECT line, wordDistIndex, text, colorR, colorG, colorB, postSpaceLength 
                    FROM highlights 
                    WHERE bookIndex=? AND chapterIndex=? 
                    ORDER BY line, wordDistIndex
                    """;

                try (PreparedStatement ps = conn.prepareStatement(query)) {
                    ps.setInt(1, currentBookIndex);
                    ps.setInt(2, currentChapterIndex);

                    try (ResultSet rs = ps.executeQuery()) {
                        Map<Integer, List<HighlightWord>> lineMap = new HashMap<>();

                        // Group highlights by line
                        while (rs.next()) {
                            int lineNumber = rs.getInt("line");
                            int wordDistIndex = rs.getInt("wordDistIndex");
                            String text = rs.getString("text");
                            Color color = new Color(
                                    rs.getInt("colorR"),
                                    rs.getInt("colorG"),
                                    rs.getInt("colorB")
                            );
                            int postSpaceLength = rs.getInt("postSpaceLength");

                            lineMap.computeIfAbsent(lineNumber, k -> new ArrayList<>())
                                   .add(new HighlightWord(wordDistIndex, text, color, postSpaceLength));
                        }

                        // Apply highlights per line
                        for (Map.Entry<Integer, List<HighlightWord>> entry : lineMap.entrySet()) {
                            int lineNumber = entry.getKey();
                            List<HighlightWord> words = entry.getValue();
                            words.sort(Comparator.comparingInt(w -> w.wordDistIndex));

                            int lineStartOffset = mainTextArea.getLineStartOffset(lineNumber);

                            for (HighlightWord hw : words) {
                                // Use saved wordDistIndex directly
                                int start = lineStartOffset + hw.wordDistIndex;
                                int end = Math.min(start + hw.text.length(), mainTextArea.getText().length());

                                mainTextArea.getHighlighter().addHighlight(
                                    start,
                                    end,
                                    new DefaultHighlighter.DefaultHighlightPainter(hw.color)
                                );
                            }
                        }
                    }
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }


        public class HighlightWord {
            public int wordDistIndex;
            public String text;
            public Color color;
            public int postSpaceLength;

            public HighlightWord(int wordDistIndex, String text, Color color) {
                this(wordDistIndex, text, color, 1); // default post-space
            }

            public HighlightWord(int wordDistIndex, String text, Color color, int postSpaceLength) {
                this.wordDistIndex = wordDistIndex;
                this.text = text;
                this.color = color;
                this.postSpaceLength = postSpaceLength;
            }
        }



        private void saveNotes() {
            // get text from JTextArea
            String text = notesInput.getText();

            // build path to Notes folder relative to project directory
            // (user.dir gives the working directory of the program)
            String projectDir = System.getProperty("user.dir");
            String notesDir = projectDir + File.separator + "Notes";

            // make sure the Notes folder exists
            File dir = new File(notesDir);
            if (!dir.exists()) {
                dir.mkdirs(); // create Notes folder if it doesn’t exist
            }

            // build the filename
            String filename = "notes" + currentBookIndex + "_" + currentChapterIndex + "notes.txt";

            // full file path
            File file = new File(dir, filename);

            try (FileWriter writer = new FileWriter(file)) {
                writer.write(text);
                System.out.println("Saved to: " + file.getAbsolutePath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    


        
public List<Integer> get12RandomBookNumbers() {
    // Folder in your project resources
    String folderPath = "bookTiles"; // relative to resources
    URL folderURL = getClass().getClassLoader().getResource(folderPath);
    if (folderURL == null) {
        System.err.println("Resource folder not found: " + folderPath);
        return Collections.nCopies(12, 0);
    }

    File folder = new File(folderURL.getPath());
    File[] files = folder.listFiles();
    if (files == null || files.length == 0) {
        System.err.println("No files found in: " + folderPath);
        return Collections.nCopies(12, 0);
    }

    // Extract numbers from filenames
    List<Integer> numbers = Arrays.stream(files)
            .filter(File::isFile)
            .map(f -> f.getName().replaceAll("\\D", ""))
            .filter(s -> !s.isEmpty())
            .map(Integer::parseInt)
            .sorted()
            .collect(Collectors.toList());

    int min = numbers.get(0);
    int max = numbers.get(numbers.size() - 1);
    System.out.println("Max number in folder: " + max);

    // Generate 12 unique random numbers
    Random rand = new Random();
    Set<Integer> randomNumbers = new LinkedHashSet<>();
    while (randomNumbers.size() < 12) {
        int r = rand.nextInt(max - min + 1) + min;
        randomNumbers.add(r);
    }

    return new ArrayList<>(randomNumbers);
}

  


private void updateBookTiles() {
    for (int i = 0; i < 12; i++) {
        JButton btn = switch(i) {
            case 0 -> bookDisplay1;
            case 1 -> bookDisplay2;
            case 2 -> bookDisplay3;
            case 3 -> bookDisplay4;
            case 4 -> bookDisplay5;
            case 5 -> bookDisplay6;
            case 6 -> bookDisplay7;
            case 7 -> bookDisplay8;
            case 8 -> bookDisplay9;
            case 9 -> bookDisplay10;
            case 10 -> bookDisplay11;
            case 11 -> bookDisplay12;
            default -> null;
        };

        int num = randomNums.get(i);
        URL iconURL = getClass().getResource("/bookTiles/" + num + ".png");
        if (iconURL != null) {
            btn.setIcon(new javax.swing.ImageIcon(iconURL));
        } else {
            System.err.println("Icon not found: /bookTiles/" + num + ".png");
        }
    }
}
      


private void updateSelectedBookIcon() {
    // currentSelected stores which button (1–12) was clicked
    // so grab its random number from the list
    int assignedNum = randomNums.get(currentSelected - 1); 

    URL selectedIconURL = getClass().getResource("/bookTiles/" + assignedNum + ".png");
    if (selectedIconURL != null) {
        selectedBook.setIcon(new ImageIcon(selectedIconURL));
    } else {
        System.err.println("Icon not found: /bookTiles/" + assignedNum + ".png");
    }
}


private void updateBookDetails() {
    if (currentSelected < 1 || currentSelected > randomNums.size()) {
        description.setText("<html><div style='text-align:center; color:red;'>Invalid selection.</div></html>");
        return;
    }

    int bookIndex = randomNums.get(currentSelected - 1); // get assigned number for this button
    String dbPath = "bookStack.db";
    String sql = "SELECT bookName, author, category, description FROM bookStack WHERE CAST(TRIM(bookIndex) AS INTEGER) = ?";

    try (Connection conn = DriverManager.getConnection("jdbc:sqlite:" + dbPath);
         PreparedStatement pstmt = conn.prepareStatement(sql)) {

        pstmt.setInt(1, bookIndex);

        try (ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                String bookName = rs.getString("bookName").trim();
                String author = rs.getString("author").trim();
                String category = rs.getString("category").trim();
                String desc = rs.getString("description").trim();

                String htmlText = "<html><div style='text-align:center; font-family:Nokia Pure Headline Ultra Light; font-size:14px; color:white;'>" +
                                  "<b>Title:</b> " + bookName + "<br>" +
                                  "<b>Author:</b> " + author + "<br>" +
                                  "<b>Category:</b> " + category + "<br><br>" +
                                  "<b>Description:</b><br>" +
                                  "<span style='font-size:12px;'>" + desc.replace("\n", "<br>") + "</span>" +
                                  "</div></html>";

                description.setText(htmlText);

            } else {
                description.setText("<html><div style='text-align:center; color:red;'>Book not found in database.</div></html>");
            }
        }

    } catch (SQLException ex) {
        ex.printStackTrace();
        description.setText("<html><div style='text-align:center; color:red;'>Database error: " + ex.getMessage() + "</div></html>");
    }
}



        
private void startScrollLogger(JScrollPane scrollPane) {

    new javax.swing.Timer(6000, e -> {
        // Get current scroll position
        JScrollBar verticalBar = scrollPane.getVerticalScrollBar();
        int currentScrollState = verticalBar.getValue();

        String dbPath = "bookStack.db";
        overhead = 0; // current value from DB

        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:" + dbPath)) {

            if (!firstScrollWrite) {
                // Read existing scroll index only if it's not the first write
                String selectSQL = "SELECT currentScrollIndex FROM scrollStatus WHERE bookIndex = ?";
                try (PreparedStatement pstmt = conn.prepareStatement(selectSQL)) {
                    pstmt.setInt(1, loadedBook);
                    try (ResultSet rs = pstmt.executeQuery()) {
                        if (rs.next()) {
                            overhead = rs.getInt("currentScrollIndex");
                        }
                    }
                }
            }

            // Insert or update DB
            if (firstScrollWrite || currentScrollState > overhead) {
                String updateSQL = "INSERT INTO scrollStatus (bookIndex, currentScrollIndex) " +
                                   "VALUES (?, ?) " +
                                   "ON CONFLICT(bookIndex) DO UPDATE SET currentScrollIndex = excluded.currentScrollIndex";

                try (PreparedStatement pstmtUpdate = conn.prepareStatement(updateSQL)) {
                    pstmtUpdate.setInt(1, loadedBook);
                    pstmtUpdate.setInt(2, currentScrollState);
                    pstmtUpdate.executeUpdate();
                    System.out.println("Updated DB with scroll index: " + currentScrollState);
                }

                firstScrollWrite = false; // reset flag after first write
            } else {
                System.out.println("No update needed. DB scroll index is higher or equal: " + overhead);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        // Debug prints
        System.out.println("Current Scroll Index: " + currentScrollState);
        System.out.println("Current Book Index: " + loadedBook);
        System.out.println("DB Overhead: " + overhead);

    }).start();
}


private void updateVolumeDisplay() {
    if (lastPlayedClip != null && lastPlayedClip.isRunning()) {
        try {
            FloatControl gainControl = (FloatControl) lastPlayedClip.getControl(FloatControl.Type.MASTER_GAIN);
            float current = gainControl.getValue();
            float min = gainControl.getMinimum();
            float max = gainControl.getMaximum();

            // Convert dB to 0–100% scale
            int percent = Math.round(((current - min) / (max - min)) * 100);
            volDisp.setText(" "+ percent );
        } catch (IllegalArgumentException ex) {
            volDisp.setText("N/A");
        }
    } else {
        volDisp.setText("N/A");
    }
}






    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel_layered = new javax.swing.JLayeredPane();
        topBar = new javax.swing.JPanel();
        closeBtn = new javax.swing.JButton();
        appTitle = new javax.swing.JLabel();
        restoreBtn = new javax.swing.JButton();
        minimizeBtn = new javax.swing.JButton();
        navBar = new javax.swing.JPanel();
        homeBtnLabel = new javax.swing.JLabel();
        bibleBtnLabel = new javax.swing.JLabel();
        searchBtnLabel = new javax.swing.JLabel();
        bookmarksBtn = new javax.swing.JLabel();
        notesBtnLabel = new javax.swing.JLabel();
        audiobookLabel = new javax.swing.JLabel();
        cmntrsBtnLabel = new javax.swing.JLabel();
        hostJoinBtnLabel = new javax.swing.JLabel();
        settingsLabel = new javax.swing.JLabel();
        homeBtn = new javax.swing.JButton();
        libraryBtn = new javax.swing.JButton();
        searchBtn = new javax.swing.JButton();
        bookmarkBtn = new javax.swing.JButton();
        journalBtn = new javax.swing.JButton();
        audiobookBtn = new javax.swing.JButton();
        cmntrsBtn = new javax.swing.JButton();
        hostJoinBtn = new javax.swing.JButton();
        settingsBtn = new javax.swing.JButton();
        tabs = new javax.swing.JTabbedPane();
        bibleTab = new javax.swing.JPanel();
        mainTextScrollPanel = new javax.swing.JScrollPane();
        mainTextArea = new javax.swing.JTextArea();
        cmtryTabbedPanel = new javax.swing.JTabbedPane();
        notesTabPanel = new javax.swing.JPanel();
        notesTabLayers = new javax.swing.JLayeredPane();
        eyeHide = new javax.swing.JButton();
        eyeShow = new javax.swing.JButton();
        saveNote = new javax.swing.JButton();
        saveTick = new javax.swing.JButton();
        addNoteBtn = new javax.swing.JButton();
        notesInput = new javax.swing.JTextArea();
        nodesTabPanel = new javax.swing.JPanel();
        cmtrsTabPanel = new javax.swing.JPanel();
        biblestudyTitle = new javax.swing.JLabel();
        highlightBtn = new javax.swing.JButton();
        boldBtn = new javax.swing.JButton();
        fontSizeSlider = new javax.swing.JSlider();
        bookChooser = new javax.swing.JComboBox<>();
        chapterChooser = new javax.swing.JComboBox<>();
        verseChooser = new javax.swing.JComboBox<>();
        testamentChooser = new javax.swing.JComboBox<>();
        bookChooserDropDown = new javax.swing.JComboBox<>();
        libraryTab = new javax.swing.JPanel();
        libraryContent = new javax.swing.JPanel();
        readStatus1 = new javax.swing.JPanel();
        dayLabel4 = new javax.swing.JLabel();
        dayLabel5 = new javax.swing.JLabel();
        dayLabel6 = new javax.swing.JLabel();
        month14 = new javax.swing.JLabel();
        month15 = new javax.swing.JLabel();
        month16 = new javax.swing.JLabel();
        month17 = new javax.swing.JLabel();
        month18 = new javax.swing.JLabel();
        month19 = new javax.swing.JLabel();
        month20 = new javax.swing.JLabel();
        month21 = new javax.swing.JLabel();
        month22 = new javax.swing.JLabel();
        month23 = new javax.swing.JLabel();
        month24 = new javax.swing.JLabel();
        month25 = new javax.swing.JLabel();
        month26 = new javax.swing.JLabel();
        statusDay1 = new javax.swing.JButton();
        statusDay2 = new javax.swing.JButton();
        statusDay3 = new javax.swing.JButton();
        statusDay4 = new javax.swing.JButton();
        statusDay5 = new javax.swing.JButton();
        statusDay6 = new javax.swing.JButton();
        statusDay7 = new javax.swing.JButton();
        statusDay8 = new javax.swing.JButton();
        statusDay9 = new javax.swing.JButton();
        statusDay10 = new javax.swing.JButton();
        statusDay11 = new javax.swing.JButton();
        statusDay12 = new javax.swing.JButton();
        statusDay13 = new javax.swing.JButton();
        statusDay14 = new javax.swing.JButton();
        statusDay15 = new javax.swing.JButton();
        statusDay16 = new javax.swing.JButton();
        statusDay17 = new javax.swing.JButton();
        statusDay18 = new javax.swing.JButton();
        statusDay19 = new javax.swing.JButton();
        statusDay20 = new javax.swing.JButton();
        statusDay21 = new javax.swing.JButton();
        statusDay22 = new javax.swing.JButton();
        statusDay23 = new javax.swing.JButton();
        statusDay24 = new javax.swing.JButton();
        statusDay25 = new javax.swing.JButton();
        statusDay26 = new javax.swing.JButton();
        statusDay27 = new javax.swing.JButton();
        statusDay28 = new javax.swing.JButton();
        statusDay29 = new javax.swing.JButton();
        statusDay30 = new javax.swing.JButton();
        statusDay31 = new javax.swing.JButton();
        statusDay32 = new javax.swing.JButton();
        statusDay33 = new javax.swing.JButton();
        statusDay34 = new javax.swing.JButton();
        statusDay35 = new javax.swing.JButton();
        statusDay36 = new javax.swing.JButton();
        statusDay37 = new javax.swing.JButton();
        statusDay38 = new javax.swing.JButton();
        statusDay39 = new javax.swing.JButton();
        statusDay40 = new javax.swing.JButton();
        statusDay41 = new javax.swing.JButton();
        statusDay42 = new javax.swing.JButton();
        statusDay43 = new javax.swing.JButton();
        statusDay44 = new javax.swing.JButton();
        statusDay45 = new javax.swing.JButton();
        statusDay46 = new javax.swing.JButton();
        statusDay47 = new javax.swing.JButton();
        statusDay48 = new javax.swing.JButton();
        statusDay49 = new javax.swing.JButton();
        statusDay50 = new javax.swing.JButton();
        statusDay51 = new javax.swing.JButton();
        statusDay52 = new javax.swing.JButton();
        statusDay53 = new javax.swing.JButton();
        statusDay54 = new javax.swing.JButton();
        statusDay55 = new javax.swing.JButton();
        statusDay56 = new javax.swing.JButton();
        statusDay57 = new javax.swing.JButton();
        statusDay58 = new javax.swing.JButton();
        statusDay59 = new javax.swing.JButton();
        statusDay60 = new javax.swing.JButton();
        statusDay61 = new javax.swing.JButton();
        statusDay62 = new javax.swing.JButton();
        statusDay63 = new javax.swing.JButton();
        statusDay64 = new javax.swing.JButton();
        statusDay65 = new javax.swing.JButton();
        statusDay66 = new javax.swing.JButton();
        statusDay67 = new javax.swing.JButton();
        statusDay68 = new javax.swing.JButton();
        statusDay69 = new javax.swing.JButton();
        statusDay70 = new javax.swing.JButton();
        statusDay71 = new javax.swing.JButton();
        statusDay72 = new javax.swing.JButton();
        statusDay73 = new javax.swing.JButton();
        statusDay74 = new javax.swing.JButton();
        statusDay75 = new javax.swing.JButton();
        statusDay76 = new javax.swing.JButton();
        statusDay77 = new javax.swing.JButton();
        statusDay78 = new javax.swing.JButton();
        statusDay79 = new javax.swing.JButton();
        statusDay80 = new javax.swing.JButton();
        statusDay81 = new javax.swing.JButton();
        statusDay82 = new javax.swing.JButton();
        statusDay83 = new javax.swing.JButton();
        statusDay84 = new javax.swing.JButton();
        statusDay85 = new javax.swing.JButton();
        statusDay86 = new javax.swing.JButton();
        statusDay87 = new javax.swing.JButton();
        statusDay88 = new javax.swing.JButton();
        statusDay89 = new javax.swing.JButton();
        statusDay90 = new javax.swing.JButton();
        statusDay91 = new javax.swing.JButton();
        statusDay92 = new javax.swing.JButton();
        statusDay93 = new javax.swing.JButton();
        statusDay94 = new javax.swing.JButton();
        statusDay95 = new javax.swing.JButton();
        statusDay96 = new javax.swing.JButton();
        statusDay97 = new javax.swing.JButton();
        statusDay98 = new javax.swing.JButton();
        statusDay99 = new javax.swing.JButton();
        statusDay100 = new javax.swing.JButton();
        statusDay101 = new javax.swing.JButton();
        statusDay102 = new javax.swing.JButton();
        statusDay103 = new javax.swing.JButton();
        statusDay104 = new javax.swing.JButton();
        statusDay105 = new javax.swing.JButton();
        statusDay106 = new javax.swing.JButton();
        statusDay107 = new javax.swing.JButton();
        statusDay108 = new javax.swing.JButton();
        statusDay109 = new javax.swing.JButton();
        statusDay110 = new javax.swing.JButton();
        statusDay111 = new javax.swing.JButton();
        statusDay112 = new javax.swing.JButton();
        statusDay113 = new javax.swing.JButton();
        statusDay114 = new javax.swing.JButton();
        statusDay115 = new javax.swing.JButton();
        statusDay116 = new javax.swing.JButton();
        statusDay117 = new javax.swing.JButton();
        statusDay118 = new javax.swing.JButton();
        statusDay119 = new javax.swing.JButton();
        statusDay120 = new javax.swing.JButton();
        statusDay121 = new javax.swing.JButton();
        statusDay122 = new javax.swing.JButton();
        statusDay123 = new javax.swing.JButton();
        statusDay124 = new javax.swing.JButton();
        statusDay125 = new javax.swing.JButton();
        statusDay126 = new javax.swing.JButton();
        statusDay127 = new javax.swing.JButton();
        statusDay128 = new javax.swing.JButton();
        statusDay129 = new javax.swing.JButton();
        statusDay130 = new javax.swing.JButton();
        statusDay131 = new javax.swing.JButton();
        statusDay132 = new javax.swing.JButton();
        statusDay133 = new javax.swing.JButton();
        statusDay134 = new javax.swing.JButton();
        statusDay135 = new javax.swing.JButton();
        statusDay136 = new javax.swing.JButton();
        statusDay137 = new javax.swing.JButton();
        statusDay138 = new javax.swing.JButton();
        statusDay139 = new javax.swing.JButton();
        statusDay140 = new javax.swing.JButton();
        statusDay141 = new javax.swing.JButton();
        statusDay142 = new javax.swing.JButton();
        statusDay143 = new javax.swing.JButton();
        statusDay144 = new javax.swing.JButton();
        statusDay145 = new javax.swing.JButton();
        statusDay146 = new javax.swing.JButton();
        statusDay147 = new javax.swing.JButton();
        statusDay148 = new javax.swing.JButton();
        statusDay149 = new javax.swing.JButton();
        statusDay150 = new javax.swing.JButton();
        statusDay151 = new javax.swing.JButton();
        statusDay152 = new javax.swing.JButton();
        statusDay153 = new javax.swing.JButton();
        statusDay154 = new javax.swing.JButton();
        statusDay155 = new javax.swing.JButton();
        statusDay156 = new javax.swing.JButton();
        statusDay157 = new javax.swing.JButton();
        statusDay158 = new javax.swing.JButton();
        statusDay159 = new javax.swing.JButton();
        statusDay160 = new javax.swing.JButton();
        statusDay161 = new javax.swing.JButton();
        statusDay162 = new javax.swing.JButton();
        statusDay163 = new javax.swing.JButton();
        statusDay164 = new javax.swing.JButton();
        statusDay165 = new javax.swing.JButton();
        statusDay166 = new javax.swing.JButton();
        statusDay167 = new javax.swing.JButton();
        statusDay168 = new javax.swing.JButton();
        statusDay169 = new javax.swing.JButton();
        statusDay170 = new javax.swing.JButton();
        statusDay171 = new javax.swing.JButton();
        statusDay172 = new javax.swing.JButton();
        statusDay173 = new javax.swing.JButton();
        statusDay174 = new javax.swing.JButton();
        statusDay175 = new javax.swing.JButton();
        statusDay176 = new javax.swing.JButton();
        statusDay177 = new javax.swing.JButton();
        statusDay178 = new javax.swing.JButton();
        statusDay179 = new javax.swing.JButton();
        statusDay180 = new javax.swing.JButton();
        statusDay181 = new javax.swing.JButton();
        statusDay182 = new javax.swing.JButton();
        statusDay183 = new javax.swing.JButton();
        statusDay184 = new javax.swing.JButton();
        statusDay185 = new javax.swing.JButton();
        statusDay186 = new javax.swing.JButton();
        statusDay187 = new javax.swing.JButton();
        statusDay188 = new javax.swing.JButton();
        statusDay189 = new javax.swing.JButton();
        statusDay190 = new javax.swing.JButton();
        statusDay191 = new javax.swing.JButton();
        statusDay192 = new javax.swing.JButton();
        statusDay193 = new javax.swing.JButton();
        statusDay194 = new javax.swing.JButton();
        statusDay195 = new javax.swing.JButton();
        statusDay196 = new javax.swing.JButton();
        statusDay197 = new javax.swing.JButton();
        statusDay198 = new javax.swing.JButton();
        statusDay199 = new javax.swing.JButton();
        statusDay200 = new javax.swing.JButton();
        statusDay201 = new javax.swing.JButton();
        statusDay202 = new javax.swing.JButton();
        statusDay203 = new javax.swing.JButton();
        statusDay204 = new javax.swing.JButton();
        statusDay205 = new javax.swing.JButton();
        statusDay206 = new javax.swing.JButton();
        statusDay207 = new javax.swing.JButton();
        statusDay208 = new javax.swing.JButton();
        statusDay209 = new javax.swing.JButton();
        statusDay210 = new javax.swing.JButton();
        statusDay211 = new javax.swing.JButton();
        statusDay212 = new javax.swing.JButton();
        statusDay213 = new javax.swing.JButton();
        statusDay214 = new javax.swing.JButton();
        statusDay215 = new javax.swing.JButton();
        statusDay216 = new javax.swing.JButton();
        statusDay217 = new javax.swing.JButton();
        statusDay218 = new javax.swing.JButton();
        statusDay219 = new javax.swing.JButton();
        statusDay220 = new javax.swing.JButton();
        statusDay221 = new javax.swing.JButton();
        statusDay222 = new javax.swing.JButton();
        statusDay223 = new javax.swing.JButton();
        statusDay224 = new javax.swing.JButton();
        statusDay225 = new javax.swing.JButton();
        statusDay226 = new javax.swing.JButton();
        statusDay227 = new javax.swing.JButton();
        statusDay228 = new javax.swing.JButton();
        statusDay229 = new javax.swing.JButton();
        statusDay230 = new javax.swing.JButton();
        statusDay231 = new javax.swing.JButton();
        statusDay232 = new javax.swing.JButton();
        statusDay233 = new javax.swing.JButton();
        statusDay234 = new javax.swing.JButton();
        statusDay235 = new javax.swing.JButton();
        statusDay236 = new javax.swing.JButton();
        statusDay237 = new javax.swing.JButton();
        statusDay238 = new javax.swing.JButton();
        statusDay239 = new javax.swing.JButton();
        statusDay240 = new javax.swing.JButton();
        statusDay241 = new javax.swing.JButton();
        statusDay242 = new javax.swing.JButton();
        statusDay243 = new javax.swing.JButton();
        statusDay244 = new javax.swing.JButton();
        statusDay245 = new javax.swing.JButton();
        statusDay246 = new javax.swing.JButton();
        statusDay247 = new javax.swing.JButton();
        statusDay248 = new javax.swing.JButton();
        statusDay249 = new javax.swing.JButton();
        statusDay250 = new javax.swing.JButton();
        statusDay251 = new javax.swing.JButton();
        statusDay252 = new javax.swing.JButton();
        statusDay253 = new javax.swing.JButton();
        statusDay254 = new javax.swing.JButton();
        statusDay255 = new javax.swing.JButton();
        statusDay256 = new javax.swing.JButton();
        statusDay257 = new javax.swing.JButton();
        statusDay258 = new javax.swing.JButton();
        statusDay259 = new javax.swing.JButton();
        statusDay260 = new javax.swing.JButton();
        statusDay261 = new javax.swing.JButton();
        statusDay262 = new javax.swing.JButton();
        statusDay263 = new javax.swing.JButton();
        statusDay264 = new javax.swing.JButton();
        statusDay265 = new javax.swing.JButton();
        statusDay266 = new javax.swing.JButton();
        statusDay267 = new javax.swing.JButton();
        statusDay268 = new javax.swing.JButton();
        statusDay269 = new javax.swing.JButton();
        statusDay270 = new javax.swing.JButton();
        statusDay271 = new javax.swing.JButton();
        statusDay272 = new javax.swing.JButton();
        statusDay273 = new javax.swing.JButton();
        statusDay274 = new javax.swing.JButton();
        statusDay275 = new javax.swing.JButton();
        statusDay276 = new javax.swing.JButton();
        statusDay277 = new javax.swing.JButton();
        statusDay278 = new javax.swing.JButton();
        statusDay279 = new javax.swing.JButton();
        statusDay280 = new javax.swing.JButton();
        statusDay281 = new javax.swing.JButton();
        statusDay282 = new javax.swing.JButton();
        statusDay283 = new javax.swing.JButton();
        statusDay284 = new javax.swing.JButton();
        statusDay285 = new javax.swing.JButton();
        statusDay286 = new javax.swing.JButton();
        statusDay287 = new javax.swing.JButton();
        statusDay288 = new javax.swing.JButton();
        statusDay289 = new javax.swing.JButton();
        statusDay290 = new javax.swing.JButton();
        statusDay291 = new javax.swing.JButton();
        statusDay292 = new javax.swing.JButton();
        statusDay293 = new javax.swing.JButton();
        statusDay294 = new javax.swing.JButton();
        statusDay295 = new javax.swing.JButton();
        statusDay296 = new javax.swing.JButton();
        statusDay297 = new javax.swing.JButton();
        statusDay298 = new javax.swing.JButton();
        statusDay299 = new javax.swing.JButton();
        statusDay300 = new javax.swing.JButton();
        statusDay301 = new javax.swing.JButton();
        statusDay302 = new javax.swing.JButton();
        statusDay303 = new javax.swing.JButton();
        statusDay304 = new javax.swing.JButton();
        statusDay305 = new javax.swing.JButton();
        statusDay306 = new javax.swing.JButton();
        statusDay307 = new javax.swing.JButton();
        statusDay308 = new javax.swing.JButton();
        statusDay309 = new javax.swing.JButton();
        statusDay310 = new javax.swing.JButton();
        statusDay311 = new javax.swing.JButton();
        statusDay312 = new javax.swing.JButton();
        statusDay313 = new javax.swing.JButton();
        statusDay314 = new javax.swing.JButton();
        statusDay315 = new javax.swing.JButton();
        statusDay316 = new javax.swing.JButton();
        statusDay317 = new javax.swing.JButton();
        statusDay318 = new javax.swing.JButton();
        statusDay319 = new javax.swing.JButton();
        statusDay320 = new javax.swing.JButton();
        statusDay321 = new javax.swing.JButton();
        statusDay322 = new javax.swing.JButton();
        statusDay323 = new javax.swing.JButton();
        statusDay324 = new javax.swing.JButton();
        statusDay325 = new javax.swing.JButton();
        statusDay326 = new javax.swing.JButton();
        statusDay327 = new javax.swing.JButton();
        statusDay328 = new javax.swing.JButton();
        statusDay329 = new javax.swing.JButton();
        statusDay330 = new javax.swing.JButton();
        statusDay331 = new javax.swing.JButton();
        statusDay332 = new javax.swing.JButton();
        statusDay333 = new javax.swing.JButton();
        statusDay334 = new javax.swing.JButton();
        statusDay335 = new javax.swing.JButton();
        statusDay336 = new javax.swing.JButton();
        statusDay337 = new javax.swing.JButton();
        statusDay338 = new javax.swing.JButton();
        statusDay339 = new javax.swing.JButton();
        statusDay340 = new javax.swing.JButton();
        statusDay341 = new javax.swing.JButton();
        statusDay342 = new javax.swing.JButton();
        statusDay343 = new javax.swing.JButton();
        statusDay344 = new javax.swing.JButton();
        statusDay345 = new javax.swing.JButton();
        statusDay346 = new javax.swing.JButton();
        statusDay347 = new javax.swing.JButton();
        statusDay348 = new javax.swing.JButton();
        statusDay349 = new javax.swing.JButton();
        statusDay350 = new javax.swing.JButton();
        statusDay351 = new javax.swing.JButton();
        statusDay352 = new javax.swing.JButton();
        statusDay353 = new javax.swing.JButton();
        statusDay354 = new javax.swing.JButton();
        statusDay355 = new javax.swing.JButton();
        statusDay356 = new javax.swing.JButton();
        statusDay357 = new javax.swing.JButton();
        statusDay358 = new javax.swing.JButton();
        statusDay359 = new javax.swing.JButton();
        statusDay360 = new javax.swing.JButton();
        statusDay361 = new javax.swing.JButton();
        statusDay362 = new javax.swing.JButton();
        statusDay363 = new javax.swing.JButton();
        statusDay364 = new javax.swing.JButton();
        statusDay365 = new javax.swing.JButton();
        statusDay366 = new javax.swing.JButton();
        bookDescriptionSideBar1 = new javax.swing.JPanel();
        aboutTheBook1 = new javax.swing.JLabel();
        selectedBook = new javax.swing.JButton();
        readBtn = new javax.swing.JButton();
        description = new javax.swing.JLabel();
        bookDisplay1 = new javax.swing.JButton();
        bookDisplay2 = new javax.swing.JButton();
        bookDisplay3 = new javax.swing.JButton();
        bookDisplay4 = new javax.swing.JButton();
        bookDisplay5 = new javax.swing.JButton();
        bookDisplay6 = new javax.swing.JButton();
        bookDisplay7 = new javax.swing.JButton();
        bookDisplay8 = new javax.swing.JButton();
        bookDisplay9 = new javax.swing.JButton();
        bookDisplay10 = new javax.swing.JButton();
        bookDisplay11 = new javax.swing.JButton();
        bookDisplay12 = new javax.swing.JButton();
        exploreMoreBtn1 = new javax.swing.JButton();
        reRandomizer = new javax.swing.JButton();
        selectedBookUnderline1 = new javax.swing.JButton();
        selectedBookUnderline2 = new javax.swing.JButton();
        selectedBookUnderline3 = new javax.swing.JButton();
        selectedBookUnderline4 = new javax.swing.JButton();
        selectedBookUnderline5 = new javax.swing.JButton();
        selectedBookUnderline6 = new javax.swing.JButton();
        selectedBookUnderline7 = new javax.swing.JButton();
        selectedBookUnderline8 = new javax.swing.JButton();
        selectedBookUnderline9 = new javax.swing.JButton();
        selectedBookUnderline10 = new javax.swing.JButton();
        selectedBookUnderline11 = new javax.swing.JButton();
        selectedBookUnderline12 = new javax.swing.JButton();
        loadingScreen = new javax.swing.JPanel();
        loadingPrevis = new javax.swing.JPanel();
        loadingLoop = new javax.swing.JLabel();
        loadingBook = new javax.swing.JLabel();
        bookReaderSubTab = new javax.swing.JPanel();
        bookReaderContent = new javax.swing.JPanel();
        subTabLayered = new javax.swing.JLayeredPane();
        mute = new javax.swing.JButton();
        unmute = new javax.swing.JButton();
        bookmarkSavedNotif = new javax.swing.JLabel();
        toolBox = new javax.swing.JPanel();
        bookmark = new javax.swing.JButton();
        cafeTheme = new javax.swing.JButton();
        treeTheme = new javax.swing.JButton();
        rainAmbience = new javax.swing.JButton();
        volPLus = new javax.swing.JButton();
        volDisp = new javax.swing.JTextField();
        volMinus = new javax.swing.JButton();
        bookScroll = new javax.swing.JScrollPane();
        pdfDisplay = new javax.swing.JTextArea();
        themer = new javax.swing.JLabel();
        spacer = new javax.swing.JButton();
        jPanel14 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        mainPanel_layered.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        topBar.setBackground(new java.awt.Color(255, 255, 255));
        topBar.setPreferredSize(new java.awt.Dimension(1427, 10));

        closeBtn.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        closeBtn.setText("X");
        closeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeBtnActionPerformed(evt);
            }
        });

        appTitle.setFont(new java.awt.Font("Nokia Pure Headline", 0, 24)); // NOI18N
        appTitle.setText("መፅሀፍ ቅዱስ");

        restoreBtn.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        restoreBtn.setText("☐");
        restoreBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                restoreBtnActionPerformed(evt);
            }
        });

        minimizeBtn.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        minimizeBtn.setText("‒");
        minimizeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                minimizeBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout topBarLayout = new javax.swing.GroupLayout(topBar);
        topBar.setLayout(topBarLayout);
        topBarLayout.setHorizontalGroup(
            topBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topBarLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(appTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 1360, Short.MAX_VALUE)
                .addComponent(minimizeBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(restoreBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(closeBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        topBarLayout.setVerticalGroup(
            topBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topBarLayout.createSequentialGroup()
                .addGroup(topBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(appTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(topBarLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(topBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(minimizeBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(restoreBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(closeBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        mainPanel_layered.add(topBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1650, 35));

        navBar.setBackground(new java.awt.Color(40, 43, 45));

        homeBtnLabel.setForeground(new java.awt.Color(255, 255, 255));
        homeBtnLabel.setText("Bible");

        bibleBtnLabel.setForeground(new java.awt.Color(255, 255, 255));
        bibleBtnLabel.setText(" Library");

        searchBtnLabel.setForeground(new java.awt.Color(255, 255, 255));
        searchBtnLabel.setText("Search");

        bookmarksBtn.setForeground(new java.awt.Color(255, 255, 255));
        bookmarksBtn.setText("Bookmarks");

        notesBtnLabel.setForeground(new java.awt.Color(255, 255, 255));
        notesBtnLabel.setText(" Journal");

        audiobookLabel.setForeground(new java.awt.Color(255, 255, 255));
        audiobookLabel.setText("Audiobook");

        cmntrsBtnLabel.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        cmntrsBtnLabel.setForeground(new java.awt.Color(255, 255, 255));
        cmntrsBtnLabel.setText("Commentaries");

        hostJoinBtnLabel.setForeground(new java.awt.Color(255, 255, 255));
        hostJoinBtnLabel.setText(" Host/Join");
        hostJoinBtnLabel.setAlignmentX(0.5F);

        settingsLabel.setForeground(new java.awt.Color(255, 255, 255));
        settingsLabel.setText("   Settings");

        homeBtn.setBackground(new java.awt.Color(40, 43, 45));
        homeBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/book.png"))); // NOI18N
        homeBtn.setBorder(null);
        homeBtn.setBorderPainted(false);
        homeBtn.setContentAreaFilled(false);
        homeBtn.setFocusPainted(false);
        homeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                homeBtnActionPerformed(evt);
            }
        });

        libraryBtn.setBackground(new java.awt.Color(40, 43, 45));
        libraryBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8-library-35 (1).png"))); // NOI18N
        libraryBtn.setBorder(null);
        libraryBtn.setBorderPainted(false);
        libraryBtn.setContentAreaFilled(false);
        libraryBtn.setFocusable(false);
        libraryBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                libraryBtnActionPerformed(evt);
            }
        });

        searchBtn.setBackground(new java.awt.Color(40, 43, 45));
        searchBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/search.png"))); // NOI18N
        searchBtn.setBorder(null);

        bookmarkBtn.setBackground(new java.awt.Color(40, 43, 45));
        bookmarkBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/bookmark.png"))); // NOI18N
        bookmarkBtn.setBorder(null);

        journalBtn.setBackground(new java.awt.Color(40, 43, 45));
        journalBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/notes.png"))); // NOI18N
        journalBtn.setBorder(null);

        audiobookBtn.setBackground(new java.awt.Color(40, 43, 45));
        audiobookBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/audiobook.png"))); // NOI18N
        audiobookBtn.setBorder(null);

        cmntrsBtn.setBackground(new java.awt.Color(40, 43, 45));
        cmntrsBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/commentaries.png"))); // NOI18N
        cmntrsBtn.setBorder(null);

        hostJoinBtn.setBackground(new java.awt.Color(40, 43, 45));
        hostJoinBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/hostJoin.png"))); // NOI18N
        hostJoinBtn.setBorder(null);

        settingsBtn.setBackground(new java.awt.Color(40, 43, 45));
        settingsBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/settings.png"))); // NOI18N
        settingsBtn.setBorder(null);
        settingsBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                settingsBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout navBarLayout = new javax.swing.GroupLayout(navBar);
        navBar.setLayout(navBarLayout);
        navBarLayout.setHorizontalGroup(
            navBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, navBarLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(notesBtnLabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(navBarLayout.createSequentialGroup()
                .addGroup(navBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, navBarLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(cmntrsBtnLabel))
                    .addGroup(navBarLayout.createSequentialGroup()
                        .addGroup(navBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(navBarLayout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addGroup(navBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(homeBtnLabel)
                                    .addComponent(searchBtnLabel)
                                    .addComponent(homeBtn)
                                    .addComponent(searchBtn)
                                    .addComponent(bookmarkBtn)))
                            .addGroup(navBarLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(bookmarksBtn))
                            .addGroup(navBarLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(audiobookLabel))
                            .addGroup(navBarLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(hostJoinBtnLabel))
                            .addGroup(navBarLayout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(cmntrsBtn))
                            .addGroup(navBarLayout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(hostJoinBtn))
                            .addGroup(navBarLayout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(settingsBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(navBarLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(navBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(journalBtn)
                                    .addComponent(audiobookBtn)))
                            .addGroup(navBarLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(settingsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(navBarLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(navBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(navBarLayout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(bibleBtnLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(libraryBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        navBarLayout.setVerticalGroup(
            navBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(navBarLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(homeBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(homeBtnLabel)
                .addGap(18, 18, 18)
                .addComponent(libraryBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bibleBtnLabel)
                .addGap(18, 18, 18)
                .addComponent(searchBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(searchBtnLabel)
                .addGap(18, 18, 18)
                .addComponent(bookmarkBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bookmarksBtn)
                .addGap(24, 24, 24)
                .addComponent(journalBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(notesBtnLabel)
                .addGap(18, 18, 18)
                .addComponent(audiobookBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(audiobookLabel)
                .addGap(18, 18, 18)
                .addComponent(cmntrsBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmntrsBtnLabel)
                .addGap(18, 18, 18)
                .addComponent(hostJoinBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(hostJoinBtnLabel)
                .addGap(18, 18, 18)
                .addComponent(settingsBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(settingsLabel)
                .addContainerGap(289, Short.MAX_VALUE))
        );

        mainPanel_layered.add(navBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 70, 930));

        bibleTab.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        mainTextArea.setEditable(false);
        mainTextArea.setColumns(20);
        mainTextArea.setFont(new java.awt.Font("Nokia Pure Headline Ultra Light", 1, 18)); // NOI18N
        mainTextArea.setRows(5);
        mainTextScrollPanel.setViewportView(mainTextArea);
        mainTextArea.addCaretListener(e -> {
            if (!highlighterActive || currentHighlightColor == null) return;

            String selectedText = mainTextArea.getSelectedText();
            if (selectedText != null && !selectedText.isEmpty()) {
                try {
                    // Highlight selected text in JTextArea
                    Highlighter.HighlightPainter painter =
                    new DefaultHighlighter.DefaultHighlightPainter(currentHighlightColor);

                    int start = mainTextArea.getSelectionStart();
                    int end = mainTextArea.getSelectionEnd();
                    mainTextArea.getHighlighter().addHighlight(start, end, painter);

                    // Save highlight immediately into SQLite
                    saveHighlight(selectedText, currentHighlightColor);

                    // Live reload after saving to ensure latest highlights are displayed
                    restoreHighlights();

                    // Optional: simulate actual mouse click
                    new Thread(() -> {
                        try {
                            Thread.sleep(5); // 5 ms delay
                            Rectangle caretRect = mainTextArea.modelToView(start);
                            Point textAreaOnScreen = mainTextArea.getLocationOnScreen();
                            int mouseX = textAreaOnScreen.x + caretRect.x + 1;
                            int mouseY = textAreaOnScreen.y + caretRect.y + 1;

                            Robot robot = new Robot();
                            robot.mouseMove(mouseX, mouseY);
                            robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                            robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }).start();

                } catch (BadLocationException ex) {
                    ex.printStackTrace();
                }
            }
        });

        // -------------------- Document Listener (Live Reload) --------------------
        mainTextArea.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                restoreHighlights(); // live reload on insertion
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                restoreHighlights(); // live reload on deletion
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                restoreHighlights(); // live reload on style changes
            }
        });

        bibleTab.add(mainTextScrollPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 870, 860));

        cmtryTabbedPanel.setBackground(new java.awt.Color(255, 255, 255));
        cmtryTabbedPanel.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N

        notesTabPanel.setBackground(new java.awt.Color(255, 255, 255));
        notesTabPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        eyeHide.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8-invisible-15.png"))); // NOI18N
        eyeHide.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eyeHideActionPerformed(evt);
            }
        });
        notesTabLayers.add(eyeHide);
        eyeHide.setBounds(610, 10, 30, 20);

        eyeShow.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8-eye-15.png"))); // NOI18N
        eyeShow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eyeShowActionPerformed(evt);
            }
        });
        notesTabLayers.add(eyeShow);
        eyeShow.setBounds(610, 10, 30, 20);

        saveNote.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/save15.png"))); // NOI18N
        saveNote.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveNoteActionPerformed(evt);
            }
        });
        notesTabLayers.add(saveNote);
        saveNote.setBounds(570, 10, 30, 20);

        saveTick.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/tick15.png"))); // NOI18N
        saveTick.setBorder(null);
        notesTabLayers.add(saveTick);
        saveTick.setBounds(570, 10, 30, 20);

        addNoteBtn.setBackground(new java.awt.Color(204, 204, 204));
        addNoteBtn.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        addNoteBtn.setForeground(new java.awt.Color(102, 102, 102));
        addNoteBtn.setText("Create a new note");
        addNoteBtn.setAlignmentY(0.0F);
        addNoteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addNoteBtnActionPerformed(evt);
            }
        });
        notesTabLayers.add(addNoteBtn);
        addNoteBtn.setBounds(260, 390, 172, 32);

        notesInput.setColumns(20);
        notesInput.setFont(new java.awt.Font("Nokia Pure Headline Ultra Light", 0, 18)); // NOI18N
        notesInput.setRows(5);
        notesInput.setBorder(null);
        notesInput.setFocusable(false);
        notesTabLayers.add(notesInput);
        notesInput.setBounds(10, 10, 630, 810);

        notesTabPanel.add(notesTabLayers, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 660, 820));

        cmtryTabbedPanel.addTab("Notes", notesTabPanel);

        nodesTabPanel.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout nodesTabPanelLayout = new javax.swing.GroupLayout(nodesTabPanel);
        nodesTabPanel.setLayout(nodesTabPanelLayout);
        nodesTabPanelLayout.setHorizontalGroup(
            nodesTabPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 650, Short.MAX_VALUE)
        );
        nodesTabPanelLayout.setVerticalGroup(
            nodesTabPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 825, Short.MAX_VALUE)
        );

        cmtryTabbedPanel.addTab("Nodes", nodesTabPanel);

        cmtrsTabPanel.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout cmtrsTabPanelLayout = new javax.swing.GroupLayout(cmtrsTabPanel);
        cmtrsTabPanel.setLayout(cmtrsTabPanelLayout);
        cmtrsTabPanelLayout.setHorizontalGroup(
            cmtrsTabPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 650, Short.MAX_VALUE)
        );
        cmtrsTabPanelLayout.setVerticalGroup(
            cmtrsTabPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 825, Short.MAX_VALUE)
        );

        cmtryTabbedPanel.addTab("Commentaries", cmtrsTabPanel);

        bibleTab.add(cmtryTabbedPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 110, 650, 860));

        biblestudyTitle.setFont(new java.awt.Font("Nokia Pure Headline Ultra Light", 1, 18)); // NOI18N
        biblestudyTitle.setText("መፅሀፍ ቅዱስ ጥናት");
        bibleTab.add(biblestudyTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(907, 74, -1, 20));

        highlightBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/highlight.png"))); // NOI18N
        highlightBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                highlightBtnActionPerformed(evt);
            }
        });
        bibleTab.add(highlightBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(863, 67, -1, -1));

        boldBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/bold.png"))); // NOI18N
        boldBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boldBtnActionPerformed(evt);
            }
        });
        bibleTab.add(boldBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(831, 67, -1, -1));

        fontSizeSlider.setSnapToTicks(true);
        fontSizeSlider.setFocusable(false);
        fontSizeSlider.setOpaque(true);
        bibleTab.add(fontSizeSlider, new org.netbeans.lib.awtextra.AbsoluteConstraints(721, 67, 104, 27));
        fontSizeSlider.setMinimum(18);
        fontSizeSlider.setMaximum(48);
        fontSizeSlider.setValue(16);   // default starting value

        fontSizeSlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                int size = fontSizeSlider.getValue();
                java.awt.Font currentFont = mainTextArea.getFont();
                mainTextArea.setFont(new java.awt.Font(
                    currentFont.getFamily(),
                    currentFont.getStyle(),
                    size
                ));
            }
        });

        bookChooser.setFont(new java.awt.Font("Nokia Pure Headline Ultra Light", 1, 14)); // NOI18N
        bookChooser.addActionListener(e -> updateChapterChooser());
        bookChooser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bookChooserActionPerformed(evt);
            }
        });
        bibleTab.add(bookChooser, new org.netbeans.lib.awtextra.AbsoluteConstraints(128, 67, 182, -1));
        bookChooser.addActionListener(e ->{
            currentBookIndex = bookChooser.getSelectedIndex();

        });

        chapterChooser.setFont(new java.awt.Font("Nokia Pure Headline Ultra Light", 0, 14)); // NOI18N
        bibleTab.add(chapterChooser, new org.netbeans.lib.awtextra.AbsoluteConstraints(322, 68, 60, -1));
        chapterChooser.addActionListener(e -> {
            int selectedChapterIndex = chapterChooser.getSelectedIndex();
            if (selectedChapterIndex < 0) return;

            int selectedBookIndex = bookChooser.getSelectedIndex();
            if (selectedBookIndex < 0) return;

            int selectedTestamentIndex = testamentChooser.getSelectedIndex();

            // Figure out the folder number
            int folderNumber;
            if (selectedTestamentIndex == 1) {
                folderNumber = selectedBookIndex + 40; // New Testament offset
            } else {
                folderNumber = selectedBookIndex + 1;  // Old Testament
            }

            // Chapter files are named as index+1.pdf
            int chapterNumber = selectedChapterIndex + 1;
            File pdfFile = new File(
                "C:\\Users\\boni\\Desktop\\Files\\The Bible Project\\BibleApp\\src\\main\\resources\\files\\books\\"
                + folderNumber,
                chapterNumber + ".pdf"
            );

            if (!pdfFile.exists()) {
                mainTextArea.setText("Chapter file not found: " + pdfFile.getName());
                return;
            }

            // Load the PDF text into mainTextArea
            try (PDDocument doc = PDDocument.load(pdfFile)) {
                PDFTextStripper stripper = new PDFTextStripper();
                String text = stripper.getText(doc);
                mainTextArea.setText(text.trim());
                mainTextArea.setCaretPosition(0); // scroll to top
            } catch (IOException ex) {
                ex.printStackTrace();
                mainTextArea.setText("Error reading: " + pdfFile.getName());
            }
        });
        chapterChooser.addActionListener(e ->{
            currentChapterIndex = chapterChooser.getSelectedIndex();
        });

        chapterChooser.addActionListener(e -> {
            int selectedChapterIndex = chapterChooser.getSelectedIndex();
            if (selectedChapterIndex < 0) return;

            int selectedBookIndex = bookChooser.getSelectedIndex();
            if (selectedBookIndex < 0) return;

            currentChapterIndex = selectedChapterIndex;
            currentBookIndex = selectedBookIndex;

            // --- Notes DB check ---
            if (NotesDatabase.noteExists(currentBookIndex, currentChapterIndex)) {
                notesInput.setText(NotesDatabase.loadNote(currentBookIndex, currentChapterIndex));
                addNoteBtn.setVisible(false); // hide the add button if note exists
                eyeHide.setVisible(true);
                saveNote.setVisible(true);
            } else {
                notesInput.setText(""); // new note
                addNoteBtn.setVisible(true);
                eyeShow.setVisible(false);
                eyeHide.setVisible(false);
                saveNote.setVisible(false);
                saveTick.setVisible(false);// show the add button if no note exists
            }
        });

        verseChooser.setFont(new java.awt.Font("Nokia Pure Headline Ultra Light", 0, 14)); // NOI18N
        bibleTab.add(verseChooser, new org.netbeans.lib.awtextra.AbsoluteConstraints(394, 68, 59, -1));
        verseChooser.addActionListener(e -> {
            String selected = (String) verseChooser.getSelectedItem();
            if (selected == null || selected.isEmpty()) return;

            int verse;
            try {
                verse = Integer.parseInt(selected.split("-")[0]); // allow "1-2" as valid
            } catch (NumberFormatException ex) {
                return;
            }

            // Skip verse 1 only if it’s exactly "1"
            if (verse == 1 && !selected.contains("-")) return;

            String text = mainTextArea.getText();
            if (text == null || text.isEmpty()) return;

            // Regex: find numbers at start of lines
            Pattern pattern = Pattern.compile("(?m)^(\\d+)\\b");
            Matcher matcher = pattern.matcher(text);

            int start = -1;
            int end = text.length();

            while (matcher.find()) {
                int foundNum = Integer.parseInt(matcher.group(1));
                if (foundNum != verse) continue;

                // skip if previous word is "ምዕራፍ"
                int lineStart = matcher.start(1);
                String lineText = text.substring(0, lineStart);
                String[] words = lineText.split("\\s+");
                if (words.length > 0 && words[words.length - 1].equals("ምዕራፍ")) {
                    return;
                }

                start = matcher.start(1);

                if (matcher.find()) {
                    end = matcher.start(1);
                }
                break;
            }

            if (start != -1) {
                // Scroll to top
                try {
                    Rectangle rect = mainTextArea.modelToView(start);
                    if (rect != null) {
                        JViewport viewport = (JViewport) mainTextArea.getParent();
                        rect.y = Math.max(0, rect.y - mainTextArea.getVisibleRect().height / 2);
                        viewport.setViewPosition(rect.getLocation());
                    }
                } catch (Exception ignored) {}

                // Highlight
                Highlighter highlighter = mainTextArea.getHighlighter();
                highlighter.removeAllHighlights();
                try {
                    final Object tag = highlighter.addHighlight(start, end,
                        new DefaultHighlighter.DefaultHighlightPainter(new Color(173, 216, 230, 128)));

                    // Remove after 3s
                    Timer timer = new Timer(3000, ev -> highlighter.removeHighlight(tag));
                    timer.setRepeats(false);
                    timer.start();

                } catch (BadLocationException ex) {
                    ex.printStackTrace();
                }
            }
        });

        testamentChooser.setFont(new java.awt.Font("Nokia Pure Headline Ultra Light", 1, 14)); // NOI18N
        testamentChooser.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ብሉይ ኪዳን", "አዲስ ኪዳን" }));
        bibleTab.add(testamentChooser, new org.netbeans.lib.awtextra.AbsoluteConstraints(17, 67, 105, -1));

        bookChooserDropDown.setFont(new java.awt.Font("Nokia Pure Headline Ultra Light", 1, 12)); // NOI18N
        bookChooserDropDown.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "አማርኛ", "English", "Afaan Oromoo", " " }));
        bookChooserDropDown.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bookChooserDropDownActionPerformed(evt);
            }
        });
        bibleTab.add(bookChooserDropDown, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 70, 89, -1));

        tabs.addTab("Home", bibleTab);

        dayLabel4.setFont(new java.awt.Font("Nokia Pure Headline Ultra Light", 0, 12)); // NOI18N
        dayLabel4.setText("Mon");

        dayLabel5.setFont(new java.awt.Font("Nokia Pure Headline Ultra Light", 0, 12)); // NOI18N
        dayLabel5.setText("Wed");

        dayLabel6.setFont(new java.awt.Font("Nokia Pure Headline Ultra Light", 0, 12)); // NOI18N
        dayLabel6.setText("Fri");

        month14.setFont(new java.awt.Font("Nokia Pure Headline Ultra Light", 0, 12)); // NOI18N
        month14.setText("Sep");

        month15.setFont(new java.awt.Font("Nokia Pure Headline Ultra Light", 0, 12)); // NOI18N
        month15.setText("Oct");

        month16.setFont(new java.awt.Font("Nokia Pure Headline Ultra Light", 0, 12)); // NOI18N
        month16.setText("Nov");

        month17.setFont(new java.awt.Font("Nokia Pure Headline Ultra Light", 0, 12)); // NOI18N
        month17.setText("Dec");

        month18.setFont(new java.awt.Font("Nokia Pure Headline Ultra Light", 0, 12)); // NOI18N
        month18.setText("Jan");

        month19.setFont(new java.awt.Font("Nokia Pure Headline Ultra Light", 0, 12)); // NOI18N
        month19.setText("Feb");

        month20.setFont(new java.awt.Font("Nokia Pure Headline Ultra Light", 0, 12)); // NOI18N
        month20.setText("Mar");

        month21.setFont(new java.awt.Font("Nokia Pure Headline Ultra Light", 0, 12)); // NOI18N
        month21.setText("Apr");

        month22.setFont(new java.awt.Font("Nokia Pure Headline Ultra Light", 0, 12)); // NOI18N
        month22.setText("May");

        month23.setFont(new java.awt.Font("Nokia Pure Headline Ultra Light", 0, 12)); // NOI18N
        month23.setText("Jun");

        month24.setFont(new java.awt.Font("Nokia Pure Headline Ultra Light", 0, 12)); // NOI18N
        month24.setText("Jul");

        month25.setFont(new java.awt.Font("Nokia Pure Headline Ultra Light", 0, 12)); // NOI18N
        month25.setText("Aug");

        month26.setFont(new java.awt.Font("Nokia Pure Headline Ultra Light", 0, 12)); // NOI18N
        month26.setText("Sep");

        javax.swing.GroupLayout readStatus1Layout = new javax.swing.GroupLayout(readStatus1);
        readStatus1.setLayout(readStatus1Layout);
        readStatus1Layout.setHorizontalGroup(
            readStatus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(readStatus1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(readStatus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(readStatus1Layout.createSequentialGroup()
                        .addGroup(readStatus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(statusDay1, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay2, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay4, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay6, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay3, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay5, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay7, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(readStatus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(statusDay8, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay9, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay11, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay13, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay10, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay12, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay14, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(readStatus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(statusDay15, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay16, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay18, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay20, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay17, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay19, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay21, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(readStatus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(statusDay22, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay23, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay25, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay27, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay24, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay26, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay28, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(readStatus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(statusDay29, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay30, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay32, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay34, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay31, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay33, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay35, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(readStatus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(statusDay36, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay37, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay39, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay41, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay38, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay40, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay42, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(readStatus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(statusDay43, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay44, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay46, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay48, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay45, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay47, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay49, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(readStatus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(statusDay50, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay51, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay53, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay55, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay52, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay54, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay56, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(readStatus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(statusDay57, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay58, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay60, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay62, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay59, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay61, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay63, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(readStatus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(statusDay64, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay65, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay67, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay69, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay66, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay68, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay70, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(readStatus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(statusDay71, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay72, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay74, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay76, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay73, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay75, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay77, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(readStatus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(statusDay78, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay79, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay81, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay83, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay80, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay82, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay84, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(readStatus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(statusDay85, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay86, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay88, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay90, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay87, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay89, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay91, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(readStatus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(statusDay92, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay93, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay95, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay97, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay94, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay96, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay98, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(readStatus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(statusDay99, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay100, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay102, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay104, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay101, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay103, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay105, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(readStatus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(statusDay106, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay107, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay109, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay111, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay108, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay110, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay112, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(readStatus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(statusDay113, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay114, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay116, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay118, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay115, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay117, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay119, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(readStatus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(statusDay120, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay121, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay123, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay125, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay122, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay124, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay126, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(readStatus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(statusDay127, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay128, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay130, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay132, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay129, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay131, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay133, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(readStatus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(statusDay134, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay135, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay137, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay139, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay136, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay138, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay140, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(readStatus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(statusDay141, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay142, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay144, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay146, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay143, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay145, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay147, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(readStatus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(statusDay148, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay149, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay151, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay153, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay150, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay152, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay154, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(readStatus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(statusDay155, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay156, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay158, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay160, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay157, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay159, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay161, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(readStatus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(statusDay162, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay163, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay165, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay167, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay164, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay166, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay168, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(readStatus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(statusDay169, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay170, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay172, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay174, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay171, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay173, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay175, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(readStatus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(statusDay176, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay177, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay179, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay181, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay178, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay180, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay182, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(readStatus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(statusDay183, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay184, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay186, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay188, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay185, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay187, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay189, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(readStatus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(statusDay190, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay191, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay193, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay195, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay192, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay194, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay196, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(readStatus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(statusDay197, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay198, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay200, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay202, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay199, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay201, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay203, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(readStatus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(statusDay204, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay205, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay207, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay209, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay206, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay208, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay210, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(readStatus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(statusDay211, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay212, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay214, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay216, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay213, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay215, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay217, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(readStatus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(statusDay218, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay219, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay221, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay223, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay220, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay222, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay224, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(readStatus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(statusDay225, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay226, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay228, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay230, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay227, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay229, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay231, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(readStatus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(statusDay232, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay233, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay235, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay237, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay234, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay236, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay238, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(readStatus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(statusDay239, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay240, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay242, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay244, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay241, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay243, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay245, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(readStatus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(statusDay246, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay247, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay249, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay251, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay248, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay250, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay252, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(readStatus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(statusDay253, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay254, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay256, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay258, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay255, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay257, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay259, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(readStatus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(statusDay260, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay261, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay263, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay265, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay262, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay264, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay266, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(readStatus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(statusDay267, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay268, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay270, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay272, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay269, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay271, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay273, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(readStatus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(statusDay274, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay275, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay277, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay279, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay276, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay278, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay280, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(readStatus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(statusDay281, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay282, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay284, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay286, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay283, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay285, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay287, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(readStatus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(statusDay288, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay289, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay291, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay293, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay290, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay292, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay294, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(readStatus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(statusDay295, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay296, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay298, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay300, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay297, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay299, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay301, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(readStatus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(statusDay302, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay303, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay305, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay307, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay304, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay306, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay308, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(readStatus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(statusDay309, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay310, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay312, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay314, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay311, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay313, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay315, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(readStatus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(statusDay316, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay317, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay319, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay321, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay318, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay320, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay322, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(readStatus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(statusDay323, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay324, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay326, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay328, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay325, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay327, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay329, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(readStatus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(statusDay330, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay331, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay333, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay335, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay332, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay334, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay336, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(readStatus1Layout.createSequentialGroup()
                        .addComponent(month14)
                        .addGap(70, 70, 70)
                        .addComponent(month15)
                        .addGap(71, 71, 71)
                        .addComponent(month16)
                        .addGap(69, 69, 69)
                        .addComponent(month17)
                        .addGap(69, 69, 69)
                        .addComponent(month18)
                        .addGap(71, 71, 71)
                        .addComponent(month19)
                        .addGap(70, 70, 70)
                        .addComponent(month20)
                        .addGap(69, 69, 69)
                        .addComponent(month21)
                        .addGap(72, 72, 72)
                        .addComponent(month22)
                        .addGap(68, 68, 68)
                        .addComponent(month23)
                        .addGap(71, 71, 71)
                        .addComponent(month24)
                        .addGap(75, 75, 75)
                        .addComponent(month25)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(readStatus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(readStatus1Layout.createSequentialGroup()
                        .addGroup(readStatus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(statusDay337, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay338, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay340, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay342, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay339, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay341, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay343, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(readStatus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(statusDay344, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay345, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay347, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay349, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay346, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay348, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay350, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(readStatus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(statusDay351, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay352, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay354, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay356, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay353, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay355, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay357, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addGroup(readStatus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(statusDay361, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay363, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay360, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay362, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay364, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(readStatus1Layout.createSequentialGroup()
                                .addGroup(readStatus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(statusDay358, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(statusDay359, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(readStatus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(statusDay365, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(statusDay366, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(50, 50, 50)
                        .addGroup(readStatus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dayLabel5)
                            .addComponent(dayLabel4)
                            .addComponent(dayLabel6)))
                    .addComponent(month26))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        readStatus1Layout.setVerticalGroup(
            readStatus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(readStatus1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(readStatus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(readStatus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(readStatus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(readStatus1Layout.createSequentialGroup()
                                .addGroup(readStatus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(readStatus1Layout.createSequentialGroup()
                                        .addComponent(statusDay1, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(statusDay2, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(statusDay3, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(statusDay4, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(statusDay5, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(statusDay6, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(readStatus1Layout.createSequentialGroup()
                                        .addGap(22, 22, 22)
                                        .addComponent(dayLabel4)
                                        .addGap(27, 27, 27)
                                        .addComponent(dayLabel5)
                                        .addGap(26, 26, 26)
                                        .addComponent(dayLabel6)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay7, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(readStatus1Layout.createSequentialGroup()
                                .addComponent(statusDay8, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay9, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay10, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay11, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay12, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay13, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay14, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(readStatus1Layout.createSequentialGroup()
                                .addComponent(statusDay15, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay16, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay17, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay18, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay19, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay20, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay21, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(readStatus1Layout.createSequentialGroup()
                                .addComponent(statusDay22, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay23, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay24, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay25, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay26, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay27, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay28, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(readStatus1Layout.createSequentialGroup()
                                .addComponent(statusDay29, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay30, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay31, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay32, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay33, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay34, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay35, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(readStatus1Layout.createSequentialGroup()
                                .addComponent(statusDay36, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay37, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay38, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay39, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay40, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay41, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay42, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(readStatus1Layout.createSequentialGroup()
                                .addComponent(statusDay43, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay44, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay45, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay46, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay47, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay48, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay49, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(readStatus1Layout.createSequentialGroup()
                                .addComponent(statusDay50, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay51, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay52, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay53, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay54, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay55, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay56, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(readStatus1Layout.createSequentialGroup()
                                .addComponent(statusDay57, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay58, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay59, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay60, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay61, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay62, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay63, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(readStatus1Layout.createSequentialGroup()
                                .addComponent(statusDay64, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay65, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay66, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay67, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay68, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay69, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay70, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(readStatus1Layout.createSequentialGroup()
                                .addComponent(statusDay71, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay72, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay73, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay74, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay75, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay76, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay77, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(readStatus1Layout.createSequentialGroup()
                                .addComponent(statusDay78, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay79, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay80, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay81, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay82, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay83, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay84, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(readStatus1Layout.createSequentialGroup()
                                .addComponent(statusDay85, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay86, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay87, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay88, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay89, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay90, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay91, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(readStatus1Layout.createSequentialGroup()
                                .addComponent(statusDay92, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay93, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay94, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay95, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay96, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay97, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay98, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(readStatus1Layout.createSequentialGroup()
                                .addComponent(statusDay99, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay100, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay101, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay102, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay103, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay104, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay105, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(readStatus1Layout.createSequentialGroup()
                                .addComponent(statusDay106, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay107, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay108, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay109, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay110, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay111, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay112, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(readStatus1Layout.createSequentialGroup()
                                .addComponent(statusDay113, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay114, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay115, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay116, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay117, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay118, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay119, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(readStatus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(readStatus1Layout.createSequentialGroup()
                                .addComponent(statusDay120, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay121, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay122, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay123, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay124, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay125, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay126, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(readStatus1Layout.createSequentialGroup()
                                .addComponent(statusDay127, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay128, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay129, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay130, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay131, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay132, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay133, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(readStatus1Layout.createSequentialGroup()
                                .addComponent(statusDay134, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay135, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay136, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay137, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay138, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay139, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay140, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(readStatus1Layout.createSequentialGroup()
                                .addComponent(statusDay141, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay142, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay143, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay144, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay145, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay146, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay147, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(readStatus1Layout.createSequentialGroup()
                                .addComponent(statusDay148, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay149, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay150, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay151, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay152, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay153, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay154, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(readStatus1Layout.createSequentialGroup()
                                .addComponent(statusDay155, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay156, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay157, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay158, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay159, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay160, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay161, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(readStatus1Layout.createSequentialGroup()
                                .addComponent(statusDay162, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay163, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay164, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay165, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay166, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay167, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay168, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(readStatus1Layout.createSequentialGroup()
                                .addComponent(statusDay169, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay170, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay171, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay172, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay173, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay174, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay175, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(readStatus1Layout.createSequentialGroup()
                                .addComponent(statusDay176, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay177, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay178, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay179, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay180, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay181, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay182, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(readStatus1Layout.createSequentialGroup()
                                .addComponent(statusDay183, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay184, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay185, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay186, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay187, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay188, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay189, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(readStatus1Layout.createSequentialGroup()
                                .addComponent(statusDay190, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay191, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay192, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay193, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay194, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay195, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay196, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(readStatus1Layout.createSequentialGroup()
                                .addComponent(statusDay197, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay198, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay199, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay200, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay201, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay202, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay203, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(readStatus1Layout.createSequentialGroup()
                                .addComponent(statusDay204, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay205, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay206, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay207, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay208, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay209, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay210, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(readStatus1Layout.createSequentialGroup()
                                .addComponent(statusDay211, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay212, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay213, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay214, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay215, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay216, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay217, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(readStatus1Layout.createSequentialGroup()
                                .addComponent(statusDay218, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay219, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay220, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay221, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay222, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay223, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay224, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(readStatus1Layout.createSequentialGroup()
                                .addComponent(statusDay225, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay226, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay227, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay228, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay229, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay230, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay231, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(readStatus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(readStatus1Layout.createSequentialGroup()
                                .addComponent(statusDay232, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay233, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay234, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay235, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay236, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay237, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay238, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(readStatus1Layout.createSequentialGroup()
                                .addComponent(statusDay239, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay240, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay241, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay242, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay243, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay244, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay245, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(readStatus1Layout.createSequentialGroup()
                                .addComponent(statusDay246, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay247, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay248, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay249, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay250, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay251, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay252, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(readStatus1Layout.createSequentialGroup()
                                .addComponent(statusDay253, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay254, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay255, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay256, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay257, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay258, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay259, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(readStatus1Layout.createSequentialGroup()
                                .addComponent(statusDay260, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay261, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay262, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay263, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay264, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay265, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay266, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(readStatus1Layout.createSequentialGroup()
                                .addComponent(statusDay267, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay268, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay269, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay270, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay271, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay272, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay273, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(readStatus1Layout.createSequentialGroup()
                                .addComponent(statusDay274, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay275, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay276, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay277, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay278, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay279, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay280, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(readStatus1Layout.createSequentialGroup()
                                .addComponent(statusDay281, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay282, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay283, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay284, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay285, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay286, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay287, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(readStatus1Layout.createSequentialGroup()
                                .addComponent(statusDay288, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay289, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay290, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay291, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay292, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay293, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay294, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(readStatus1Layout.createSequentialGroup()
                                .addComponent(statusDay295, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay296, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay297, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay298, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay299, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay300, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay301, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(readStatus1Layout.createSequentialGroup()
                                .addComponent(statusDay302, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay303, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay304, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay305, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay306, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay307, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay308, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(readStatus1Layout.createSequentialGroup()
                                .addComponent(statusDay309, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay310, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay311, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay312, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay313, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay314, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay315, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(readStatus1Layout.createSequentialGroup()
                                .addComponent(statusDay316, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay317, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay318, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay319, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay320, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay321, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay322, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(readStatus1Layout.createSequentialGroup()
                                .addComponent(statusDay323, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay324, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay325, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay326, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay327, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay328, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay329, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(readStatus1Layout.createSequentialGroup()
                                .addComponent(statusDay330, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay331, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay332, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay333, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay334, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay335, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay336, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(readStatus1Layout.createSequentialGroup()
                                .addComponent(statusDay337, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay338, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay339, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay340, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay341, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay342, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay343, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(readStatus1Layout.createSequentialGroup()
                            .addComponent(statusDay344, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(statusDay345, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(statusDay346, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(statusDay347, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(statusDay348, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(statusDay349, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(statusDay350, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(readStatus1Layout.createSequentialGroup()
                        .addComponent(statusDay351, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay352, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay353, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay354, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay355, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay356, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay357, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(readStatus1Layout.createSequentialGroup()
                        .addGroup(readStatus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(readStatus1Layout.createSequentialGroup()
                                .addComponent(statusDay358, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay359, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(readStatus1Layout.createSequentialGroup()
                                .addComponent(statusDay365, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay366, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay360, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay361, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay362, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay363, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay364, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(readStatus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(month14)
                    .addComponent(month15)
                    .addComponent(month16)
                    .addComponent(month17)
                    .addComponent(month18)
                    .addComponent(month19)
                    .addComponent(month20)
                    .addComponent(month21)
                    .addComponent(month22)
                    .addComponent(month23)
                    .addComponent(month24)
                    .addComponent(month25)
                    .addComponent(month26))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        bookDescriptionSideBar1.setBackground(new java.awt.Color(40, 43, 45));

        aboutTheBook1.setFont(new java.awt.Font("Nokia Pure Headline Ultra Light", 1, 18)); // NOI18N
        aboutTheBook1.setForeground(new java.awt.Color(255, 255, 255));
        aboutTheBook1.setText("About the book");

        selectedBook.setContentAreaFilled(false);

        readBtn.setBackground(new java.awt.Color(86, 211, 100));
        readBtn.setFont(new java.awt.Font("Nokia Pure Headline Ultra Light", 0, 24)); // NOI18N
        readBtn.setForeground(new java.awt.Color(255, 255, 255));
        readBtn.setText("Read");
        readBtn.setBorderPainted(false);
        readBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                readBtnActionPerformed(evt);
            }
        });

        description.setFont(new java.awt.Font("Nokia Pure Headline Ultra Light", 0, 18)); // NOI18N
        description.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout bookDescriptionSideBar1Layout = new javax.swing.GroupLayout(bookDescriptionSideBar1);
        bookDescriptionSideBar1.setLayout(bookDescriptionSideBar1Layout);
        bookDescriptionSideBar1Layout.setHorizontalGroup(
            bookDescriptionSideBar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bookDescriptionSideBar1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(bookDescriptionSideBar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bookDescriptionSideBar1Layout.createSequentialGroup()
                        .addComponent(readBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(17, 17, 17))
                    .addComponent(aboutTheBook1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(115, 115, 115))
            .addGroup(bookDescriptionSideBar1Layout.createSequentialGroup()
                .addGroup(bookDescriptionSideBar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(bookDescriptionSideBar1Layout.createSequentialGroup()
                        .addGap(84, 84, 84)
                        .addComponent(selectedBook, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(bookDescriptionSideBar1Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(description, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(31, Short.MAX_VALUE))
        );
        bookDescriptionSideBar1Layout.setVerticalGroup(
            bookDescriptionSideBar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bookDescriptionSideBar1Layout.createSequentialGroup()
                .addGap(102, 102, 102)
                .addComponent(aboutTheBook1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(selectedBook, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(description, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(readBtn)
                .addContainerGap(140, Short.MAX_VALUE))
        );

        // Set the icon to currentSelected.png from resources
        URL selectedIconURL = getClass().getResource("/bookTiles/"  +currentSelected+ ".png");
        if (selectedIconURL != null) {
            bookDisplay1.setIcon(new ImageIcon(selectedIconURL));
        } else {
            System.err.println("Icon not found: /bookTiles/" +currentSelected+ ".png");
        }

        bookDisplay1.setFocusPainted(false);
        bookDisplay1.setFocusable(false);
        bookDisplay1.setRequestFocusEnabled(false);
        bookDisplay1.setRolloverEnabled(false);
        bookDisplay1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bookDisplay1ActionPerformed(evt);
            }
        });

        bookDisplay2.setFocusPainted(false);
        bookDisplay2.setFocusable(false);
        bookDisplay2.setRequestFocusEnabled(false);
        bookDisplay2.setRolloverEnabled(false);
        bookDisplay2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bookDisplay2ActionPerformed(evt);
            }
        });

        bookDisplay3.setFocusPainted(false);
        bookDisplay3.setFocusable(false);
        bookDisplay3.setRequestFocusEnabled(false);
        bookDisplay3.setRolloverEnabled(false);
        bookDisplay3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bookDisplay3ActionPerformed(evt);
            }
        });

        bookDisplay4.setFocusPainted(false);
        bookDisplay4.setFocusable(false);
        bookDisplay4.setRequestFocusEnabled(false);
        bookDisplay4.setRolloverEnabled(false);
        bookDisplay4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bookDisplay4ActionPerformed(evt);
            }
        });

        bookDisplay5.setFocusPainted(false);
        bookDisplay5.setFocusable(false);
        bookDisplay5.setRequestFocusEnabled(false);
        bookDisplay5.setRolloverEnabled(false);
        bookDisplay5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bookDisplay5ActionPerformed(evt);
            }
        });

        bookDisplay6.setFocusPainted(false);
        bookDisplay6.setFocusable(false);
        bookDisplay6.setRequestFocusEnabled(false);
        bookDisplay6.setRolloverEnabled(false);
        bookDisplay6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bookDisplay6ActionPerformed(evt);
            }
        });

        bookDisplay7.setFocusPainted(false);
        bookDisplay7.setFocusable(false);
        bookDisplay7.setRequestFocusEnabled(false);
        bookDisplay7.setRolloverEnabled(false);
        bookDisplay7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bookDisplay7ActionPerformed(evt);
            }
        });

        bookDisplay8.setFocusPainted(false);
        bookDisplay8.setFocusable(false);
        bookDisplay8.setRequestFocusEnabled(false);
        bookDisplay8.setRolloverEnabled(false);
        bookDisplay8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bookDisplay8ActionPerformed(evt);
            }
        });

        bookDisplay9.setFocusPainted(false);
        bookDisplay9.setFocusable(false);
        bookDisplay9.setRequestFocusEnabled(false);
        bookDisplay9.setRolloverEnabled(false);
        bookDisplay9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bookDisplay9ActionPerformed(evt);
            }
        });

        bookDisplay10.setFocusPainted(false);
        bookDisplay10.setFocusable(false);
        bookDisplay10.setRequestFocusEnabled(false);
        bookDisplay10.setRolloverEnabled(false);
        bookDisplay10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bookDisplay10ActionPerformed(evt);
            }
        });

        bookDisplay11.setFocusPainted(false);
        bookDisplay11.setFocusable(false);
        bookDisplay11.setRequestFocusEnabled(false);
        bookDisplay11.setRolloverEnabled(false);
        bookDisplay11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bookDisplay11ActionPerformed(evt);
            }
        });

        bookDisplay12.setFocusPainted(false);
        bookDisplay12.setFocusable(false);
        bookDisplay12.setRequestFocusEnabled(false);
        bookDisplay12.setRolloverEnabled(false);
        bookDisplay12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bookDisplay12ActionPerformed(evt);
            }
        });

        exploreMoreBtn1.setBackground(new java.awt.Color(86, 211, 100));
        exploreMoreBtn1.setFont(new java.awt.Font("Nokia Pure Headline Ultra Light", 0, 12)); // NOI18N
        exploreMoreBtn1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8-arrow-right-15.png"))); // NOI18N
        exploreMoreBtn1.setText("Explore More");
        exploreMoreBtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exploreMoreBtn1ActionPerformed(evt);
            }
        });

        reRandomizer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/refresh20.png"))); // NOI18N
        reRandomizer.setContentAreaFilled(false);
        reRandomizer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reRandomizerActionPerformed(evt);
            }
        });

        selectedBookUnderline1.setBackground(new java.awt.Color(86, 211, 100));

        selectedBookUnderline2.setBackground(new java.awt.Color(86, 211, 100));

        selectedBookUnderline3.setBackground(new java.awt.Color(86, 211, 100));
        selectedBookUnderline3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectedBookUnderline3ActionPerformed(evt);
            }
        });

        selectedBookUnderline4.setBackground(new java.awt.Color(86, 211, 100));

        selectedBookUnderline5.setBackground(new java.awt.Color(86, 211, 100));

        selectedBookUnderline6.setBackground(new java.awt.Color(86, 211, 100));

        selectedBookUnderline7.setBackground(new java.awt.Color(86, 211, 100));

        selectedBookUnderline8.setBackground(new java.awt.Color(86, 211, 100));
        selectedBookUnderline8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectedBookUnderline8ActionPerformed(evt);
            }
        });

        selectedBookUnderline9.setBackground(new java.awt.Color(86, 211, 100));
        selectedBookUnderline9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectedBookUnderline9ActionPerformed(evt);
            }
        });

        selectedBookUnderline10.setBackground(new java.awt.Color(86, 211, 100));

        selectedBookUnderline11.setBackground(new java.awt.Color(86, 211, 100));

        selectedBookUnderline12.setBackground(new java.awt.Color(86, 211, 100));

        javax.swing.GroupLayout libraryContentLayout = new javax.swing.GroupLayout(libraryContent);
        libraryContent.setLayout(libraryContentLayout);
        libraryContentLayout.setHorizontalGroup(
            libraryContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(libraryContentLayout.createSequentialGroup()
                .addGroup(libraryContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(libraryContentLayout.createSequentialGroup()
                        .addContainerGap(18, Short.MAX_VALUE)
                        .addGroup(libraryContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(libraryContentLayout.createSequentialGroup()
                                .addComponent(bookDisplay7, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(bookDisplay8, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(bookDisplay9, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(bookDisplay10, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(bookDisplay11, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(bookDisplay12, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(libraryContentLayout.createSequentialGroup()
                                .addGroup(libraryContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(bookDisplay1, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(reRandomizer)
                                    .addGroup(libraryContentLayout.createSequentialGroup()
                                        .addGap(31, 31, 31)
                                        .addComponent(selectedBookUnderline1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(libraryContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(libraryContentLayout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(bookDisplay2, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addGroup(libraryContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, libraryContentLayout.createSequentialGroup()
                                                .addGap(115, 115, 115)
                                                .addComponent(exploreMoreBtn1, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(libraryContentLayout.createSequentialGroup()
                                                .addComponent(bookDisplay3, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(bookDisplay4, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(bookDisplay5, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(bookDisplay6, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(libraryContentLayout.createSequentialGroup()
                                        .addGap(52, 52, 52)
                                        .addComponent(selectedBookUnderline2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(79, 79, 79)
                                        .addComponent(selectedBookUnderline3, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(81, 81, 81)
                                        .addComponent(selectedBookUnderline4, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(selectedBookUnderline5, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(81, 81, 81)
                                        .addComponent(selectedBookUnderline6, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(28, 28, 28))))
                            .addComponent(readStatus1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 1178, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27))
                    .addGroup(libraryContentLayout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addComponent(selectedBookUnderline7, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(78, 78, 78)
                        .addComponent(selectedBookUnderline8, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(85, 85, 85)
                        .addComponent(selectedBookUnderline9, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(79, 79, 79)
                        .addComponent(selectedBookUnderline10, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(78, 78, 78)
                        .addComponent(selectedBookUnderline11, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(selectedBookUnderline12, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(54, 54, 54)))
                .addComponent(bookDescriptionSideBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        libraryContentLayout.setVerticalGroup(
            libraryContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(libraryContentLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(libraryContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(reRandomizer)
                    .addComponent(exploreMoreBtn1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(libraryContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(libraryContentLayout.createSequentialGroup()
                        .addGroup(libraryContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(bookDisplay1, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bookDisplay2, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bookDisplay3, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bookDisplay4, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bookDisplay5, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bookDisplay6, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(libraryContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(libraryContentLayout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(selectedBookUnderline1))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, libraryContentLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(libraryContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(selectedBookUnderline3, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(selectedBookUnderline4, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(selectedBookUnderline5, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(selectedBookUnderline6, javax.swing.GroupLayout.Alignment.TRAILING)))))
                    .addComponent(selectedBookUnderline2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(libraryContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(bookDisplay7, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bookDisplay8, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bookDisplay9, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bookDisplay10, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bookDisplay11, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bookDisplay12, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4)
                .addGroup(libraryContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(selectedBookUnderline7)
                    .addComponent(selectedBookUnderline9)
                    .addComponent(selectedBookUnderline10)
                    .addComponent(selectedBookUnderline11)
                    .addComponent(selectedBookUnderline12)
                    .addComponent(selectedBookUnderline8))
                .addGap(18, 18, 18)
                .addComponent(readStatus1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(236, 236, 236))
            .addGroup(libraryContentLayout.createSequentialGroup()
                .addComponent(bookDescriptionSideBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        int num1 = randomNums.get(0);
        URL iconURL1 = getClass().getResource("/bookTiles/" + num1 + ".png");
        if(iconURL1 != null) {
            bookDisplay1.setIcon(new javax.swing.ImageIcon(iconURL1));
        } else {
            System.err.println("Icon not found: /bookTiles/" + num1 + ".png");
        }
        int num2 = randomNums.get(1);
        URL iconURL2 = getClass().getResource("/bookTiles/" + num2 + ".png");
        if(iconURL2 != null) {
            bookDisplay2.setIcon(new javax.swing.ImageIcon(iconURL2));
        } else {
            System.err.println("Icon not found: /bookTiles/" + num2 + ".png");
        }
        int num3 = randomNums.get(2);
        URL iconURL3 = getClass().getResource("/bookTiles/" + num3 + ".png");
        if(iconURL3 != null) {
            bookDisplay3.setIcon(new javax.swing.ImageIcon(iconURL3));
        } else {
            System.err.println("Icon not found: /bookTiles/" + num3 + ".png");
        }
        int num4 = randomNums.get(3);
        URL iconURL4 = getClass().getResource("/bookTiles/" + num4 + ".png");
        if(iconURL4 != null) {
            bookDisplay4.setIcon(new javax.swing.ImageIcon(iconURL4));
        } else {
            System.err.println("Icon not found: /bookTiles/" + num4 + ".png");
        }
        int num5 = randomNums.get(4);
        URL iconURL5 = getClass().getResource("/bookTiles/" + num5 + ".png");
        if(iconURL5 != null) {
            bookDisplay5.setIcon(new javax.swing.ImageIcon(iconURL5));
        } else {
            System.err.println("Icon not found: /bookTiles/" + num5 + ".png");
        }
        int num6 = randomNums.get(5);
        URL iconURL6 = getClass().getResource("/bookTiles/" + num6 + ".png");
        if(iconURL6 != null) {
            bookDisplay6.setIcon(new javax.swing.ImageIcon(iconURL6));
        } else {
            System.err.println("Icon not found: /bookTiles/" + num6 + ".png");
        }
        int num7 = randomNums.get(6);
        URL iconURL7 = getClass().getResource("/bookTiles/" + num7 + ".png");
        if(iconURL7 != null) {
            bookDisplay7.setIcon(new javax.swing.ImageIcon(iconURL7));
        } else {
            System.err.println("Icon not found: /bookTiles/" + num7 + ".png");
        }
        int num8 = randomNums.get(7);
        URL iconURL8 = getClass().getResource("/bookTiles/" + num8 + ".png");
        if(iconURL8 != null) {
            bookDisplay8.setIcon(new javax.swing.ImageIcon(iconURL8));
        } else {
            System.err.println("Icon not found: /bookTiles/" + num8 + ".png");
        }
        int num9 = randomNums.get(8);
        URL iconURL9 = getClass().getResource("/bookTiles/" + num9 + ".png");
        if(iconURL9 != null) {
            bookDisplay9.setIcon(new javax.swing.ImageIcon(iconURL9));
        } else {
            System.err.println("Icon not found: /bookTiles/" + num9 + ".png");
        }
        int num10 = randomNums.get(9);
        URL iconURL10 = getClass().getResource("/bookTiles/" + num10 + ".png");
        if(iconURL10 != null) {
            bookDisplay10.setIcon(new javax.swing.ImageIcon(iconURL10));
        } else {
            System.err.println("Icon not found: /bookTiles/" + num10 + ".png");
        }
        int num11 = randomNums.get(10);
        URL iconURL11 = getClass().getResource("/bookTiles/" + num11 + ".png");
        if(iconURL11 != null) {
            bookDisplay11.setIcon(new javax.swing.ImageIcon(iconURL11));
        } else {
            System.err.println("Icon not found: /bookTiles/" + num11 + ".png");
        }
        int num12 = randomNums.get(11);
        URL iconURL12 = getClass().getResource("/bookTiles/" + num12 + ".png");
        if(iconURL12 != null) {
            bookDisplay12.setIcon(new javax.swing.ImageIcon(iconURL12));
        } else {
            System.err.println("Icon not found: /bookTiles/" + num12 + ".png");
        }

        javax.swing.GroupLayout libraryTabLayout = new javax.swing.GroupLayout(libraryTab);
        libraryTab.setLayout(libraryTabLayout);
        libraryTabLayout.setHorizontalGroup(
            libraryTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(libraryTabLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(libraryContent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        libraryTabLayout.setVerticalGroup(
            libraryTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, libraryTabLayout.createSequentialGroup()
                .addGap(0, 52, Short.MAX_VALUE)
                .addComponent(libraryContent, javax.swing.GroupLayout.PREFERRED_SIZE, 923, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        tabs.addTab("Notes", libraryTab);

        loadingPrevis.setBackground(new java.awt.Color(255, 255, 255));

        loadingLoop.setIcon(new javax.swing.ImageIcon(getClass().getResource("/animatedIcons/loadingScreen.gif"))); // NOI18N

        loadingBook.setFont(new java.awt.Font("Nokia Pure Headline Ultra Light", 0, 14)); // NOI18N
        loadingBook.setText("loading your book...");

        javax.swing.GroupLayout loadingPrevisLayout = new javax.swing.GroupLayout(loadingPrevis);
        loadingPrevis.setLayout(loadingPrevisLayout);
        loadingPrevisLayout.setHorizontalGroup(
            loadingPrevisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loadingPrevisLayout.createSequentialGroup()
                .addGroup(loadingPrevisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(loadingPrevisLayout.createSequentialGroup()
                        .addGap(170, 170, 170)
                        .addComponent(loadingLoop))
                    .addGroup(loadingPrevisLayout.createSequentialGroup()
                        .addGap(161, 161, 161)
                        .addComponent(loadingBook, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(142, Short.MAX_VALUE))
        );
        loadingPrevisLayout.setVerticalGroup(
            loadingPrevisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loadingPrevisLayout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addComponent(loadingBook)
                .addGap(18, 18, 18)
                .addComponent(loadingLoop, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(56, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout loadingScreenLayout = new javax.swing.GroupLayout(loadingScreen);
        loadingScreen.setLayout(loadingScreenLayout);
        loadingScreenLayout.setHorizontalGroup(
            loadingScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loadingScreenLayout.createSequentialGroup()
                .addGap(555, 555, 555)
                .addComponent(loadingPrevis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(605, Short.MAX_VALUE))
        );
        loadingScreenLayout.setVerticalGroup(
            loadingScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loadingScreenLayout.createSequentialGroup()
                .addGap(351, 351, 351)
                .addComponent(loadingPrevis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(369, Short.MAX_VALUE))
        );

        tabs.addTab("HostJoin", loadingScreen);

        subTabLayered.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        mute.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/mute30.png"))); // NOI18N
        mute.setBorderPainted(false);
        mute.setContentAreaFilled(false);
        mute.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                muteActionPerformed(evt);
            }
        });
        subTabLayered.add(mute, new org.netbeans.lib.awtextra.AbsoluteConstraints(1540, 930, -1, -1));

        unmute.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/sound30.png"))); // NOI18N
        unmute.setBorderPainted(false);
        unmute.setContentAreaFilled(false);
        unmute.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                unmuteActionPerformed(evt);
            }
        });
        subTabLayered.add(unmute, new org.netbeans.lib.awtextra.AbsoluteConstraints(1540, 930, -1, -1));

        bookmarkSavedNotif.setFont(new java.awt.Font("Nokia Pure Headline Ultra Light", 0, 12)); // NOI18N
        bookmarkSavedNotif.setForeground(new java.awt.Color(255, 255, 255));
        bookmarkSavedNotif.setText("Bookmark Saved");
        subTabLayered.add(bookmarkSavedNotif, new org.netbeans.lib.awtextra.AbsoluteConstraints(1450, 340, -1, 40));

        toolBox.setBackground(new java.awt.Color(40, 43, 45));

        bookmark.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/bookmark30W.png"))); // NOI18N
        bookmark.setContentAreaFilled(false);
        bookmark.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bookmarkActionPerformed(evt);
            }
        });

        cafeTheme.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/cafe30.png"))); // NOI18N
        cafeTheme.setContentAreaFilled(false);
        cafeTheme.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cafeThemeActionPerformed(evt);
            }
        });

        treeTheme.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/trees30.png"))); // NOI18N
        treeTheme.setContentAreaFilled(false);
        treeTheme.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                treeThemeActionPerformed(evt);
            }
        });

        rainAmbience.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/rain30.png"))); // NOI18N
        rainAmbience.setContentAreaFilled(false);
        rainAmbience.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rainAmbienceActionPerformed(evt);
            }
        });

        volPLus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/plus30.png"))); // NOI18N
        volPLus.setContentAreaFilled(false);
        volPLus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                volPLusActionPerformed(evt);
            }
        });

        volDisp.setFont(new java.awt.Font("Nokia Pure Headline Ultra Light", 0, 10)); // NOI18N

        volMinus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/minus30.png"))); // NOI18N
        volMinus.setContentAreaFilled(false);
        volMinus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                volMinusActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout toolBoxLayout = new javax.swing.GroupLayout(toolBox);
        toolBox.setLayout(toolBoxLayout);
        toolBoxLayout.setHorizontalGroup(
            toolBoxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, toolBoxLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(toolBoxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cafeTheme, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(treeTheme, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rainAmbience, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(volPLus, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(volDisp, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(volMinus, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(bookmark, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        toolBoxLayout.setVerticalGroup(
            toolBoxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(toolBoxLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(bookmark, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cafeTheme, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(treeTheme, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rainAmbience, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(volPLus, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(volDisp, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(volMinus, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(10, Short.MAX_VALUE))
        );

        subTabLayered.add(toolBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(1540, 340, 40, 250));

        bookScroll.setBackground(new java.awt.Color(255, 246, 236));

        pdfDisplay.setEditable(false);
        pdfDisplay.setBackground(new java.awt.Color(255, 251, 247));
        pdfDisplay.setColumns(20);
        pdfDisplay.setRows(5);
        pdfDisplay.setFocusable(false);
        bookScroll.setViewportView(pdfDisplay);
        startScrollLogger(bookScroll);

        subTabLayered.add(bookScroll, new org.netbeans.lib.awtextra.AbsoluteConstraints(333, 55, 930, 920));
        subTabLayered.add(themer, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 55, 1580, 930));

        spacer.setBorderPainted(false);
        spacer.setContentAreaFilled(false);
        subTabLayered.add(spacer, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 220, 330, -1));

        javax.swing.GroupLayout bookReaderContentLayout = new javax.swing.GroupLayout(bookReaderContent);
        bookReaderContent.setLayout(bookReaderContentLayout);
        bookReaderContentLayout.setHorizontalGroup(
            bookReaderContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(subTabLayered)
        );
        bookReaderContentLayout.setVerticalGroup(
            bookReaderContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(subTabLayered, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        javax.swing.GroupLayout bookReaderSubTabLayout = new javax.swing.GroupLayout(bookReaderSubTab);
        bookReaderSubTab.setLayout(bookReaderSubTabLayout);
        bookReaderSubTabLayout.setHorizontalGroup(
            bookReaderSubTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bookReaderContent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        bookReaderSubTabLayout.setVerticalGroup(
            bookReaderSubTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bookReaderContent, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        tabs.addTab("Commentaries", bookReaderSubTab);
        tabs.addChangeListener(e -> {
            if (tabs.getSelectedIndex() == 3) { // Commentaries tab
                // Delay 1 second before restoring scroll
                new javax.swing.Timer(100, ev -> {
                    ((javax.swing.Timer) ev.getSource()).stop(); // run once

                    JScrollBar verticalBar = bookScroll.getVerticalScrollBar();
                    String dbPath = "bookStack.db";
                    int savedScroll = 0;

                    try (Connection conn = DriverManager.getConnection("jdbc:sqlite:" + dbPath)) {
                        String selectSQL = "SELECT currentScrollIndex FROM scrollStatus WHERE bookIndex = ?";
                        try (PreparedStatement pstmt = conn.prepareStatement(selectSQL)) {
                            pstmt.setInt(1, loadedBook);
                            try (ResultSet rs = pstmt.executeQuery()) {
                                if (rs.next()) {
                                    savedScroll = rs.getInt("currentScrollIndex");
                                }
                            }
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }

                    final int scrollToSet = savedScroll;
                    SwingUtilities.invokeLater(() -> {
                        verticalBar.setValue(scrollToSet);
                        int caretPos = Math.min(pdfDisplay.getDocument().getLength(), scrollToSet);
                        pdfDisplay.setCaretPosition(caretPos);
                    });

                }).start();
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1580, Short.MAX_VALUE)
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 975, Short.MAX_VALUE)
        );

        tabs.addTab("Settings", jPanel14);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1580, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 975, Short.MAX_VALUE)
        );

        tabs.addTab("tab6", jPanel2);

        mainPanel_layered.add(tabs, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, -50, 1580, 1010));
        tabs.addChangeListener(e -> {
            int selectedIndex = tabs.getSelectedIndex();
            if (selectedIndex >= 0 && selectedIndex <= 10) {
                // Clear the background image
                themer.setIcon(null);

                // Pause all clips
                if (cafeClip != null && cafeClip.isRunning()) {
                    cafeClip.stop();
                }
                if (treeClip != null && treeClip.isRunning()) {
                    treeClip.stop();
                }
                if (rainClip != null && rainClip.isRunning()) {
                    rainClip.stop();
                }

                System.out.println("Tabs 0–10 selected: Themer cleared, all music paused");
            }
        });

        tabs.addChangeListener(e -> {
            int selectedIndex = tabs.getSelectedIndex();

            final String dbPath = "bookStack.db";
            final int PAGE_HEIGHT = 1365;       // ~height of one page
            final int SCROLL_THRESHOLD = 5460;  // minimum scroll difference to count
            final int TIME_THRESHOLD_MS = 5 * 60 * 1000; // 5 minutes

            // Holder class for session data
            class SessionState {
                long startTime = 0;
                int startScrollIndex = 0;
                javax.swing.Timer readingTimer = null;
            }

            // Store one SessionState in the tabs property for persistence
            if (tabs.getClientProperty("sessionState") == null) {
                tabs.putClientProperty("sessionState", new SessionState());
            }
            SessionState state = (SessionState) tabs.getClientProperty("sessionState");

            // ========== TAB 3 SELECTED → START TRACKING ==========
            if (selectedIndex == 3) {
                try (Connection conn = DriverManager.getConnection("jdbc:sqlite:" + dbPath);
                    Statement stmt = conn.createStatement()) {

                    ResultSet rs = stmt.executeQuery(
                        "SELECT currentScrollIndex FROM scrollStatus WHERE bookIndex = " + loadedBook + " LIMIT 1"
                    );
                    if (rs.next()) {
                        state.startScrollIndex = rs.getInt("currentScrollIndex");
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                state.startTime = System.currentTimeMillis();
                state.readingTimer = new javax.swing.Timer(1000, evt2 -> {}); // ticks every sec, not doing anything
                state.readingTimer.start();

                System.out.println("📖 Entered Tab 3 → Timer started | Book: " + loadedBook +
                    " | Start Scroll: " + state.startScrollIndex);
            }

            // ========== ANY OTHER TAB SELECTED → STOP TRACKING ==========
            else {
                if (state.readingTimer != null && state.readingTimer.isRunning()) {
                    state.readingTimer.stop();
                    long endTime = System.currentTimeMillis();
                    long duration = endTime - state.startTime; // in ms
                    int endScrollIndex = 0;

                    // Get ending scroll index
                    try (Connection conn = DriverManager.getConnection("jdbc:sqlite:" + dbPath);
                        Statement stmt = conn.createStatement()) {

                        ResultSet rs = stmt.executeQuery(
                            "SELECT currentScrollIndex FROM scrollStatus WHERE bookIndex = " + loadedBook + " LIMIT 1"
                        );
                        if (rs.next()) {
                            endScrollIndex = rs.getInt("currentScrollIndex");
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }

                    int scrollDiff = Math.abs(endScrollIndex - state.startScrollIndex);
                    int pagesRead = (int) Math.ceil((double) scrollDiff / PAGE_HEIGHT); // round up partial pages

                    System.out.println("📘 Leaving Tab 3 → Time: " + (duration / 1000) + "s | ScrollDiff: " +
                        scrollDiff + " | PagesRead: " + pagesRead);

                    // Only log if conditions met
                    if (duration >= TIME_THRESHOLD_MS && scrollDiff > SCROLL_THRESHOLD) {
                        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:" + dbPath)) {

                            // Add pagesRead column if missing (ignore error)
                            try (Statement stmt = conn.createStatement()) {
                                stmt.execute("ALTER TABLE session ADD COLUMN pagesRead INTEGER");
                            } catch (SQLException ignored) {}

                            try (PreparedStatement pstmt = conn.prepareStatement(
                                "INSERT INTO session (bookIndex, readFor, lastRead, scrollDiff, pagesRead) " +
                                "VALUES (?, ?, datetime('now'), ?, ?)"
                            )) {
                                pstmt.setInt(1, loadedBook);                             // book index
                                pstmt.setInt(2, (int) (duration / 1000 / 60));           // minutes
                                pstmt.setInt(3, scrollDiff);                              // scroll diff
                                pstmt.setInt(4, pagesRead);                               // pages read
                                pstmt.executeUpdate();

                                System.out.println("✅ Session saved → Book: " + loadedBook +
                                    " | Time: " + (duration / 1000 / 60) + "min | Pages: " + pagesRead);
                            }

                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    } else {
                        System.out.println("⚠️ Session skipped (too short or no significant scroll)");
                    }

                    // Reset session state
                    state.readingTimer = null;
                    state.startTime = 0;
                    state.startScrollIndex = 0;
                }
            }

            // ========== EXISTING LOGIC: Themer + Audio Control ==========
            if (selectedIndex >= 0 && selectedIndex <= 10) {
                themer.setIcon(null);

                if (cafeClip != null && cafeClip.isRunning()) cafeClip.stop();
                if (treeClip != null && treeClip.isRunning()) treeClip.stop();
                if (rainClip != null && rainClip.isRunning()) rainClip.stop();

                System.out.println("🎵 Tabs 0–10 selected → Themer cleared, all music paused");
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel_layered)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel_layered)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bookChooserDropDownActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bookChooserDropDownActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bookChooserDropDownActionPerformed

    private void restoreBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_restoreBtnActionPerformed
        
    }//GEN-LAST:event_restoreBtnActionPerformed

    private void minimizeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_minimizeBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_minimizeBtnActionPerformed

    private void settingsBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_settingsBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_settingsBtnActionPerformed

    
    
    private void closeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeBtnActionPerformed
           int result = JOptionPane.showConfirmDialog(
            this, 
            "Are you sure you want to exit?", 
            "Warning", 
            JOptionPane.YES_NO_OPTION, 
            JOptionPane.WARNING_MESSAGE
            );

            if (result == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
    }//GEN-LAST:event_closeBtnActionPerformed

    private void bookChooserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bookChooserActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bookChooserActionPerformed

    private void boldBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boldBtnActionPerformed

        isBoldActive = !isBoldActive;

        Font currentFont = mainTextArea.getFont();

        Font newFont = currentFont.deriveFont(isBoldActive ? Font.BOLD : Font.PLAIN);
        mainTextArea.setFont(newFont);

        boldBtn.setBackground(isBoldActive ? Color.LIGHT_GRAY : UIManager.getColor("Button.background"));
    }//GEN-LAST:event_boldBtnActionPerformed

    private void highlightBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_highlightBtnActionPerformed
        JPopupMenu popup = new JPopupMenu();
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 4, 5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
        panel.setBackground(Color.WHITE);

        // Create color buttons
        JButton yellowBtn = new JButton();
        yellowBtn.setBackground(Color.YELLOW);
        yellowBtn.setPreferredSize(new Dimension(18, 18));
        yellowBtn.addActionListener(e -> {
            currentHighlightColor = Color.YELLOW;
            highlightBtn.setBackground(Color.YELLOW);
            highlighterActive = true;   // activate highlighter
            popup.setVisible(false);
            mainTextArea.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
        });

        JButton greenBtn = new JButton();
        greenBtn.setBackground(Color.GREEN);
        greenBtn.setPreferredSize(new Dimension(18, 18));
        greenBtn.addActionListener(e -> {
            currentHighlightColor = Color.GREEN;
            highlightBtn.setBackground(Color.GREEN);
            highlighterActive = true;
            popup.setVisible(false);
            mainTextArea.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
        });

        JButton blueBtn = new JButton();
        blueBtn.setBackground(Color.CYAN);
        blueBtn.setPreferredSize(new Dimension(18, 18));
        blueBtn.addActionListener(e -> {
            currentHighlightColor = Color.CYAN;
            highlightBtn.setBackground(Color.CYAN);
            highlighterActive = true;
            popup.setVisible(false);
            mainTextArea.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
        });

        JButton pinkBtn = new JButton();
        pinkBtn.setBackground(Color.PINK);
        pinkBtn.setPreferredSize(new Dimension(18, 18));
        pinkBtn.addActionListener(e -> {
            currentHighlightColor = Color.PINK;
            highlightBtn.setBackground(Color.PINK);
            highlighterActive = true;
            popup.setVisible(false);
            mainTextArea.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
        });

        panel.add(yellowBtn);
        panel.add(greenBtn);
        panel.add(blueBtn);
        panel.add(pinkBtn);

        popup.add(panel);

        // Toggle behavior: if already active, clicking the button deactivates
        if (highlighterActive) {
            highlighterActive = false;
            currentHighlightColor = null;
            highlightBtn.setBackground(null); // reset button color
            mainTextArea.setCursor(Cursor.getDefaultCursor());
        } else {
            popup.show(highlightBtn, -panel.getPreferredSize().width, 0); // show color selection
        }
    }//GEN-LAST:event_highlightBtnActionPerformed

    private void libraryBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_libraryBtnActionPerformed
        tabs.setSelectedIndex(1);
    }//GEN-LAST:event_libraryBtnActionPerformed

    private void addNoteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addNoteBtnActionPerformed
        
    }//GEN-LAST:event_addNoteBtnActionPerformed

    private void homeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_homeBtnActionPerformed
        tabs.setSelectedIndex(0);
    }//GEN-LAST:event_homeBtnActionPerformed

    private void eyeShowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eyeShowActionPerformed
        // Toggle visibility
        notesInput.setVisible(true);

        // Optional: refresh the parent container so the change takes effect immediately
        notesTabLayers.revalidate();
        notesTabLayers.repaint();
        eyeHide.setVisible(true);
        eyeShow.setVisible(false);
    }//GEN-LAST:event_eyeShowActionPerformed

    private void eyeHideActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eyeHideActionPerformed
        // Toggle visibility
        notesInput.setVisible(!notesInput.isVisible());

        // Optional: refresh the parent container so the change takes effect immediately
        notesTabLayers.revalidate();
        notesTabLayers.repaint();
        eyeHide.setVisible(false);
        eyeShow.setVisible(true);
    }//GEN-LAST:event_eyeHideActionPerformed

    private void saveNoteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveNoteActionPerformed
        saveTick.setVisible(true);
        saveNote.setVisible(false);
        
    }//GEN-LAST:event_saveNoteActionPerformed

    private void bookDisp1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bookDisp1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bookDisp1ActionPerformed

    private void exploreMoreBtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exploreMoreBtn1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_exploreMoreBtn1ActionPerformed

    private void bookDisplay1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bookDisplay1ActionPerformed
        selectedBookUnderline1.setContentAreaFilled(true);
        selectedBookUnderline2.setContentAreaFilled(false);
        selectedBookUnderline3.setContentAreaFilled(false);
        selectedBookUnderline4.setContentAreaFilled(false);
        selectedBookUnderline5.setContentAreaFilled(false);
        selectedBookUnderline6.setContentAreaFilled(false);
        selectedBookUnderline7.setContentAreaFilled(false);
        selectedBookUnderline8.setContentAreaFilled(false);
        selectedBookUnderline9.setContentAreaFilled(false);
        selectedBookUnderline10.setContentAreaFilled(false);
        selectedBookUnderline11.setContentAreaFilled(false);
        selectedBookUnderline12.setContentAreaFilled(false);
        
        
        currentSelected = 1;
        updateSelectedBookIcon();
        updateBookDetails();
        
    }//GEN-LAST:event_bookDisplay1ActionPerformed

    private void reRandomizerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reRandomizerActionPerformed
        // Re-generate 12 random numbers
        randomNums = get12RandomBookNumbers();

        // Update all book display buttons
        updateBookTiles();
        
        selectedBookUnderline1.setContentAreaFilled(false);
        selectedBookUnderline2.setContentAreaFilled(false);
        selectedBookUnderline3.setContentAreaFilled(false);
        selectedBookUnderline4.setContentAreaFilled(false);
        selectedBookUnderline5.setContentAreaFilled(false);
        selectedBookUnderline6.setContentAreaFilled(false);
        selectedBookUnderline7.setContentAreaFilled(false);
        selectedBookUnderline8.setContentAreaFilled(false);
        selectedBookUnderline9.setContentAreaFilled(false);
        selectedBookUnderline10.setContentAreaFilled(false);
        selectedBookUnderline11.setContentAreaFilled(false);
        selectedBookUnderline12.setContentAreaFilled(false);
        
        

    }//GEN-LAST:event_reRandomizerActionPerformed

    private void selectedBookUnderline3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectedBookUnderline3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_selectedBookUnderline3ActionPerformed

    private void selectedBookUnderline9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectedBookUnderline9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_selectedBookUnderline9ActionPerformed

    private void selectedBookUnderline8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectedBookUnderline8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_selectedBookUnderline8ActionPerformed

    private void bookDisplay2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bookDisplay2ActionPerformed
        selectedBookUnderline1.setContentAreaFilled(false);
        selectedBookUnderline2.setContentAreaFilled(true);
        selectedBookUnderline3.setContentAreaFilled(false);
        selectedBookUnderline4.setContentAreaFilled(false);
        selectedBookUnderline5.setContentAreaFilled(false);
        selectedBookUnderline6.setContentAreaFilled(false);
        selectedBookUnderline7.setContentAreaFilled(false);
        selectedBookUnderline8.setContentAreaFilled(false);
        selectedBookUnderline9.setContentAreaFilled(false);
        selectedBookUnderline10.setContentAreaFilled(false);
        selectedBookUnderline11.setContentAreaFilled(false);
        selectedBookUnderline12.setContentAreaFilled(false);
        
        currentSelected = 2;
        updateSelectedBookIcon();
        updateBookDetails();
    }//GEN-LAST:event_bookDisplay2ActionPerformed

    private void bookDisplay3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bookDisplay3ActionPerformed
        selectedBookUnderline1.setContentAreaFilled(false);
        selectedBookUnderline2.setContentAreaFilled(false);
        selectedBookUnderline3.setContentAreaFilled(true);
        selectedBookUnderline4.setContentAreaFilled(false);
        selectedBookUnderline5.setContentAreaFilled(false);
        selectedBookUnderline6.setContentAreaFilled(false);
        selectedBookUnderline7.setContentAreaFilled(false);
        selectedBookUnderline8.setContentAreaFilled(false);
        selectedBookUnderline9.setContentAreaFilled(false);
        selectedBookUnderline10.setContentAreaFilled(false);
        selectedBookUnderline11.setContentAreaFilled(false);
        selectedBookUnderline12.setContentAreaFilled(false);
        
        currentSelected = 3;
        updateSelectedBookIcon();
        updateBookDetails();
    }//GEN-LAST:event_bookDisplay3ActionPerformed

    private void bookDisplay4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bookDisplay4ActionPerformed
        selectedBookUnderline1.setContentAreaFilled(false);
        selectedBookUnderline2.setContentAreaFilled(false);
        selectedBookUnderline3.setContentAreaFilled(false);
        selectedBookUnderline4.setContentAreaFilled(true);
        selectedBookUnderline5.setContentAreaFilled(false);
        selectedBookUnderline6.setContentAreaFilled(false);
        selectedBookUnderline7.setContentAreaFilled(false);
        selectedBookUnderline8.setContentAreaFilled(false);
        selectedBookUnderline9.setContentAreaFilled(false);
        selectedBookUnderline10.setContentAreaFilled(false);
        selectedBookUnderline11.setContentAreaFilled(false);
        selectedBookUnderline12.setContentAreaFilled(false);
        
        currentSelected = 4;
        updateSelectedBookIcon();
        updateBookDetails();
    }//GEN-LAST:event_bookDisplay4ActionPerformed

    private void bookDisplay5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bookDisplay5ActionPerformed
        selectedBookUnderline1.setContentAreaFilled(false);
        selectedBookUnderline2.setContentAreaFilled(false);
        selectedBookUnderline3.setContentAreaFilled(false);
        selectedBookUnderline4.setContentAreaFilled(false);
        selectedBookUnderline5.setContentAreaFilled(true);
        selectedBookUnderline6.setContentAreaFilled(false);
        selectedBookUnderline7.setContentAreaFilled(false);
        selectedBookUnderline8.setContentAreaFilled(false);
        selectedBookUnderline9.setContentAreaFilled(false);
        selectedBookUnderline10.setContentAreaFilled(false);
        selectedBookUnderline11.setContentAreaFilled(false);
        selectedBookUnderline12.setContentAreaFilled(false);
        
        currentSelected = 5;
        updateSelectedBookIcon();
        updateBookDetails();
    }//GEN-LAST:event_bookDisplay5ActionPerformed

    private void bookDisplay6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bookDisplay6ActionPerformed
        selectedBookUnderline1.setContentAreaFilled(false);
        selectedBookUnderline2.setContentAreaFilled(false);
        selectedBookUnderline3.setContentAreaFilled(false);
        selectedBookUnderline4.setContentAreaFilled(false);
        selectedBookUnderline5.setContentAreaFilled(false);
        selectedBookUnderline6.setContentAreaFilled(true);
        selectedBookUnderline7.setContentAreaFilled(false);
        selectedBookUnderline8.setContentAreaFilled(false);
        selectedBookUnderline9.setContentAreaFilled(false);
        selectedBookUnderline10.setContentAreaFilled(false);
        selectedBookUnderline11.setContentAreaFilled(false);
        selectedBookUnderline12.setContentAreaFilled(false);
        
        currentSelected = 6;
        updateSelectedBookIcon();
        updateBookDetails();
    }//GEN-LAST:event_bookDisplay6ActionPerformed

    private void bookDisplay7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bookDisplay7ActionPerformed
        selectedBookUnderline1.setContentAreaFilled(false);
        selectedBookUnderline2.setContentAreaFilled(false);
        selectedBookUnderline3.setContentAreaFilled(false);
        selectedBookUnderline4.setContentAreaFilled(false);
        selectedBookUnderline5.setContentAreaFilled(false);
        selectedBookUnderline6.setContentAreaFilled(false);
        selectedBookUnderline7.setContentAreaFilled(true);
        selectedBookUnderline8.setContentAreaFilled(false);
        selectedBookUnderline9.setContentAreaFilled(false);
        selectedBookUnderline10.setContentAreaFilled(false);
        selectedBookUnderline11.setContentAreaFilled(false);
        selectedBookUnderline12.setContentAreaFilled(false);
        
        currentSelected = 7;
        updateSelectedBookIcon();
        updateBookDetails();
    }//GEN-LAST:event_bookDisplay7ActionPerformed

    private void bookDisplay8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bookDisplay8ActionPerformed
        selectedBookUnderline1.setContentAreaFilled(false);
        selectedBookUnderline2.setContentAreaFilled(false);
        selectedBookUnderline3.setContentAreaFilled(false);
        selectedBookUnderline4.setContentAreaFilled(false);
        selectedBookUnderline5.setContentAreaFilled(false);
        selectedBookUnderline6.setContentAreaFilled(false);
        selectedBookUnderline7.setContentAreaFilled(false);
        selectedBookUnderline8.setContentAreaFilled(true);
        selectedBookUnderline9.setContentAreaFilled(false);
        selectedBookUnderline10.setContentAreaFilled(false);
        selectedBookUnderline11.setContentAreaFilled(false);
        selectedBookUnderline12.setContentAreaFilled(false);
        
        currentSelected = 8;
        updateSelectedBookIcon();
        updateBookDetails();
    }//GEN-LAST:event_bookDisplay8ActionPerformed

    private void bookDisplay9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bookDisplay9ActionPerformed
        selectedBookUnderline1.setContentAreaFilled(false);
        selectedBookUnderline2.setContentAreaFilled(false);
        selectedBookUnderline3.setContentAreaFilled(false);
        selectedBookUnderline4.setContentAreaFilled(false);
        selectedBookUnderline5.setContentAreaFilled(false);
        selectedBookUnderline6.setContentAreaFilled(false);
        selectedBookUnderline7.setContentAreaFilled(false);
        selectedBookUnderline8.setContentAreaFilled(false);
        selectedBookUnderline9.setContentAreaFilled(true);
        selectedBookUnderline10.setContentAreaFilled(false);
        selectedBookUnderline11.setContentAreaFilled(false);
        selectedBookUnderline12.setContentAreaFilled(false);
        
        currentSelected = 9;
        updateSelectedBookIcon();
        updateBookDetails();
    }//GEN-LAST:event_bookDisplay9ActionPerformed

    private void bookDisplay10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bookDisplay10ActionPerformed
        selectedBookUnderline1.setContentAreaFilled(false);
        selectedBookUnderline2.setContentAreaFilled(false);
        selectedBookUnderline3.setContentAreaFilled(false);
        selectedBookUnderline4.setContentAreaFilled(false);
        selectedBookUnderline5.setContentAreaFilled(false);
        selectedBookUnderline6.setContentAreaFilled(false);
        selectedBookUnderline7.setContentAreaFilled(false);
        selectedBookUnderline8.setContentAreaFilled(false);
        selectedBookUnderline9.setContentAreaFilled(false);
        selectedBookUnderline10.setContentAreaFilled(true);
        selectedBookUnderline11.setContentAreaFilled(false);
        selectedBookUnderline12.setContentAreaFilled(false);
        
        currentSelected = 10;
        updateSelectedBookIcon();
        updateBookDetails();
    }//GEN-LAST:event_bookDisplay10ActionPerformed

    private void bookDisplay11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bookDisplay11ActionPerformed
        selectedBookUnderline1.setContentAreaFilled(false);
        selectedBookUnderline2.setContentAreaFilled(false);
        selectedBookUnderline3.setContentAreaFilled(false);
        selectedBookUnderline4.setContentAreaFilled(false);
        selectedBookUnderline5.setContentAreaFilled(false);
        selectedBookUnderline6.setContentAreaFilled(false);
        selectedBookUnderline7.setContentAreaFilled(false);
        selectedBookUnderline8.setContentAreaFilled(false);
        selectedBookUnderline9.setContentAreaFilled(false);
        selectedBookUnderline10.setContentAreaFilled(false);
        selectedBookUnderline11.setContentAreaFilled(true);
        selectedBookUnderline12.setContentAreaFilled(false);
        
        currentSelected = 11;
        updateSelectedBookIcon();
        updateBookDetails();
    }//GEN-LAST:event_bookDisplay11ActionPerformed

    private void bookDisplay12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bookDisplay12ActionPerformed
        selectedBookUnderline1.setContentAreaFilled(false);
        selectedBookUnderline2.setContentAreaFilled(false);
        selectedBookUnderline3.setContentAreaFilled(false);
        selectedBookUnderline4.setContentAreaFilled(false);
        selectedBookUnderline5.setContentAreaFilled(false);
        selectedBookUnderline6.setContentAreaFilled(false);
        selectedBookUnderline7.setContentAreaFilled(false);
        selectedBookUnderline8.setContentAreaFilled(false);
        selectedBookUnderline9.setContentAreaFilled(false);
        selectedBookUnderline10.setContentAreaFilled(false);
        selectedBookUnderline11.setContentAreaFilled(false);
        selectedBookUnderline12.setContentAreaFilled(true);
        
        currentSelected = 12;
        updateSelectedBookIcon();
        updateBookDetails();
    }//GEN-LAST:event_bookDisplay12ActionPerformed

    private void readBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_readBtnActionPerformed
    // Immediately switch to loading tab
    tabs.setSelectedIndex(2);

    // Get the bookIndex early
    int assignedNum = randomNums.get(currentSelected - 1);
    loadedBook = assignedNum;

    // Background worker to load the PDF
    SwingWorker<JTextPane, Void> worker = new SwingWorker<>() {
        @Override
        protected JTextPane doInBackground() throws Exception {
            String fileName = null;

            // Query the database
            try (Connection conn = DriverManager.getConnection("jdbc:sqlite:bookStack.db")) {
                String query = "SELECT fileName FROM bookStack WHERE bookIndex=?";
                try (PreparedStatement ps = conn.prepareStatement(query)) {
                    ps.setInt(1, assignedNum);
                    try (ResultSet rs = ps.executeQuery()) {
                        if (rs.next()) {
                            fileName = rs.getString("fileName");
                        } else {
                            SwingUtilities.invokeLater(() -> 
                                JOptionPane.showMessageDialog(null, "Book not found in database!")
                            );
                            return null;
                        }
                    }
                }
            }

            // Load PDF file
            File pdfFile = new File("src/main/resources/bookStack/" + fileName);
            if (!pdfFile.exists()) {
                SwingUtilities.invokeLater(() -> 
                    JOptionPane.showMessageDialog(null, "PDF file not found: " + pdfFile.getAbsolutePath())
                );
                return null;
            }

            // Extract text
            String text;
            try (PDDocument document = PDDocument.load(pdfFile)) {
                PDFTextStripper stripper = new PDFTextStripper();
                text = stripper.getText(document);
            }

            // Convert to HTML
            String htmlText = "<html><body style='font-family:Serif; font-size:20pt; "
                    + "text-align:center; background-color:rgb(255,251,247)'>"
                    + text.replace("\n", "<br>")
                    + "</body></html>";

            // Prepare JTextPane
            JTextPane pdfPane = new JTextPane();
            pdfPane.setContentType("text/html");
            pdfPane.setText(htmlText);
            pdfPane.setEditable(false);
            pdfPane.setBackground(new Color(255, 251, 247));
            return pdfPane;
        }

        @Override
        protected void done() {
            try {
                JTextPane pdfPane = get();
                if (pdfPane != null) {
                    bookScroll.setViewportView(pdfPane);
                    //bookScroll.getVerticalScrollBar().setValue(currentScrollState);

                    // Switch to the book display tab
                    tabs.setSelectedIndex(3);

                    // Refresh
                    bookScroll.requestFocusInWindow(); 
                    bookScroll.revalidate();           
                    bookScroll.repaint();
                }
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error loading book: " + e.getMessage());
            }
        }
    };

    worker.execute(); // start background loading

    }//GEN-LAST:event_readBtnActionPerformed

    private void volMinusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_volMinusActionPerformed
        if (lastPlayedClip != null && lastPlayedClip.isRunning()) {
            try {
                FloatControl gainControl = (FloatControl) lastPlayedClip.getControl(FloatControl.Type.MASTER_GAIN);

                // Get current volume in dB
                float current = gainControl.getValue();

                // Decrease by 6 dB for noticeable change, clamp to min
                float min = gainControl.getMinimum();
                float newGain = Math.max(current - 6.0f, min);

                gainControl.setValue(newGain);
                System.out.println("Volume decreased to: " + newGain + " dB");
                updateVolumeDisplay();
            } catch (IllegalArgumentException ex) {
                System.out.println("Volume control not supported for this clip.");
            }
        } else {
            System.out.println("No clip is currently running.");
        }
    }//GEN-LAST:event_volMinusActionPerformed

    private void bookmarkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bookmarkActionPerformed
        int bookIndex = loadedBook; // assuming this field exists
        int scrollIndex = bookScroll.getVerticalScrollBar().getValue();

        String dbPath = "bookStack.db";
        String insertQuery = "INSERT INTO bookmarks (bookIndex, scrollIndex) VALUES (?, ?)";

        // --- Save to DB ---
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:" + dbPath);
             PreparedStatement pstmt = conn.prepareStatement(insertQuery)) {

            pstmt.setInt(1, bookIndex);
            pstmt.setInt(2, scrollIndex);
            pstmt.executeUpdate();

            System.out.println("Bookmark saved for bookIndex: " + bookIndex + " | scrollIndex: " + scrollIndex);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        
        bookmark.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/bookmark.png")));
        bookmarkSavedNotif.setVisible(true);

        
        javax.swing.Timer timer = new javax.swing.Timer(1500, e -> {
            bookmarkSavedNotif.setVisible(false);
            bookmark.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/bookmark30W.png")));
        });
        timer.setRepeats(false);
        timer.start(); 
    }//GEN-LAST:event_bookmarkActionPerformed

    private void cafeThemeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cafeThemeActionPerformed
        //mute.setVisible(false);
        //unmute.setVisible(false);
        if(mute.isVisible()){
            mute.setVisible(false);
            unmute.setVisible(true);
        }
        volDisp.setText("100");
        if (treeClip != null && treeClip.isRunning()) {
            treeClip.stop();
            treeClip.close();
        }
        if (rainClip != null && rainClip.isRunning()) {
            rainClip.stop();
            rainClip.close();
        }

        // Stop previous cafe clip
        if (cafeClip != null && cafeClip.isRunning()) {
            cafeClip.stop();
            cafeClip.close();
        }

        // Load background image
        java.net.URL imgURL = getClass().getResource("/themes/cafe" + itr + ".jpg");
        if (imgURL != null) {
            themer.setIcon(new javax.swing.ImageIcon(imgURL));
            themer.repaint();
        } else {
            System.err.println("⚠️ Image not found: cafe" + itr + ".jpg");
        }

        // Load and loop the matching cafe .wav file
        try {
            java.net.URL soundURL = getClass().getResource("/ambienceSfx/cafe" + itr + ".wav");
            if (soundURL != null) {
                AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundURL);
                cafeClip = AudioSystem.getClip();
                cafeClip.open(audioIn);
                cafeClip.loop(Clip.LOOP_CONTINUOUSLY); // instant seamless loop
            } else {
                System.err.println("⚠️ Sound not found: cafe" + itr + ".wav");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Cycle images/sounds
        itr++;
        if (itr == 4) itr = 1;

        System.out.println("Cafe theme #" + itr + " activated");

        if (!mute.isVisible()) {
            unmute.setVisible(true);
        }
        lastPlayedClip = cafeClip;
    }//GEN-LAST:event_cafeThemeActionPerformed

    private void treeThemeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_treeThemeActionPerformed
        //mute.setVisible(false);
        //unmute.setVisible(false);
        if(mute.isVisible()){
            mute.setVisible(false);
            unmute.setVisible(true);
        }
        volDisp.setText("100");
        if (rainClip != null && rainClip.isRunning()) {
            rainClip.stop();
            rainClip.close();
        }
        if (cafeClip != null && cafeClip.isRunning()) {
            cafeClip.stop();
            cafeClip.close();
        }
        // Stop previous tree clip
        if (treeClip != null && treeClip.isRunning()) {
            treeClip.stop();
            treeClip.close();
        }

        // Load background image
        java.net.URL imgURL = getClass().getResource("/themes/trees" + itr + ".jpg");
        if (imgURL != null) {
            themer.setIcon(new javax.swing.ImageIcon(imgURL));
            themer.repaint();
        } else {
            System.err.println("⚠️ Image not found: trees" + itr + ".jpg");
        }

        // Load and loop matching tree sound
        try {
            java.net.URL soundURL = getClass().getResource("/ambienceSfx/trees" + itr + ".wav");
            if (soundURL != null) {
                AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundURL);
                treeClip = AudioSystem.getClip();
                treeClip.open(audioIn);
                treeClip.loop(Clip.LOOP_CONTINUOUSLY);
            } else {
                System.err.println("⚠️ Sound not found: trees" + itr + ".wav");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Cycle images/sounds
        itr++;
        if (itr == 4) itr = 1;

        System.out.println("Tree theme #" + itr + " activated");

        if (!mute.isVisible()) {
            unmute.setVisible(true);
        }
        lastPlayedClip = treeClip;
    }//GEN-LAST:event_treeThemeActionPerformed

    private void rainAmbienceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rainAmbienceActionPerformed
        //mute.setVisible(false);
        //unmute.setVisible(false);  
        if(mute.isVisible()){
            mute.setVisible(false);
            unmute.setVisible(true);
        }
        volDisp.setText("100");
        
        if (cafeClip != null && cafeClip.isRunning()) {
            cafeClip.stop();
            cafeClip.close();
        }

        // Stop tree sound for previous itr
        if (treeClip != null && treeClip.isRunning()) {
            treeClip.stop();
            treeClip.close();
        }

        // Stop current rainClip if already running
        if (rainClip != null && rainClip.isRunning()) {
            rainClip.stop();
            rainClip.close();
        }

        // Load background image
        java.net.URL imgURL = getClass().getResource("/themes/rain" + itr + ".jpg");
        if (imgURL != null) {
            themer.setIcon(new javax.swing.ImageIcon(imgURL));
            themer.repaint();
        } else {
            System.err.println("⚠️ Image not found: rain" + itr + ".jpg");
        }

        // Load and play rain ambience (loop continuously)
        try {
            java.net.URL soundURL = getClass().getResource("/ambienceSfx/rain" + itr + ".wav");
            if (soundURL != null) {
                AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundURL);
                rainClip = AudioSystem.getClip();
                rainClip.open(audioIn);
                rainClip.loop(Clip.LOOP_CONTINUOUSLY);
            } else {
                System.err.println("⚠️ Sound not found: rain" + itr + ".wav");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Cycle through images/sounds
        itr++;
        if (itr == 4) {
            itr = 1;
        }

        System.out.println("Rain ambience #" + itr + " activated");

        if (!mute.isVisible()) {
            unmute.setVisible(true);
        }
        lastPlayedClip = rainClip;
    }//GEN-LAST:event_rainAmbienceActionPerformed

    private void muteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_muteActionPerformed
       mute.setVisible(false);
       unmute.setVisible(true);
       
        if (lastPlayedClip != null) {
            try {
                // Resume the clip from where it left
                lastPlayedClip.setMicrosecondPosition(lastClipPosition);
                lastPlayedClip.loop(Clip.LOOP_CONTINUOUSLY);
                lastPlayedClip.start();
                System.out.println("🔊 Resuming last ambience");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }       
    }//GEN-LAST:event_muteActionPerformed

    private void unmuteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_unmuteActionPerformed
        unmute.setVisible(false);
        mute.setVisible(true);
           
        if (cafeClip != null && cafeClip.isRunning()) {
            lastPlayedClip = cafeClip;
            lastClipPosition = cafeClip.getMicrosecondPosition();
            cafeClip.stop();
        } else if (treeClip != null && treeClip.isRunning()) {
            lastPlayedClip = treeClip;
            lastClipPosition = treeClip.getMicrosecondPosition();
            treeClip.stop();
        } else if (rainClip != null && rainClip.isRunning()) {
            lastPlayedClip = rainClip;
            lastClipPosition = rainClip.getMicrosecondPosition();
            rainClip.stop();
        } else {
            lastPlayedClip = null;
            lastClipPosition = 0;
        }

        unmute.setVisible(false);
        mute.setVisible(true);

        System.out.println("🔇 All ambience muted");        
    }//GEN-LAST:event_unmuteActionPerformed

    private void volPLusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_volPLusActionPerformed
        if (lastPlayedClip != null && lastPlayedClip.isRunning()) {
            try {
                FloatControl gainControl = (FloatControl) lastPlayedClip.getControl(FloatControl.Type.MASTER_GAIN);

                // Get current volume in dB
                float current = gainControl.getValue();

                // Increase by 6 dB for noticeable change, clamp to max
                float max = gainControl.getMaximum();
                float newGain = Math.min(current + 6.0f, max);

                gainControl.setValue(newGain);
                System.out.println("Volume increased to: " + newGain + " dB");
                updateVolumeDisplay();
            } catch (IllegalArgumentException ex) {
                System.out.println("Volume control not supported for this clip.");
            }
        } else {
            System.out.println("No clip is currently running.");
        }
    }//GEN-LAST:event_volPLusActionPerformed

    
   
 
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(mainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(mainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(mainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(mainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        
         try {
            UIManager.setLookAndFeel(new FlatLightLaf()); 
            
        } catch (Exception ex) {
            System.err.println("Failed to initialize FlatLaf");
        }
         
         
         
         

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new mainWindow().setVisible(true);
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel aboutTheBook1;
    private javax.swing.JButton addNoteBtn;
    private javax.swing.JLabel appTitle;
    private javax.swing.JButton audiobookBtn;
    private javax.swing.JLabel audiobookLabel;
    private javax.swing.JLabel bibleBtnLabel;
    private javax.swing.JPanel bibleTab;
    private javax.swing.JLabel biblestudyTitle;
    private javax.swing.JButton boldBtn;
    private javax.swing.JComboBox<String> bookChooser;
    private javax.swing.JComboBox<String> bookChooserDropDown;
    private javax.swing.JPanel bookDescriptionSideBar1;
    private javax.swing.JButton bookDisplay1;
    private javax.swing.JButton bookDisplay10;
    private javax.swing.JButton bookDisplay11;
    private javax.swing.JButton bookDisplay12;
    private javax.swing.JButton bookDisplay2;
    private javax.swing.JButton bookDisplay3;
    private javax.swing.JButton bookDisplay4;
    private javax.swing.JButton bookDisplay5;
    private javax.swing.JButton bookDisplay6;
    private javax.swing.JButton bookDisplay7;
    private javax.swing.JButton bookDisplay8;
    private javax.swing.JButton bookDisplay9;
    private javax.swing.JPanel bookReaderContent;
    private javax.swing.JPanel bookReaderSubTab;
    private javax.swing.JScrollPane bookScroll;
    private javax.swing.JButton bookmark;
    private javax.swing.JButton bookmarkBtn;
    private javax.swing.JLabel bookmarkSavedNotif;
    private javax.swing.JLabel bookmarksBtn;
    private javax.swing.JButton cafeTheme;
    private javax.swing.JComboBox<String> chapterChooser;
    private javax.swing.JButton closeBtn;
    private javax.swing.JButton cmntrsBtn;
    private javax.swing.JLabel cmntrsBtnLabel;
    private javax.swing.JPanel cmtrsTabPanel;
    private javax.swing.JTabbedPane cmtryTabbedPanel;
    private javax.swing.JLabel dayLabel4;
    private javax.swing.JLabel dayLabel5;
    private javax.swing.JLabel dayLabel6;
    private javax.swing.JLabel description;
    private javax.swing.JButton exploreMoreBtn1;
    private javax.swing.JButton eyeHide;
    private javax.swing.JButton eyeShow;
    private javax.swing.JSlider fontSizeSlider;
    private javax.swing.JButton highlightBtn;
    private javax.swing.JButton homeBtn;
    private javax.swing.JLabel homeBtnLabel;
    private javax.swing.JButton hostJoinBtn;
    private javax.swing.JLabel hostJoinBtnLabel;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JButton journalBtn;
    private javax.swing.JButton libraryBtn;
    private javax.swing.JPanel libraryContent;
    private javax.swing.JPanel libraryTab;
    private javax.swing.JLabel loadingBook;
    private javax.swing.JLabel loadingLoop;
    private javax.swing.JPanel loadingPrevis;
    private javax.swing.JPanel loadingScreen;
    private javax.swing.JLayeredPane mainPanel_layered;
    private javax.swing.JTextArea mainTextArea;
    private javax.swing.JScrollPane mainTextScrollPanel;
    private javax.swing.JButton minimizeBtn;
    private javax.swing.JLabel month14;
    private javax.swing.JLabel month15;
    private javax.swing.JLabel month16;
    private javax.swing.JLabel month17;
    private javax.swing.JLabel month18;
    private javax.swing.JLabel month19;
    private javax.swing.JLabel month20;
    private javax.swing.JLabel month21;
    private javax.swing.JLabel month22;
    private javax.swing.JLabel month23;
    private javax.swing.JLabel month24;
    private javax.swing.JLabel month25;
    private javax.swing.JLabel month26;
    private javax.swing.JButton mute;
    private javax.swing.JPanel navBar;
    private javax.swing.JPanel nodesTabPanel;
    private javax.swing.JLabel notesBtnLabel;
    private javax.swing.JTextArea notesInput;
    private javax.swing.JLayeredPane notesTabLayers;
    private javax.swing.JPanel notesTabPanel;
    private javax.swing.JTextArea pdfDisplay;
    private javax.swing.JButton rainAmbience;
    private javax.swing.JButton reRandomizer;
    private javax.swing.JButton readBtn;
    private javax.swing.JPanel readStatus1;
    private javax.swing.JButton restoreBtn;
    private javax.swing.JButton saveNote;
    private javax.swing.JButton saveTick;
    private javax.swing.JButton searchBtn;
    private javax.swing.JLabel searchBtnLabel;
    private javax.swing.JButton selectedBook;
    private javax.swing.JButton selectedBookUnderline1;
    private javax.swing.JButton selectedBookUnderline10;
    private javax.swing.JButton selectedBookUnderline11;
    private javax.swing.JButton selectedBookUnderline12;
    private javax.swing.JButton selectedBookUnderline2;
    private javax.swing.JButton selectedBookUnderline3;
    private javax.swing.JButton selectedBookUnderline4;
    private javax.swing.JButton selectedBookUnderline5;
    private javax.swing.JButton selectedBookUnderline6;
    private javax.swing.JButton selectedBookUnderline7;
    private javax.swing.JButton selectedBookUnderline8;
    private javax.swing.JButton selectedBookUnderline9;
    private javax.swing.JButton settingsBtn;
    private javax.swing.JLabel settingsLabel;
    private javax.swing.JButton spacer;
    private javax.swing.JButton statusDay1;
    private javax.swing.JButton statusDay10;
    private javax.swing.JButton statusDay100;
    private javax.swing.JButton statusDay101;
    private javax.swing.JButton statusDay102;
    private javax.swing.JButton statusDay103;
    private javax.swing.JButton statusDay104;
    private javax.swing.JButton statusDay105;
    private javax.swing.JButton statusDay106;
    private javax.swing.JButton statusDay107;
    private javax.swing.JButton statusDay108;
    private javax.swing.JButton statusDay109;
    private javax.swing.JButton statusDay11;
    private javax.swing.JButton statusDay110;
    private javax.swing.JButton statusDay111;
    private javax.swing.JButton statusDay112;
    private javax.swing.JButton statusDay113;
    private javax.swing.JButton statusDay114;
    private javax.swing.JButton statusDay115;
    private javax.swing.JButton statusDay116;
    private javax.swing.JButton statusDay117;
    private javax.swing.JButton statusDay118;
    private javax.swing.JButton statusDay119;
    private javax.swing.JButton statusDay12;
    private javax.swing.JButton statusDay120;
    private javax.swing.JButton statusDay121;
    private javax.swing.JButton statusDay122;
    private javax.swing.JButton statusDay123;
    private javax.swing.JButton statusDay124;
    private javax.swing.JButton statusDay125;
    private javax.swing.JButton statusDay126;
    private javax.swing.JButton statusDay127;
    private javax.swing.JButton statusDay128;
    private javax.swing.JButton statusDay129;
    private javax.swing.JButton statusDay13;
    private javax.swing.JButton statusDay130;
    private javax.swing.JButton statusDay131;
    private javax.swing.JButton statusDay132;
    private javax.swing.JButton statusDay133;
    private javax.swing.JButton statusDay134;
    private javax.swing.JButton statusDay135;
    private javax.swing.JButton statusDay136;
    private javax.swing.JButton statusDay137;
    private javax.swing.JButton statusDay138;
    private javax.swing.JButton statusDay139;
    private javax.swing.JButton statusDay14;
    private javax.swing.JButton statusDay140;
    private javax.swing.JButton statusDay141;
    private javax.swing.JButton statusDay142;
    private javax.swing.JButton statusDay143;
    private javax.swing.JButton statusDay144;
    private javax.swing.JButton statusDay145;
    private javax.swing.JButton statusDay146;
    private javax.swing.JButton statusDay147;
    private javax.swing.JButton statusDay148;
    private javax.swing.JButton statusDay149;
    private javax.swing.JButton statusDay15;
    private javax.swing.JButton statusDay150;
    private javax.swing.JButton statusDay151;
    private javax.swing.JButton statusDay152;
    private javax.swing.JButton statusDay153;
    private javax.swing.JButton statusDay154;
    private javax.swing.JButton statusDay155;
    private javax.swing.JButton statusDay156;
    private javax.swing.JButton statusDay157;
    private javax.swing.JButton statusDay158;
    private javax.swing.JButton statusDay159;
    private javax.swing.JButton statusDay16;
    private javax.swing.JButton statusDay160;
    private javax.swing.JButton statusDay161;
    private javax.swing.JButton statusDay162;
    private javax.swing.JButton statusDay163;
    private javax.swing.JButton statusDay164;
    private javax.swing.JButton statusDay165;
    private javax.swing.JButton statusDay166;
    private javax.swing.JButton statusDay167;
    private javax.swing.JButton statusDay168;
    private javax.swing.JButton statusDay169;
    private javax.swing.JButton statusDay17;
    private javax.swing.JButton statusDay170;
    private javax.swing.JButton statusDay171;
    private javax.swing.JButton statusDay172;
    private javax.swing.JButton statusDay173;
    private javax.swing.JButton statusDay174;
    private javax.swing.JButton statusDay175;
    private javax.swing.JButton statusDay176;
    private javax.swing.JButton statusDay177;
    private javax.swing.JButton statusDay178;
    private javax.swing.JButton statusDay179;
    private javax.swing.JButton statusDay18;
    private javax.swing.JButton statusDay180;
    private javax.swing.JButton statusDay181;
    private javax.swing.JButton statusDay182;
    private javax.swing.JButton statusDay183;
    private javax.swing.JButton statusDay184;
    private javax.swing.JButton statusDay185;
    private javax.swing.JButton statusDay186;
    private javax.swing.JButton statusDay187;
    private javax.swing.JButton statusDay188;
    private javax.swing.JButton statusDay189;
    private javax.swing.JButton statusDay19;
    private javax.swing.JButton statusDay190;
    private javax.swing.JButton statusDay191;
    private javax.swing.JButton statusDay192;
    private javax.swing.JButton statusDay193;
    private javax.swing.JButton statusDay194;
    private javax.swing.JButton statusDay195;
    private javax.swing.JButton statusDay196;
    private javax.swing.JButton statusDay197;
    private javax.swing.JButton statusDay198;
    private javax.swing.JButton statusDay199;
    private javax.swing.JButton statusDay2;
    private javax.swing.JButton statusDay20;
    private javax.swing.JButton statusDay200;
    private javax.swing.JButton statusDay201;
    private javax.swing.JButton statusDay202;
    private javax.swing.JButton statusDay203;
    private javax.swing.JButton statusDay204;
    private javax.swing.JButton statusDay205;
    private javax.swing.JButton statusDay206;
    private javax.swing.JButton statusDay207;
    private javax.swing.JButton statusDay208;
    private javax.swing.JButton statusDay209;
    private javax.swing.JButton statusDay21;
    private javax.swing.JButton statusDay210;
    private javax.swing.JButton statusDay211;
    private javax.swing.JButton statusDay212;
    private javax.swing.JButton statusDay213;
    private javax.swing.JButton statusDay214;
    private javax.swing.JButton statusDay215;
    private javax.swing.JButton statusDay216;
    private javax.swing.JButton statusDay217;
    private javax.swing.JButton statusDay218;
    private javax.swing.JButton statusDay219;
    private javax.swing.JButton statusDay22;
    private javax.swing.JButton statusDay220;
    private javax.swing.JButton statusDay221;
    private javax.swing.JButton statusDay222;
    private javax.swing.JButton statusDay223;
    private javax.swing.JButton statusDay224;
    private javax.swing.JButton statusDay225;
    private javax.swing.JButton statusDay226;
    private javax.swing.JButton statusDay227;
    private javax.swing.JButton statusDay228;
    private javax.swing.JButton statusDay229;
    private javax.swing.JButton statusDay23;
    private javax.swing.JButton statusDay230;
    private javax.swing.JButton statusDay231;
    private javax.swing.JButton statusDay232;
    private javax.swing.JButton statusDay233;
    private javax.swing.JButton statusDay234;
    private javax.swing.JButton statusDay235;
    private javax.swing.JButton statusDay236;
    private javax.swing.JButton statusDay237;
    private javax.swing.JButton statusDay238;
    private javax.swing.JButton statusDay239;
    private javax.swing.JButton statusDay24;
    private javax.swing.JButton statusDay240;
    private javax.swing.JButton statusDay241;
    private javax.swing.JButton statusDay242;
    private javax.swing.JButton statusDay243;
    private javax.swing.JButton statusDay244;
    private javax.swing.JButton statusDay245;
    private javax.swing.JButton statusDay246;
    private javax.swing.JButton statusDay247;
    private javax.swing.JButton statusDay248;
    private javax.swing.JButton statusDay249;
    private javax.swing.JButton statusDay25;
    private javax.swing.JButton statusDay250;
    private javax.swing.JButton statusDay251;
    private javax.swing.JButton statusDay252;
    private javax.swing.JButton statusDay253;
    private javax.swing.JButton statusDay254;
    private javax.swing.JButton statusDay255;
    private javax.swing.JButton statusDay256;
    private javax.swing.JButton statusDay257;
    private javax.swing.JButton statusDay258;
    private javax.swing.JButton statusDay259;
    private javax.swing.JButton statusDay26;
    private javax.swing.JButton statusDay260;
    private javax.swing.JButton statusDay261;
    private javax.swing.JButton statusDay262;
    private javax.swing.JButton statusDay263;
    private javax.swing.JButton statusDay264;
    private javax.swing.JButton statusDay265;
    private javax.swing.JButton statusDay266;
    private javax.swing.JButton statusDay267;
    private javax.swing.JButton statusDay268;
    private javax.swing.JButton statusDay269;
    private javax.swing.JButton statusDay27;
    private javax.swing.JButton statusDay270;
    private javax.swing.JButton statusDay271;
    private javax.swing.JButton statusDay272;
    private javax.swing.JButton statusDay273;
    private javax.swing.JButton statusDay274;
    private javax.swing.JButton statusDay275;
    private javax.swing.JButton statusDay276;
    private javax.swing.JButton statusDay277;
    private javax.swing.JButton statusDay278;
    private javax.swing.JButton statusDay279;
    private javax.swing.JButton statusDay28;
    private javax.swing.JButton statusDay280;
    private javax.swing.JButton statusDay281;
    private javax.swing.JButton statusDay282;
    private javax.swing.JButton statusDay283;
    private javax.swing.JButton statusDay284;
    private javax.swing.JButton statusDay285;
    private javax.swing.JButton statusDay286;
    private javax.swing.JButton statusDay287;
    private javax.swing.JButton statusDay288;
    private javax.swing.JButton statusDay289;
    private javax.swing.JButton statusDay29;
    private javax.swing.JButton statusDay290;
    private javax.swing.JButton statusDay291;
    private javax.swing.JButton statusDay292;
    private javax.swing.JButton statusDay293;
    private javax.swing.JButton statusDay294;
    private javax.swing.JButton statusDay295;
    private javax.swing.JButton statusDay296;
    private javax.swing.JButton statusDay297;
    private javax.swing.JButton statusDay298;
    private javax.swing.JButton statusDay299;
    private javax.swing.JButton statusDay3;
    private javax.swing.JButton statusDay30;
    private javax.swing.JButton statusDay300;
    private javax.swing.JButton statusDay301;
    private javax.swing.JButton statusDay302;
    private javax.swing.JButton statusDay303;
    private javax.swing.JButton statusDay304;
    private javax.swing.JButton statusDay305;
    private javax.swing.JButton statusDay306;
    private javax.swing.JButton statusDay307;
    private javax.swing.JButton statusDay308;
    private javax.swing.JButton statusDay309;
    private javax.swing.JButton statusDay31;
    private javax.swing.JButton statusDay310;
    private javax.swing.JButton statusDay311;
    private javax.swing.JButton statusDay312;
    private javax.swing.JButton statusDay313;
    private javax.swing.JButton statusDay314;
    private javax.swing.JButton statusDay315;
    private javax.swing.JButton statusDay316;
    private javax.swing.JButton statusDay317;
    private javax.swing.JButton statusDay318;
    private javax.swing.JButton statusDay319;
    private javax.swing.JButton statusDay32;
    private javax.swing.JButton statusDay320;
    private javax.swing.JButton statusDay321;
    private javax.swing.JButton statusDay322;
    private javax.swing.JButton statusDay323;
    private javax.swing.JButton statusDay324;
    private javax.swing.JButton statusDay325;
    private javax.swing.JButton statusDay326;
    private javax.swing.JButton statusDay327;
    private javax.swing.JButton statusDay328;
    private javax.swing.JButton statusDay329;
    private javax.swing.JButton statusDay33;
    private javax.swing.JButton statusDay330;
    private javax.swing.JButton statusDay331;
    private javax.swing.JButton statusDay332;
    private javax.swing.JButton statusDay333;
    private javax.swing.JButton statusDay334;
    private javax.swing.JButton statusDay335;
    private javax.swing.JButton statusDay336;
    private javax.swing.JButton statusDay337;
    private javax.swing.JButton statusDay338;
    private javax.swing.JButton statusDay339;
    private javax.swing.JButton statusDay34;
    private javax.swing.JButton statusDay340;
    private javax.swing.JButton statusDay341;
    private javax.swing.JButton statusDay342;
    private javax.swing.JButton statusDay343;
    private javax.swing.JButton statusDay344;
    private javax.swing.JButton statusDay345;
    private javax.swing.JButton statusDay346;
    private javax.swing.JButton statusDay347;
    private javax.swing.JButton statusDay348;
    private javax.swing.JButton statusDay349;
    private javax.swing.JButton statusDay35;
    private javax.swing.JButton statusDay350;
    private javax.swing.JButton statusDay351;
    private javax.swing.JButton statusDay352;
    private javax.swing.JButton statusDay353;
    private javax.swing.JButton statusDay354;
    private javax.swing.JButton statusDay355;
    private javax.swing.JButton statusDay356;
    private javax.swing.JButton statusDay357;
    private javax.swing.JButton statusDay358;
    private javax.swing.JButton statusDay359;
    private javax.swing.JButton statusDay36;
    private javax.swing.JButton statusDay360;
    private javax.swing.JButton statusDay361;
    private javax.swing.JButton statusDay362;
    private javax.swing.JButton statusDay363;
    private javax.swing.JButton statusDay364;
    private javax.swing.JButton statusDay365;
    private javax.swing.JButton statusDay366;
    private javax.swing.JButton statusDay37;
    private javax.swing.JButton statusDay38;
    private javax.swing.JButton statusDay39;
    private javax.swing.JButton statusDay4;
    private javax.swing.JButton statusDay40;
    private javax.swing.JButton statusDay41;
    private javax.swing.JButton statusDay42;
    private javax.swing.JButton statusDay43;
    private javax.swing.JButton statusDay44;
    private javax.swing.JButton statusDay45;
    private javax.swing.JButton statusDay46;
    private javax.swing.JButton statusDay47;
    private javax.swing.JButton statusDay48;
    private javax.swing.JButton statusDay49;
    private javax.swing.JButton statusDay5;
    private javax.swing.JButton statusDay50;
    private javax.swing.JButton statusDay51;
    private javax.swing.JButton statusDay52;
    private javax.swing.JButton statusDay53;
    private javax.swing.JButton statusDay54;
    private javax.swing.JButton statusDay55;
    private javax.swing.JButton statusDay56;
    private javax.swing.JButton statusDay57;
    private javax.swing.JButton statusDay58;
    private javax.swing.JButton statusDay59;
    private javax.swing.JButton statusDay6;
    private javax.swing.JButton statusDay60;
    private javax.swing.JButton statusDay61;
    private javax.swing.JButton statusDay62;
    private javax.swing.JButton statusDay63;
    private javax.swing.JButton statusDay64;
    private javax.swing.JButton statusDay65;
    private javax.swing.JButton statusDay66;
    private javax.swing.JButton statusDay67;
    private javax.swing.JButton statusDay68;
    private javax.swing.JButton statusDay69;
    private javax.swing.JButton statusDay7;
    private javax.swing.JButton statusDay70;
    private javax.swing.JButton statusDay71;
    private javax.swing.JButton statusDay72;
    private javax.swing.JButton statusDay73;
    private javax.swing.JButton statusDay74;
    private javax.swing.JButton statusDay75;
    private javax.swing.JButton statusDay76;
    private javax.swing.JButton statusDay77;
    private javax.swing.JButton statusDay78;
    private javax.swing.JButton statusDay79;
    private javax.swing.JButton statusDay8;
    private javax.swing.JButton statusDay80;
    private javax.swing.JButton statusDay81;
    private javax.swing.JButton statusDay82;
    private javax.swing.JButton statusDay83;
    private javax.swing.JButton statusDay84;
    private javax.swing.JButton statusDay85;
    private javax.swing.JButton statusDay86;
    private javax.swing.JButton statusDay87;
    private javax.swing.JButton statusDay88;
    private javax.swing.JButton statusDay89;
    private javax.swing.JButton statusDay9;
    private javax.swing.JButton statusDay90;
    private javax.swing.JButton statusDay91;
    private javax.swing.JButton statusDay92;
    private javax.swing.JButton statusDay93;
    private javax.swing.JButton statusDay94;
    private javax.swing.JButton statusDay95;
    private javax.swing.JButton statusDay96;
    private javax.swing.JButton statusDay97;
    private javax.swing.JButton statusDay98;
    private javax.swing.JButton statusDay99;
    private javax.swing.JLayeredPane subTabLayered;
    private javax.swing.JTabbedPane tabs;
    private javax.swing.JComboBox<String> testamentChooser;
    private javax.swing.JLabel themer;
    private javax.swing.JPanel toolBox;
    private javax.swing.JPanel topBar;
    private javax.swing.JButton treeTheme;
    private javax.swing.JButton unmute;
    private javax.swing.JComboBox<String> verseChooser;
    private javax.swing.JTextField volDisp;
    private javax.swing.JButton volMinus;
    private javax.swing.JButton volPLus;
    // End of variables declaration//GEN-END:variables
}