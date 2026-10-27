package com.svi.warcard.io;

import com.svi.warcard.util.Constants;
import com.svi.warcard.enums.Rank;
import com.svi.warcard.enums.Suit;
import com.svi.warcard.model.Card;
import com.svi.warcard.model.Deck;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class FileLoader {

    /** @return a full deck, or an empty deck if the user gave up loading. */
    public Deck loadDeck(InputHandler userInput) {
        Deck newDeck;
        String errorMessage;
        do {
            String fileName = userInput.promptFileName();
            newDeck = new Deck();
            if (!fileExists(fileName)) {
                errorMessage = "File " + fileName + " cannot be found or opened.";
            } else {
                errorMessage = readCards(newDeck, fileName);
            }
            if (!errorMessage.isEmpty()) {
                System.out.println(errorMessage);
                if (!userInput.promptRetryLoad()) {
                    return new Deck();
                }
            }
        } while (!errorMessage.isEmpty());
        return newDeck;
    }

    private boolean fileExists(String fileName) {
        File f = new File(fileName);
        return f.isFile() && f.canRead();
    }

    /** The file holds comma-separated cards in the form Suit-Rank, e.g. D-A,H-10,S-2. */
    private String readCards(Deck targetDeck, String fileName) {
        String message = "";
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
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
                        message = "Duplicate cards detected.";
                    } else if (targetDeck.size() == Constants.total_cards) {
                        message = "Invalid number of cards in the deck.";
                    } else {
                        targetDeck.addCard(newCard);
                    }
                }
            }
        } catch (IOException e) {
            return "File " + fileName + " cannot be found or opened.";
        }
        if (message.isEmpty() && targetDeck.size() != Constants.total_cards) {
            message = "Invalid number of cards in the deck.";
        }
        return message;
    }

    /** @return the parsed card, or null when the token is not a valid Suit-Rank card. */
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
