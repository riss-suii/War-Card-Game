public class PlayedCard {
    final Player owner;
    final Card cardPlayed;

    PlayedCard(Player owner, Card cardPlayed) {
        this.owner = owner;
        this.cardPlayed = cardPlayed;
    }
}
