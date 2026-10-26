/** Suits, declared lowest to highest: clubs, spades, hearts, diamonds. */
public enum Suit {
    CLUBS("C"), SPADES("S"), HEARTS("H"), DIAMONDS("D");

    private final String symbol;

    Suit(String symbol) {
        this.symbol = symbol;
    }

    String getSymbol() {
        return symbol;
    }

    /** @return the matching suit, or null if the text is not a valid suit. */
    public static Suit fromSymbol(String text) {
        for (Suit s : values()) {
            if (s.symbol.equals(text)) {
                return s;
            }
        }
        return null;
    }
}
