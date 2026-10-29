package com.svi.warcard.model;

import com.svi.warcard.io.TextWrapper;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Player {
    private final int id;
    private final LinkedList<Card> hand = new LinkedList<>();
    private boolean active;

    public Player(int playerId) {
        this.id = playerId;
        this.active = true;
    }

    public void addToHand(Card newCard) {
        hand.add(newCard);
    }

    public Card playTopCard() {
        return hand.removeFirst();
    }

    public boolean hasCards() {
        return !hand.isEmpty();
    }

    public void eliminate() {
        active = false;
    }

    public boolean isActive() {
        return active;
    }

    public int getId() {
        return id;
    }

    public void displayHand(String label) {
        List<String> items = new ArrayList<>();
        for (Card card : hand) {
            items.add(card.toString());
        }
        TextWrapper.print(label, items, ",", " ");
    }

}
