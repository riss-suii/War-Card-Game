package com.svi.warcard.model;

import com.svi.warcard.util.TextWrapper;

import java.util.ArrayList;
import java.util.List;

/**
 * Runs one game of War: shuffles and deals the deck, plays rounds, awards
 * cards to round winners, eliminates players with no cards, and reports the
 * overall winner.
 */
public class Game {
    private final List<Player> players;
    private final Deck gameDeck;
    private final List<PlayedCard> pile = new ArrayList<>();
    private int roundNumber;

    /**
     * Creates a game.
     */
    public Game(List<Player> players, Deck deck) {
        this.players = players;
        this.gameDeck = deck;
        this.roundNumber = 0;
    }

    /**
     * Shuffles the deck, shows the result, and deals every card to the players.
     */
    public void prepareGame(int numShuffles) {
        gameDeck.shuffle(numShuffles);
        System.out.println();
        System.out.println("Shuffled Deck:");
        gameDeck.display();
        System.out.println();
        gameDeck.deal(players);
    }

    /**
     * Plays one round: every active player plays their top card, the highest
     * card wins the whole pile, and players left with no cards are eliminated.
     */
    public void playRound() {
        roundNumber++;
        pile.clear();
        for (Player currentPlayer : players) {
            if (currentPlayer.isActive()) {
                pile.add(new PlayedCard(currentPlayer, currentPlayer.playTopCard()));
            }
        }
        displayRound();

        PlayedCard winner = determineRoundWinner();
        awardPile(winner);

        List<Player> outPlayers = removeEliminatedPlayers();
        displayStatus(winner.owner(), outPlayers);
    }

    /**
     * Highest card wins. Cards are unique, so there are no ties.
     */
    private PlayedCard determineRoundWinner() {
        PlayedCard best = pile.getFirst();
        for (PlayedCard currentCard : pile) {
            if (currentCard.cardPlayed().compareTo(best.cardPlayed()) > 0) {
                best = currentCard;
            }
        }
        return best;
    }

    /**
     * Winner takes every card played: their own first, then the rest in play order.
     */
    private void awardPile(PlayedCard winner) {
        Player owner = winner.owner();
        owner.addToHand(winner.cardPlayed());
        for (PlayedCard currentCard : pile) {
            if (currentCard != winner) {
                owner.addToHand(currentCard.cardPlayed());
            }
        }
    }

    /**
     * Eliminates every active player who has no cards left.
     */
    private List<Player> removeEliminatedPlayers() {
        List<Player> out = new ArrayList<>();
        for (Player currentPlayer : players) {
            if (currentPlayer.isActive() && !currentPlayer.hasCards()) {
                currentPlayer.eliminate();
                out.add(currentPlayer);
            }
        }
        return out;
    }

    /**
     * Prints the round banner and the card each player played.
     */
    private void displayRound() {
        System.out.println(TextWrapper.banner("ROUND " + roundNumber));
        for (PlayedCard currentCard : pile) {
            System.out.println("Player " + currentCard.owner().getId() + ": "
                    + TextWrapper.colorize(currentCard.cardPlayed().toString()));
        }
    }

    /**
     * Prints the round winner, their new hand, and any eliminated players.
     */
    private void displayStatus(Player winner, List<Player> outPlayers) {
        System.out.println();
        System.out.println(TextWrapper.ORANGE + "\uD83D\uDC51 Player " + winner.getId() + TextWrapper.RESET);
        winner.displayHand("Winner's Deck: ");
        if (!outPlayers.isEmpty()) {
            System.out.println();
            for (Player currentPlayer : outPlayers) {
                System.out.println(TextWrapper.RED + "\uD83D\uDC80 Player " + currentPlayer.getId() + " has been eliminated." + TextWrapper.RESET);
            }
        }
        System.out.println(TextWrapper.rule());
        System.out.println();
    }

    /**
     * Prints the victory banner announcing the winning player.
     */
    public void displayGameOver() {
        System.out.println(TextWrapper.CYAN + TextWrapper.banner("\uD83C\uDFC6 VICTORY \uD83C\uDFC6") + TextWrapper.RESET);
        System.out.println();
        System.out.println(TextWrapper.ORANGE + TextWrapper.center("PLAYER " + getWinner().getId() + " WINS!"));
        System.out.println();
        System.out.println(TextWrapper.center("Thank you for playing!") + TextWrapper.RESET);
        System.out.println();
        System.out.println(TextWrapper.CYAN + TextWrapper.rule() + TextWrapper.RESET);
        System.out.println();
    }

    /**
     * Checks whether the game has ended.
     */
    public boolean isGameOver() {
        return countActivePlayers() == 1;
    }

    /**
     * Finds the winner of the game.
     */
    Player getWinner() {
        for (Player currentPlayer : players) {
            if (currentPlayer.isActive()) {
                return currentPlayer;
            }
        }
        return null;
    }

    /**
     * Counts the player who are still in the game.
     */
    private int countActivePlayers() {
        int total = 0;
        for (Player currentPlayer : players) {
            if (currentPlayer.isActive()) {
                total++;
            }
        }
        return total;
    }
}