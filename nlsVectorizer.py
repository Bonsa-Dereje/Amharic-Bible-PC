from sentence_transformers import SentenceTransformer
import json
import os
import re

# -----------------------------
# Config paths
# -----------------------------
BASE_DIR = os.path.dirname(os.path.abspath(__file__))
NLSER_FOLDER = os.path.join(BASE_DIR, "nlser")
OUTPUT_FILE = os.path.join(BASE_DIR, "nlsVectors.json")

# -----------------------------
# Load model
# -----------------------------
model = SentenceTransformer("all-MiniLM-L6-v2")

embeddings_index = []

# Regex to capture "verse number + text until next number"
VERSE_PATTERN = re.compile(r'(\d+)\s([^0-9]+)(?=\s\d+|$)')

# -----------------------------
# Process every JSON book
# -----------------------------
for file in os.listdir(NLSER_FOLDER):
    if file.endswith(".json"):
        book_name = file.replace(".json", "")
        file_path = os.path.join(NLSER_FOLDER, file)

        with open(file_path, "r", encoding="utf-8") as f:
            chapters = json.load(f)

        total_verses = 0

        for chapter in chapters:
            chapter_num = chapter.get("chapter", "N/A")
            text = chapter.get("text", "")

            # Extract verse number + text pairs
            verses = VERSE_PATTERN.findall(text)

            # Skip if no verses detected
            if not verses:
                continue

            # Prepare text list for encoding
            verse_texts = [v[1].strip() for v in verses]
            vectors = model.encode(verse_texts, convert_to_numpy=True)

            for i, (num, verse_text) in enumerate(verses):
                embeddings_index.append({
                    "book": book_name,
                    "chapter": chapter_num,
                    "verse": num,
                    "text": verse_text.strip(),
                    "vector": vectors[i].tolist()
                })

            total_verses += len(verses)

        print(f" Encoded {book_name} ({total_verses} verses)")

# -----------------------------
# Save final merged vector JSON
# -----------------------------
with open(OUTPUT_FILE, "w", encoding="utf-8") as f:
    json.dump(embeddings_index, f, ensure_ascii=False)

print(f"\n All done! Verse-level embeddings saved to: {OUTPUT_FILE}")
