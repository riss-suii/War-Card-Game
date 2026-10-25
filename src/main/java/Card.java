import java.io.File;

public class Card {
    private final Rank rank;
    private final Suit suit;

    public Card(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public Rank getRank() {
        return rank;
    }
    public Suit getSuit() {
        return suit;
    }

    /** Rank decides first; suit breaks ties. Returns 1, -1 or 0. */
    public int compareTo(Card other) {
        if (rank.compareTo(other.rank) > 0) return 1;
        if (rank.compareTo(other.rank) < 0) return -1;
        if (suit.compareTo(other.suit) > 0) return 1;
        if (suit.compareTo(other.suit) < 0) return -1;
        return 0;
    }

    /** Round display format, e.g. A-D. */
    @Override
    public String toString() {
        return rank.getSymbol() + "-" + suit.getSymbol();
    }

    /** File / deck display format, e.g. D-A. */
    public String fileFormat() {
        return suit.getSymbol() + "-" + rank.getSymbol();
    }
}
