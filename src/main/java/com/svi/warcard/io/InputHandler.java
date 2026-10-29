package com.svi.warcard.io;

import com.svi.warcard.util.Constants;

import java.util.Scanner;

public class InputHandler {
<<<<<<< HEAD
    private static final Scanner scanner = new Scanner(System.in);

    public static void showIntro() {
=======
    private final Scanner scanner = new Scanner(System.in);

    public void showIntro() {
>>>>>>> master
        System.out.println("================ WELCOME TO THE WAR CARD GAME =================");
        System.out.println("Game Mechanics:");
        System.out.println("1. Each round every player plays their top card.");
        System.out.println("2. The highest card wins all cards played that round.");
        System.out.println("3. A player with no cards left is eliminated.");
        System.out.println("===============================================================");
    }

<<<<<<< HEAD
    public static int promptNumPlayers() {
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

    public static int promptNumShuffles() {
=======
    public int promptNumPlayers() {
        int n;
        do {
            System.out.println("Please enter your desired number of players ("
                    + Constants.min_players + "-" + Constants.max_players + "):");
            n = readInt();
            if (n < Constants.min_players || n > Constants.max_players) {
                System.out.println("Invalid number of players. Please enter a number between "
                        + Constants.min_players + " and " + Constants.max_players + ".");
            }
        } while (n < Constants.min_players || n > Constants.max_players);
        return n;
    }

    public int promptNumShuffles() {
>>>>>>> master
        int n;
        do {
            System.out.println("Please enter your desired number of shuffles:");
            n = readInt();
            if (n <= 0) {
<<<<<<< HEAD
                System.out.println(TextWrapper.RED + "⚠ Invalid number of shuffles. Please enter a whole number greater than 0." + TextWrapper.RESET);
                System.out.println(" ");
=======
                System.out.println("Invalid number of shuffles. Please enter a positive integer greater than 0.");
>>>>>>> master
            }
        } while (n <= 0);
        return n;
    }

<<<<<<< HEAD
    public static boolean promptPlayAgain() {
        System.out.println("Do you want to play again? (Y/N)");
        char choice = readChoice();
        while (choice != 'Y' && choice != 'N') {
            System.out.println(TextWrapper.RED + "⚠ Invalid choice. Please enter Y or N." + TextWrapper.RESET);
=======
    public String promptFileName() {
        String name;
        do {
            System.out.println("Please enter the name of the deck file:");
            name = scanner.nextLine().trim();
            if (name.isEmpty()) {
                System.out.println("File name cannot be empty.");
            }
        } while (name.isEmpty());
        return name;
    }

    boolean promptRetryLoad() {
        return askYesNo("Would you like to try another file? (Y/N)");
    }

    public boolean promptPlayAgain() {
        return askYesNo("Do you want to play again? (Y/N)");
    }

    private boolean askYesNo(String question) {
        System.out.println(question);
        char choice = readChoice();
        while (choice != 'Y' && choice != 'N') {
            System.out.println("Invalid choice. Please enter Y or N.");
>>>>>>> master
            choice = readChoice();
        }
        return choice == 'Y';
    }

<<<<<<< HEAD
    private static char readChoice() {
=======
    private char readChoice() {
>>>>>>> master
        String line = scanner.nextLine().trim();
        return line.isEmpty() ? ' ' : Character.toUpperCase(line.charAt(0));
    }

    /** Reads an integer; anything that is not a whole number counts as invalid (returns -1). */
<<<<<<< HEAD
    private static int readInt() {
=======
    private int readInt() {
>>>>>>> master
        String line = scanner.nextLine().trim();
        try {
            return Integer.parseInt(line);
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
