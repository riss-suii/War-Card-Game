package com.svi.warcard.enums;

/**
 * The four suits of a standard deck.
 */
public enum Suit {
    DIAMONDS("D", "♦"),
    HEARTS("H", "♥"),
    SPADES("S", "♠"),
    CLUBS("C", "♣");

    private final String symbol;
    private final String icon;

    Suit(String symbol, String icon) {
        this.symbol = symbol;
        this.icon = icon;
    }

    /** Returns the one-letter symbol, e.g. "H". */
    public String getSymbol() {
        return symbol;
    }

    /** Returns the Unicode icon, e.g. "♥". */
    public String getIcon() {
        return icon;
    }

    /**
     * Looks up a suit by its symbol.
     */
    public static Suit fromSymbol(String text) {
        for (Suit currentSuit : values()) {
            if (currentSuit.symbol.equals(text)) {
                return currentSuit;
            }
        }
        throw new IllegalArgumentException("Unknown suit \"" + text + "\"");
    }
}