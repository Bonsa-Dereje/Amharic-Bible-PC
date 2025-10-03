package com.ebma.bibleapp;

import com.formdev.flatlaf.FlatLightLaf;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import javax.imageio.ImageIO;
import javax.swing.*;

public class bookToStack extends JFrame {

    private JTextField authorField, bookNameField, categoryField;
    private JTextArea descriptionArea;
    private JLabel bookTileLabel, dropPdfLabel, dropTileLabel;
    private File selectedPdfFile;
    private File selectedTileFile;

    private final File defaultBookFolder = new File("src/main/resources/bookStack");
    private final File defaultTileFolder = new File("src/main/resources/bookTiles");

    public bookToStack() {
        setTitle("Add Book to Stack");
        setSize(520, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        // Author Name
        JLabel authorLabel = new JLabel("Author:");
        authorLabel.setBounds(30, 30, 100, 25);
        add(authorLabel);

        authorField = new JTextField();
        authorField.setBounds(140, 30, 320, 25);
        add(authorField);

        // Book Name
        JLabel bookLabel = new JLabel("Book Name:");
        bookLabel.setBounds(30, 70, 100, 25);
        add(bookLabel);

        bookNameField = new JTextField();
        bookNameField.setBounds(140, 70, 320, 25);
        add(bookNameField);

        // Category
        JLabel categoryLabel = new JLabel("Category:");
        categoryLabel.setBounds(30, 110, 100, 25);
        add(categoryLabel);

        categoryField = new JTextField();
        categoryField.setBounds(140, 110, 320, 25);
        add(categoryField);

        // Description
        JLabel descLabel = new JLabel("Description:");
        descLabel.setBounds(30, 150, 100, 25);
        add(descLabel);

        descriptionArea = new JTextArea();
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);
        JScrollPane descScroll = new JScrollPane(descriptionArea);
        descScroll.setBounds(140, 150, 320, 70);
        add(descScroll);

        // Book Tile button
        JButton chooseTileBtn = new JButton("Choose Book Tile");
        chooseTileBtn.setBounds(30, 240, 150, 25);
        chooseTileBtn.addActionListener(e -> chooseTile());
        add(chooseTileBtn);

        bookTileLabel = new JLabel("No Tile Selected");
        bookTileLabel.setBounds(190, 240, 250, 25);
        add(bookTileLabel);

        // Drag & Drop PDF area
        dropPdfLabel = new JLabel("Drag & Drop PDF Here");
        dropPdfLabel.setHorizontalAlignment(SwingConstants.CENTER);
        dropPdfLabel.setBorder(BorderFactory.createDashedBorder(Color.GRAY));
        dropPdfLabel.setBounds(30, 280, 430, 80);
        new DropTarget(dropPdfLabel, new PdfDropListener());
        add(dropPdfLabel);

        // Drag & Drop Tile area
        dropTileLabel = new JLabel("Drag & Drop Tile Here");
        dropTileLabel.setHorizontalAlignment(SwingConstants.CENTER);
        dropTileLabel.setBorder(BorderFactory.createDashedBorder(Color.GRAY));
        dropTileLabel.setBounds(30, 370, 430, 80);
        new DropTarget(dropTileLabel, new TileDropListener());
        add(dropTileLabel);

        // Save Button
        JButton saveBtn = new JButton("Generate SQL Query");
        saveBtn.setBounds(120, 480, 160, 30);
        saveBtn.addActionListener(e -> saveBook());
        add(saveBtn);

        // Extract Button
        JButton extractBtn = new JButton("Extract");
        extractBtn.setBounds(300, 480, 120, 30);
        extractBtn.addActionListener(e -> extractFromClipboard());
        add(extractBtn);

        setVisible(true);
    }

    private void chooseTile() {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int res = chooser.showOpenDialog(this);
        if (res == JFileChooser.APPROVE_OPTION) {
            selectedTileFile = chooser.getSelectedFile();
            bookTileLabel.setText(selectedTileFile.getName());
        }
    }

