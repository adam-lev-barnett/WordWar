package mechanics.dictionary;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public enum Dictionary {
    INSTANCE;

    private final Set<String> allowedWords = new HashSet<>();

    Dictionary() {
        loadDefaultDictionary();
    }

    private void loadDefaultDictionary() {
        // 1. Try loading from classpath resources
        InputStream stream = getClass().getResourceAsStream("/wordList.txt");
        if (stream == null) {
            stream = getClass().getClassLoader().getResourceAsStream("wordList.txt");
        }
        if (stream == null) {
            stream = ClassLoader.getSystemResourceAsStream("wordList.txt");
        }

        if (stream != null) {
            try {
                loadDictionary(stream);
                return;
            } catch (IOException ignored) {
            }
        }

        // 2. Try loading from filesystem fallback paths
        String[] fallbackPaths = new String[]{
                "wordList.txt",
                "app/src/main/resources/wordList.txt",
                "app/src/main/assets/wordList.txt",
                "src/main/resources/wordList.txt",
                "src/main/assets/wordList.txt"
        };

        for (String path : fallbackPaths) {
            File file = new File(path);
            if (file.exists() && file.isFile() && file.length() > 0) {
                try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                    loadDictionary(reader);
                    return;
                } catch (IOException ignored) {
                }
            }
        }
    }

    public synchronized void loadDictionary(InputStream inputStream) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            loadDictionary(reader);
        }
    }

    public synchronized void loadDictionary(Reader reader) throws IOException {
        BufferedReader bufferedReader = (reader instanceof BufferedReader br) ? br : new BufferedReader(reader);
        String word;
        while ((word = bufferedReader.readLine()) != null) {
            word = word.trim().toUpperCase();
            if (!word.isEmpty()) {
                allowedWords.add(word);
            }
        }
    }

    public synchronized boolean validateWord(String word) {
        if (word == null) return false;
        return allowedWords.contains(word.trim().toUpperCase());
    }

    public synchronized void addWord(String word) {
        if (word != null) {
            String trimmed = word.trim().toUpperCase();
            if (!trimmed.isEmpty()) {
                allowedWords.add(trimmed);
            }
        }
    }

    public synchronized void addWords(Collection<String> words) {
        if (words != null) {
            for (String w : words) {
                addWord(w);
            }
        }
    }

    public synchronized void clear() {
        allowedWords.clear();
    }

    public synchronized int size() {
        return allowedWords.size();
    }

    public synchronized boolean contains(String word) {
        return validateWord(word);
    }
}
