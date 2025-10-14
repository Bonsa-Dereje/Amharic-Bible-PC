# nlSearch.py
import json
import sys
import numpy as np
from sentence_transformers import SentenceTransformer

# --- Configuration ---
VECTORS_JSON = "nlsVectors.json"  # Make sure this is in your project root
TOP_K = 5  # number of results to show

# --- Load vectors from JSON ---
with open(VECTORS_JSON, "r", encoding="utf-8") as f:
    chapters = json.load(f)  # expect list of dicts: {"book":..., "chapter":..., "vector": [...]}

# Convert vectors to NumPy array for fast computation
vectors = np.array([np.array(chap["vector"]) for chap in chapters])

# --- Load model ---
model = SentenceTransformer('all-MiniLM-L6-v2')  # or whichever model you used for vectorization

# --- Get query from command-line argument ---
if len(sys.argv) < 2:
    print("Usage: python nlSearch.py \"search query here\"")
    sys.exit(1)

query = sys.argv[1]
query_vector = model.encode(query)

# --- Cosine similarity function ---
def cosine_sim(a, b):
    return np.dot(a, b) / (np.linalg.norm(a) * np.linalg.norm(b))

# --- Compute similarities ---
sims = np.array([cosine_sim(query_vector, v) for v in vectors])
top_indexes = sims.argsort()[::-1][:TOP_K]

# --- Print top results ---
print(f"Top {TOP_K} chapters for query: \"{query}\"")
for idx in top_indexes:
    chap = chapters[idx]
    print(f"{chap['book']} {chap['chapter']}  (score: {sims[idx]:.4f})")
