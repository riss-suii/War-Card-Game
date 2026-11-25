package com.svi.warcard.model;

import com.svi.warcard.enums.Rank;
import com.svi.warcard.enums.Suit;

import java.util.Objects;

/**
 * An immutable playing card with a rank and a suit.
 *
 * @author Chariss Borlaza
 * @since 1.0
 */
public final class Card implements Comparable<Card> {
    private final Rank rank;
    private final Suit suit;

    /**
     * Creates a card.
     *
     * @param rank the card's rank
     * @param suit the card's suit
     */
    public Card(Rank rank, Suit suit) {
        this.rank = Objects.requireNonNull(rank, "rank");
        this.suit = Objects.requireNonNull(suit, "suit");
    }

    /**
     * Compares this card to another: rank first, then suit as the tiebreaker.
     * Consistent with {@link #equals(Object)}.
     */
    @Override
    public int compareTo(Card other) {
        int result = rank.compareTo(other.rank);
        if (result != 0) return result;
        return suit.compareTo(other.suit);
    }

    /**
     * Two cards are equal when they have the same rank and suit.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Card other)) return false;
        return rank == other.rank && suit == other.suit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rank, suit);
    }

    /**
     * Display format, e.g. ♥H-A.
     */
    @Override
    public String toString() {
        return suit.getIcon() + suit.getSymbol() + "-" + rank.getSymbol();
    }
}