package board;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Manages all words currently on the board.
 */
public class WordsOnBoard {
    private final List<BoardWord> words = new ArrayList<>();

    public WordsOnBoard() {}

    public synchronized void addWord(BoardWord word) {
        if (word != null) {
            words.add(word);
        }
    }

    public synchronized void addWords(Collection<BoardWord> newWords) {
        if (newWords != null) {
            words.addAll(newWords);
        }
    }

    public synchronized boolean removeWord(BoardWord word) {
        return words.remove(word);
    }

    public synchronized List<BoardWord> getWords() {
        return Collections.unmodifiableList(new ArrayList<>(words));
    }

    public synchronized List<BoardWord> getWordsContaining(int row, int col) {
        List<BoardWord> matching = new ArrayList<>();
        for (BoardWord bw : words) {
            if (bw.occupiesSquare(row, col)) {
                matching.add(bw);
            }
        }
        return matching;
    }

    public synchronized void clear() {
        words.clear();
    }

    public synchronized int size() {
        return words.size();
    }
}
