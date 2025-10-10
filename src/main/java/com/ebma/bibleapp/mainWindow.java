

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


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.LocalDate;

import java.awt.geom.Rectangle2D;




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
    
    private boolean closeClicked = false;

    private String[] allBooksEnglish = {
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
    
    private String langChosen = "amh";
    private boolean isAmh = true;
    
    
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
        
        
        //loadLastReadBookFromSession();
        
        startAutoSaveClickTimer();

        writeYourReflection.setVisible(false);
        
        //updateReadingStatus();
        //updateReadingStatusDebug();
        //SwingUtilities.invokeLater(() -> updateReadingStatus());

        englishDropDownUpdater();



           // updateChapterChooserEnglish();
        

      
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
                    "src/main/resources/files/books/amh",
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

            // Just verify the text has verses
            Pattern pattern = Pattern.compile("\\b\\d+\\b");
            Matcher matcher = pattern.matcher(text);

            boolean found = matcher.find();
            if (!found) return;

            // Always default to first verse (index 0)
            try {
                Rectangle viewRect = mainTextArea.modelToView(0);
                if (viewRect != null) {
                    JViewport viewport = (JViewport) mainTextArea.getParent();
                    viewport.setViewPosition(viewRect.getLocation());
                }
            } catch (Exception ignored) {}

            // Optional: mark that verse loading is initialized
            verseChooserInitialized = false;
        }


        
        private void saveHighlight(String text, Color color) {
            if(isAmh){
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
        }



        private void restoreHighlights() {
            if(isAmh){
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


private void loadLastReadDirectly() {
    String dbPath = "bookStack.db";

    try (Connection conn = DriverManager.getConnection("jdbc:sqlite:" + dbPath)) {

        // 1. Get the last read bookIndex (this is already the assignedNum)
        int assignedNum = -1;
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT bookIndex FROM lastRead LIMIT 1")) {
            if (rs.next()) {
                assignedNum = rs.getInt("bookIndex");
            }
        }

        if (assignedNum == -1) {
            // No last read, fallback to first book in randomNums
            assignedNum = randomNums.get(0);
        }

        // 2. Find button number corresponding to assignedNum (for highlighting, optional)
        int buttonNumber = 1;
        for (int i = 0; i < randomNums.size(); i++) {
            if (randomNums.get(i) == assignedNum) {
                buttonNumber = i + 1;
                break;
            }
        }
        currentSelected = buttonNumber;

        // 3. Load icon directly using assignedNum
        URL selectedIconURL = getClass().getResource("/bookTiles/" + assignedNum + ".png");
        if (selectedIconURL != null) {
            selectedBook.setIcon(new ImageIcon(selectedIconURL));
        } else {
            System.err.println("Icon not found: /bookTiles/" + assignedNum + ".png");
        }

        // 4. Load book details directly
        String sql = "SELECT bookName, author, category, description FROM bookStack WHERE CAST(TRIM(bookIndex) AS INTEGER) = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, assignedNum);
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
        }

        continueReading.setVisible(true);

    } catch (SQLException ex) {
        ex.printStackTrace();
        description.setText("<html><div style='text-align:center; color:red;'>Database error: " + ex.getMessage() + "</div></html>");
    }
}
private String loadNoteFromDB(int testamentIndex, int bookIndex, int chapterIndex) {
    String noteText = "";

    // Build the key as stored in DB: "testament-book-chapter" (0-based)
    String bookChapterIndex = testamentIndex + "-" + bookIndex + "-" + chapterIndex;

    String dbPath = "notesNJournals.db"; // make sure this path is correct relative to your project
    try (Connection conn = DriverManager.getConnection("jdbc:sqlite:" + dbPath)) {
        String sql = "SELECT note FROM notesReflections WHERE bookChapterIndex = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, bookChapterIndex);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    noteText = rs.getString("note");
                }
            }
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }

    return noteText;
}


private void startAutoSaveClickTimer() {
    // 120000ms = 2 minutes
    new javax.swing.Timer(120000, e -> {
        if (saveNote.isVisible()) {      // only click if the button is visible
            saveNote.doClick();          // simulate button click
        }
    }).start();
}

public void updateStatusLabels() {
    String dbPath = "bookStack.db";

    // Map to hold summed data per day
    Map<LocalDate, int[]> dailyTotals = new HashMap<>();

    try (Connection conn = DriverManager.getConnection("jdbc:sqlite:" + dbPath)) {
        String sql = "SELECT pagesRead, readFor, lastRead FROM session";
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            // --- Aggregate totals per day ---
            while (rs.next()) {
                String lastReadStr = rs.getString("lastRead");
                String dateOnlyStr = lastReadStr.split(" ")[0]; // yyyy-MM-dd
                LocalDate date = LocalDate.parse(dateOnlyStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

                int pages = rs.getInt("pagesRead");
                int minutes = rs.getInt("readFor");

                dailyTotals.putIfAbsent(date, new int[]{0, 0});
                int[] totals = dailyTotals.get(date);
                totals[0] += pages;   // sum pagesRead
                totals[1] += minutes; // sum readFor
            }

            // --- Set label colors based on aggregated totals ---
            for (Map.Entry<LocalDate, int[]> entry : dailyTotals.entrySet()) {
                LocalDate date = entry.getKey();
                int pagesRead = entry.getValue()[0];
                int readFor = entry.getValue()[1];

                int month = date.getMonthValue();
                int day = date.getDayOfMonth();
                int customDay = 0;

                // --- Custom month mapping ---
                switch (month) {
                    case 9:  customDay = 1   + (day - 1); break;  // Sept
                    case 10: customDay = 31  + (day - 1); break;  // Oct
                    case 11: customDay = 61  + (day - 1); break;  // Nov
                    case 12: customDay = 91  + (day - 1); break;  // Dec
                    case 1:  customDay = 122 + (day - 1); break;  // Jan
                    case 2:  customDay = 153 + (day - 1); break;  // Feb
                    case 3:  customDay = 181 + (day - 1); break;  // Mar
                    case 4:  customDay = 212 + (day - 1); break;  // Apr
                    case 5:  customDay = 242 + (day - 1); break;  // May
                    case 6:  customDay = 273 + (day - 1); break;  // Jun
                    case 7:  customDay = 303 + (day - 1); break;  // Jul
                    case 8:  customDay = 334 + (day - 1); break;  // Aug
                    default: customDay = 0; break;
                }

                // --- Determine label color ---
                Color color;
                if (pagesRead >= 5 && pagesRead < 10 && readFor >= 5) {
                    color = new Color(70, 140, 70); // level 1
                } else if (pagesRead >= 10 && pagesRead < 15 && readFor >= 10) {
                    color = new Color(100, 170, 85); // level 2
                } else if (pagesRead >= 15 && pagesRead < 20 && readFor >= 15) {
                    color = new Color(46, 160, 67); // level 3
                } else if (pagesRead >= 20 && readFor >= 20) {
                    color = new Color(86, 211, 100); // level 4
                } else {
                    color = new Color(50, 90, 50); // level 0
                }

                // --- Map to JLabel ---
                int labelIndex = customDay - 1; // convert to 0-based index
                if (labelIndex >= 0 && labelIndex < readStatus.getComponentCount()) {
                    Component comp = readStatus.getComponent(labelIndex);
                    if (comp instanceof JLabel lbl) {
                        lbl.setOpaque(true);
                        lbl.setBackground(color);
                        lbl.repaint();
                    }
                }
            }

        }
    } catch (Exception e) {
        e.printStackTrace();
    }
}

private void englishDropDownUpdater() {
    boolean isEnglish = langChooser.getSelectedIndex() == 1;

    int scrollTo = mainTextScrollPanel.getVerticalScrollBar().getValue();
    int openBook = bookChooser.getSelectedIndex();
    int openChapter = chapterChooser.getSelectedIndex();

    int fontStyle = isEnglish ? Font.PLAIN : Font.BOLD;
    testamentChooser.setFont(new Font("Nokia Pure Headline Ultra Light", fontStyle, 14));
    bookChooser.setFont(new Font("Nokia Pure Headline Ultra Light", fontStyle, 14));
    chapterChooser.setFont(new Font("Nokia Pure Headline Ultra Light", fontStyle, 14));
    langChooser.setFont(new Font("Nokia Pure Headline Ultra Light", fontStyle, 14));

    if(isEnglish) {
        // Save current testament before replacing model
        int selectedTestament = testamentChooser.getSelectedIndex();

        // Update testament model and restore selection
        testamentChooser.setModel(new DefaultComboBoxModel<>(new String[]{"Old Testament", "New Testament"}));
        testamentChooser.setSelectedIndex(selectedTestament);

        // Update books correctly
        int start = (selectedTestament == 0) ? 0 : 39;
        int end = (selectedTestament == 0) ? 39 : allBooksEnglish.length;
        String[] subsetEng = Arrays.copyOfRange(allBooksEnglish, start, end);
        bookChooser.setModel(new DefaultComboBoxModel<>(subsetEng));

        // Restore previous book/chapter selection if valid
        bookChooser.setSelectedIndex(Math.min(openBook, subsetEng.length - 1));
        chapterChooser.setSelectedIndex(openChapter);

        // Update chapters if needed
        updateChapterChooserEnglish();
    } else {
        // Amharic branch
        testamentChooser.setModel(new DefaultComboBoxModel<>(new String[]{"ብሉይ ኪዳን", "አዲስ ኪዳን"}));
        updateBookChooser();
        updateVerseChooser();
    }

    // Restore scroll position
    mainTextScrollPanel.getVerticalScrollBar().setValue(scrollTo);
}

private void updateChapterChooserEnglish(){
            int selectedTestament = testamentChooser.getSelectedIndex();
                int start, end;

                if (selectedTestament == 0) { 
                    start = 0;
                    end = 39;
                } else { 
                    start = 39;
                    end = allBooksEnglish.length;
                }

                String[] subsetEng = java.util.Arrays.copyOfRange(allBooksEnglish, start, end);
                bookChooser.setModel(new javax.swing.DefaultComboBoxModel<>(subsetEng)); 
}

