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
import java.sql.*;
import javax.imageio.ImageIO;
import javax.swing.*;

public class bookToStack extends JFrame {

    private JTextField authorField, bookNameField, categoryField;
    private JLabel bookTileLabel, dropPdfLabel, dropTileLabel;
    private File selectedPdfFile;
    private File selectedTileFile;

    private final File defaultBookFolder = new File("src/main/resources/bookStack");
    private final File defaultTileFolder = new File("src/main/resources/bookTiles");
    private final String dbPath = "jdbc:sqlite:src/main/resources/bookStack.db";

    public bookToStack() {
        setTitle("Add Book to Stack");
        setSize(500, 520);
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
        saveBtn.setBounds(100, 420, 120, 30);
        saveBtn.addActionListener(e -> saveBook());
        add(saveBtn);

        // Extract Button
        JButton extractBtn = new JButton("Extract");
        extractBtn.setBounds(260, 420, 120, 30);
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

        // Determine next bookIndex by scanning existing tiles
        int nextBookIndex = 1;
        File[] existingTiles = defaultTileFolder.listFiles((dir, name) -> name.matches("\\d+\\.png"));
        if (existingTiles != null && existingTiles.length > 0) {
            for (File f : existingTiles) {
                String nameWithoutExt = f.getName().replace(".png", "");
                try {
                    int num = Integer.parseInt(nameWithoutExt);
                    if (num >= nextBookIndex) nextBookIndex = num + 1;
                } catch (NumberFormatException ignored) {}
            }
        }

        // Copy and resize Tile with name as nextBookIndex
        if (selectedTileFile != null) {
            BufferedImage originalImage = ImageIO.read(selectedTileFile);
            int canvasWidth = 182;
            int canvasHeight = 286;

            BufferedImage resizedImage = new BufferedImage(canvasWidth, canvasHeight, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = resizedImage.createGraphics();
            g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
            g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            double scale = Math.min((double) canvasWidth / originalImage.getWidth(),
                                    (double) canvasHeight / originalImage.getHeight());
            int newWidth = (int) (originalImage.getWidth() * scale);
            int newHeight = (int) (originalImage.getHeight() * scale);

            int x = (canvasWidth - newWidth) / 2;
            int y = (canvasHeight - newHeight) / 2;

            g2d.setColor(new Color(0,0,0,0));
            g2d.fillRect(0, 0, canvasWidth, canvasHeight);

            g2d.drawImage(originalImage, x, y, newWidth, newHeight, null);
            g2d.dispose();

            File destTile = new File(defaultTileFolder, nextBookIndex + ".png");
            ImageIO.write(resizedImage, "png", destTile);
        }

        String pdfRelativePath = "bookStack/" + cleanedPdfName;

        // Build SQL without bookTile column
        String insertQuery = String.format(
                "INSERT INTO bookStack (bookName, tag, path, text, author, category) " +
                        "VALUES ('%s', '%s', '%s', %s, '%s', '%s');",
                bookName,
                "Book",
                pdfRelativePath,
                "true",
                author,
                category
        );

        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(
                new java.awt.datatransfer.StringSelection(insertQuery), null);

        JOptionPane.showMessageDialog(this, "Book saved!\nQuery copied to clipboard.");

        // RESET FORM AFTER SAVE
        authorField.setText("");
        bookNameField.setText("");
        categoryField.setText("");
        selectedPdfFile = null;
        selectedTileFile = null;
        bookTileLabel.setText("No Tile Selected");
        dropPdfLabel.setText("Drag & Drop PDF Here");
        dropTileLabel.setText("Drag & Drop Tile Here");

    } catch (IOException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error saving files: " + ex.getMessage());
    }
}



    private int insertBookAndGetIndex(String bookName, String author, String category, String pdfPath) throws SQLException {
        int bookIndex = -1;
        try (Connection conn = DriverManager.getConnection(dbPath)) {
            String insertSQL = "INSERT INTO bookStack (bookName, tag, path, text, author, category) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, bookName);
            stmt.setString(2, "Book");
            stmt.setString(3, pdfPath);
            stmt.setBoolean(4, true);
            stmt.setString(5, author);
            stmt.setString(6, category);
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                bookIndex = rs.getInt(1);
            }
        }
        return bookIndex;
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
                        bookTileLabel.setText(selectedTileFile.getName());
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
