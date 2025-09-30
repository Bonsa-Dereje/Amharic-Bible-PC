

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
    




    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

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
        libraryTab = new javax.swing.JPanel();
        upperBar = new javax.swing.JPanel();
        book1 = new javax.swing.JButton();
        book2 = new javax.swing.JButton();
        book3 = new javax.swing.JButton();
        book4 = new javax.swing.JButton();
        moreBtn1 = new javax.swing.JButton();
        bottomBar = new javax.swing.JPanel();
        readStatusPanel3 = new javax.swing.JPanel();
        readStatusTile1 = new javax.swing.JButton();
        readStatusTile2 = new javax.swing.JButton();
        readStatusTile3 = new javax.swing.JButton();
        readStatusTile4 = new javax.swing.JButton();
        readStatusTile5 = new javax.swing.JButton();
        readStatusTile6 = new javax.swing.JButton();
        readStatusTile7 = new javax.swing.JButton();
        readStatusTile8 = new javax.swing.JButton();
        readStatusTile9 = new javax.swing.JButton();
        readStatusTile10 = new javax.swing.JButton();
        readStatusTile11 = new javax.swing.JButton();
        readStatusTile12 = new javax.swing.JButton();
        readStatusTile13 = new javax.swing.JButton();
        readStatusTile14 = new javax.swing.JButton();
        readStatusTile15 = new javax.swing.JButton();
        readStatusTile16 = new javax.swing.JButton();
        readStatusTile17 = new javax.swing.JButton();
        readStatusTile18 = new javax.swing.JButton();
        readStatusTile19 = new javax.swing.JButton();
        readStatusTile20 = new javax.swing.JButton();
        readStatusTile21 = new javax.swing.JButton();
        readStatusTile22 = new javax.swing.JButton();
        readStatusTile23 = new javax.swing.JButton();
        readStatusTile24 = new javax.swing.JButton();
        readStatusTile25 = new javax.swing.JButton();
        readStatusTile26 = new javax.swing.JButton();
        readStatusTile27 = new javax.swing.JButton();
        readStatusTile28 = new javax.swing.JButton();
        readStatusTile29 = new javax.swing.JButton();
        readStatusTile30 = new javax.swing.JButton();
        readStatusTile31 = new javax.swing.JButton();
        readStatusTile32 = new javax.swing.JButton();
        readStatusTile33 = new javax.swing.JButton();
        readStatusTile34 = new javax.swing.JButton();
        readStatusTile35 = new javax.swing.JButton();
        readStatusTile36 = new javax.swing.JButton();
        readStatusTile37 = new javax.swing.JButton();
        readStatusTile38 = new javax.swing.JButton();
        readStatusTile39 = new javax.swing.JButton();
        readStatusTile40 = new javax.swing.JButton();
        readStatusTile41 = new javax.swing.JButton();
        readStatusTile42 = new javax.swing.JButton();
        readStatusTile43 = new javax.swing.JButton();
        readStatusTile44 = new javax.swing.JButton();
        readStatusTile45 = new javax.swing.JButton();
        readStatusTile46 = new javax.swing.JButton();
        readStatusTile47 = new javax.swing.JButton();
        readStatusTile48 = new javax.swing.JButton();
        readStatusTile49 = new javax.swing.JButton();
        readStatusTile50 = new javax.swing.JButton();
        readStatusTile51 = new javax.swing.JButton();
        readStatusTile52 = new javax.swing.JButton();
        readStatusTile53 = new javax.swing.JButton();
        readStatusTile54 = new javax.swing.JButton();
        readStatusTile55 = new javax.swing.JButton();
        readStatusTile56 = new javax.swing.JButton();
        readStatusTile57 = new javax.swing.JButton();
        readStatusTile58 = new javax.swing.JButton();
        readStatusTile59 = new javax.swing.JButton();
        readStatusTile60 = new javax.swing.JButton();
        readStatusTile61 = new javax.swing.JButton();
        readStatusTile62 = new javax.swing.JButton();
        readStatusTile63 = new javax.swing.JButton();
        readStatusTile64 = new javax.swing.JButton();
        readStatusTile65 = new javax.swing.JButton();
        readStatusTile66 = new javax.swing.JButton();
        readStatusTile67 = new javax.swing.JButton();
        readStatusTile68 = new javax.swing.JButton();
        readStatusTile69 = new javax.swing.JButton();
        readStatusTile70 = new javax.swing.JButton();
        readStatusTile71 = new javax.swing.JButton();
        readStatusTile72 = new javax.swing.JButton();
        readStatusTile73 = new javax.swing.JButton();
        readStatusTile74 = new javax.swing.JButton();
        readStatusTile75 = new javax.swing.JButton();
        readStatusTile76 = new javax.swing.JButton();
        readStatusTile77 = new javax.swing.JButton();
        readStatusTile78 = new javax.swing.JButton();
        readStatusTile79 = new javax.swing.JButton();
        readStatusTile80 = new javax.swing.JButton();
        readStatusTile81 = new javax.swing.JButton();
        readStatusTile82 = new javax.swing.JButton();
        readStatusTile83 = new javax.swing.JButton();
        readStatusTile84 = new javax.swing.JButton();
        readStatusTile85 = new javax.swing.JButton();
        readStatusTile86 = new javax.swing.JButton();
        readStatusTile87 = new javax.swing.JButton();
        readStatusTile88 = new javax.swing.JButton();
        readStatusTile89 = new javax.swing.JButton();
        readStatusTile90 = new javax.swing.JButton();
        readStatusTile91 = new javax.swing.JButton();
        readStatusTile92 = new javax.swing.JButton();
        readStatusTile93 = new javax.swing.JButton();
        readStatusTile94 = new javax.swing.JButton();
        readStatusTile95 = new javax.swing.JButton();
        readStatusTile96 = new javax.swing.JButton();
        readStatusTile97 = new javax.swing.JButton();
        readStatusTile98 = new javax.swing.JButton();
        readStatusTile99 = new javax.swing.JButton();
        readStatusTile100 = new javax.swing.JButton();
        readStatusTile101 = new javax.swing.JButton();
        readStatusTile102 = new javax.swing.JButton();
        readStatusTile103 = new javax.swing.JButton();
        readStatusTile104 = new javax.swing.JButton();
        readStatusTile105 = new javax.swing.JButton();
        readStatusTile106 = new javax.swing.JButton();
        readStatusTile107 = new javax.swing.JButton();
        readStatusTile108 = new javax.swing.JButton();
        readStatusTile109 = new javax.swing.JButton();
        readStatusTile110 = new javax.swing.JButton();
        readStatusTile111 = new javax.swing.JButton();
        readStatusTile112 = new javax.swing.JButton();
        readStatusTile113 = new javax.swing.JButton();
        readStatusTile114 = new javax.swing.JButton();
        readStatusTile115 = new javax.swing.JButton();
        readStatusTile116 = new javax.swing.JButton();
        readStatusTile117 = new javax.swing.JButton();
        readStatusTile118 = new javax.swing.JButton();
        readStatusTile119 = new javax.swing.JButton();
        readStatusTile120 = new javax.swing.JButton();
        readStatusTile121 = new javax.swing.JButton();
        readStatusTile122 = new javax.swing.JButton();
        readStatusTile123 = new javax.swing.JButton();
        readStatusTile124 = new javax.swing.JButton();
        readStatusTile125 = new javax.swing.JButton();
        readStatusTile126 = new javax.swing.JButton();
        readStatusTile127 = new javax.swing.JButton();
        readStatusTile128 = new javax.swing.JButton();
        readStatusTile129 = new javax.swing.JButton();
        readStatusTile130 = new javax.swing.JButton();
        readStatusTile131 = new javax.swing.JButton();
        readStatusTile132 = new javax.swing.JButton();
        readStatusTile133 = new javax.swing.JButton();
        readStatusTile134 = new javax.swing.JButton();
        readStatusTile135 = new javax.swing.JButton();
        readStatusTile136 = new javax.swing.JButton();
        readStatusTile137 = new javax.swing.JButton();
        readStatusTile138 = new javax.swing.JButton();
        readStatusTile139 = new javax.swing.JButton();
        readStatusTile140 = new javax.swing.JButton();
        readStatusTile141 = new javax.swing.JButton();
        readStatusTile142 = new javax.swing.JButton();
        readStatusTile143 = new javax.swing.JButton();
        readStatusTile144 = new javax.swing.JButton();
        readStatusTile145 = new javax.swing.JButton();
        readStatusTile146 = new javax.swing.JButton();
        readStatusTile147 = new javax.swing.JButton();
        readStatusTile148 = new javax.swing.JButton();
        readStatusTile149 = new javax.swing.JButton();
        readStatusTile150 = new javax.swing.JButton();
        readStatusTile151 = new javax.swing.JButton();
        readStatusTile152 = new javax.swing.JButton();
        readStatusTile153 = new javax.swing.JButton();
        readStatusTile154 = new javax.swing.JButton();
        readStatusTile155 = new javax.swing.JButton();
        readStatusTile156 = new javax.swing.JButton();
        readStatusTile157 = new javax.swing.JButton();
        readStatusTile158 = new javax.swing.JButton();
        readStatusTile159 = new javax.swing.JButton();
        readStatusTile160 = new javax.swing.JButton();
        readStatusTile161 = new javax.swing.JButton();
        readStatusTile162 = new javax.swing.JButton();
        readStatusTile163 = new javax.swing.JButton();
        readStatusTile164 = new javax.swing.JButton();
        readStatusTile165 = new javax.swing.JButton();
        readStatusTile166 = new javax.swing.JButton();
        readStatusTile167 = new javax.swing.JButton();
        readStatusTile168 = new javax.swing.JButton();
        readStatusTile169 = new javax.swing.JButton();
        readStatusTile170 = new javax.swing.JButton();
        readStatusTile171 = new javax.swing.JButton();
        readStatusTile172 = new javax.swing.JButton();
        readStatusTile173 = new javax.swing.JButton();
        readStatusTile174 = new javax.swing.JButton();
        readStatusTile175 = new javax.swing.JButton();
        readStatusTile176 = new javax.swing.JButton();
        readStatusTile177 = new javax.swing.JButton();
        readStatusTile178 = new javax.swing.JButton();
        readStatusTile179 = new javax.swing.JButton();
        readStatusTile180 = new javax.swing.JButton();
        readStatusTile181 = new javax.swing.JButton();
        readStatusTile182 = new javax.swing.JButton();
        readStatusTile183 = new javax.swing.JButton();
        readStatusTile184 = new javax.swing.JButton();
        readStatusTile185 = new javax.swing.JButton();
        readStatusTile186 = new javax.swing.JButton();
        readStatusTile187 = new javax.swing.JButton();
        readStatusTile188 = new javax.swing.JButton();
        readStatusTile189 = new javax.swing.JButton();
        readStatusTile190 = new javax.swing.JButton();
        readStatusTile191 = new javax.swing.JButton();
        readStatusTile192 = new javax.swing.JButton();
        readStatusTile193 = new javax.swing.JButton();
        readStatusTile194 = new javax.swing.JButton();
        readStatusTile195 = new javax.swing.JButton();
        readStatusTile196 = new javax.swing.JButton();
        readStatusTile197 = new javax.swing.JButton();
        readStatusTile198 = new javax.swing.JButton();
        readStatusTile199 = new javax.swing.JButton();
        readStatusTile200 = new javax.swing.JButton();
        readStatusTile201 = new javax.swing.JButton();
        readStatusTile202 = new javax.swing.JButton();
        readStatusTile203 = new javax.swing.JButton();
        readStatusTile204 = new javax.swing.JButton();
        readStatusTile205 = new javax.swing.JButton();
        readStatusTile206 = new javax.swing.JButton();
        readStatusTile207 = new javax.swing.JButton();
        readStatusTile208 = new javax.swing.JButton();
        readStatusTile209 = new javax.swing.JButton();
        readStatusTile210 = new javax.swing.JButton();
        readStatusTile211 = new javax.swing.JButton();
        readStatusTile212 = new javax.swing.JButton();
        readStatusTile213 = new javax.swing.JButton();
        readStatusTile214 = new javax.swing.JButton();
        readStatusTile215 = new javax.swing.JButton();
        readStatusTile216 = new javax.swing.JButton();
        readStatusTile217 = new javax.swing.JButton();
        readStatusTile218 = new javax.swing.JButton();
        readStatusTile219 = new javax.swing.JButton();
        readStatusTile220 = new javax.swing.JButton();
        readStatusTile221 = new javax.swing.JButton();
        readStatusTile222 = new javax.swing.JButton();
        readStatusTile223 = new javax.swing.JButton();
        readStatusTile224 = new javax.swing.JButton();
        readStatusTile225 = new javax.swing.JButton();
        readStatusTile226 = new javax.swing.JButton();
        readStatusTile227 = new javax.swing.JButton();
        readStatusTile228 = new javax.swing.JButton();
        readStatusTile229 = new javax.swing.JButton();
        readStatusTile230 = new javax.swing.JButton();
        readStatusTile231 = new javax.swing.JButton();
        readStatusTile232 = new javax.swing.JButton();
        readStatusTile233 = new javax.swing.JButton();
        readStatusTile234 = new javax.swing.JButton();
        readStatusTile235 = new javax.swing.JButton();
        readStatusTile236 = new javax.swing.JButton();
        readStatusTile237 = new javax.swing.JButton();
        readStatusTile238 = new javax.swing.JButton();
        readStatusTile239 = new javax.swing.JButton();
        readStatusTile240 = new javax.swing.JButton();
        readStatusTile241 = new javax.swing.JButton();
        readStatusTile242 = new javax.swing.JButton();
        readStatusTile243 = new javax.swing.JButton();
        readStatusTile244 = new javax.swing.JButton();
        readStatusTile245 = new javax.swing.JButton();
        readStatusTile246 = new javax.swing.JButton();
        readStatusTile247 = new javax.swing.JButton();
        readStatusTile248 = new javax.swing.JButton();
        readStatusTile249 = new javax.swing.JButton();
        readStatusTile250 = new javax.swing.JButton();
        readStatusTile251 = new javax.swing.JButton();
        readStatusTile252 = new javax.swing.JButton();
        readStatusTile253 = new javax.swing.JButton();
        readStatusTile254 = new javax.swing.JButton();
        readStatusTile255 = new javax.swing.JButton();
        readStatusTile256 = new javax.swing.JButton();
        readStatusTile257 = new javax.swing.JButton();
        readStatusTile258 = new javax.swing.JButton();
        readStatusTile259 = new javax.swing.JButton();
        readStatusTile260 = new javax.swing.JButton();
        readStatusTile261 = new javax.swing.JButton();
        readStatusTile262 = new javax.swing.JButton();
        readStatusTile263 = new javax.swing.JButton();
        readStatusTile264 = new javax.swing.JButton();
        readStatusTile265 = new javax.swing.JButton();
        readStatusTile266 = new javax.swing.JButton();
        readStatusTile267 = new javax.swing.JButton();
        readStatusTile268 = new javax.swing.JButton();
        readStatusTile269 = new javax.swing.JButton();
        readStatusTile270 = new javax.swing.JButton();
        readStatusTile271 = new javax.swing.JButton();
        readStatusTile272 = new javax.swing.JButton();
        readStatusTile273 = new javax.swing.JButton();
        readStatusTile274 = new javax.swing.JButton();
        readStatusTile275 = new javax.swing.JButton();
        readStatusTile276 = new javax.swing.JButton();
        readStatusTile277 = new javax.swing.JButton();
        readStatusTile278 = new javax.swing.JButton();
        readStatusTile279 = new javax.swing.JButton();
        readStatusTile280 = new javax.swing.JButton();
        readStatusTile281 = new javax.swing.JButton();
        readStatusTile282 = new javax.swing.JButton();
        readStatusTile283 = new javax.swing.JButton();
        readStatusTile284 = new javax.swing.JButton();
        readStatusTile285 = new javax.swing.JButton();
        readStatusTile286 = new javax.swing.JButton();
        readStatusTile287 = new javax.swing.JButton();
        readStatusTile288 = new javax.swing.JButton();
        readStatusTile289 = new javax.swing.JButton();
        readStatusTile290 = new javax.swing.JButton();
        readStatusTile291 = new javax.swing.JButton();
        readStatusTile292 = new javax.swing.JButton();
        readStatusTile293 = new javax.swing.JButton();
        readStatusTile294 = new javax.swing.JButton();
        readStatusTile295 = new javax.swing.JButton();
        readStatusTile296 = new javax.swing.JButton();
        readStatusTile297 = new javax.swing.JButton();
        readStatusTile298 = new javax.swing.JButton();
        readStatusTile299 = new javax.swing.JButton();
        readStatusTile300 = new javax.swing.JButton();
        readStatusTile301 = new javax.swing.JButton();
        readStatusTile302 = new javax.swing.JButton();
        readStatusTile303 = new javax.swing.JButton();
        readStatusTile304 = new javax.swing.JButton();
        readStatusTile305 = new javax.swing.JButton();
        readStatusTile306 = new javax.swing.JButton();
        readStatusTile307 = new javax.swing.JButton();
        readStatusTile308 = new javax.swing.JButton();
        readStatusTile309 = new javax.swing.JButton();
        readStatusTile310 = new javax.swing.JButton();
        readStatusTile311 = new javax.swing.JButton();
        readStatusTile312 = new javax.swing.JButton();
        readStatusTile313 = new javax.swing.JButton();
        readStatusTile314 = new javax.swing.JButton();
        readStatusTile315 = new javax.swing.JButton();
        readStatusTile316 = new javax.swing.JButton();
        readStatusTile317 = new javax.swing.JButton();
        readStatusTile318 = new javax.swing.JButton();
        readStatusTile319 = new javax.swing.JButton();
        readStatusTile320 = new javax.swing.JButton();
        readStatusTile321 = new javax.swing.JButton();
        readStatusTile322 = new javax.swing.JButton();
        readStatusTile323 = new javax.swing.JButton();
        readStatusTile324 = new javax.swing.JButton();
        readStatusTile325 = new javax.swing.JButton();
        readStatusTile326 = new javax.swing.JButton();
        readStatusTile327 = new javax.swing.JButton();
        readStatusTile328 = new javax.swing.JButton();
        readStatusTile329 = new javax.swing.JButton();
        readStatusTile330 = new javax.swing.JButton();
        readStatusTile331 = new javax.swing.JButton();
        readStatusTile332 = new javax.swing.JButton();
        readStatusTile333 = new javax.swing.JButton();
        readStatusTile334 = new javax.swing.JButton();
        readStatusTile335 = new javax.swing.JButton();
        readStatusTile336 = new javax.swing.JButton();
        readStatusTile337 = new javax.swing.JButton();
        readStatusTile338 = new javax.swing.JButton();
        readStatusTile339 = new javax.swing.JButton();
        readStatusTile340 = new javax.swing.JButton();
        readStatusTile341 = new javax.swing.JButton();
        readStatusTile342 = new javax.swing.JButton();
        readStatusTile343 = new javax.swing.JButton();
        readStatusTile344 = new javax.swing.JButton();
        readStatusTile345 = new javax.swing.JButton();
        readStatusTile346 = new javax.swing.JButton();
        readStatusTile347 = new javax.swing.JButton();
        readStatusTile348 = new javax.swing.JButton();
        readStatusTile349 = new javax.swing.JButton();
        readStatusTile350 = new javax.swing.JButton();
        readStatusTile351 = new javax.swing.JButton();
        readStatusTile352 = new javax.swing.JButton();
        readStatusTile353 = new javax.swing.JButton();
        readStatusTile354 = new javax.swing.JButton();
        readStatusTile355 = new javax.swing.JButton();
        readStatusTile356 = new javax.swing.JButton();
        readStatusTile357 = new javax.swing.JButton();
        readStatusTile358 = new javax.swing.JButton();
        readStatusTile359 = new javax.swing.JButton();
        readStatusTile360 = new javax.swing.JButton();
        readStatusTile361 = new javax.swing.JButton();
        readStatusTile362 = new javax.swing.JButton();
        readStatusTile363 = new javax.swing.JButton();
        readStatusTile364 = new javax.swing.JButton();
        readStatusTile365 = new javax.swing.JButton();
        readStatusTile366 = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        sep = new javax.swing.JLabel();
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
        topSpacer = new javax.swing.JPanel();
        inLibraryTabs = new javax.swing.JPanel();
        pdfReader = new javax.swing.JScrollPane();
        jPanel10 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        book5 = new javax.swing.JButton();
        bookDescriptionTileOne8 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        book6 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        layeredStitcher = new javax.swing.JPanel();
        bookTileImage = new javax.swing.JPanel();
        bookTileClickable = new javax.swing.JButton();
        bookNameDisplay = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();

        tileOne4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bookTiles/1V1S9h1Ey1zGTa-QIn83.jpeg"))); // NOI18N

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

        book1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bookTiles/1V1S9h1Ey1zGTa-QIn83.jpeg"))); // NOI18N
        book1.setBorder(null);
        book1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                book1ActionPerformed(evt);
            }
        });

        book2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bookTiles/1V1SrF1BZ2uDBV-ndXZj.jpeg"))); // NOI18N
        book2.setBorder(null);

        book3.setBackground(new java.awt.Color(204, 204, 204));
        book3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bookTiles/1V1SyG12FcdRFF-m2vIY.jpeg"))); // NOI18N
        book3.setBorder(null);
        book3.setBorderPainted(false);
        book3.setContentAreaFilled(false);

        book4.setBackground(new java.awt.Color(204, 204, 204));
        book4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bookTiles/1V1SFW1LlyJ9te-cLuOA.jpeg"))); // NOI18N
        book4.setBorder(null);
        book4.setBorderPainted(false);
        book4.setContentAreaFilled(false);

        moreBtn1.setBackground(new java.awt.Color(204, 204, 204));
        moreBtn1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/arrowRight.png"))); // NOI18N
        moreBtn1.setContentAreaFilled(false);
        moreBtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                moreBtn1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout upperBarLayout = new javax.swing.GroupLayout(upperBar);
        upperBar.setLayout(upperBarLayout);
        upperBarLayout.setHorizontalGroup(
            upperBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, upperBarLayout.createSequentialGroup()
                .addGap(771, 771, 771)
                .addComponent(book3)
                .addGap(31, 31, 31)
                .addComponent(book4, javax.swing.GroupLayout.DEFAULT_SIZE, 321, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(moreBtn1)
                .addGap(27, 27, 27))
            .addGroup(upperBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(upperBarLayout.createSequentialGroup()
                    .addGap(44, 44, 44)
                    .addComponent(book1)
                    .addGap(42, 42, 42)
                    .addComponent(book2, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(846, Short.MAX_VALUE)))
        );
        upperBarLayout.setVerticalGroup(
            upperBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(upperBarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(upperBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(book3, javax.swing.GroupLayout.PREFERRED_SIZE, 449, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(book4, javax.swing.GroupLayout.PREFERRED_SIZE, 449, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(upperBarLayout.createSequentialGroup()
                .addGap(127, 127, 127)
                .addComponent(moreBtn1, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(upperBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(upperBarLayout.createSequentialGroup()
                    .addGap(8, 8, 8)
                    .addGroup(upperBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(book1, javax.swing.GroupLayout.PREFERRED_SIZE, 449, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(book2, javax.swing.GroupLayout.PREFERRED_SIZE, 449, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        bottomBar.setLayout(null);

        readStatusPanel3.setBackground(new java.awt.Color(204, 204, 204));

        javax.swing.GroupLayout readStatusPanel3Layout = new javax.swing.GroupLayout(readStatusPanel3);
        readStatusPanel3.setLayout(readStatusPanel3Layout);
        readStatusPanel3Layout.setHorizontalGroup(
            readStatusPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(readStatusPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(readStatusPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(readStatusTile1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile5, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile6, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile7, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(readStatusPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(readStatusTile8, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile9, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile10, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile11, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile12, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile13, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile14, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(readStatusPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(readStatusTile15, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile16, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile17, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile18, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile19, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile20, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile21, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(readStatusPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(readStatusTile22, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile23, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile24, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile25, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile26, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile27, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile28, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(readStatusPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(readStatusTile29, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile30, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile31, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile32, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile33, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile34, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile35, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(readStatusPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(readStatusTile36, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile37, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile38, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile39, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile40, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile41, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile42, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(readStatusPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(readStatusTile43, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile44, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile45, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile46, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile47, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile48, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile49, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(readStatusPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(readStatusTile50, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile51, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile52, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile53, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile54, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile55, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile56, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(readStatusPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(readStatusTile57, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile58, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile59, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile60, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile61, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile62, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile63, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(readStatusPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(readStatusTile64, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile65, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile66, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile67, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile68, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile69, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile70, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(readStatusPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(readStatusTile71, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile72, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile73, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile74, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile75, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile76, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile77, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(readStatusPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(readStatusTile78, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile79, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile80, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile81, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile82, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile83, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile84, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(readStatusPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(readStatusTile85, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile86, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile87, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile88, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile89, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile90, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile91, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(readStatusPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(readStatusTile92, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile93, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile94, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile95, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile96, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile97, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile98, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(readStatusPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(readStatusTile99, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile100, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile101, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile102, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile103, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile104, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile105, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(readStatusPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(readStatusTile106, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile107, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile108, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile109, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile110, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile111, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile112, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(readStatusPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(readStatusTile113, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile114, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile115, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile116, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile117, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile118, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile119, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(readStatusPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(readStatusTile120, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile121, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile122, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile123, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile124, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile125, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile126, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(readStatusPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(readStatusTile127, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile128, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile129, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile130, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile131, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile132, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile133, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(readStatusPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(readStatusTile134, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile135, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile136, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile137, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile138, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile139, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile140, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(readStatusPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(readStatusTile141, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile142, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile143, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile144, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile145, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile146, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile147, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(readStatusPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(readStatusTile148, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile149, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile150, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile151, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile152, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile153, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile154, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(readStatusPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(readStatusTile155, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile156, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile157, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile158, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile159, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile160, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile161, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(readStatusPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(readStatusTile162, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile163, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile164, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile165, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile166, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile167, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile168, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(readStatusPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(readStatusTile169, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile170, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile171, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile172, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile173, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile174, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile175, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(readStatusPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(readStatusTile176, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile177, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile178, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile179, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile180, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile181, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile182, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(readStatusPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(readStatusTile183, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile184, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile185, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile186, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile187, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile188, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile189, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(readStatusPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(readStatusTile190, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile191, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile192, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile193, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile194, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile195, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile196, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(readStatusPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(readStatusTile197, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile198, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile199, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile200, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile201, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile202, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile203, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(readStatusPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(readStatusTile204, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile205, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile206, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile207, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile208, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile209, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile210, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(readStatusPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(readStatusTile211, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile212, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile213, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile214, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile215, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile216, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile217, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(readStatusPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(readStatusTile218, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile219, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile220, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile221, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile222, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile223, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile224, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(readStatusPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(readStatusTile225, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile226, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile227, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile228, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile229, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile230, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile231, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(readStatusPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(readStatusTile232, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile233, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile234, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile235, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile236, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile237, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile238, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(readStatusPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(readStatusTile239, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile240, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile241, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile242, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile243, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile244, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile245, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(readStatusPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(readStatusTile246, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile247, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile248, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile249, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile250, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile251, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile252, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(readStatusPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(readStatusTile253, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile254, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile255, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile256, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile257, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile258, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile259, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(readStatusPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(readStatusTile260, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile261, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile262, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile263, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile264, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile265, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile266, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(readStatusPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(readStatusTile267, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile268, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile269, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile270, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile271, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile272, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile273, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(readStatusPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(readStatusTile274, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile275, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile276, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile277, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile278, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile279, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile280, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(readStatusPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(readStatusTile281, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile282, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile283, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile284, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile285, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile286, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile287, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(readStatusPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(readStatusTile288, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile289, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile290, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile291, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile292, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile293, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile294, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(readStatusPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(readStatusTile295, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile296, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile297, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile298, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile299, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile300, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile301, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(readStatusPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(readStatusTile302, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile303, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile304, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile305, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile306, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile307, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile308, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(readStatusPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(readStatusTile309, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile310, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile311, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile312, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile313, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile314, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile315, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(readStatusPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(readStatusTile316, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile317, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile318, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile319, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile320, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile321, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile322, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(readStatusPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(readStatusTile323, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile324, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile325, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile326, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile327, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile328, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile329, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(readStatusPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(readStatusTile330, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile331, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile332, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile333, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile334, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile335, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile336, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(readStatusPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(readStatusTile337, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile338, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile339, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile340, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile341, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile342, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile343, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(readStatusPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(readStatusTile344, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile345, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile346, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile347, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile348, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile349, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile350, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(readStatusPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(readStatusTile351, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile352, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile353, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile354, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile355, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile356, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile357, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(readStatusPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(readStatusTile358, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile359, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile360, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile361, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile362, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile363, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile364, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(readStatusPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(readStatusTile365, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile366, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        readStatusPanel3Layout.setVerticalGroup(
            readStatusPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(readStatusPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(readStatusPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, readStatusPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(readStatusPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(readStatusPanel3Layout.createSequentialGroup()
                                .addComponent(readStatusTile351, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(readStatusTile352, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(readStatusTile353, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(readStatusTile354, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(readStatusTile355, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(readStatusTile356, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(readStatusTile357, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(readStatusPanel3Layout.createSequentialGroup()
                                .addGroup(readStatusPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(readStatusPanel3Layout.createSequentialGroup()
                                        .addComponent(readStatusTile365, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(readStatusTile366, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(readStatusPanel3Layout.createSequentialGroup()
                                        .addComponent(readStatusTile358, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(readStatusTile359, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(readStatusTile360, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(readStatusTile361, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(readStatusTile362, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(readStatusTile363, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(readStatusTile364, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(readStatusPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(readStatusPanel3Layout.createSequentialGroup()
                                .addComponent(readStatusTile344, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(readStatusTile345, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(readStatusTile346, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(readStatusTile347, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(readStatusTile348, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(readStatusTile349, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(readStatusTile350, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(readStatusPanel3Layout.createSequentialGroup()
                                .addComponent(readStatusTile337, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(readStatusTile338, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(readStatusTile339, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(readStatusTile340, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(readStatusTile341, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(readStatusTile342, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(readStatusTile343, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(readStatusPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(readStatusPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(readStatusPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(readStatusPanel3Layout.createSequentialGroup()
                                    .addComponent(readStatusTile330, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile331, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile332, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile333, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile334, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile335, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile336, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(readStatusPanel3Layout.createSequentialGroup()
                                    .addComponent(readStatusTile323, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile324, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile325, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile326, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile327, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile328, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile329, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(readStatusPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(readStatusPanel3Layout.createSequentialGroup()
                                    .addComponent(readStatusTile316, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile317, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile318, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile319, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile320, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile321, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile322, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(readStatusPanel3Layout.createSequentialGroup()
                                    .addComponent(readStatusTile309, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile310, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile311, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile312, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile313, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile314, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile315, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(readStatusPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(readStatusPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(readStatusPanel3Layout.createSequentialGroup()
                                    .addComponent(readStatusTile302, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile303, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile304, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile305, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile306, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile307, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile308, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(readStatusPanel3Layout.createSequentialGroup()
                                    .addComponent(readStatusTile295, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile296, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile297, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile298, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile299, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile300, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile301, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(readStatusPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(readStatusPanel3Layout.createSequentialGroup()
                                    .addComponent(readStatusTile288, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile289, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile290, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile291, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile292, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile293, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile294, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(readStatusPanel3Layout.createSequentialGroup()
                                    .addComponent(readStatusTile281, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile282, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile283, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile284, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile285, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile286, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile287, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(readStatusPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(readStatusPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(readStatusPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(readStatusPanel3Layout.createSequentialGroup()
                                    .addComponent(readStatusTile274, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile275, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile276, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile277, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile278, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile279, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile280, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(readStatusPanel3Layout.createSequentialGroup()
                                    .addComponent(readStatusTile267, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile268, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile269, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile270, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile271, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile272, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile273, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(readStatusPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(readStatusPanel3Layout.createSequentialGroup()
                                    .addComponent(readStatusTile260, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile261, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile262, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile263, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile264, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile265, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile266, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(readStatusPanel3Layout.createSequentialGroup()
                                    .addComponent(readStatusTile253, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile254, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile255, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile256, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile257, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile258, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile259, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(readStatusPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(readStatusPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(readStatusPanel3Layout.createSequentialGroup()
                                    .addComponent(readStatusTile246, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile247, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile248, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile249, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile250, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile251, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile252, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(readStatusPanel3Layout.createSequentialGroup()
                                    .addComponent(readStatusTile239, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile240, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile241, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile242, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile243, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile244, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile245, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(readStatusPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(readStatusPanel3Layout.createSequentialGroup()
                                    .addComponent(readStatusTile232, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile233, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile234, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile235, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile236, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile237, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile238, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(readStatusPanel3Layout.createSequentialGroup()
                                    .addComponent(readStatusTile225, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile226, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile227, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile228, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile229, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile230, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile231, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(readStatusPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(readStatusPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(readStatusPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(readStatusPanel3Layout.createSequentialGroup()
                                    .addComponent(readStatusTile218, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile219, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile220, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile221, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile222, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile223, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile224, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(readStatusPanel3Layout.createSequentialGroup()
                                    .addComponent(readStatusTile211, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile212, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile213, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile214, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile215, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile216, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile217, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(readStatusPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(readStatusPanel3Layout.createSequentialGroup()
                                    .addComponent(readStatusTile204, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile205, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile206, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile207, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile208, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile209, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile210, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(readStatusPanel3Layout.createSequentialGroup()
                                    .addComponent(readStatusTile197, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile198, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile199, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile200, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile201, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile202, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile203, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(readStatusPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(readStatusPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(readStatusPanel3Layout.createSequentialGroup()
                                    .addComponent(readStatusTile190, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile191, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile192, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile193, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile194, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile195, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile196, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(readStatusPanel3Layout.createSequentialGroup()
                                    .addComponent(readStatusTile183, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile184, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile185, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile186, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile187, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile188, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile189, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(readStatusPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(readStatusPanel3Layout.createSequentialGroup()
                                    .addComponent(readStatusTile176, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile177, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile178, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile179, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile180, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile181, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile182, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(readStatusPanel3Layout.createSequentialGroup()
                                    .addComponent(readStatusTile169, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile170, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile171, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile172, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile173, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile174, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile175, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(readStatusPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(readStatusPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(readStatusPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(readStatusPanel3Layout.createSequentialGroup()
                                    .addComponent(readStatusTile162, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile163, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile164, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile165, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile166, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile167, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile168, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(readStatusPanel3Layout.createSequentialGroup()
                                    .addComponent(readStatusTile155, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile156, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile157, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile158, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile159, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile160, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile161, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(readStatusPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(readStatusPanel3Layout.createSequentialGroup()
                                    .addComponent(readStatusTile148, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile149, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile150, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile151, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile152, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile153, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile154, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(readStatusPanel3Layout.createSequentialGroup()
                                    .addComponent(readStatusTile141, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile142, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile143, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile144, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile145, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile146, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile147, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(readStatusPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(readStatusPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(readStatusPanel3Layout.createSequentialGroup()
                                    .addComponent(readStatusTile134, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile135, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile136, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile137, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile138, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile139, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile140, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(readStatusPanel3Layout.createSequentialGroup()
                                    .addComponent(readStatusTile127, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile128, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile129, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile130, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile131, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile132, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile133, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(readStatusPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(readStatusPanel3Layout.createSequentialGroup()
                                    .addComponent(readStatusTile120, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile121, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile122, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile123, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile124, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile125, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile126, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(readStatusPanel3Layout.createSequentialGroup()
                                    .addComponent(readStatusTile113, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile114, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile115, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile116, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile117, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile118, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile119, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(readStatusPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(readStatusPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(readStatusPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(readStatusPanel3Layout.createSequentialGroup()
                                    .addComponent(readStatusTile106, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile107, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile108, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile109, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile110, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile111, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile112, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(readStatusPanel3Layout.createSequentialGroup()
                                    .addComponent(readStatusTile99, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile100, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile101, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile102, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile103, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile104, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile105, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(readStatusPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(readStatusPanel3Layout.createSequentialGroup()
                                    .addComponent(readStatusTile92, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile93, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile94, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile95, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile96, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile97, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile98, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(readStatusPanel3Layout.createSequentialGroup()
                                    .addComponent(readStatusTile85, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile86, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile87, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile88, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile89, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile90, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile91, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(readStatusPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(readStatusPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(readStatusPanel3Layout.createSequentialGroup()
                                    .addComponent(readStatusTile78, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile79, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile80, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile81, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile82, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile83, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile84, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(readStatusPanel3Layout.createSequentialGroup()
                                    .addComponent(readStatusTile71, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile72, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile73, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile74, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile75, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile76, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile77, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(readStatusPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(readStatusPanel3Layout.createSequentialGroup()
                                    .addComponent(readStatusTile64, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile65, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile66, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile67, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile68, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile69, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile70, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(readStatusPanel3Layout.createSequentialGroup()
                                    .addComponent(readStatusTile57, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile58, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile59, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile60, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile61, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile62, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile63, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(readStatusPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(readStatusPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(readStatusPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(readStatusPanel3Layout.createSequentialGroup()
                                    .addComponent(readStatusTile50, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile51, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile52, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile53, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile54, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile55, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile56, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(readStatusPanel3Layout.createSequentialGroup()
                                    .addComponent(readStatusTile43, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile44, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile45, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile46, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile47, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile48, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile49, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(readStatusPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(readStatusPanel3Layout.createSequentialGroup()
                                    .addComponent(readStatusTile36, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile37, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile38, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile39, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile40, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile41, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile42, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(readStatusPanel3Layout.createSequentialGroup()
                                    .addComponent(readStatusTile29, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile30, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile31, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile32, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile33, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile34, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile35, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(readStatusPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(readStatusPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(readStatusPanel3Layout.createSequentialGroup()
                                    .addComponent(readStatusTile22, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile23, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile24, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile25, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile26, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile27, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile28, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(readStatusPanel3Layout.createSequentialGroup()
                                    .addComponent(readStatusTile15, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile16, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile17, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile18, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile19, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile20, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile21, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(readStatusPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(readStatusPanel3Layout.createSequentialGroup()
                                    .addComponent(readStatusTile8, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile9, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile10, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile11, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile12, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile13, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile14, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(readStatusPanel3Layout.createSequentialGroup()
                                    .addComponent(readStatusTile1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile5, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile6, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile7, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        bottomBar.add(readStatusPanel3);
        readStatusPanel3.setBounds(40, 10, 1384, 188);

        jLabel21.setFont(new java.awt.Font("Nokia Pure Headline Ultra Light", 0, 14)); // NOI18N
        jLabel21.setText("Wed");
        bottomBar.add(jLabel21);
        jLabel21.setBounds(1430, 90, 25, 20);

        jLabel20.setFont(new java.awt.Font("Nokia Pure Headline Ultra Light", 0, 14)); // NOI18N
        jLabel20.setText("Mon");
        bottomBar.add(jLabel20);
        jLabel20.setBounds(1430, 40, 25, 20);

        jLabel22.setFont(new java.awt.Font("Nokia Pure Headline Ultra Light", 0, 14)); // NOI18N
        jLabel22.setText("Fri");
        bottomBar.add(jLabel22);
        jLabel22.setBounds(1430, 140, 15, 20);

        sep.setFont(new java.awt.Font("Nokia Pure Headline Ultra Light", 0, 14)); // NOI18N
        sep.setText("Sep");
        bottomBar.add(sep);
        sep.setBounds(50, 200, 21, 20);

        oct.setFont(new java.awt.Font("Nokia Pure Headline Ultra Light", 0, 14)); // NOI18N
        oct.setText("Oct");
        bottomBar.add(oct);
        oct.setBounds(150, 200, 21, 20);

        nov.setFont(new java.awt.Font("Nokia Pure Headline Ultra Light", 0, 14)); // NOI18N
        nov.setText("Nov");
        bottomBar.add(nov);
        nov.setBounds(260, 200, 23, 20);

        dec.setFont(new java.awt.Font("Nokia Pure Headline Ultra Light", 0, 14)); // NOI18N
        dec.setText("Dec");
        bottomBar.add(dec);
        dec.setBounds(360, 200, 22, 20);

        jan.setFont(new java.awt.Font("Nokia Pure Headline Ultra Light", 0, 14)); // NOI18N
        jan.setText("Jan");
        bottomBar.add(jan);
        jan.setBounds(460, 200, 20, 20);

        feb.setFont(new java.awt.Font("Nokia Pure Headline Ultra Light", 0, 14)); // NOI18N
        feb.setText("Feb");
        bottomBar.add(feb);
        feb.setBounds(570, 200, 21, 20);

        mar.setFont(new java.awt.Font("Nokia Pure Headline Ultra Light", 0, 14)); // NOI18N
        mar.setText("Mar");
        bottomBar.add(mar);
        mar.setBounds(670, 200, 22, 20);

        apr.setFont(new java.awt.Font("Nokia Pure Headline Ultra Light", 0, 14)); // NOI18N
        apr.setText("Apr");
        bottomBar.add(apr);
        apr.setBounds(780, 200, 20, 20);

        may.setFont(new java.awt.Font("Nokia Pure Headline Ultra Light", 0, 14)); // NOI18N
        may.setText("May");
        bottomBar.add(may);
        may.setBounds(880, 200, 23, 20);

        jun.setFont(new java.awt.Font("Nokia Pure Headline Ultra Light", 0, 14)); // NOI18N
        jun.setText("Jun");
        bottomBar.add(jun);
        jun.setBounds(980, 200, 20, 20);

        jul.setFont(new java.awt.Font("Nokia Pure Headline Ultra Light", 0, 14)); // NOI18N
        jul.setText("Jul");
        bottomBar.add(jul);
        jul.setBounds(1090, 200, 16, 20);

        aug.setFont(new java.awt.Font("Nokia Pure Headline Ultra Light", 0, 14)); // NOI18N
        aug.setText("Aug");
        bottomBar.add(aug);
        aug.setBounds(1190, 200, 22, 20);

        sep2.setFont(new java.awt.Font("Nokia Pure Headline Ultra Light", 0, 14)); // NOI18N
        sep2.setText("Sep");
        bottomBar.add(sep2);
        sep2.setBounds(1290, 200, 21, 20);

        javax.swing.GroupLayout topSpacerLayout = new javax.swing.GroupLayout(topSpacer);
        topSpacer.setLayout(topSpacerLayout);
        topSpacerLayout.setHorizontalGroup(
            topSpacerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        topSpacerLayout.setVerticalGroup(
            topSpacerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 34, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout libraryTabLayout = new javax.swing.GroupLayout(libraryTab);
        libraryTab.setLayout(libraryTabLayout);
        libraryTabLayout.setHorizontalGroup(
            libraryTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(libraryTabLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(topSpacer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, libraryTabLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(libraryTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bottomBar, javax.swing.GroupLayout.PREFERRED_SIZE, 1554, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(upperBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        libraryTabLayout.setVerticalGroup(
            libraryTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(libraryTabLayout.createSequentialGroup()
                .addGap(95, 95, 95)
                .addComponent(topSpacer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(upperBar, javax.swing.GroupLayout.PREFERRED_SIZE, 455, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(bottomBar, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(122, Short.MAX_VALUE))
        );

        tabs.addTab("Boomarks", libraryTab);

        pdfReader.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout inLibraryTabsLayout = new javax.swing.GroupLayout(inLibraryTabs);
        inLibraryTabs.setLayout(inLibraryTabsLayout);
        inLibraryTabsLayout.setHorizontalGroup(
            inLibraryTabsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(inLibraryTabsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pdfReader, javax.swing.GroupLayout.DEFAULT_SIZE, 1568, Short.MAX_VALUE)
                .addContainerGap())
        );
        inLibraryTabsLayout.setVerticalGroup(
            inLibraryTabsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(inLibraryTabsLayout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addComponent(pdfReader, javax.swing.GroupLayout.DEFAULT_SIZE, 912, Short.MAX_VALUE)
                .addContainerGap())
        );

        tabs.addTab("Search", inLibraryTabs);

        book5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bookTiles/1V1S9h1Ey1zGTa-QIn83.jpeg"))); // NOI18N
        book5.setBorder(null);
        book5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                book5ActionPerformed(evt);
            }
        });

        bookDescriptionTileOne8.setBackground(new java.awt.Color(204, 204, 204));

        jLabel11.setFont(new java.awt.Font("Nokia Pure Headline Ultra Light", 1, 18)); // NOI18N
        jLabel11.setText("Desiring GOD ");

        jLabel23.setFont(new java.awt.Font("Nokia Pure Headline Ultra Light", 0, 14)); // NOI18N
        jLabel23.setText("John Piper");

        javax.swing.GroupLayout bookDescriptionTileOne8Layout = new javax.swing.GroupLayout(bookDescriptionTileOne8);
        bookDescriptionTileOne8.setLayout(bookDescriptionTileOne8Layout);
        bookDescriptionTileOne8Layout.setHorizontalGroup(
            bookDescriptionTileOne8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bookDescriptionTileOne8Layout.createSequentialGroup()
                .addGap(104, 104, 104)
                .addGroup(bookDescriptionTileOne8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bookDescriptionTileOne8Layout.createSequentialGroup()
                        .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        bookDescriptionTileOne8Layout.setVerticalGroup(
            bookDescriptionTileOne8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bookDescriptionTileOne8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel23)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new java.awt.GridBagLayout());

        book6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bookTiles/1V1S9h1Ey1zGTa-QIn83.jpeg"))); // NOI18N
        book6.setBorder(null);
        book6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                book6ActionPerformed(evt);
            }
        });
        jPanel3.add(book6, new java.awt.GridBagConstraints());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 53, Short.MAX_VALUE)
        );

        jLayeredPane1.setBackground(new java.awt.Color(255, 255, 255));

        layeredStitcher.setBackground(new java.awt.Color(255, 255, 255));

        bookTileImage.setBackground(new java.awt.Color(255, 255, 255));
        bookTileImage.setLayout(new java.awt.GridBagLayout());

        bookTileClickable.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bookTiles/1V1S9h1Ey1zGTa-QIn83.jpeg"))); // NOI18N
        bookTileClickable.setBorder(null);
        bookTileClickable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bookTileClickableActionPerformed(evt);
            }
        });
        bookTileImage.add(bookTileClickable, new java.awt.GridBagConstraints());

        bookNameDisplay.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout bookNameDisplayLayout = new javax.swing.GroupLayout(bookNameDisplay);
        bookNameDisplay.setLayout(bookNameDisplayLayout);
        bookNameDisplayLayout.setHorizontalGroup(
            bookNameDisplayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 366, Short.MAX_VALUE)
        );
        bookNameDisplayLayout.setVerticalGroup(
            bookNameDisplayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 53, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layeredStitcherLayout = new javax.swing.GroupLayout(layeredStitcher);
        layeredStitcher.setLayout(layeredStitcherLayout);
        layeredStitcherLayout.setHorizontalGroup(
            layeredStitcherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layeredStitcherLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layeredStitcherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(bookTileImage, javax.swing.GroupLayout.DEFAULT_SIZE, 366, Short.MAX_VALUE)
                    .addComponent(bookNameDisplay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layeredStitcherLayout.setVerticalGroup(
            layeredStitcherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layeredStitcherLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(bookTileImage, javax.swing.GroupLayout.PREFERRED_SIZE, 483, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bookNameDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLayeredPane1.setLayer(layeredStitcher, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(layeredStitcher, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(layeredStitcher, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 366, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 213, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(bookDescriptionTileOne8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(book5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(197, 197, 197))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(book5, javax.swing.GroupLayout.PREFERRED_SIZE, 449, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(bookDescriptionTileOne8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 483, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(94, 94, 94))
        );

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 601, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(313, Short.MAX_VALUE))
        );

        tabs.addTab("Notes", jPanel10);

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

    private void moreBtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_moreBtn1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_moreBtn1ActionPerformed

    private void book1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_book1ActionPerformed
        tabs.setSelectedIndex(2);
    }//GEN-LAST:event_book1ActionPerformed

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

    private void book5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_book5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_book5ActionPerformed

    private void book6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_book6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_book6ActionPerformed

    private void bookTileClickableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bookTileClickableActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bookTileClickableActionPerformed

    
 
    
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
    private javax.swing.JButton book1;
    private javax.swing.JButton book2;
    private javax.swing.JButton book3;
    private javax.swing.JButton book4;
    private javax.swing.JButton book5;
    private javax.swing.JButton book6;
    private javax.swing.JComboBox<String> bookChooser;
    private javax.swing.JComboBox<String> bookChooserDropDown;
    private javax.swing.JPanel bookDescriptionTileOne8;
    private javax.swing.JPanel bookNameDisplay;
    private javax.swing.JButton bookTileClickable;
    private javax.swing.JPanel bookTileImage;
    private javax.swing.JButton bookmarkBtn;
    private javax.swing.JLabel bookmarksBtn;
    private javax.swing.JPanel bottomBar;
    private javax.swing.JComboBox<String> chapterChooser;
    private javax.swing.JButton closeBtn;
    private javax.swing.JButton cmntrsBtn;
    private javax.swing.JLabel cmntrsBtnLabel;
    private javax.swing.JPanel cmtrsTabPanel;
    private javax.swing.JTabbedPane cmtryTabbedPanel;
    private javax.swing.JLabel dec;
    private javax.swing.JButton eyeHide;
    private javax.swing.JButton eyeShow;
    private javax.swing.JLabel feb;
    private javax.swing.JSlider fontSizeSlider;
    private javax.swing.JButton highlightBtn;
    private javax.swing.JButton homeBtn;
    private javax.swing.JLabel homeBtnLabel;
    private javax.swing.JButton hostJoinBtn;
    private javax.swing.JLabel hostJoinBtnLabel;
    private javax.swing.JPanel inLibraryTabs;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel jan;
    private javax.swing.JButton journalBtn;
    private javax.swing.JLabel jul;
    private javax.swing.JLabel jun;
    private javax.swing.JPanel layeredStitcher;
    private javax.swing.JButton libraryBtn;
    private javax.swing.JPanel libraryTab;
    private javax.swing.JLayeredPane mainPanel_layered;
    private javax.swing.JTextArea mainTextArea;
    private javax.swing.JScrollPane mainTextScrollPanel;
    private javax.swing.JLabel mar;
    private javax.swing.JLabel may;
    private javax.swing.JButton minimizeBtn;
    private javax.swing.JButton moreBtn1;
    private javax.swing.JPanel navBar;
    private javax.swing.JPanel nodesTabPanel;
    private javax.swing.JLabel notesBtnLabel;
    private javax.swing.JTextArea notesInput;
    private javax.swing.JLayeredPane notesTabLayers;
    private javax.swing.JPanel notesTabPanel;
    private javax.swing.JLabel nov;
    private javax.swing.JLabel oct;
    private javax.swing.JScrollPane pdfReader;
    private javax.swing.JPanel readStatusPanel3;
    private javax.swing.JButton readStatusTile1;
    private javax.swing.JButton readStatusTile10;
    private javax.swing.JButton readStatusTile100;
    private javax.swing.JButton readStatusTile101;
    private javax.swing.JButton readStatusTile102;
    private javax.swing.JButton readStatusTile103;
    private javax.swing.JButton readStatusTile104;
    private javax.swing.JButton readStatusTile105;
    private javax.swing.JButton readStatusTile106;
    private javax.swing.JButton readStatusTile107;
    private javax.swing.JButton readStatusTile108;
    private javax.swing.JButton readStatusTile109;
    private javax.swing.JButton readStatusTile11;
    private javax.swing.JButton readStatusTile110;
    private javax.swing.JButton readStatusTile111;
    private javax.swing.JButton readStatusTile112;
    private javax.swing.JButton readStatusTile113;
    private javax.swing.JButton readStatusTile114;
    private javax.swing.JButton readStatusTile115;
    private javax.swing.JButton readStatusTile116;
    private javax.swing.JButton readStatusTile117;
    private javax.swing.JButton readStatusTile118;
    private javax.swing.JButton readStatusTile119;
    private javax.swing.JButton readStatusTile12;
    private javax.swing.JButton readStatusTile120;
    private javax.swing.JButton readStatusTile121;
    private javax.swing.JButton readStatusTile122;
    private javax.swing.JButton readStatusTile123;
    private javax.swing.JButton readStatusTile124;
    private javax.swing.JButton readStatusTile125;
    private javax.swing.JButton readStatusTile126;
    private javax.swing.JButton readStatusTile127;
    private javax.swing.JButton readStatusTile128;
    private javax.swing.JButton readStatusTile129;
    private javax.swing.JButton readStatusTile13;
    private javax.swing.JButton readStatusTile130;
    private javax.swing.JButton readStatusTile131;
    private javax.swing.JButton readStatusTile132;
    private javax.swing.JButton readStatusTile133;
    private javax.swing.JButton readStatusTile134;
    private javax.swing.JButton readStatusTile135;
    private javax.swing.JButton readStatusTile136;
    private javax.swing.JButton readStatusTile137;
    private javax.swing.JButton readStatusTile138;
    private javax.swing.JButton readStatusTile139;
    private javax.swing.JButton readStatusTile14;
    private javax.swing.JButton readStatusTile140;
    private javax.swing.JButton readStatusTile141;
    private javax.swing.JButton readStatusTile142;
    private javax.swing.JButton readStatusTile143;
    private javax.swing.JButton readStatusTile144;
    private javax.swing.JButton readStatusTile145;
    private javax.swing.JButton readStatusTile146;
    private javax.swing.JButton readStatusTile147;
    private javax.swing.JButton readStatusTile148;
    private javax.swing.JButton readStatusTile149;
    private javax.swing.JButton readStatusTile15;
    private javax.swing.JButton readStatusTile150;
    private javax.swing.JButton readStatusTile151;
    private javax.swing.JButton readStatusTile152;
    private javax.swing.JButton readStatusTile153;
    private javax.swing.JButton readStatusTile154;
    private javax.swing.JButton readStatusTile155;
    private javax.swing.JButton readStatusTile156;
    private javax.swing.JButton readStatusTile157;
    private javax.swing.JButton readStatusTile158;
    private javax.swing.JButton readStatusTile159;
    private javax.swing.JButton readStatusTile16;
    private javax.swing.JButton readStatusTile160;
    private javax.swing.JButton readStatusTile161;
    private javax.swing.JButton readStatusTile162;
    private javax.swing.JButton readStatusTile163;
    private javax.swing.JButton readStatusTile164;
    private javax.swing.JButton readStatusTile165;
    private javax.swing.JButton readStatusTile166;
    private javax.swing.JButton readStatusTile167;
    private javax.swing.JButton readStatusTile168;
    private javax.swing.JButton readStatusTile169;
    private javax.swing.JButton readStatusTile17;
    private javax.swing.JButton readStatusTile170;
    private javax.swing.JButton readStatusTile171;
    private javax.swing.JButton readStatusTile172;
    private javax.swing.JButton readStatusTile173;
    private javax.swing.JButton readStatusTile174;
    private javax.swing.JButton readStatusTile175;
    private javax.swing.JButton readStatusTile176;
    private javax.swing.JButton readStatusTile177;
    private javax.swing.JButton readStatusTile178;
    private javax.swing.JButton readStatusTile179;
    private javax.swing.JButton readStatusTile18;
    private javax.swing.JButton readStatusTile180;
    private javax.swing.JButton readStatusTile181;
    private javax.swing.JButton readStatusTile182;
    private javax.swing.JButton readStatusTile183;
    private javax.swing.JButton readStatusTile184;
    private javax.swing.JButton readStatusTile185;
    private javax.swing.JButton readStatusTile186;
    private javax.swing.JButton readStatusTile187;
    private javax.swing.JButton readStatusTile188;
    private javax.swing.JButton readStatusTile189;
    private javax.swing.JButton readStatusTile19;
    private javax.swing.JButton readStatusTile190;
    private javax.swing.JButton readStatusTile191;
    private javax.swing.JButton readStatusTile192;
    private javax.swing.JButton readStatusTile193;
    private javax.swing.JButton readStatusTile194;
    private javax.swing.JButton readStatusTile195;
    private javax.swing.JButton readStatusTile196;
    private javax.swing.JButton readStatusTile197;
    private javax.swing.JButton readStatusTile198;
    private javax.swing.JButton readStatusTile199;
    private javax.swing.JButton readStatusTile2;
    private javax.swing.JButton readStatusTile20;
    private javax.swing.JButton readStatusTile200;
    private javax.swing.JButton readStatusTile201;
    private javax.swing.JButton readStatusTile202;
    private javax.swing.JButton readStatusTile203;
    private javax.swing.JButton readStatusTile204;
    private javax.swing.JButton readStatusTile205;
    private javax.swing.JButton readStatusTile206;
    private javax.swing.JButton readStatusTile207;
    private javax.swing.JButton readStatusTile208;
    private javax.swing.JButton readStatusTile209;
    private javax.swing.JButton readStatusTile21;
    private javax.swing.JButton readStatusTile210;
    private javax.swing.JButton readStatusTile211;
    private javax.swing.JButton readStatusTile212;
    private javax.swing.JButton readStatusTile213;
    private javax.swing.JButton readStatusTile214;
    private javax.swing.JButton readStatusTile215;
    private javax.swing.JButton readStatusTile216;
    private javax.swing.JButton readStatusTile217;
    private javax.swing.JButton readStatusTile218;
    private javax.swing.JButton readStatusTile219;
    private javax.swing.JButton readStatusTile22;
    private javax.swing.JButton readStatusTile220;
    private javax.swing.JButton readStatusTile221;
    private javax.swing.JButton readStatusTile222;
    private javax.swing.JButton readStatusTile223;
    private javax.swing.JButton readStatusTile224;
    private javax.swing.JButton readStatusTile225;
    private javax.swing.JButton readStatusTile226;
    private javax.swing.JButton readStatusTile227;
    private javax.swing.JButton readStatusTile228;
    private javax.swing.JButton readStatusTile229;
    private javax.swing.JButton readStatusTile23;
    private javax.swing.JButton readStatusTile230;
    private javax.swing.JButton readStatusTile231;
    private javax.swing.JButton readStatusTile232;
    private javax.swing.JButton readStatusTile233;
    private javax.swing.JButton readStatusTile234;
    private javax.swing.JButton readStatusTile235;
    private javax.swing.JButton readStatusTile236;
    private javax.swing.JButton readStatusTile237;
    private javax.swing.JButton readStatusTile238;
    private javax.swing.JButton readStatusTile239;
    private javax.swing.JButton readStatusTile24;
    private javax.swing.JButton readStatusTile240;
    private javax.swing.JButton readStatusTile241;
    private javax.swing.JButton readStatusTile242;
    private javax.swing.JButton readStatusTile243;
    private javax.swing.JButton readStatusTile244;
    private javax.swing.JButton readStatusTile245;
    private javax.swing.JButton readStatusTile246;
    private javax.swing.JButton readStatusTile247;
    private javax.swing.JButton readStatusTile248;
    private javax.swing.JButton readStatusTile249;
    private javax.swing.JButton readStatusTile25;
    private javax.swing.JButton readStatusTile250;
    private javax.swing.JButton readStatusTile251;
    private javax.swing.JButton readStatusTile252;
    private javax.swing.JButton readStatusTile253;
    private javax.swing.JButton readStatusTile254;
    private javax.swing.JButton readStatusTile255;
    private javax.swing.JButton readStatusTile256;
    private javax.swing.JButton readStatusTile257;
    private javax.swing.JButton readStatusTile258;
    private javax.swing.JButton readStatusTile259;
    private javax.swing.JButton readStatusTile26;
    private javax.swing.JButton readStatusTile260;
    private javax.swing.JButton readStatusTile261;
    private javax.swing.JButton readStatusTile262;
    private javax.swing.JButton readStatusTile263;
    private javax.swing.JButton readStatusTile264;
    private javax.swing.JButton readStatusTile265;
    private javax.swing.JButton readStatusTile266;
    private javax.swing.JButton readStatusTile267;
    private javax.swing.JButton readStatusTile268;
    private javax.swing.JButton readStatusTile269;
    private javax.swing.JButton readStatusTile27;
    private javax.swing.JButton readStatusTile270;
    private javax.swing.JButton readStatusTile271;
    private javax.swing.JButton readStatusTile272;
    private javax.swing.JButton readStatusTile273;
    private javax.swing.JButton readStatusTile274;
    private javax.swing.JButton readStatusTile275;
    private javax.swing.JButton readStatusTile276;
    private javax.swing.JButton readStatusTile277;
    private javax.swing.JButton readStatusTile278;
    private javax.swing.JButton readStatusTile279;
    private javax.swing.JButton readStatusTile28;
    private javax.swing.JButton readStatusTile280;
    private javax.swing.JButton readStatusTile281;
    private javax.swing.JButton readStatusTile282;
    private javax.swing.JButton readStatusTile283;
    private javax.swing.JButton readStatusTile284;
    private javax.swing.JButton readStatusTile285;
    private javax.swing.JButton readStatusTile286;
    private javax.swing.JButton readStatusTile287;
    private javax.swing.JButton readStatusTile288;
    private javax.swing.JButton readStatusTile289;
    private javax.swing.JButton readStatusTile29;
    private javax.swing.JButton readStatusTile290;
    private javax.swing.JButton readStatusTile291;
    private javax.swing.JButton readStatusTile292;
    private javax.swing.JButton readStatusTile293;
    private javax.swing.JButton readStatusTile294;
    private javax.swing.JButton readStatusTile295;
    private javax.swing.JButton readStatusTile296;
    private javax.swing.JButton readStatusTile297;
    private javax.swing.JButton readStatusTile298;
    private javax.swing.JButton readStatusTile299;
    private javax.swing.JButton readStatusTile3;
    private javax.swing.JButton readStatusTile30;
    private javax.swing.JButton readStatusTile300;
    private javax.swing.JButton readStatusTile301;
    private javax.swing.JButton readStatusTile302;
    private javax.swing.JButton readStatusTile303;
    private javax.swing.JButton readStatusTile304;
    private javax.swing.JButton readStatusTile305;
    private javax.swing.JButton readStatusTile306;
    private javax.swing.JButton readStatusTile307;
    private javax.swing.JButton readStatusTile308;
    private javax.swing.JButton readStatusTile309;
    private javax.swing.JButton readStatusTile31;
    private javax.swing.JButton readStatusTile310;
    private javax.swing.JButton readStatusTile311;
    private javax.swing.JButton readStatusTile312;
    private javax.swing.JButton readStatusTile313;
    private javax.swing.JButton readStatusTile314;
    private javax.swing.JButton readStatusTile315;
    private javax.swing.JButton readStatusTile316;
    private javax.swing.JButton readStatusTile317;
    private javax.swing.JButton readStatusTile318;
    private javax.swing.JButton readStatusTile319;
    private javax.swing.JButton readStatusTile32;
    private javax.swing.JButton readStatusTile320;
    private javax.swing.JButton readStatusTile321;
    private javax.swing.JButton readStatusTile322;
    private javax.swing.JButton readStatusTile323;
    private javax.swing.JButton readStatusTile324;
    private javax.swing.JButton readStatusTile325;
    private javax.swing.JButton readStatusTile326;
    private javax.swing.JButton readStatusTile327;
    private javax.swing.JButton readStatusTile328;
    private javax.swing.JButton readStatusTile329;
    private javax.swing.JButton readStatusTile33;
    private javax.swing.JButton readStatusTile330;
    private javax.swing.JButton readStatusTile331;
    private javax.swing.JButton readStatusTile332;
    private javax.swing.JButton readStatusTile333;
    private javax.swing.JButton readStatusTile334;
    private javax.swing.JButton readStatusTile335;
    private javax.swing.JButton readStatusTile336;
    private javax.swing.JButton readStatusTile337;
    private javax.swing.JButton readStatusTile338;
    private javax.swing.JButton readStatusTile339;
    private javax.swing.JButton readStatusTile34;
    private javax.swing.JButton readStatusTile340;
    private javax.swing.JButton readStatusTile341;
    private javax.swing.JButton readStatusTile342;
    private javax.swing.JButton readStatusTile343;
    private javax.swing.JButton readStatusTile344;
    private javax.swing.JButton readStatusTile345;
    private javax.swing.JButton readStatusTile346;
    private javax.swing.JButton readStatusTile347;
    private javax.swing.JButton readStatusTile348;
    private javax.swing.JButton readStatusTile349;
    private javax.swing.JButton readStatusTile35;
    private javax.swing.JButton readStatusTile350;
    private javax.swing.JButton readStatusTile351;
    private javax.swing.JButton readStatusTile352;
    private javax.swing.JButton readStatusTile353;
    private javax.swing.JButton readStatusTile354;
    private javax.swing.JButton readStatusTile355;
    private javax.swing.JButton readStatusTile356;
    private javax.swing.JButton readStatusTile357;
    private javax.swing.JButton readStatusTile358;
    private javax.swing.JButton readStatusTile359;
    private javax.swing.JButton readStatusTile36;
    private javax.swing.JButton readStatusTile360;
    private javax.swing.JButton readStatusTile361;
    private javax.swing.JButton readStatusTile362;
    private javax.swing.JButton readStatusTile363;
    private javax.swing.JButton readStatusTile364;
    private javax.swing.JButton readStatusTile365;
    private javax.swing.JButton readStatusTile366;
    private javax.swing.JButton readStatusTile37;
    private javax.swing.JButton readStatusTile38;
    private javax.swing.JButton readStatusTile39;
    private javax.swing.JButton readStatusTile4;
    private javax.swing.JButton readStatusTile40;
    private javax.swing.JButton readStatusTile41;
    private javax.swing.JButton readStatusTile42;
    private javax.swing.JButton readStatusTile43;
    private javax.swing.JButton readStatusTile44;
    private javax.swing.JButton readStatusTile45;
    private javax.swing.JButton readStatusTile46;
    private javax.swing.JButton readStatusTile47;
    private javax.swing.JButton readStatusTile48;
    private javax.swing.JButton readStatusTile49;
    private javax.swing.JButton readStatusTile5;
    private javax.swing.JButton readStatusTile50;
    private javax.swing.JButton readStatusTile51;
    private javax.swing.JButton readStatusTile52;
    private javax.swing.JButton readStatusTile53;
    private javax.swing.JButton readStatusTile54;
    private javax.swing.JButton readStatusTile55;
    private javax.swing.JButton readStatusTile56;
    private javax.swing.JButton readStatusTile57;
    private javax.swing.JButton readStatusTile58;
    private javax.swing.JButton readStatusTile59;
    private javax.swing.JButton readStatusTile6;
    private javax.swing.JButton readStatusTile60;
    private javax.swing.JButton readStatusTile61;
    private javax.swing.JButton readStatusTile62;
    private javax.swing.JButton readStatusTile63;
    private javax.swing.JButton readStatusTile64;
    private javax.swing.JButton readStatusTile65;
    private javax.swing.JButton readStatusTile66;
    private javax.swing.JButton readStatusTile67;
    private javax.swing.JButton readStatusTile68;
    private javax.swing.JButton readStatusTile69;
    private javax.swing.JButton readStatusTile7;
    private javax.swing.JButton readStatusTile70;
    private javax.swing.JButton readStatusTile71;
    private javax.swing.JButton readStatusTile72;
    private javax.swing.JButton readStatusTile73;
    private javax.swing.JButton readStatusTile74;
    private javax.swing.JButton readStatusTile75;
    private javax.swing.JButton readStatusTile76;
    private javax.swing.JButton readStatusTile77;
    private javax.swing.JButton readStatusTile78;
    private javax.swing.JButton readStatusTile79;
    private javax.swing.JButton readStatusTile8;
    private javax.swing.JButton readStatusTile80;
    private javax.swing.JButton readStatusTile81;
    private javax.swing.JButton readStatusTile82;
    private javax.swing.JButton readStatusTile83;
    private javax.swing.JButton readStatusTile84;
    private javax.swing.JButton readStatusTile85;
    private javax.swing.JButton readStatusTile86;
    private javax.swing.JButton readStatusTile87;
    private javax.swing.JButton readStatusTile88;
    private javax.swing.JButton readStatusTile89;
    private javax.swing.JButton readStatusTile9;
    private javax.swing.JButton readStatusTile90;
    private javax.swing.JButton readStatusTile91;
    private javax.swing.JButton readStatusTile92;
    private javax.swing.JButton readStatusTile93;
    private javax.swing.JButton readStatusTile94;
    private javax.swing.JButton readStatusTile95;
    private javax.swing.JButton readStatusTile96;
    private javax.swing.JButton readStatusTile97;
    private javax.swing.JButton readStatusTile98;
    private javax.swing.JButton readStatusTile99;
    private javax.swing.JButton restoreBtn;
    private javax.swing.JButton saveNote;
    private javax.swing.JButton saveTick;
    private javax.swing.JButton searchBtn;
    private javax.swing.JLabel searchBtnLabel;
    private javax.swing.JLabel sep;
    private javax.swing.JLabel sep2;
    private javax.swing.JButton settingsBtn;
    private javax.swing.JLabel settingsLabel;
    private javax.swing.JTabbedPane tabs;
    private javax.swing.JComboBox<String> testamentChooser;
    private javax.swing.JLabel tileOne4;
    private javax.swing.JPanel topBar;
    private javax.swing.JPanel topSpacer;
    private javax.swing.JPanel upperBar;
    private javax.swing.JComboBox<String> verseChooser;
    // End of variables declaration//GEN-END:variables
}