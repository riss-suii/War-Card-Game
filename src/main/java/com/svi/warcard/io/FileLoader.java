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
        try (InputStream input = FileLoader.class.getClassLoader().getResourceAsStream(DECK_FILE)) {
            if (input == null) {
                throw new IllegalArgumentException("File does not exist.");
            }
            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(input, StandardCharsets.UTF_8))) {
                return readCards(reader);
            }
        } catch (IOException e) {
            throw new IllegalArgumentException("File cannot be opened.", e);
        }
    }

    private Deck readCards(BufferedReader reader) throws IOException {
        Deck deck = new Deck();
        String line;
        while ((line = reader.readLine()) != null) {
            if (line.trim().isEmpty()) {
                continue;
            }
            StringTokenizer tokenizer = new StringTokenizer(line, ",");
            while (tokenizer.hasMoreTokens()) {
                Card card = parseCard(tokenizer.nextToken().trim());
                if (deck.contains(card)) {
                    throw new IllegalArgumentException("Duplicate card detected.");
                }
                if (deck.size() == Constants.TOTAL_CARDS) {
                    throw new IllegalArgumentException("Invalid number of cards in the deck.");
                }
                deck.addCard(card);
            }
        }
        if (deck.size() != Constants.TOTAL_CARDS) {
            throw new IllegalArgumentException("Invalid number of cards in the deck.");
        }
        return deck;
    }

    private Card parseCard(String token) {
        StringTokenizer tokenizer = new StringTokenizer(token, "-");
        if (tokenizer.countTokens() != 2) {
            throw new IllegalArgumentException("Invalid file format.");
        }
        Suit suit = Suit.fromSymbol(tokenizer.nextToken());
        Rank rank = Rank.fromSymbol(tokenizer.nextToken());
        if (suit == null || rank == null) {
            throw new IllegalArgumentException("Invalid file format.");
        }
        return new Card(rank, suit);
    }
}
