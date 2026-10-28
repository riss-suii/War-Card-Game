package com.svi.warcard.enums;

/** Ranks, declared lowest to highest so ordinal() gives the card strength. */
public enum Rank {
    TWO("2"), THREE("3"), FOUR("4"), FIVE("5"), SIX("6"), SEVEN("7"),
    EIGHT("8"), NINE("9"), TEN("10"), JACK("J"), QUEEN("Q"),
    KING("K"), ACE("A");

    private final String symbol;

    Rank(String symbol) { this.symbol = symbol; }

    public String getSymbol() { return symbol; }

    /** @return the matching rank, or null if the text is not a valid rank. */
    public static Rank fromSymbol(String text) {
        for (Rank r : values()) {
            if (r.symbol.equals(text)) {
                return r;
            }
        }
        return null;
    }
}
