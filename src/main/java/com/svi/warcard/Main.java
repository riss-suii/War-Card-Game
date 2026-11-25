package com.svi.warcard;

import com.svi.warcard.game.GameLauncher;


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
    private Main() {}

    static void main() {
        GameLauncher.launch();
    }
}

