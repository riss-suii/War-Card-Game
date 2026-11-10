package com.svi.warcard;

import com.svi.warcard.model.Game;
import com.svi.warcard.model.Player;
import com.svi.warcard.io.FileLoader;
import com.svi.warcard.io.InputHandler;
import com.svi.warcard.model.Deck;

import java.util.ArrayList;
import java.util.List;

/**
 * Entry point of the War card game.
 *
 * <p>Runs a replay loop: show the rules, load the deck from {@code input.txt},
 * ask for the number of players and shuffles, play one full game, then ask
 * whether the user wants to play again.</p>
 *
 * @author Chariss Borlaza
 * @since 1.0
 */
public class Main {

    /**
     * Starts the program and keeps running games until the user quits or the deck file is invalid.
     */
    static void main() {
        FileLoader loader = new FileLoader();

        boolean finished = false;

        try {
            do {
                InputHandler.showIntro();
                Deck gameDeck = loader.loadDeck();
                if (gameDeck.isEmpty()) {
                    System.out.println("Exiting program.");
                    finished = true;
                } else {
                    int numPlayers = InputHandler.promptNumPlayers();
                    int numShuffles = InputHandler.promptNumShuffles();

                    System.out.println();
                    System.out.println("Initial Deck:");
                    gameDeck.display();

                    List<Player> players = new ArrayList<>();
                    for (int i = 1; i <= numPlayers; i++) {
                        players.add(new Player(i));
                    }

                    Game game = new Game(players, gameDeck);
                    game.prepareGame(numShuffles);
                    while (!game.isGameOver()) {
                        game.playRound();
                    }
                    game.displayGameOver();

                    if (!InputHandler.promptPlayAgain()) {

                        finished = true;
                    }
                }
            } while (!finished);
        } catch (Exception e) {
            System.out.println();
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
    }
}
