package com.ebma.bibleapp;

import com.formdev.flatlaf.FlatLightLaf;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import javax.swing.*;

public class bookToStack extends JFrame {

    private JTextField authorField, bookNameField, categoryField;
    private JLabel bookTileLabel, dropPdfLabel, dropTileLabel;
    private File selectedPdfFile;
    private File selectedTileFile;

    private final File defaultBookFolder = new File("src/main/resources/bookStack");
    private final File defaultTileFolder = new File("src/main/resources/bookTiles");

    public bookToStack() {
        setTitle("Add Book to Stack");
        setSize(500, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        // Author Name
        JLabel authorLabel = new JLabel("Author:");
        authorLabel.setBounds(30, 30, 100, 25);
        add(authorLabel);

        authorField = new JTextField();
        authorField.setBounds(140, 30, 300, 25);
        add(authorField);

        // Book Name
        JLabel bookLabel = new JLabel("Book Name:");
        bookLabel.setBounds(30, 70, 100, 25);
        add(bookLabel);

        bookNameField = new JTextField();
        bookNameField.setBounds(140, 70, 300, 25);
        add(bookNameField);

        // Category
        JLabel categoryLabel = new JLabel("Category:");
        categoryLabel.setBounds(30, 110, 100, 25);
        add(categoryLabel);

        categoryField = new JTextField();
        categoryField.setBounds(140, 110, 300, 25);
        add(categoryField);

        // Book Tile button
        JButton chooseTileBtn = new JButton("Choose Book Tile");
        chooseTileBtn.setBounds(30, 150, 150, 25);
        chooseTileBtn.addActionListener(e -> chooseTile());
        add(chooseTileBtn);

        bookTileLabel = new JLabel("No Tile Selected");
        bookTileLabel.setBounds(190, 150, 250, 25);
        add(bookTileLabel);

        // Drag & Drop PDF area
        dropPdfLabel = new JLabel("Drag & Drop PDF Here");
        dropPdfLabel.setHorizontalAlignment(SwingConstants.CENTER);
        dropPdfLabel.setBorder(BorderFactory.createDashedBorder(Color.GRAY));
        dropPdfLabel.setBounds(30, 190, 410, 100);
        new DropTarget(dropPdfLabel, new PdfDropListener());
        add(dropPdfLabel);

        // Drag & Drop Tile area
        dropTileLabel = new JLabel("Drag & Drop Tile Here");
        dropTileLabel.setHorizontalAlignment(SwingConstants.CENTER);
        dropTileLabel.setBorder(BorderFactory.createDashedBorder(Color.GRAY));
        dropTileLabel.setBounds(30, 300, 410, 100);
        new DropTarget(dropTileLabel, new TileDropListener());
        add(dropTileLabel);

        // Save Button
        JButton saveBtn = new JButton("Save Book");
        saveBtn.setBounds(180, 420, 120, 30);
        saveBtn.addActionListener(e -> saveBook());
        add(saveBtn);

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

        if (author.isEmpty() || bookName.isEmpty() || category.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Author, Book Name, and Category cannot be empty!");
            return;
        }

        try {
            // Remove "_OceanofPDF.com_" from PDF filename
            String originalPdfName = selectedPdfFile.getName();
            String cleanedPdfName = originalPdfName.replace("_OceanofPDF.com_", "");

            // Copy PDF to default folder with cleaned name
            File destPdf = new File(defaultBookFolder, cleanedPdfName);
            Files.copy(selectedPdfFile.toPath(), destPdf.toPath(), StandardCopyOption.REPLACE_EXISTING);

            // Copy Tile
            String tileRelativePath = null;
            if (selectedTileFile != null) {
                File destTile = new File(defaultTileFolder, selectedTileFile.getName());
                Files.copy(selectedTileFile.toPath(), destTile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                tileRelativePath = "bookTiles/" + selectedTileFile.getName();
            }

            // Relative PDF path
            String pdfRelativePath = "bookStack/" + cleanedPdfName;

            // Generate SQLite INSERT including category
            String insertQuery = String.format(
                    "INSERT INTO bookStack (bookName, tag, path, bookTile, text, author, category) " +
                            "VALUES ('%s', '%s', '%s', %s, %s, '%s', '%s');",
                    bookName,
                    "Bible", // placeholder tag
                    pdfRelativePath,
                    tileRelativePath != null ? "'" + tileRelativePath + "'" : "NULL",
                    "true",
                    author,
                    category
            );

            // Copy query to clipboard
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(
                    new java.awt.datatransfer.StringSelection(insertQuery), null);

            JOptionPane.showMessageDialog(this, "Book saved!\nQuery copied to clipboard.");

        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error saving files: " + ex.getMessage());
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
                        JOptionPane.showMessageDialog(bookToStack.this, "PDF Selected: " + displayName);
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
                        bookTileLabel.setText(file.getName());
                        JOptionPane.showMessageDialog(bookToStack.this, "Tile Selected: " + file.getName());
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
