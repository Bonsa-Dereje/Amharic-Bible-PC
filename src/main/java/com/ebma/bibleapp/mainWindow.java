

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
        bookTile5 = new javax.swing.JButton();
        bookDescriptionTileOne4 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        bookTile6 = new javax.swing.JButton();
        bookDescriptionTileOne5 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        bookTile7 = new javax.swing.JButton();
        bookDescriptionTileOne6 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        bookTile8 = new javax.swing.JButton();
        bookDescriptionTileOne7 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        moreBtn1 = new javax.swing.JButton();
        bottomBar = new javax.swing.JPanel();
        readStatusPanel3 = new javax.swing.JPanel();
        readStatusTile1098 = new javax.swing.JButton();
        readStatusTile1099 = new javax.swing.JButton();
        readStatusTile1100 = new javax.swing.JButton();
        readStatusTile1101 = new javax.swing.JButton();
        readStatusTile1102 = new javax.swing.JButton();
        readStatusTile1103 = new javax.swing.JButton();
        readStatusTile1104 = new javax.swing.JButton();
        readStatusTile1105 = new javax.swing.JButton();
        readStatusTile1106 = new javax.swing.JButton();
        readStatusTile1107 = new javax.swing.JButton();
        readStatusTile1108 = new javax.swing.JButton();
        readStatusTile1109 = new javax.swing.JButton();
        readStatusTile1110 = new javax.swing.JButton();
        readStatusTile1111 = new javax.swing.JButton();
        readStatusTile1112 = new javax.swing.JButton();
        readStatusTile1113 = new javax.swing.JButton();
        readStatusTile1114 = new javax.swing.JButton();
        readStatusTile1115 = new javax.swing.JButton();
        readStatusTile1116 = new javax.swing.JButton();
        readStatusTile1117 = new javax.swing.JButton();
        readStatusTile1118 = new javax.swing.JButton();
        readStatusTile1119 = new javax.swing.JButton();
        readStatusTile1120 = new javax.swing.JButton();
        readStatusTile1121 = new javax.swing.JButton();
        readStatusTile1122 = new javax.swing.JButton();
        readStatusTile1123 = new javax.swing.JButton();
        readStatusTile1124 = new javax.swing.JButton();
        readStatusTile1125 = new javax.swing.JButton();
        readStatusTile1126 = new javax.swing.JButton();
        readStatusTile1127 = new javax.swing.JButton();
        readStatusTile1128 = new javax.swing.JButton();
        readStatusTile1129 = new javax.swing.JButton();
        readStatusTile1130 = new javax.swing.JButton();
        readStatusTile1131 = new javax.swing.JButton();
        readStatusTile1132 = new javax.swing.JButton();
        readStatusTile1133 = new javax.swing.JButton();
        readStatusTile1134 = new javax.swing.JButton();
        readStatusTile1135 = new javax.swing.JButton();
        readStatusTile1136 = new javax.swing.JButton();
        readStatusTile1137 = new javax.swing.JButton();
        readStatusTile1138 = new javax.swing.JButton();
        readStatusTile1139 = new javax.swing.JButton();
        readStatusTile1140 = new javax.swing.JButton();
        readStatusTile1141 = new javax.swing.JButton();
        readStatusTile1142 = new javax.swing.JButton();
        readStatusTile1143 = new javax.swing.JButton();
        readStatusTile1144 = new javax.swing.JButton();
        readStatusTile1145 = new javax.swing.JButton();
        readStatusTile1146 = new javax.swing.JButton();
        readStatusTile1147 = new javax.swing.JButton();
        readStatusTile1148 = new javax.swing.JButton();
        readStatusTile1149 = new javax.swing.JButton();
        readStatusTile1150 = new javax.swing.JButton();
        readStatusTile1151 = new javax.swing.JButton();
        readStatusTile1152 = new javax.swing.JButton();
        readStatusTile1153 = new javax.swing.JButton();
        readStatusTile1154 = new javax.swing.JButton();
        readStatusTile1155 = new javax.swing.JButton();
        readStatusTile1156 = new javax.swing.JButton();
        readStatusTile1157 = new javax.swing.JButton();
        readStatusTile1158 = new javax.swing.JButton();
        readStatusTile1159 = new javax.swing.JButton();
        readStatusTile1160 = new javax.swing.JButton();
        readStatusTile1161 = new javax.swing.JButton();
        readStatusTile1162 = new javax.swing.JButton();
        readStatusTile1163 = new javax.swing.JButton();
        readStatusTile1164 = new javax.swing.JButton();
        readStatusTile1165 = new javax.swing.JButton();
        readStatusTile1166 = new javax.swing.JButton();
        readStatusTile1167 = new javax.swing.JButton();
        readStatusTile1168 = new javax.swing.JButton();
        readStatusTile1169 = new javax.swing.JButton();
        readStatusTile1170 = new javax.swing.JButton();
        readStatusTile1171 = new javax.swing.JButton();
        readStatusTile1172 = new javax.swing.JButton();
        readStatusTile1173 = new javax.swing.JButton();
        readStatusTile1174 = new javax.swing.JButton();
        readStatusTile1175 = new javax.swing.JButton();
        readStatusTile1176 = new javax.swing.JButton();
        readStatusTile1177 = new javax.swing.JButton();
        readStatusTile1178 = new javax.swing.JButton();
        readStatusTile1179 = new javax.swing.JButton();
        readStatusTile1180 = new javax.swing.JButton();
        readStatusTile1181 = new javax.swing.JButton();
        readStatusTile1182 = new javax.swing.JButton();
        readStatusTile1183 = new javax.swing.JButton();
        readStatusTile1184 = new javax.swing.JButton();
        readStatusTile1185 = new javax.swing.JButton();
        readStatusTile1186 = new javax.swing.JButton();
        readStatusTile1187 = new javax.swing.JButton();
        readStatusTile1188 = new javax.swing.JButton();
        readStatusTile1189 = new javax.swing.JButton();
        readStatusTile1190 = new javax.swing.JButton();
        readStatusTile1191 = new javax.swing.JButton();
        readStatusTile1192 = new javax.swing.JButton();
        readStatusTile1193 = new javax.swing.JButton();
        readStatusTile1194 = new javax.swing.JButton();
        readStatusTile1195 = new javax.swing.JButton();
        readStatusTile1196 = new javax.swing.JButton();
        readStatusTile1197 = new javax.swing.JButton();
        readStatusTile1198 = new javax.swing.JButton();
        readStatusTile1199 = new javax.swing.JButton();
        readStatusTile1200 = new javax.swing.JButton();
        readStatusTile1201 = new javax.swing.JButton();
        readStatusTile1202 = new javax.swing.JButton();
        readStatusTile1203 = new javax.swing.JButton();
        readStatusTile1204 = new javax.swing.JButton();
        readStatusTile1205 = new javax.swing.JButton();
        readStatusTile1206 = new javax.swing.JButton();
        readStatusTile1207 = new javax.swing.JButton();
        readStatusTile1208 = new javax.swing.JButton();
        readStatusTile1209 = new javax.swing.JButton();
        readStatusTile1210 = new javax.swing.JButton();
        readStatusTile1211 = new javax.swing.JButton();
        readStatusTile1212 = new javax.swing.JButton();
        readStatusTile1213 = new javax.swing.JButton();
        readStatusTile1214 = new javax.swing.JButton();
        readStatusTile1215 = new javax.swing.JButton();
        readStatusTile1216 = new javax.swing.JButton();
        readStatusTile1217 = new javax.swing.JButton();
        readStatusTile1218 = new javax.swing.JButton();
        readStatusTile1219 = new javax.swing.JButton();
        readStatusTile1220 = new javax.swing.JButton();
        readStatusTile1221 = new javax.swing.JButton();
        readStatusTile1222 = new javax.swing.JButton();
        readStatusTile1223 = new javax.swing.JButton();
        readStatusTile1224 = new javax.swing.JButton();
        readStatusTile1225 = new javax.swing.JButton();
        readStatusTile1226 = new javax.swing.JButton();
        readStatusTile1227 = new javax.swing.JButton();
        readStatusTile1228 = new javax.swing.JButton();
        readStatusTile1229 = new javax.swing.JButton();
        readStatusTile1230 = new javax.swing.JButton();
        readStatusTile1231 = new javax.swing.JButton();
        readStatusTile1232 = new javax.swing.JButton();
        readStatusTile1233 = new javax.swing.JButton();
        readStatusTile1234 = new javax.swing.JButton();
        readStatusTile1235 = new javax.swing.JButton();
        readStatusTile1236 = new javax.swing.JButton();
        readStatusTile1237 = new javax.swing.JButton();
        readStatusTile1238 = new javax.swing.JButton();
        readStatusTile1239 = new javax.swing.JButton();
        readStatusTile1240 = new javax.swing.JButton();
        readStatusTile1241 = new javax.swing.JButton();
        readStatusTile1242 = new javax.swing.JButton();
        readStatusTile1243 = new javax.swing.JButton();
        readStatusTile1244 = new javax.swing.JButton();
        readStatusTile1245 = new javax.swing.JButton();
        readStatusTile1246 = new javax.swing.JButton();
        readStatusTile1247 = new javax.swing.JButton();
        readStatusTile1248 = new javax.swing.JButton();
        readStatusTile1249 = new javax.swing.JButton();
        readStatusTile1250 = new javax.swing.JButton();
        readStatusTile1251 = new javax.swing.JButton();
        readStatusTile1252 = new javax.swing.JButton();
        readStatusTile1253 = new javax.swing.JButton();
        readStatusTile1254 = new javax.swing.JButton();
        readStatusTile1255 = new javax.swing.JButton();
        readStatusTile1256 = new javax.swing.JButton();
        readStatusTile1257 = new javax.swing.JButton();
        readStatusTile1258 = new javax.swing.JButton();
        readStatusTile1259 = new javax.swing.JButton();
        readStatusTile1260 = new javax.swing.JButton();
        readStatusTile1261 = new javax.swing.JButton();
        readStatusTile1262 = new javax.swing.JButton();
        readStatusTile1263 = new javax.swing.JButton();
        readStatusTile1264 = new javax.swing.JButton();
        readStatusTile1265 = new javax.swing.JButton();
        readStatusTile1266 = new javax.swing.JButton();
        readStatusTile1267 = new javax.swing.JButton();
        readStatusTile1268 = new javax.swing.JButton();
        readStatusTile1269 = new javax.swing.JButton();
        readStatusTile1270 = new javax.swing.JButton();
        readStatusTile1271 = new javax.swing.JButton();
        readStatusTile1272 = new javax.swing.JButton();
        readStatusTile1273 = new javax.swing.JButton();
        readStatusTile1274 = new javax.swing.JButton();
        readStatusTile1275 = new javax.swing.JButton();
        readStatusTile1276 = new javax.swing.JButton();
        readStatusTile1277 = new javax.swing.JButton();
        readStatusTile1278 = new javax.swing.JButton();
        readStatusTile1279 = new javax.swing.JButton();
        readStatusTile1280 = new javax.swing.JButton();
        readStatusTile1281 = new javax.swing.JButton();
        readStatusTile1282 = new javax.swing.JButton();
        readStatusTile1283 = new javax.swing.JButton();
        readStatusTile1284 = new javax.swing.JButton();
        readStatusTile1285 = new javax.swing.JButton();
        readStatusTile1286 = new javax.swing.JButton();
        readStatusTile1287 = new javax.swing.JButton();
        readStatusTile1288 = new javax.swing.JButton();
        readStatusTile1289 = new javax.swing.JButton();
        readStatusTile1290 = new javax.swing.JButton();
        readStatusTile1291 = new javax.swing.JButton();
        readStatusTile1292 = new javax.swing.JButton();
        readStatusTile1293 = new javax.swing.JButton();
        readStatusTile1294 = new javax.swing.JButton();
        readStatusTile1295 = new javax.swing.JButton();
        readStatusTile1296 = new javax.swing.JButton();
        readStatusTile1297 = new javax.swing.JButton();
        readStatusTile1298 = new javax.swing.JButton();
        readStatusTile1299 = new javax.swing.JButton();
        readStatusTile1300 = new javax.swing.JButton();
        readStatusTile1301 = new javax.swing.JButton();
        readStatusTile1302 = new javax.swing.JButton();
        readStatusTile1303 = new javax.swing.JButton();
        readStatusTile1304 = new javax.swing.JButton();
        readStatusTile1305 = new javax.swing.JButton();
        readStatusTile1306 = new javax.swing.JButton();
        readStatusTile1307 = new javax.swing.JButton();
        readStatusTile1308 = new javax.swing.JButton();
        readStatusTile1309 = new javax.swing.JButton();
        readStatusTile1310 = new javax.swing.JButton();
        readStatusTile1311 = new javax.swing.JButton();
        readStatusTile1312 = new javax.swing.JButton();
        readStatusTile1313 = new javax.swing.JButton();
        readStatusTile1314 = new javax.swing.JButton();
        readStatusTile1315 = new javax.swing.JButton();
        readStatusTile1316 = new javax.swing.JButton();
        readStatusTile1317 = new javax.swing.JButton();
        readStatusTile1318 = new javax.swing.JButton();
        readStatusTile1319 = new javax.swing.JButton();
        readStatusTile1320 = new javax.swing.JButton();
        readStatusTile1321 = new javax.swing.JButton();
        readStatusTile1322 = new javax.swing.JButton();
        readStatusTile1323 = new javax.swing.JButton();
        readStatusTile1324 = new javax.swing.JButton();
        readStatusTile1325 = new javax.swing.JButton();
        readStatusTile1326 = new javax.swing.JButton();
        readStatusTile1327 = new javax.swing.JButton();
        readStatusTile1328 = new javax.swing.JButton();
        readStatusTile1329 = new javax.swing.JButton();
        readStatusTile1330 = new javax.swing.JButton();
        readStatusTile1331 = new javax.swing.JButton();
        readStatusTile1332 = new javax.swing.JButton();
        readStatusTile1333 = new javax.swing.JButton();
        readStatusTile1334 = new javax.swing.JButton();
        readStatusTile1335 = new javax.swing.JButton();
        readStatusTile1336 = new javax.swing.JButton();
        readStatusTile1337 = new javax.swing.JButton();
        readStatusTile1338 = new javax.swing.JButton();
        readStatusTile1339 = new javax.swing.JButton();
        readStatusTile1340 = new javax.swing.JButton();
        readStatusTile1341 = new javax.swing.JButton();
        readStatusTile1342 = new javax.swing.JButton();
        readStatusTile1343 = new javax.swing.JButton();
        readStatusTile1344 = new javax.swing.JButton();
        readStatusTile1345 = new javax.swing.JButton();
        readStatusTile1346 = new javax.swing.JButton();
        readStatusTile1347 = new javax.swing.JButton();
        readStatusTile1348 = new javax.swing.JButton();
        readStatusTile1349 = new javax.swing.JButton();
        readStatusTile1350 = new javax.swing.JButton();
        readStatusTile1351 = new javax.swing.JButton();
        readStatusTile1352 = new javax.swing.JButton();
        readStatusTile1353 = new javax.swing.JButton();
        readStatusTile1354 = new javax.swing.JButton();
        readStatusTile1355 = new javax.swing.JButton();
        readStatusTile1356 = new javax.swing.JButton();
        readStatusTile1357 = new javax.swing.JButton();
        readStatusTile1358 = new javax.swing.JButton();
        readStatusTile1359 = new javax.swing.JButton();
        readStatusTile1360 = new javax.swing.JButton();
        readStatusTile1361 = new javax.swing.JButton();
        readStatusTile1362 = new javax.swing.JButton();
        readStatusTile1363 = new javax.swing.JButton();
        readStatusTile1364 = new javax.swing.JButton();
        readStatusTile1365 = new javax.swing.JButton();
        readStatusTile1366 = new javax.swing.JButton();
        readStatusTile1367 = new javax.swing.JButton();
        readStatusTile1368 = new javax.swing.JButton();
        readStatusTile1369 = new javax.swing.JButton();
        readStatusTile1370 = new javax.swing.JButton();
        readStatusTile1371 = new javax.swing.JButton();
        readStatusTile1372 = new javax.swing.JButton();
        readStatusTile1373 = new javax.swing.JButton();
        readStatusTile1374 = new javax.swing.JButton();
        readStatusTile1375 = new javax.swing.JButton();
        readStatusTile1376 = new javax.swing.JButton();
        readStatusTile1377 = new javax.swing.JButton();
        readStatusTile1378 = new javax.swing.JButton();
        readStatusTile1379 = new javax.swing.JButton();
        readStatusTile1380 = new javax.swing.JButton();
        readStatusTile1381 = new javax.swing.JButton();
        readStatusTile1382 = new javax.swing.JButton();
        readStatusTile1383 = new javax.swing.JButton();
        readStatusTile1384 = new javax.swing.JButton();
        readStatusTile1385 = new javax.swing.JButton();
        readStatusTile1386 = new javax.swing.JButton();
        readStatusTile1387 = new javax.swing.JButton();
        readStatusTile1388 = new javax.swing.JButton();
        readStatusTile1389 = new javax.swing.JButton();
        readStatusTile1390 = new javax.swing.JButton();
        readStatusTile1391 = new javax.swing.JButton();
        readStatusTile1392 = new javax.swing.JButton();
        readStatusTile1393 = new javax.swing.JButton();
        readStatusTile1394 = new javax.swing.JButton();
        readStatusTile1395 = new javax.swing.JButton();
        readStatusTile1396 = new javax.swing.JButton();
        readStatusTile1397 = new javax.swing.JButton();
        readStatusTile1398 = new javax.swing.JButton();
        readStatusTile1399 = new javax.swing.JButton();
        readStatusTile1400 = new javax.swing.JButton();
        readStatusTile1401 = new javax.swing.JButton();
        readStatusTile1402 = new javax.swing.JButton();
        readStatusTile1403 = new javax.swing.JButton();
        readStatusTile1404 = new javax.swing.JButton();
        readStatusTile1405 = new javax.swing.JButton();
        readStatusTile1406 = new javax.swing.JButton();
        readStatusTile1407 = new javax.swing.JButton();
        readStatusTile1408 = new javax.swing.JButton();
        readStatusTile1409 = new javax.swing.JButton();
        readStatusTile1410 = new javax.swing.JButton();
        readStatusTile1411 = new javax.swing.JButton();
        readStatusTile1412 = new javax.swing.JButton();
        readStatusTile1413 = new javax.swing.JButton();
        readStatusTile1414 = new javax.swing.JButton();
        readStatusTile1415 = new javax.swing.JButton();
        readStatusTile1416 = new javax.swing.JButton();
        readStatusTile1417 = new javax.swing.JButton();
        readStatusTile1418 = new javax.swing.JButton();
        readStatusTile1419 = new javax.swing.JButton();
        readStatusTile1420 = new javax.swing.JButton();
        readStatusTile1421 = new javax.swing.JButton();
        readStatusTile1422 = new javax.swing.JButton();
        readStatusTile1423 = new javax.swing.JButton();
        readStatusTile1424 = new javax.swing.JButton();
        readStatusTile1425 = new javax.swing.JButton();
        readStatusTile1426 = new javax.swing.JButton();
        readStatusTile1427 = new javax.swing.JButton();
        readStatusTile1428 = new javax.swing.JButton();
        readStatusTile1429 = new javax.swing.JButton();
        readStatusTile1430 = new javax.swing.JButton();
        readStatusTile1431 = new javax.swing.JButton();
        readStatusTile1432 = new javax.swing.JButton();
        readStatusTile1433 = new javax.swing.JButton();
        readStatusTile1434 = new javax.swing.JButton();
        readStatusTile1435 = new javax.swing.JButton();
        readStatusTile1436 = new javax.swing.JButton();
        readStatusTile1437 = new javax.swing.JButton();
        readStatusTile1438 = new javax.swing.JButton();
        readStatusTile1439 = new javax.swing.JButton();
        readStatusTile1440 = new javax.swing.JButton();
        readStatusTile1441 = new javax.swing.JButton();
        readStatusTile1442 = new javax.swing.JButton();
        readStatusTile1443 = new javax.swing.JButton();
        readStatusTile1444 = new javax.swing.JButton();
        readStatusTile1445 = new javax.swing.JButton();
        readStatusTile1446 = new javax.swing.JButton();
        readStatusTile1447 = new javax.swing.JButton();
        readStatusTile1448 = new javax.swing.JButton();
        readStatusTile1449 = new javax.swing.JButton();
        readStatusTile1450 = new javax.swing.JButton();
        readStatusTile1451 = new javax.swing.JButton();
        readStatusTile1452 = new javax.swing.JButton();
        readStatusTile1453 = new javax.swing.JButton();
        readStatusTile1454 = new javax.swing.JButton();
        readStatusTile1455 = new javax.swing.JButton();
        readStatusTile1456 = new javax.swing.JButton();
        readStatusTile1457 = new javax.swing.JButton();
        readStatusTile1458 = new javax.swing.JButton();
        readStatusTile1459 = new javax.swing.JButton();
        readStatusTile1460 = new javax.swing.JButton();
        readStatusTile1461 = new javax.swing.JButton();
        readStatusTile1462 = new javax.swing.JButton();
        readStatusTile1463 = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        months13 = new javax.swing.JLabel();
        months14 = new javax.swing.JLabel();
        months15 = new javax.swing.JLabel();
        months16 = new javax.swing.JLabel();
        months17 = new javax.swing.JLabel();
        months18 = new javax.swing.JLabel();
        months19 = new javax.swing.JLabel();
        months20 = new javax.swing.JLabel();
        months21 = new javax.swing.JLabel();
        months22 = new javax.swing.JLabel();
        months23 = new javax.swing.JLabel();
        months24 = new javax.swing.JLabel();
        months25 = new javax.swing.JLabel();
        libraryTabbackup = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        bookDescriptionTileOne = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        bookDescriptionTileOne1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        bookDescriptionTileOne2 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        bookDescriptionTileOne3 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        bookTile1 = new javax.swing.JButton();
        bookTile2 = new javax.swing.JButton();
        bookTile3 = new javax.swing.JButton();
        bookTile4 = new javax.swing.JButton();
        moreBtn = new javax.swing.JButton();
        readStatusPanel = new javax.swing.JPanel();
        readStatusTile = new javax.swing.JButton();
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
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        bottomSpacer = new javax.swing.JPanel();
        months = new javax.swing.JLabel();
        months1 = new javax.swing.JLabel();
        months2 = new javax.swing.JLabel();
        months3 = new javax.swing.JLabel();
        months4 = new javax.swing.JLabel();
        months5 = new javax.swing.JLabel();
        months6 = new javax.swing.JLabel();
        months7 = new javax.swing.JLabel();
        months8 = new javax.swing.JLabel();
        months9 = new javax.swing.JLabel();
        months10 = new javax.swing.JLabel();
        months11 = new javax.swing.JLabel();
        months12 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
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
        notesInput.setRows(5);
        notesInput.setBorder(null);
        notesTabLayers.add(notesInput);
        notesInput.setBounds(0, 0, 670, 820);
        notesInput.setMargin(new Insets(10, 10, 10, 10));

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

        bookTile5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bookTiles/1V1S9h1Ey1zGTa-QIn83.jpeg"))); // NOI18N
        bookTile5.setBorder(null);

        bookDescriptionTileOne4.setBackground(new java.awt.Color(204, 204, 204));

        jLabel10.setFont(new java.awt.Font("Nokia Pure Headline Ultra Light", 1, 18)); // NOI18N
        jLabel10.setText("Desiring GOD ");

        jLabel13.setFont(new java.awt.Font("Nokia Pure Headline Ultra Light", 0, 14)); // NOI18N
        jLabel13.setText("John Piper");

        javax.swing.GroupLayout bookDescriptionTileOne4Layout = new javax.swing.GroupLayout(bookDescriptionTileOne4);
        bookDescriptionTileOne4.setLayout(bookDescriptionTileOne4Layout);
        bookDescriptionTileOne4Layout.setHorizontalGroup(
            bookDescriptionTileOne4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bookDescriptionTileOne4Layout.createSequentialGroup()
                .addGap(104, 104, 104)
                .addGroup(bookDescriptionTileOne4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bookDescriptionTileOne4Layout.createSequentialGroup()
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        bookDescriptionTileOne4Layout.setVerticalGroup(
            bookDescriptionTileOne4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bookDescriptionTileOne4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel13)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        bookTile6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bookTiles/1V1SrF1BZ2uDBV-ndXZj.jpeg"))); // NOI18N
        bookTile6.setBorder(null);

        bookDescriptionTileOne5.setBackground(new java.awt.Color(204, 204, 204));

        jLabel14.setFont(new java.awt.Font("Nokia Pure Headline Ultra Light", 1, 18)); // NOI18N
        jLabel14.setText("With Christ in the School of Prayer");

        jLabel15.setFont(new java.awt.Font("Nokia Pure Headline Ultra Light", 0, 14)); // NOI18N
        jLabel15.setText("Andrew Murray");

        javax.swing.GroupLayout bookDescriptionTileOne5Layout = new javax.swing.GroupLayout(bookDescriptionTileOne5);
        bookDescriptionTileOne5.setLayout(bookDescriptionTileOne5Layout);
        bookDescriptionTileOne5Layout.setHorizontalGroup(
            bookDescriptionTileOne5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bookDescriptionTileOne5Layout.createSequentialGroup()
                .addContainerGap(39, Short.MAX_VALUE)
                .addGroup(bookDescriptionTileOne5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bookDescriptionTileOne5Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addGap(25, 25, 25))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bookDescriptionTileOne5Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addGap(116, 116, 116))))
        );
        bookDescriptionTileOne5Layout.setVerticalGroup(
            bookDescriptionTileOne5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bookDescriptionTileOne5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel15)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        bookTile7.setBackground(new java.awt.Color(204, 204, 204));
        bookTile7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bookTiles/1V1SyG12FcdRFF-m2vIY.jpeg"))); // NOI18N
        bookTile7.setBorder(null);
        bookTile7.setBorderPainted(false);
        bookTile7.setContentAreaFilled(false);

        bookDescriptionTileOne6.setBackground(new java.awt.Color(204, 204, 204));

        jLabel16.setFont(new java.awt.Font("Nokia Pure Headline Ultra Light", 1, 18)); // NOI18N
        jLabel16.setText("Good Morning Holy Spirit");

        jLabel17.setFont(new java.awt.Font("Nokia Pure Headline Ultra Light", 0, 14)); // NOI18N
        jLabel17.setText("Benny Hinn");

        javax.swing.GroupLayout bookDescriptionTileOne6Layout = new javax.swing.GroupLayout(bookDescriptionTileOne6);
        bookDescriptionTileOne6.setLayout(bookDescriptionTileOne6Layout);
        bookDescriptionTileOne6Layout.setHorizontalGroup(
            bookDescriptionTileOne6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bookDescriptionTileOne6Layout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addComponent(jLabel16)
                .addContainerGap(53, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bookDescriptionTileOne6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel17)
                .addGap(118, 118, 118))
        );
        bookDescriptionTileOne6Layout.setVerticalGroup(
            bookDescriptionTileOne6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bookDescriptionTileOne6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel17)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        bookTile8.setBackground(new java.awt.Color(204, 204, 204));
        bookTile8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bookTiles/1V1SFW1LlyJ9te-cLuOA.jpeg"))); // NOI18N
        bookTile8.setBorder(null);
        bookTile8.setBorderPainted(false);
        bookTile8.setContentAreaFilled(false);

        bookDescriptionTileOne7.setBackground(new java.awt.Color(204, 204, 204));

        jLabel18.setFont(new java.awt.Font("Nokia Pure Headline Ultra Light", 1, 18)); // NOI18N
        jLabel18.setText("Crazy Love");

        jLabel19.setFont(new java.awt.Font("Nokia Pure Headline Ultra Light", 0, 14)); // NOI18N
        jLabel19.setText("Francis Chan");

        javax.swing.GroupLayout bookDescriptionTileOne7Layout = new javax.swing.GroupLayout(bookDescriptionTileOne7);
        bookDescriptionTileOne7.setLayout(bookDescriptionTileOne7Layout);
        bookDescriptionTileOne7Layout.setHorizontalGroup(
            bookDescriptionTileOne7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bookDescriptionTileOne7Layout.createSequentialGroup()
                .addGap(104, 104, 104)
                .addGroup(bookDescriptionTileOne7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(bookDescriptionTileOne7Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel19)))
                .addContainerGap(92, Short.MAX_VALUE))
        );
        bookDescriptionTileOne7Layout.setVerticalGroup(
            bookDescriptionTileOne7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bookDescriptionTileOne7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel19)
                .addGap(0, 9, Short.MAX_VALUE))
        );

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
                .addContainerGap(1438, Short.MAX_VALUE)
                .addComponent(moreBtn1)
                .addGap(24, 24, 24))
            .addGroup(upperBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(upperBarLayout.createSequentialGroup()
                    .addGap(44, 44, 44)
                    .addGroup(upperBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(bookDescriptionTileOne4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bookTile5))
                    .addGap(42, 42, 42)
                    .addGroup(upperBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(bookTile6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(bookDescriptionTileOne5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(32, 32, 32)
                    .addGroup(upperBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(bookTile7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bookDescriptionTileOne6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(44, 44, 44)
                    .addGroup(upperBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, upperBarLayout.createSequentialGroup()
                            .addComponent(bookTile8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGap(151, 151, 151))
                        .addGroup(upperBarLayout.createSequentialGroup()
                            .addGap(1, 1, 1)
                            .addComponent(bookDescriptionTileOne7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(150, 150, 150)))))
        );
        upperBarLayout.setVerticalGroup(
            upperBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(upperBarLayout.createSequentialGroup()
                .addGap(135, 135, 135)
                .addComponent(moreBtn1, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(244, Short.MAX_VALUE))
            .addGroup(upperBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(upperBarLayout.createSequentialGroup()
                    .addGap(8, 8, 8)
                    .addGroup(upperBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(upperBarLayout.createSequentialGroup()
                            .addGroup(upperBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(bookTile5, javax.swing.GroupLayout.PREFERRED_SIZE, 449, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(bookTile7, javax.swing.GroupLayout.PREFERRED_SIZE, 449, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(bookTile8, javax.swing.GroupLayout.PREFERRED_SIZE, 449, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(upperBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(bookDescriptionTileOne4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(bookDescriptionTileOne6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(bookDescriptionTileOne7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(upperBarLayout.createSequentialGroup()
                            .addComponent(bookTile6, javax.swing.GroupLayout.PREFERRED_SIZE, 449, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(bookDescriptionTileOne5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addContainerGap(26, Short.MAX_VALUE)))
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
                    .addComponent(readStatusTile1098, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1099, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1101, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1100, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1102, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1103, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1104, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(readStatusPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(readStatusTile1105, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1106, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1108, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1107, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1109, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1110, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1111, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(readStatusPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(readStatusTile1112, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1113, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1115, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1114, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1116, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1117, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1118, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(readStatusPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(readStatusTile1119, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1120, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1122, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1121, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1123, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1124, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1125, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(readStatusPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(readStatusTile1126, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1127, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1129, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1128, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1130, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1131, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1132, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(readStatusPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(readStatusTile1133, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1134, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1136, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1135, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1137, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1138, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1139, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(readStatusPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(readStatusTile1140, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1141, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1143, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1142, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1144, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1145, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1146, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(readStatusPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(readStatusTile1147, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1148, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1150, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1149, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1151, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1152, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1153, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(readStatusPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(readStatusTile1154, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1155, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1157, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1156, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1158, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1159, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1160, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(readStatusPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(readStatusTile1161, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1162, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1164, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1163, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1165, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1166, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1167, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(readStatusPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(readStatusTile1168, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1169, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1171, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1170, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1172, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1173, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1174, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(readStatusPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(readStatusTile1175, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1176, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1178, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1177, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1179, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1180, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1181, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(readStatusPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(readStatusTile1182, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1183, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1185, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1184, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1186, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1187, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1188, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(readStatusPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(readStatusTile1189, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1190, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1192, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1191, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1193, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1194, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1195, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(readStatusPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(readStatusTile1196, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1197, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1199, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1198, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1200, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1201, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1202, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(readStatusPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(readStatusTile1203, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1204, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1206, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1205, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1207, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1208, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1209, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(readStatusPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(readStatusTile1210, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1211, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1213, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1212, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1214, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1215, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1216, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(readStatusPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(readStatusTile1217, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1218, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1220, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1219, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1221, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1222, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1223, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(readStatusPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(readStatusTile1224, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1225, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1227, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1226, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1228, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1229, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1230, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(readStatusPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(readStatusTile1231, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1232, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1234, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1233, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1235, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1236, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1237, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(readStatusPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(readStatusTile1238, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1239, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1241, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1240, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1242, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1243, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1244, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(readStatusPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(readStatusTile1245, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1246, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1248, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1247, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1249, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1250, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1251, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(readStatusPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(readStatusTile1252, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1253, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1255, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1254, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1256, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1257, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1258, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(readStatusPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(readStatusTile1259, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1260, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1262, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1261, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1263, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1264, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1265, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(readStatusPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(readStatusTile1266, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1267, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1269, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1268, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1270, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1271, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1272, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(readStatusPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(readStatusTile1273, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1274, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1276, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1275, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1277, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1278, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1279, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(readStatusPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(readStatusTile1280, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1281, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1283, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1282, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1284, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1285, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1286, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(readStatusPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(readStatusTile1287, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1288, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1290, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1289, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1291, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1292, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1293, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(readStatusPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(readStatusTile1294, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1295, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1297, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1296, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1298, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1299, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1300, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(readStatusPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(readStatusTile1301, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1302, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1304, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1303, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1305, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1306, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1307, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(readStatusPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(readStatusTile1308, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1309, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1311, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1310, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1312, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1313, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1314, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(readStatusPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(readStatusTile1315, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1316, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1318, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1317, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1319, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1320, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1321, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(readStatusPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(readStatusTile1322, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1323, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1325, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1324, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1326, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1327, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1328, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(readStatusPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(readStatusTile1329, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1330, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1332, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1331, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1333, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1334, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1335, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(readStatusPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(readStatusTile1336, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1337, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1339, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1338, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1340, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1341, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1342, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(readStatusPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(readStatusTile1343, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1344, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1346, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1345, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1347, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1348, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1349, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(readStatusPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(readStatusTile1350, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1351, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1353, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1352, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1354, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1355, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1356, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(readStatusPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(readStatusTile1357, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1358, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1360, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1359, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1361, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1362, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1363, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(readStatusPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(readStatusTile1364, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1365, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1367, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1366, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1368, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1369, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1370, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(readStatusPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(readStatusTile1371, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1372, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1374, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1373, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1375, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1376, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1377, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(readStatusPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(readStatusTile1378, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1379, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1381, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1380, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1382, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1383, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1384, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(readStatusPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(readStatusTile1385, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1386, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1388, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1387, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1389, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1390, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1391, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(readStatusPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(readStatusTile1392, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1393, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1395, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1394, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1396, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1397, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1398, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(readStatusPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(readStatusTile1399, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1400, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1402, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1401, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1403, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1404, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1405, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(readStatusPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(readStatusTile1406, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1407, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1409, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1408, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1410, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1411, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1412, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(readStatusPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(readStatusTile1413, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1414, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1416, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1415, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1417, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1418, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1419, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(readStatusPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(readStatusTile1420, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1421, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1423, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1422, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1424, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1425, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1426, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(readStatusPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(readStatusTile1427, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1428, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1430, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1429, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1431, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1432, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1433, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(readStatusPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(readStatusTile1434, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1435, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1437, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1436, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1438, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1439, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1440, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(readStatusPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(readStatusTile1441, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1442, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1444, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1443, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1445, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1446, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1447, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(readStatusPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(readStatusTile1448, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1449, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1451, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1450, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1452, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1453, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1454, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(readStatusPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(readStatusTile1455, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1456, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1458, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1457, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1459, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1460, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1461, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(readStatusPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(readStatusTile1462, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1463, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        readStatusPanel3Layout.setVerticalGroup(
            readStatusPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(readStatusPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(readStatusPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(readStatusPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(readStatusPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(readStatusPanel3Layout.createSequentialGroup()
                                .addComponent(readStatusTile1462, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(readStatusTile1463, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(readStatusPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(readStatusPanel3Layout.createSequentialGroup()
                                    .addComponent(readStatusTile1455, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1456, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1458, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1457, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1459, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1460, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1461, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(readStatusPanel3Layout.createSequentialGroup()
                                    .addComponent(readStatusTile1448, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1449, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1451, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1450, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1452, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1453, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1454, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(readStatusPanel3Layout.createSequentialGroup()
                                    .addComponent(readStatusTile1441, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1442, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1444, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1443, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1445, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1446, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1447, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(readStatusPanel3Layout.createSequentialGroup()
                                    .addComponent(readStatusTile1434, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1435, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1437, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1436, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1438, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1439, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1440, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(readStatusPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(readStatusPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(readStatusPanel3Layout.createSequentialGroup()
                                    .addComponent(readStatusTile1427, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1428, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1430, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1429, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1431, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1432, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1433, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(readStatusPanel3Layout.createSequentialGroup()
                                    .addComponent(readStatusTile1420, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1421, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1423, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1422, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1424, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1425, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1426, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(readStatusPanel3Layout.createSequentialGroup()
                                    .addComponent(readStatusTile1413, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1414, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1416, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1415, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1417, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1418, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1419, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(readStatusPanel3Layout.createSequentialGroup()
                                    .addComponent(readStatusTile1406, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1407, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1409, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1408, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1410, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1411, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1412, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(readStatusPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(readStatusPanel3Layout.createSequentialGroup()
                                    .addComponent(readStatusTile1399, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1400, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1402, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1401, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1403, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1404, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1405, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(readStatusPanel3Layout.createSequentialGroup()
                                    .addComponent(readStatusTile1392, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1393, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1395, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1394, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1396, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1397, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1398, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(readStatusPanel3Layout.createSequentialGroup()
                                    .addComponent(readStatusTile1385, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1386, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1388, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1387, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1389, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1390, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1391, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(readStatusPanel3Layout.createSequentialGroup()
                                    .addComponent(readStatusTile1378, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1379, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1381, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1380, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1382, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1383, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1384, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(readStatusPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(readStatusPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(readStatusPanel3Layout.createSequentialGroup()
                                    .addComponent(readStatusTile1371, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1372, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1374, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1373, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1375, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1376, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1377, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(readStatusPanel3Layout.createSequentialGroup()
                                    .addComponent(readStatusTile1364, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1365, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1367, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1366, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1368, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1369, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1370, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(readStatusPanel3Layout.createSequentialGroup()
                                    .addComponent(readStatusTile1357, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1358, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1360, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1359, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1361, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1362, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1363, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(readStatusPanel3Layout.createSequentialGroup()
                                    .addComponent(readStatusTile1350, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1351, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1353, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1352, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1354, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1355, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1356, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(readStatusPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(readStatusPanel3Layout.createSequentialGroup()
                                    .addComponent(readStatusTile1343, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1344, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1346, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1345, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1347, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1348, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1349, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(readStatusPanel3Layout.createSequentialGroup()
                                    .addComponent(readStatusTile1336, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1337, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1339, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1338, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1340, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1341, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1342, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(readStatusPanel3Layout.createSequentialGroup()
                                    .addComponent(readStatusTile1329, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1330, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1332, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1331, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1333, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1334, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1335, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(readStatusPanel3Layout.createSequentialGroup()
                                    .addComponent(readStatusTile1322, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1323, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1325, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1324, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1326, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1327, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1328, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(readStatusPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(readStatusPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(readStatusPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(readStatusPanel3Layout.createSequentialGroup()
                                    .addComponent(readStatusTile1315, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1316, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1318, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1317, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1319, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1320, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1321, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(readStatusPanel3Layout.createSequentialGroup()
                                    .addComponent(readStatusTile1308, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1309, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1311, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1310, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1312, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1313, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1314, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(readStatusPanel3Layout.createSequentialGroup()
                                    .addComponent(readStatusTile1301, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1302, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1304, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1303, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1305, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1306, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1307, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(readStatusPanel3Layout.createSequentialGroup()
                                    .addComponent(readStatusTile1294, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1295, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1297, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1296, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1298, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1299, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1300, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(readStatusPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(readStatusPanel3Layout.createSequentialGroup()
                                    .addComponent(readStatusTile1287, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1288, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1290, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1289, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1291, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1292, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1293, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(readStatusPanel3Layout.createSequentialGroup()
                                    .addComponent(readStatusTile1280, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1281, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1283, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1282, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1284, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1285, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1286, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(readStatusPanel3Layout.createSequentialGroup()
                                    .addComponent(readStatusTile1273, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1274, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1276, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1275, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1277, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1278, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1279, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(readStatusPanel3Layout.createSequentialGroup()
                                    .addComponent(readStatusTile1266, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1267, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1269, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1268, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1270, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1271, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1272, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(readStatusPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(readStatusPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(readStatusPanel3Layout.createSequentialGroup()
                                    .addComponent(readStatusTile1259, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1260, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1262, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1261, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1263, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1264, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1265, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(readStatusPanel3Layout.createSequentialGroup()
                                    .addComponent(readStatusTile1252, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1253, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1255, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1254, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1256, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1257, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1258, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(readStatusPanel3Layout.createSequentialGroup()
                                    .addComponent(readStatusTile1245, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1246, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1248, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1247, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1249, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1250, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1251, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(readStatusPanel3Layout.createSequentialGroup()
                                    .addComponent(readStatusTile1238, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1239, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1241, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1240, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1242, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1243, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1244, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(readStatusPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(readStatusPanel3Layout.createSequentialGroup()
                                    .addComponent(readStatusTile1231, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1232, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1234, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1233, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1235, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1236, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1237, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(readStatusPanel3Layout.createSequentialGroup()
                                    .addComponent(readStatusTile1224, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1225, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1227, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1226, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1228, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1229, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1230, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(readStatusPanel3Layout.createSequentialGroup()
                                    .addComponent(readStatusTile1217, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1218, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1220, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1219, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1221, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1222, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1223, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(readStatusPanel3Layout.createSequentialGroup()
                                    .addComponent(readStatusTile1210, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1211, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1213, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1212, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1214, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1215, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1216, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(readStatusPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(readStatusPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(readStatusPanel3Layout.createSequentialGroup()
                                    .addComponent(readStatusTile1203, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1204, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1206, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1205, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1207, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1208, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1209, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(readStatusPanel3Layout.createSequentialGroup()
                                    .addComponent(readStatusTile1196, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1197, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1199, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1198, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1200, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1201, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1202, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(readStatusPanel3Layout.createSequentialGroup()
                                    .addComponent(readStatusTile1189, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1190, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1192, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1191, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1193, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1194, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1195, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(readStatusPanel3Layout.createSequentialGroup()
                                    .addComponent(readStatusTile1182, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1183, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1185, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1184, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1186, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1187, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1188, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(readStatusPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(readStatusPanel3Layout.createSequentialGroup()
                                    .addComponent(readStatusTile1175, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1176, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1178, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1177, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1179, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1180, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1181, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(readStatusPanel3Layout.createSequentialGroup()
                                    .addComponent(readStatusTile1168, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1169, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1171, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1170, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1172, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1173, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1174, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(readStatusPanel3Layout.createSequentialGroup()
                                    .addComponent(readStatusTile1161, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1162, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1164, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1163, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1165, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1166, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1167, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(readStatusPanel3Layout.createSequentialGroup()
                                    .addComponent(readStatusTile1154, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1155, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1157, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1156, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1158, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1159, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1160, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(readStatusPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(readStatusPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(readStatusPanel3Layout.createSequentialGroup()
                                    .addComponent(readStatusTile1147, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1148, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1150, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1149, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1151, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1152, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1153, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(readStatusPanel3Layout.createSequentialGroup()
                                    .addComponent(readStatusTile1140, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1141, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1143, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1142, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1144, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1145, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1146, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(readStatusPanel3Layout.createSequentialGroup()
                                    .addComponent(readStatusTile1133, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1134, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1136, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1135, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1137, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1138, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1139, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(readStatusPanel3Layout.createSequentialGroup()
                                    .addComponent(readStatusTile1126, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1127, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1129, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1128, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1130, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1131, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1132, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(readStatusPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(readStatusPanel3Layout.createSequentialGroup()
                                    .addComponent(readStatusTile1119, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1120, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1122, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1121, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1123, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1124, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1125, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(readStatusPanel3Layout.createSequentialGroup()
                                    .addComponent(readStatusTile1112, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1113, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1115, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1114, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1116, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1117, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1118, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(readStatusPanel3Layout.createSequentialGroup()
                                    .addComponent(readStatusTile1105, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1106, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1108, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1107, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1109, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1110, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1111, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(readStatusPanel3Layout.createSequentialGroup()
                                    .addComponent(readStatusTile1098, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1099, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1101, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1100, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1102, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1103, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1104, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))))))
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

        months13.setFont(new java.awt.Font("Nokia Pure Headline Ultra Light", 0, 14)); // NOI18N
        months13.setText("Sep");
        bottomBar.add(months13);
        months13.setBounds(50, 200, 21, 20);

        months14.setFont(new java.awt.Font("Nokia Pure Headline Ultra Light", 0, 14)); // NOI18N
        months14.setText("Oct");
        bottomBar.add(months14);
        months14.setBounds(150, 200, 21, 20);

        months15.setFont(new java.awt.Font("Nokia Pure Headline Ultra Light", 0, 14)); // NOI18N
        months15.setText("Nov");
        bottomBar.add(months15);
        months15.setBounds(260, 200, 23, 20);

        months16.setFont(new java.awt.Font("Nokia Pure Headline Ultra Light", 0, 14)); // NOI18N
        months16.setText("Dec");
        bottomBar.add(months16);
        months16.setBounds(360, 200, 22, 20);

        months17.setFont(new java.awt.Font("Nokia Pure Headline Ultra Light", 0, 14)); // NOI18N
        months17.setText("Jan");
        bottomBar.add(months17);
        months17.setBounds(460, 200, 20, 20);

        months18.setFont(new java.awt.Font("Nokia Pure Headline Ultra Light", 0, 14)); // NOI18N
        months18.setText("Feb");
        bottomBar.add(months18);
        months18.setBounds(570, 200, 21, 20);

        months19.setFont(new java.awt.Font("Nokia Pure Headline Ultra Light", 0, 14)); // NOI18N
        months19.setText("Mar");
        bottomBar.add(months19);
        months19.setBounds(670, 200, 22, 20);

        months20.setFont(new java.awt.Font("Nokia Pure Headline Ultra Light", 0, 14)); // NOI18N
        months20.setText("Apr");
        bottomBar.add(months20);
        months20.setBounds(780, 200, 20, 20);

        months21.setFont(new java.awt.Font("Nokia Pure Headline Ultra Light", 0, 14)); // NOI18N
        months21.setText("Jun");
        bottomBar.add(months21);
        months21.setBounds(980, 200, 20, 20);

        months22.setFont(new java.awt.Font("Nokia Pure Headline Ultra Light", 0, 14)); // NOI18N
        months22.setText("Jul");
        bottomBar.add(months22);
        months22.setBounds(1090, 200, 16, 20);

        months23.setFont(new java.awt.Font("Nokia Pure Headline Ultra Light", 0, 14)); // NOI18N
        months23.setText("Aug");
        bottomBar.add(months23);
        months23.setBounds(1190, 200, 22, 20);

        months24.setFont(new java.awt.Font("Nokia Pure Headline Ultra Light", 0, 14)); // NOI18N
        months24.setText("May");
        bottomBar.add(months24);
        months24.setBounds(880, 200, 23, 20);

        months25.setFont(new java.awt.Font("Nokia Pure Headline Ultra Light", 0, 14)); // NOI18N
        months25.setText("Sep");
        bottomBar.add(months25);
        months25.setBounds(1290, 200, 21, 20);

        javax.swing.GroupLayout libraryTabLayout = new javax.swing.GroupLayout(libraryTab);
        libraryTab.setLayout(libraryTabLayout);
        libraryTabLayout.setHorizontalGroup(
            libraryTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, libraryTabLayout.createSequentialGroup()
                .addGap(0, 12, Short.MAX_VALUE)
                .addComponent(upperBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, libraryTabLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(bottomBar, javax.swing.GroupLayout.PREFERRED_SIZE, 1554, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        libraryTabLayout.setVerticalGroup(
            libraryTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(libraryTabLayout.createSequentialGroup()
                .addContainerGap(102, Short.MAX_VALUE)
                .addComponent(upperBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(bottomBar, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34))
        );

        tabs.addTab("Boomarks", libraryTab);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        bookDescriptionTileOne.setBackground(new java.awt.Color(204, 204, 204));

        jLabel2.setFont(new java.awt.Font("Nokia Pure Headline Ultra Light", 1, 18)); // NOI18N
        jLabel2.setText("Desiring GOD ");

        jLabel3.setFont(new java.awt.Font("Nokia Pure Headline Ultra Light", 0, 14)); // NOI18N
        jLabel3.setText("John Piper");

        javax.swing.GroupLayout bookDescriptionTileOneLayout = new javax.swing.GroupLayout(bookDescriptionTileOne);
        bookDescriptionTileOne.setLayout(bookDescriptionTileOneLayout);
        bookDescriptionTileOneLayout.setHorizontalGroup(
            bookDescriptionTileOneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bookDescriptionTileOneLayout.createSequentialGroup()
                .addGap(104, 104, 104)
                .addGroup(bookDescriptionTileOneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bookDescriptionTileOneLayout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        bookDescriptionTileOneLayout.setVerticalGroup(
            bookDescriptionTileOneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bookDescriptionTileOneLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        bookDescriptionTileOne1.setBackground(new java.awt.Color(204, 204, 204));

        jLabel5.setFont(new java.awt.Font("Nokia Pure Headline Ultra Light", 1, 18)); // NOI18N
        jLabel5.setText("With Christ in the School of Prayer");

        jLabel6.setFont(new java.awt.Font("Nokia Pure Headline Ultra Light", 0, 14)); // NOI18N
        jLabel6.setText("Andrew Murray");

        javax.swing.GroupLayout bookDescriptionTileOne1Layout = new javax.swing.GroupLayout(bookDescriptionTileOne1);
        bookDescriptionTileOne1.setLayout(bookDescriptionTileOne1Layout);
        bookDescriptionTileOne1Layout.setHorizontalGroup(
            bookDescriptionTileOne1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bookDescriptionTileOne1Layout.createSequentialGroup()
                .addContainerGap(39, Short.MAX_VALUE)
                .addGroup(bookDescriptionTileOne1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bookDescriptionTileOne1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(25, 25, 25))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bookDescriptionTileOne1Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(116, 116, 116))))
        );
        bookDescriptionTileOne1Layout.setVerticalGroup(
            bookDescriptionTileOne1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bookDescriptionTileOne1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        bookDescriptionTileOne2.setBackground(new java.awt.Color(204, 204, 204));

        jLabel8.setFont(new java.awt.Font("Nokia Pure Headline Ultra Light", 1, 18)); // NOI18N
        jLabel8.setText("Good Morning Holy Spirit");

        jLabel9.setFont(new java.awt.Font("Nokia Pure Headline Ultra Light", 0, 14)); // NOI18N
        jLabel9.setText("Benny Hinn");

        javax.swing.GroupLayout bookDescriptionTileOne2Layout = new javax.swing.GroupLayout(bookDescriptionTileOne2);
        bookDescriptionTileOne2.setLayout(bookDescriptionTileOne2Layout);
        bookDescriptionTileOne2Layout.setHorizontalGroup(
            bookDescriptionTileOne2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bookDescriptionTileOne2Layout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addComponent(jLabel8)
                .addContainerGap(53, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bookDescriptionTileOne2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel9)
                .addGap(118, 118, 118))
        );
        bookDescriptionTileOne2Layout.setVerticalGroup(
            bookDescriptionTileOne2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bookDescriptionTileOne2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        bookDescriptionTileOne3.setBackground(new java.awt.Color(204, 204, 204));

        jLabel11.setFont(new java.awt.Font("Nokia Pure Headline Ultra Light", 1, 18)); // NOI18N
        jLabel11.setText("Crazy Love");

        jLabel12.setFont(new java.awt.Font("Nokia Pure Headline Ultra Light", 0, 14)); // NOI18N
        jLabel12.setText("Francis Chan");

        javax.swing.GroupLayout bookDescriptionTileOne3Layout = new javax.swing.GroupLayout(bookDescriptionTileOne3);
        bookDescriptionTileOne3.setLayout(bookDescriptionTileOne3Layout);
        bookDescriptionTileOne3Layout.setHorizontalGroup(
            bookDescriptionTileOne3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bookDescriptionTileOne3Layout.createSequentialGroup()
                .addGap(104, 104, 104)
                .addGroup(bookDescriptionTileOne3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(bookDescriptionTileOne3Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel12)))
                .addContainerGap(92, Short.MAX_VALUE))
        );
        bookDescriptionTileOne3Layout.setVerticalGroup(
            bookDescriptionTileOne3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bookDescriptionTileOne3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel12)
                .addGap(0, 9, Short.MAX_VALUE))
        );

        bookTile1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bookTiles/1V1S9h1Ey1zGTa-QIn83.jpeg"))); // NOI18N
        bookTile1.setBorder(null);

        bookTile2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bookTiles/1V1SrF1BZ2uDBV-ndXZj.jpeg"))); // NOI18N
        bookTile2.setBorder(null);

        bookTile3.setBackground(new java.awt.Color(204, 204, 204));
        bookTile3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bookTiles/1V1SyG12FcdRFF-m2vIY.jpeg"))); // NOI18N
        bookTile3.setBorder(null);
        bookTile3.setBorderPainted(false);
        bookTile3.setContentAreaFilled(false);

        bookTile4.setBackground(new java.awt.Color(204, 204, 204));
        bookTile4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bookTiles/1V1SFW1LlyJ9te-cLuOA.jpeg"))); // NOI18N
        bookTile4.setBorder(null);
        bookTile4.setBorderPainted(false);
        bookTile4.setContentAreaFilled(false);

        moreBtn.setBackground(new java.awt.Color(204, 204, 204));
        moreBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/arrowRight.png"))); // NOI18N
        moreBtn.setContentAreaFilled(false);
        moreBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                moreBtnActionPerformed(evt);
            }
        });

        readStatusPanel.setBackground(new java.awt.Color(204, 204, 204));

        javax.swing.GroupLayout readStatusPanelLayout = new javax.swing.GroupLayout(readStatusPanel);
        readStatusPanel.setLayout(readStatusPanelLayout);
        readStatusPanelLayout.setHorizontalGroup(
            readStatusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(readStatusPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(readStatusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(readStatusTile, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile5, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile6, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(readStatusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(readStatusTile7, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile8, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile10, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile9, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile11, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile12, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile13, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(readStatusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(readStatusTile14, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile15, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile17, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile16, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile18, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile19, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile20, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(readStatusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(readStatusTile21, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile22, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile24, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile23, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile25, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile26, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile27, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(readStatusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(readStatusTile28, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile29, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile31, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile30, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile32, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile33, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile34, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(readStatusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(readStatusTile35, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile36, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile38, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile37, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile39, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile40, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile41, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(readStatusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(readStatusTile42, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile43, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile45, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile44, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile46, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile47, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile48, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(readStatusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(readStatusTile49, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile50, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile52, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile51, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile53, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile54, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile55, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(readStatusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(readStatusTile56, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile57, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile59, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile58, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile60, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile61, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile62, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(readStatusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(readStatusTile63, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile64, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile66, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile65, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile67, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile68, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile69, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(readStatusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(readStatusTile70, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile71, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile73, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile72, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile74, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile75, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile76, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(readStatusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(readStatusTile77, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile78, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile80, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile79, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile81, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile82, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile83, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(readStatusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(readStatusTile84, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile85, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile87, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile86, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile88, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile89, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile90, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(readStatusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(readStatusTile91, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile92, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile94, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile93, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile95, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile96, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile97, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(readStatusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(readStatusTile98, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile99, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile101, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile100, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile102, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile103, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile104, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(readStatusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(readStatusTile105, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile106, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile108, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile107, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile109, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile110, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile111, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(readStatusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(readStatusTile112, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile113, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile115, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile114, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile116, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile117, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile118, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(readStatusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(readStatusTile119, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile120, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile122, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile121, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile123, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile124, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile125, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(readStatusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(readStatusTile126, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile127, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile129, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile128, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile130, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile131, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile132, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(readStatusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(readStatusTile133, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile134, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile136, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile135, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile137, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile138, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile139, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(readStatusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(readStatusTile140, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile141, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile143, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile142, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile144, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile145, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile146, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(readStatusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(readStatusTile147, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile148, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile150, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile149, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile151, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile152, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile153, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(readStatusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(readStatusTile154, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile155, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile157, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile156, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile158, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile159, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile160, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(readStatusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(readStatusTile161, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile162, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile164, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile163, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile165, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile166, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile167, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(readStatusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(readStatusTile168, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile169, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile171, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile170, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile172, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile173, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile174, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(readStatusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(readStatusTile175, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile176, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile178, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile177, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile179, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile180, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile181, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(readStatusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(readStatusTile182, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile183, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile185, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile184, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile186, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile187, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile188, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(readStatusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(readStatusTile189, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile190, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile192, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile191, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile193, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile194, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile195, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(readStatusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(readStatusTile196, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile197, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile199, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile198, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile200, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile201, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile202, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(readStatusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(readStatusTile203, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile204, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile206, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile205, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile207, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile208, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile209, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(readStatusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(readStatusTile210, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile211, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile213, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile212, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile214, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile215, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile216, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(readStatusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(readStatusTile217, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile218, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile220, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile219, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile221, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile222, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile223, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(readStatusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(readStatusTile224, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile225, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile227, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile226, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile228, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile229, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile230, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(readStatusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(readStatusTile231, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile232, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile234, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile233, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile235, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile236, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile237, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(readStatusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(readStatusTile238, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile239, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile241, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile240, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile242, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile243, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile244, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(readStatusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(readStatusTile245, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile246, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile248, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile247, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile249, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile250, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile251, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(readStatusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(readStatusTile252, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile253, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile255, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile254, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile256, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile257, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile258, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(readStatusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(readStatusTile259, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile260, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile262, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile261, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile263, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile264, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile265, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(readStatusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(readStatusTile266, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile267, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile269, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile268, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile270, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile271, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile272, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(readStatusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(readStatusTile273, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile274, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile276, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile275, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile277, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile278, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile279, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(readStatusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(readStatusTile280, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile281, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile283, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile282, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile284, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile285, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile286, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(readStatusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(readStatusTile287, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile288, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile290, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile289, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile291, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile292, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile293, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(readStatusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(readStatusTile294, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile295, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile297, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile296, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile298, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile299, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile300, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(readStatusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(readStatusTile301, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile302, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile304, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile303, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile305, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile306, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile307, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(readStatusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(readStatusTile308, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile309, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile311, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile310, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile312, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile313, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile314, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(readStatusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(readStatusTile315, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile316, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile318, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile317, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile319, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile320, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile321, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(readStatusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(readStatusTile322, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile323, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile325, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile324, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile326, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile327, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile328, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(readStatusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(readStatusTile329, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile330, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile332, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile331, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile333, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile334, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile335, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(readStatusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(readStatusTile336, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile337, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile339, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile338, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile340, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile341, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile342, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(readStatusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(readStatusTile343, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile344, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile346, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile345, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile347, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile348, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile349, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(readStatusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(readStatusTile350, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile351, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile353, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile352, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile354, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile355, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile356, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(readStatusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(readStatusTile357, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile358, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile360, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile359, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile361, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile362, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile363, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(readStatusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(readStatusTile364, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readStatusTile365, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        readStatusPanelLayout.setVerticalGroup(
            readStatusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(readStatusPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(readStatusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(readStatusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(readStatusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(readStatusPanelLayout.createSequentialGroup()
                                .addComponent(readStatusTile364, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(readStatusTile365, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(130, 130, 130))
                            .addGroup(readStatusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(readStatusPanelLayout.createSequentialGroup()
                                    .addComponent(readStatusTile357, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile358, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile360, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile359, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile361, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile362, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile363, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(readStatusPanelLayout.createSequentialGroup()
                                    .addComponent(readStatusTile350, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile351, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile353, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile352, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile354, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile355, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile356, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(readStatusPanelLayout.createSequentialGroup()
                                    .addComponent(readStatusTile343, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile344, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile346, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile345, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile347, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile348, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile349, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(readStatusPanelLayout.createSequentialGroup()
                                    .addComponent(readStatusTile336, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile337, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile339, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile338, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile340, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile341, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile342, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(readStatusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(readStatusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(readStatusPanelLayout.createSequentialGroup()
                                    .addComponent(readStatusTile329, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile330, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile332, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile331, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile333, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile334, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile335, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(readStatusPanelLayout.createSequentialGroup()
                                    .addComponent(readStatusTile322, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile323, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile325, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile324, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile326, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile327, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile328, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(readStatusPanelLayout.createSequentialGroup()
                                    .addComponent(readStatusTile315, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile316, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile318, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile317, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile319, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile320, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile321, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(readStatusPanelLayout.createSequentialGroup()
                                    .addComponent(readStatusTile308, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile309, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile311, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile310, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile312, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile313, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile314, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(readStatusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(readStatusPanelLayout.createSequentialGroup()
                                    .addComponent(readStatusTile301, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile302, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile304, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile303, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile305, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile306, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile307, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(readStatusPanelLayout.createSequentialGroup()
                                    .addComponent(readStatusTile294, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile295, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile297, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile296, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile298, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile299, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile300, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(readStatusPanelLayout.createSequentialGroup()
                                    .addComponent(readStatusTile287, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile288, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile290, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile289, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile291, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile292, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile293, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(readStatusPanelLayout.createSequentialGroup()
                                    .addComponent(readStatusTile280, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile281, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile283, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile282, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile284, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile285, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile286, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(readStatusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(readStatusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(readStatusPanelLayout.createSequentialGroup()
                                    .addComponent(readStatusTile273, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile274, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile276, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile275, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile277, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile278, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile279, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(readStatusPanelLayout.createSequentialGroup()
                                    .addComponent(readStatusTile266, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile267, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile269, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile268, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile270, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile271, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile272, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(readStatusPanelLayout.createSequentialGroup()
                                    .addComponent(readStatusTile259, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile260, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile262, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile261, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile263, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile264, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile265, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(readStatusPanelLayout.createSequentialGroup()
                                    .addComponent(readStatusTile252, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile253, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile255, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile254, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile256, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile257, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile258, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(readStatusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(readStatusPanelLayout.createSequentialGroup()
                                    .addComponent(readStatusTile245, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile246, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile248, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile247, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile249, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile250, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile251, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(readStatusPanelLayout.createSequentialGroup()
                                    .addComponent(readStatusTile238, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile239, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile241, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile240, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile242, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile243, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile244, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(readStatusPanelLayout.createSequentialGroup()
                                    .addComponent(readStatusTile231, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile232, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile234, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile233, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile235, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile236, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile237, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(readStatusPanelLayout.createSequentialGroup()
                                    .addComponent(readStatusTile224, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile225, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile227, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile226, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile228, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile229, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile230, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(readStatusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(readStatusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(readStatusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(readStatusPanelLayout.createSequentialGroup()
                                    .addComponent(readStatusTile217, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile218, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile220, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile219, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile221, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile222, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile223, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(readStatusPanelLayout.createSequentialGroup()
                                    .addComponent(readStatusTile210, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile211, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile213, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile212, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile214, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile215, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile216, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(readStatusPanelLayout.createSequentialGroup()
                                    .addComponent(readStatusTile203, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile204, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile206, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile205, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile207, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile208, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile209, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(readStatusPanelLayout.createSequentialGroup()
                                    .addComponent(readStatusTile196, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile197, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile199, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile198, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile200, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile201, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile202, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(readStatusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(readStatusPanelLayout.createSequentialGroup()
                                    .addComponent(readStatusTile189, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile190, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile192, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile191, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile193, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile194, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile195, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(readStatusPanelLayout.createSequentialGroup()
                                    .addComponent(readStatusTile182, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile183, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile185, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile184, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile186, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile187, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile188, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(readStatusPanelLayout.createSequentialGroup()
                                    .addComponent(readStatusTile175, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile176, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile178, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile177, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile179, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile180, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile181, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(readStatusPanelLayout.createSequentialGroup()
                                    .addComponent(readStatusTile168, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile169, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile171, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile170, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile172, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile173, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile174, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(readStatusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(readStatusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(readStatusPanelLayout.createSequentialGroup()
                                    .addComponent(readStatusTile161, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile162, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile164, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile163, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile165, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile166, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile167, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(readStatusPanelLayout.createSequentialGroup()
                                    .addComponent(readStatusTile154, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile155, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile157, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile156, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile158, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile159, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile160, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(readStatusPanelLayout.createSequentialGroup()
                                    .addComponent(readStatusTile147, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile148, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile150, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile149, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile151, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile152, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile153, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(readStatusPanelLayout.createSequentialGroup()
                                    .addComponent(readStatusTile140, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile141, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile143, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile142, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile144, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile145, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile146, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(readStatusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(readStatusPanelLayout.createSequentialGroup()
                                    .addComponent(readStatusTile133, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile134, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile136, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile135, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile137, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile138, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile139, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(readStatusPanelLayout.createSequentialGroup()
                                    .addComponent(readStatusTile126, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile127, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile129, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile128, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile130, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile131, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile132, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(readStatusPanelLayout.createSequentialGroup()
                                    .addComponent(readStatusTile119, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile120, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile122, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile121, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile123, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile124, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile125, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(readStatusPanelLayout.createSequentialGroup()
                                    .addComponent(readStatusTile112, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile113, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile115, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile114, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile116, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile117, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile118, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(readStatusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(readStatusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(readStatusPanelLayout.createSequentialGroup()
                                    .addComponent(readStatusTile105, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile106, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile108, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile107, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile109, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile110, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile111, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(readStatusPanelLayout.createSequentialGroup()
                                    .addComponent(readStatusTile98, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile99, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile101, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile100, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile102, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile103, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile104, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(readStatusPanelLayout.createSequentialGroup()
                                    .addComponent(readStatusTile91, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile92, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile94, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile93, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile95, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile96, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile97, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(readStatusPanelLayout.createSequentialGroup()
                                    .addComponent(readStatusTile84, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile85, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile87, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile86, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile88, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile89, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile90, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(readStatusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(readStatusPanelLayout.createSequentialGroup()
                                    .addComponent(readStatusTile77, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile78, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile80, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile79, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile81, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile82, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile83, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(readStatusPanelLayout.createSequentialGroup()
                                    .addComponent(readStatusTile70, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile71, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile73, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile72, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile74, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile75, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile76, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(readStatusPanelLayout.createSequentialGroup()
                                    .addComponent(readStatusTile63, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile64, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile66, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile65, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile67, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile68, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile69, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(readStatusPanelLayout.createSequentialGroup()
                                    .addComponent(readStatusTile56, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile57, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile59, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile58, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile60, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile61, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile62, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(readStatusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(readStatusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(readStatusPanelLayout.createSequentialGroup()
                                    .addComponent(readStatusTile49, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile50, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile52, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile51, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile53, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile54, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile55, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(readStatusPanelLayout.createSequentialGroup()
                                    .addComponent(readStatusTile42, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile43, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile45, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile44, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile46, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile47, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile48, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(readStatusPanelLayout.createSequentialGroup()
                                    .addComponent(readStatusTile35, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile36, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile38, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile37, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile39, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile40, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile41, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(readStatusPanelLayout.createSequentialGroup()
                                    .addComponent(readStatusTile28, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile29, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile31, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile30, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile32, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile33, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile34, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(readStatusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(readStatusPanelLayout.createSequentialGroup()
                                    .addComponent(readStatusTile21, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile22, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile24, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile23, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile25, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile26, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile27, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(readStatusPanelLayout.createSequentialGroup()
                                    .addComponent(readStatusTile14, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile15, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile17, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile16, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile18, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile19, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile20, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(readStatusPanelLayout.createSequentialGroup()
                                    .addComponent(readStatusTile7, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile8, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile10, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile9, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile11, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile12, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile13, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(readStatusPanelLayout.createSequentialGroup()
                                    .addComponent(readStatusTile, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile5, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(readStatusTile6, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Nokia Pure Headline Ultra Light", 0, 14)); // NOI18N
        jLabel1.setText("Mon");

        jLabel4.setFont(new java.awt.Font("Nokia Pure Headline Ultra Light", 0, 14)); // NOI18N
        jLabel4.setText("Wed");

        jLabel7.setFont(new java.awt.Font("Nokia Pure Headline Ultra Light", 0, 14)); // NOI18N
        jLabel7.setText("Fri");

        bottomSpacer.setAlignmentX(0.0F);
        bottomSpacer.setAlignmentY(0.0F);
        bottomSpacer.setDoubleBuffered(false);

        months.setFont(new java.awt.Font("Nokia Pure Headline Ultra Light", 0, 14)); // NOI18N
        months.setText("Sep");

        months1.setFont(new java.awt.Font("Nokia Pure Headline Ultra Light", 0, 14)); // NOI18N
        months1.setText("Oct");

        months2.setFont(new java.awt.Font("Nokia Pure Headline Ultra Light", 0, 14)); // NOI18N
        months2.setText("Nov");

        months3.setFont(new java.awt.Font("Nokia Pure Headline Ultra Light", 0, 14)); // NOI18N
        months3.setText("Dec");

        months4.setFont(new java.awt.Font("Nokia Pure Headline Ultra Light", 0, 14)); // NOI18N
        months4.setText("Jan");

        months5.setFont(new java.awt.Font("Nokia Pure Headline Ultra Light", 0, 14)); // NOI18N
        months5.setText("Feb");

        months6.setFont(new java.awt.Font("Nokia Pure Headline Ultra Light", 0, 14)); // NOI18N
        months6.setText("Mar");

        months7.setFont(new java.awt.Font("Nokia Pure Headline Ultra Light", 0, 14)); // NOI18N
        months7.setText("Apr");

        months8.setFont(new java.awt.Font("Nokia Pure Headline Ultra Light", 0, 14)); // NOI18N
        months8.setText("Jun");

        months9.setFont(new java.awt.Font("Nokia Pure Headline Ultra Light", 0, 14)); // NOI18N
        months9.setText("Jul");

        months10.setFont(new java.awt.Font("Nokia Pure Headline Ultra Light", 0, 14)); // NOI18N
        months10.setText("Aug");

        months11.setFont(new java.awt.Font("Nokia Pure Headline Ultra Light", 0, 14)); // NOI18N
        months11.setText("May");

        months12.setFont(new java.awt.Font("Nokia Pure Headline Ultra Light", 0, 14)); // NOI18N
        months12.setText("Sep");

        javax.swing.GroupLayout bottomSpacerLayout = new javax.swing.GroupLayout(bottomSpacer);
        bottomSpacer.setLayout(bottomSpacerLayout);
        bottomSpacerLayout.setHorizontalGroup(
            bottomSpacerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bottomSpacerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(months)
                .addGap(84, 84, 84)
                .addComponent(months1)
                .addGap(84, 84, 84)
                .addComponent(months2)
                .addGap(81, 81, 81)
                .addComponent(months3)
                .addGap(79, 79, 79)
                .addComponent(months4)
                .addGap(88, 88, 88)
                .addComponent(months5)
                .addGap(81, 81, 81)
                .addComponent(months6)
                .addGap(84, 84, 84)
                .addComponent(months7)
                .addGap(84, 84, 84)
                .addComponent(months11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(months8)
                .addGap(110, 110, 110)
                .addComponent(months9)
                .addGap(86, 86, 86)
                .addComponent(months10)
                .addGap(82, 82, 82)
                .addComponent(months12)
                .addGap(56, 56, 56))
        );
        bottomSpacerLayout.setVerticalGroup(
            bottomSpacerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bottomSpacerLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(bottomSpacerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(months)
                    .addComponent(months1)
                    .addComponent(months2)
                    .addComponent(months3)
                    .addComponent(months4)
                    .addComponent(months5)
                    .addComponent(months6)
                    .addComponent(months7)
                    .addComponent(months8)
                    .addComponent(months9)
                    .addComponent(months10)
                    .addComponent(months11)
                    .addComponent(months12))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout libraryTabbackupLayout = new javax.swing.GroupLayout(libraryTabbackup);
        libraryTabbackup.setLayout(libraryTabbackupLayout);
        libraryTabbackupLayout.setHorizontalGroup(
            libraryTabbackupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(libraryTabbackupLayout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addGroup(libraryTabbackupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(libraryTabbackupLayout.createSequentialGroup()
                        .addGroup(libraryTabbackupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(bookDescriptionTileOne, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(bookTile1))
                        .addGap(42, 42, 42)
                        .addGroup(libraryTabbackupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(bookTile2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(bookDescriptionTileOne1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(32, 32, 32)
                        .addGroup(libraryTabbackupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(bookTile3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(bookDescriptionTileOne2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(44, 44, 44)
                        .addGroup(libraryTabbackupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, libraryTabbackupLayout.createSequentialGroup()
                                .addComponent(bookTile4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(1, 1, 1))
                            .addGroup(libraryTabbackupLayout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(bookDescriptionTileOne3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addComponent(moreBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 64, Short.MAX_VALUE))
                    .addGroup(libraryTabbackupLayout.createSequentialGroup()
                        .addGroup(libraryTabbackupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(bottomSpacer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(readStatusPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(libraryTabbackupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel4)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        libraryTabbackupLayout.setVerticalGroup(
            libraryTabbackupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(libraryTabbackupLayout.createSequentialGroup()
                .addGroup(libraryTabbackupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(libraryTabbackupLayout.createSequentialGroup()
                        .addGap(74, 74, 74)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(51, 51, 51)
                        .addComponent(moreBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, libraryTabbackupLayout.createSequentialGroup()
                        .addContainerGap(98, Short.MAX_VALUE)
                        .addGroup(libraryTabbackupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(libraryTabbackupLayout.createSequentialGroup()
                                .addGroup(libraryTabbackupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(bookTile1, javax.swing.GroupLayout.PREFERRED_SIZE, 449, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(bookTile3, javax.swing.GroupLayout.PREFERRED_SIZE, 449, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(bookTile4, javax.swing.GroupLayout.PREFERRED_SIZE, 449, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(libraryTabbackupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(bookDescriptionTileOne, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(bookDescriptionTileOne2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(bookDescriptionTileOne3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(libraryTabbackupLayout.createSequentialGroup()
                                .addComponent(bookTile2, javax.swing.GroupLayout.PREFERRED_SIZE, 449, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(bookDescriptionTileOne1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(44, 44, 44)))
                .addGroup(libraryTabbackupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(libraryTabbackupLayout.createSequentialGroup()
                        .addComponent(readStatusPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 74, Short.MAX_VALUE)
                        .addComponent(bottomSpacer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, libraryTabbackupLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(32, 32, 32)
                        .addComponent(jLabel4)
                        .addGap(31, 31, 31)
                        .addComponent(jLabel7)
                        .addGap(150, 150, 150))))
        );

        tabs.addTab("Search", libraryTabbackup);

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1580, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 975, Short.MAX_VALUE)
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

    private void moreBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_moreBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_moreBtnActionPerformed

    private void moreBtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_moreBtn1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_moreBtn1ActionPerformed

    
 
    
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
    private javax.swing.JButton audiobookBtn;
    private javax.swing.JLabel audiobookLabel;
    private javax.swing.JLabel bibleBtnLabel;
    private javax.swing.JPanel bibleTab;
    private javax.swing.JLabel biblestudyTitle;
    private javax.swing.JButton boldBtn;
    private javax.swing.JComboBox<String> bookChooser;
    private javax.swing.JComboBox<String> bookChooserDropDown;
    private javax.swing.JPanel bookDescriptionTileOne;
    private javax.swing.JPanel bookDescriptionTileOne1;
    private javax.swing.JPanel bookDescriptionTileOne2;
    private javax.swing.JPanel bookDescriptionTileOne3;
    private javax.swing.JPanel bookDescriptionTileOne4;
    private javax.swing.JPanel bookDescriptionTileOne5;
    private javax.swing.JPanel bookDescriptionTileOne6;
    private javax.swing.JPanel bookDescriptionTileOne7;
    private javax.swing.JButton bookTile1;
    private javax.swing.JButton bookTile2;
    private javax.swing.JButton bookTile3;
    private javax.swing.JButton bookTile4;
    private javax.swing.JButton bookTile5;
    private javax.swing.JButton bookTile6;
    private javax.swing.JButton bookTile7;
    private javax.swing.JButton bookTile8;
    private javax.swing.JButton bookmarkBtn;
    private javax.swing.JLabel bookmarksBtn;
    private javax.swing.JPanel bottomBar;
    private javax.swing.JPanel bottomSpacer;
    private javax.swing.JComboBox<String> chapterChooser;
    private javax.swing.JButton closeBtn;
    private javax.swing.JButton cmntrsBtn;
    private javax.swing.JLabel cmntrsBtnLabel;
    private javax.swing.JPanel cmtrsTabPanel;
    private javax.swing.JTabbedPane cmtryTabbedPanel;
    private javax.swing.JSlider fontSizeSlider;
    private javax.swing.JButton highlightBtn;
    private javax.swing.JButton homeBtn;
    private javax.swing.JLabel homeBtnLabel;
    private javax.swing.JButton hostJoinBtn;
    private javax.swing.JLabel hostJoinBtnLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JButton journalBtn;
    private javax.swing.JButton libraryBtn;
    private javax.swing.JPanel libraryTab;
    private javax.swing.JPanel libraryTabbackup;
    private javax.swing.JLayeredPane mainPanel_layered;
    private javax.swing.JTextArea mainTextArea;
    private javax.swing.JScrollPane mainTextScrollPanel;
    private javax.swing.JButton minimizeBtn;
    private javax.swing.JLabel months;
    private javax.swing.JLabel months1;
    private javax.swing.JLabel months10;
    private javax.swing.JLabel months11;
    private javax.swing.JLabel months12;
    private javax.swing.JLabel months13;
    private javax.swing.JLabel months14;
    private javax.swing.JLabel months15;
    private javax.swing.JLabel months16;
    private javax.swing.JLabel months17;
    private javax.swing.JLabel months18;
    private javax.swing.JLabel months19;
    private javax.swing.JLabel months2;
    private javax.swing.JLabel months20;
    private javax.swing.JLabel months21;
    private javax.swing.JLabel months22;
    private javax.swing.JLabel months23;
    private javax.swing.JLabel months24;
    private javax.swing.JLabel months25;
    private javax.swing.JLabel months3;
    private javax.swing.JLabel months4;
    private javax.swing.JLabel months5;
    private javax.swing.JLabel months6;
    private javax.swing.JLabel months7;
    private javax.swing.JLabel months8;
    private javax.swing.JLabel months9;
    private javax.swing.JButton moreBtn;
    private javax.swing.JButton moreBtn1;
    private javax.swing.JPanel navBar;
    private javax.swing.JPanel nodesTabPanel;
    private javax.swing.JLabel notesBtnLabel;
    private javax.swing.JTextArea notesInput;
    private javax.swing.JLayeredPane notesTabLayers;
    private javax.swing.JPanel notesTabPanel;
    private javax.swing.JPanel readStatusPanel;
    private javax.swing.JPanel readStatusPanel1;
    private javax.swing.JPanel readStatusPanel2;
    private javax.swing.JPanel readStatusPanel3;
    private javax.swing.JButton readStatusTile;
    private javax.swing.JButton readStatusTile1;
    private javax.swing.JButton readStatusTile10;
    private javax.swing.JButton readStatusTile100;
    private javax.swing.JButton readStatusTile1000;
    private javax.swing.JButton readStatusTile1001;
    private javax.swing.JButton readStatusTile1002;
    private javax.swing.JButton readStatusTile1003;
    private javax.swing.JButton readStatusTile1004;
    private javax.swing.JButton readStatusTile1005;
    private javax.swing.JButton readStatusTile1006;
    private javax.swing.JButton readStatusTile1007;
    private javax.swing.JButton readStatusTile1008;
    private javax.swing.JButton readStatusTile1009;
    private javax.swing.JButton readStatusTile101;
    private javax.swing.JButton readStatusTile1010;
    private javax.swing.JButton readStatusTile1011;
    private javax.swing.JButton readStatusTile1012;
    private javax.swing.JButton readStatusTile1013;
    private javax.swing.JButton readStatusTile1014;
    private javax.swing.JButton readStatusTile1015;
    private javax.swing.JButton readStatusTile1016;
    private javax.swing.JButton readStatusTile1017;
    private javax.swing.JButton readStatusTile1018;
    private javax.swing.JButton readStatusTile1019;
    private javax.swing.JButton readStatusTile102;
    private javax.swing.JButton readStatusTile1020;
    private javax.swing.JButton readStatusTile1021;
    private javax.swing.JButton readStatusTile1022;
    private javax.swing.JButton readStatusTile1023;
    private javax.swing.JButton readStatusTile1024;
    private javax.swing.JButton readStatusTile1025;
    private javax.swing.JButton readStatusTile1026;
    private javax.swing.JButton readStatusTile1027;
    private javax.swing.JButton readStatusTile1028;
    private javax.swing.JButton readStatusTile1029;
    private javax.swing.JButton readStatusTile103;
    private javax.swing.JButton readStatusTile1030;
    private javax.swing.JButton readStatusTile1031;
    private javax.swing.JButton readStatusTile1032;
    private javax.swing.JButton readStatusTile1033;
    private javax.swing.JButton readStatusTile1034;
    private javax.swing.JButton readStatusTile1035;
    private javax.swing.JButton readStatusTile1036;
    private javax.swing.JButton readStatusTile1037;
    private javax.swing.JButton readStatusTile1038;
    private javax.swing.JButton readStatusTile1039;
    private javax.swing.JButton readStatusTile104;
    private javax.swing.JButton readStatusTile1040;
    private javax.swing.JButton readStatusTile1041;
    private javax.swing.JButton readStatusTile1042;
    private javax.swing.JButton readStatusTile1043;
    private javax.swing.JButton readStatusTile1044;
    private javax.swing.JButton readStatusTile1045;
    private javax.swing.JButton readStatusTile1046;
    private javax.swing.JButton readStatusTile1047;
    private javax.swing.JButton readStatusTile1048;
    private javax.swing.JButton readStatusTile1049;
    private javax.swing.JButton readStatusTile105;
    private javax.swing.JButton readStatusTile1050;
    private javax.swing.JButton readStatusTile1051;
    private javax.swing.JButton readStatusTile1052;
    private javax.swing.JButton readStatusTile1053;
    private javax.swing.JButton readStatusTile1054;
    private javax.swing.JButton readStatusTile1055;
    private javax.swing.JButton readStatusTile1056;
    private javax.swing.JButton readStatusTile1057;
    private javax.swing.JButton readStatusTile1058;
    private javax.swing.JButton readStatusTile1059;
    private javax.swing.JButton readStatusTile106;
    private javax.swing.JButton readStatusTile1060;
    private javax.swing.JButton readStatusTile1061;
    private javax.swing.JButton readStatusTile1062;
    private javax.swing.JButton readStatusTile1063;
    private javax.swing.JButton readStatusTile1064;
    private javax.swing.JButton readStatusTile1065;
    private javax.swing.JButton readStatusTile1066;
    private javax.swing.JButton readStatusTile1067;
    private javax.swing.JButton readStatusTile1068;
    private javax.swing.JButton readStatusTile1069;
    private javax.swing.JButton readStatusTile107;
    private javax.swing.JButton readStatusTile1070;
    private javax.swing.JButton readStatusTile1071;
    private javax.swing.JButton readStatusTile1072;
    private javax.swing.JButton readStatusTile1073;
    private javax.swing.JButton readStatusTile1074;
    private javax.swing.JButton readStatusTile1075;
    private javax.swing.JButton readStatusTile1076;
    private javax.swing.JButton readStatusTile1077;
    private javax.swing.JButton readStatusTile1078;
    private javax.swing.JButton readStatusTile1079;
    private javax.swing.JButton readStatusTile108;
    private javax.swing.JButton readStatusTile1080;
    private javax.swing.JButton readStatusTile1081;
    private javax.swing.JButton readStatusTile1082;
    private javax.swing.JButton readStatusTile1083;
    private javax.swing.JButton readStatusTile1084;
    private javax.swing.JButton readStatusTile1085;
    private javax.swing.JButton readStatusTile1086;
    private javax.swing.JButton readStatusTile1087;
    private javax.swing.JButton readStatusTile1088;
    private javax.swing.JButton readStatusTile1089;
    private javax.swing.JButton readStatusTile109;
    private javax.swing.JButton readStatusTile1090;
    private javax.swing.JButton readStatusTile1091;
    private javax.swing.JButton readStatusTile1092;
    private javax.swing.JButton readStatusTile1093;
    private javax.swing.JButton readStatusTile1094;
    private javax.swing.JButton readStatusTile1095;
    private javax.swing.JButton readStatusTile1096;
    private javax.swing.JButton readStatusTile1097;
    private javax.swing.JButton readStatusTile1098;
    private javax.swing.JButton readStatusTile1099;
    private javax.swing.JButton readStatusTile11;
    private javax.swing.JButton readStatusTile110;
    private javax.swing.JButton readStatusTile1100;
    private javax.swing.JButton readStatusTile1101;
    private javax.swing.JButton readStatusTile1102;
    private javax.swing.JButton readStatusTile1103;
    private javax.swing.JButton readStatusTile1104;
    private javax.swing.JButton readStatusTile1105;
    private javax.swing.JButton readStatusTile1106;
    private javax.swing.JButton readStatusTile1107;
    private javax.swing.JButton readStatusTile1108;
    private javax.swing.JButton readStatusTile1109;
    private javax.swing.JButton readStatusTile111;
    private javax.swing.JButton readStatusTile1110;
    private javax.swing.JButton readStatusTile1111;
    private javax.swing.JButton readStatusTile1112;
    private javax.swing.JButton readStatusTile1113;
    private javax.swing.JButton readStatusTile1114;
    private javax.swing.JButton readStatusTile1115;
    private javax.swing.JButton readStatusTile1116;
    private javax.swing.JButton readStatusTile1117;
    private javax.swing.JButton readStatusTile1118;
    private javax.swing.JButton readStatusTile1119;
    private javax.swing.JButton readStatusTile112;
    private javax.swing.JButton readStatusTile1120;
    private javax.swing.JButton readStatusTile1121;
    private javax.swing.JButton readStatusTile1122;
    private javax.swing.JButton readStatusTile1123;
    private javax.swing.JButton readStatusTile1124;
    private javax.swing.JButton readStatusTile1125;
    private javax.swing.JButton readStatusTile1126;
    private javax.swing.JButton readStatusTile1127;
    private javax.swing.JButton readStatusTile1128;
    private javax.swing.JButton readStatusTile1129;
    private javax.swing.JButton readStatusTile113;
    private javax.swing.JButton readStatusTile1130;
    private javax.swing.JButton readStatusTile1131;
    private javax.swing.JButton readStatusTile1132;
    private javax.swing.JButton readStatusTile1133;
    private javax.swing.JButton readStatusTile1134;
    private javax.swing.JButton readStatusTile1135;
    private javax.swing.JButton readStatusTile1136;
    private javax.swing.JButton readStatusTile1137;
    private javax.swing.JButton readStatusTile1138;
    private javax.swing.JButton readStatusTile1139;
    private javax.swing.JButton readStatusTile114;
    private javax.swing.JButton readStatusTile1140;
    private javax.swing.JButton readStatusTile1141;
    private javax.swing.JButton readStatusTile1142;
    private javax.swing.JButton readStatusTile1143;
    private javax.swing.JButton readStatusTile1144;
    private javax.swing.JButton readStatusTile1145;
    private javax.swing.JButton readStatusTile1146;
    private javax.swing.JButton readStatusTile1147;
    private javax.swing.JButton readStatusTile1148;
    private javax.swing.JButton readStatusTile1149;
    private javax.swing.JButton readStatusTile115;
    private javax.swing.JButton readStatusTile1150;
    private javax.swing.JButton readStatusTile1151;
    private javax.swing.JButton readStatusTile1152;
    private javax.swing.JButton readStatusTile1153;
    private javax.swing.JButton readStatusTile1154;
    private javax.swing.JButton readStatusTile1155;
    private javax.swing.JButton readStatusTile1156;
    private javax.swing.JButton readStatusTile1157;
    private javax.swing.JButton readStatusTile1158;
    private javax.swing.JButton readStatusTile1159;
    private javax.swing.JButton readStatusTile116;
    private javax.swing.JButton readStatusTile1160;
    private javax.swing.JButton readStatusTile1161;
    private javax.swing.JButton readStatusTile1162;
    private javax.swing.JButton readStatusTile1163;
    private javax.swing.JButton readStatusTile1164;
    private javax.swing.JButton readStatusTile1165;
    private javax.swing.JButton readStatusTile1166;
    private javax.swing.JButton readStatusTile1167;
    private javax.swing.JButton readStatusTile1168;
    private javax.swing.JButton readStatusTile1169;
    private javax.swing.JButton readStatusTile117;
    private javax.swing.JButton readStatusTile1170;
    private javax.swing.JButton readStatusTile1171;
    private javax.swing.JButton readStatusTile1172;
    private javax.swing.JButton readStatusTile1173;
    private javax.swing.JButton readStatusTile1174;
    private javax.swing.JButton readStatusTile1175;
    private javax.swing.JButton readStatusTile1176;
    private javax.swing.JButton readStatusTile1177;
    private javax.swing.JButton readStatusTile1178;
    private javax.swing.JButton readStatusTile1179;
    private javax.swing.JButton readStatusTile118;
    private javax.swing.JButton readStatusTile1180;
    private javax.swing.JButton readStatusTile1181;
    private javax.swing.JButton readStatusTile1182;
    private javax.swing.JButton readStatusTile1183;
    private javax.swing.JButton readStatusTile1184;
    private javax.swing.JButton readStatusTile1185;
    private javax.swing.JButton readStatusTile1186;
    private javax.swing.JButton readStatusTile1187;
    private javax.swing.JButton readStatusTile1188;
    private javax.swing.JButton readStatusTile1189;
    private javax.swing.JButton readStatusTile119;
    private javax.swing.JButton readStatusTile1190;
    private javax.swing.JButton readStatusTile1191;
    private javax.swing.JButton readStatusTile1192;
    private javax.swing.JButton readStatusTile1193;
    private javax.swing.JButton readStatusTile1194;
    private javax.swing.JButton readStatusTile1195;
    private javax.swing.JButton readStatusTile1196;
    private javax.swing.JButton readStatusTile1197;
    private javax.swing.JButton readStatusTile1198;
    private javax.swing.JButton readStatusTile1199;
    private javax.swing.JButton readStatusTile12;
    private javax.swing.JButton readStatusTile120;
    private javax.swing.JButton readStatusTile1200;
    private javax.swing.JButton readStatusTile1201;
    private javax.swing.JButton readStatusTile1202;
    private javax.swing.JButton readStatusTile1203;
    private javax.swing.JButton readStatusTile1204;
    private javax.swing.JButton readStatusTile1205;
    private javax.swing.JButton readStatusTile1206;
    private javax.swing.JButton readStatusTile1207;
    private javax.swing.JButton readStatusTile1208;
    private javax.swing.JButton readStatusTile1209;
    private javax.swing.JButton readStatusTile121;
    private javax.swing.JButton readStatusTile1210;
    private javax.swing.JButton readStatusTile1211;
    private javax.swing.JButton readStatusTile1212;
    private javax.swing.JButton readStatusTile1213;
    private javax.swing.JButton readStatusTile1214;
    private javax.swing.JButton readStatusTile1215;
    private javax.swing.JButton readStatusTile1216;
    private javax.swing.JButton readStatusTile1217;
    private javax.swing.JButton readStatusTile1218;
    private javax.swing.JButton readStatusTile1219;
    private javax.swing.JButton readStatusTile122;
    private javax.swing.JButton readStatusTile1220;
    private javax.swing.JButton readStatusTile1221;
    private javax.swing.JButton readStatusTile1222;
    private javax.swing.JButton readStatusTile1223;
    private javax.swing.JButton readStatusTile1224;
    private javax.swing.JButton readStatusTile1225;
    private javax.swing.JButton readStatusTile1226;
    private javax.swing.JButton readStatusTile1227;
    private javax.swing.JButton readStatusTile1228;
    private javax.swing.JButton readStatusTile1229;
    private javax.swing.JButton readStatusTile123;
    private javax.swing.JButton readStatusTile1230;
    private javax.swing.JButton readStatusTile1231;
    private javax.swing.JButton readStatusTile1232;
    private javax.swing.JButton readStatusTile1233;
    private javax.swing.JButton readStatusTile1234;
    private javax.swing.JButton readStatusTile1235;
    private javax.swing.JButton readStatusTile1236;
    private javax.swing.JButton readStatusTile1237;
    private javax.swing.JButton readStatusTile1238;
    private javax.swing.JButton readStatusTile1239;
    private javax.swing.JButton readStatusTile124;
    private javax.swing.JButton readStatusTile1240;
    private javax.swing.JButton readStatusTile1241;
    private javax.swing.JButton readStatusTile1242;
    private javax.swing.JButton readStatusTile1243;
    private javax.swing.JButton readStatusTile1244;
    private javax.swing.JButton readStatusTile1245;
    private javax.swing.JButton readStatusTile1246;
    private javax.swing.JButton readStatusTile1247;
    private javax.swing.JButton readStatusTile1248;
    private javax.swing.JButton readStatusTile1249;
    private javax.swing.JButton readStatusTile125;
    private javax.swing.JButton readStatusTile1250;
    private javax.swing.JButton readStatusTile1251;
    private javax.swing.JButton readStatusTile1252;
    private javax.swing.JButton readStatusTile1253;
    private javax.swing.JButton readStatusTile1254;
    private javax.swing.JButton readStatusTile1255;
    private javax.swing.JButton readStatusTile1256;
    private javax.swing.JButton readStatusTile1257;
    private javax.swing.JButton readStatusTile1258;
    private javax.swing.JButton readStatusTile1259;
    private javax.swing.JButton readStatusTile126;
    private javax.swing.JButton readStatusTile1260;
    private javax.swing.JButton readStatusTile1261;
    private javax.swing.JButton readStatusTile1262;
    private javax.swing.JButton readStatusTile1263;
    private javax.swing.JButton readStatusTile1264;
    private javax.swing.JButton readStatusTile1265;
    private javax.swing.JButton readStatusTile1266;
    private javax.swing.JButton readStatusTile1267;
    private javax.swing.JButton readStatusTile1268;
    private javax.swing.JButton readStatusTile1269;
    private javax.swing.JButton readStatusTile127;
    private javax.swing.JButton readStatusTile1270;
    private javax.swing.JButton readStatusTile1271;
    private javax.swing.JButton readStatusTile1272;
    private javax.swing.JButton readStatusTile1273;
    private javax.swing.JButton readStatusTile1274;
    private javax.swing.JButton readStatusTile1275;
    private javax.swing.JButton readStatusTile1276;
    private javax.swing.JButton readStatusTile1277;
    private javax.swing.JButton readStatusTile1278;
    private javax.swing.JButton readStatusTile1279;
    private javax.swing.JButton readStatusTile128;
    private javax.swing.JButton readStatusTile1280;
    private javax.swing.JButton readStatusTile1281;
    private javax.swing.JButton readStatusTile1282;
    private javax.swing.JButton readStatusTile1283;
    private javax.swing.JButton readStatusTile1284;
    private javax.swing.JButton readStatusTile1285;
    private javax.swing.JButton readStatusTile1286;
    private javax.swing.JButton readStatusTile1287;
    private javax.swing.JButton readStatusTile1288;
    private javax.swing.JButton readStatusTile1289;
    private javax.swing.JButton readStatusTile129;
    private javax.swing.JButton readStatusTile1290;
    private javax.swing.JButton readStatusTile1291;
    private javax.swing.JButton readStatusTile1292;
    private javax.swing.JButton readStatusTile1293;
    private javax.swing.JButton readStatusTile1294;
    private javax.swing.JButton readStatusTile1295;
    private javax.swing.JButton readStatusTile1296;
    private javax.swing.JButton readStatusTile1297;
    private javax.swing.JButton readStatusTile1298;
    private javax.swing.JButton readStatusTile1299;
    private javax.swing.JButton readStatusTile13;
    private javax.swing.JButton readStatusTile130;
    private javax.swing.JButton readStatusTile1300;
    private javax.swing.JButton readStatusTile1301;
    private javax.swing.JButton readStatusTile1302;
    private javax.swing.JButton readStatusTile1303;
    private javax.swing.JButton readStatusTile1304;
    private javax.swing.JButton readStatusTile1305;
    private javax.swing.JButton readStatusTile1306;
    private javax.swing.JButton readStatusTile1307;
    private javax.swing.JButton readStatusTile1308;
    private javax.swing.JButton readStatusTile1309;
    private javax.swing.JButton readStatusTile131;
    private javax.swing.JButton readStatusTile1310;
    private javax.swing.JButton readStatusTile1311;
    private javax.swing.JButton readStatusTile1312;
    private javax.swing.JButton readStatusTile1313;
    private javax.swing.JButton readStatusTile1314;
    private javax.swing.JButton readStatusTile1315;
    private javax.swing.JButton readStatusTile1316;
    private javax.swing.JButton readStatusTile1317;
    private javax.swing.JButton readStatusTile1318;
    private javax.swing.JButton readStatusTile1319;
    private javax.swing.JButton readStatusTile132;
    private javax.swing.JButton readStatusTile1320;
    private javax.swing.JButton readStatusTile1321;
    private javax.swing.JButton readStatusTile1322;
    private javax.swing.JButton readStatusTile1323;
    private javax.swing.JButton readStatusTile1324;
    private javax.swing.JButton readStatusTile1325;
    private javax.swing.JButton readStatusTile1326;
    private javax.swing.JButton readStatusTile1327;
    private javax.swing.JButton readStatusTile1328;
    private javax.swing.JButton readStatusTile1329;
    private javax.swing.JButton readStatusTile133;
    private javax.swing.JButton readStatusTile1330;
    private javax.swing.JButton readStatusTile1331;
    private javax.swing.JButton readStatusTile1332;
    private javax.swing.JButton readStatusTile1333;
    private javax.swing.JButton readStatusTile1334;
    private javax.swing.JButton readStatusTile1335;
    private javax.swing.JButton readStatusTile1336;
    private javax.swing.JButton readStatusTile1337;
    private javax.swing.JButton readStatusTile1338;
    private javax.swing.JButton readStatusTile1339;
    private javax.swing.JButton readStatusTile134;
    private javax.swing.JButton readStatusTile1340;
    private javax.swing.JButton readStatusTile1341;
    private javax.swing.JButton readStatusTile1342;
    private javax.swing.JButton readStatusTile1343;
    private javax.swing.JButton readStatusTile1344;
    private javax.swing.JButton readStatusTile1345;
    private javax.swing.JButton readStatusTile1346;
    private javax.swing.JButton readStatusTile1347;
    private javax.swing.JButton readStatusTile1348;
    private javax.swing.JButton readStatusTile1349;
    private javax.swing.JButton readStatusTile135;
    private javax.swing.JButton readStatusTile1350;
    private javax.swing.JButton readStatusTile1351;
    private javax.swing.JButton readStatusTile1352;
    private javax.swing.JButton readStatusTile1353;
    private javax.swing.JButton readStatusTile1354;
    private javax.swing.JButton readStatusTile1355;
    private javax.swing.JButton readStatusTile1356;
    private javax.swing.JButton readStatusTile1357;
    private javax.swing.JButton readStatusTile1358;
    private javax.swing.JButton readStatusTile1359;
    private javax.swing.JButton readStatusTile136;
    private javax.swing.JButton readStatusTile1360;
    private javax.swing.JButton readStatusTile1361;
    private javax.swing.JButton readStatusTile1362;
    private javax.swing.JButton readStatusTile1363;
    private javax.swing.JButton readStatusTile1364;
    private javax.swing.JButton readStatusTile1365;
    private javax.swing.JButton readStatusTile1366;
    private javax.swing.JButton readStatusTile1367;
    private javax.swing.JButton readStatusTile1368;
    private javax.swing.JButton readStatusTile1369;
    private javax.swing.JButton readStatusTile137;
    private javax.swing.JButton readStatusTile1370;
    private javax.swing.JButton readStatusTile1371;
    private javax.swing.JButton readStatusTile1372;
    private javax.swing.JButton readStatusTile1373;
    private javax.swing.JButton readStatusTile1374;
    private javax.swing.JButton readStatusTile1375;
    private javax.swing.JButton readStatusTile1376;
    private javax.swing.JButton readStatusTile1377;
    private javax.swing.JButton readStatusTile1378;
    private javax.swing.JButton readStatusTile1379;
    private javax.swing.JButton readStatusTile138;
    private javax.swing.JButton readStatusTile1380;
    private javax.swing.JButton readStatusTile1381;
    private javax.swing.JButton readStatusTile1382;
    private javax.swing.JButton readStatusTile1383;
    private javax.swing.JButton readStatusTile1384;
    private javax.swing.JButton readStatusTile1385;
    private javax.swing.JButton readStatusTile1386;
    private javax.swing.JButton readStatusTile1387;
    private javax.swing.JButton readStatusTile1388;
    private javax.swing.JButton readStatusTile1389;
    private javax.swing.JButton readStatusTile139;
    private javax.swing.JButton readStatusTile1390;
    private javax.swing.JButton readStatusTile1391;
    private javax.swing.JButton readStatusTile1392;
    private javax.swing.JButton readStatusTile1393;
    private javax.swing.JButton readStatusTile1394;
    private javax.swing.JButton readStatusTile1395;
    private javax.swing.JButton readStatusTile1396;
    private javax.swing.JButton readStatusTile1397;
    private javax.swing.JButton readStatusTile1398;
    private javax.swing.JButton readStatusTile1399;
    private javax.swing.JButton readStatusTile14;
    private javax.swing.JButton readStatusTile140;
    private javax.swing.JButton readStatusTile1400;
    private javax.swing.JButton readStatusTile1401;
    private javax.swing.JButton readStatusTile1402;
    private javax.swing.JButton readStatusTile1403;
    private javax.swing.JButton readStatusTile1404;
    private javax.swing.JButton readStatusTile1405;
    private javax.swing.JButton readStatusTile1406;
    private javax.swing.JButton readStatusTile1407;
    private javax.swing.JButton readStatusTile1408;
    private javax.swing.JButton readStatusTile1409;
    private javax.swing.JButton readStatusTile141;
    private javax.swing.JButton readStatusTile1410;
    private javax.swing.JButton readStatusTile1411;
    private javax.swing.JButton readStatusTile1412;
    private javax.swing.JButton readStatusTile1413;
    private javax.swing.JButton readStatusTile1414;
    private javax.swing.JButton readStatusTile1415;
    private javax.swing.JButton readStatusTile1416;
    private javax.swing.JButton readStatusTile1417;
    private javax.swing.JButton readStatusTile1418;
    private javax.swing.JButton readStatusTile1419;
    private javax.swing.JButton readStatusTile142;
    private javax.swing.JButton readStatusTile1420;
    private javax.swing.JButton readStatusTile1421;
    private javax.swing.JButton readStatusTile1422;
    private javax.swing.JButton readStatusTile1423;
    private javax.swing.JButton readStatusTile1424;
    private javax.swing.JButton readStatusTile1425;
    private javax.swing.JButton readStatusTile1426;
    private javax.swing.JButton readStatusTile1427;
    private javax.swing.JButton readStatusTile1428;
    private javax.swing.JButton readStatusTile1429;
    private javax.swing.JButton readStatusTile143;
    private javax.swing.JButton readStatusTile1430;
    private javax.swing.JButton readStatusTile1431;
    private javax.swing.JButton readStatusTile1432;
    private javax.swing.JButton readStatusTile1433;
    private javax.swing.JButton readStatusTile1434;
    private javax.swing.JButton readStatusTile1435;
    private javax.swing.JButton readStatusTile1436;
    private javax.swing.JButton readStatusTile1437;
    private javax.swing.JButton readStatusTile1438;
    private javax.swing.JButton readStatusTile1439;
    private javax.swing.JButton readStatusTile144;
    private javax.swing.JButton readStatusTile1440;
    private javax.swing.JButton readStatusTile1441;
    private javax.swing.JButton readStatusTile1442;
    private javax.swing.JButton readStatusTile1443;
    private javax.swing.JButton readStatusTile1444;
    private javax.swing.JButton readStatusTile1445;
    private javax.swing.JButton readStatusTile1446;
    private javax.swing.JButton readStatusTile1447;
    private javax.swing.JButton readStatusTile1448;
    private javax.swing.JButton readStatusTile1449;
    private javax.swing.JButton readStatusTile145;
    private javax.swing.JButton readStatusTile1450;
    private javax.swing.JButton readStatusTile1451;
    private javax.swing.JButton readStatusTile1452;
    private javax.swing.JButton readStatusTile1453;
    private javax.swing.JButton readStatusTile1454;
    private javax.swing.JButton readStatusTile1455;
    private javax.swing.JButton readStatusTile1456;
    private javax.swing.JButton readStatusTile1457;
    private javax.swing.JButton readStatusTile1458;
    private javax.swing.JButton readStatusTile1459;
    private javax.swing.JButton readStatusTile146;
    private javax.swing.JButton readStatusTile1460;
    private javax.swing.JButton readStatusTile1461;
    private javax.swing.JButton readStatusTile1462;
    private javax.swing.JButton readStatusTile1463;
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
    private javax.swing.JButton readStatusTile367;
    private javax.swing.JButton readStatusTile368;
    private javax.swing.JButton readStatusTile369;
    private javax.swing.JButton readStatusTile37;
    private javax.swing.JButton readStatusTile370;
    private javax.swing.JButton readStatusTile371;
    private javax.swing.JButton readStatusTile372;
    private javax.swing.JButton readStatusTile373;
    private javax.swing.JButton readStatusTile374;
    private javax.swing.JButton readStatusTile375;
    private javax.swing.JButton readStatusTile376;
    private javax.swing.JButton readStatusTile377;
    private javax.swing.JButton readStatusTile378;
    private javax.swing.JButton readStatusTile379;
    private javax.swing.JButton readStatusTile38;
    private javax.swing.JButton readStatusTile380;
    private javax.swing.JButton readStatusTile381;
    private javax.swing.JButton readStatusTile382;
    private javax.swing.JButton readStatusTile383;
    private javax.swing.JButton readStatusTile384;
    private javax.swing.JButton readStatusTile385;
    private javax.swing.JButton readStatusTile386;
    private javax.swing.JButton readStatusTile387;
    private javax.swing.JButton readStatusTile388;
    private javax.swing.JButton readStatusTile389;
    private javax.swing.JButton readStatusTile39;
    private javax.swing.JButton readStatusTile390;
    private javax.swing.JButton readStatusTile391;
    private javax.swing.JButton readStatusTile392;
    private javax.swing.JButton readStatusTile393;
    private javax.swing.JButton readStatusTile394;
    private javax.swing.JButton readStatusTile395;
    private javax.swing.JButton readStatusTile396;
    private javax.swing.JButton readStatusTile397;
    private javax.swing.JButton readStatusTile398;
    private javax.swing.JButton readStatusTile399;
    private javax.swing.JButton readStatusTile4;
    private javax.swing.JButton readStatusTile40;
    private javax.swing.JButton readStatusTile400;
    private javax.swing.JButton readStatusTile401;
    private javax.swing.JButton readStatusTile402;
    private javax.swing.JButton readStatusTile403;
    private javax.swing.JButton readStatusTile404;
    private javax.swing.JButton readStatusTile405;
    private javax.swing.JButton readStatusTile406;
    private javax.swing.JButton readStatusTile407;
    private javax.swing.JButton readStatusTile408;
    private javax.swing.JButton readStatusTile409;
    private javax.swing.JButton readStatusTile41;
    private javax.swing.JButton readStatusTile410;
    private javax.swing.JButton readStatusTile411;
    private javax.swing.JButton readStatusTile412;
    private javax.swing.JButton readStatusTile413;
    private javax.swing.JButton readStatusTile414;
    private javax.swing.JButton readStatusTile415;
    private javax.swing.JButton readStatusTile416;
    private javax.swing.JButton readStatusTile417;
    private javax.swing.JButton readStatusTile418;
    private javax.swing.JButton readStatusTile419;
    private javax.swing.JButton readStatusTile42;
    private javax.swing.JButton readStatusTile420;
    private javax.swing.JButton readStatusTile421;
    private javax.swing.JButton readStatusTile422;
    private javax.swing.JButton readStatusTile423;
    private javax.swing.JButton readStatusTile424;
    private javax.swing.JButton readStatusTile425;
    private javax.swing.JButton readStatusTile426;
    private javax.swing.JButton readStatusTile427;
    private javax.swing.JButton readStatusTile428;
    private javax.swing.JButton readStatusTile429;
    private javax.swing.JButton readStatusTile43;
    private javax.swing.JButton readStatusTile430;
    private javax.swing.JButton readStatusTile431;
    private javax.swing.JButton readStatusTile432;
    private javax.swing.JButton readStatusTile433;
    private javax.swing.JButton readStatusTile434;
    private javax.swing.JButton readStatusTile435;
    private javax.swing.JButton readStatusTile436;
    private javax.swing.JButton readStatusTile437;
    private javax.swing.JButton readStatusTile438;
    private javax.swing.JButton readStatusTile439;
    private javax.swing.JButton readStatusTile44;
    private javax.swing.JButton readStatusTile440;
    private javax.swing.JButton readStatusTile441;
    private javax.swing.JButton readStatusTile442;
    private javax.swing.JButton readStatusTile443;
    private javax.swing.JButton readStatusTile444;
    private javax.swing.JButton readStatusTile445;
    private javax.swing.JButton readStatusTile446;
    private javax.swing.JButton readStatusTile447;
    private javax.swing.JButton readStatusTile448;
    private javax.swing.JButton readStatusTile449;
    private javax.swing.JButton readStatusTile45;
    private javax.swing.JButton readStatusTile450;
    private javax.swing.JButton readStatusTile451;
    private javax.swing.JButton readStatusTile452;
    private javax.swing.JButton readStatusTile453;
    private javax.swing.JButton readStatusTile454;
    private javax.swing.JButton readStatusTile455;
    private javax.swing.JButton readStatusTile456;
    private javax.swing.JButton readStatusTile457;
    private javax.swing.JButton readStatusTile458;
    private javax.swing.JButton readStatusTile459;
    private javax.swing.JButton readStatusTile46;
    private javax.swing.JButton readStatusTile460;
    private javax.swing.JButton readStatusTile461;
    private javax.swing.JButton readStatusTile462;
    private javax.swing.JButton readStatusTile463;
    private javax.swing.JButton readStatusTile464;
    private javax.swing.JButton readStatusTile465;
    private javax.swing.JButton readStatusTile466;
    private javax.swing.JButton readStatusTile467;
    private javax.swing.JButton readStatusTile468;
    private javax.swing.JButton readStatusTile469;
    private javax.swing.JButton readStatusTile47;
    private javax.swing.JButton readStatusTile470;
    private javax.swing.JButton readStatusTile471;
    private javax.swing.JButton readStatusTile472;
    private javax.swing.JButton readStatusTile473;
    private javax.swing.JButton readStatusTile474;
    private javax.swing.JButton readStatusTile475;
    private javax.swing.JButton readStatusTile476;
    private javax.swing.JButton readStatusTile477;
    private javax.swing.JButton readStatusTile478;
    private javax.swing.JButton readStatusTile479;
    private javax.swing.JButton readStatusTile48;
    private javax.swing.JButton readStatusTile480;
    private javax.swing.JButton readStatusTile481;
    private javax.swing.JButton readStatusTile482;
    private javax.swing.JButton readStatusTile483;
    private javax.swing.JButton readStatusTile484;
    private javax.swing.JButton readStatusTile485;
    private javax.swing.JButton readStatusTile486;
    private javax.swing.JButton readStatusTile487;
    private javax.swing.JButton readStatusTile488;
    private javax.swing.JButton readStatusTile489;
    private javax.swing.JButton readStatusTile49;
    private javax.swing.JButton readStatusTile490;
    private javax.swing.JButton readStatusTile491;
    private javax.swing.JButton readStatusTile492;
    private javax.swing.JButton readStatusTile493;
    private javax.swing.JButton readStatusTile494;
    private javax.swing.JButton readStatusTile495;
    private javax.swing.JButton readStatusTile496;
    private javax.swing.JButton readStatusTile497;
    private javax.swing.JButton readStatusTile498;
    private javax.swing.JButton readStatusTile499;
    private javax.swing.JButton readStatusTile5;
    private javax.swing.JButton readStatusTile50;
    private javax.swing.JButton readStatusTile500;
    private javax.swing.JButton readStatusTile501;
    private javax.swing.JButton readStatusTile502;
    private javax.swing.JButton readStatusTile503;
    private javax.swing.JButton readStatusTile504;
    private javax.swing.JButton readStatusTile505;
    private javax.swing.JButton readStatusTile506;
    private javax.swing.JButton readStatusTile507;
    private javax.swing.JButton readStatusTile508;
    private javax.swing.JButton readStatusTile509;
    private javax.swing.JButton readStatusTile51;
    private javax.swing.JButton readStatusTile510;
    private javax.swing.JButton readStatusTile511;
    private javax.swing.JButton readStatusTile512;
    private javax.swing.JButton readStatusTile513;
    private javax.swing.JButton readStatusTile514;
    private javax.swing.JButton readStatusTile515;
    private javax.swing.JButton readStatusTile516;
    private javax.swing.JButton readStatusTile517;
    private javax.swing.JButton readStatusTile518;
    private javax.swing.JButton readStatusTile519;
    private javax.swing.JButton readStatusTile52;
    private javax.swing.JButton readStatusTile520;
    private javax.swing.JButton readStatusTile521;
    private javax.swing.JButton readStatusTile522;
    private javax.swing.JButton readStatusTile523;
    private javax.swing.JButton readStatusTile524;
    private javax.swing.JButton readStatusTile525;
    private javax.swing.JButton readStatusTile526;
    private javax.swing.JButton readStatusTile527;
    private javax.swing.JButton readStatusTile528;
    private javax.swing.JButton readStatusTile529;
    private javax.swing.JButton readStatusTile53;
    private javax.swing.JButton readStatusTile530;
    private javax.swing.JButton readStatusTile531;
    private javax.swing.JButton readStatusTile532;
    private javax.swing.JButton readStatusTile533;
    private javax.swing.JButton readStatusTile534;
    private javax.swing.JButton readStatusTile535;
    private javax.swing.JButton readStatusTile536;
    private javax.swing.JButton readStatusTile537;
    private javax.swing.JButton readStatusTile538;
    private javax.swing.JButton readStatusTile539;
    private javax.swing.JButton readStatusTile54;
    private javax.swing.JButton readStatusTile540;
    private javax.swing.JButton readStatusTile541;
    private javax.swing.JButton readStatusTile542;
    private javax.swing.JButton readStatusTile543;
    private javax.swing.JButton readStatusTile544;
    private javax.swing.JButton readStatusTile545;
    private javax.swing.JButton readStatusTile546;
    private javax.swing.JButton readStatusTile547;
    private javax.swing.JButton readStatusTile548;
    private javax.swing.JButton readStatusTile549;
    private javax.swing.JButton readStatusTile55;
    private javax.swing.JButton readStatusTile550;
    private javax.swing.JButton readStatusTile551;
    private javax.swing.JButton readStatusTile552;
    private javax.swing.JButton readStatusTile553;
    private javax.swing.JButton readStatusTile554;
    private javax.swing.JButton readStatusTile555;
    private javax.swing.JButton readStatusTile556;
    private javax.swing.JButton readStatusTile557;
    private javax.swing.JButton readStatusTile558;
    private javax.swing.JButton readStatusTile559;
    private javax.swing.JButton readStatusTile56;
    private javax.swing.JButton readStatusTile560;
    private javax.swing.JButton readStatusTile561;
    private javax.swing.JButton readStatusTile562;
    private javax.swing.JButton readStatusTile563;
    private javax.swing.JButton readStatusTile564;
    private javax.swing.JButton readStatusTile565;
    private javax.swing.JButton readStatusTile566;
    private javax.swing.JButton readStatusTile567;
    private javax.swing.JButton readStatusTile568;
    private javax.swing.JButton readStatusTile569;
    private javax.swing.JButton readStatusTile57;
    private javax.swing.JButton readStatusTile570;
    private javax.swing.JButton readStatusTile571;
    private javax.swing.JButton readStatusTile572;
    private javax.swing.JButton readStatusTile573;
    private javax.swing.JButton readStatusTile574;
    private javax.swing.JButton readStatusTile575;
    private javax.swing.JButton readStatusTile576;
    private javax.swing.JButton readStatusTile577;
    private javax.swing.JButton readStatusTile578;
    private javax.swing.JButton readStatusTile579;
    private javax.swing.JButton readStatusTile58;
    private javax.swing.JButton readStatusTile580;
    private javax.swing.JButton readStatusTile581;
    private javax.swing.JButton readStatusTile582;
    private javax.swing.JButton readStatusTile583;
    private javax.swing.JButton readStatusTile584;
    private javax.swing.JButton readStatusTile585;
    private javax.swing.JButton readStatusTile586;
    private javax.swing.JButton readStatusTile587;
    private javax.swing.JButton readStatusTile588;
    private javax.swing.JButton readStatusTile589;
    private javax.swing.JButton readStatusTile59;
    private javax.swing.JButton readStatusTile590;
    private javax.swing.JButton readStatusTile591;
    private javax.swing.JButton readStatusTile592;
    private javax.swing.JButton readStatusTile593;
    private javax.swing.JButton readStatusTile594;
    private javax.swing.JButton readStatusTile595;
    private javax.swing.JButton readStatusTile596;
    private javax.swing.JButton readStatusTile597;
    private javax.swing.JButton readStatusTile598;
    private javax.swing.JButton readStatusTile599;
    private javax.swing.JButton readStatusTile6;
    private javax.swing.JButton readStatusTile60;
    private javax.swing.JButton readStatusTile600;
    private javax.swing.JButton readStatusTile601;
    private javax.swing.JButton readStatusTile602;
    private javax.swing.JButton readStatusTile603;
    private javax.swing.JButton readStatusTile604;
    private javax.swing.JButton readStatusTile605;
    private javax.swing.JButton readStatusTile606;
    private javax.swing.JButton readStatusTile607;
    private javax.swing.JButton readStatusTile608;
    private javax.swing.JButton readStatusTile609;
    private javax.swing.JButton readStatusTile61;
    private javax.swing.JButton readStatusTile610;
    private javax.swing.JButton readStatusTile611;
    private javax.swing.JButton readStatusTile612;
    private javax.swing.JButton readStatusTile613;
    private javax.swing.JButton readStatusTile614;
    private javax.swing.JButton readStatusTile615;
    private javax.swing.JButton readStatusTile616;
    private javax.swing.JButton readStatusTile617;
    private javax.swing.JButton readStatusTile618;
    private javax.swing.JButton readStatusTile619;
    private javax.swing.JButton readStatusTile62;
    private javax.swing.JButton readStatusTile620;
    private javax.swing.JButton readStatusTile621;
    private javax.swing.JButton readStatusTile622;
    private javax.swing.JButton readStatusTile623;
    private javax.swing.JButton readStatusTile624;
    private javax.swing.JButton readStatusTile625;
    private javax.swing.JButton readStatusTile626;
    private javax.swing.JButton readStatusTile627;
    private javax.swing.JButton readStatusTile628;
    private javax.swing.JButton readStatusTile629;
    private javax.swing.JButton readStatusTile63;
    private javax.swing.JButton readStatusTile630;
    private javax.swing.JButton readStatusTile631;
    private javax.swing.JButton readStatusTile632;
    private javax.swing.JButton readStatusTile633;
    private javax.swing.JButton readStatusTile634;
    private javax.swing.JButton readStatusTile635;
    private javax.swing.JButton readStatusTile636;
    private javax.swing.JButton readStatusTile637;
    private javax.swing.JButton readStatusTile638;
    private javax.swing.JButton readStatusTile639;
    private javax.swing.JButton readStatusTile64;
    private javax.swing.JButton readStatusTile640;
    private javax.swing.JButton readStatusTile641;
    private javax.swing.JButton readStatusTile642;
    private javax.swing.JButton readStatusTile643;
    private javax.swing.JButton readStatusTile644;
    private javax.swing.JButton readStatusTile645;
    private javax.swing.JButton readStatusTile646;
    private javax.swing.JButton readStatusTile647;
    private javax.swing.JButton readStatusTile648;
    private javax.swing.JButton readStatusTile649;
    private javax.swing.JButton readStatusTile65;
    private javax.swing.JButton readStatusTile650;
    private javax.swing.JButton readStatusTile651;
    private javax.swing.JButton readStatusTile652;
    private javax.swing.JButton readStatusTile653;
    private javax.swing.JButton readStatusTile654;
    private javax.swing.JButton readStatusTile655;
    private javax.swing.JButton readStatusTile656;
    private javax.swing.JButton readStatusTile657;
    private javax.swing.JButton readStatusTile658;
    private javax.swing.JButton readStatusTile659;
    private javax.swing.JButton readStatusTile66;
    private javax.swing.JButton readStatusTile660;
    private javax.swing.JButton readStatusTile661;
    private javax.swing.JButton readStatusTile662;
    private javax.swing.JButton readStatusTile663;
    private javax.swing.JButton readStatusTile664;
    private javax.swing.JButton readStatusTile665;
    private javax.swing.JButton readStatusTile666;
    private javax.swing.JButton readStatusTile667;
    private javax.swing.JButton readStatusTile668;
    private javax.swing.JButton readStatusTile669;
    private javax.swing.JButton readStatusTile67;
    private javax.swing.JButton readStatusTile670;
    private javax.swing.JButton readStatusTile671;
    private javax.swing.JButton readStatusTile672;
    private javax.swing.JButton readStatusTile673;
    private javax.swing.JButton readStatusTile674;
    private javax.swing.JButton readStatusTile675;
    private javax.swing.JButton readStatusTile676;
    private javax.swing.JButton readStatusTile677;
    private javax.swing.JButton readStatusTile678;
    private javax.swing.JButton readStatusTile679;
    private javax.swing.JButton readStatusTile68;
    private javax.swing.JButton readStatusTile680;
    private javax.swing.JButton readStatusTile681;
    private javax.swing.JButton readStatusTile682;
    private javax.swing.JButton readStatusTile683;
    private javax.swing.JButton readStatusTile684;
    private javax.swing.JButton readStatusTile685;
    private javax.swing.JButton readStatusTile686;
    private javax.swing.JButton readStatusTile687;
    private javax.swing.JButton readStatusTile688;
    private javax.swing.JButton readStatusTile689;
    private javax.swing.JButton readStatusTile69;
    private javax.swing.JButton readStatusTile690;
    private javax.swing.JButton readStatusTile691;
    private javax.swing.JButton readStatusTile692;
    private javax.swing.JButton readStatusTile693;
    private javax.swing.JButton readStatusTile694;
    private javax.swing.JButton readStatusTile695;
    private javax.swing.JButton readStatusTile696;
    private javax.swing.JButton readStatusTile697;
    private javax.swing.JButton readStatusTile698;
    private javax.swing.JButton readStatusTile699;
    private javax.swing.JButton readStatusTile7;
    private javax.swing.JButton readStatusTile70;
    private javax.swing.JButton readStatusTile700;
    private javax.swing.JButton readStatusTile701;
    private javax.swing.JButton readStatusTile702;
    private javax.swing.JButton readStatusTile703;
    private javax.swing.JButton readStatusTile704;
    private javax.swing.JButton readStatusTile705;
    private javax.swing.JButton readStatusTile706;
    private javax.swing.JButton readStatusTile707;
    private javax.swing.JButton readStatusTile708;
    private javax.swing.JButton readStatusTile709;
    private javax.swing.JButton readStatusTile71;
    private javax.swing.JButton readStatusTile710;
    private javax.swing.JButton readStatusTile711;
    private javax.swing.JButton readStatusTile712;
    private javax.swing.JButton readStatusTile713;
    private javax.swing.JButton readStatusTile714;
    private javax.swing.JButton readStatusTile715;
    private javax.swing.JButton readStatusTile716;
    private javax.swing.JButton readStatusTile717;
    private javax.swing.JButton readStatusTile718;
    private javax.swing.JButton readStatusTile719;
    private javax.swing.JButton readStatusTile72;
    private javax.swing.JButton readStatusTile720;
    private javax.swing.JButton readStatusTile721;
    private javax.swing.JButton readStatusTile722;
    private javax.swing.JButton readStatusTile723;
    private javax.swing.JButton readStatusTile724;
    private javax.swing.JButton readStatusTile725;
    private javax.swing.JButton readStatusTile726;
    private javax.swing.JButton readStatusTile727;
    private javax.swing.JButton readStatusTile728;
    private javax.swing.JButton readStatusTile729;
    private javax.swing.JButton readStatusTile73;
    private javax.swing.JButton readStatusTile730;
    private javax.swing.JButton readStatusTile731;
    private javax.swing.JButton readStatusTile732;
    private javax.swing.JButton readStatusTile733;
    private javax.swing.JButton readStatusTile734;
    private javax.swing.JButton readStatusTile735;
    private javax.swing.JButton readStatusTile736;
    private javax.swing.JButton readStatusTile737;
    private javax.swing.JButton readStatusTile738;
    private javax.swing.JButton readStatusTile739;
    private javax.swing.JButton readStatusTile74;
    private javax.swing.JButton readStatusTile740;
    private javax.swing.JButton readStatusTile741;
    private javax.swing.JButton readStatusTile742;
    private javax.swing.JButton readStatusTile743;
    private javax.swing.JButton readStatusTile744;
    private javax.swing.JButton readStatusTile745;
    private javax.swing.JButton readStatusTile746;
    private javax.swing.JButton readStatusTile747;
    private javax.swing.JButton readStatusTile748;
    private javax.swing.JButton readStatusTile749;
    private javax.swing.JButton readStatusTile75;
    private javax.swing.JButton readStatusTile750;
    private javax.swing.JButton readStatusTile751;
    private javax.swing.JButton readStatusTile752;
    private javax.swing.JButton readStatusTile753;
    private javax.swing.JButton readStatusTile754;
    private javax.swing.JButton readStatusTile755;
    private javax.swing.JButton readStatusTile756;
    private javax.swing.JButton readStatusTile757;
    private javax.swing.JButton readStatusTile758;
    private javax.swing.JButton readStatusTile759;
    private javax.swing.JButton readStatusTile76;
    private javax.swing.JButton readStatusTile760;
    private javax.swing.JButton readStatusTile761;
    private javax.swing.JButton readStatusTile762;
    private javax.swing.JButton readStatusTile763;
    private javax.swing.JButton readStatusTile764;
    private javax.swing.JButton readStatusTile765;
    private javax.swing.JButton readStatusTile766;
    private javax.swing.JButton readStatusTile767;
    private javax.swing.JButton readStatusTile768;
    private javax.swing.JButton readStatusTile769;
    private javax.swing.JButton readStatusTile77;
    private javax.swing.JButton readStatusTile770;
    private javax.swing.JButton readStatusTile771;
    private javax.swing.JButton readStatusTile772;
    private javax.swing.JButton readStatusTile773;
    private javax.swing.JButton readStatusTile774;
    private javax.swing.JButton readStatusTile775;
    private javax.swing.JButton readStatusTile776;
    private javax.swing.JButton readStatusTile777;
    private javax.swing.JButton readStatusTile778;
    private javax.swing.JButton readStatusTile779;
    private javax.swing.JButton readStatusTile78;
    private javax.swing.JButton readStatusTile780;
    private javax.swing.JButton readStatusTile781;
    private javax.swing.JButton readStatusTile782;
    private javax.swing.JButton readStatusTile783;
    private javax.swing.JButton readStatusTile784;
    private javax.swing.JButton readStatusTile785;
    private javax.swing.JButton readStatusTile786;
    private javax.swing.JButton readStatusTile787;
    private javax.swing.JButton readStatusTile788;
    private javax.swing.JButton readStatusTile789;
    private javax.swing.JButton readStatusTile79;
    private javax.swing.JButton readStatusTile790;
    private javax.swing.JButton readStatusTile791;
    private javax.swing.JButton readStatusTile792;
    private javax.swing.JButton readStatusTile793;
    private javax.swing.JButton readStatusTile794;
    private javax.swing.JButton readStatusTile795;
    private javax.swing.JButton readStatusTile796;
    private javax.swing.JButton readStatusTile797;
    private javax.swing.JButton readStatusTile798;
    private javax.swing.JButton readStatusTile799;
    private javax.swing.JButton readStatusTile8;
    private javax.swing.JButton readStatusTile80;
    private javax.swing.JButton readStatusTile800;
    private javax.swing.JButton readStatusTile801;
    private javax.swing.JButton readStatusTile802;
    private javax.swing.JButton readStatusTile803;
    private javax.swing.JButton readStatusTile804;
    private javax.swing.JButton readStatusTile805;
    private javax.swing.JButton readStatusTile806;
    private javax.swing.JButton readStatusTile807;
    private javax.swing.JButton readStatusTile808;
    private javax.swing.JButton readStatusTile809;
    private javax.swing.JButton readStatusTile81;
    private javax.swing.JButton readStatusTile810;
    private javax.swing.JButton readStatusTile811;
    private javax.swing.JButton readStatusTile812;
    private javax.swing.JButton readStatusTile813;
    private javax.swing.JButton readStatusTile814;
    private javax.swing.JButton readStatusTile815;
    private javax.swing.JButton readStatusTile816;
    private javax.swing.JButton readStatusTile817;
    private javax.swing.JButton readStatusTile818;
    private javax.swing.JButton readStatusTile819;
    private javax.swing.JButton readStatusTile82;
    private javax.swing.JButton readStatusTile820;
    private javax.swing.JButton readStatusTile821;
    private javax.swing.JButton readStatusTile822;
    private javax.swing.JButton readStatusTile823;
    private javax.swing.JButton readStatusTile824;
    private javax.swing.JButton readStatusTile825;
    private javax.swing.JButton readStatusTile826;
    private javax.swing.JButton readStatusTile827;
    private javax.swing.JButton readStatusTile828;
    private javax.swing.JButton readStatusTile829;
    private javax.swing.JButton readStatusTile83;
    private javax.swing.JButton readStatusTile830;
    private javax.swing.JButton readStatusTile831;
    private javax.swing.JButton readStatusTile832;
    private javax.swing.JButton readStatusTile833;
    private javax.swing.JButton readStatusTile834;
    private javax.swing.JButton readStatusTile835;
    private javax.swing.JButton readStatusTile836;
    private javax.swing.JButton readStatusTile837;
    private javax.swing.JButton readStatusTile838;
    private javax.swing.JButton readStatusTile839;
    private javax.swing.JButton readStatusTile84;
    private javax.swing.JButton readStatusTile840;
    private javax.swing.JButton readStatusTile841;
    private javax.swing.JButton readStatusTile842;
    private javax.swing.JButton readStatusTile843;
    private javax.swing.JButton readStatusTile844;
    private javax.swing.JButton readStatusTile845;
    private javax.swing.JButton readStatusTile846;
    private javax.swing.JButton readStatusTile847;
    private javax.swing.JButton readStatusTile848;
    private javax.swing.JButton readStatusTile849;
    private javax.swing.JButton readStatusTile85;
    private javax.swing.JButton readStatusTile850;
    private javax.swing.JButton readStatusTile851;
    private javax.swing.JButton readStatusTile852;
    private javax.swing.JButton readStatusTile853;
    private javax.swing.JButton readStatusTile854;
    private javax.swing.JButton readStatusTile855;
    private javax.swing.JButton readStatusTile856;
    private javax.swing.JButton readStatusTile857;
    private javax.swing.JButton readStatusTile858;
    private javax.swing.JButton readStatusTile859;
    private javax.swing.JButton readStatusTile86;
    private javax.swing.JButton readStatusTile860;
    private javax.swing.JButton readStatusTile861;
    private javax.swing.JButton readStatusTile862;
    private javax.swing.JButton readStatusTile863;
    private javax.swing.JButton readStatusTile864;
    private javax.swing.JButton readStatusTile865;
    private javax.swing.JButton readStatusTile866;
    private javax.swing.JButton readStatusTile867;
    private javax.swing.JButton readStatusTile868;
    private javax.swing.JButton readStatusTile869;
    private javax.swing.JButton readStatusTile87;
    private javax.swing.JButton readStatusTile870;
    private javax.swing.JButton readStatusTile871;
    private javax.swing.JButton readStatusTile872;
    private javax.swing.JButton readStatusTile873;
    private javax.swing.JButton readStatusTile874;
    private javax.swing.JButton readStatusTile875;
    private javax.swing.JButton readStatusTile876;
    private javax.swing.JButton readStatusTile877;
    private javax.swing.JButton readStatusTile878;
    private javax.swing.JButton readStatusTile879;
    private javax.swing.JButton readStatusTile88;
    private javax.swing.JButton readStatusTile880;
    private javax.swing.JButton readStatusTile881;
    private javax.swing.JButton readStatusTile882;
    private javax.swing.JButton readStatusTile883;
    private javax.swing.JButton readStatusTile884;
    private javax.swing.JButton readStatusTile885;
    private javax.swing.JButton readStatusTile886;
    private javax.swing.JButton readStatusTile887;
    private javax.swing.JButton readStatusTile888;
    private javax.swing.JButton readStatusTile889;
    private javax.swing.JButton readStatusTile89;
    private javax.swing.JButton readStatusTile890;
    private javax.swing.JButton readStatusTile891;
    private javax.swing.JButton readStatusTile892;
    private javax.swing.JButton readStatusTile893;
    private javax.swing.JButton readStatusTile894;
    private javax.swing.JButton readStatusTile895;
    private javax.swing.JButton readStatusTile896;
    private javax.swing.JButton readStatusTile897;
    private javax.swing.JButton readStatusTile898;
    private javax.swing.JButton readStatusTile899;
    private javax.swing.JButton readStatusTile9;
    private javax.swing.JButton readStatusTile90;
    private javax.swing.JButton readStatusTile900;
    private javax.swing.JButton readStatusTile901;
    private javax.swing.JButton readStatusTile902;
    private javax.swing.JButton readStatusTile903;
    private javax.swing.JButton readStatusTile904;
    private javax.swing.JButton readStatusTile905;
    private javax.swing.JButton readStatusTile906;
    private javax.swing.JButton readStatusTile907;
    private javax.swing.JButton readStatusTile908;
    private javax.swing.JButton readStatusTile909;
    private javax.swing.JButton readStatusTile91;
    private javax.swing.JButton readStatusTile910;
    private javax.swing.JButton readStatusTile911;
    private javax.swing.JButton readStatusTile912;
    private javax.swing.JButton readStatusTile913;
    private javax.swing.JButton readStatusTile914;
    private javax.swing.JButton readStatusTile915;
    private javax.swing.JButton readStatusTile916;
    private javax.swing.JButton readStatusTile917;
    private javax.swing.JButton readStatusTile918;
    private javax.swing.JButton readStatusTile919;
    private javax.swing.JButton readStatusTile92;
    private javax.swing.JButton readStatusTile920;
    private javax.swing.JButton readStatusTile921;
    private javax.swing.JButton readStatusTile922;
    private javax.swing.JButton readStatusTile923;
    private javax.swing.JButton readStatusTile924;
    private javax.swing.JButton readStatusTile925;
    private javax.swing.JButton readStatusTile926;
    private javax.swing.JButton readStatusTile927;
    private javax.swing.JButton readStatusTile928;
    private javax.swing.JButton readStatusTile929;
    private javax.swing.JButton readStatusTile93;
    private javax.swing.JButton readStatusTile930;
    private javax.swing.JButton readStatusTile931;
    private javax.swing.JButton readStatusTile932;
    private javax.swing.JButton readStatusTile933;
    private javax.swing.JButton readStatusTile934;
    private javax.swing.JButton readStatusTile935;
    private javax.swing.JButton readStatusTile936;
    private javax.swing.JButton readStatusTile937;
    private javax.swing.JButton readStatusTile938;
    private javax.swing.JButton readStatusTile939;
    private javax.swing.JButton readStatusTile94;
    private javax.swing.JButton readStatusTile940;
    private javax.swing.JButton readStatusTile941;
    private javax.swing.JButton readStatusTile942;
    private javax.swing.JButton readStatusTile943;
    private javax.swing.JButton readStatusTile944;
    private javax.swing.JButton readStatusTile945;
    private javax.swing.JButton readStatusTile946;
    private javax.swing.JButton readStatusTile947;
    private javax.swing.JButton readStatusTile948;
    private javax.swing.JButton readStatusTile949;
    private javax.swing.JButton readStatusTile95;
    private javax.swing.JButton readStatusTile950;
    private javax.swing.JButton readStatusTile951;
    private javax.swing.JButton readStatusTile952;
    private javax.swing.JButton readStatusTile953;
    private javax.swing.JButton readStatusTile954;
    private javax.swing.JButton readStatusTile955;
    private javax.swing.JButton readStatusTile956;
    private javax.swing.JButton readStatusTile957;
    private javax.swing.JButton readStatusTile958;
    private javax.swing.JButton readStatusTile959;
    private javax.swing.JButton readStatusTile96;
    private javax.swing.JButton readStatusTile960;
    private javax.swing.JButton readStatusTile961;
    private javax.swing.JButton readStatusTile962;
    private javax.swing.JButton readStatusTile963;
    private javax.swing.JButton readStatusTile964;
    private javax.swing.JButton readStatusTile965;
    private javax.swing.JButton readStatusTile966;
    private javax.swing.JButton readStatusTile967;
    private javax.swing.JButton readStatusTile968;
    private javax.swing.JButton readStatusTile969;
    private javax.swing.JButton readStatusTile97;
    private javax.swing.JButton readStatusTile970;
    private javax.swing.JButton readStatusTile971;
    private javax.swing.JButton readStatusTile972;
    private javax.swing.JButton readStatusTile973;
    private javax.swing.JButton readStatusTile974;
    private javax.swing.JButton readStatusTile975;
    private javax.swing.JButton readStatusTile976;
    private javax.swing.JButton readStatusTile977;
    private javax.swing.JButton readStatusTile978;
    private javax.swing.JButton readStatusTile979;
    private javax.swing.JButton readStatusTile98;
    private javax.swing.JButton readStatusTile980;
    private javax.swing.JButton readStatusTile981;
    private javax.swing.JButton readStatusTile982;
    private javax.swing.JButton readStatusTile983;
    private javax.swing.JButton readStatusTile984;
    private javax.swing.JButton readStatusTile985;
    private javax.swing.JButton readStatusTile986;
    private javax.swing.JButton readStatusTile987;
    private javax.swing.JButton readStatusTile988;
    private javax.swing.JButton readStatusTile989;
    private javax.swing.JButton readStatusTile99;
    private javax.swing.JButton readStatusTile990;
    private javax.swing.JButton readStatusTile991;
    private javax.swing.JButton readStatusTile992;
    private javax.swing.JButton readStatusTile993;
    private javax.swing.JButton readStatusTile994;
    private javax.swing.JButton readStatusTile995;
    private javax.swing.JButton readStatusTile996;
    private javax.swing.JButton readStatusTile997;
    private javax.swing.JButton readStatusTile998;
    private javax.swing.JButton readStatusTile999;
    private javax.swing.JButton restoreBtn;
    private javax.swing.JButton searchBtn;
    private javax.swing.JLabel searchBtnLabel;
    private javax.swing.JButton settingsBtn;
    private javax.swing.JLabel settingsLabel;
    private javax.swing.JTabbedPane tabs;
    private javax.swing.JComboBox<String> testamentChooser;
    private javax.swing.JLabel tileOne4;
    private javax.swing.JPanel topBar;
    private javax.swing.JPanel upperBar;
    private javax.swing.JComboBox<String> verseChooser;
    // End of variables declaration//GEN-END:variables
}