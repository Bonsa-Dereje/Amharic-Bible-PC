import json

# Path to your embeddings JSON
with open("nlsVectors.json", "r", encoding="utf-8") as f:
    data = json.load(f)

books = []
chapters = []
verses = []
texts = []
vectors = []

for entry in data:
    books.append(entry["book"])
    chapters.append(entry["chapter"])
    verses.append(entry["verse"])
    texts.append(entry["text"])
    vectors.append(entry["vector"])

# Build Java-style arrays
print("String[] books = {")
print(",\n".join(f"\"{b}\"" for b in books))
print("};\n")

print("String[] chapters = {")
print(",\n".join(f"\"{c}\"" for c in chapters))
print("};\n")

print("String[] verses = {")
print(",\n".join(f"\"{v}\"" for v in verses))
print("};\n")

print("String[] texts = {")
print(",\n".join(f"\"{t.replace('\"', '\\\"')}\"" for t in texts))
print("};\n")

print("float[][] vectors = {")
print(",\n".join(
    "{" + ", ".join(f"{x:.6f}" for x in vec) + "}" for vec in vectors
))
print("};")
