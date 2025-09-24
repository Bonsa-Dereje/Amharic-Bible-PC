

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

        // Remove overlapping highlights in same book, chapter, line, and text
        String delete = """
            DELETE FROM highlights
            WHERE bookIndex=? AND chapterIndex=? AND line=? AND wordDistIndex>=? AND text=?
        """;
        try (PreparedStatement ps = conn.prepareStatement(delete)) {
            ps.setInt(1, currentBookIndex);
            ps.setInt(2, currentChapterIndex);
            ps.setInt(3, mainTextArea.getLineOfOffset(mainTextArea.getSelectionStart()));
            ps.setInt(4, 0); // fallback
            ps.setString(5, text);
            ps.executeUpdate();
        }

        // Insert new highlight
        String insert = """
            INSERT INTO highlights (bookIndex, chapterIndex, line, wordDistIndex, text, colorR, colorG, colorB)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?)
        """;
        try (PreparedStatement ps = conn.prepareStatement(insert)) {
            int caretPosition = mainTextArea.getSelectionStart();
            int lineNumber = mainTextArea.getLineOfOffset(caretPosition);

            int lineStartOffset = mainTextArea.getLineStartOffset(lineNumber);
            String lineText = mainTextArea.getText(lineStartOffset,
                    mainTextArea.getLineEndOffset(lineNumber) - lineStartOffset);

            String[] words = lineText.split("\\s+");
            int wordDistIndex = 0;
            int selectionStartInLine = caretPosition - lineStartOffset;
            int cumulativeIndex = 0;
            for (int i = 0; i < words.length; i++) {
                int wordStart = cumulativeIndex;
                int wordEnd = cumulativeIndex + words[i].length();
                if (selectionStartInLine >= wordStart && selectionStartInLine < wordEnd) {
                    wordDistIndex = i;
                    break;
                }
                cumulativeIndex = wordEnd + 1;
            }

            ps.setInt(1, currentBookIndex);
            ps.setInt(2, currentChapterIndex);
            ps.setInt(3, lineNumber);
            ps.setInt(4, wordDistIndex);
            ps.setString(5, text);
            ps.setInt(6, color.getRed());
            ps.setInt(7, color.getGreen());
            ps.setInt(8, color.getBlue());
            ps.executeUpdate();
        }

    } catch (Exception ex) {
        ex.printStackTrace();
    }
}


