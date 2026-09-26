package mechanics.pieceplacement;

public enum Direction {
    VERTICAL,
    HORIZONTAL;

    public Direction perpendicular() {
        return this == HORIZONTAL ? VERTICAL : HORIZONTAL;
    }
}
