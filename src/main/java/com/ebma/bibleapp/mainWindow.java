

package com.ebma.bibleapp;



import javax.swing.UIManager;
import com.formdev.flatlaf.FlatLightLaf;


import javax.swing.JPopupMenu;
import javax.swing.JMenuItem;

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




import java.io.File;
import java.nio.file.Files;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.Point;
import java.awt.Rectangle;
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
    


        


  /* 
 public void loadRandomBookTiles() {
    try (Connection conn = DriverManager.getConnection("jdbc:sqlite:bookStack.db")) {
        // Get min and max bookIndex
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT MIN(bookIndex) AS minIndex, MAX(bookIndex) AS maxIndex FROM bookStack");
        int minIndex = 0, maxIndex = 0;
        if (rs.next()) {
            minIndex = rs.getInt("minIndex");
            maxIndex = rs.getInt("maxIndex");
        }

        // Generate 12 unique random bookIndex values
        Set<Integer> randomIndices = new LinkedHashSet<>();
        Random rand = new Random();
        while (randomIndices.size() < 12) {
            int randomIndex = rand.nextInt(maxIndex - minIndex + 1) + minIndex;
            randomIndices.add(randomIndex);
        }

        // Prepare buttons array for looping
        JButton[] buttons = { bookDisp1, bookDisp2, bookDisp3, bookDisp4, bookDisp5, bookDisp6,
                              bookDisp7, bookDisp8, bookDisp9, bookDisp10, bookDisp11, bookDisp12 };

        int i = 0;
        for (int bookIndex : randomIndices) {
            // File name is just the index with .png
            String fileName = bookIndex + ".png";

            // Load icon from resources/bookTiles
            URL iconURL = getClass().getResource("/bookTiles/" + fileName);
            if (iconURL != null) {
                ImageIcon icon = new ImageIcon(iconURL);
                buttons[i].setIcon(icon);
            } else {
                System.err.println("Icon not found in resources: /bookTiles/" + fileName);
            }
            i++;
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }
}
       */
        
        


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tileOne4 = new javax.swing.JLabel();
        mainPanel_layered = new javax.swing.JLayeredPane();
        topBar = new javax.swing.JPanel();
        bookChooserDropDown = new javax.swing.JComboBox<>();
        jTextField1 = new javax.swing.JTextField();
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
        libraryRedo = new javax.swing.JPanel();
        libraryContent = new javax.swing.JPanel();
        readStatus1 = new javax.swing.JPanel();
        statusDay361 = new javax.swing.JButton();
        statusDay362 = new javax.swing.JButton();
        statusDay363 = new javax.swing.JButton();
        statusDay364 = new javax.swing.JButton();
        statusDay365 = new javax.swing.JButton();
        statusDay366 = new javax.swing.JButton();
        statusDay367 = new javax.swing.JButton();
        statusDay368 = new javax.swing.JButton();
        statusDay369 = new javax.swing.JButton();
        statusDay370 = new javax.swing.JButton();
        statusDay371 = new javax.swing.JButton();
        statusDay372 = new javax.swing.JButton();
        statusDay373 = new javax.swing.JButton();
        statusDay374 = new javax.swing.JButton();
        statusDay375 = new javax.swing.JButton();
        statusDay376 = new javax.swing.JButton();
        statusDay377 = new javax.swing.JButton();
        statusDay378 = new javax.swing.JButton();
        statusDay379 = new javax.swing.JButton();
        statusDay380 = new javax.swing.JButton();
        statusDay381 = new javax.swing.JButton();
        statusDay382 = new javax.swing.JButton();
        statusDay383 = new javax.swing.JButton();
        statusDay384 = new javax.swing.JButton();
        statusDay385 = new javax.swing.JButton();
        statusDay386 = new javax.swing.JButton();
        statusDay387 = new javax.swing.JButton();
        statusDay388 = new javax.swing.JButton();
        statusDay389 = new javax.swing.JButton();
        statusDay390 = new javax.swing.JButton();
        statusDay391 = new javax.swing.JButton();
        statusDay392 = new javax.swing.JButton();
        statusDay393 = new javax.swing.JButton();
        statusDay394 = new javax.swing.JButton();
        statusDay395 = new javax.swing.JButton();
        statusDay396 = new javax.swing.JButton();
        statusDay397 = new javax.swing.JButton();
        statusDay398 = new javax.swing.JButton();
        statusDay399 = new javax.swing.JButton();
        statusDay400 = new javax.swing.JButton();
        statusDay401 = new javax.swing.JButton();
        statusDay402 = new javax.swing.JButton();
        statusDay403 = new javax.swing.JButton();
        statusDay404 = new javax.swing.JButton();
        statusDay405 = new javax.swing.JButton();
        statusDay406 = new javax.swing.JButton();
        statusDay407 = new javax.swing.JButton();
        statusDay408 = new javax.swing.JButton();
        statusDay409 = new javax.swing.JButton();
        statusDay410 = new javax.swing.JButton();
        statusDay411 = new javax.swing.JButton();
        statusDay412 = new javax.swing.JButton();
        statusDay413 = new javax.swing.JButton();
        statusDay414 = new javax.swing.JButton();
        statusDay415 = new javax.swing.JButton();
        statusDay416 = new javax.swing.JButton();
        statusDay417 = new javax.swing.JButton();
        statusDay418 = new javax.swing.JButton();
        statusDay419 = new javax.swing.JButton();
        statusDay420 = new javax.swing.JButton();
        statusDay421 = new javax.swing.JButton();
        statusDay422 = new javax.swing.JButton();
        statusDay423 = new javax.swing.JButton();
        statusDay424 = new javax.swing.JButton();
        statusDay425 = new javax.swing.JButton();
        statusDay426 = new javax.swing.JButton();
        statusDay427 = new javax.swing.JButton();
        statusDay428 = new javax.swing.JButton();
        statusDay429 = new javax.swing.JButton();
        statusDay430 = new javax.swing.JButton();
        statusDay431 = new javax.swing.JButton();
        statusDay432 = new javax.swing.JButton();
        statusDay433 = new javax.swing.JButton();
        statusDay434 = new javax.swing.JButton();
        statusDay435 = new javax.swing.JButton();
        statusDay436 = new javax.swing.JButton();
        statusDay437 = new javax.swing.JButton();
        statusDay438 = new javax.swing.JButton();
        statusDay439 = new javax.swing.JButton();
        statusDay440 = new javax.swing.JButton();
        statusDay441 = new javax.swing.JButton();
        statusDay442 = new javax.swing.JButton();
        statusDay443 = new javax.swing.JButton();
        statusDay444 = new javax.swing.JButton();
        statusDay445 = new javax.swing.JButton();
        statusDay446 = new javax.swing.JButton();
        statusDay447 = new javax.swing.JButton();
        statusDay448 = new javax.swing.JButton();
        statusDay449 = new javax.swing.JButton();
        statusDay450 = new javax.swing.JButton();
        statusDay451 = new javax.swing.JButton();
        statusDay452 = new javax.swing.JButton();
        statusDay453 = new javax.swing.JButton();
        statusDay454 = new javax.swing.JButton();
        statusDay455 = new javax.swing.JButton();
        statusDay456 = new javax.swing.JButton();
        statusDay457 = new javax.swing.JButton();
        statusDay458 = new javax.swing.JButton();
        statusDay459 = new javax.swing.JButton();
        statusDay460 = new javax.swing.JButton();
        statusDay461 = new javax.swing.JButton();
        statusDay462 = new javax.swing.JButton();
        statusDay463 = new javax.swing.JButton();
        statusDay464 = new javax.swing.JButton();
        statusDay465 = new javax.swing.JButton();
        statusDay466 = new javax.swing.JButton();
        statusDay467 = new javax.swing.JButton();
        statusDay468 = new javax.swing.JButton();
        statusDay469 = new javax.swing.JButton();
        statusDay470 = new javax.swing.JButton();
        statusDay471 = new javax.swing.JButton();
        statusDay472 = new javax.swing.JButton();
        statusDay473 = new javax.swing.JButton();
        statusDay474 = new javax.swing.JButton();
        statusDay475 = new javax.swing.JButton();
        statusDay476 = new javax.swing.JButton();
        statusDay477 = new javax.swing.JButton();
        statusDay478 = new javax.swing.JButton();
        statusDay479 = new javax.swing.JButton();
        statusDay480 = new javax.swing.JButton();
        statusDay481 = new javax.swing.JButton();
        statusDay482 = new javax.swing.JButton();
        statusDay483 = new javax.swing.JButton();
        statusDay484 = new javax.swing.JButton();
        statusDay485 = new javax.swing.JButton();
        statusDay486 = new javax.swing.JButton();
        statusDay487 = new javax.swing.JButton();
        statusDay488 = new javax.swing.JButton();
        statusDay489 = new javax.swing.JButton();
        statusDay490 = new javax.swing.JButton();
        statusDay491 = new javax.swing.JButton();
        statusDay492 = new javax.swing.JButton();
        statusDay493 = new javax.swing.JButton();
        statusDay494 = new javax.swing.JButton();
        statusDay495 = new javax.swing.JButton();
        statusDay496 = new javax.swing.JButton();
        statusDay497 = new javax.swing.JButton();
        statusDay498 = new javax.swing.JButton();
        statusDay499 = new javax.swing.JButton();
        statusDay500 = new javax.swing.JButton();
        statusDay501 = new javax.swing.JButton();
        statusDay502 = new javax.swing.JButton();
        statusDay503 = new javax.swing.JButton();
        statusDay504 = new javax.swing.JButton();
        statusDay505 = new javax.swing.JButton();
        statusDay506 = new javax.swing.JButton();
        statusDay507 = new javax.swing.JButton();
        statusDay508 = new javax.swing.JButton();
        statusDay509 = new javax.swing.JButton();
        statusDay510 = new javax.swing.JButton();
        statusDay511 = new javax.swing.JButton();
        statusDay512 = new javax.swing.JButton();
        statusDay513 = new javax.swing.JButton();
        statusDay514 = new javax.swing.JButton();
        statusDay515 = new javax.swing.JButton();
        statusDay516 = new javax.swing.JButton();
        statusDay517 = new javax.swing.JButton();
        statusDay518 = new javax.swing.JButton();
        statusDay519 = new javax.swing.JButton();
        statusDay520 = new javax.swing.JButton();
        statusDay521 = new javax.swing.JButton();
        statusDay522 = new javax.swing.JButton();
        statusDay523 = new javax.swing.JButton();
        statusDay524 = new javax.swing.JButton();
        statusDay525 = new javax.swing.JButton();
        statusDay526 = new javax.swing.JButton();
        statusDay527 = new javax.swing.JButton();
        statusDay528 = new javax.swing.JButton();
        statusDay529 = new javax.swing.JButton();
        statusDay530 = new javax.swing.JButton();
        statusDay531 = new javax.swing.JButton();
        statusDay532 = new javax.swing.JButton();
        statusDay533 = new javax.swing.JButton();
        statusDay534 = new javax.swing.JButton();
        statusDay535 = new javax.swing.JButton();
        statusDay536 = new javax.swing.JButton();
        statusDay537 = new javax.swing.JButton();
        statusDay538 = new javax.swing.JButton();
        statusDay539 = new javax.swing.JButton();
        statusDay540 = new javax.swing.JButton();
        statusDay541 = new javax.swing.JButton();
        statusDay542 = new javax.swing.JButton();
        statusDay543 = new javax.swing.JButton();
        statusDay544 = new javax.swing.JButton();
        statusDay545 = new javax.swing.JButton();
        statusDay546 = new javax.swing.JButton();
        statusDay547 = new javax.swing.JButton();
        statusDay548 = new javax.swing.JButton();
        statusDay549 = new javax.swing.JButton();
        statusDay550 = new javax.swing.JButton();
        statusDay551 = new javax.swing.JButton();
        statusDay552 = new javax.swing.JButton();
        statusDay553 = new javax.swing.JButton();
        statusDay554 = new javax.swing.JButton();
        statusDay555 = new javax.swing.JButton();
        statusDay556 = new javax.swing.JButton();
        statusDay557 = new javax.swing.JButton();
        statusDay558 = new javax.swing.JButton();
        statusDay559 = new javax.swing.JButton();
        statusDay560 = new javax.swing.JButton();
        statusDay561 = new javax.swing.JButton();
        statusDay562 = new javax.swing.JButton();
        statusDay563 = new javax.swing.JButton();
        statusDay564 = new javax.swing.JButton();
        statusDay565 = new javax.swing.JButton();
        statusDay566 = new javax.swing.JButton();
        statusDay567 = new javax.swing.JButton();
        statusDay568 = new javax.swing.JButton();
        statusDay569 = new javax.swing.JButton();
        statusDay570 = new javax.swing.JButton();
        statusDay571 = new javax.swing.JButton();
        statusDay572 = new javax.swing.JButton();
        statusDay573 = new javax.swing.JButton();
        statusDay574 = new javax.swing.JButton();
        statusDay575 = new javax.swing.JButton();
        statusDay576 = new javax.swing.JButton();
        statusDay577 = new javax.swing.JButton();
        statusDay578 = new javax.swing.JButton();
        statusDay579 = new javax.swing.JButton();
        statusDay580 = new javax.swing.JButton();
        statusDay581 = new javax.swing.JButton();
        statusDay582 = new javax.swing.JButton();
        statusDay583 = new javax.swing.JButton();
        statusDay584 = new javax.swing.JButton();
        statusDay585 = new javax.swing.JButton();
        statusDay586 = new javax.swing.JButton();
        statusDay587 = new javax.swing.JButton();
        statusDay588 = new javax.swing.JButton();
        statusDay589 = new javax.swing.JButton();
        statusDay590 = new javax.swing.JButton();
        statusDay591 = new javax.swing.JButton();
        statusDay592 = new javax.swing.JButton();
        statusDay593 = new javax.swing.JButton();
        statusDay594 = new javax.swing.JButton();
        statusDay595 = new javax.swing.JButton();
        statusDay596 = new javax.swing.JButton();
        statusDay597 = new javax.swing.JButton();
        statusDay598 = new javax.swing.JButton();
        statusDay599 = new javax.swing.JButton();
        statusDay600 = new javax.swing.JButton();
        statusDay601 = new javax.swing.JButton();
        statusDay602 = new javax.swing.JButton();
        statusDay603 = new javax.swing.JButton();
        statusDay604 = new javax.swing.JButton();
        statusDay605 = new javax.swing.JButton();
        statusDay606 = new javax.swing.JButton();
        statusDay607 = new javax.swing.JButton();
        statusDay608 = new javax.swing.JButton();
        statusDay609 = new javax.swing.JButton();
        statusDay610 = new javax.swing.JButton();
        statusDay611 = new javax.swing.JButton();
        statusDay612 = new javax.swing.JButton();
        statusDay613 = new javax.swing.JButton();
        statusDay614 = new javax.swing.JButton();
        statusDay615 = new javax.swing.JButton();
        statusDay616 = new javax.swing.JButton();
        statusDay617 = new javax.swing.JButton();
        statusDay618 = new javax.swing.JButton();
        statusDay619 = new javax.swing.JButton();
        statusDay620 = new javax.swing.JButton();
        statusDay621 = new javax.swing.JButton();
        statusDay622 = new javax.swing.JButton();
        statusDay623 = new javax.swing.JButton();
        statusDay624 = new javax.swing.JButton();
        statusDay625 = new javax.swing.JButton();
        statusDay626 = new javax.swing.JButton();
        statusDay627 = new javax.swing.JButton();
        statusDay628 = new javax.swing.JButton();
        statusDay629 = new javax.swing.JButton();
        statusDay630 = new javax.swing.JButton();
        statusDay631 = new javax.swing.JButton();
        statusDay632 = new javax.swing.JButton();
        statusDay633 = new javax.swing.JButton();
        statusDay634 = new javax.swing.JButton();
        statusDay635 = new javax.swing.JButton();
        statusDay636 = new javax.swing.JButton();
        statusDay637 = new javax.swing.JButton();
        statusDay638 = new javax.swing.JButton();
        statusDay639 = new javax.swing.JButton();
        statusDay640 = new javax.swing.JButton();
        statusDay641 = new javax.swing.JButton();
        statusDay642 = new javax.swing.JButton();
        statusDay643 = new javax.swing.JButton();
        statusDay644 = new javax.swing.JButton();
        statusDay645 = new javax.swing.JButton();
        statusDay646 = new javax.swing.JButton();
        statusDay647 = new javax.swing.JButton();
        statusDay648 = new javax.swing.JButton();
        statusDay649 = new javax.swing.JButton();
        statusDay650 = new javax.swing.JButton();
        statusDay651 = new javax.swing.JButton();
        statusDay652 = new javax.swing.JButton();
        statusDay653 = new javax.swing.JButton();
        statusDay654 = new javax.swing.JButton();
        statusDay655 = new javax.swing.JButton();
        statusDay656 = new javax.swing.JButton();
        statusDay657 = new javax.swing.JButton();
        statusDay658 = new javax.swing.JButton();
        statusDay659 = new javax.swing.JButton();
        statusDay660 = new javax.swing.JButton();
        statusDay661 = new javax.swing.JButton();
        statusDay662 = new javax.swing.JButton();
        statusDay663 = new javax.swing.JButton();
        statusDay664 = new javax.swing.JButton();
        statusDay665 = new javax.swing.JButton();
        statusDay666 = new javax.swing.JButton();
        statusDay667 = new javax.swing.JButton();
        statusDay668 = new javax.swing.JButton();
        statusDay669 = new javax.swing.JButton();
        statusDay670 = new javax.swing.JButton();
        statusDay671 = new javax.swing.JButton();
        statusDay672 = new javax.swing.JButton();
        statusDay673 = new javax.swing.JButton();
        statusDay674 = new javax.swing.JButton();
        statusDay675 = new javax.swing.JButton();
        statusDay676 = new javax.swing.JButton();
        statusDay677 = new javax.swing.JButton();
        statusDay678 = new javax.swing.JButton();
        statusDay679 = new javax.swing.JButton();
        statusDay680 = new javax.swing.JButton();
        statusDay681 = new javax.swing.JButton();
        statusDay682 = new javax.swing.JButton();
        statusDay683 = new javax.swing.JButton();
        statusDay684 = new javax.swing.JButton();
        statusDay685 = new javax.swing.JButton();
        statusDay686 = new javax.swing.JButton();
        statusDay687 = new javax.swing.JButton();
        statusDay688 = new javax.swing.JButton();
        statusDay689 = new javax.swing.JButton();
        statusDay690 = new javax.swing.JButton();
        statusDay691 = new javax.swing.JButton();
        statusDay692 = new javax.swing.JButton();
        statusDay693 = new javax.swing.JButton();
        statusDay694 = new javax.swing.JButton();
        statusDay695 = new javax.swing.JButton();
        statusDay696 = new javax.swing.JButton();
        statusDay697 = new javax.swing.JButton();
        statusDay698 = new javax.swing.JButton();
        statusDay699 = new javax.swing.JButton();
        statusDay700 = new javax.swing.JButton();
        statusDay701 = new javax.swing.JButton();
        statusDay702 = new javax.swing.JButton();
        statusDay703 = new javax.swing.JButton();
        statusDay704 = new javax.swing.JButton();
        statusDay705 = new javax.swing.JButton();
        statusDay706 = new javax.swing.JButton();
        statusDay707 = new javax.swing.JButton();
        statusDay708 = new javax.swing.JButton();
        statusDay709 = new javax.swing.JButton();
        statusDay710 = new javax.swing.JButton();
        statusDay711 = new javax.swing.JButton();
        statusDay712 = new javax.swing.JButton();
        statusDay713 = new javax.swing.JButton();
        statusDay714 = new javax.swing.JButton();
        statusDay715 = new javax.swing.JButton();
        statusDay716 = new javax.swing.JButton();
        statusDay717 = new javax.swing.JButton();
        statusDay718 = new javax.swing.JButton();
        statusDay719 = new javax.swing.JButton();
        statusDay720 = new javax.swing.JButton();
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
        bookDescriptionSideBar1 = new javax.swing.JPanel();
        aboutTheBook1 = new javax.swing.JLabel();
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
        jPanel11 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();

        //tileOne4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bookTiles/1V1S9h1Ey1zGTa-QIn83.jpeg"))); // NOI18N

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        mainPanel_layered.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        topBar.setBackground(new java.awt.Color(255, 255, 255));
        topBar.setPreferredSize(new java.awt.Dimension(1427, 10));

        bookChooserDropDown.setFont(new java.awt.Font("Nokia Pure Headline Ultra Light", 1, 12)); // NOI18N
        bookChooserDropDown.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "አማርኛ", "English", "Afaan Oromoo", " " }));
        bookChooserDropDown.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bookChooserDropDownActionPerformed(evt);
            }
        });

        jTextField1.setText("Search");

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 1058, Short.MAX_VALUE)
                .addComponent(bookChooserDropDown, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
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
                            .addComponent(closeBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bookChooserDropDown, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
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
                            .addComponent(statusDay361, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay362, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay363, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay364, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay365, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay366, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay367, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(readStatus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(statusDay368, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay369, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay370, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay371, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay372, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay373, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay374, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(readStatus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(statusDay375, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay376, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay377, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay378, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay379, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay380, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay381, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(month14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(readStatus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(statusDay382, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(statusDay383, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(statusDay384, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(statusDay385, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(statusDay386, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(statusDay387, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(statusDay388, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(readStatus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(readStatus1Layout.createSequentialGroup()
                        .addGroup(readStatus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(statusDay389, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay390, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay391, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay392, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay393, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay394, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay395, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(readStatus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(statusDay396, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay397, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay398, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay399, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay400, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay401, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay402, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(readStatus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(statusDay403, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay404, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay405, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay406, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay407, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay408, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay409, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(readStatus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(statusDay410, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay411, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay412, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay413, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay414, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay415, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay416, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(month15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(readStatus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(readStatus1Layout.createSequentialGroup()
                        .addGroup(readStatus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(statusDay417, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay418, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay419, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay420, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay421, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay422, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay423, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(readStatus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(statusDay424, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay425, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay426, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay427, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay428, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay429, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay430, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(readStatus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(statusDay431, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay432, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay433, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay434, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay435, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay436, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay437, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(readStatus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(statusDay438, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay439, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay440, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay441, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay442, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay443, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay444, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(month16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(readStatus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(readStatus1Layout.createSequentialGroup()
                        .addGroup(readStatus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(statusDay445, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay446, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay447, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay448, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay449, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay450, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay451, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(readStatus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(statusDay452, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay453, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay454, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay455, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay456, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay457, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay458, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(readStatus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(statusDay459, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay460, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay461, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay462, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay463, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay464, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay465, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(readStatus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(statusDay466, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay467, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay468, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay469, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay470, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay471, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay472, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(month17))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(readStatus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(readStatus1Layout.createSequentialGroup()
                        .addGroup(readStatus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(statusDay473, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay474, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay475, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay476, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay477, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay478, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay479, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(readStatus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(statusDay480, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay481, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay482, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay483, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay484, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay485, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay486, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(readStatus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(statusDay487, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay488, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay489, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay490, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay491, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay492, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay493, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(readStatus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(statusDay494, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay495, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay496, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay497, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay498, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay499, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay500, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(month18))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(readStatus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(readStatus1Layout.createSequentialGroup()
                        .addGroup(readStatus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(statusDay501, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay502, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay503, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay504, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay505, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay506, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay507, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(readStatus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(statusDay508, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay509, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay510, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay511, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay512, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay513, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay514, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(readStatus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(statusDay515, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay516, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay517, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay518, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay519, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay520, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay521, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(readStatus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(statusDay522, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay523, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay524, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay525, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay526, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay527, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay528, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(month19))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(readStatus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(readStatus1Layout.createSequentialGroup()
                        .addGroup(readStatus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(statusDay529, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay530, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay531, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay532, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay533, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay534, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay535, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(readStatus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(statusDay536, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay537, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay538, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay539, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay540, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay541, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay542, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(readStatus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(statusDay543, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay544, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay545, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay546, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay547, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay548, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay549, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(readStatus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(statusDay550, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay551, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay552, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay553, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay554, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay555, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay556, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(month20))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(readStatus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(readStatus1Layout.createSequentialGroup()
                        .addGroup(readStatus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(statusDay557, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay558, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay559, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay560, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay561, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay562, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay563, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(readStatus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(statusDay564, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay565, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay566, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay567, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay568, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay569, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay570, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(readStatus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(statusDay571, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay572, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay573, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay574, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay575, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay576, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay577, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(readStatus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(statusDay578, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay579, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay580, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay581, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay582, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay583, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay584, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(month21))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(readStatus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(readStatus1Layout.createSequentialGroup()
                        .addGroup(readStatus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(statusDay585, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay586, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay587, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay588, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay589, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay590, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay591, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(readStatus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(statusDay592, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay593, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay594, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay595, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay596, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay597, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay598, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(readStatus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(statusDay599, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay600, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay601, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay602, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay603, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay604, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay605, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(readStatus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(statusDay606, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay607, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay608, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay609, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay610, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay611, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay612, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(month22))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(readStatus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(readStatus1Layout.createSequentialGroup()
                        .addGroup(readStatus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(statusDay613, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay614, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay615, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay616, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay617, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay618, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay619, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(readStatus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(statusDay620, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay621, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay622, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay623, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay624, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay625, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay626, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(readStatus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(statusDay627, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay628, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay629, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay630, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay631, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay632, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay633, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(readStatus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(statusDay634, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay635, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay636, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay637, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay638, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay639, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay640, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(month23))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(readStatus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(readStatus1Layout.createSequentialGroup()
                        .addGroup(readStatus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(statusDay641, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay642, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay643, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay644, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay645, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay646, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay647, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(readStatus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(statusDay648, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay649, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay650, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay651, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay652, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay653, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay654, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(readStatus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(statusDay655, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay656, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay657, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay658, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay659, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay660, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay661, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(readStatus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(statusDay662, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay663, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay664, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay665, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay666, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay667, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay668, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(month24))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(readStatus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(readStatus1Layout.createSequentialGroup()
                        .addGroup(readStatus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(statusDay669, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay670, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay671, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay672, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay673, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay674, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay675, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(readStatus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(statusDay676, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay677, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay678, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay679, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay680, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay681, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay682, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(readStatus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(statusDay683, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay684, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay685, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay686, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay687, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay688, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay689, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(readStatus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(statusDay690, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay691, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay692, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay693, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay694, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay695, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay696, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(month25))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(readStatus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(readStatus1Layout.createSequentialGroup()
                        .addGroup(readStatus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(statusDay697, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay698, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay699, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay700, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay701, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay702, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay703, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(readStatus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(statusDay704, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay705, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay706, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay707, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay708, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay709, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay710, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(readStatus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(statusDay711, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay712, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay713, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay714, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay715, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay716, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay717, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(readStatus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(statusDay718, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusDay720, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(readStatus1Layout.createSequentialGroup()
                                .addComponent(statusDay719, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(readStatus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(dayLabel5)
                                    .addComponent(dayLabel4)
                                    .addComponent(dayLabel6)))))
                    .addComponent(month26))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        readStatus1Layout.setVerticalGroup(
            readStatus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(readStatus1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(readStatus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(readStatus1Layout.createSequentialGroup()
                        .addComponent(statusDay704, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay705, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay706, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay707, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay708, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay709, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay710, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(readStatus1Layout.createSequentialGroup()
                        .addComponent(statusDay697, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay698, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay699, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay700, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay701, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay702, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay703, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(readStatus1Layout.createSequentialGroup()
                        .addComponent(statusDay690, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay691, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay692, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay693, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay694, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay695, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay696, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(readStatus1Layout.createSequentialGroup()
                        .addComponent(statusDay683, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay684, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay685, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay686, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay687, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay688, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay689, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(readStatus1Layout.createSequentialGroup()
                        .addComponent(statusDay676, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay677, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay678, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay679, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay680, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay681, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay682, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(readStatus1Layout.createSequentialGroup()
                        .addComponent(statusDay669, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay670, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay671, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay672, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay673, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay674, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay675, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(readStatus1Layout.createSequentialGroup()
                        .addComponent(statusDay662, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay663, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay664, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay665, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay666, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay667, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay668, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(readStatus1Layout.createSequentialGroup()
                        .addComponent(statusDay655, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay656, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay657, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay658, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay659, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay660, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay661, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(readStatus1Layout.createSequentialGroup()
                        .addComponent(statusDay648, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay649, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay650, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay651, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay652, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay653, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay654, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(readStatus1Layout.createSequentialGroup()
                        .addComponent(statusDay641, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay642, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay643, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay644, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay645, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay646, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay647, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(readStatus1Layout.createSequentialGroup()
                        .addComponent(statusDay634, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay635, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay636, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay637, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay638, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay639, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay640, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(readStatus1Layout.createSequentialGroup()
                        .addComponent(statusDay627, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay628, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay629, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay630, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay631, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay632, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay633, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(readStatus1Layout.createSequentialGroup()
                        .addComponent(statusDay620, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay621, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay622, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay623, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay624, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay625, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay626, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(readStatus1Layout.createSequentialGroup()
                        .addComponent(statusDay613, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay614, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay615, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay616, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay617, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay618, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay619, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(readStatus1Layout.createSequentialGroup()
                        .addComponent(statusDay606, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay607, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay608, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay609, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay610, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay611, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay612, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(readStatus1Layout.createSequentialGroup()
                        .addComponent(statusDay599, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay600, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay601, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay602, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay603, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay604, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay605, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(readStatus1Layout.createSequentialGroup()
                        .addComponent(statusDay592, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay593, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay594, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay595, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay596, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay597, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay598, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(readStatus1Layout.createSequentialGroup()
                        .addComponent(statusDay585, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay586, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay587, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay588, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay589, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay590, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay591, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(readStatus1Layout.createSequentialGroup()
                        .addComponent(statusDay578, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay579, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay580, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay581, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay582, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay583, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay584, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(readStatus1Layout.createSequentialGroup()
                        .addComponent(statusDay571, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay572, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay573, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay574, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay575, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay576, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay577, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(readStatus1Layout.createSequentialGroup()
                        .addComponent(statusDay564, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay565, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay566, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay567, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay568, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay569, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay570, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(readStatus1Layout.createSequentialGroup()
                        .addComponent(statusDay557, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay558, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay559, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay560, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay561, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay562, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay563, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(readStatus1Layout.createSequentialGroup()
                        .addComponent(statusDay550, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay551, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay552, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay553, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay554, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay555, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay556, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(readStatus1Layout.createSequentialGroup()
                        .addComponent(statusDay543, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay544, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay545, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay546, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay547, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay548, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay549, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(readStatus1Layout.createSequentialGroup()
                        .addComponent(statusDay536, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay537, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay538, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay539, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay540, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay541, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay542, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(readStatus1Layout.createSequentialGroup()
                        .addComponent(statusDay529, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay530, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay531, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay532, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay533, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay534, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay535, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(readStatus1Layout.createSequentialGroup()
                        .addComponent(statusDay522, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay523, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay524, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay525, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay526, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay527, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay528, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(readStatus1Layout.createSequentialGroup()
                        .addComponent(statusDay515, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay516, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay517, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay518, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay519, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay520, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay521, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(readStatus1Layout.createSequentialGroup()
                        .addComponent(statusDay508, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay509, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay510, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay511, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay512, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay513, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay514, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(readStatus1Layout.createSequentialGroup()
                        .addComponent(statusDay501, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay502, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay503, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay504, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay505, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay506, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay507, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(readStatus1Layout.createSequentialGroup()
                        .addComponent(statusDay494, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay495, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay496, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay497, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay498, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay499, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay500, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(readStatus1Layout.createSequentialGroup()
                        .addComponent(statusDay487, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay488, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay489, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay490, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay491, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay492, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay493, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(readStatus1Layout.createSequentialGroup()
                        .addComponent(statusDay480, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay481, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay482, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay483, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay484, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay485, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay486, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(readStatus1Layout.createSequentialGroup()
                        .addComponent(statusDay473, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay474, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay475, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay476, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay477, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay478, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay479, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(readStatus1Layout.createSequentialGroup()
                        .addComponent(statusDay466, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay467, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay468, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay469, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay470, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay471, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay472, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(readStatus1Layout.createSequentialGroup()
                        .addComponent(statusDay459, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay460, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay461, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay462, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay463, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay464, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay465, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(readStatus1Layout.createSequentialGroup()
                        .addComponent(statusDay452, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay453, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay454, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay455, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay456, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay457, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay458, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(readStatus1Layout.createSequentialGroup()
                        .addComponent(statusDay445, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay446, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay447, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay448, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay449, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay450, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay451, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(readStatus1Layout.createSequentialGroup()
                        .addComponent(statusDay438, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay439, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay440, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay441, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay442, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay443, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay444, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(readStatus1Layout.createSequentialGroup()
                        .addComponent(statusDay431, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay432, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay433, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay434, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay435, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay436, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay437, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(readStatus1Layout.createSequentialGroup()
                        .addComponent(statusDay424, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay425, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay426, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay427, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay428, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay429, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay430, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(readStatus1Layout.createSequentialGroup()
                        .addComponent(statusDay417, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay418, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay419, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay420, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay421, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay422, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay423, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(readStatus1Layout.createSequentialGroup()
                        .addComponent(statusDay410, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay411, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay412, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay413, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay414, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay415, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay416, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(readStatus1Layout.createSequentialGroup()
                        .addComponent(statusDay403, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay404, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay405, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay406, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay407, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay408, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay409, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(readStatus1Layout.createSequentialGroup()
                        .addComponent(statusDay396, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay397, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay398, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay399, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay400, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay401, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay402, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(readStatus1Layout.createSequentialGroup()
                        .addComponent(statusDay389, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay390, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay391, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay392, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay393, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay394, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay395, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(readStatus1Layout.createSequentialGroup()
                        .addComponent(statusDay382, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay383, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay384, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay385, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay386, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay387, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay388, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(readStatus1Layout.createSequentialGroup()
                        .addComponent(statusDay375, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay376, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay377, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay378, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay379, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay380, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay381, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(readStatus1Layout.createSequentialGroup()
                        .addComponent(statusDay368, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay369, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay370, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay371, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay372, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay373, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay374, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(readStatus1Layout.createSequentialGroup()
                        .addComponent(statusDay361, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay362, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay363, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay364, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay365, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay366, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay367, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(readStatus1Layout.createSequentialGroup()
                        .addGroup(readStatus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, readStatus1Layout.createSequentialGroup()
                                .addComponent(statusDay718, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(readStatus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(statusDay719, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(dayLabel4))
                                .addGroup(readStatus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(readStatus1Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(statusDay720, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(readStatus1Layout.createSequentialGroup()
                                        .addGap(27, 27, 27)
                                        .addComponent(dayLabel5)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(dayLabel6))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, readStatus1Layout.createSequentialGroup()
                                .addComponent(statusDay711, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay712, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay713, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay714, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay715, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusDay716, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusDay717, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)))
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

        aboutTheBook1.setFont(new java.awt.Font("Nokia Pure Headline", 0, 18)); // NOI18N
        aboutTheBook1.setForeground(new java.awt.Color(255, 255, 255));
        aboutTheBook1.setText("About the book");

        javax.swing.GroupLayout bookDescriptionSideBar1Layout = new javax.swing.GroupLayout(bookDescriptionSideBar1);
        bookDescriptionSideBar1.setLayout(bookDescriptionSideBar1Layout);
        bookDescriptionSideBar1Layout.setHorizontalGroup(
            bookDescriptionSideBar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bookDescriptionSideBar1Layout.createSequentialGroup()
                .addContainerGap(110, Short.MAX_VALUE)
                .addComponent(aboutTheBook1)
                .addGap(118, 118, 118))
        );
        bookDescriptionSideBar1Layout.setVerticalGroup(
            bookDescriptionSideBar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bookDescriptionSideBar1Layout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addComponent(aboutTheBook1)
                .addContainerGap(837, Short.MAX_VALUE))
        );

        exploreMoreBtn1.setBackground(new java.awt.Color(86, 211, 100));
        exploreMoreBtn1.setFont(new java.awt.Font("Nokia Pure Headline Ultra Light", 0, 12)); // NOI18N
        exploreMoreBtn1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8-arrow-right-15.png"))); // NOI18N
        exploreMoreBtn1.setText("Explore More");
        exploreMoreBtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exploreMoreBtn1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout libraryContentLayout = new javax.swing.GroupLayout(libraryContent);
        libraryContent.setLayout(libraryContentLayout);
        libraryContentLayout.setHorizontalGroup(
            libraryContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(libraryContentLayout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addGroup(libraryContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(libraryContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(libraryContentLayout.createSequentialGroup()
                            .addComponent(bookDisplay1, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(bookDisplay2, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(bookDisplay3, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(bookDisplay4, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(bookDisplay5, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(bookDisplay6, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                        .addComponent(readStatus1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(exploreMoreBtn1, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addComponent(bookDescriptionSideBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        libraryContentLayout.setVerticalGroup(
            libraryContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(libraryContentLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(exploreMoreBtn1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(libraryContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(bookDisplay1, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bookDisplay2, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bookDisplay3, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bookDisplay4, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bookDisplay5, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bookDisplay6, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(libraryContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(bookDisplay7, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bookDisplay8, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bookDisplay9, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bookDisplay10, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bookDisplay11, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bookDisplay12, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(readStatus1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(247, 247, 247))
            .addGroup(libraryContentLayout.createSequentialGroup()
                .addComponent(bookDescriptionSideBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout libraryRedoLayout = new javax.swing.GroupLayout(libraryRedo);
        libraryRedo.setLayout(libraryRedoLayout);
        libraryRedoLayout.setHorizontalGroup(
            libraryRedoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(libraryRedoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(libraryContent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        libraryRedoLayout.setVerticalGroup(
            libraryRedoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, libraryRedoLayout.createSequentialGroup()
                .addContainerGap(52, Short.MAX_VALUE)
                .addComponent(libraryContent, javax.swing.GroupLayout.PREFERRED_SIZE, 917, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        tabs.addTab("Notes", libraryRedo);

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1580, Short.MAX_VALUE)
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 975, Short.MAX_VALUE)
        );

        tabs.addTab("AudioBooks", jPanel11);

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1580, Short.MAX_VALUE)
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 975, Short.MAX_VALUE)
        );

        tabs.addTab("Commentaries", jPanel12);

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1580, Short.MAX_VALUE)
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 975, Short.MAX_VALUE)
        );

        tabs.addTab("HostJoin", jPanel13);

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

        mainPanel_layered.add(tabs, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, -50, 1580, 1010));

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
    private javax.swing.JButton bookmarkBtn;
    private javax.swing.JLabel bookmarksBtn;
    private javax.swing.JComboBox<String> chapterChooser;
    private javax.swing.JButton closeBtn;
    private javax.swing.JButton cmntrsBtn;
    private javax.swing.JLabel cmntrsBtnLabel;
    private javax.swing.JPanel cmtrsTabPanel;
    private javax.swing.JTabbedPane cmtryTabbedPanel;
    private javax.swing.JLabel dayLabel4;
    private javax.swing.JLabel dayLabel5;
    private javax.swing.JLabel dayLabel6;
    private javax.swing.JButton exploreMoreBtn1;
    private javax.swing.JButton eyeHide;
    private javax.swing.JButton eyeShow;
    private javax.swing.JSlider fontSizeSlider;
    private javax.swing.JButton highlightBtn;
    private javax.swing.JButton homeBtn;
    private javax.swing.JLabel homeBtnLabel;
    private javax.swing.JButton hostJoinBtn;
    private javax.swing.JLabel hostJoinBtnLabel;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JButton journalBtn;
    private javax.swing.JButton libraryBtn;
    private javax.swing.JPanel libraryContent;
    private javax.swing.JPanel libraryRedo;
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
    private javax.swing.JPanel navBar;
    private javax.swing.JPanel nodesTabPanel;
    private javax.swing.JLabel notesBtnLabel;
    private javax.swing.JTextArea notesInput;
    private javax.swing.JLayeredPane notesTabLayers;
    private javax.swing.JPanel notesTabPanel;
    private javax.swing.JPanel readStatus1;
    private javax.swing.JButton restoreBtn;
    private javax.swing.JButton saveNote;
    private javax.swing.JButton saveTick;
    private javax.swing.JButton searchBtn;
    private javax.swing.JLabel searchBtnLabel;
    private javax.swing.JButton settingsBtn;
    private javax.swing.JLabel settingsLabel;
    private javax.swing.JButton statusDay361;
    private javax.swing.JButton statusDay362;
    private javax.swing.JButton statusDay363;
    private javax.swing.JButton statusDay364;
    private javax.swing.JButton statusDay365;
    private javax.swing.JButton statusDay366;
    private javax.swing.JButton statusDay367;
    private javax.swing.JButton statusDay368;
    private javax.swing.JButton statusDay369;
    private javax.swing.JButton statusDay370;
    private javax.swing.JButton statusDay371;
    private javax.swing.JButton statusDay372;
    private javax.swing.JButton statusDay373;
    private javax.swing.JButton statusDay374;
    private javax.swing.JButton statusDay375;
    private javax.swing.JButton statusDay376;
    private javax.swing.JButton statusDay377;
    private javax.swing.JButton statusDay378;
    private javax.swing.JButton statusDay379;
    private javax.swing.JButton statusDay380;
    private javax.swing.JButton statusDay381;
    private javax.swing.JButton statusDay382;
    private javax.swing.JButton statusDay383;
    private javax.swing.JButton statusDay384;
    private javax.swing.JButton statusDay385;
    private javax.swing.JButton statusDay386;
    private javax.swing.JButton statusDay387;
    private javax.swing.JButton statusDay388;
    private javax.swing.JButton statusDay389;
    private javax.swing.JButton statusDay390;
    private javax.swing.JButton statusDay391;
    private javax.swing.JButton statusDay392;
    private javax.swing.JButton statusDay393;
    private javax.swing.JButton statusDay394;
    private javax.swing.JButton statusDay395;
    private javax.swing.JButton statusDay396;
    private javax.swing.JButton statusDay397;
    private javax.swing.JButton statusDay398;
    private javax.swing.JButton statusDay399;
    private javax.swing.JButton statusDay400;
    private javax.swing.JButton statusDay401;
    private javax.swing.JButton statusDay402;
    private javax.swing.JButton statusDay403;
    private javax.swing.JButton statusDay404;
    private javax.swing.JButton statusDay405;
    private javax.swing.JButton statusDay406;
    private javax.swing.JButton statusDay407;
    private javax.swing.JButton statusDay408;
    private javax.swing.JButton statusDay409;
    private javax.swing.JButton statusDay410;
    private javax.swing.JButton statusDay411;
    private javax.swing.JButton statusDay412;
    private javax.swing.JButton statusDay413;
    private javax.swing.JButton statusDay414;
    private javax.swing.JButton statusDay415;
    private javax.swing.JButton statusDay416;
    private javax.swing.JButton statusDay417;
    private javax.swing.JButton statusDay418;
    private javax.swing.JButton statusDay419;
    private javax.swing.JButton statusDay420;
    private javax.swing.JButton statusDay421;
    private javax.swing.JButton statusDay422;
    private javax.swing.JButton statusDay423;
    private javax.swing.JButton statusDay424;
    private javax.swing.JButton statusDay425;
    private javax.swing.JButton statusDay426;
    private javax.swing.JButton statusDay427;
    private javax.swing.JButton statusDay428;
    private javax.swing.JButton statusDay429;
    private javax.swing.JButton statusDay430;
    private javax.swing.JButton statusDay431;
    private javax.swing.JButton statusDay432;
    private javax.swing.JButton statusDay433;
    private javax.swing.JButton statusDay434;
    private javax.swing.JButton statusDay435;
    private javax.swing.JButton statusDay436;
    private javax.swing.JButton statusDay437;
    private javax.swing.JButton statusDay438;
    private javax.swing.JButton statusDay439;
    private javax.swing.JButton statusDay440;
    private javax.swing.JButton statusDay441;
    private javax.swing.JButton statusDay442;
    private javax.swing.JButton statusDay443;
    private javax.swing.JButton statusDay444;
    private javax.swing.JButton statusDay445;
    private javax.swing.JButton statusDay446;
    private javax.swing.JButton statusDay447;
    private javax.swing.JButton statusDay448;
    private javax.swing.JButton statusDay449;
    private javax.swing.JButton statusDay450;
    private javax.swing.JButton statusDay451;
    private javax.swing.JButton statusDay452;
    private javax.swing.JButton statusDay453;
    private javax.swing.JButton statusDay454;
    private javax.swing.JButton statusDay455;
    private javax.swing.JButton statusDay456;
    private javax.swing.JButton statusDay457;
    private javax.swing.JButton statusDay458;
    private javax.swing.JButton statusDay459;
    private javax.swing.JButton statusDay460;
    private javax.swing.JButton statusDay461;
    private javax.swing.JButton statusDay462;
    private javax.swing.JButton statusDay463;
    private javax.swing.JButton statusDay464;
    private javax.swing.JButton statusDay465;
    private javax.swing.JButton statusDay466;
    private javax.swing.JButton statusDay467;
    private javax.swing.JButton statusDay468;
    private javax.swing.JButton statusDay469;
    private javax.swing.JButton statusDay470;
    private javax.swing.JButton statusDay471;
    private javax.swing.JButton statusDay472;
    private javax.swing.JButton statusDay473;
    private javax.swing.JButton statusDay474;
    private javax.swing.JButton statusDay475;
    private javax.swing.JButton statusDay476;
    private javax.swing.JButton statusDay477;
    private javax.swing.JButton statusDay478;
    private javax.swing.JButton statusDay479;
    private javax.swing.JButton statusDay480;
    private javax.swing.JButton statusDay481;
    private javax.swing.JButton statusDay482;
    private javax.swing.JButton statusDay483;
    private javax.swing.JButton statusDay484;
    private javax.swing.JButton statusDay485;
    private javax.swing.JButton statusDay486;
    private javax.swing.JButton statusDay487;
    private javax.swing.JButton statusDay488;
    private javax.swing.JButton statusDay489;
    private javax.swing.JButton statusDay490;
    private javax.swing.JButton statusDay491;
    private javax.swing.JButton statusDay492;
    private javax.swing.JButton statusDay493;
    private javax.swing.JButton statusDay494;
    private javax.swing.JButton statusDay495;
    private javax.swing.JButton statusDay496;
    private javax.swing.JButton statusDay497;
    private javax.swing.JButton statusDay498;
    private javax.swing.JButton statusDay499;
    private javax.swing.JButton statusDay500;
    private javax.swing.JButton statusDay501;
    private javax.swing.JButton statusDay502;
    private javax.swing.JButton statusDay503;
    private javax.swing.JButton statusDay504;
    private javax.swing.JButton statusDay505;
    private javax.swing.JButton statusDay506;
    private javax.swing.JButton statusDay507;
    private javax.swing.JButton statusDay508;
    private javax.swing.JButton statusDay509;
    private javax.swing.JButton statusDay510;
    private javax.swing.JButton statusDay511;
    private javax.swing.JButton statusDay512;
    private javax.swing.JButton statusDay513;
    private javax.swing.JButton statusDay514;
    private javax.swing.JButton statusDay515;
    private javax.swing.JButton statusDay516;
    private javax.swing.JButton statusDay517;
    private javax.swing.JButton statusDay518;
    private javax.swing.JButton statusDay519;
    private javax.swing.JButton statusDay520;
    private javax.swing.JButton statusDay521;
    private javax.swing.JButton statusDay522;
    private javax.swing.JButton statusDay523;
    private javax.swing.JButton statusDay524;
    private javax.swing.JButton statusDay525;
    private javax.swing.JButton statusDay526;
    private javax.swing.JButton statusDay527;
    private javax.swing.JButton statusDay528;
    private javax.swing.JButton statusDay529;
    private javax.swing.JButton statusDay530;
    private javax.swing.JButton statusDay531;
    private javax.swing.JButton statusDay532;
    private javax.swing.JButton statusDay533;
    private javax.swing.JButton statusDay534;
    private javax.swing.JButton statusDay535;
    private javax.swing.JButton statusDay536;
    private javax.swing.JButton statusDay537;
    private javax.swing.JButton statusDay538;
    private javax.swing.JButton statusDay539;
    private javax.swing.JButton statusDay540;
    private javax.swing.JButton statusDay541;
    private javax.swing.JButton statusDay542;
    private javax.swing.JButton statusDay543;
    private javax.swing.JButton statusDay544;
    private javax.swing.JButton statusDay545;
    private javax.swing.JButton statusDay546;
    private javax.swing.JButton statusDay547;
    private javax.swing.JButton statusDay548;
    private javax.swing.JButton statusDay549;
    private javax.swing.JButton statusDay550;
    private javax.swing.JButton statusDay551;
    private javax.swing.JButton statusDay552;
    private javax.swing.JButton statusDay553;
    private javax.swing.JButton statusDay554;
    private javax.swing.JButton statusDay555;
    private javax.swing.JButton statusDay556;
    private javax.swing.JButton statusDay557;
    private javax.swing.JButton statusDay558;
    private javax.swing.JButton statusDay559;
    private javax.swing.JButton statusDay560;
    private javax.swing.JButton statusDay561;
    private javax.swing.JButton statusDay562;
    private javax.swing.JButton statusDay563;
    private javax.swing.JButton statusDay564;
    private javax.swing.JButton statusDay565;
    private javax.swing.JButton statusDay566;
    private javax.swing.JButton statusDay567;
    private javax.swing.JButton statusDay568;
    private javax.swing.JButton statusDay569;
    private javax.swing.JButton statusDay570;
    private javax.swing.JButton statusDay571;
    private javax.swing.JButton statusDay572;
    private javax.swing.JButton statusDay573;
    private javax.swing.JButton statusDay574;
    private javax.swing.JButton statusDay575;
    private javax.swing.JButton statusDay576;
    private javax.swing.JButton statusDay577;
    private javax.swing.JButton statusDay578;
    private javax.swing.JButton statusDay579;
    private javax.swing.JButton statusDay580;
    private javax.swing.JButton statusDay581;
    private javax.swing.JButton statusDay582;
    private javax.swing.JButton statusDay583;
    private javax.swing.JButton statusDay584;
    private javax.swing.JButton statusDay585;
    private javax.swing.JButton statusDay586;
    private javax.swing.JButton statusDay587;
    private javax.swing.JButton statusDay588;
    private javax.swing.JButton statusDay589;
    private javax.swing.JButton statusDay590;
    private javax.swing.JButton statusDay591;
    private javax.swing.JButton statusDay592;
    private javax.swing.JButton statusDay593;
    private javax.swing.JButton statusDay594;
    private javax.swing.JButton statusDay595;
    private javax.swing.JButton statusDay596;
    private javax.swing.JButton statusDay597;
    private javax.swing.JButton statusDay598;
    private javax.swing.JButton statusDay599;
    private javax.swing.JButton statusDay600;
    private javax.swing.JButton statusDay601;
    private javax.swing.JButton statusDay602;
    private javax.swing.JButton statusDay603;
    private javax.swing.JButton statusDay604;
    private javax.swing.JButton statusDay605;
    private javax.swing.JButton statusDay606;
    private javax.swing.JButton statusDay607;
    private javax.swing.JButton statusDay608;
    private javax.swing.JButton statusDay609;
    private javax.swing.JButton statusDay610;
    private javax.swing.JButton statusDay611;
    private javax.swing.JButton statusDay612;
    private javax.swing.JButton statusDay613;
    private javax.swing.JButton statusDay614;
    private javax.swing.JButton statusDay615;
    private javax.swing.JButton statusDay616;
    private javax.swing.JButton statusDay617;
    private javax.swing.JButton statusDay618;
    private javax.swing.JButton statusDay619;
    private javax.swing.JButton statusDay620;
    private javax.swing.JButton statusDay621;
    private javax.swing.JButton statusDay622;
    private javax.swing.JButton statusDay623;
    private javax.swing.JButton statusDay624;
    private javax.swing.JButton statusDay625;
    private javax.swing.JButton statusDay626;
    private javax.swing.JButton statusDay627;
    private javax.swing.JButton statusDay628;
    private javax.swing.JButton statusDay629;
    private javax.swing.JButton statusDay630;
    private javax.swing.JButton statusDay631;
    private javax.swing.JButton statusDay632;
    private javax.swing.JButton statusDay633;
    private javax.swing.JButton statusDay634;
    private javax.swing.JButton statusDay635;
    private javax.swing.JButton statusDay636;
    private javax.swing.JButton statusDay637;
    private javax.swing.JButton statusDay638;
    private javax.swing.JButton statusDay639;
    private javax.swing.JButton statusDay640;
    private javax.swing.JButton statusDay641;
    private javax.swing.JButton statusDay642;
    private javax.swing.JButton statusDay643;
    private javax.swing.JButton statusDay644;
    private javax.swing.JButton statusDay645;
    private javax.swing.JButton statusDay646;
    private javax.swing.JButton statusDay647;
    private javax.swing.JButton statusDay648;
    private javax.swing.JButton statusDay649;
    private javax.swing.JButton statusDay650;
    private javax.swing.JButton statusDay651;
    private javax.swing.JButton statusDay652;
    private javax.swing.JButton statusDay653;
    private javax.swing.JButton statusDay654;
    private javax.swing.JButton statusDay655;
    private javax.swing.JButton statusDay656;
    private javax.swing.JButton statusDay657;
    private javax.swing.JButton statusDay658;
    private javax.swing.JButton statusDay659;
    private javax.swing.JButton statusDay660;
    private javax.swing.JButton statusDay661;
    private javax.swing.JButton statusDay662;
    private javax.swing.JButton statusDay663;
    private javax.swing.JButton statusDay664;
    private javax.swing.JButton statusDay665;
    private javax.swing.JButton statusDay666;
    private javax.swing.JButton statusDay667;
    private javax.swing.JButton statusDay668;
    private javax.swing.JButton statusDay669;
    private javax.swing.JButton statusDay670;
    private javax.swing.JButton statusDay671;
    private javax.swing.JButton statusDay672;
    private javax.swing.JButton statusDay673;
    private javax.swing.JButton statusDay674;
    private javax.swing.JButton statusDay675;
    private javax.swing.JButton statusDay676;
    private javax.swing.JButton statusDay677;
    private javax.swing.JButton statusDay678;
    private javax.swing.JButton statusDay679;
    private javax.swing.JButton statusDay680;
    private javax.swing.JButton statusDay681;
    private javax.swing.JButton statusDay682;
    private javax.swing.JButton statusDay683;
    private javax.swing.JButton statusDay684;
    private javax.swing.JButton statusDay685;
    private javax.swing.JButton statusDay686;
    private javax.swing.JButton statusDay687;
    private javax.swing.JButton statusDay688;
    private javax.swing.JButton statusDay689;
    private javax.swing.JButton statusDay690;
    private javax.swing.JButton statusDay691;
    private javax.swing.JButton statusDay692;
    private javax.swing.JButton statusDay693;
    private javax.swing.JButton statusDay694;
    private javax.swing.JButton statusDay695;
    private javax.swing.JButton statusDay696;
    private javax.swing.JButton statusDay697;
    private javax.swing.JButton statusDay698;
    private javax.swing.JButton statusDay699;
    private javax.swing.JButton statusDay700;
    private javax.swing.JButton statusDay701;
    private javax.swing.JButton statusDay702;
    private javax.swing.JButton statusDay703;
    private javax.swing.JButton statusDay704;
    private javax.swing.JButton statusDay705;
    private javax.swing.JButton statusDay706;
    private javax.swing.JButton statusDay707;
    private javax.swing.JButton statusDay708;
    private javax.swing.JButton statusDay709;
    private javax.swing.JButton statusDay710;
    private javax.swing.JButton statusDay711;
    private javax.swing.JButton statusDay712;
    private javax.swing.JButton statusDay713;
    private javax.swing.JButton statusDay714;
    private javax.swing.JButton statusDay715;
    private javax.swing.JButton statusDay716;
    private javax.swing.JButton statusDay717;
    private javax.swing.JButton statusDay718;
    private javax.swing.JButton statusDay719;
    private javax.swing.JButton statusDay720;
    private javax.swing.JTabbedPane tabs;
    private javax.swing.JComboBox<String> testamentChooser;
    private javax.swing.JLabel tileOne4;
    private javax.swing.JPanel topBar;
    private javax.swing.JComboBox<String> verseChooser;
    // End of variables declaration//GEN-END:variables
}