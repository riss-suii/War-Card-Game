package com.svi.warcard;

import com.svi.warcard.io.TextWrapper;
import com.svi.warcard.model.Deck;
import com.svi.warcard.model.Player;
import com.svi.warcard.model.PlayedCard;
import com.svi.warcard.util.Constants;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private final List<Player> players;
    private final Deck gameDeck;
    private final List<PlayedCard> pile = new ArrayList<>();
    private int roundNumber;

    Game(List<Player> players, Deck deck) {
        this.players = players;
        this.gameDeck = deck;
        this.roundNumber = 0;
    }

    public void prepareGame(int numShuffles) {
        gameDeck.shuffle(numShuffles);
        System.out.println();
        System.out.println("Shuffled Deck:");
        gameDeck.display();
        System.out.println();
        gameDeck.deal(players);
    }

    public void playRound() {
        roundNumber++;
        pile.clear();
        for (Player p : players) {
            if (p.isActive()) {
                pile.add(new PlayedCard(p, p.playTopCard()));
            }
        }
        displayRound();

        PlayedCard winner = determineRoundWinner();
        awardPile(winner);

        List<Player> outPlayers = removeEliminatedPlayers();
        displayStatus(winner.owner(), outPlayers);
    }

    /** Highest card wins. Cards are unique, so there are no ties. */
    private PlayedCard determineRoundWinner() {
        PlayedCard best = pile.getFirst();
        for (PlayedCard pc : pile) {
            if (pc.cardPlayed().compareTo(best.cardPlayed()) > 0) {
                best = pc;
            }
        }
        return best;
    }

    /** Winner takes every card played: their own first, then the rest in play order. */
    private void awardPile(PlayedCard winner) {
        Player owner = winner.owner();
        owner.addToHand(winner.cardPlayed());
        for (PlayedCard pc : pile) {
            if (pc != winner) {
                owner.addToHand(pc.cardPlayed());
            }
        }
    }

    private List<Player> removeEliminatedPlayers() {
        List<Player> out = new ArrayList<>();
        for (Player p : players) {
            if (p.isActive() && !p.hasCards()) {
                p.eliminate();
                out.add(p);
            }
        }
        return out;
    }

    private void displayRound() {
        System.out.println(banner("ROUND " + roundNumber));
        for (PlayedCard pc : pile) {
            System.out.println("Player " + pc.owner().getId() + ": "
                    + TextWrapper.colorize(pc.cardPlayed().toString()));
        }
    }

    private void displayStatus(Player winner, List<Player> outPlayers) {
        System.out.println();
        System.out.println(TextWrapper.ORANGE + "\uD83D\uDC51 Player " + winner.getId() + TextWrapper.RESET);
        winner.displayHand("Winner's Deck: ");
        if (!outPlayers.isEmpty()) {
            System.out.println();
            for (Player p : outPlayers) {
                System.out.println(TextWrapper.RED + "\uD83D\uDC80 Player " + p.getId() + " has been eliminated." + TextWrapper.RESET);
            }
        }
        System.out.println(rule());
        System.out.println();
    }

    void displayGameOver() {
        System.out.println(TextWrapper.CYAN + banner("\uD83C\uDFC6 VICTORY \uD83C\uDFC6") + TextWrapper.RESET);
        System.out.println();
        System.out.println(TextWrapper.ORANGE + TextWrapper.center("PLAYER " + getWinner().getId() + " WINS!"));
        System.out.println();
        System.out.println(TextWrapper.center("Thank you for playing!") + TextWrapper.RESET);
        System.out.println();
        System.out.println(TextWrapper.CYAN + rule() + TextWrapper.RESET);
        System.out.println();
    }

    private String banner(String title) {
        int right = Constants.BANNER_WIDTH - Constants.BANNER_LEAD - title.length() - 2;
        return "=".repeat(Constants.BANNER_LEAD) + " " + title + " " + "=".repeat(Math.max(0, right));
    }

    private String rule() {
        return "=".repeat(Constants.BANNER_WIDTH);
    }

    public boolean isGameOver() {
        return countActivePlayers() == 1;
    }

    Player getWinner() {
        for (Player p : players) {
            if (p.isActive()) {
                return p;
            }
        }
        return null;
    }

    private int countActivePlayers() {
        int total = 0;
        for (Player p : players) {
            if (p.isActive()) {
                total++;
            }
        }
        return total;
    }
}