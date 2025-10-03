package com.ebma.bibleapp;

import javax.swing.*;

import com.formdev.flatlaf.FlatLightLaf;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class tilizer extends JFrame {

    private JTextField sourceField;
    private JTextField destField;
    private JTextField widthField;
    private JTextField heightField;
    private JTextArea logArea;

    public tilizer() {
        setTitle("Book Tile Tilizer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 500);
        setLocationRelativeTo(null);
        initComponents();
    }

    private void initComponents() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel sourceLabel = new JLabel("Source Folder:");
        sourceField = new JTextField("src/main/resources/bookTiles");
        JButton sourceBtn = new JButton("Browse");
        sourceBtn.addActionListener(this::browseSource);

        JLabel destLabel = new JLabel("Destination Folder:");
        destField = new JTextField("src/main/resources/tinyBookTiles");
        JButton destBtn = new JButton("Browse");
        destBtn.addActionListener(this::browseDest);

        JLabel widthLabel = new JLabel("Width:");
        widthField = new JTextField("400");

        JLabel heightLabel = new JLabel("Height:");
        heightField = new JTextField("285");

        JButton generateBtn = new JButton("Generate Tiles");
        generateBtn.addActionListener(this::generateTiles);

        logArea = new JTextArea(15, 50);
        logArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(logArea);

        gbc.gridx = 0; gbc.gridy = 0; panel.add(sourceLabel, gbc);
        gbc.gridx = 1; gbc.gridy = 0; panel.add(sourceField, gbc);
        gbc.gridx = 2; gbc.gridy = 0; panel.add(sourceBtn, gbc);

        gbc.gridx = 0; gbc.gridy = 1; panel.add(destLabel, gbc);
        gbc.gridx = 1; gbc.gridy = 1; panel.add(destField, gbc);
        gbc.gridx = 2; gbc.gridy = 1; panel.add(destBtn, gbc);

        gbc.gridx = 0; gbc.gridy = 2; panel.add(widthLabel, gbc);
        gbc.gridx = 1; gbc.gridy = 2; panel.add(widthField, gbc);

        gbc.gridx = 0; gbc.gridy = 3; panel.add(heightLabel, gbc);
        gbc.gridx = 1; gbc.gridy = 3; panel.add(heightField, gbc);

        gbc.gridx = 1; gbc.gridy = 4; panel.add(generateBtn, gbc);

        gbc.gridx = 0; gbc.gridy = 5; gbc.gridwidth = 3; panel.add(scrollPane, gbc);

        add(panel);
    }

    private void browseSource(ActionEvent e) {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            sourceField.setText(chooser.getSelectedFile().getAbsolutePath());
        }
    }

    private void browseDest(ActionEvent e) {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        if (chooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            destField.setText(chooser.getSelectedFile().getAbsolutePath());
        }
    }

    private void generateTiles(ActionEvent e) {
        String sourcePath = sourceField.getText();
        String destPath = destField.getText();
        int width = Integer.parseInt(widthField.getText());
        int height = Integer.parseInt(heightField.getText());

        File sourceFolder = new File(sourcePath);
        File destFolder = new File(destPath);
        if (!destFolder.exists()) destFolder.mkdirs();

        File[] files = sourceFolder.listFiles((dir, name) ->
                name.toLowerCase().matches(".*\\.(png|jpg|jpeg|bmp|gif)$")
        );

        if (files == null || files.length == 0) {
            log("No image files found in: " + sourcePath);
            return;
        }

        new Thread(() -> {
            for (File file : files) {
                try {
                    BufferedImage original = ImageIO.read(file);
                    if (original == null) {
                        log("Skipping non-image: " + file.getName());
                        continue;
                    }
                    BufferedImage tiny = resizeImage(original, width, height);
                    File destFile = new File(destFolder, stripExtension(file.getName()) + ".png");
                    ImageIO.write(tiny, "png", destFile);
                    log("Created tiny tile: " + destFile.getName());
                } catch (IOException ex) {
                    log("Error processing: " + file.getName());
                    ex.printStackTrace();
                }
            }
            log("All done!");
        }).start();
    }

    private void log(String msg) {
        SwingUtilities.invokeLater(() -> logArea.append(msg + "\n"));
    }

    private static BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) {
        BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resizedImage.createGraphics();

        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        double scale = Math.min((double) targetWidth / originalImage.getWidth(),
                                (double) targetHeight / originalImage.getHeight());
        int newWidth = (int) (originalImage.getWidth() * scale);
        int newHeight = (int) (originalImage.getHeight() * scale);

        int x = (targetWidth - newWidth) / 2;
        int y = (targetHeight - newHeight) / 2;

        g2d.setColor(new Color(0, 0, 0, 0));
        g2d.fillRect(0, 0, targetWidth, targetHeight);
        g2d.drawImage(originalImage, x, y, newWidth, newHeight, null);
        g2d.dispose();

        return resizedImage;
    }

    private static String stripExtension(String fileName) {
        int i = fileName.lastIndexOf('.');
        return (i > 0) ? fileName.substring(0, i) : fileName;
    }

    public static void main(String[] args) {
            try {
                FlatLightLaf.setup();
            } catch (Exception ex) {
                System.err.println("Failed to initialize FlatLaf");
            }

        
        SwingUtilities.invokeLater(() -> new tilizer().setVisible(true));
    }
}
