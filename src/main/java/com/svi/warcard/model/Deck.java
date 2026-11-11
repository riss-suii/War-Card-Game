package com.svi.warcard.model;

import com.svi.warcard.util.TextWrapper;

import java.util.ArrayList;
import java.util.List;

/**
 * An ordered collection of cards that can be shuffled, dealt, and displayed.
 *
 * @author Chariss Borlaza
 * @since 1.0
 */
public class Deck {
    private List<Card> cards;

    /**
     * Creates an empty deck.
     */
    public Deck() {
        cards = new ArrayList<>();
    }

    /**
     * Adds a card to the bottom of the deck.
     */
    public void addCard(Card card) {
        cards.add(card);
    }

    /**
     * Checks whether a card is already in the deck (used to detect duplicates).
     */
    public boolean contains(Card target) {
        for (Card currentCard : cards) {
            if (currentCard.compareTo(target) == 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * Shuffles the deck several times.
     */
    public void shuffle(int numShuffles) {
        for (int i = 1; i <= numShuffles; i++) {
            singleShuffle();
        }
    }

    /**
     * Performs one perfect out shuffle: splits the deck in half and interleave the halves, starting with the top half.
     */
    private void singleShuffle() {
        int half = cards.size() / 2;
        List<Card> shuffled = new ArrayList<>();
        for (int k = 0; k < half; k++) {
            shuffled.add(cards.get(k));
            shuffled.add(cards.get(half + k));
        }
        cards = shuffled;
    }

    /**
     * Deal one card at a time to each player in turn until the deck is empty.
     */
    public void deal(List<Player> players) {
        for (int i = 0; i < cards.size(); i++) {
            players.get(i % players.size()).addToHand(cards.removeFirst());
        }
    }

    /**
     * Prints every card in the deck in aligned columns.
     */
    public void display() {
        List<String> items = new ArrayList<>();
        for (Card currentCard : cards) {
            items.add(currentCard.toString());
        }
        TextWrapper.print("", items, ",", "");
    }

    /**
     * Gets the number of the cards in the deck.
     */
    public int size() {
        return cards.size();
    }

    /**
     * Checks whether the deck has no cards.
     */
    public boolean isEmpty() {
        return cards.isEmpty();
    }
}
