package com.svi.warcard.model;

/**
 * A card on the pile together with the player who played it.
 *
 * @author Chariss Borlaza
 * @since 1.0
 */
public record PlayedCard(Player owner, Card cardPlayed) {
}