package mechanics.dictionary;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

public enum Dictionary {
    INSTANCE;
    private final static Set<String> allowedWords = new HashSet<>();
    private static final String dictPath = "wordList.txt";

    static {
        try (BufferedReader reader = new BufferedReader(new FileReader(dictPath))){
            String word;
            while ((word = reader.readLine()) != null) {
                allowedWords.add(word);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean validateWord(String word) {
        return allowedWords.contains(word);
    }
}