private void langSwitch(){

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
            "src/main/resources/files/books/" + langChosen + "/" + folderNumber,
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
        writeYourReflection = new javax.swing.JLabel();
        nodesTabPanel = new javax.swing.JPanel();
        cmtrsTabPanel = new javax.swing.JPanel();
        biblestudyTitle = new javax.swing.JLabel();
        highlightBtn = new javax.swing.JButton();
        boldBtn = new javax.swing.JButton();
        fontSizeSlider = new javax.swing.JSlider();
        langChooser = new javax.swing.JComboBox<>();
        bookChooser = new javax.swing.JComboBox<>();
        chapterChooser = new javax.swing.JComboBox<>();
        testamentChooser = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        libraryTab = new javax.swing.JPanel();
        libraryContent = new javax.swing.JPanel();
        bookDescriptionSideBar1 = new javax.swing.JPanel();
        aboutTheBook1 = new javax.swing.JLabel();
        selectedBook = new javax.swing.JButton();
        readBtn = new javax.swing.JButton();
        description = new javax.swing.JLabel();
        continueReading = new javax.swing.JButton();
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
        readStatus = new javax.swing.JPanel();
        statusDay1 = new javax.swing.JLabel();
        statusDay2 = new javax.swing.JLabel();
        statusDay3 = new javax.swing.JLabel();
        statusDay4 = new javax.swing.JLabel();
        statusDay5 = new javax.swing.JLabel();
        statusDay6 = new javax.swing.JLabel();
        statusDay7 = new javax.swing.JLabel();
        statusDay8 = new javax.swing.JLabel();
        statusDay9 = new javax.swing.JLabel();
        statusDay10 = new javax.swing.JLabel();
        statusDay11 = new javax.swing.JLabel();
        statusDay12 = new javax.swing.JLabel();
        statusDay13 = new javax.swing.JLabel();
        statusDay14 = new javax.swing.JLabel();
        statusDay15 = new javax.swing.JLabel();
        statusDay16 = new javax.swing.JLabel();
        statusDay17 = new javax.swing.JLabel();
        statusDay18 = new javax.swing.JLabel();
        statusDay19 = new javax.swing.JLabel();
        statusDay20 = new javax.swing.JLabel();
        statusDay21 = new javax.swing.JLabel();
        statusDay22 = new javax.swing.JLabel();
        statusDay23 = new javax.swing.JLabel();
        statusDay24 = new javax.swing.JLabel();
        statusDay25 = new javax.swing.JLabel();
        statusDay26 = new javax.swing.JLabel();
        statusDay27 = new javax.swing.JLabel();
        statusDay28 = new javax.swing.JLabel();
        statusDay29 = new javax.swing.JLabel();
        statusDay30 = new javax.swing.JLabel();
        statusDay31 = new javax.swing.JLabel();
        statusDay32 = new javax.swing.JLabel();
        statusDay33 = new javax.swing.JLabel();
        statusDay34 = new javax.swing.JLabel();
        statusDay35 = new javax.swing.JLabel();
        statusDay36 = new javax.swing.JLabel();
        statusDay37 = new javax.swing.JLabel();
        statusDay38 = new javax.swing.JLabel();
        statusDay39 = new javax.swing.JLabel();
        statusDay40 = new javax.swing.JLabel();
        statusDay41 = new javax.swing.JLabel();
        statusDay42 = new javax.swing.JLabel();
        statusDay43 = new javax.swing.JLabel();
        statusDay44 = new javax.swing.JLabel();
        statusDay45 = new javax.swing.JLabel();
        statusDay46 = new javax.swing.JLabel();
        statusDay47 = new javax.swing.JLabel();
        statusDay48 = new javax.swing.JLabel();
        statusDay49 = new javax.swing.JLabel();
        statusDay50 = new javax.swing.JLabel();
        statusDay51 = new javax.swing.JLabel();
        statusDay52 = new javax.swing.JLabel();
        statusDay53 = new javax.swing.JLabel();
        statusDay54 = new javax.swing.JLabel();
        statusDay55 = new javax.swing.JLabel();
        statusDay56 = new javax.swing.JLabel();
        statusDay57 = new javax.swing.JLabel();
        statusDay58 = new javax.swing.JLabel();
        statusDay59 = new javax.swing.JLabel();
        statusDay60 = new javax.swing.JLabel();
        statusDay61 = new javax.swing.JLabel();
        statusDay62 = new javax.swing.JLabel();
        statusDay63 = new javax.swing.JLabel();
        statusDay64 = new javax.swing.JLabel();
        statusDay65 = new javax.swing.JLabel();
        statusDay66 = new javax.swing.JLabel();
        statusDay67 = new javax.swing.JLabel();
        statusDay68 = new javax.swing.JLabel();
        statusDay69 = new javax.swing.JLabel();
        statusDay70 = new javax.swing.JLabel();
        statusDay71 = new javax.swing.JLabel();
        statusDay72 = new javax.swing.JLabel();
        statusDay73 = new javax.swing.JLabel();
        statusDay74 = new javax.swing.JLabel();
        statusDay75 = new javax.swing.JLabel();
        statusDay76 = new javax.swing.JLabel();
        statusDay77 = new javax.swing.JLabel();
        statusDay78 = new javax.swing.JLabel();
        statusDay79 = new javax.swing.JLabel();
        statusDay80 = new javax.swing.JLabel();
        statusDay81 = new javax.swing.JLabel();
        statusDay82 = new javax.swing.JLabel();
        statusDay83 = new javax.swing.JLabel();
        statusDay84 = new javax.swing.JLabel();
        statusDay85 = new javax.swing.JLabel();
        statusDay86 = new javax.swing.JLabel();
        statusDay87 = new javax.swing.JLabel();
        statusDay88 = new javax.swing.JLabel();
        statusDay89 = new javax.swing.JLabel();
        statusDay90 = new javax.swing.JLabel();
        statusDay91 = new javax.swing.JLabel();
        statusDay92 = new javax.swing.JLabel();
        statusDay93 = new javax.swing.JLabel();
        statusDay94 = new javax.swing.JLabel();
        statusDay95 = new javax.swing.JLabel();
        statusDay96 = new javax.swing.JLabel();
        statusDay97 = new javax.swing.JLabel();
        statusDay98 = new javax.swing.JLabel();
        statusDay99 = new javax.swing.JLabel();
        statusDay100 = new javax.swing.JLabel();
        statusDay101 = new javax.swing.JLabel();
        statusDay102 = new javax.swing.JLabel();
        statusDay103 = new javax.swing.JLabel();
        statusDay104 = new javax.swing.JLabel();
        statusDay105 = new javax.swing.JLabel();
        statusDay106 = new javax.swing.JLabel();
        statusDay107 = new javax.swing.JLabel();
        statusDay108 = new javax.swing.JLabel();
        statusDay109 = new javax.swing.JLabel();
        statusDay110 = new javax.swing.JLabel();
        statusDay111 = new javax.swing.JLabel();
        statusDay112 = new javax.swing.JLabel();
        statusDay113 = new javax.swing.JLabel();
        statusDay114 = new javax.swing.JLabel();
        statusDay115 = new javax.swing.JLabel();
        statusDay116 = new javax.swing.JLabel();
        statusDay117 = new javax.swing.JLabel();
        statusDay118 = new javax.swing.JLabel();
        statusDay119 = new javax.swing.JLabel();
        statusDay120 = new javax.swing.JLabel();
        statusDay121 = new javax.swing.JLabel();
        statusDay122 = new javax.swing.JLabel();
        statusDay123 = new javax.swing.JLabel();
        statusDay124 = new javax.swing.JLabel();
        statusDay125 = new javax.swing.JLabel();
        statusDay126 = new javax.swing.JLabel();
        statusDay127 = new javax.swing.JLabel();
        statusDay128 = new javax.swing.JLabel();
        statusDay129 = new javax.swing.JLabel();
        statusDay130 = new javax.swing.JLabel();
        statusDay131 = new javax.swing.JLabel();
        statusDay132 = new javax.swing.JLabel();
        statusDay133 = new javax.swing.JLabel();
        statusDay134 = new javax.swing.JLabel();
        statusDay135 = new javax.swing.JLabel();
        statusDay136 = new javax.swing.JLabel();
        statusDay137 = new javax.swing.JLabel();
        statusDay138 = new javax.swing.JLabel();
        statusDay139 = new javax.swing.JLabel();
        statusDay140 = new javax.swing.JLabel();
        statusDay141 = new javax.swing.JLabel();
        statusDay142 = new javax.swing.JLabel();
        statusDay143 = new javax.swing.JLabel();
        statusDay144 = new javax.swing.JLabel();
        statusDay145 = new javax.swing.JLabel();
        statusDay146 = new javax.swing.JLabel();
        statusDay147 = new javax.swing.JLabel();
        statusDay148 = new javax.swing.JLabel();
        statusDay149 = new javax.swing.JLabel();
        statusDay150 = new javax.swing.JLabel();
        statusDay151 = new javax.swing.JLabel();
        statusDay152 = new javax.swing.JLabel();
        statusDay153 = new javax.swing.JLabel();
        statusDay154 = new javax.swing.JLabel();
        statusDay155 = new javax.swing.JLabel();
        statusDay156 = new javax.swing.JLabel();
        statusDay157 = new javax.swing.JLabel();
        statusDay158 = new javax.swing.JLabel();
        statusDay159 = new javax.swing.JLabel();
        statusDay160 = new javax.swing.JLabel();
        statusDay161 = new javax.swing.JLabel();
        statusDay162 = new javax.swing.JLabel();
        statusDay163 = new javax.swing.JLabel();
        statusDay164 = new javax.swing.JLabel();
        statusDay165 = new javax.swing.JLabel();
        statusDay166 = new javax.swing.JLabel();
        statusDay167 = new javax.swing.JLabel();
        statusDay168 = new javax.swing.JLabel();
        statusDay169 = new javax.swing.JLabel();
        statusDay170 = new javax.swing.JLabel();
        statusDay171 = new javax.swing.JLabel();
        statusDay172 = new javax.swing.JLabel();
        statusDay173 = new javax.swing.JLabel();
        statusDay174 = new javax.swing.JLabel();
        statusDay175 = new javax.swing.JLabel();
        statusDay176 = new javax.swing.JLabel();
        statusDay177 = new javax.swing.JLabel();
        statusDay178 = new javax.swing.JLabel();
        statusDay179 = new javax.swing.JLabel();
        statusDay180 = new javax.swing.JLabel();
        statusDay181 = new javax.swing.JLabel();
        statusDay182 = new javax.swing.JLabel();
        statusDay183 = new javax.swing.JLabel();
        statusDay184 = new javax.swing.JLabel();
        statusDay185 = new javax.swing.JLabel();
        statusDay186 = new javax.swing.JLabel();
        statusDay187 = new javax.swing.JLabel();
        statusDay188 = new javax.swing.JLabel();
        statusDay189 = new javax.swing.JLabel();
        statusDay190 = new javax.swing.JLabel();
        statusDay191 = new javax.swing.JLabel();
        statusDay192 = new javax.swing.JLabel();
        statusDay193 = new javax.swing.JLabel();
        statusDay194 = new javax.swing.JLabel();
        statusDay195 = new javax.swing.JLabel();
        statusDay196 = new javax.swing.JLabel();
        statusDay197 = new javax.swing.JLabel();
        statusDay198 = new javax.swing.JLabel();
        statusDay199 = new javax.swing.JLabel();
        statusDay200 = new javax.swing.JLabel();
        statusDay201 = new javax.swing.JLabel();
        statusDay202 = new javax.swing.JLabel();
        statusDay203 = new javax.swing.JLabel();
        statusDay204 = new javax.swing.JLabel();
        statusDay205 = new javax.swing.JLabel();
        statusDay206 = new javax.swing.JLabel();
        statusDay207 = new javax.swing.JLabel();
        statusDay208 = new javax.swing.JLabel();
        statusDay209 = new javax.swing.JLabel();
        statusDay210 = new javax.swing.JLabel();
        statusDay211 = new javax.swing.JLabel();
        statusDay212 = new javax.swing.JLabel();
        statusDay213 = new javax.swing.JLabel();
        statusDay214 = new javax.swing.JLabel();
        statusDay215 = new javax.swing.JLabel();
        statusDay216 = new javax.swing.JLabel();
        statusDay217 = new javax.swing.JLabel();
        statusDay218 = new javax.swing.JLabel();
        statusDay219 = new javax.swing.JLabel();
        statusDay220 = new javax.swing.JLabel();
        statusDay221 = new javax.swing.JLabel();
        statusDay222 = new javax.swing.JLabel();
        statusDay223 = new javax.swing.JLabel();
        statusDay224 = new javax.swing.JLabel();
        statusDay225 = new javax.swing.JLabel();
        statusDay226 = new javax.swing.JLabel();
        statusDay227 = new javax.swing.JLabel();
        statusDay228 = new javax.swing.JLabel();
        statusDay229 = new javax.swing.JLabel();
        statusDay230 = new javax.swing.JLabel();
        statusDay231 = new javax.swing.JLabel();
        statusDay232 = new javax.swing.JLabel();
        statusDay233 = new javax.swing.JLabel();
        statusDay234 = new javax.swing.JLabel();
        statusDay235 = new javax.swing.JLabel();
        statusDay236 = new javax.swing.JLabel();
        statusDay237 = new javax.swing.JLabel();
        statusDay238 = new javax.swing.JLabel();
        statusDay239 = new javax.swing.JLabel();
        statusDay240 = new javax.swing.JLabel();
        statusDay241 = new javax.swing.JLabel();
        statusDay242 = new javax.swing.JLabel();
        statusDay243 = new javax.swing.JLabel();
        statusDay244 = new javax.swing.JLabel();
        statusDay245 = new javax.swing.JLabel();
        statusDay246 = new javax.swing.JLabel();
        statusDay247 = new javax.swing.JLabel();
        statusDay248 = new javax.swing.JLabel();
        statusDay249 = new javax.swing.JLabel();
        statusDay250 = new javax.swing.JLabel();
        statusDay251 = new javax.swing.JLabel();
        statusDay252 = new javax.swing.JLabel();
        statusDay253 = new javax.swing.JLabel();
        statusDay254 = new javax.swing.JLabel();
        statusDay255 = new javax.swing.JLabel();
        statusDay256 = new javax.swing.JLabel();
        statusDay257 = new javax.swing.JLabel();
        statusDay258 = new javax.swing.JLabel();
        statusDay259 = new javax.swing.JLabel();
        statusDay260 = new javax.swing.JLabel();
        statusDay261 = new javax.swing.JLabel();
        statusDay262 = new javax.swing.JLabel();
        statusDay263 = new javax.swing.JLabel();
        statusDay264 = new javax.swing.JLabel();
        statusDay265 = new javax.swing.JLabel();
        statusDay266 = new javax.swing.JLabel();
        statusDay267 = new javax.swing.JLabel();
        statusDay268 = new javax.swing.JLabel();
        statusDay269 = new javax.swing.JLabel();
        statusDay270 = new javax.swing.JLabel();
        statusDay271 = new javax.swing.JLabel();
        statusDay272 = new javax.swing.JLabel();
        statusDay273 = new javax.swing.JLabel();
        statusDay274 = new javax.swing.JLabel();
        statusDay275 = new javax.swing.JLabel();
        statusDay276 = new javax.swing.JLabel();
        statusDay277 = new javax.swing.JLabel();
        statusDay278 = new javax.swing.JLabel();
        statusDay279 = new javax.swing.JLabel();
        statusDay280 = new javax.swing.JLabel();
        statusDay281 = new javax.swing.JLabel();
        statusDay282 = new javax.swing.JLabel();
        statusDay283 = new javax.swing.JLabel();
        statusDay284 = new javax.swing.JLabel();
        statusDay285 = new javax.swing.JLabel();
        statusDay286 = new javax.swing.JLabel();
        statusDay287 = new javax.swing.JLabel();
        statusDay288 = new javax.swing.JLabel();
        statusDay289 = new javax.swing.JLabel();
        statusDay290 = new javax.swing.JLabel();
        statusDay291 = new javax.swing.JLabel();
        statusDay292 = new javax.swing.JLabel();
        statusDay293 = new javax.swing.JLabel();
        statusDay294 = new javax.swing.JLabel();
        statusDay295 = new javax.swing.JLabel();
        statusDay296 = new javax.swing.JLabel();
        statusDay297 = new javax.swing.JLabel();
        statusDay298 = new javax.swing.JLabel();
        statusDay299 = new javax.swing.JLabel();
        statusDay300 = new javax.swing.JLabel();
        statusDay301 = new javax.swing.JLabel();
        statusDay302 = new javax.swing.JLabel();
        statusDay303 = new javax.swing.JLabel();
        statusDay304 = new javax.swing.JLabel();
        statusDay305 = new javax.swing.JLabel();
        statusDay306 = new javax.swing.JLabel();
        statusDay307 = new javax.swing.JLabel();
        statusDay308 = new javax.swing.JLabel();
        statusDay309 = new javax.swing.JLabel();
        statusDay310 = new javax.swing.JLabel();
        statusDay311 = new javax.swing.JLabel();
        statusDay312 = new javax.swing.JLabel();
        statusDay313 = new javax.swing.JLabel();
        statusDay314 = new javax.swing.JLabel();
        statusDay315 = new javax.swing.JLabel();
        statusDay316 = new javax.swing.JLabel();
        statusDay317 = new javax.swing.JLabel();
        statusDay318 = new javax.swing.JLabel();
        statusDay319 = new javax.swing.JLabel();
        statusDay320 = new javax.swing.JLabel();
        statusDay321 = new javax.swing.JLabel();
        statusDay322 = new javax.swing.JLabel();
        statusDay323 = new javax.swing.JLabel();
        statusDay324 = new javax.swing.JLabel();
        statusDay325 = new javax.swing.JLabel();
        statusDay326 = new javax.swing.JLabel();
        statusDay327 = new javax.swing.JLabel();
        statusDay328 = new javax.swing.JLabel();
        statusDay329 = new javax.swing.JLabel();
        statusDay330 = new javax.swing.JLabel();
        statusDay331 = new javax.swing.JLabel();
        statusDay332 = new javax.swing.JLabel();
        statusDay333 = new javax.swing.JLabel();
        statusDay334 = new javax.swing.JLabel();
        statusDay335 = new javax.swing.JLabel();
        statusDay336 = new javax.swing.JLabel();
        statusDay337 = new javax.swing.JLabel();
        statusDay338 = new javax.swing.JLabel();
        statusDay339 = new javax.swing.JLabel();
        statusDay340 = new javax.swing.JLabel();
        statusDay341 = new javax.swing.JLabel();
        statusDay342 = new javax.swing.JLabel();
        statusDay343 = new javax.swing.JLabel();
        statusDay344 = new javax.swing.JLabel();
        statusDay345 = new javax.swing.JLabel();
        statusDay346 = new javax.swing.JLabel();
        statusDay347 = new javax.swing.JLabel();
        statusDay348 = new javax.swing.JLabel();
        statusDay349 = new javax.swing.JLabel();
        statusDay350 = new javax.swing.JLabel();
        statusDay351 = new javax.swing.JLabel();
        statusDay352 = new javax.swing.JLabel();
        statusDay353 = new javax.swing.JLabel();
        statusDay354 = new javax.swing.JLabel();
        statusDay355 = new javax.swing.JLabel();
        statusDay356 = new javax.swing.JLabel();
        statusDay357 = new javax.swing.JLabel();
        statusDay358 = new javax.swing.JLabel();
        statusDay359 = new javax.swing.JLabel();
        statusDay360 = new javax.swing.JLabel();
        statusDay361 = new javax.swing.JLabel();
        statusDay362 = new javax.swing.JLabel();
        statusDay363 = new javax.swing.JLabel();
        statusDay364 = new javax.swing.JLabel();
        statusDay365 = new javax.swing.JLabel();
        statusDay366 = new javax.swing.JLabel();
        sep1 = new javax.swing.JLabel();
        oct = new javax.swing.JLabel();
        nov = new javax.swing.JLabel();
        dec = new javax.swing.JLabel();
        jan = new javax.swing.JLabel();
        feb = new javax.swing.JLabel();
        mar = new javax.swing.JLabel();
        apr = new javax.swing.JLabel();
        may = new javax.swing.JLabel();
        jun = new javax.swing.JLabel();
        jul = new javax.swing.JLabel();
        aug = new javax.swing.JLabel();
        sep2 = new javax.swing.JLabel();
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
        appTitle.setText("መጽሐፍ ቅዱስ");

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

                    /*           // Optional: simulate actual mouse click
                    new Thread(() -> {
                        try {
                            Thread.sleep(150); // wait 150ms
                            Rectangle2D caretRect2D = mainTextArea.modelToView2D(start);
                            Point textAreaOnScreen = mainTextArea.getLocationOnScreen();
                            int mouseX = textAreaOnScreen.x + (int) caretRect2D.getX() + 1;
                            int mouseY = textAreaOnScreen.y + (int) caretRect2D.getY() + 1;

                            Robot robot = new Robot();
                            robot.mouseMove(mouseX, mouseY);
                            robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                            robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }).start();
                    */

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
        langSwitch();

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

        eyeShow.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8-eye-15.png"))); // NOI18N
        eyeShow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eyeShowActionPerformed(evt);
            }
        });

        saveNote.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/save15.png"))); // NOI18N
        saveNote.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveNoteActionPerformed(evt);
            }
        });

        saveTick.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/tick15.png"))); // NOI18N
        saveTick.setBorder(null);

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

        notesInput.setColumns(20);
        notesInput.setFont(new java.awt.Font("Nokia Pure Headline Ultra Light", 0, 18)); // NOI18N
        notesInput.setRows(5);
        notesInput.setBorder(null);
        notesInput.setOpaque(false);
        notesInput.setLineWrap(true);           // enable line wrapping
        notesInput.setWrapStyleWord(true);      // wrap at word boundaries, not mid-word

        writeYourReflection.setFont(new java.awt.Font("Godana", 0, 14)); // NOI18N
        writeYourReflection.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/write15.png"))); // NOI18N
        writeYourReflection.setText("Write your reflection on this chapter");

        notesTabLayers.setLayer(eyeHide, javax.swing.JLayeredPane.DEFAULT_LAYER);
        notesTabLayers.setLayer(eyeShow, javax.swing.JLayeredPane.DEFAULT_LAYER);
        notesTabLayers.setLayer(saveNote, javax.swing.JLayeredPane.DEFAULT_LAYER);
        notesTabLayers.setLayer(saveTick, javax.swing.JLayeredPane.DEFAULT_LAYER);
        notesTabLayers.setLayer(addNoteBtn, javax.swing.JLayeredPane.DEFAULT_LAYER);
        notesTabLayers.setLayer(notesInput, javax.swing.JLayeredPane.DEFAULT_LAYER);
        notesTabLayers.setLayer(writeYourReflection, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout notesTabLayersLayout = new javax.swing.GroupLayout(notesTabLayers);
        notesTabLayers.setLayout(notesTabLayersLayout);
        notesTabLayersLayout.setHorizontalGroup(
            notesTabLayersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(notesTabLayersLayout.createSequentialGroup()
                .addGroup(notesTabLayersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(notesTabLayersLayout.createSequentialGroup()
                        .addGap(260, 260, 260)
                        .addComponent(addNoteBtn))
                    .addGroup(notesTabLayersLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(notesTabLayersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(notesTabLayersLayout.createSequentialGroup()
                                .addComponent(writeYourReflection, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(297, 297, 297)
                                .addGroup(notesTabLayersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(saveTick, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(saveNote, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(10, 10, 10)
                                .addGroup(notesTabLayersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(eyeShow, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(eyeHide, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(notesInput, javax.swing.GroupLayout.PREFERRED_SIZE, 618, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(20, 20, 20))
        );
        notesTabLayersLayout.setVerticalGroup(
            notesTabLayersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(notesTabLayersLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(notesTabLayersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(writeYourReflection)
                    .addComponent(saveTick, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(saveNote, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(eyeShow, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(eyeHide, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(notesTabLayersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(notesTabLayersLayout.createSequentialGroup()
                        .addGap(350, 350, 350)
                        .addComponent(addNoteBtn))
                    .addComponent(notesInput, javax.swing.GroupLayout.PREFERRED_SIZE, 770, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        notesInput.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            private void handleChange() {
                if (saveTick.isVisible() && notesInput.getText().length() > 0) {
                    saveTick.setVisible(false);
                    saveNote.setVisible(true);
                }
            }

            @Override
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                handleChange();
            }

            @Override
            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                handleChange();
            }

            @Override
            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                handleChange();
            }
        });

        notesInput.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (addNoteBtn.isVisible()) {
                    notesInput.setFocusable(false);
                    notesInput.getCaret().setVisible(false);
                    notesInput.transferFocus(); // move focus away
                } else {
                    notesInput.setFocusable(true);
                    notesInput.getCaret().setVisible(true);
                }
            }
        });

        // --- Also prevent mouse clicks from showing caret when addNoteBtn is visible ---
        notesInput.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                if (addNoteBtn.isVisible()) {
                    notesInput.setFocusable(false);
                    notesInput.getCaret().setVisible(false);
                    evt.consume(); // block the click
                } else {
                    notesInput.setFocusable(true);
                    notesInput.getCaret().setVisible(true);
                }
            }
        });

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
        biblestudyTitle.setText("Bible Study");
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

        langChooser.setFont(new java.awt.Font("Nokia Pure Headline Ultra Light", 1, 14)); // NOI18N
        langChooser.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "አማርኛ", "English" }));
        bookChooser.addActionListener(e -> updateChapterChooser());
        langChooser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                langChooserActionPerformed(evt);
            }
        });
        bibleTab.add(langChooser, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 70, 90, 20));
        langChooser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                langChooserActionPerformed(evt);
                englishDropDownUpdater(); //  add this line
            }
        });
        langChooser.addActionListener(e -> {
            int selectedIndex = langChooser.getSelectedIndex();
            //langChosen = (selectedIndex == 1) ? "eng" : "amh";

            // Only change font when English is selected
            if (selectedIndex == 1) {
                isAmh = false;
                langChosen = "eng";
                mainTextArea.setFont(new java.awt.Font("Times New Roman", java.awt.Font.PLAIN, 20));
                langSwitch();
            }
            if (selectedIndex == 0) {
                mainTextArea.setFont(new java.awt.Font("Nokia Pure Headline Ultra Light", java.awt.Font.BOLD, 20));
                isAmh = true;
                langChosen = "amh";
                langSwitch();
                System.out.println(langChosen);
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
            langSwitch();
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

        chapterChooser.addActionListener(e -> {

            int selectedChapterIndex = chapterChooser.getSelectedIndex();
            if (selectedChapterIndex < 0) return;

            int selectedBookIndex = bookChooser.getSelectedIndex();
            if (selectedBookIndex < 0) return;

            int selectedTestamentIndex = testamentChooser.getSelectedIndex();

            // Update current indices
            currentChapterIndex = selectedChapterIndex;
            currentBookIndex = selectedBookIndex;

            // --- Load note from DB ---
            String loadedNote = loadNoteFromDB(selectedTestamentIndex, selectedBookIndex, selectedChapterIndex);
            notesInput.setText(loadedNote);

            // Adjust buttons visibility based on whether a note exists
            if (!loadedNote.isEmpty()) {
                addNoteBtn.setVisible(false); // hide the add button if note exists

                eyeHide.setVisible(true);
                saveNote.setVisible(true);
                saveTick.setVisible(false);
                writeYourReflection.setVisible(true);
            } else {
                notesInput.setText(""); // new note
                addNoteBtn.setVisible(true);
                eyeShow.setVisible(false);
                eyeHide.setVisible(false);
                saveNote.setVisible(false);
                saveTick.setVisible(false); // show the add button if no note exists
                writeYourReflection.setVisible(false);
            }
        });

        testamentChooser.setFont(new java.awt.Font("Nokia Pure Headline Ultra Light", 1, 14)); // NOI18N
        testamentChooser.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ብሉይ ኪዳን", "አዲስ ኪዳን" }));
        testamentChooser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                testamentChooserActionPerformed(evt);
            }
        });
        bibleTab.add(testamentChooser, new org.netbeans.lib.awtextra.AbsoluteConstraints(17, 67, 105, -1));
        testamentChooser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                // Only live-update bookChooser if English is selected
                if (langChooser.getSelectedIndex() == 1) {
                    int selectedTestament = testamentChooser.getSelectedIndex();
                    int start = (selectedTestament == 0) ? 0 : 39;
                    int end = (selectedTestament == 0) ? 39 : allBooksEnglish.length;

                    String[] subsetEng = java.util.Arrays.copyOfRange(allBooksEnglish, start, end);
                    bookChooser.setModel(new javax.swing.DefaultComboBoxModel<>(subsetEng));

                    // Optionally reset chapter selection to first chapter
                    chapterChooser.setSelectedIndex(0);
                }

                // Call your existing handler if needed
                testamentChooserActionPerformed(evt);
            }
        });

        jLabel1.setText("jLabel1");
        bibleTab.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 70, -1, -1));

        tabs.addTab("Home", bibleTab);

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

        continueReading.setBackground(new java.awt.Color(86, 211, 100));
        continueReading.setFont(new java.awt.Font("Nokia Pure Headline Ultra Light", 0, 14)); // NOI18N
        continueReading.setText("Last Read");
        continueReading.setBorderPainted(false);
        continueReading.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                continueReadingActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout bookDescriptionSideBar1Layout = new javax.swing.GroupLayout(bookDescriptionSideBar1);
        bookDescriptionSideBar1.setLayout(bookDescriptionSideBar1Layout);
        bookDescriptionSideBar1Layout.setHorizontalGroup(
            bookDescriptionSideBar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bookDescriptionSideBar1Layout.createSequentialGroup()
                .addGroup(bookDescriptionSideBar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(bookDescriptionSideBar1Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(description, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(bookDescriptionSideBar1Layout.createSequentialGroup()
                        .addGap(84, 84, 84)
                        .addComponent(selectedBook, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(31, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bookDescriptionSideBar1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(bookDescriptionSideBar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(bookDescriptionSideBar1Layout.createSequentialGroup()
                        .addComponent(readBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(17, 17, 17))
                    .addComponent(aboutTheBook1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(continueReading, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(115, 115, 115))
        );
        bookDescriptionSideBar1Layout.setVerticalGroup(
            bookDescriptionSideBar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bookDescriptionSideBar1Layout.createSequentialGroup()
                .addComponent(continueReading)
                .addGap(78, 78, 78)
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

        statusDay1.setBackground(new java.awt.Color(255, 255, 255));
        statusDay1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay1.setOpaque(true);

        statusDay2.setBackground(new java.awt.Color(255, 255, 255));
        statusDay2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay2.setOpaque(true);

        statusDay3.setBackground(new java.awt.Color(255, 255, 255));
        statusDay3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay3.setOpaque(true);

        statusDay4.setBackground(new java.awt.Color(255, 255, 255));
        statusDay4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay4.setOpaque(true);

        statusDay5.setBackground(new java.awt.Color(255, 255, 255));
        statusDay5.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay5.setOpaque(true);

        statusDay6.setBackground(new java.awt.Color(255, 255, 255));
        statusDay6.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay6.setOpaque(true);

        statusDay7.setBackground(new java.awt.Color(255, 255, 255));
        statusDay7.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay7.setOpaque(true);

        statusDay8.setBackground(new java.awt.Color(255, 255, 255));
        statusDay8.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay8.setOpaque(true);

        statusDay9.setBackground(new java.awt.Color(255, 255, 255));
        statusDay9.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay9.setOpaque(true);

        statusDay10.setBackground(new java.awt.Color(255, 255, 255));
        statusDay10.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay10.setOpaque(true);

        statusDay11.setBackground(new java.awt.Color(255, 255, 255));
        statusDay11.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay11.setOpaque(true);

        statusDay12.setBackground(new java.awt.Color(255, 255, 255));
        statusDay12.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay12.setOpaque(true);

        statusDay13.setBackground(new java.awt.Color(255, 255, 255));
        statusDay13.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay13.setOpaque(true);

        statusDay14.setBackground(new java.awt.Color(255, 255, 255));
        statusDay14.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay14.setOpaque(true);

        statusDay15.setBackground(new java.awt.Color(255, 255, 255));
        statusDay15.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay15.setOpaque(true);

        statusDay16.setBackground(new java.awt.Color(255, 255, 255));
        statusDay16.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay16.setOpaque(true);

        statusDay17.setBackground(new java.awt.Color(255, 255, 255));
        statusDay17.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay17.setOpaque(true);

        statusDay18.setBackground(new java.awt.Color(255, 255, 255));
        statusDay18.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay18.setOpaque(true);

        statusDay19.setBackground(new java.awt.Color(255, 255, 255));
        statusDay19.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay19.setOpaque(true);

        statusDay20.setBackground(new java.awt.Color(255, 255, 255));
        statusDay20.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay20.setOpaque(true);

        statusDay21.setBackground(new java.awt.Color(255, 255, 255));
        statusDay21.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay21.setOpaque(true);

        statusDay22.setBackground(new java.awt.Color(255, 255, 255));
        statusDay22.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay22.setOpaque(true);

        statusDay23.setBackground(new java.awt.Color(255, 255, 255));
        statusDay23.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay23.setOpaque(true);

        statusDay24.setBackground(new java.awt.Color(255, 255, 255));
        statusDay24.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay24.setOpaque(true);

        statusDay25.setBackground(new java.awt.Color(255, 255, 255));
        statusDay25.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay25.setOpaque(true);

        statusDay26.setBackground(new java.awt.Color(255, 255, 255));
        statusDay26.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay26.setOpaque(true);

        statusDay27.setBackground(new java.awt.Color(255, 255, 255));
        statusDay27.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay27.setOpaque(true);

        statusDay28.setBackground(new java.awt.Color(255, 255, 255));
        statusDay28.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay28.setOpaque(true);

        statusDay29.setBackground(new java.awt.Color(255, 255, 255));
        statusDay29.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay29.setOpaque(true);

        statusDay30.setBackground(new java.awt.Color(255, 255, 255));
        statusDay30.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay30.setOpaque(true);

        statusDay31.setBackground(new java.awt.Color(255, 255, 255));
        statusDay31.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay31.setOpaque(true);

        statusDay32.setBackground(new java.awt.Color(255, 255, 255));
        statusDay32.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay32.setOpaque(true);

        statusDay33.setBackground(new java.awt.Color(255, 255, 255));
        statusDay33.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay33.setOpaque(true);

        statusDay34.setBackground(new java.awt.Color(255, 255, 255));
        statusDay34.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay34.setOpaque(true);

        statusDay35.setBackground(new java.awt.Color(255, 255, 255));
        statusDay35.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay35.setOpaque(true);

        statusDay36.setBackground(new java.awt.Color(255, 255, 255));
        statusDay36.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay36.setOpaque(true);

        statusDay37.setBackground(new java.awt.Color(255, 255, 255));
        statusDay37.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay37.setOpaque(true);

        statusDay38.setBackground(new java.awt.Color(255, 255, 255));
        statusDay38.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay38.setOpaque(true);

        statusDay39.setBackground(new java.awt.Color(255, 255, 255));
        statusDay39.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay39.setOpaque(true);

        statusDay40.setBackground(new java.awt.Color(255, 255, 255));
        statusDay40.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay40.setOpaque(true);

        statusDay41.setBackground(new java.awt.Color(255, 255, 255));
        statusDay41.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay41.setOpaque(true);

        statusDay42.setBackground(new java.awt.Color(255, 255, 255));
        statusDay42.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay42.setOpaque(true);

        statusDay43.setBackground(new java.awt.Color(255, 255, 255));
        statusDay43.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay43.setOpaque(true);

        statusDay44.setBackground(new java.awt.Color(255, 255, 255));
        statusDay44.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay44.setOpaque(true);

        statusDay45.setBackground(new java.awt.Color(255, 255, 255));
        statusDay45.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay45.setOpaque(true);

        statusDay46.setBackground(new java.awt.Color(255, 255, 255));
        statusDay46.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay46.setOpaque(true);

        statusDay47.setBackground(new java.awt.Color(255, 255, 255));
        statusDay47.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay47.setOpaque(true);

        statusDay48.setBackground(new java.awt.Color(255, 255, 255));
        statusDay48.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay48.setOpaque(true);

        statusDay49.setBackground(new java.awt.Color(255, 255, 255));
        statusDay49.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay49.setOpaque(true);

        statusDay50.setBackground(new java.awt.Color(255, 255, 255));
        statusDay50.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay50.setOpaque(true);

        statusDay51.setBackground(new java.awt.Color(255, 255, 255));
        statusDay51.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay51.setOpaque(true);

        statusDay52.setBackground(new java.awt.Color(255, 255, 255));
        statusDay52.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay52.setOpaque(true);

        statusDay53.setBackground(new java.awt.Color(255, 255, 255));
        statusDay53.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay53.setOpaque(true);

        statusDay54.setBackground(new java.awt.Color(255, 255, 255));
        statusDay54.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay54.setOpaque(true);

        statusDay55.setBackground(new java.awt.Color(255, 255, 255));
        statusDay55.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay55.setOpaque(true);

        statusDay56.setBackground(new java.awt.Color(255, 255, 255));
        statusDay56.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay56.setOpaque(true);

        statusDay57.setBackground(new java.awt.Color(255, 255, 255));
        statusDay57.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay57.setOpaque(true);

        statusDay58.setBackground(new java.awt.Color(255, 255, 255));
        statusDay58.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay58.setOpaque(true);

        statusDay59.setBackground(new java.awt.Color(255, 255, 255));
        statusDay59.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay59.setOpaque(true);

        statusDay60.setBackground(new java.awt.Color(255, 255, 255));
        statusDay60.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay60.setOpaque(true);

        statusDay61.setBackground(new java.awt.Color(255, 255, 255));
        statusDay61.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay61.setOpaque(true);

        statusDay62.setBackground(new java.awt.Color(255, 255, 255));
        statusDay62.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay62.setOpaque(true);

        statusDay63.setBackground(new java.awt.Color(255, 255, 255));
        statusDay63.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay63.setOpaque(true);

        statusDay64.setBackground(new java.awt.Color(255, 255, 255));
        statusDay64.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay64.setOpaque(true);

        statusDay65.setBackground(new java.awt.Color(255, 255, 255));
        statusDay65.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay65.setOpaque(true);

        statusDay66.setBackground(new java.awt.Color(255, 255, 255));
        statusDay66.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay66.setOpaque(true);

        statusDay67.setBackground(new java.awt.Color(255, 255, 255));
        statusDay67.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay67.setOpaque(true);

        statusDay68.setBackground(new java.awt.Color(255, 255, 255));
        statusDay68.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay68.setOpaque(true);

        statusDay69.setBackground(new java.awt.Color(255, 255, 255));
        statusDay69.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay69.setOpaque(true);

        statusDay70.setBackground(new java.awt.Color(255, 255, 255));
        statusDay70.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay70.setOpaque(true);

        statusDay71.setBackground(new java.awt.Color(255, 255, 255));
        statusDay71.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay71.setOpaque(true);

        statusDay72.setBackground(new java.awt.Color(255, 255, 255));
        statusDay72.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay72.setOpaque(true);

        statusDay73.setBackground(new java.awt.Color(255, 255, 255));
        statusDay73.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay73.setOpaque(true);

        statusDay74.setBackground(new java.awt.Color(255, 255, 255));
        statusDay74.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay74.setOpaque(true);

        statusDay75.setBackground(new java.awt.Color(255, 255, 255));
        statusDay75.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay75.setOpaque(true);

        statusDay76.setBackground(new java.awt.Color(255, 255, 255));
        statusDay76.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay76.setOpaque(true);

        statusDay77.setBackground(new java.awt.Color(255, 255, 255));
        statusDay77.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay77.setOpaque(true);

        statusDay78.setBackground(new java.awt.Color(255, 255, 255));
        statusDay78.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay78.setOpaque(true);

        statusDay79.setBackground(new java.awt.Color(255, 255, 255));
        statusDay79.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay79.setOpaque(true);

        statusDay80.setBackground(new java.awt.Color(255, 255, 255));
        statusDay80.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay80.setOpaque(true);

        statusDay81.setBackground(new java.awt.Color(255, 255, 255));
        statusDay81.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay81.setOpaque(true);

        statusDay82.setBackground(new java.awt.Color(255, 255, 255));
        statusDay82.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay82.setOpaque(true);

        statusDay83.setBackground(new java.awt.Color(255, 255, 255));
        statusDay83.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay83.setOpaque(true);

        statusDay84.setBackground(new java.awt.Color(255, 255, 255));
        statusDay84.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay84.setOpaque(true);

        statusDay85.setBackground(new java.awt.Color(255, 255, 255));
        statusDay85.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay85.setOpaque(true);

        statusDay86.setBackground(new java.awt.Color(255, 255, 255));
        statusDay86.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay86.setOpaque(true);

        statusDay87.setBackground(new java.awt.Color(255, 255, 255));
        statusDay87.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay87.setOpaque(true);

        statusDay88.setBackground(new java.awt.Color(255, 255, 255));
        statusDay88.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay88.setOpaque(true);

        statusDay89.setBackground(new java.awt.Color(255, 255, 255));
        statusDay89.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay89.setOpaque(true);

        statusDay90.setBackground(new java.awt.Color(255, 255, 255));
        statusDay90.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay90.setOpaque(true);

        statusDay91.setBackground(new java.awt.Color(255, 255, 255));
        statusDay91.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay91.setOpaque(true);

        statusDay92.setBackground(new java.awt.Color(255, 255, 255));
        statusDay92.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay92.setOpaque(true);

        statusDay93.setBackground(new java.awt.Color(255, 255, 255));
        statusDay93.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay93.setOpaque(true);

        statusDay94.setBackground(new java.awt.Color(255, 255, 255));
        statusDay94.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay94.setOpaque(true);

        statusDay95.setBackground(new java.awt.Color(255, 255, 255));
        statusDay95.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay95.setOpaque(true);

        statusDay96.setBackground(new java.awt.Color(255, 255, 255));
        statusDay96.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay96.setOpaque(true);

        statusDay97.setBackground(new java.awt.Color(255, 255, 255));
        statusDay97.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay97.setOpaque(true);

        statusDay98.setBackground(new java.awt.Color(255, 255, 255));
        statusDay98.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay98.setOpaque(true);

        statusDay99.setBackground(new java.awt.Color(255, 255, 255));
        statusDay99.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay99.setOpaque(true);

        statusDay100.setBackground(new java.awt.Color(255, 255, 255));
        statusDay100.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay100.setOpaque(true);

        statusDay101.setBackground(new java.awt.Color(255, 255, 255));
        statusDay101.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay101.setOpaque(true);

        statusDay102.setBackground(new java.awt.Color(255, 255, 255));
        statusDay102.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay102.setOpaque(true);

        statusDay103.setBackground(new java.awt.Color(255, 255, 255));
        statusDay103.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay103.setOpaque(true);

        statusDay104.setBackground(new java.awt.Color(255, 255, 255));
        statusDay104.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay104.setOpaque(true);

        statusDay105.setBackground(new java.awt.Color(255, 255, 255));
        statusDay105.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay105.setOpaque(true);

        statusDay106.setBackground(new java.awt.Color(255, 255, 255));
        statusDay106.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay106.setOpaque(true);

        statusDay107.setBackground(new java.awt.Color(255, 255, 255));
        statusDay107.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay107.setOpaque(true);

        statusDay108.setBackground(new java.awt.Color(255, 255, 255));
        statusDay108.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay108.setOpaque(true);

        statusDay109.setBackground(new java.awt.Color(255, 255, 255));
        statusDay109.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay109.setOpaque(true);

        statusDay110.setBackground(new java.awt.Color(255, 255, 255));
        statusDay110.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay110.setOpaque(true);

        statusDay111.setBackground(new java.awt.Color(255, 255, 255));
        statusDay111.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay111.setOpaque(true);

        statusDay112.setBackground(new java.awt.Color(255, 255, 255));
        statusDay112.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay112.setOpaque(true);

        statusDay113.setBackground(new java.awt.Color(255, 255, 255));
        statusDay113.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay113.setOpaque(true);

        statusDay114.setBackground(new java.awt.Color(255, 255, 255));
        statusDay114.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay114.setOpaque(true);

        statusDay115.setBackground(new java.awt.Color(255, 255, 255));
        statusDay115.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay115.setOpaque(true);

        statusDay116.setBackground(new java.awt.Color(255, 255, 255));
        statusDay116.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay116.setOpaque(true);

        statusDay117.setBackground(new java.awt.Color(255, 255, 255));
        statusDay117.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay117.setOpaque(true);

        statusDay118.setBackground(new java.awt.Color(255, 255, 255));
        statusDay118.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay118.setOpaque(true);

        statusDay119.setBackground(new java.awt.Color(255, 255, 255));
        statusDay119.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay119.setOpaque(true);

        statusDay120.setBackground(new java.awt.Color(255, 255, 255));
        statusDay120.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay120.setOpaque(true);

        statusDay121.setBackground(new java.awt.Color(255, 255, 255));
        statusDay121.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay121.setOpaque(true);

        statusDay122.setBackground(new java.awt.Color(255, 255, 255));
        statusDay122.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay122.setOpaque(true);

        statusDay123.setBackground(new java.awt.Color(255, 255, 255));
        statusDay123.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay123.setOpaque(true);

        statusDay124.setBackground(new java.awt.Color(255, 255, 255));
        statusDay124.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay124.setOpaque(true);

        statusDay125.setBackground(new java.awt.Color(255, 255, 255));
        statusDay125.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay125.setOpaque(true);

        statusDay126.setBackground(new java.awt.Color(255, 255, 255));
        statusDay126.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay126.setOpaque(true);

        statusDay127.setBackground(new java.awt.Color(255, 255, 255));
        statusDay127.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay127.setOpaque(true);

        statusDay128.setBackground(new java.awt.Color(255, 255, 255));
        statusDay128.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay128.setOpaque(true);

        statusDay129.setBackground(new java.awt.Color(255, 255, 255));
        statusDay129.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay129.setOpaque(true);

        statusDay130.setBackground(new java.awt.Color(255, 255, 255));
        statusDay130.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay130.setOpaque(true);

        statusDay131.setBackground(new java.awt.Color(255, 255, 255));
        statusDay131.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay131.setOpaque(true);

        statusDay132.setBackground(new java.awt.Color(255, 255, 255));
        statusDay132.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay132.setOpaque(true);

        statusDay133.setBackground(new java.awt.Color(255, 255, 255));
        statusDay133.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay133.setOpaque(true);

        statusDay134.setBackground(new java.awt.Color(255, 255, 255));
        statusDay134.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay134.setOpaque(true);

        statusDay135.setBackground(new java.awt.Color(255, 255, 255));
        statusDay135.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay135.setOpaque(true);

        statusDay136.setBackground(new java.awt.Color(255, 255, 255));
        statusDay136.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay136.setOpaque(true);

        statusDay137.setBackground(new java.awt.Color(255, 255, 255));
        statusDay137.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay137.setOpaque(true);

        statusDay138.setBackground(new java.awt.Color(255, 255, 255));
        statusDay138.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay138.setOpaque(true);

        statusDay139.setBackground(new java.awt.Color(255, 255, 255));
        statusDay139.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay139.setOpaque(true);

        statusDay140.setBackground(new java.awt.Color(255, 255, 255));
        statusDay140.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay140.setOpaque(true);

        statusDay141.setBackground(new java.awt.Color(255, 255, 255));
        statusDay141.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay141.setOpaque(true);

        statusDay142.setBackground(new java.awt.Color(255, 255, 255));
        statusDay142.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay142.setOpaque(true);

        statusDay143.setBackground(new java.awt.Color(255, 255, 255));
        statusDay143.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay143.setOpaque(true);

        statusDay144.setBackground(new java.awt.Color(255, 255, 255));
        statusDay144.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay144.setOpaque(true);

        statusDay145.setBackground(new java.awt.Color(255, 255, 255));
        statusDay145.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay145.setOpaque(true);

        statusDay146.setBackground(new java.awt.Color(255, 255, 255));
        statusDay146.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay146.setOpaque(true);

        statusDay147.setBackground(new java.awt.Color(255, 255, 255));
        statusDay147.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay147.setOpaque(true);

        statusDay148.setBackground(new java.awt.Color(255, 255, 255));
        statusDay148.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay148.setOpaque(true);

        statusDay149.setBackground(new java.awt.Color(255, 255, 255));
        statusDay149.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay149.setOpaque(true);

        statusDay150.setBackground(new java.awt.Color(255, 255, 255));
        statusDay150.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay150.setOpaque(true);

        statusDay151.setBackground(new java.awt.Color(255, 255, 255));
        statusDay151.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay151.setOpaque(true);

        statusDay152.setBackground(new java.awt.Color(255, 255, 255));
        statusDay152.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay152.setOpaque(true);

        statusDay153.setBackground(new java.awt.Color(255, 255, 255));
        statusDay153.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay153.setOpaque(true);

        statusDay154.setBackground(new java.awt.Color(255, 255, 255));
        statusDay154.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay154.setOpaque(true);

        statusDay155.setBackground(new java.awt.Color(255, 255, 255));
        statusDay155.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay155.setOpaque(true);

        statusDay156.setBackground(new java.awt.Color(255, 255, 255));
        statusDay156.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay156.setOpaque(true);

        statusDay157.setBackground(new java.awt.Color(255, 255, 255));
        statusDay157.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay157.setOpaque(true);

        statusDay158.setBackground(new java.awt.Color(255, 255, 255));
        statusDay158.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay158.setOpaque(true);

        statusDay159.setBackground(new java.awt.Color(255, 255, 255));
        statusDay159.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay159.setOpaque(true);

        statusDay160.setBackground(new java.awt.Color(255, 255, 255));
        statusDay160.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay160.setOpaque(true);

        statusDay161.setBackground(new java.awt.Color(255, 255, 255));
        statusDay161.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay161.setOpaque(true);

        statusDay162.setBackground(new java.awt.Color(255, 255, 255));
        statusDay162.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay162.setOpaque(true);

        statusDay163.setBackground(new java.awt.Color(255, 255, 255));
        statusDay163.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay163.setOpaque(true);

        statusDay164.setBackground(new java.awt.Color(255, 255, 255));
        statusDay164.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay164.setOpaque(true);

        statusDay165.setBackground(new java.awt.Color(255, 255, 255));
        statusDay165.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay165.setOpaque(true);

        statusDay166.setBackground(new java.awt.Color(255, 255, 255));
        statusDay166.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay166.setOpaque(true);

        statusDay167.setBackground(new java.awt.Color(255, 255, 255));
        statusDay167.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay167.setOpaque(true);

        statusDay168.setBackground(new java.awt.Color(255, 255, 255));
        statusDay168.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay168.setOpaque(true);

        statusDay169.setBackground(new java.awt.Color(255, 255, 255));
        statusDay169.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay169.setOpaque(true);

        statusDay170.setBackground(new java.awt.Color(255, 255, 255));
        statusDay170.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay170.setOpaque(true);

        statusDay171.setBackground(new java.awt.Color(255, 255, 255));
        statusDay171.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay171.setOpaque(true);

        statusDay172.setBackground(new java.awt.Color(255, 255, 255));
        statusDay172.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay172.setOpaque(true);

        statusDay173.setBackground(new java.awt.Color(255, 255, 255));
        statusDay173.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay173.setOpaque(true);

        statusDay174.setBackground(new java.awt.Color(255, 255, 255));
        statusDay174.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay174.setOpaque(true);

        statusDay175.setBackground(new java.awt.Color(255, 255, 255));
        statusDay175.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay175.setOpaque(true);

        statusDay176.setBackground(new java.awt.Color(255, 255, 255));
        statusDay176.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay176.setOpaque(true);

        statusDay177.setBackground(new java.awt.Color(255, 255, 255));
        statusDay177.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay177.setOpaque(true);

        statusDay178.setBackground(new java.awt.Color(255, 255, 255));
        statusDay178.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay178.setOpaque(true);

        statusDay179.setBackground(new java.awt.Color(255, 255, 255));
        statusDay179.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay179.setOpaque(true);

        statusDay180.setBackground(new java.awt.Color(255, 255, 255));
        statusDay180.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay180.setOpaque(true);

        statusDay181.setBackground(new java.awt.Color(255, 255, 255));
        statusDay181.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay181.setOpaque(true);

        statusDay182.setBackground(new java.awt.Color(255, 255, 255));
        statusDay182.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay182.setOpaque(true);

        statusDay183.setBackground(new java.awt.Color(255, 255, 255));
        statusDay183.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay183.setOpaque(true);

        statusDay184.setBackground(new java.awt.Color(255, 255, 255));
        statusDay184.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay184.setOpaque(true);

        statusDay185.setBackground(new java.awt.Color(255, 255, 255));
        statusDay185.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay185.setOpaque(true);

        statusDay186.setBackground(new java.awt.Color(255, 255, 255));
        statusDay186.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay186.setOpaque(true);

        statusDay187.setBackground(new java.awt.Color(255, 255, 255));
        statusDay187.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay187.setOpaque(true);

        statusDay188.setBackground(new java.awt.Color(255, 255, 255));
        statusDay188.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay188.setOpaque(true);

        statusDay189.setBackground(new java.awt.Color(255, 255, 255));
        statusDay189.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay189.setOpaque(true);

        statusDay190.setBackground(new java.awt.Color(255, 255, 255));
        statusDay190.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay190.setOpaque(true);

        statusDay191.setBackground(new java.awt.Color(255, 255, 255));
        statusDay191.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay191.setOpaque(true);

        statusDay192.setBackground(new java.awt.Color(255, 255, 255));
        statusDay192.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay192.setOpaque(true);

        statusDay193.setBackground(new java.awt.Color(255, 255, 255));
        statusDay193.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay193.setOpaque(true);

        statusDay194.setBackground(new java.awt.Color(255, 255, 255));
        statusDay194.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay194.setOpaque(true);

        statusDay195.setBackground(new java.awt.Color(255, 255, 255));
        statusDay195.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay195.setOpaque(true);

        statusDay196.setBackground(new java.awt.Color(255, 255, 255));
        statusDay196.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay196.setOpaque(true);

        statusDay197.setBackground(new java.awt.Color(255, 255, 255));
        statusDay197.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay197.setOpaque(true);

        statusDay198.setBackground(new java.awt.Color(255, 255, 255));
        statusDay198.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay198.setOpaque(true);

        statusDay199.setBackground(new java.awt.Color(255, 255, 255));
        statusDay199.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay199.setOpaque(true);

        statusDay200.setBackground(new java.awt.Color(255, 255, 255));
        statusDay200.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay200.setOpaque(true);

        statusDay201.setBackground(new java.awt.Color(255, 255, 255));
        statusDay201.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay201.setOpaque(true);

        statusDay202.setBackground(new java.awt.Color(255, 255, 255));
        statusDay202.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay202.setOpaque(true);

        statusDay203.setBackground(new java.awt.Color(255, 255, 255));
        statusDay203.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay203.setOpaque(true);

        statusDay204.setBackground(new java.awt.Color(255, 255, 255));
        statusDay204.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay204.setOpaque(true);

        statusDay205.setBackground(new java.awt.Color(255, 255, 255));
        statusDay205.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay205.setOpaque(true);

        statusDay206.setBackground(new java.awt.Color(255, 255, 255));
        statusDay206.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay206.setOpaque(true);

        statusDay207.setBackground(new java.awt.Color(255, 255, 255));
        statusDay207.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay207.setOpaque(true);

        statusDay208.setBackground(new java.awt.Color(255, 255, 255));
        statusDay208.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay208.setOpaque(true);

        statusDay209.setBackground(new java.awt.Color(255, 255, 255));
        statusDay209.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay209.setOpaque(true);

        statusDay210.setBackground(new java.awt.Color(255, 255, 255));
        statusDay210.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay210.setOpaque(true);

        statusDay211.setBackground(new java.awt.Color(255, 255, 255));
        statusDay211.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay211.setOpaque(true);

        statusDay212.setBackground(new java.awt.Color(255, 255, 255));
        statusDay212.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay212.setOpaque(true);

        statusDay213.setBackground(new java.awt.Color(255, 255, 255));
        statusDay213.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay213.setOpaque(true);

        statusDay214.setBackground(new java.awt.Color(255, 255, 255));
        statusDay214.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay214.setOpaque(true);

        statusDay215.setBackground(new java.awt.Color(255, 255, 255));
        statusDay215.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay215.setOpaque(true);

        statusDay216.setBackground(new java.awt.Color(255, 255, 255));
        statusDay216.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay216.setOpaque(true);

        statusDay217.setBackground(new java.awt.Color(255, 255, 255));
        statusDay217.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay217.setOpaque(true);

        statusDay218.setBackground(new java.awt.Color(255, 255, 255));
        statusDay218.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay218.setOpaque(true);

        statusDay219.setBackground(new java.awt.Color(255, 255, 255));
        statusDay219.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay219.setOpaque(true);

        statusDay220.setBackground(new java.awt.Color(255, 255, 255));
        statusDay220.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay220.setOpaque(true);

        statusDay221.setBackground(new java.awt.Color(255, 255, 255));
        statusDay221.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay221.setOpaque(true);

        statusDay222.setBackground(new java.awt.Color(255, 255, 255));
        statusDay222.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay222.setOpaque(true);

        statusDay223.setBackground(new java.awt.Color(255, 255, 255));
        statusDay223.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay223.setOpaque(true);

        statusDay224.setBackground(new java.awt.Color(255, 255, 255));
        statusDay224.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay224.setOpaque(true);

        statusDay225.setBackground(new java.awt.Color(255, 255, 255));
        statusDay225.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay225.setOpaque(true);

        statusDay226.setBackground(new java.awt.Color(255, 255, 255));
        statusDay226.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay226.setOpaque(true);

        statusDay227.setBackground(new java.awt.Color(255, 255, 255));
        statusDay227.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay227.setOpaque(true);

        statusDay228.setBackground(new java.awt.Color(255, 255, 255));
        statusDay228.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay228.setOpaque(true);

        statusDay229.setBackground(new java.awt.Color(255, 255, 255));
        statusDay229.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay229.setOpaque(true);

        statusDay230.setBackground(new java.awt.Color(255, 255, 255));
        statusDay230.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay230.setOpaque(true);

        statusDay231.setBackground(new java.awt.Color(255, 255, 255));
        statusDay231.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay231.setOpaque(true);

        statusDay232.setBackground(new java.awt.Color(255, 255, 255));
        statusDay232.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay232.setOpaque(true);

        statusDay233.setBackground(new java.awt.Color(255, 255, 255));
        statusDay233.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay233.setOpaque(true);

        statusDay234.setBackground(new java.awt.Color(255, 255, 255));
        statusDay234.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay234.setOpaque(true);

        statusDay235.setBackground(new java.awt.Color(255, 255, 255));
        statusDay235.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay235.setOpaque(true);

        statusDay236.setBackground(new java.awt.Color(255, 255, 255));
        statusDay236.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay236.setOpaque(true);

        statusDay237.setBackground(new java.awt.Color(255, 255, 255));
        statusDay237.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay237.setOpaque(true);

        statusDay238.setBackground(new java.awt.Color(255, 255, 255));
        statusDay238.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay238.setOpaque(true);

        statusDay239.setBackground(new java.awt.Color(255, 255, 255));
        statusDay239.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay239.setOpaque(true);

        statusDay240.setBackground(new java.awt.Color(255, 255, 255));
        statusDay240.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay240.setOpaque(true);

        statusDay241.setBackground(new java.awt.Color(255, 255, 255));
        statusDay241.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay241.setOpaque(true);

        statusDay242.setBackground(new java.awt.Color(255, 255, 255));
        statusDay242.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay242.setOpaque(true);

        statusDay243.setBackground(new java.awt.Color(255, 255, 255));
        statusDay243.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay243.setOpaque(true);

        statusDay244.setBackground(new java.awt.Color(255, 255, 255));
        statusDay244.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay244.setOpaque(true);

        statusDay245.setBackground(new java.awt.Color(255, 255, 255));
        statusDay245.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay245.setOpaque(true);

        statusDay246.setBackground(new java.awt.Color(255, 255, 255));
        statusDay246.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay246.setOpaque(true);

        statusDay247.setBackground(new java.awt.Color(255, 255, 255));
        statusDay247.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay247.setOpaque(true);

        statusDay248.setBackground(new java.awt.Color(255, 255, 255));
        statusDay248.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay248.setOpaque(true);

        statusDay249.setBackground(new java.awt.Color(255, 255, 255));
        statusDay249.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay249.setOpaque(true);

        statusDay250.setBackground(new java.awt.Color(255, 255, 255));
        statusDay250.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay250.setOpaque(true);

        statusDay251.setBackground(new java.awt.Color(255, 255, 255));
        statusDay251.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay251.setOpaque(true);

        statusDay252.setBackground(new java.awt.Color(255, 255, 255));
        statusDay252.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay252.setOpaque(true);

        statusDay253.setBackground(new java.awt.Color(255, 255, 255));
        statusDay253.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay253.setOpaque(true);

        statusDay254.setBackground(new java.awt.Color(255, 255, 255));
        statusDay254.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay254.setOpaque(true);

        statusDay255.setBackground(new java.awt.Color(255, 255, 255));
        statusDay255.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay255.setOpaque(true);

        statusDay256.setBackground(new java.awt.Color(255, 255, 255));
        statusDay256.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay256.setOpaque(true);

        statusDay257.setBackground(new java.awt.Color(255, 255, 255));
        statusDay257.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay257.setOpaque(true);

        statusDay258.setBackground(new java.awt.Color(255, 255, 255));
        statusDay258.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay258.setOpaque(true);

        statusDay259.setBackground(new java.awt.Color(255, 255, 255));
        statusDay259.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay259.setOpaque(true);

        statusDay260.setBackground(new java.awt.Color(255, 255, 255));
        statusDay260.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay260.setOpaque(true);

        statusDay261.setBackground(new java.awt.Color(255, 255, 255));
        statusDay261.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay261.setOpaque(true);

        statusDay262.setBackground(new java.awt.Color(255, 255, 255));
        statusDay262.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay262.setOpaque(true);

        statusDay263.setBackground(new java.awt.Color(255, 255, 255));
        statusDay263.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay263.setOpaque(true);

        statusDay264.setBackground(new java.awt.Color(255, 255, 255));
        statusDay264.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay264.setOpaque(true);

        statusDay265.setBackground(new java.awt.Color(255, 255, 255));
        statusDay265.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay265.setOpaque(true);

        statusDay266.setBackground(new java.awt.Color(255, 255, 255));
        statusDay266.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay266.setOpaque(true);

        statusDay267.setBackground(new java.awt.Color(255, 255, 255));
        statusDay267.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay267.setOpaque(true);

        statusDay268.setBackground(new java.awt.Color(255, 255, 255));
        statusDay268.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay268.setOpaque(true);

        statusDay269.setBackground(new java.awt.Color(255, 255, 255));
        statusDay269.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay269.setOpaque(true);

        statusDay270.setBackground(new java.awt.Color(255, 255, 255));
        statusDay270.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay270.setOpaque(true);

        statusDay271.setBackground(new java.awt.Color(255, 255, 255));
        statusDay271.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay271.setOpaque(true);

        statusDay272.setBackground(new java.awt.Color(255, 255, 255));
        statusDay272.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay272.setOpaque(true);

        statusDay273.setBackground(new java.awt.Color(255, 255, 255));
        statusDay273.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay273.setOpaque(true);

        statusDay274.setBackground(new java.awt.Color(255, 255, 255));
        statusDay274.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay274.setOpaque(true);

        statusDay275.setBackground(new java.awt.Color(255, 255, 255));
        statusDay275.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay275.setOpaque(true);

        statusDay276.setBackground(new java.awt.Color(255, 255, 255));
        statusDay276.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay276.setOpaque(true);

        statusDay277.setBackground(new java.awt.Color(255, 255, 255));
        statusDay277.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay277.setOpaque(true);

        statusDay278.setBackground(new java.awt.Color(255, 255, 255));
        statusDay278.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay278.setOpaque(true);

        statusDay279.setBackground(new java.awt.Color(255, 255, 255));
        statusDay279.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay279.setOpaque(true);

        statusDay280.setBackground(new java.awt.Color(255, 255, 255));
        statusDay280.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay280.setOpaque(true);

        statusDay281.setBackground(new java.awt.Color(255, 255, 255));
        statusDay281.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay281.setOpaque(true);

        statusDay282.setBackground(new java.awt.Color(255, 255, 255));
        statusDay282.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay282.setOpaque(true);

        statusDay283.setBackground(new java.awt.Color(255, 255, 255));
        statusDay283.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay283.setOpaque(true);

        statusDay284.setBackground(new java.awt.Color(255, 255, 255));
        statusDay284.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay284.setOpaque(true);

        statusDay285.setBackground(new java.awt.Color(255, 255, 255));
        statusDay285.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay285.setOpaque(true);

        statusDay286.setBackground(new java.awt.Color(255, 255, 255));
        statusDay286.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay286.setOpaque(true);

        statusDay287.setBackground(new java.awt.Color(255, 255, 255));
        statusDay287.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay287.setOpaque(true);

        statusDay288.setBackground(new java.awt.Color(255, 255, 255));
        statusDay288.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay288.setOpaque(true);

        statusDay289.setBackground(new java.awt.Color(255, 255, 255));
        statusDay289.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay289.setOpaque(true);

        statusDay290.setBackground(new java.awt.Color(255, 255, 255));
        statusDay290.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay290.setOpaque(true);

        statusDay291.setBackground(new java.awt.Color(255, 255, 255));
        statusDay291.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay291.setOpaque(true);

        statusDay292.setBackground(new java.awt.Color(255, 255, 255));
        statusDay292.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay292.setOpaque(true);

        statusDay293.setBackground(new java.awt.Color(255, 255, 255));
        statusDay293.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay293.setOpaque(true);

        statusDay294.setBackground(new java.awt.Color(255, 255, 255));
        statusDay294.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay294.setOpaque(true);

        statusDay295.setBackground(new java.awt.Color(255, 255, 255));
        statusDay295.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay295.setOpaque(true);

        statusDay296.setBackground(new java.awt.Color(255, 255, 255));
        statusDay296.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay296.setOpaque(true);

        statusDay297.setBackground(new java.awt.Color(255, 255, 255));
        statusDay297.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay297.setOpaque(true);

        statusDay298.setBackground(new java.awt.Color(255, 255, 255));
        statusDay298.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay298.setOpaque(true);

        statusDay299.setBackground(new java.awt.Color(255, 255, 255));
        statusDay299.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay299.setOpaque(true);

        statusDay300.setBackground(new java.awt.Color(255, 255, 255));
        statusDay300.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay300.setOpaque(true);

        statusDay301.setBackground(new java.awt.Color(255, 255, 255));
        statusDay301.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay301.setOpaque(true);

        statusDay302.setBackground(new java.awt.Color(255, 255, 255));
        statusDay302.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay302.setOpaque(true);

        statusDay303.setBackground(new java.awt.Color(255, 255, 255));
        statusDay303.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay303.setOpaque(true);

        statusDay304.setBackground(new java.awt.Color(255, 255, 255));
        statusDay304.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay304.setOpaque(true);

        statusDay305.setBackground(new java.awt.Color(255, 255, 255));
        statusDay305.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay305.setOpaque(true);

        statusDay306.setBackground(new java.awt.Color(255, 255, 255));
        statusDay306.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay306.setOpaque(true);

        statusDay307.setBackground(new java.awt.Color(255, 255, 255));
        statusDay307.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay307.setOpaque(true);

        statusDay308.setBackground(new java.awt.Color(255, 255, 255));
        statusDay308.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay308.setOpaque(true);

        statusDay309.setBackground(new java.awt.Color(255, 255, 255));
        statusDay309.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay309.setOpaque(true);

        statusDay310.setBackground(new java.awt.Color(255, 255, 255));
        statusDay310.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay310.setOpaque(true);

        statusDay311.setBackground(new java.awt.Color(255, 255, 255));
        statusDay311.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay311.setOpaque(true);

        statusDay312.setBackground(new java.awt.Color(255, 255, 255));
        statusDay312.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay312.setOpaque(true);

        statusDay313.setBackground(new java.awt.Color(255, 255, 255));
        statusDay313.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay313.setOpaque(true);

        statusDay314.setBackground(new java.awt.Color(255, 255, 255));
        statusDay314.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay314.setOpaque(true);

        statusDay315.setBackground(new java.awt.Color(255, 255, 255));
        statusDay315.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay315.setOpaque(true);

        statusDay316.setBackground(new java.awt.Color(255, 255, 255));
        statusDay316.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay316.setOpaque(true);

        statusDay317.setBackground(new java.awt.Color(255, 255, 255));
        statusDay317.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay317.setOpaque(true);

        statusDay318.setBackground(new java.awt.Color(255, 255, 255));
        statusDay318.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay318.setOpaque(true);

        statusDay319.setBackground(new java.awt.Color(255, 255, 255));
        statusDay319.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay319.setOpaque(true);

        statusDay320.setBackground(new java.awt.Color(255, 255, 255));
        statusDay320.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay320.setOpaque(true);

        statusDay321.setBackground(new java.awt.Color(255, 255, 255));
        statusDay321.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay321.setOpaque(true);

        statusDay322.setBackground(new java.awt.Color(255, 255, 255));
        statusDay322.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay322.setOpaque(true);

        statusDay323.setBackground(new java.awt.Color(255, 255, 255));
        statusDay323.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay323.setOpaque(true);

        statusDay324.setBackground(new java.awt.Color(255, 255, 255));
        statusDay324.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay324.setOpaque(true);

        statusDay325.setBackground(new java.awt.Color(255, 255, 255));
        statusDay325.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay325.setOpaque(true);

        statusDay326.setBackground(new java.awt.Color(255, 255, 255));
        statusDay326.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay326.setOpaque(true);

        statusDay327.setBackground(new java.awt.Color(255, 255, 255));
        statusDay327.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay327.setOpaque(true);

        statusDay328.setBackground(new java.awt.Color(255, 255, 255));
        statusDay328.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay328.setOpaque(true);

        statusDay329.setBackground(new java.awt.Color(255, 255, 255));
        statusDay329.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay329.setOpaque(true);

        statusDay330.setBackground(new java.awt.Color(255, 255, 255));
        statusDay330.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay330.setOpaque(true);

        statusDay331.setBackground(new java.awt.Color(255, 255, 255));
        statusDay331.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay331.setOpaque(true);

        statusDay332.setBackground(new java.awt.Color(255, 255, 255));
        statusDay332.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay332.setOpaque(true);

        statusDay333.setBackground(new java.awt.Color(255, 255, 255));
        statusDay333.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay333.setOpaque(true);

        statusDay334.setBackground(new java.awt.Color(255, 255, 255));
        statusDay334.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay334.setOpaque(true);

        statusDay335.setBackground(new java.awt.Color(255, 255, 255));
        statusDay335.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay335.setOpaque(true);

        statusDay336.setBackground(new java.awt.Color(255, 255, 255));
        statusDay336.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay336.setOpaque(true);

        statusDay337.setBackground(new java.awt.Color(255, 255, 255));
        statusDay337.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay337.setOpaque(true);

        statusDay338.setBackground(new java.awt.Color(255, 255, 255));
        statusDay338.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay338.setOpaque(true);

        statusDay339.setBackground(new java.awt.Color(255, 255, 255));
        statusDay339.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay339.setOpaque(true);

        statusDay340.setBackground(new java.awt.Color(255, 255, 255));
        statusDay340.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay340.setOpaque(true);

        statusDay341.setBackground(new java.awt.Color(255, 255, 255));
        statusDay341.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay341.setOpaque(true);

        statusDay342.setBackground(new java.awt.Color(255, 255, 255));
        statusDay342.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay342.setOpaque(true);

        statusDay343.setBackground(new java.awt.Color(255, 255, 255));
        statusDay343.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay343.setOpaque(true);

        statusDay344.setBackground(new java.awt.Color(255, 255, 255));
        statusDay344.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay344.setOpaque(true);

        statusDay345.setBackground(new java.awt.Color(255, 255, 255));
        statusDay345.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay345.setOpaque(true);

        statusDay346.setBackground(new java.awt.Color(255, 255, 255));
        statusDay346.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay346.setOpaque(true);

        statusDay347.setBackground(new java.awt.Color(255, 255, 255));
        statusDay347.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay347.setOpaque(true);

        statusDay348.setBackground(new java.awt.Color(255, 255, 255));
        statusDay348.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay348.setOpaque(true);

        statusDay349.setBackground(new java.awt.Color(255, 255, 255));
        statusDay349.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay349.setOpaque(true);

        statusDay350.setBackground(new java.awt.Color(255, 255, 255));
        statusDay350.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay350.setOpaque(true);

        statusDay351.setBackground(new java.awt.Color(255, 255, 255));
        statusDay351.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay351.setOpaque(true);

        statusDay352.setBackground(new java.awt.Color(255, 255, 255));
        statusDay352.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay352.setOpaque(true);

        statusDay353.setBackground(new java.awt.Color(255, 255, 255));
        statusDay353.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay353.setOpaque(true);

        statusDay354.setBackground(new java.awt.Color(255, 255, 255));
        statusDay354.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay354.setOpaque(true);

        statusDay355.setBackground(new java.awt.Color(255, 255, 255));
        statusDay355.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay355.setOpaque(true);

        statusDay356.setBackground(new java.awt.Color(255, 255, 255));
        statusDay356.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay356.setOpaque(true);

        statusDay357.setBackground(new java.awt.Color(255, 255, 255));
        statusDay357.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay357.setOpaque(true);

        statusDay358.setBackground(new java.awt.Color(255, 255, 255));
        statusDay358.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay358.setOpaque(true);

        statusDay359.setBackground(new java.awt.Color(255, 255, 255));
        statusDay359.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay359.setOpaque(true);

        statusDay360.setBackground(new java.awt.Color(255, 255, 255));
        statusDay360.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay360.setOpaque(true);

        statusDay361.setBackground(new java.awt.Color(255, 255, 255));
        statusDay361.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay361.setOpaque(true);

        statusDay362.setBackground(new java.awt.Color(255, 255, 255));
        statusDay362.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay362.setOpaque(true);

        statusDay363.setBackground(new java.awt.Color(255, 255, 255));
        statusDay363.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay363.setOpaque(true);

        statusDay364.setBackground(new java.awt.Color(255, 255, 255));
        statusDay364.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay364.setOpaque(true);

        statusDay365.setBackground(new java.awt.Color(255, 255, 255));
        statusDay365.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay365.setOpaque(true);

        statusDay366.setBackground(new java.awt.Color(255, 255, 255));
        statusDay366.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        statusDay366.setOpaque(true);

        sep1.setFont(new java.awt.Font("Nokia Pure Headline Ultra Light", 0, 12)); // NOI18N
        sep1.setText("Sep");

        oct.setFont(new java.awt.Font("Nokia Pure Headline Ultra Light", 0, 12)); // NOI18N
        oct.setText("Oct");

        nov.setFont(new java.awt.Font("Nokia Pure Headline Ultra Light", 0, 12)); // NOI18N
        nov.setText("Nov");

        dec.setFont(new java.awt.Font("Nokia Pure Headline Ultra Light", 0, 12)); // NOI18N
        dec.setText("Dec");

        jan.setFont(new java.awt.Font("Nokia Pure Headline Ultra Light", 0, 12)); // NOI18N
        jan.setText("Jan");

        feb.setFont(new java.awt.Font("Nokia Pure Headline Ultra Light", 0, 12)); // NOI18N
        feb.setText("Feb");

        mar.setFont(new java.awt.Font("Nokia Pure Headline Ultra Light", 0, 12)); // NOI18N
        mar.setText("Mar");

        apr.setFont(new java.awt.Font("Nokia Pure Headline Ultra Light", 0, 12)); // NOI18N
        apr.setText("Apr");

        may.setFont(new java.awt.Font("Nokia Pure Headline Ultra Light", 0, 12)); // NOI18N
        may.setText("May");

        jun.setFont(new java.awt.Font("Nokia Pure Headline Ultra Light", 0, 12)); // NOI18N
        jun.setText("Jun");

        jul.setFont(new java.awt.Font("Nokia Pure Headline Ultra Light", 0, 12)); // NOI18N
        jul.setText("Jul");

        aug.setFont(new java.awt.Font("Nokia Pure Headline Ultra Light", 0, 12)); // NOI18N
        aug.setText("Aug");

        sep2.setFont(new java.awt.Font("Nokia Pure Headline Ultra Light", 0, 12)); // NOI18N
        sep2.setText("Sep");

        javax.swing.GroupLayout readStatusLayout = new javax.swing.GroupLayout(readStatus);
        readStatus.setLayout(readStatusLayout);
        readStatusLayout.setHorizontalGroup(
            readStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(readStatusLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(readStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(readStatusLayout.createSequentialGroup()
                        .addGroup(readStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(statusDay1, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay2, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay3, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay4, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay5, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay6, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay7, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(readStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(statusDay8, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay9, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay10, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay11, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay12, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay13, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay14, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(readStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(statusDay15, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay16, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay17, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay18, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay19, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay20, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay21, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(readStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(statusDay22, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay23, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay24, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay25, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay26, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay27, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay28, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(readStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(statusDay29, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay30, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay31, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay32, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay33, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay34, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay35, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(readStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(statusDay36, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay37, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay38, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay39, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay40, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay41, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay42, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(readStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(statusDay43, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay44, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay45, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay46, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay47, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay48, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay49, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(readStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(statusDay50, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay51, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay52, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay53, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay54, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay55, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay56, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(readStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(statusDay57, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay58, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay59, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay60, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay61, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay62, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay63, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(readStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(statusDay64, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay65, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay66, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay67, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay68, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay69, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay70, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(readStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(statusDay71, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay72, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay73, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay74, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay75, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay76, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay77, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(readStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(statusDay78, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay79, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay80, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay81, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay82, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay83, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay84, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(readStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(statusDay85, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay86, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay87, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay88, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay89, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay90, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay91, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(readStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(statusDay92, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay93, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay94, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay95, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay96, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay97, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay98, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(readStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(statusDay99, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay100, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay101, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay102, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay103, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay104, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay105, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(readStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(statusDay106, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay107, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay108, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay109, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay110, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay111, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay112, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(readStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(statusDay113, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay114, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay115, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay116, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay117, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay118, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay119, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(readStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(statusDay120, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay121, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay122, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay123, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay124, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay125, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay126, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(readStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(statusDay127, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay128, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay129, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay130, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay131, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay132, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay133, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(readStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(statusDay134, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay135, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay136, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay137, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay138, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay139, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay140, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(readStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(statusDay141, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay142, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay143, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay144, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay145, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay146, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay147, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(readStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(statusDay148, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay149, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay150, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay151, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay152, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay153, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay154, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(readStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(statusDay155, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay156, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay157, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay158, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay159, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay160, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay161, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(readStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(statusDay162, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay163, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay164, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay165, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay166, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay167, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay168, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(readStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(statusDay169, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay170, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay171, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay172, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay173, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay174, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay175, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(readStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(statusDay176, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay177, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay178, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay179, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay180, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay181, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay182, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(readStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(statusDay183, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay184, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay185, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay186, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay187, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay188, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay189, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(readStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(statusDay190, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay191, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay192, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay193, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay194, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay195, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay196, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(readStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(statusDay197, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay198, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay199, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay200, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay201, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay202, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay203, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(readStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(statusDay204, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay205, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay206, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay207, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay208, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay209, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay210, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(readStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(statusDay211, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay212, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay213, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay214, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay215, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay216, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay217, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(readStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(statusDay218, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay219, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay220, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay221, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay222, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay223, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay224, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(readStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(statusDay225, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay226, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay227, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay228, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay229, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay230, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay231, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(readStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(statusDay232, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay233, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay234, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay235, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay236, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay237, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay238, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(readStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(statusDay239, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay240, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay241, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay242, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay243, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay244, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay245, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(readStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(statusDay246, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay247, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay248, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay249, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay250, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay251, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay252, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(readStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(statusDay253, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay254, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay255, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay256, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay257, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay258, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay259, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(readStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(statusDay260, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay261, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay262, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay263, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay264, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay265, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay266, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(readStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(statusDay267, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay268, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay269, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay270, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay271, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay272, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay273, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(readStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(statusDay274, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay275, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay276, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay277, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay278, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay279, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay280, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(readStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(statusDay281, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay282, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay283, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay284, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay285, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay286, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay287, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(readStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(statusDay288, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay289, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay290, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay291, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay292, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay293, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay294, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(readStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(statusDay295, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay296, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay297, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay298, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay299, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay300, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay301, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(readStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(statusDay302, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay303, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay304, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay305, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay306, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay307, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay308, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(readStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(statusDay309, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay310, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay311, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay312, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay313, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay314, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay315, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(readStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(statusDay316, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay317, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay318, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay319, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay320, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay321, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay322, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(readStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(statusDay323, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay324, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay325, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay326, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay327, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay328, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay329, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(readStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(statusDay330, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay331, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay332, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay333, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay334, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay335, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay336, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(readStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(statusDay337, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay338, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay339, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay340, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay341, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay342, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay343, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(readStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(statusDay344, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay345, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay346, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay347, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay348, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay349, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay350, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(readStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(statusDay351, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay352, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay353, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay354, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay355, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay356, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay357, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(readStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(statusDay358, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay359, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay360, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay361, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay362, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay363, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay364, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(readStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(statusDay365, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay366, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(readStatusLayout.createSequentialGroup()
                        .addComponent(sep1)
                        .addGap(70, 70, 70)
                        .addComponent(oct)
                        .addGap(71, 71, 71)
                        .addComponent(nov)
                        .addGap(69, 69, 69)
                        .addComponent(dec)
                        .addGap(69, 69, 69)
                        .addComponent(jan)
                        .addGap(71, 71, 71)
                        .addComponent(feb)
                        .addGap(70, 70, 70)
                        .addComponent(mar)
                        .addGap(69, 69, 69)
                        .addComponent(apr)
                        .addGap(72, 72, 72)
                        .addComponent(may)
                        .addGap(68, 68, 68)
                        .addComponent(jun)
                        .addGap(71, 71, 71)
                        .addComponent(jul)
                        .addGap(75, 75, 75)
                        .addComponent(aug)
                        .addGap(70, 70, 70)
                        .addComponent(sep2)))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        readStatusLayout.setVerticalGroup(
            readStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(readStatusLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(readStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(readStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(readStatusLayout.createSequentialGroup()
                            .addComponent(statusDay365, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(statusDay366, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(110, 110, 110))
                        .addGroup(readStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(readStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(readStatusLayout.createSequentialGroup()
                                    .addComponent(statusDay358, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(statusDay359, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(statusDay360, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(statusDay361, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(statusDay362, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(statusDay363, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(statusDay364, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(readStatusLayout.createSequentialGroup()
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
                                    .addComponent(statusDay357, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(readStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(readStatusLayout.createSequentialGroup()
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
                                    .addComponent(statusDay350, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(readStatusLayout.createSequentialGroup()
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
                                    .addComponent(statusDay343, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(readStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(readStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(readStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(readStatusLayout.createSequentialGroup()
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
                                .addGroup(readStatusLayout.createSequentialGroup()
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
                                    .addComponent(statusDay329, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(readStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(readStatusLayout.createSequentialGroup()
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
                                .addGroup(readStatusLayout.createSequentialGroup()
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
                                    .addComponent(statusDay315, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(readStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(readStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(readStatusLayout.createSequentialGroup()
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
                                .addGroup(readStatusLayout.createSequentialGroup()
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
                                    .addComponent(statusDay301, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(readStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(readStatusLayout.createSequentialGroup()
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
                                .addGroup(readStatusLayout.createSequentialGroup()
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
                                    .addComponent(statusDay287, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(readStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(readStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(readStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(readStatusLayout.createSequentialGroup()
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
                                .addGroup(readStatusLayout.createSequentialGroup()
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
                                    .addComponent(statusDay273, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(readStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(readStatusLayout.createSequentialGroup()
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
                                .addGroup(readStatusLayout.createSequentialGroup()
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
                                    .addComponent(statusDay259, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(readStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(readStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(readStatusLayout.createSequentialGroup()
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
                                .addGroup(readStatusLayout.createSequentialGroup()
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
                                    .addComponent(statusDay245, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(readStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(readStatusLayout.createSequentialGroup()
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
                                .addGroup(readStatusLayout.createSequentialGroup()
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
                                    .addComponent(statusDay231, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(readStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(readStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(readStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(readStatusLayout.createSequentialGroup()
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
                                .addGroup(readStatusLayout.createSequentialGroup()
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
                                    .addComponent(statusDay217, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(readStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(readStatusLayout.createSequentialGroup()
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
                                .addGroup(readStatusLayout.createSequentialGroup()
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
                                    .addComponent(statusDay203, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(readStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(readStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(readStatusLayout.createSequentialGroup()
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
                                .addGroup(readStatusLayout.createSequentialGroup()
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
                                    .addComponent(statusDay189, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(readStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(readStatusLayout.createSequentialGroup()
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
                                .addGroup(readStatusLayout.createSequentialGroup()
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
                                    .addComponent(statusDay175, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(readStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(readStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(readStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(readStatusLayout.createSequentialGroup()
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
                                .addGroup(readStatusLayout.createSequentialGroup()
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
                                    .addComponent(statusDay161, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(readStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(readStatusLayout.createSequentialGroup()
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
                                .addGroup(readStatusLayout.createSequentialGroup()
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
                                    .addComponent(statusDay147, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(readStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(readStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(readStatusLayout.createSequentialGroup()
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
                                .addGroup(readStatusLayout.createSequentialGroup()
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
                                    .addComponent(statusDay133, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(readStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(readStatusLayout.createSequentialGroup()
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
                                .addGroup(readStatusLayout.createSequentialGroup()
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
                                    .addComponent(statusDay119, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(readStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(readStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(readStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(readStatusLayout.createSequentialGroup()
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
                                .addGroup(readStatusLayout.createSequentialGroup()
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
                                    .addComponent(statusDay105, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(readStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(readStatusLayout.createSequentialGroup()
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
                                .addGroup(readStatusLayout.createSequentialGroup()
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
                                    .addComponent(statusDay91, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(readStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(readStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(readStatusLayout.createSequentialGroup()
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
                                .addGroup(readStatusLayout.createSequentialGroup()
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
                                    .addComponent(statusDay77, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(readStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(readStatusLayout.createSequentialGroup()
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
                                .addGroup(readStatusLayout.createSequentialGroup()
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
                                    .addComponent(statusDay63, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(readStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(readStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(readStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(readStatusLayout.createSequentialGroup()
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
                                .addGroup(readStatusLayout.createSequentialGroup()
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
                                    .addComponent(statusDay49, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(readStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(readStatusLayout.createSequentialGroup()
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
                                .addGroup(readStatusLayout.createSequentialGroup()
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
                                    .addComponent(statusDay35, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(readStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(readStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(readStatusLayout.createSequentialGroup()
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
                                .addGroup(readStatusLayout.createSequentialGroup()
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
                                    .addComponent(statusDay21, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(readStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(readStatusLayout.createSequentialGroup()
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
                                .addGroup(readStatusLayout.createSequentialGroup()
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
                                    .addComponent(statusDay6, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(statusDay7, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(readStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sep1)
                    .addComponent(oct)
                    .addComponent(nov)
                    .addComponent(dec)
                    .addComponent(jan)
                    .addComponent(feb)
                    .addComponent(mar)
                    .addComponent(apr)
                    .addComponent(may)
                    .addComponent(jun)
                    .addComponent(jul)
                    .addComponent(aug)
                    .addComponent(sep2))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout libraryContentLayout = new javax.swing.GroupLayout(libraryContent);
        libraryContent.setLayout(libraryContentLayout);
        libraryContentLayout.setHorizontalGroup(
            libraryContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(libraryContentLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(libraryContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
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
                                    .addGap(79, 79, 79)
                                    .addComponent(selectedBookUnderline5, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(81, 81, 81)
                                    .addComponent(selectedBookUnderline6, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addComponent(readStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(libraryContentLayout.createSequentialGroup()
                        .addGap(35, 35, 35)
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
                        .addGap(27, 27, 27)))
                .addGap(26, 26, 26)
                .addComponent(bookDescriptionSideBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        libraryContentLayout.setVerticalGroup(
            libraryContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(libraryContentLayout.createSequentialGroup()
                .addComponent(bookDescriptionSideBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(libraryContentLayout.createSequentialGroup()
                .addGap(15, 15, 15)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(readStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
        });tabs.addChangeListener(e -> {
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

            // TAB 3 SELECTED → START TRACKING
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
            }

            // ANY OTHER TAB SELECTED → STOP TRACKING
            else {
                if (state.readingTimer != null && state.readingTimer.isRunning()) {
                    if (closeClicked) {
                        state.readingTimer.stop();
                        state.startTime = 0;
                        state.startScrollIndex = 0;
                        state.readingTimer = null;
                        return; // exit early, don’t log session
                    }
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

                    // Only log to DB if conditions met
                    if (duration >= TIME_THRESHOLD_MS && scrollDiff > SCROLL_THRESHOLD) {
                        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:" + dbPath)) {

                            // Add pagesRead column if missing (ignore error)
                            try (Statement stmt = conn.createStatement()) {
                                stmt.execute("ALTER TABLE session ADD COLUMN pagesRead INTEGER");
                            } catch (SQLException ignored) {}

                            try (PreparedStatement pstmt = conn.prepareStatement(
                                "INSERT INTO session (bookIndex, readFor, continueReading, scrollDiff, pagesRead) " +
                                "VALUES (?, ?, datetime('now'), ?, ?)"
                            )) {
                                pstmt.setInt(1, loadedBook);                             // book index
                                pstmt.setInt(2, (int) (duration / 1000 / 60));           // minutes
                                pstmt.setInt(3, scrollDiff);                              // scroll diff
                                pstmt.setInt(4, pagesRead);                               // pages read
                                pstmt.executeUpdate();
                            }

                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }

                    // Reset session state
                    state.readingTimer = null;
                    state.startTime = 0;
                    state.startScrollIndex = 0;
                }
            }

            // EXISTING LOGIC: Themer + Audio Control
            if (selectedIndex >= 0 && selectedIndex <= 10) {
                themer.setIcon(null);

                if (cafeClip != null && cafeClip.isRunning()) cafeClip.stop();
                if (treeClip != null && treeClip.isRunning()) treeClip.stop();
                if (rainClip != null && rainClip.isRunning()) rainClip.stop();
            }
        });

        tabs.addChangeListener(e -> {
            if (tabs.getSelectedIndex() == 1) {
                loadLastReadDirectly();
                updateStatusLabels();
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

    private void restoreBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_restoreBtnActionPerformed
        
    }//GEN-LAST:event_restoreBtnActionPerformed

    private void minimizeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_minimizeBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_minimizeBtnActionPerformed

    private void settingsBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_settingsBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_settingsBtnActionPerformed

    
    
    private void closeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeBtnActionPerformed
        closeClicked = true;
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
        addNoteBtn.setVisible(false);
        eyeHide.setVisible(true);
        saveNote.setVisible(true);
        writeYourReflection.setVisible(true);
        
        notesInput.setFocusable(true);
        notesInput.setEditable(true);

        // Set font color to black
        notesInput.setForeground(Color.BLACK);

        // Optionally, request focus so user can type immediately
        notesInput.requestFocusInWindow();
        notesTabLayers.moveToFront(eyeHide);
        notesTabLayers.moveToFront(saveNote);        
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

        // Get note text
        String noteText = notesInput.getText();
        if (noteText == null || noteText.trim().isEmpty()) return;

        // Get current selections (0-based indices)
        int testamentNum = testamentChooser.getSelectedIndex(); 
        int bookNum = bookChooser.getSelectedIndex();
        int chapterNum = chapterChooser.getSelectedIndex();

        // Combine with dashes
        String bookChapterIndex = testamentNum + "-" + bookNum + "-" + chapterNum;

        // Save to database with upsert logic
        String dbPath = "notesNJournals.db"; // database in project folder
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:" + dbPath)) {
            // Check if entry exists
            String checkSql = "SELECT COUNT(*) FROM notesReflections WHERE bookChapterIndex = ?";
            boolean exists = false;
            try (PreparedStatement checkStmt = conn.prepareStatement(checkSql)) {
                checkStmt.setString(1, bookChapterIndex);
                try (ResultSet rs = checkStmt.executeQuery()) {
                    if (rs.next() && rs.getInt(1) > 0) {
                        exists = true;
                    }
                }
            }

            if (exists) {
                // Update existing note
                String updateSql = "UPDATE notesReflections SET note = ? WHERE bookChapterIndex = ?";
                try (PreparedStatement updateStmt = conn.prepareStatement(updateSql)) {
                    updateStmt.setString(1, noteText);
                    updateStmt.setString(2, bookChapterIndex);
                    updateStmt.executeUpdate();
                }
            } else {
                // Insert new note
                String insertSql = "INSERT INTO notesReflections (bookChapterIndex, note) VALUES (?, ?)";
                try (PreparedStatement insertStmt = conn.prepareStatement(insertSql)) {
                    insertStmt.setString(1, bookChapterIndex);
                    insertStmt.setString(2, noteText);
                    insertStmt.executeUpdate();
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        // Change text color to light gray after saving
        notesInput.setForeground(new Color(100, 100, 100));

        // Keep notesInput editable for new notes
        notesInput.setFocusable(true);
        notesInput.setEditable(true);
        notesInput.requestFocusInWindow();
        
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
        
        continueReading.setVisible(false);
        
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
        
        continueReading.setVisible(false);
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
        
        continueReading.setVisible(false);
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
        
        continueReading.setVisible(false);
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
        
        continueReading.setVisible(false);
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
        
        continueReading.setVisible(false);
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
        
        continueReading.setVisible(false);
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
        
        continueReading.setVisible(false);
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
        
        continueReading.setVisible(false);
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
        
        continueReading.setVisible(false);
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
        
        continueReading.setVisible(false);
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
        
        continueReading.setVisible(false);
    }//GEN-LAST:event_bookDisplay12ActionPerformed

    private void readBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_readBtnActionPerformed
    // Immediately switch to loading tab
    tabs.setSelectedIndex(2);

    // Get the bookIndex early
    int assignedNum = randomNums.get(currentSelected - 1);
    loadedBook = assignedNum;
    
    // Update lastRead table
    int realBookIndex = -1;
    String dbPath = "bookStack.db";

    // Query the real bookIndex from bookStack
    try (Connection conn = DriverManager.getConnection("jdbc:sqlite:" + dbPath);
         PreparedStatement ps = conn.prepareStatement(
             "SELECT bookIndex FROM bookStack WHERE CAST(TRIM(bookIndex) AS INTEGER) = ?")) {

        ps.setInt(1, assignedNum);
        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                realBookIndex = rs.getInt("bookIndex");
            }
        }

    } catch (SQLException ex) {
        ex.printStackTrace();
    }

    // Save to lastRead table (clear previous row first)
    if (realBookIndex != -1) {
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:" + dbPath);
             Statement stmt = conn.createStatement()) {

            // Delete old lastRead row
            stmt.executeUpdate("DELETE FROM lastRead");

            // Insert new lastRead row
            try (PreparedStatement ps = conn.prepareStatement("INSERT INTO lastRead(bookIndex) VALUES(?)")) {
                ps.setInt(1, realBookIndex);
                ps.executeUpdate();
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


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

    private void continueReadingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_continueReadingActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_continueReadingActionPerformed

    private void langChooserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_langChooserActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_langChooserActionPerformed

    private void testamentChooserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_testamentChooserActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_testamentChooserActionPerformed

    
   
 
    
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
    private javax.swing.JLabel apr;
    private javax.swing.JButton audiobookBtn;
    private javax.swing.JLabel audiobookLabel;
    private javax.swing.JLabel aug;
    private javax.swing.JLabel bibleBtnLabel;
    private javax.swing.JPanel bibleTab;
    private javax.swing.JLabel biblestudyTitle;
    private javax.swing.JButton boldBtn;
    private javax.swing.JComboBox<String> bookChooser;
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
    private javax.swing.JButton continueReading;
    private javax.swing.JLabel dec;
    private javax.swing.JLabel description;
    private javax.swing.JButton exploreMoreBtn1;
    private javax.swing.JButton eyeHide;
    private javax.swing.JButton eyeShow;
    private javax.swing.JLabel feb;
    private javax.swing.JSlider fontSizeSlider;
    private javax.swing.JButton highlightBtn;
    private javax.swing.JButton homeBtn;
    private javax.swing.JLabel homeBtnLabel;
    private javax.swing.JButton hostJoinBtn;
    private javax.swing.JLabel hostJoinBtnLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel jan;
    private javax.swing.JButton journalBtn;
    private javax.swing.JLabel jul;
    private javax.swing.JLabel jun;
    private javax.swing.JComboBox<String> langChooser;
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
    private javax.swing.JLabel mar;
    private javax.swing.JLabel may;
    private javax.swing.JButton minimizeBtn;
    private javax.swing.JButton mute;
    private javax.swing.JPanel navBar;
    private javax.swing.JPanel nodesTabPanel;
    private javax.swing.JLabel notesBtnLabel;
    private javax.swing.JTextArea notesInput;
    private javax.swing.JLayeredPane notesTabLayers;
    private javax.swing.JPanel notesTabPanel;
    private javax.swing.JLabel nov;
    private javax.swing.JLabel oct;
    private javax.swing.JTextArea pdfDisplay;
    private javax.swing.JButton rainAmbience;
    private javax.swing.JButton reRandomizer;
    private javax.swing.JButton readBtn;
    private javax.swing.JPanel readStatus;
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
    private javax.swing.JLabel sep1;
    private javax.swing.JLabel sep2;
    private javax.swing.JButton settingsBtn;
    private javax.swing.JLabel settingsLabel;
    private javax.swing.JButton spacer;
    private javax.swing.JLabel statusDay1;
    private javax.swing.JLabel statusDay10;
    private javax.swing.JLabel statusDay100;
    private javax.swing.JLabel statusDay101;
    private javax.swing.JLabel statusDay102;
    private javax.swing.JLabel statusDay103;
    private javax.swing.JLabel statusDay104;
    private javax.swing.JLabel statusDay105;
    private javax.swing.JLabel statusDay106;
    private javax.swing.JLabel statusDay107;
    private javax.swing.JLabel statusDay108;
    private javax.swing.JLabel statusDay109;
    private javax.swing.JLabel statusDay11;
    private javax.swing.JLabel statusDay110;
    private javax.swing.JLabel statusDay111;
    private javax.swing.JLabel statusDay112;
    private javax.swing.JLabel statusDay113;
    private javax.swing.JLabel statusDay114;
    private javax.swing.JLabel statusDay115;
    private javax.swing.JLabel statusDay116;
    private javax.swing.JLabel statusDay117;
    private javax.swing.JLabel statusDay118;
    private javax.swing.JLabel statusDay119;
    private javax.swing.JLabel statusDay12;
    private javax.swing.JLabel statusDay120;
    private javax.swing.JLabel statusDay121;
    private javax.swing.JLabel statusDay122;
    private javax.swing.JLabel statusDay123;
    private javax.swing.JLabel statusDay124;
    private javax.swing.JLabel statusDay125;
    private javax.swing.JLabel statusDay126;
    private javax.swing.JLabel statusDay127;
    private javax.swing.JLabel statusDay128;
    private javax.swing.JLabel statusDay129;
    private javax.swing.JLabel statusDay13;
    private javax.swing.JLabel statusDay130;
    private javax.swing.JLabel statusDay131;
    private javax.swing.JLabel statusDay132;
    private javax.swing.JLabel statusDay133;
    private javax.swing.JLabel statusDay134;
    private javax.swing.JLabel statusDay135;
    private javax.swing.JLabel statusDay136;
    private javax.swing.JLabel statusDay137;
    private javax.swing.JLabel statusDay138;
    private javax.swing.JLabel statusDay139;
    private javax.swing.JLabel statusDay14;
    private javax.swing.JLabel statusDay140;
    private javax.swing.JLabel statusDay141;
    private javax.swing.JLabel statusDay142;
    private javax.swing.JLabel statusDay143;
    private javax.swing.JLabel statusDay144;
    private javax.swing.JLabel statusDay145;
    private javax.swing.JLabel statusDay146;
    private javax.swing.JLabel statusDay147;
    private javax.swing.JLabel statusDay148;
    private javax.swing.JLabel statusDay149;
    private javax.swing.JLabel statusDay15;
    private javax.swing.JLabel statusDay150;
    private javax.swing.JLabel statusDay151;
    private javax.swing.JLabel statusDay152;
    private javax.swing.JLabel statusDay153;
    private javax.swing.JLabel statusDay154;
    private javax.swing.JLabel statusDay155;
    private javax.swing.JLabel statusDay156;
    private javax.swing.JLabel statusDay157;
    private javax.swing.JLabel statusDay158;
    private javax.swing.JLabel statusDay159;
    private javax.swing.JLabel statusDay16;
    private javax.swing.JLabel statusDay160;
    private javax.swing.JLabel statusDay161;
    private javax.swing.JLabel statusDay162;
    private javax.swing.JLabel statusDay163;
    private javax.swing.JLabel statusDay164;
    private javax.swing.JLabel statusDay165;
    private javax.swing.JLabel statusDay166;
    private javax.swing.JLabel statusDay167;
    private javax.swing.JLabel statusDay168;
    private javax.swing.JLabel statusDay169;
    private javax.swing.JLabel statusDay17;
    private javax.swing.JLabel statusDay170;
    private javax.swing.JLabel statusDay171;
    private javax.swing.JLabel statusDay172;
    private javax.swing.JLabel statusDay173;
    private javax.swing.JLabel statusDay174;
    private javax.swing.JLabel statusDay175;
    private javax.swing.JLabel statusDay176;
    private javax.swing.JLabel statusDay177;
    private javax.swing.JLabel statusDay178;
    private javax.swing.JLabel statusDay179;
    private javax.swing.JLabel statusDay18;
    private javax.swing.JLabel statusDay180;
    private javax.swing.JLabel statusDay181;
    private javax.swing.JLabel statusDay182;
    private javax.swing.JLabel statusDay183;
    private javax.swing.JLabel statusDay184;
    private javax.swing.JLabel statusDay185;
    private javax.swing.JLabel statusDay186;
    private javax.swing.JLabel statusDay187;
    private javax.swing.JLabel statusDay188;
    private javax.swing.JLabel statusDay189;
    private javax.swing.JLabel statusDay19;
    private javax.swing.JLabel statusDay190;
    private javax.swing.JLabel statusDay191;
    private javax.swing.JLabel statusDay192;
    private javax.swing.JLabel statusDay193;
    private javax.swing.JLabel statusDay194;
    private javax.swing.JLabel statusDay195;
    private javax.swing.JLabel statusDay196;
    private javax.swing.JLabel statusDay197;
    private javax.swing.JLabel statusDay198;
    private javax.swing.JLabel statusDay199;
    private javax.swing.JLabel statusDay2;
    private javax.swing.JLabel statusDay20;
    private javax.swing.JLabel statusDay200;
    private javax.swing.JLabel statusDay201;
    private javax.swing.JLabel statusDay202;
    private javax.swing.JLabel statusDay203;
    private javax.swing.JLabel statusDay204;
    private javax.swing.JLabel statusDay205;
    private javax.swing.JLabel statusDay206;
    private javax.swing.JLabel statusDay207;
    private javax.swing.JLabel statusDay208;
    private javax.swing.JLabel statusDay209;
    private javax.swing.JLabel statusDay21;
    private javax.swing.JLabel statusDay210;
    private javax.swing.JLabel statusDay211;
    private javax.swing.JLabel statusDay212;
    private javax.swing.JLabel statusDay213;
    private javax.swing.JLabel statusDay214;
    private javax.swing.JLabel statusDay215;
    private javax.swing.JLabel statusDay216;
    private javax.swing.JLabel statusDay217;
    private javax.swing.JLabel statusDay218;
    private javax.swing.JLabel statusDay219;
    private javax.swing.JLabel statusDay22;
    private javax.swing.JLabel statusDay220;
    private javax.swing.JLabel statusDay221;
    private javax.swing.JLabel statusDay222;
    private javax.swing.JLabel statusDay223;
    private javax.swing.JLabel statusDay224;
    private javax.swing.JLabel statusDay225;
    private javax.swing.JLabel statusDay226;
    private javax.swing.JLabel statusDay227;
    private javax.swing.JLabel statusDay228;
    private javax.swing.JLabel statusDay229;
    private javax.swing.JLabel statusDay23;
    private javax.swing.JLabel statusDay230;
    private javax.swing.JLabel statusDay231;
    private javax.swing.JLabel statusDay232;
    private javax.swing.JLabel statusDay233;
    private javax.swing.JLabel statusDay234;
    private javax.swing.JLabel statusDay235;
    private javax.swing.JLabel statusDay236;
    private javax.swing.JLabel statusDay237;
    private javax.swing.JLabel statusDay238;
    private javax.swing.JLabel statusDay239;
    private javax.swing.JLabel statusDay24;
    private javax.swing.JLabel statusDay240;
    private javax.swing.JLabel statusDay241;
    private javax.swing.JLabel statusDay242;
    private javax.swing.JLabel statusDay243;
    private javax.swing.JLabel statusDay244;
    private javax.swing.JLabel statusDay245;
    private javax.swing.JLabel statusDay246;
    private javax.swing.JLabel statusDay247;
    private javax.swing.JLabel statusDay248;
    private javax.swing.JLabel statusDay249;
    private javax.swing.JLabel statusDay25;
    private javax.swing.JLabel statusDay250;
    private javax.swing.JLabel statusDay251;
    private javax.swing.JLabel statusDay252;
    private javax.swing.JLabel statusDay253;
    private javax.swing.JLabel statusDay254;
    private javax.swing.JLabel statusDay255;
    private javax.swing.JLabel statusDay256;
    private javax.swing.JLabel statusDay257;
    private javax.swing.JLabel statusDay258;
    private javax.swing.JLabel statusDay259;
    private javax.swing.JLabel statusDay26;
    private javax.swing.JLabel statusDay260;
    private javax.swing.JLabel statusDay261;
    private javax.swing.JLabel statusDay262;
    private javax.swing.JLabel statusDay263;
    private javax.swing.JLabel statusDay264;
    private javax.swing.JLabel statusDay265;
    private javax.swing.JLabel statusDay266;
    private javax.swing.JLabel statusDay267;
    private javax.swing.JLabel statusDay268;
    private javax.swing.JLabel statusDay269;
    private javax.swing.JLabel statusDay27;
    private javax.swing.JLabel statusDay270;
    private javax.swing.JLabel statusDay271;
    private javax.swing.JLabel statusDay272;
    private javax.swing.JLabel statusDay273;
    private javax.swing.JLabel statusDay274;
    private javax.swing.JLabel statusDay275;
    private javax.swing.JLabel statusDay276;
    private javax.swing.JLabel statusDay277;
    private javax.swing.JLabel statusDay278;
    private javax.swing.JLabel statusDay279;
    private javax.swing.JLabel statusDay28;
    private javax.swing.JLabel statusDay280;
    private javax.swing.JLabel statusDay281;
    private javax.swing.JLabel statusDay282;
    private javax.swing.JLabel statusDay283;
    private javax.swing.JLabel statusDay284;
    private javax.swing.JLabel statusDay285;
    private javax.swing.JLabel statusDay286;
    private javax.swing.JLabel statusDay287;
    private javax.swing.JLabel statusDay288;
    private javax.swing.JLabel statusDay289;
    private javax.swing.JLabel statusDay29;
    private javax.swing.JLabel statusDay290;
    private javax.swing.JLabel statusDay291;
    private javax.swing.JLabel statusDay292;
    private javax.swing.JLabel statusDay293;
    private javax.swing.JLabel statusDay294;
    private javax.swing.JLabel statusDay295;
    private javax.swing.JLabel statusDay296;
    private javax.swing.JLabel statusDay297;
    private javax.swing.JLabel statusDay298;
    private javax.swing.JLabel statusDay299;
    private javax.swing.JLabel statusDay3;
    private javax.swing.JLabel statusDay30;
    private javax.swing.JLabel statusDay300;
    private javax.swing.JLabel statusDay301;
    private javax.swing.JLabel statusDay302;
    private javax.swing.JLabel statusDay303;
    private javax.swing.JLabel statusDay304;
    private javax.swing.JLabel statusDay305;
    private javax.swing.JLabel statusDay306;
    private javax.swing.JLabel statusDay307;
    private javax.swing.JLabel statusDay308;
    private javax.swing.JLabel statusDay309;
    private javax.swing.JLabel statusDay31;
    private javax.swing.JLabel statusDay310;
    private javax.swing.JLabel statusDay311;
    private javax.swing.JLabel statusDay312;
    private javax.swing.JLabel statusDay313;
    private javax.swing.JLabel statusDay314;
    private javax.swing.JLabel statusDay315;
    private javax.swing.JLabel statusDay316;
    private javax.swing.JLabel statusDay317;
    private javax.swing.JLabel statusDay318;
    private javax.swing.JLabel statusDay319;
    private javax.swing.JLabel statusDay32;
    private javax.swing.JLabel statusDay320;
    private javax.swing.JLabel statusDay321;
    private javax.swing.JLabel statusDay322;
    private javax.swing.JLabel statusDay323;
    private javax.swing.JLabel statusDay324;
    private javax.swing.JLabel statusDay325;
    private javax.swing.JLabel statusDay326;
    private javax.swing.JLabel statusDay327;
    private javax.swing.JLabel statusDay328;
    private javax.swing.JLabel statusDay329;
    private javax.swing.JLabel statusDay33;
    private javax.swing.JLabel statusDay330;
    private javax.swing.JLabel statusDay331;
    private javax.swing.JLabel statusDay332;
    private javax.swing.JLabel statusDay333;
    private javax.swing.JLabel statusDay334;
    private javax.swing.JLabel statusDay335;
    private javax.swing.JLabel statusDay336;
    private javax.swing.JLabel statusDay337;
    private javax.swing.JLabel statusDay338;
    private javax.swing.JLabel statusDay339;
    private javax.swing.JLabel statusDay34;
    private javax.swing.JLabel statusDay340;
    private javax.swing.JLabel statusDay341;
    private javax.swing.JLabel statusDay342;
    private javax.swing.JLabel statusDay343;
    private javax.swing.JLabel statusDay344;
    private javax.swing.JLabel statusDay345;
    private javax.swing.JLabel statusDay346;
    private javax.swing.JLabel statusDay347;
    private javax.swing.JLabel statusDay348;
    private javax.swing.JLabel statusDay349;
    private javax.swing.JLabel statusDay35;
    private javax.swing.JLabel statusDay350;
    private javax.swing.JLabel statusDay351;
    private javax.swing.JLabel statusDay352;
    private javax.swing.JLabel statusDay353;
    private javax.swing.JLabel statusDay354;
    private javax.swing.JLabel statusDay355;
    private javax.swing.JLabel statusDay356;
    private javax.swing.JLabel statusDay357;
    private javax.swing.JLabel statusDay358;
    private javax.swing.JLabel statusDay359;
    private javax.swing.JLabel statusDay36;
    private javax.swing.JLabel statusDay360;
    private javax.swing.JLabel statusDay361;
    private javax.swing.JLabel statusDay362;
    private javax.swing.JLabel statusDay363;
    private javax.swing.JLabel statusDay364;
    private javax.swing.JLabel statusDay365;
    private javax.swing.JLabel statusDay366;
    private javax.swing.JLabel statusDay37;
    private javax.swing.JLabel statusDay38;
    private javax.swing.JLabel statusDay39;
    private javax.swing.JLabel statusDay4;
    private javax.swing.JLabel statusDay40;
    private javax.swing.JLabel statusDay41;
    private javax.swing.JLabel statusDay42;
    private javax.swing.JLabel statusDay43;
    private javax.swing.JLabel statusDay44;
    private javax.swing.JLabel statusDay45;
    private javax.swing.JLabel statusDay46;
    private javax.swing.JLabel statusDay47;
    private javax.swing.JLabel statusDay48;
    private javax.swing.JLabel statusDay49;
    private javax.swing.JLabel statusDay5;
    private javax.swing.JLabel statusDay50;
    private javax.swing.JLabel statusDay51;
    private javax.swing.JLabel statusDay52;
    private javax.swing.JLabel statusDay53;
    private javax.swing.JLabel statusDay54;
    private javax.swing.JLabel statusDay55;
    private javax.swing.JLabel statusDay56;
    private javax.swing.JLabel statusDay57;
    private javax.swing.JLabel statusDay58;
    private javax.swing.JLabel statusDay59;
    private javax.swing.JLabel statusDay6;
    private javax.swing.JLabel statusDay60;
    private javax.swing.JLabel statusDay61;
    private javax.swing.JLabel statusDay62;
    private javax.swing.JLabel statusDay63;
    private javax.swing.JLabel statusDay64;
    private javax.swing.JLabel statusDay65;
    private javax.swing.JLabel statusDay66;
    private javax.swing.JLabel statusDay67;
    private javax.swing.JLabel statusDay68;
    private javax.swing.JLabel statusDay69;
    private javax.swing.JLabel statusDay7;
    private javax.swing.JLabel statusDay70;
    private javax.swing.JLabel statusDay71;
    private javax.swing.JLabel statusDay72;
    private javax.swing.JLabel statusDay73;
    private javax.swing.JLabel statusDay74;
    private javax.swing.JLabel statusDay75;
    private javax.swing.JLabel statusDay76;
    private javax.swing.JLabel statusDay77;
    private javax.swing.JLabel statusDay78;
    private javax.swing.JLabel statusDay79;
    private javax.swing.JLabel statusDay8;
    private javax.swing.JLabel statusDay80;
    private javax.swing.JLabel statusDay81;
    private javax.swing.JLabel statusDay82;
    private javax.swing.JLabel statusDay83;
    private javax.swing.JLabel statusDay84;
    private javax.swing.JLabel statusDay85;
    private javax.swing.JLabel statusDay86;
    private javax.swing.JLabel statusDay87;
    private javax.swing.JLabel statusDay88;
    private javax.swing.JLabel statusDay89;
    private javax.swing.JLabel statusDay9;
    private javax.swing.JLabel statusDay90;
    private javax.swing.JLabel statusDay91;
    private javax.swing.JLabel statusDay92;
    private javax.swing.JLabel statusDay93;
    private javax.swing.JLabel statusDay94;
    private javax.swing.JLabel statusDay95;
    private javax.swing.JLabel statusDay96;
    private javax.swing.JLabel statusDay97;
    private javax.swing.JLabel statusDay98;
    private javax.swing.JLabel statusDay99;
    private javax.swing.JLayeredPane subTabLayered;
    private javax.swing.JTabbedPane tabs;
    private javax.swing.JComboBox<String> testamentChooser;
    private javax.swing.JLabel themer;
    private javax.swing.JPanel toolBox;
    private javax.swing.JPanel topBar;
    private javax.swing.JButton treeTheme;
    private javax.swing.JButton unmute;
    private javax.swing.JTextField volDisp;
    private javax.swing.JButton volMinus;
    private javax.swing.JButton volPLus;
    private javax.swing.JLabel writeYourReflection;
    // End of variables declaration//GEN-END:variables
}