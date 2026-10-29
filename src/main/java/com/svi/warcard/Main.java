package com.svi.warcard;

import com.svi.warcard.model.Player;
import com.svi.warcard.io.FileLoader;
import com.svi.warcard.io.InputHandler;
import com.svi.warcard.model.Deck;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class Main {
    static void main() {
<<<<<<< HEAD
        FileLoader loader = new FileLoader();

        InputHandler.showIntro();
=======
        InputHandler userInput = new InputHandler();
        FileLoader loader = new FileLoader();

        userInput.showIntro();
>>>>>>> master
        boolean finished = false;

        try {
            do {
<<<<<<< HEAD
                Deck gameDeck = loader.loadDeck();
=======
                Deck gameDeck = loader.loadDeck(userInput);
>>>>>>> master
                if (gameDeck.isEmpty()) {
                    System.out.println("Exiting program.");
                    finished = true;
                } else {
<<<<<<< HEAD
                    int numPlayers = InputHandler.promptNumPlayers();
                    int numShuffles = InputHandler.promptNumShuffles();
=======
                    System.out.println();
                    int numPlayers = userInput.promptNumPlayers();
                    int numShuffles = userInput.promptNumShuffles();
>>>>>>> master
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

<<<<<<< HEAD
                    if (!InputHandler.promptPlayAgain()) {
=======
                    if (!userInput.promptPlayAgain()) {
>>>>>>> master
                        finished = true;
                    }
                }
            } while (!finished);
        } catch (NoSuchElementException e) {
<<<<<<< HEAD
=======
            // Input stream ended (e.g. Ctrl+D) - leave quietly.
>>>>>>> master
            System.out.println();
            System.out.println("Input ended. Exiting program.");
        }
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> master
