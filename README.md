<div align="center">

# 📖 Amharic Bible PC

**A full-featured desktop Bible for Amharic and English speakers**

*Read · Listen · Study · Connect*

[![Java](https://img.shields.io/badge/Java-24-orange?style=flat-square&logo=openjdk)](https://openjdk.org/)
[![JavaFX](https://img.shields.io/badge/JavaFX-21-blue?style=flat-square)](https://openjfx.io/)
[![SQLite](https://img.shields.io/badge/SQLite-3.42-lightgrey?style=flat-square&logo=sqlite)](https://sqlite.org/)
[![Maven](https://img.shields.io/badge/Build-Maven-red?style=flat-square&logo=apachemaven)](https://maven.apache.org/)

</div>

---



| | Feature | Description |
|---|---|---|
| 🌐 | **Dual-language** | Amharic and English Bible side by side |
| 🔊 | **Audio Bible** | Listen to scripture read aloud |
| 📚 | **Commentaries** | In-app commentary support |
| 📖 | **Spiritual books** | Integrated library of spiritual reading material |
| 🗓️ | **Study plans** | Structured reading plans |
| 🔍 | **Semantic search** | NLP-powered related-verse lookup (Python vectorizer) |
| 📝 | **Notes & journals** | Personal notes tied to specific verses |
| 🖊️ | **Highlights** | Highlight verses independently per language |
| 🎙️ | **Live group study** | Audio and video conferencing for group Bible study |

---

## 🛠️ Tech Stack

| Layer | Technology |
|---|---|
| Language | Java 24 |
| UI | Java Swing + JavaFX 21 (WebView, JFXPanel) |
| Look & Feel | FlatLaf 3.4 |
| Database | SQLite via `sqlite-jdbc` 3.42 |
| PDF rendering | Apache PDFBox 2.0 |
| Semantic search | Python (vectorizer compiled via PyInstaller) |
| Build | Maven |

---

## ⚙️ Prerequisites

- JDK 24+
- Maven 3.8+
- Python 3.x *(only if rebuilding the NLS vectorizer)*

---

## 🚀 Build & Run

```bash
git clone https://github.com/Bonsa-Dereje/Amharic-Bible-PC.git
cd Amharic-Bible-PC
mvn clean package
java -jar target/BibleApp-1.0-SNAPSHOT.jar
```

> **Note:** JavaFX modules must be on the module path. If your JDK doesn't bundle JavaFX, add these flags:
> ```
> --module-path /path/to/javafx-sdk/lib \
> --add-modules javafx.controls,javafx.swing,javafx.web
> ```

---

## 🗂️ Project Structure

```
src/main/            # Java source
bookToStack/         # Book-import utilities
nlsEngine/           # Compiled NLS (natural language search) engine
notes/               # Notes feature assets
splicer/             # Verse-splitting utilities
sqbPro/              # SQLite database helpers
nlSearch.py          # Python semantic search entry point
nlsVectorizer.py     # Amharic Bible vectorizer
nlsVectorizerNIV.py  # English (NIV) vectorizer
schema.sql           # Database schema
*.db                 # Local SQLite databases (highlights, notes, search cache)
```

---

## 🔧 Rebuilding the Search Engine

The compiled `nlsEngine` binary is included and ready to use. To rebuild from source:

```bash
pip install sentence-transformers pyinstaller
python nlsVectorizer.py       # Build Amharic index
python nlsVectorizerNIV.py    # Build English (NIV) index
pyinstaller nlSearch.spec     # Package into binary
```

---

## 🤝 Contributing

Pull requests are welcome. Please open an issue first to discuss significant changes.

---

<div align="center">
<sub>Built with ❤️</sub>
</div>
