package com.svi.warcard.model;

import com.svi.warcard.util.TextWrapper;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/** A player with an id, a hand of cards, and an active flag. */

public class Player {
    private final int id;
    private final LinkedList<Card> hand = new LinkedList<>();
    private boolean active;

    /** Creates an active player with an empty hand. */
    public Player(int playerId) {
        this.id = playerId;
        this.active = true;
    }

    /** Adds a card to the bottom of the hand. */
    public void addToHand(Card newCard) {
        hand.add(newCard);
    }

    /** Marks the player as out of the game. */
    public Card playTopCard() {
        return hand.removeFirst();
    }

    /** Checks whether the player still has cards. */
    public boolean hasCards() {
        return !hand.isEmpty();
    }

    /** Checks whether the player is still in the game. */
    public void eliminate() {
        active = false;
    }

    /** Checks whether the player is still in the game. */
    public boolean isActive() {
        return active;
    }

    /** Get's the player's number. */
    public int getId() {
        return id;
    }

    /** Prints the player's hand in aligned columns after a label. */
    public void displayHand(String label) {
        List<String> items = new ArrayList<>();
        for (Card currentCard : hand) {
            items.add(currentCard.toString());
        }
        TextWrapper.print(label, items, ",", " ");
    }

}
