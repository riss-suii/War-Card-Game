package com.svi.warcard.enums;

/**
 * Suits, declared lowest to highest: clubs, spades, hearts, diamonds.
 *
 * @author Chariss Borlaza
 * @since 1.0
 */
public enum Suit {
    CLUBS("C"), SPADES("S"), HEARTS("H"), DIAMONDS("D");

    private final String symbol;

    /**
     * Creates a suit.
     */
    Suit(String symbol) {
        this.symbol = symbol;
    }

    /**
     * Gets the suit's letter.
     */
    public String getSymbol() {
        return symbol;
    }

    /**
     * Looks up a suit by its letter.
     */
    public static Suit fromSymbol(String text) {
        for (Suit currentSuit : values()) {
            if (currentSuit.symbol.equals(text)) {
                return currentSuit;
            }
        }
        return null;
    }
}
