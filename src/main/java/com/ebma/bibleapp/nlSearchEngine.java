package com.ebma.bibleapp;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class nlSearchEngine {

    private static class VerseVector {
        String text;
        float[] vector;

        VerseVector(String text, float[] vector) {
            this.text = text;
            this.vector = vector;
        }
    }

    private final List<VerseVector> verses = new ArrayList<>();

    public nlSearchEngine(String vectorFilePath) {
        loadVectors(vectorFilePath);
    }

    private void loadVectors(String path) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(path));
            for (String line : lines) {
                if (!line.contains("|")) continue;
                String[] parts = line.split("\\|");
                if (parts.length < 2) continue;

                String text = parts[0].trim();
                String[] nums = parts[1].trim().split("\\s+");
                float[] vector = new float[nums.length];
                for (int i = 0; i < nums.length; i++) {
                    try {
                        vector[i] = Float.parseFloat(nums[i]);
                    } catch (NumberFormatException ignored) {}
                }
                verses.add(new VerseVector(text, vector));
            }
            System.out.println("Loaded " + verses.size() + " verses.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Searches for the top K most similar verses to the given query.
     */
    public List<String> search(String query, int topK) {
        if (verses.isEmpty()) {
            return List.of("No verses loaded.");
        }

        float[] queryVec = fakeVectorFromQuery(query);
        PriorityQueue<Map.Entry<String, Float>> topResults = new PriorityQueue<>(
            Comparator.comparing(Map.Entry::getValue)
        );

        for (VerseVector vv : verses) {
            float sim = cosineSimilarity(queryVec, vv.vector);
            topResults.offer(Map.entry(vv.text, sim));

            if (topResults.size() > topK) {
                topResults.poll(); // keep only topK
            }
        }

        List<Map.Entry<String, Float>> sorted = new ArrayList<>(topResults);
        sorted.sort((a, b) -> Float.compare(b.getValue(), a.getValue()));

        List<String> results = new ArrayList<>();
        for (Map.Entry<String, Float> e : sorted) {
            results.add(String.format("%s  (%.4f)", e.getKey(), e.getValue()));
        }

        return results;
    }

    private float cosineSimilarity(float[] a, float[] b) {
        if (a.length == 0 || b.length == 0) return 0;
        float dot = 0, magA = 0, magB = 0;
        for (int i = 0; i < a.length; i++) {
            dot += a[i] * b[i];
            magA += a[i] * a[i];
            magB += b[i] * b[i];
        }
        return (float) (dot / (Math.sqrt(magA) * Math.sqrt(magB) + 1e-9));
    }

    // TEMPORARY placeholder: replace this once you can embed queries properly
    private float[] fakeVectorFromQuery(String text) {
        if (verses.isEmpty()) return new float[0];
        int dim = verses.get(0).vector.length;
        float[] vec = new float[dim];
        Random rand = new Random(text.hashCode());
        for (int i = 0; i < dim; i++) vec[i] = rand.nextFloat();
        return vec;
    }
}
