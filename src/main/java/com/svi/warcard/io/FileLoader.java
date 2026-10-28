package com.svi.warcard.io;

import com.svi.warcard.util.Constants;
import com.svi.warcard.enums.Rank;
import com.svi.warcard.enums.Suit;
import com.svi.warcard.model.Card;
import com.svi.warcard.model.Deck;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.StringTokenizer;

public class FileLoader {

    private static final String DECK_FILE = "input.txt";

    public Deck loadDeck() {
        Deck deck = new Deck();
        String errorMessage = readCards(deck);
        if (!errorMessage.isEmpty()) {
            System.out.println();
            System.out.println(errorMessage);
            System.out.println("Please double check the text file and rerun the application.");
            return new Deck();
        }
        return deck;
    }

    private String readCards(Deck targetDeck) {
        try (InputStream input = FileLoader.class.getClassLoader().getResourceAsStream(DECK_FILE)) {
            if (input == null) {
                return "File does not exist.";
            }

            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(input, StandardCharsets.UTF_8))) {
                String message = "";
                String line;
                while (message.isEmpty() && (line = reader.readLine()) != null) {
                    if (line.trim().isEmpty()) {
                        continue;
                    }
                    StringTokenizer lineTokenizer = new StringTokenizer(line, ",");
                    while (lineTokenizer.hasMoreTokens() && message.isEmpty()) {
                        String token = lineTokenizer.nextToken().trim();
                        Card newCard = parseCard(token);
                        if (newCard == null) {
                            message = "Invalid file format.";
                        } else if (targetDeck.contains(newCard)) {
                            message = "Duplicate card detected.";
                        } else if (targetDeck.size() == Constants.TOTAL_CARDS) {
                            message = "Invalid number of cards in the deck.";
                        } else {
                            targetDeck.addCard(newCard);
                        }
                    }
                }
                if (message.isEmpty() && targetDeck.size() != Constants.TOTAL_CARDS) {
                    message = "Invalid number of cards in the deck.";
                }
                return message;
            }
        } catch (IOException e) {
            return "File cannot be opened.";
        }
    }

    private Card parseCard(String token) {
        StringTokenizer tokenizer = new StringTokenizer(token, "-");
        if (tokenizer.countTokens() != 2) {
            return null;
        }
        Suit suit = Suit.fromSymbol(tokenizer.nextToken());
        Rank rank = Rank.fromSymbol(tokenizer.nextToken());
        if (suit == null || rank == null) {
            return null;
        }
        return new Card(rank, suit);
    }
}