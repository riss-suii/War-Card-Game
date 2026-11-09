package com.svi.warcard.model;

/** A card on the pile together with the player who played it. */
public record PlayedCard(Player owner, Card cardPlayed) {}