import java.util.LinkedList;
import java.util.ArrayList;
import java.util.List;

public class Player {
    private final int id;
    private final LinkedList<Card> hand = new LinkedList<>();
    private boolean eliminated;

    public Player(int playerId) {
        this.id = playerId;
        this.eliminated = false;
    }

    public void addToHand(Card newCard) {
        hand.add(newCard);
    }

    public Card playTopCard() {
        return hand.remove(0);
    }

    public void collectCards(List<Card> cardList) {
        for(Card card : cardList) {
            addToHand(card);
        }
    }

    public boolean hasCards() {
        return !hand.isEmpty();
    }

    public void eliminate() {
        eliminated = true;
    }

    public boolean isEliminated() {
        return eliminated;
    }

    public int getId() {
        return id;
    }

    public void displayHand(String label) {
        List<String> items = new ArrayList<>();
        for (Card card : hand) {
            items.add(card.toString());
        }
        TextWrapper.print(label, items, ",", " ");
    }

}