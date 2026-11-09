package com.svi.warcard.io;

import com.svi.warcard.util.Constants;
import com.svi.warcard.util.TextWrapper;

import java.util.Scanner;

/** Handles all console input and the intro screen. */

public class InputHandler {
    private static final Scanner scanner = new Scanner(System.in);

    /** Prints the welcome banner and the rules of the game. */
    public static void showIntro() {
        System.out.println(TextWrapper.ORANGE + "======================= WELCOME TO THE WAR CARD GAME =======================" + TextWrapper.RESET);
        System.out.println(TextWrapper.BOLD + "                           ⚔  THE RULES OF WAR  ⚔");
        System.out.println();
        System.out.println("             ♠ THE DRAW    Each player plays their top card.");
        System.out.println("             ♥ THE CLASH   The highest card wins the round.");
        System.out.println("             ♦ THE SPOILS  The winner takes every card played.");
        System.out.println("             ♣ THE FALL    No cards left means you're out.");
        System.out.println();
        System.out.println("                    Last warrior standing wins the war!" + TextWrapper.RESET);
        System.out.println(TextWrapper.ORANGE + "============================================================================" + TextWrapper.RESET);
    }

    /** Ask how many players will join, repeating until the answer is between 2-8. */
    public static int promptNumPlayers() {
        int n;
        do {
            System.out.println(TextWrapper.CYAN + "Please enter your desired number of players ("
                    + Constants.MIN_PLAYERS + "-" + Constants.MAX_PLAYERS + "):" + TextWrapper.RESET);
            n = readInt();
            if (n < Constants.MIN_PLAYERS || n > Constants.MAX_PLAYERS) {
                System.out.println(TextWrapper.RED + "⚠ Invalid input. Please enter a number between "
                        + Constants.MIN_PLAYERS + " and " + Constants.MAX_PLAYERS + "." + TextWrapper.RESET);
                System.out.println(" ");
            }
        } while (n < Constants.MIN_PLAYERS || n > Constants.MAX_PLAYERS);
        return n;
    }

    /** Ask how many times the deck should be shuffled, repeating until the answer is 1-8. */
    public static int promptNumShuffles() {
        int n;
        do {
            System.out.println(TextWrapper.CYAN + "Please enter your desired number of shuffles (1-"
                    + Constants.MAX_SHUFFLES + "):" + TextWrapper.RESET);
            n = readInt();
            if (n < 1 || n > Constants.MAX_SHUFFLES) {
                System.out.println(TextWrapper.RED + "⚠ Invalid input. Please enter a whole number between 1 and "
                        + Constants.MAX_SHUFFLES + "." + TextWrapper.RESET);
                System.out.println(" ");
            }
        } while (n < 1 || n > Constants.MAX_SHUFFLES);
        return n;
    }

    /** Ask whether the user wants another game, repeating until the answer is Y or N. */
    public static boolean promptPlayAgain() {
        System.out.println(TextWrapper.ORANGE + "Do you want to play again? (Y/N)" + TextWrapper.RESET);
        while (true) {
            String answer = scanner.nextLine().trim().toUpperCase();
            if (answer.equals("Y")) {
                return true;
            }
            if (answer.equals("N")) {
                return false;
            }
            System.out.println(TextWrapper.RED + "⚠ Invalid choice. Please enter Y/N."
                    + TextWrapper.RESET);
        }
    }
    /** Reads an integer; anything that is not a whole number counts as invalid (returns -1). */
    private static int readInt() {
        String line = scanner.nextLine().trim();
        try {
            return Integer.parseInt(line);
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
