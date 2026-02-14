package board;

/** Represent the available effects each board square can have*/
public enum BoardSquareEffect {

    /** No effect */
    PLAIN,

    /** Letter placed on tile earns double points*/
    DOUBLE_LETTER_SCORE,
    /** Letter placed on tile earns triple points*/
    TRIPLE_LETTER_SCORE,
    /** Entire word with a tile placed on this square this turn earns double points*/
    DOUBLE_WORD_SCORE,
    /** Entire word with a tile placed on this square this turn earns double points*/
    TRIPLE_WORD_SCORE,
    /** Player must swap tiles with another player of their choosing*/
    TILE_SWAP,
    /** Player must remove any word on the board regardless of how it affects other words*/
    REMOVE_WORD,
    /** Player chooses another player who has to discard three tiles and not draw again until the end of their next turn*/
    HALVE_LETTERS,
    /** Player can shuffle any word on the board, regardless of how it affects other words*/
    SHUFFLE_WORD

}
