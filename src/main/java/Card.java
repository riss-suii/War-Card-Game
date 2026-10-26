public class Card {
    private final Rank rank;
    private final Suit suit;

    public Card(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public int compareTo(Card other) {
        int cmp = rank.compareTo(other.rank);
        if (cmp != 0) return cmp;
        return suit.compareTo(other.suit);
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
