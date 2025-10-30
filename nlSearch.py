from sentence_transformers import SentenceTransformer
import numpy as np
import faiss
import json
import os
import sys
import re

# Load model (same one you used before)
model = SentenceTransformer("all-MiniLM-L6-v2")

# Load precomputed verse vectors
with open("nlsVectors.json", "r", encoding="utf-8") as f:
    entries = json.load(f)

vectors = np.array([e["vector"] for e in entries], dtype=np.float32)
dim = vectors.shape[1]

# Build FAISS index
index = faiss.IndexFlatIP(dim)
faiss.normalize_L2(vectors)
index.add(vectors)

# Get user query
query_arg = sys.argv[1]
k = int(sys.argv[2]) if len(sys.argv) > 2 else 5

# Embed the query text
query_vec = model.encode(query_arg, normalize_embeddings=True)
query_vec = np.array(query_vec, dtype=np.float32).reshape(1, -1)

# Search
scores, indices = index.search(query_vec, k)

# Print results
print(f'\nTop {k} matches for query: "{query_arg}"\n')
for rank, (idx, score) in enumerate(zip(indices[0], scores[0]), start=1):
    entry = entries[idx]
    book = entry.get("book", "Unknown")
    chapter = entry.get("chapter", "N/A")
    verse = entry.get("verse", "N/A")
    text = re.sub(r"\s+", " ", entry["text"]).strip()
    snippet = text[:150] + "..." if len(text) > 150 else text
    print(f"{rank}. {book} {chapter}:{verse}. {snippet} (score: {score:.4f})")
