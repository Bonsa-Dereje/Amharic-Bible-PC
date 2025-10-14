from sentence_transformers import SentenceTransformer
import faiss
import numpy as np
import json
import sys
import os

# -----------------------------
# Config paths
# -----------------------------
BASE_DIR = os.path.dirname(os.path.abspath(__file__))
VECTOR_FILE = os.path.join(BASE_DIR, "nlsVectors.json")  # merged vector file

# -----------------------------
# Load model
# -----------------------------
model = SentenceTransformer("all-MiniLM-L6-v2")

# -----------------------------
# Load precomputed vectors and metadata
# -----------------------------
with open(VECTOR_FILE, "r", encoding="utf-8") as f:
    entries = json.load(f)

# Convert vectors to numpy array
vectors = np.array([e["vector"] for e in entries], dtype=np.float32)
dim = vectors.shape[1]

# Build FAISS index
index = faiss.IndexFlatIP(dim)  # Inner product = cosine similarity if vectors are normalized
faiss.normalize_L2(vectors)     # normalize for cosine similarity
index.add(vectors)

# -----------------------------
# Get query
# -----------------------------
if len(sys.argv) < 2:
    print("⚠️  Please provide a search query.")
    sys.exit(1)

query = sys.argv[1]
query_vec = model.encode(query, convert_to_numpy=True).reshape(1, -1).astype(np.float32)
faiss.normalize_L2(query_vec)

# -----------------------------
# Search top 5 matches
# -----------------------------
k = 5
scores, indices = index.search(query_vec, k)

# -----------------------------
# Print results
# -----------------------------
print(f"\n🔍 Top {k} matches for query: \"{query}\"\n")

for rank, (idx, score) in enumerate(zip(indices[0], scores[0]), start=1):
    entry = entries[idx]
    book = entry["book"]
    chapter = entry["chapter"]
    text = entry["text"].replace("\r", " ").replace("\n", " ")
    snippet = text[:150] + "..." if len(text) > 150 else text
    print(f"{rank}. {book} — Chapter {chapter} — {snippet} (score: {score:.4f})")