        private void saveBook() {
            if (selectedPdfFile == null) {
                JOptionPane.showMessageDialog(this, "Please drag and drop a PDF file first!");
                return;
            }

            String author = authorField.getText().trim();
            String bookName = bookNameField.getText().trim();
            String category = categoryField.getText().trim();
            String description = descriptionArea.getText().trim();

            if (author.isEmpty() || bookName.isEmpty() || category.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Author, Book Name, and Category cannot be empty!");
                return;
            }

            try {
                // Clean PDF name
                String originalPdfName = selectedPdfFile.getName();
                String cleanedPdfName = originalPdfName.replace("_OceanofPDF.com_", "");

                // Determine next bookIndex (by scanning tiles)
                int nextBookIndex = 1;
                File[] existingTiles = defaultTileFolder.listFiles((dir, name) -> name.matches("\\d+\\.png"));
                if (existingTiles != null && existingTiles.length > 0) {
                    for (File f : existingTiles) {
                        try {
                            int num = Integer.parseInt(f.getName().replace(".png", ""));
                            if (num >= nextBookIndex) nextBookIndex = num + 1;
                        } catch (NumberFormatException ignored) {}
                    }
                }

                // Generate SQL query
                String sqlQuery = String.format(
                    "INSERT INTO bookStack (bookName, author, category, description, fileName) VALUES ('%s', '%s', '%s', '%s', '%s');",
                    bookName.replace("'", "''"),
                    author.replace("'", "''"),
                    category.replace("'", "''"),
                    description.replace("'", "''"),
                    cleanedPdfName.replace("'", "''")
                );

                // Copy to clipboard
                Toolkit.getDefaultToolkit().getSystemClipboard()
                    .setContents(new java.awt.datatransfer.StringSelection(sqlQuery), null);

                // Reset all fields and selections
                authorField.setText("");
                bookNameField.setText("");
                categoryField.setText("");
                descriptionArea.setText("");
                selectedPdfFile = null;
                selectedTileFile = null;
                bookTileLabel.setText("No Tile Selected");
                dropPdfLabel.setText("Drag & Drop PDF Here");
                dropTileLabel.setText("Drag & Drop Tile Here");

                JOptionPane.showMessageDialog(this, "SQL query copied to clipboard! Form has been reset.");

            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error generating SQL query: " + ex.getMessage());
            }
        }



    private void extractFromClipboard() {
        try {
            String clipboardText = (String) Toolkit.getDefaultToolkit()
                    .getSystemClipboard().getData(DataFlavor.stringFlavor);

            JTextArea textArea = new JTextArea(clipboardText, 15, 40);
            textArea.setLineWrap(true);
            textArea.setWrapStyleWord(true);
            JScrollPane scrollPane = new JScrollPane(textArea);

            int result = JOptionPane.showConfirmDialog(this, scrollPane,
                    "Extract Info from Clipboard", JOptionPane.OK_CANCEL_OPTION);

            if (result == JOptionPane.OK_OPTION) {
                String bookName = "";
                String author = "";
                String category = "";

                for (String line : clipboardText.split("\\r?\\n")) {
                    line = line.trim();
                    if (line.startsWith("Book 📖")) {
                        bookName = line.replace("Book 📖", "").trim();
                    } else if (line.startsWith("Author ✒️")) {
                        author = line.replace("Author ✒️", "").trim();
                    } else if (line.startsWith("Category 📚")) {
                        category = line.replace("Category 📚", "").trim();
                    }
                }

                bookNameField.setText(bookName);
                authorField.setText(author);
                categoryField.setText(category);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to read clipboard.");
        }
    }

    private class PdfDropListener extends DropTargetAdapter {
        @Override
        public void drop(DropTargetDropEvent dtde) {
            try {
                dtde.acceptDrop(DnDConstants.ACTION_COPY);
                java.util.List<File> droppedFiles = (java.util.List<File>) dtde.getTransferable()
                        .getTransferData(DataFlavor.javaFileListFlavor);

                for (File file : droppedFiles) {
                    if (file.getName().toLowerCase().endsWith(".pdf")) {
                        selectedPdfFile = file;
                        String displayName = selectedPdfFile.getName().replace("_OceanofPDF.com_", "");
                        dropPdfLabel.setText("Selected: " + displayName);
                        break;
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    private class TileDropListener extends DropTargetAdapter {
        @Override
        public void drop(DropTargetDropEvent dtde) {
            try {
                dtde.acceptDrop(DnDConstants.ACTION_COPY);
                java.util.List<File> droppedFiles = (java.util.List<File>) dtde.getTransferable()
                        .getTransferData(DataFlavor.javaFileListFlavor);

                for (File file : droppedFiles) {
                    if (file.getName().toLowerCase().matches(".*\\.(png|jpg|jpeg|bmp|gif)$")) {
                        selectedTileFile = file;
                        bookTileLabel.setText("Selected: " + file.getName());
                        break;
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception ex) {
            System.err.println("Failed to initialize FlatLaf");
        }
        SwingUtilities.invokeLater(bookToStack::new);
    }
}
