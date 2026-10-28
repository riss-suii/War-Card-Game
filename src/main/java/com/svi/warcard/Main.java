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
        InputHandler userInput = new InputHandler();
        FileLoader loader = new FileLoader();

        userInput.showIntro();
        boolean finished = false;

        try {
            do {
                Deck gameDeck = loader.loadDeck();
                if (gameDeck.isEmpty()) {
                    System.out.println("Exiting program.");
                    finished = true;
                } else {
                    int numPlayers = userInput.promptNumPlayers();
                    int numShuffles = userInput.promptNumShuffles();
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

                    if (!userInput.promptPlayAgain()) {
                        finished = true;
                    }
                }
            } while (!finished);
        } catch (NoSuchElementException e) {
            System.out.println();
            System.out.println("Input ended. Exiting program.");
        }
    }
}