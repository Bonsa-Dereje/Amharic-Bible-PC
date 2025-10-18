import faiss
import numpy as np
import json
import sys
import os
import re

# -----------------------------
# Config paths
# -----------------------------
# When running as a PyInstaller .exe, __file__ points to a temp folder.
# So we detect that and use sys.executable instead to find the real folder.
if getattr(sys, 'frozen', False):
    BASE_DIR = os.path.dirname(sys.executable)
else:
    BASE_DIR = os.path.dirname(os.path.abspath(__file__))

VECTOR_FILE = os.path.join(BASE_DIR, "nlsVectors.json")  # verse-level vector file

# -----------------------------
# Load precomputed vectors and metadata
# -----------------------------
if not os.path.exists(VECTOR_FILE):
    print(f"❌ nlsVectors.json not found in:\n{BASE_DIR}")
    sys.exit(1)

with open(VECTOR_FILE, "r", encoding="utf-8") as f:
    entries = json.load(f)

# Convert vectors to NumPy array
vectors = np.array([e["vector"] for e in entries], dtype=np.float32)
dim = vectors.shape[1]

# Build FAISS index
index = faiss.IndexFlatIP(dim)  # Inner product similarity
faiss.normalize_L2(vectors)
index.add(vectors)

# -----------------------------
# Parse command-line arguments
# -----------------------------
if len(sys.argv) < 2:
    print("Usage: nlSearch <query> [num_matches]")
    sys.exit(1)

query_arg = sys.argv[1]
k = int(sys.argv[2]) if len(sys.argv) > 2 else 5  # Default top 5 matches

# -----------------------------
# Find query vector
# -----------------------------
# Use a verse containing the query text as the query vector
found = next((e for e in entries if query_arg.lower() in e["text"].lower()), None)
if not found:
    print(f"❌ Could not find any verse containing \"{query_arg}\" to use as a query vector.")
    sys.exit(1)

query_vec = np.array(found["vector"], dtype=np.float32).reshape(1, -1)
faiss.normalize_L2(query_vec)

# -----------------------------
# Search top k matches
# -----------------------------
scores, indices = index.search(query_vec, k)

# -----------------------------
# Print results
# -----------------------------
print(f"\nTop {k} matches for query: \"{query_arg}\"\n")
for rank, (idx, score) in enumerate(zip(indices[0], scores[0]), start=1):
    entry = entries[idx]
    book = entry.get("book", "Unknown")
    chapter = entry.get("chapter", "N/A")
    verse = entry.get("verse", "N/A")
    
    # Clean up the verse text
    text = entry.get("text", "")
    text = text.replace("\r", " ").replace("\n", " ")
    text = re.sub(r'\s+', ' ', text).strip()  # Shrink multiple spaces/tabs/newlines to one space
    
    snippet = text[:150] + "..." if len(text) > 150 else text
    print(f"{rank}. {book} {chapter}:{verse} — {snippet} (score: {score:.4f})")
