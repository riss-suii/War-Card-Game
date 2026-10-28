package com.svi.warcard.io;

import com.svi.warcard.util.Constants;

import java.util.Scanner;

public class InputHandler {
    private final Scanner scanner = new Scanner(System.in);

    public void showIntro() {
        System.out.println("================ WELCOME TO THE WAR CARD GAME =================");
        System.out.println("Game Mechanics:");
        System.out.println("1. Each round every player plays their top card.");
        System.out.println("2. The highest card wins all cards played that round.");
        System.out.println("3. A player with no cards left is eliminated.");
        System.out.println("===============================================================");
    }

    public int promptNumPlayers() {
        int n;
        do {
            System.out.println("Please enter your desired number of players ("
                    + Constants.MIN_PLAYERS + "-" + Constants.MAX_PLAYERS + "):");
            n = readInt();
            if (n < Constants.MIN_PLAYERS || n > Constants.MAX_PLAYERS) {
                System.out.println(TextWrapper.RED + "⚠ Invalid number of players. Please enter a number between "
                        + Constants.MIN_PLAYERS + " and " + Constants.MAX_PLAYERS + "." + TextWrapper.RESET);
                System.out.println(" ");
            }
        } while (n < Constants.MIN_PLAYERS || n > Constants.MAX_PLAYERS);
        return n;
    }

    public int promptNumShuffles() {
        int n;
        do {
            System.out.println("Please enter your desired number of shuffles:");
            n = readInt();
            if (n <= 0) {
                System.out.println(TextWrapper.RED + "⚠ Invalid number of shuffles. Please enter a whole number greater than 0." + TextWrapper.RESET);
                System.out.println(" ");
            }
        } while (n <= 0);
        return n;
    }

    public boolean promptPlayAgain() {
        return askYesNo("Do you want to play again? (Y/N)");
    }

    private boolean askYesNo(String question) {
        System.out.println(question);
        char choice = readChoice();
        while (choice != 'Y' && choice != 'N') {
            System.out.println(TextWrapper.RED + "⚠ Invalid choice. Please enter Y or N." + TextWrapper.RESET);
            choice = readChoice();
        }
        return choice == 'Y';
    }

    private char readChoice() {
        String line = scanner.nextLine().trim();
        return line.isEmpty() ? ' ' : Character.toUpperCase(line.charAt(0));
    }

    /** Reads an integer; anything that is not a whole number counts as invalid (returns -1). */
    private int readInt() {
        String line = scanner.nextLine().trim();
        try {
            return Integer.parseInt(line);
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
