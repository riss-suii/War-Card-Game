package com.svi.warcard.enums;

/** Ranks, declared lowest to highest so ordinal() gives the card strength. */
public enum Rank {
    TWO("2"), THREE("3"), FOUR("4"), FIVE("5"), SIX("6"), SEVEN("7"),
    EIGHT("8"), NINE("9"), TEN("10"), JACK("J"), QUEEN("Q"),
    KING("K"), ACE("A");

    private final String symbol;

    /** Creates a rank. */
    Rank(String symbol) { this.symbol = symbol; }

    /** Gets the rank's text symbol. */
    public String getSymbol() { return symbol; }

    /** Looks up a rank by its symbol. */
    public static Rank fromSymbol(String text) {
        for (Rank currentRank : values()) {
            if (currentRank.symbol.equals(text)) {
                return currentRank;
            }
        }
        return null;
    }
}
