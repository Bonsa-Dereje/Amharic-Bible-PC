

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


    public mainWindow() {
        
        setUndecorated(true);  
        initComponents();
        
         
        setLocationRelativeTo(null);
        
        boldBtn.setBackground(Color.LIGHT_GRAY);
        
        testamentChooser.addActionListener(e -> updateBookChooser());

        testamentChooser.setSelectedIndex(0);
        updateBookChooser();
      
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
    
     
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel_layered = new javax.swing.JLayeredPane();
        jPanel1 = new javax.swing.JPanel();
        bookChooserDropDown = new javax.swing.JComboBox<>();
        jTextField1 = new javax.swing.JTextField();
        closeBtn = new javax.swing.JButton();
        appTitle = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        homeBtnLabel = new javax.swing.JLabel();
        bibleBtnLabel = new javax.swing.JLabel();
        searchBtnLabel = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        notesBtnLabel = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        cmntrsBtnLabel = new javax.swing.JLabel();
        hostJoinBtnLabel = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        homeBtn = new javax.swing.JButton();
        bibleBtn = new javax.swing.JButton();
        searchBtn = new javax.swing.JButton();
        bookmarkBtn = new javax.swing.JButton();
        homeBtn5 = new javax.swing.JButton();
        audiobookBtn = new javax.swing.JButton();
        cmntrsBtn = new javax.swing.JButton();
        hostJoinBtn = new javax.swing.JButton();
        homeBtn4 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        mainTextArea = new javax.swing.JTextArea();
        cmtryTabbedPanel = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        addNoteBtn = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        Tabs = new javax.swing.JTabbedPane();
        jPanel6 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        highlightBtn = new javax.swing.JButton();
        boldBtn = new javax.swing.JButton();
        fontSizeSlider = new javax.swing.JSlider();
        bookChooser = new javax.swing.JComboBox<>();
        jComboBox2 = new javax.swing.JComboBox<>();
        jComboBox1 = new javax.swing.JComboBox<>();
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

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(1427, 10));

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

        jButton2.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jButton2.setText("☐");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jButton3.setText("‒");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(appTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 1058, Short.MAX_VALUE)
                .addComponent(bookChooserDropDown, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(closeBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(appTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(closeBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bookChooserDropDown, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        mainPanel_layered.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1650, 35));

        jPanel2.setBackground(new java.awt.Color(40, 43, 45));

        homeBtnLabel.setForeground(new java.awt.Color(255, 255, 255));
        homeBtnLabel.setText("Home");

        bibleBtnLabel.setForeground(new java.awt.Color(255, 255, 255));
        bibleBtnLabel.setText(" Bible");

        searchBtnLabel.setForeground(new java.awt.Color(255, 255, 255));
        searchBtnLabel.setText("Search");

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Bookmarks");

        notesBtnLabel.setForeground(new java.awt.Color(255, 255, 255));
        notesBtnLabel.setText("Notes");

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Audiobook");

        cmntrsBtnLabel.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        cmntrsBtnLabel.setForeground(new java.awt.Color(255, 255, 255));
        cmntrsBtnLabel.setText("Commentaries");

        hostJoinBtnLabel.setForeground(new java.awt.Color(255, 255, 255));
        hostJoinBtnLabel.setText(" Host/Join");
        hostJoinBtnLabel.setAlignmentX(0.5F);

        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("   Settings");

        homeBtn.setBackground(new java.awt.Color(40, 43, 45));
        homeBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/home.png"))); // NOI18N
        homeBtn.setBorder(null);

        bibleBtn.setBackground(new java.awt.Color(40, 43, 45));
        bibleBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/book.png"))); // NOI18N
        bibleBtn.setBorder(null);

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

        homeBtn4.setBackground(new java.awt.Color(40, 43, 45));
        homeBtn4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/settings.png"))); // NOI18N
        homeBtn4.setBorder(null);
        homeBtn4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                homeBtn4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(cmntrsBtnLabel))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(homeBtnLabel)
                                    .addComponent(bibleBtnLabel)
                                    .addComponent(searchBtnLabel)
                                    .addComponent(homeBtn)
                                    .addComponent(bibleBtn)
                                    .addComponent(searchBtn)
                                    .addComponent(bookmarkBtn)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel2))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(notesBtnLabel)
                                    .addComponent(homeBtn5)
                                    .addComponent(audiobookBtn)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel3))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(hostJoinBtnLabel))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(cmntrsBtn))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(hostJoinBtn))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(homeBtn4, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
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
                .addComponent(jLabel2)
                .addGap(24, 24, 24)
                .addComponent(homeBtn5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(notesBtnLabel)
                .addGap(18, 18, 18)
                .addComponent(audiobookBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(cmntrsBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmntrsBtnLabel)
                .addGap(18, 18, 18)
                .addComponent(hostJoinBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(hostJoinBtnLabel)
                .addGap(18, 18, 18)
                .addComponent(homeBtn4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addContainerGap(294, Short.MAX_VALUE))
        );

        mainPanel_layered.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 70, 930));

        mainTextArea.setEditable(false);
        mainTextArea.setColumns(20);
        mainTextArea.setFont(new java.awt.Font("Nokia Pure Headline Ultra Light", 1, 18)); // NOI18N
        mainTextArea.setRows(5);
        jScrollPane1.setViewportView(mainTextArea);
        mainTextArea.addCaretListener(e -> {
            String selectedText = mainTextArea.getSelectedText();
            if (selectedText != null && !selectedText.isEmpty() && currentHighlightColor != null) {
                try {
                    // Remove old highlights (optional)
                    mainTextArea.getHighlighter().removeAllHighlights();

                    // Force selected text to render in black immediately
                    mainTextArea.setSelectedTextColor(Color.BLACK);

                    // Apply highlight with chosen color
                    Highlighter.HighlightPainter painter =
                    new DefaultHighlighter.DefaultHighlightPainter(currentHighlightColor);

                    int start = mainTextArea.getSelectionStart();
                    int end = mainTextArea.getSelectionEnd();

                    mainTextArea.getHighlighter().addHighlight(start, end, painter);

                } catch (BadLocationException ex) {
                    ex.printStackTrace();
                }
            }
        });

        mainPanel_layered.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 90, 870, 860));

        cmtryTabbedPanel.setBackground(new java.awt.Color(255, 255, 255));
        cmtryTabbedPanel.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new java.awt.GridBagLayout());

        addNoteBtn.setBackground(new java.awt.Color(204, 204, 204));
        addNoteBtn.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        addNoteBtn.setForeground(new java.awt.Color(102, 102, 102));
        addNoteBtn.setText("Create a new note");
        addNoteBtn.setAlignmentY(0.0F);
        jPanel3.add(addNoteBtn, new java.awt.GridBagConstraints());

        cmtryTabbedPanel.addTab("Notes", jPanel3);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 660, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 825, Short.MAX_VALUE)
        );

        cmtryTabbedPanel.addTab("Commentaries", jPanel4);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 660, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 825, Short.MAX_VALUE)
        );

        cmtryTabbedPanel.addTab("Nodes", jPanel5);

        mainPanel_layered.add(cmtryTabbedPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 90, 660, 860));

        jLabel5.setFont(new java.awt.Font("Nokia Pure Headline Ultra Light", 1, 18)); // NOI18N
        jLabel5.setText("መፅሀፍ ቅዱስ ጥናት");

        highlightBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/highlight.png"))); // NOI18N
        highlightBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                highlightBtnActionPerformed(evt);
            }
        });

        boldBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/bold.png"))); // NOI18N
        boldBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boldBtnActionPerformed(evt);
            }
        });

        fontSizeSlider.setSnapToTicks(true);
        fontSizeSlider.setFocusable(false);
        fontSizeSlider.setOpaque(true);

        bookChooser.setFont(new java.awt.Font("Nokia Pure Headline Ultra Light", 1, 14)); // NOI18N

        jComboBox2.setFont(new java.awt.Font("Nokia Pure Headline Ultra Light", 0, 14)); // NOI18N
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4" }));

        jComboBox1.setFont(new java.awt.Font("Nokia Pure Headline Ultra Light", 0, 14)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4" }));

        testamentChooser.setFont(new java.awt.Font("Nokia Pure Headline Ultra Light", 1, 14)); // NOI18N
        testamentChooser.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ብሉይ ኪዳን", "አዲስ ኪዳን" }));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(testamentChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bookChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 330, Short.MAX_VALUE)
                .addComponent(fontSizeSlider, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(boldBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(highlightBtn)
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addGap(310, 310, 310))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(highlightBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(boldBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(fontSizeSlider, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(testamentChooser, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(bookChooser)))
                .addContainerGap(721, Short.MAX_VALUE))
        );

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

        Tabs.addTab("Home", jPanel6);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1350, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 815, Short.MAX_VALUE)
        );

        Tabs.addTab("Bible", jPanel7);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1350, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 815, Short.MAX_VALUE)
        );

        Tabs.addTab("Search", jPanel8);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1350, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 815, Short.MAX_VALUE)
        );

        Tabs.addTab("Boomarks", jPanel9);

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1350, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 815, Short.MAX_VALUE)
        );

        Tabs.addTab("Notes", jPanel10);

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1350, Short.MAX_VALUE)
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 815, Short.MAX_VALUE)
        );

        Tabs.addTab("AudioBooks", jPanel11);

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1350, Short.MAX_VALUE)
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 815, Short.MAX_VALUE)
        );

        Tabs.addTab("Commentaries", jPanel12);

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1350, Short.MAX_VALUE)
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 815, Short.MAX_VALUE)
        );

        Tabs.addTab("HostJoin", jPanel13);

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1350, Short.MAX_VALUE)
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 815, Short.MAX_VALUE)
        );

        Tabs.addTab("Settings", jPanel14);

        mainPanel_layered.add(Tabs, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, -50, 1350, 850));

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

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void homeBtn4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_homeBtn4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_homeBtn4ActionPerformed

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

    private void boldBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boldBtnActionPerformed
       
        isBoldActive = !isBoldActive;

        Font currentFont = mainTextArea.getFont();

        Font newFont = currentFont.deriveFont(isBoldActive ? Font.BOLD : Font.PLAIN);
        mainTextArea.setFont(newFont);

        boldBtn.setBackground(isBoldActive ? Color.LIGHT_GRAY : UIManager.getColor("Button.background"));

    }//GEN-LAST:event_boldBtnActionPerformed

    
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
    private javax.swing.JTabbedPane Tabs;
    private javax.swing.JButton addNoteBtn;
    private javax.swing.JLabel appTitle;
    private javax.swing.JButton audiobookBtn;
    private javax.swing.JButton bibleBtn;
    private javax.swing.JLabel bibleBtnLabel;
    private javax.swing.JButton boldBtn;
    private javax.swing.JComboBox<String> bookChooser;
    private javax.swing.JComboBox<String> bookChooserDropDown;
    private javax.swing.JButton bookmarkBtn;
    private javax.swing.JButton closeBtn;
    private javax.swing.JButton cmntrsBtn;
    private javax.swing.JLabel cmntrsBtnLabel;
    private javax.swing.JTabbedPane cmtryTabbedPanel;
    private javax.swing.JSlider fontSizeSlider;
    private javax.swing.JButton highlightBtn;
    private javax.swing.JButton homeBtn;
    private javax.swing.JButton homeBtn4;
    private javax.swing.JButton homeBtn5;
    private javax.swing.JLabel homeBtnLabel;
    private javax.swing.JButton hostJoinBtn;
    private javax.swing.JLabel hostJoinBtnLabel;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLayeredPane mainPanel_layered;
    private javax.swing.JTextArea mainTextArea;
    private javax.swing.JLabel notesBtnLabel;
    private javax.swing.JButton searchBtn;
    private javax.swing.JLabel searchBtnLabel;
    private javax.swing.JComboBox<String> testamentChooser;
    // End of variables declaration//GEN-END:variables
}
