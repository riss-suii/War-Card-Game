package com.svi.warcard.model;

import com.svi.warcard.enums.Rank;
import com.svi.warcard.enums.Suit;

public class Card {
    private final Rank rank;
    private final Suit suit;

    public Card(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public int compareTo(Card other) {
        int cmp = rank.compareTo(other.rank);
        if (cmp != 0) return cmp;
        return suit.compareTo(other.suit);
    }

    /** Round display format, e.g. ♥A-H. */
    @Override
    public String toString() {
        return suitIcon() + suit.getSymbol() + "-" + rank.getSymbol();
    }

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