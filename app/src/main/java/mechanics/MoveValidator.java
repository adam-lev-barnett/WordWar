package mechanics;

import board.BoardSquare;
import board.GameBoard;
import mechanics.dictionary.Dictionary;
import mechanics.pieceplacement.Direction;
import mechanics.turn.Submission;
import mechanics.turn.scoring.ScoreCalculator;
import tiles.LetterTile;

public enum MoveValidator {
    INSTANCE;

    public boolean validateMove(GameBoard board, Submission submission) {
        if (board == null || submission == null || submission.tiles() == null || submission.tiles().isEmpty()) {
            return false;
        }

        int row = submission.row();
        int col = submission.col();
        Direction dir = (submission.direction() != null) ? submission.direction() : Direction.HORIZONTAL;
        int numTiles = submission.tiles().size();

        // 1. Can't play tiles beyond bounds of board
        if (row < 0 || col < 0) return false;
        if (dir == Direction.HORIZONTAL && col + numTiles > board.getNumCols()) return false;
        if (dir == Direction.VERTICAL && row + numTiles > board.getNumRows()) return false;

        // 2. Can't play over already occupied squares
        for (int i = 0; i < numTiles; i++) {
            int r = (dir == Direction.VERTICAL) ? row + i : row;
            int c = (dir == Direction.HORIZONTAL) ? col + i : col;
            BoardSquare square = board.getSquare(r, c);
            if (square == null || square.isFilled()) return false;
        }

        // 3. First move must cover center square (7, 7); subsequent moves must connect to existing tiles
        if (board.isBoardEmpty()) {
            if (!coversCenterSquare(submission)) return false;
        } else {
            if (!hasAdjacentTiles(board, submission)) return false;
        }

        // 4. Validate main word in dictionary
        String mainWord = buildWord(board, submission);
        boolean hasMainWord = mainWord.length() >= 2;
        if (hasMainWord && !Dictionary.INSTANCE.validateWord(mainWord)) {
            return false;
        }

        // 5. Validate all cross-words in dictionary
        boolean hasCrossWord = false;
        for (int i = 0; i < numTiles; i++) {
            int r = (dir == Direction.VERTICAL) ? row + i : row;
            int c = (dir == Direction.HORIZONTAL) ? col + i : col;
            String crossWord = buildCrossWord(board, submission.tiles().get(i), r, c, dir);
            if (crossWord != null) {
                hasCrossWord = true;
                if (!Dictionary.INSTANCE.validateWord(crossWord)) {
                    return false;
                }
            }
        }

        return hasMainWord || hasCrossWord;
    }

    public boolean coversCenterSquare(Submission submission) {
        if (submission == null || submission.tiles() == null) return false;
        int row = submission.row();
        int col = submission.col();
        Direction dir = (submission.direction() != null) ? submission.direction() : Direction.HORIZONTAL;
        int numTiles = submission.tiles().size();

        for (int i = 0; i < numTiles; i++) {
            int r = (dir == Direction.VERTICAL) ? row + i : row;
            int c = (dir == Direction.HORIZONTAL) ? col + i : col;
            if (r == 7 && c == 7) return true;
        }
        return false;
    }

    public boolean hasAdjacentTiles(GameBoard board, Submission submission) {
        if (board == null || submission == null || submission.tiles() == null) return false;
        int row = submission.row();
        int col = submission.col();
        Direction dir = (submission.direction() != null) ? submission.direction() : Direction.HORIZONTAL;
        int numTiles = submission.tiles().size();

        for (int i = 0; i < numTiles; i++) {
            int r = (dir == Direction.VERTICAL) ? row + i : row;
            int c = (dir == Direction.HORIZONTAL) ? col + i : col;
            if (hasAdjacentFilledSquare(board, r, c)) {
                return true;
            }
        }
        return false;
    }

    public boolean hasAdjacentFilledSquare(GameBoard board, int row, int col) {
        if (board == null) return false;
        int[][] diffs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        for (int[] d : diffs) {
            int adjRow = row + d[0];
            int adjCol = col + d[1];
            if (board.isSquareInBounds(adjRow, adjCol)) {
                BoardSquare square = board.getSquare(adjRow, adjCol);
                if (square != null && square.isFilled()) return true;
            }
        }
        return false;
    }

    public String buildWord(GameBoard board, Submission submission) {
        if (submission == null || submission.tiles() == null) return "";
        int row = submission.row();
        int col = submission.col();
        int numTiles = submission.tiles().size();
        Direction dir = (submission.direction() != null) ? submission.direction() : Direction.HORIZONTAL;

        StringBuilder sb = new StringBuilder();
        sb.append(getStartOfWord(board, row, col, dir));

        for (LetterTile tile : submission.tiles()) {
            sb.append(tile.getLetter());
        }

        if (dir == Direction.HORIZONTAL) {
            sb.append(getEndOfWord(board, row, col + numTiles, dir));
        } else {
            sb.append(getEndOfWord(board, row + numTiles, col, dir));
        }

        return sb.toString();
    }

    public String buildCrossWord(GameBoard board, LetterTile tile, int row, int col, Direction moveDirection) {
        if (tile == null || moveDirection == null) return null;
        Direction crossDir = moveDirection.perpendicular();
        String start = getStartOfWord(board, row, col, crossDir);
        int endRow = (crossDir == Direction.VERTICAL) ? row + 1 : row;
        int endCol = (crossDir == Direction.HORIZONTAL) ? col + 1 : col;
        String end = getEndOfWord(board, endRow, endCol, crossDir);

        if (start.isEmpty() && end.isEmpty()) {
            return null;
        }
        return start + tile.getLetter() + end;
    }

    public String getStartOfWord(GameBoard board, int row, int col, Direction direction) {
        StringBuilder sb = new StringBuilder();
        for (LetterTile tile : ScoreCalculator.INSTANCE.getStartTiles(board, row, col, direction)) {
            sb.append(tile.getLetter());
        }
        return sb.toString();
    }

    public String getEndOfWord(GameBoard board, int row, int col, Direction direction) {
        StringBuilder sb = new StringBuilder();
        for (LetterTile tile : ScoreCalculator.INSTANCE.getEndTiles(board, row, col, direction)) {
            sb.append(tile.getLetter());
        }
        return sb.toString();
    }
}
