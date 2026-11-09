package com.svi.warcard.model;

import com.svi.warcard.enums.Rank;
import com.svi.warcard.enums.Suit;

/** An immutable playing card with a rank and a suit. */

public class Card implements Comparable<Card> {
    private final Rank rank;
    private final Suit suit;

    /** Creates a card. */
    public Card(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    /** Compares this card to another: rank first, then suit as the tiebreaker. */
    @Override
    public int compareTo(Card other) {
        int result = rank.compareTo(other.rank);
        if (result != 0) return result;
        return suit.compareTo(other.suit);
    }

    /** Display format, e.g. ♥H-A. */
    @Override
    public String toString() {
        return suitIcon() + suit.getSymbol() + "-" + rank.getSymbol();
    }

    /** Returns the Unicode icon that represents this card's suit. */
    private String suitIcon() {
        return switch (suit.getSymbol()) {
            case "D" -> "♦";
            case "H" -> "♥";
            case "S" -> "♠";
            case "C" -> "♣";
            default -> "";
        };
    }
}