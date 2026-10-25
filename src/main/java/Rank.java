/** Ranks, declared lowest to highest so ordinal() gives the card strength. */
public enum Rank {
    TWO("2", 2), THREE("3", 3), FOUR("4", 4), FIVE("5", 5), SIX("6", 6), SEVEN("7", 7),
    EIGHT("8", 8), NINE("9", 9), TEN("10", 10), JACK("J", 11), QUEEN("Q", 12),
    KING("K", 13), ACE("A", 14);

    private final String symbol;
    private final int value;

    Rank(String symbol, int value) {
        this.symbol = symbol;
        this.value = value;
    }

    String getSymbol() { return symbol; }
    int getValue() { return value; }

    /** @return the matching rank, or null if the text is not a valid rank. */
    static Rank fromSymbol(String text) {
        for (Rank r : values()) {
            if (r.symbol.equals(text)) {
                return r;
            }
        }
        return null;
    }
}
