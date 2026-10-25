import java.util.ArrayList;
import java.util.List;

public class Game {
    private final List<Player> players;
    private final Deck gameDeck;
    private int roundNumber;

    Game(List<Player> players, Deck deck) {
        this.players = players;
        this.gameDeck = deck;
        this.roundNumber = 0;
    }

    void prepareGame(int numShuffles) {
        gameDeck.shuffle(numShuffles);
        System.out.println();
        System.out.println("Shuffled Deck:");
        gameDeck.display();
        System.out.println();
        System.out.println();
        gameDeck.deal(players);
    }

    void playRound() {
        roundNumber++;
        List<PlayedCard> pile = new ArrayList<>();
        for (Player p : players) {
            if (!p.isEliminated()) {
                pile.add(new PlayedCard(p, p.playTopCard()));
            }
        }
        displayRound(pile);
        int winIndex = determineWinner(pile);
        awardCards(winIndex, pile);
        List<Player> outPlayers = removeEliminatedPlayers();
        displayStatus(pile.get(winIndex).owner, outPlayers);
    }

    private int determineWinner(List<PlayedCard> pile) {
        int best = 0;
        for (int i = 1; i < pile.size(); i++) {
            if (pile.get(i).cardPlayed.compareTo(pile.get(best).cardPlayed) > 0) {
                best = i;
            }
        }
        return best;
    }

    /** Winner's cards go to the bottom of the winner's deck, winning card first. */
    private void awardCards(int winIndex, List<PlayedCard> pile) {
        List<Card> collected = new ArrayList<>();
        collected.add(pile.get(winIndex).cardPlayed);
        for (int i = 0; i < pile.size(); i++) {
            if (i != winIndex) {
                collected.add(pile.get(i).cardPlayed);
            }
        }
        pile.get(winIndex).owner.collectCards(collected);
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

    private void displayRound(List<PlayedCard> pile) {
        System.out.println(banner("ROUND " + roundNumber));
        for (PlayedCard pc : pile) {
            System.out.println("Player " + pc.owner.getId() + ": " + pc.cardPlayed);
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
        System.out.println(banner("GAME OVER "));
        System.out.println("Player " + getWinner().getId() + " Wins!");
        System.out.println();
        System.out.println("Thank you for playing.");
        System.out.println(rule());
        System.out.println();
    }

    private String banner(String title) {
        int right = Constants.BANNER_WIDTH - Constants.BANNER_LEAD - title.length() - 2;
        return "=".repeat(Constants.BANNER_LEAD) + " " + title + " " + "=".repeat(Math.max(0, right));
    }

    private String rule() {
        return "=".repeat(Constants.BANNER_WIDTH);
    }

    boolean isGameOver() {
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
