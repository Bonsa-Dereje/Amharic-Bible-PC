from sentence_transformers import SentenceTransformer
import json
import os


BASE_DIR = os.path.dirname(os.path.abspath(__file__))          # current folder (BibleApp/)
NLSER_FOLDER = os.path.join("nlser")                 # where per-book JSONs are saved
OUTPUT_FILE = os.path.join("nlsVectors.json")        # output file in project root


model = SentenceTransformer("all-MiniLM-L6-v2")

embeddings_index = {}


for file in os.listdir(NLSER_FOLDER):
    if file.endswith(".json"):
        book_name = file.replace(".json", "")
        file_path = os.path.join(NLSER_FOLDER, file)

        with open(file_path, "r", encoding="utf-8") as f:
            verses = json.load(f)

        texts = [v["text"] for v in verses]
        vectors = model.encode(texts, convert_to_numpy=True)

        embeddings_index[book_name] = vectors.tolist()
        print(f" Encoded {book_name} ({len(texts)} verses)")


with open(OUTPUT_FILE, "w", encoding="utf-8") as f:
    json.dump(embeddings_index, f)

print(f"\n All done! Embeddings saved to: {OUTPUT_FILE}")
