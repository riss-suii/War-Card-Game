import java.util.ArrayList;
import java.util.List;

public class Deck {
    private List<Card> cards;

    public Deck() {
        cards = new ArrayList<>();
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public boolean contains(Card target) {
        for (Card card : cards) {
            if (card.compareTo(target) == 0) {
                return true;
            }
        }
        return false;
    }

    public void shuffle(int numShuffles) {
        for (int i = 1; i <= numShuffles; i++) {
            singleShuffle();
        }
    }

    private void singleShuffle() {
        int half = cards.size() / 2;
        List<Card> shuffled = new ArrayList<>();
        for (int k = 0; k < half; k++) {
            shuffled.add(cards.get(k));
            shuffled.add(cards.get(half + k));
        }
        if (cards.size() % 2 == 1) {
            shuffled.add(cards.get(cards.size() - 1));
        }
        cards = shuffled;
    }

    /** Deal one card at a time to each player in turn until the deck is empty. */
    public void deal(List<Player> players) {
        for (int i = 0; i < cards.size(); i++) {
            players.get(i % players.size()).addToHand(cards.get(i));
        }
    }

    public void display() {
        List<String> items = new ArrayList<>();
        for (Card c : cards) {
            items.add(c.fileFormat());
        }
        TextWrapper.print("", items, ",", "");
    }

    public int size() {
        return cards.size();
    }
    public boolean isEmpty() {
        return cards.isEmpty();
    }
}
