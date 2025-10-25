package com.ebma.bibleapp;

import com.formdev.flatlaf.FlatDarkLaf;
import org.json.JSONObject;
import org.json.JSONTokener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileInputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class BibleNodeSearchApp extends JFrame {

    private NodePanel nodePanel;
    private JTextArea verseDisplay;

    public BibleNodeSearchApp() {
        FlatDarkLaf.setup();
        setTitle("Bible Node Search App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 800);
        setLocationRelativeTo(null);

        // Layout split pane
        JSplitPane splitPane = new JSplitPane();
        splitPane.setDividerLocation(800);
        splitPane.setResizeWeight(0.75);
        getContentPane().add(splitPane);

        // Node panel (graph area)
        nodePanel = new NodePanel(this);
        splitPane.setLeftComponent(nodePanel);

        // Verse info display
        verseDisplay = new JTextArea();
        verseDisplay.setEditable(false);
        verseDisplay.setWrapStyleWord(true);
        verseDisplay.setLineWrap(true);
        verseDisplay.setFont(new Font("Consolas", Font.PLAIN, 16));
        verseDisplay.setForeground(Color.WHITE);
        verseDisplay.setBackground(new Color(45, 45, 45));
        splitPane.setRightComponent(new JScrollPane(verseDisplay));

        // Prompt for search phrase
        SwingUtilities.invokeLater(() -> {
            String phrase = JOptionPane.showInputDialog(this,
                    "Enter a word, phrase, or sentence to search in the Bible:");
            if (phrase != null && !phrase.trim().isEmpty()) {
                nodePanel.loadBibleAndSearch(phrase.trim());
            }
        });
    }

    public void displayVerse(String info) {
        verseDisplay.setText(info);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new BibleNodeSearchApp().setVisible(true));
    }
}

class VerseNode {
    String label;
    String book;
    String chapter;
    String verseNum;
    String text;
    int x, y;
    static final int SIZE = 60;
    Color color = new Color(100, 150, 255);

    public VerseNode(String label, String book, String chapter, String verseNum, String text, int x, int y) {
        this.label = label;
        this.book = book;
        this.chapter = chapter;
        this.verseNum = verseNum;
        this.text = text;
        this.x = x;
        this.y = y;
    }

    public boolean contains(Point p) {
        return new Rectangle(x - SIZE / 2, y - SIZE / 2, SIZE, SIZE).contains(p);
    }

    public void draw(Graphics2D g) {
        g.setColor(color);
        g.fillOval(x - SIZE / 2, y - SIZE / 2, SIZE, SIZE);
        g.setColor(Color.WHITE);
        g.drawOval(x - SIZE / 2, y - SIZE / 2, SIZE, SIZE);
        FontMetrics fm = g.getFontMetrics();
        int textWidth = fm.stringWidth(label);
        g.drawString(label, x - textWidth / 2, y + fm.getAscent() / 2);
    }
}

class NodePanel extends JPanel {
    private final List<VerseNode> nodes = new ArrayList<>();
    private VerseNode selectedNode = null;
    private Point offset = null;
    private final BibleNodeSearchApp parentApp;

    public NodePanel(BibleNodeSearchApp parentApp) {
        this.parentApp = parentApp;
        setBackground(new Color(25, 25, 25));

        MouseAdapter mouseHandler = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                for (VerseNode node : nodes) {
                    if (node.contains(e.getPoint())) {
                        selectedNode = node;
                        offset = new Point(e.getX() - node.x, e.getY() - node.y);

                        // Left click shows verse info
                        if (SwingUtilities.isLeftMouseButton(e)) {
                            parentApp.displayVerse(String.format(
                                    "%s %s:%s\n\n%s",
                                    node.book, node.chapter, node.verseNum, node.text
                            ));
                        }
                        return;
                    }
                }
                selectedNode = null;
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                if (selectedNode != null && offset != null) {
                    selectedNode.x = e.getX() - offset.x;
                    selectedNode.y = e.getY() - offset.y;
                    repaint();
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                selectedNode = null;
            }
        };

        addMouseListener(mouseHandler);
        addMouseMotionListener(mouseHandler);
    }

    public void loadBibleAndSearch(String phrase) {
        try {
            String path = "src/main/resources/files/books/NIV/NIV_bible.json";
            FileInputStream fis = new FileInputStream(path);

            // ✅ FIXED: Wrap InputStream in InputStreamReader with UTF-8
            JSONObject bible = new JSONObject(new JSONTokener(new java.io.InputStreamReader(fis, StandardCharsets.UTF_8)));

            nodes.clear();

            int centerX = getWidth() / 2;
            int centerY = getHeight() / 2;
            int radius = 250;
            int count = 0;

            for (String book : bible.keySet()) {
                JSONObject chapters = bible.getJSONObject(book);
                for (String ch : chapters.keySet()) {
                    JSONObject verses = chapters.getJSONObject(ch);
                    for (String vnum : verses.keySet()) {
                        String verseText = verses.getString(vnum);
                        if (verseText.toLowerCase().contains(phrase.toLowerCase())) {
                            double angle = Math.toRadians((360.0 / 20) * (count % 20));
                            int x = (int) (centerX + radius * Math.cos(angle));
                            int y = (int) (centerY + radius * Math.sin(angle));
                            String label = book.substring(0, Math.min(3, book.length())) + ":" + vnum;
                            nodes.add(new VerseNode(label, book, ch, vnum, verseText, x, y));
                            count++;
                        }
                    }
                }
            }

            if (nodes.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No matches found for: " + phrase);
            } else {
                repaint();
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading or parsing Bible JSON:\n" + ex.getMessage());
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw all nodes
        for (VerseNode node : nodes) {
            node.draw(g2);
        }
    }
}
