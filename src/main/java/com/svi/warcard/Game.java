package com.svi.warcard;

import com.svi.warcard.io.TextWrapper;
import com.svi.warcard.model.Deck;
import com.svi.warcard.model.Player;
import com.svi.warcard.model.Card;
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
        System.out.println();
        gameDeck.deal(players);
    }

    public void playRound() {
        roundNumber++;
        pile.clear();
        for (Player p : players) {
            if (!p.isEliminated()) {
                pile.add(new PlayedCard(p, p.playTopCard()));
            }
        }
        displayRound();
        PlayedCard winner = determineWinner();
        winner.owner().collectCards(cardsWinnerFirst(winner));
        List<Player> outPlayers = removeEliminatedPlayers();
        displayStatus(winner.owner(), outPlayers);
    }

    /** Highest card wins; on a tie the earlier play wins. */
    private PlayedCard determineWinner() {
        PlayedCard best = pile.get(0);
        for (PlayedCard pc : pile) {
            if (pc.cardPlayed().compareTo(best.cardPlayed()) > 0) {
                best = pc;
            }
        }
        return best;
    }

    /** Winning card first, then the rest in play order. */
    private List<Card> cardsWinnerFirst(PlayedCard winner) {
        List<Card> cards = new ArrayList<>();
        cards.add(winner.cardPlayed());
        for (PlayedCard pc : pile) {
            if (pc != winner) {
                cards.add(pc.cardPlayed());
            }
        }
        return cards;
    }

    private List<Player> removeEliminatedPlayers() {
        List<Player> out = new ArrayList<>();
        for (Player p : players) {
            if (!p.isEliminated() && !p.hasCards()) {
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
        System.out.println("Round Winner: Player " + winner.getId());
        winner.displayHand("Winner's Deck: ");
        if (!outPlayers.isEmpty()) {
            System.out.println();
            for (Player p : outPlayers) {
                System.out.println("Player " + p.getId() + " has been eliminated.");
            }
        }
        System.out.println(rule());
        System.out.println();
    }

    void displayGameOver() {
        System.out.println();
        System.out.println(TextWrapper.CYAN + "================ VICTORY =================");
        System.out.println(TextWrapper.center("👑"));
        System.out.println(TextWrapper.center("PLAYER " + getWinner().getId() + " WINS!"));
        System.out.println();
        System.out.println(TextWrapper.center("Thank you for playing!"));
        System.out.println("==========================================" + TextWrapper.RESET);
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
            if (!p.isEliminated()) {
                return p;
            }
        }
        return null;
    }

    private int countActivePlayers() {
        int total = 0;
        for (Player p : players) {
            if (!p.isEliminated()) {
                total++;
            }
        }
        return total;
    }
}