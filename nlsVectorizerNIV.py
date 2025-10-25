from sentence_transformers import SentenceTransformer
import json
import os

# -----------------------------
# Config paths
# -----------------------------
BASE_DIR = os.path.dirname(os.path.abspath(__file__))
NIV_FILE = os.path.join(BASE_DIR, "NIV_bible.json")
OUTPUT_FILE = os.path.join(BASE_DIR, "nlsVectors.json")

# -----------------------------
# Check if file exists
# -----------------------------
if not os.path.exists(NIV_FILE):
    raise FileNotFoundError(f" NIV JSON file not found in project folder: {NIV_FILE}")
else:
    print(f"✅ Found NIV JSON file: {NIV_FILE}")

# -----------------------------
# Load model
# -----------------------------
model = SentenceTransformer("all-MiniLM-L6-v2")

embeddings_index = []

# -----------------------------
# Load NIV JSON
# -----------------------------
with open(NIV_FILE, "r", encoding="utf-8") as f:
    bible = json.load(f)  # now a dict: { "Genesis": { "1": { "1": "...", ... } } }

total_verses_all_books = 0

for book_name, chapters in bible.items():
    total_verses_book = 0
    for chapter_num, verses in chapters.items():
        for verse_num, verse_text in verses.items():
            vector = model.encode(verse_text, convert_to_numpy=True)
            embeddings_index.append({
                "book": book_name,
                "chapter": chapter_num,
                "verse": verse_num,
                "text": verse_text,
                "vector": vector.tolist()
            })
            total_verses_book += 1
    print(f"Encoded {book_name} ({total_verses_book} verses)")
    total_verses_all_books += total_verses_book

# -----------------------------
# Save final merged vector JSON
# -----------------------------
with open(OUTPUT_FILE, "w", encoding="utf-8") as f:
    json.dump(embeddings_index, f, ensure_ascii=False)

print(f"\nAll done! Total verses encoded: {total_verses_all_books}")
print(f"Verse-level embeddings saved to: {OUTPUT_FILE}")
