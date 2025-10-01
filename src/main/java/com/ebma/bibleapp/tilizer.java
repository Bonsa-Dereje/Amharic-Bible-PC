package com.ebma.bibleapp;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class tilizer {

    private static final File bookTilesFolder = new File("src/main/resources/bookTiles");
    private static final File tinyTilesFolder = new File("src/main/resources/tinyBookTiles");
    private static final int TINY_WIDTH = 400;
    private static final int TINY_HEIGHT = 285;

    public static void main(String[] args) {
        if (!bookTilesFolder.exists() || !bookTilesFolder.isDirectory()) {
            System.out.println("Book tiles folder does not exist: " + bookTilesFolder.getAbsolutePath());
            return;
        }

        if (!tinyTilesFolder.exists()) {
            tinyTilesFolder.mkdirs();
        }

        File[] files = bookTilesFolder.listFiles((dir, name) ->
                name.toLowerCase().matches(".*\\.(png|jpg|jpeg|bmp|gif)$")
        );

        if (files == null || files.length == 0) {
            System.out.println("No image files found in " + bookTilesFolder.getAbsolutePath());
            return;
        }

        for (File file : files) {
            try {
                BufferedImage originalImage = ImageIO.read(file);
                if (originalImage == null) {
                    System.out.println("Skipping non-image file: " + file.getName());
                    continue;
                }

                BufferedImage tinyImage = resizeImage(originalImage, TINY_WIDTH, TINY_HEIGHT);
                String tinyName = stripExtension(file.getName()) + ".png";
                File destFile = new File(tinyTilesFolder, tinyName);
                ImageIO.write(tinyImage, "png", destFile);

                System.out.println("Created tiny tile: " + destFile.getName());
            } catch (IOException e) {
                System.err.println("Error processing file: " + file.getName());
                e.printStackTrace();
            }
        }

        System.out.println("All done!");
    }

    private static BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) {
        BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resizedImage.createGraphics();

        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Scale while maintaining aspect ratio
        double scale = Math.min((double) targetWidth / originalImage.getWidth(),
                                (double) targetHeight / originalImage.getHeight());
        int newWidth = (int) (originalImage.getWidth() * scale);
        int newHeight = (int) (originalImage.getHeight() * scale);

        int x = (targetWidth - newWidth) / 2;
        int y = (targetHeight - newHeight) / 2;

        g2d.setColor(new Color(0, 0, 0, 0)); // transparent background
        g2d.fillRect(0, 0, targetWidth, targetHeight);
        g2d.drawImage(originalImage, x, y, newWidth, newHeight, null);
        g2d.dispose();

        return resizedImage;
    }

    private static String stripExtension(String fileName) {
        int i = fileName.lastIndexOf('.');
        return (i > 0) ? fileName.substring(0, i) : fileName;
    }
}