private void restoreHighlights() {
    try (Connection conn = DBManager.getConnection()) {
        String query = """
            SELECT line, wordDistIndex, text, colorR, colorG, colorB
            FROM highlights
            WHERE bookIndex=? AND chapterIndex=?
        """;

        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, currentBookIndex);
            ps.setInt(2, currentChapterIndex);

            ResultSet rs = ps.executeQuery();
            mainTextArea.getHighlighter().removeAllHighlights();

            while (rs.next()) {
                int lineNumber = rs.getInt("line");
                int wordDistIndex = rs.getInt("wordDistIndex");
                String text = rs.getString("text");
                Color color = new Color(rs.getInt("colorR"), rs.getInt("colorG"), rs.getInt("colorB"));

                int lineStartOffset = mainTextArea.getLineStartOffset(lineNumber);
                int lineEndOffset = mainTextArea.getLineEndOffset(lineNumber);
                String lineText = mainTextArea.getText(lineStartOffset, lineEndOffset - lineStartOffset);

                String[] words = lineText.split("\\s+");
                int cumulativeIndex = 0;
                for (int i = 0; i < wordDistIndex; i++) {
                    cumulativeIndex += words[i].length() + 1;
                }
                int startInLine = cumulativeIndex;
                int start = lineStartOffset + startInLine;
                int end = Math.min(start + text.length(), mainTextArea.getText().length());

                mainTextArea.getHighlighter().addHighlight(
                        start, end,
                        new DefaultHighlighter.DefaultHighlightPainter(color)
                );
            }
        }

    } catch (Exception ex) {
        ex.printStackTrace();
    }
}



    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel_layered = new javax.swing.JLayeredPane();
        topBar = new javax.swing.JPanel();
        bookChooserDropDown = new javax.swing.JComboBox<>();
        jTextField1 = new javax.swing.JTextField();
        closeBtn = new javax.swing.JButton();
        appTitle = new javax.swing.JLabel();
        restoreBtn = new javax.swing.JButton();
        minimizeBtn = new javax.swing.JButton();
        JSON = new javax.swing.JButton();
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
        bibleBtn = new javax.swing.JButton();
        searchBtn = new javax.swing.JButton();
        bookmarkBtn = new javax.swing.JButton();
        homeBtn5 = new javax.swing.JButton();
        audiobookBtn = new javax.swing.JButton();
        cmntrsBtn = new javax.swing.JButton();
        hostJoinBtn = new javax.swing.JButton();
        settingsBtn = new javax.swing.JButton();
        tabs = new javax.swing.JTabbedPane();
        homeTab = new javax.swing.JPanel();
        mainTextScrollPanel = new javax.swing.JScrollPane();
        mainTextArea = new javax.swing.JTextArea();
        cmtryTabbedPanel = new javax.swing.JTabbedPane();
        notesTabPanel = new javax.swing.JPanel();
        addNoteBtn = new javax.swing.JButton();
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
        jPanel7 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();

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

        JSON.setText("JSON");
        JSON.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JSONActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout topBarLayout = new javax.swing.GroupLayout(topBar);
        topBar.setLayout(topBarLayout);
        topBarLayout.setHorizontalGroup(
            topBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topBarLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(appTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(461, 461, 461)
                .addComponent(JSON)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 525, Short.MAX_VALUE)
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
            .addComponent(appTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(topBarLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(topBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(minimizeBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(restoreBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(closeBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bookChooserDropDown, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(topBarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(JSON))
        );

        mainPanel_layered.add(topBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1650, 35));

        navBar.setBackground(new java.awt.Color(40, 43, 45));

        homeBtnLabel.setForeground(new java.awt.Color(255, 255, 255));
        homeBtnLabel.setText("Home");

        bibleBtnLabel.setForeground(new java.awt.Color(255, 255, 255));
        bibleBtnLabel.setText(" Bible");

        searchBtnLabel.setForeground(new java.awt.Color(255, 255, 255));
        searchBtnLabel.setText("Search");

        bookmarksBtn.setForeground(new java.awt.Color(255, 255, 255));
        bookmarksBtn.setText("Bookmarks");

        notesBtnLabel.setForeground(new java.awt.Color(255, 255, 255));
        notesBtnLabel.setText("Notes");

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
        homeBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/home.png"))); // NOI18N
        homeBtn.setBorder(null);
        homeBtn.setBorderPainted(false);
        homeBtn.setContentAreaFilled(false);
        homeBtn.setFocusPainted(false);
        homeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                homeBtnActionPerformed(evt);
            }
        });

        bibleBtn.setBackground(new java.awt.Color(40, 43, 45));
        bibleBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/book.png"))); // NOI18N
        bibleBtn.setBorder(null);
        bibleBtn.setBorderPainted(false);
        bibleBtn.setContentAreaFilled(false);
        bibleBtn.setFocusable(false);
        bibleBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bibleBtnActionPerformed(evt);
            }
        });

        searchBtn.setBackground(new java.awt.Color(40, 43, 45));
        searchBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/search.png"))); // NOI18N
        searchBtn.setBorder(null);

        bookmarkBtn.setBackground(new java.awt.Color(40, 43, 45));
        bookmarkBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/bookmark.png"))); // NOI18N
        bookmarkBtn.setBorder(null);

        homeBtn5.setBackground(new java.awt.Color(40, 43, 45));
        homeBtn5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/notes.png"))); // NOI18N
        homeBtn5.setBorder(null);

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
                                    .addComponent(bibleBtnLabel)
                                    .addComponent(searchBtnLabel)
                                    .addComponent(homeBtn)
                                    .addComponent(bibleBtn)
                                    .addComponent(searchBtn)
                                    .addComponent(bookmarkBtn)))
                            .addGroup(navBarLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(bookmarksBtn))
                            .addGroup(navBarLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(navBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(notesBtnLabel)
                                    .addComponent(homeBtn5)
                                    .addComponent(audiobookBtn)))
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
                                .addComponent(settingsBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(navBarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(settingsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        navBarLayout.setVerticalGroup(
            navBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(navBarLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(homeBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(homeBtnLabel)
                .addGap(18, 18, 18)
                .addComponent(bibleBtn)
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
                .addComponent(homeBtn5)
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
                .addContainerGap(294, Short.MAX_VALUE))
        );

        mainPanel_layered.add(navBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 70, 930));

        homeTab.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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
                    // Highlight selected text
                    Highlighter.HighlightPainter painter =
                    new DefaultHighlighter.DefaultHighlightPainter(currentHighlightColor);

                    int start = mainTextArea.getSelectionStart();
                    int end = mainTextArea.getSelectionEnd();

                    mainTextArea.getHighlighter().addHighlight(start, end, painter);

                    // Save highlight immediately into SQLite
                    saveHighlight(selectedText, currentHighlightColor);

                    // Simulate actual mouse click after 5 ms
                    new Thread(() -> {
                        try {
                            Thread.sleep(5); // 5 ms delay

                            // Get the screen location of the caret
                            Rectangle caretRect = mainTextArea.modelToView(start);
                            Point textAreaOnScreen = mainTextArea.getLocationOnScreen();
                            int mouseX = textAreaOnScreen.x + caretRect.x + 1;
                            int mouseY = textAreaOnScreen.y + caretRect.y + 1;

                            // Create Robot and click
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

        // -------------------- Document listener --------------------
        mainTextArea.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                restoreHighlights(); // live update after insertion
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                restoreHighlights(); // live update after removal
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                restoreHighlights();
            }
        });

        homeTab.add(mainTextScrollPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 870, 860));

        cmtryTabbedPanel.setBackground(new java.awt.Color(255, 255, 255));
        cmtryTabbedPanel.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N

        notesTabPanel.setBackground(new java.awt.Color(255, 255, 255));
        notesTabPanel.setLayout(new java.awt.GridBagLayout());

        addNoteBtn.setBackground(new java.awt.Color(204, 204, 204));
        addNoteBtn.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        addNoteBtn.setForeground(new java.awt.Color(102, 102, 102));
        addNoteBtn.setText("Create a new note");
        addNoteBtn.setAlignmentY(0.0F);
        notesTabPanel.add(addNoteBtn, new java.awt.GridBagConstraints());

        cmtryTabbedPanel.addTab("Notes", notesTabPanel);

        nodesTabPanel.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout nodesTabPanelLayout = new javax.swing.GroupLayout(nodesTabPanel);
        nodesTabPanel.setLayout(nodesTabPanelLayout);
        nodesTabPanelLayout.setHorizontalGroup(
            nodesTabPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 660, Short.MAX_VALUE)
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
            .addGap(0, 660, Short.MAX_VALUE)
        );
        cmtrsTabPanelLayout.setVerticalGroup(
            cmtrsTabPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 825, Short.MAX_VALUE)
        );

        cmtryTabbedPanel.addTab("Commentaries", cmtrsTabPanel);

        homeTab.add(cmtryTabbedPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 110, 660, 860));

        biblestudyTitle.setFont(new java.awt.Font("Nokia Pure Headline Ultra Light", 1, 18)); // NOI18N
        biblestudyTitle.setText("መፅሀፍ ቅዱስ ጥናት");
        homeTab.add(biblestudyTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(907, 74, -1, 20));

        highlightBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/highlight.png"))); // NOI18N
        highlightBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                highlightBtnActionPerformed(evt);
            }
        });
        homeTab.add(highlightBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(863, 67, -1, -1));

        boldBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/bold.png"))); // NOI18N
        boldBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boldBtnActionPerformed(evt);
            }
        });
        homeTab.add(boldBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(831, 67, -1, -1));

        fontSizeSlider.setSnapToTicks(true);
        fontSizeSlider.setFocusable(false);
        fontSizeSlider.setOpaque(true);
        homeTab.add(fontSizeSlider, new org.netbeans.lib.awtextra.AbsoluteConstraints(721, 67, 104, 27));
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
        homeTab.add(bookChooser, new org.netbeans.lib.awtextra.AbsoluteConstraints(128, 67, 182, -1));
        bookChooser.addActionListener(e ->{
            currentBookIndex = bookChooser.getSelectedIndex();

        });

        chapterChooser.setFont(new java.awt.Font("Nokia Pure Headline Ultra Light", 0, 14)); // NOI18N
        homeTab.add(chapterChooser, new org.netbeans.lib.awtextra.AbsoluteConstraints(322, 68, 60, -1));
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
        homeTab.add(verseChooser, new org.netbeans.lib.awtextra.AbsoluteConstraints(394, 68, 59, -1));
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
        homeTab.add(testamentChooser, new org.netbeans.lib.awtextra.AbsoluteConstraints(17, 67, 105, -1));

        tabs.addTab("Home", homeTab);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1580, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 975, Short.MAX_VALUE)
        );

        tabs.addTab("Bible", jPanel7);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1580, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 975, Short.MAX_VALUE)
        );

        tabs.addTab("Search", jPanel8);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1580, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 975, Short.MAX_VALUE)
        );

        tabs.addTab("Boomarks", jPanel9);

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

    private void homeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_homeBtnActionPerformed
        tabs.setSelectedIndex(0);
    }//GEN-LAST:event_homeBtnActionPerformed

    private void bibleBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bibleBtnActionPerformed
        tabs.setSelectedIndex(1);
    }//GEN-LAST:event_bibleBtnActionPerformed

    private void JSONActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JSONActionPerformed
    File file = new File("highlightState.json");
    if (!file.exists()) {
        System.out.println("No highlight JSON file found.");
        return;
    }

    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) sb.append(line);

        JSONArray allSections = new JSONArray(sb.toString());
        String targetHeader = "#" + currentBookIndex + "_*" + currentChapterIndex;

        for (int i = 0; i < allSections.length(); i++) {
            Object item = allSections.get(i);
            if (item instanceof String && item.equals(targetHeader)) {
                if (i + 1 < allSections.length() && allSections.get(i + 1) instanceof JSONArray) {
                    JSONArray section = allSections.getJSONArray(i + 1);

                    // Compare each highlight with every other on the same line
                    for (int j = 0; j < section.length(); j++) {
                        JSONObject lowObj = section.getJSONObject(j);
                        int lowLine = lowObj.getInt("line");
                        int lowIndex = lowObj.getInt("wordDistIndex");
                        List<String> lowWords = new ArrayList<>(List.of(lowObj.getString("text").split("\\s+")));

                        for (int k = 0; k < section.length(); k++) {
                            if (j == k) continue;

                            JSONObject highObj = section.getJSONObject(k);
                            int highLine = highObj.getInt("line");
                            int highIndex = highObj.getInt("wordDistIndex");
                            List<String> highWords = new ArrayList<>(List.of(highObj.getString("text").split("\\s+")));

                            // Only act if same line and highIndex > lowIndex
                            if (lowLine == highLine && highIndex > lowIndex) {
                                List<String> omitted = new ArrayList<>(lowWords);
                                omitted.retainAll(highWords); // words present in highWords

                                if (!omitted.isEmpty()) {
                                    // Remove omitted words from lower highlight
                                    lowWords.removeAll(omitted);
                                    lowObj.put("text", String.join(" ", lowWords));

                                    // Print omitted words
                                    System.out.println("Omitted words from lower highlight: " + omitted);
                                }
                            }
                        }
                    }
                }
            }
        }

    } catch (Exception ex) {
        ex.printStackTrace();
    }
    }//GEN-LAST:event_JSONActionPerformed

    
 
    
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
    private javax.swing.JButton JSON;
    private javax.swing.JButton addNoteBtn;
    private javax.swing.JLabel appTitle;
    private javax.swing.JButton audiobookBtn;
    private javax.swing.JLabel audiobookLabel;
    private javax.swing.JButton bibleBtn;
    private javax.swing.JLabel bibleBtnLabel;
    private javax.swing.JLabel biblestudyTitle;
    private javax.swing.JButton boldBtn;
    private javax.swing.JComboBox<String> bookChooser;
    private javax.swing.JComboBox<String> bookChooserDropDown;
    private javax.swing.JButton bookmarkBtn;
    private javax.swing.JLabel bookmarksBtn;
    private javax.swing.JComboBox<String> chapterChooser;
    private javax.swing.JButton closeBtn;
    private javax.swing.JButton cmntrsBtn;
    private javax.swing.JLabel cmntrsBtnLabel;
    private javax.swing.JPanel cmtrsTabPanel;
    private javax.swing.JTabbedPane cmtryTabbedPanel;
    private javax.swing.JSlider fontSizeSlider;
    private javax.swing.JButton highlightBtn;
    private javax.swing.JButton homeBtn;
    private javax.swing.JButton homeBtn5;
    private javax.swing.JLabel homeBtnLabel;
    private javax.swing.JPanel homeTab;
    private javax.swing.JButton hostJoinBtn;
    private javax.swing.JLabel hostJoinBtnLabel;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLayeredPane mainPanel_layered;
    private javax.swing.JTextArea mainTextArea;
    private javax.swing.JScrollPane mainTextScrollPanel;
    private javax.swing.JButton minimizeBtn;
    private javax.swing.JPanel navBar;
    private javax.swing.JPanel nodesTabPanel;
    private javax.swing.JLabel notesBtnLabel;
    private javax.swing.JPanel notesTabPanel;
    private javax.swing.JButton restoreBtn;
    private javax.swing.JButton searchBtn;
    private javax.swing.JLabel searchBtnLabel;
    private javax.swing.JButton settingsBtn;
    private javax.swing.JLabel settingsLabel;
    private javax.swing.JTabbedPane tabs;
    private javax.swing.JComboBox<String> testamentChooser;
    private javax.swing.JPanel topBar;
    private javax.swing.JComboBox<String> verseChooser;
    // End of variables declaration//GEN-END:variables
}