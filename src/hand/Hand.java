package hand;

import deck.Card;
import players.Player;
import players.PlayerTemplate;

import java.util.ArrayList;
import java.util.List;

public class Hand {
    public Player owner;
    private List<Card> cards = new ArrayList<>();

    public Hand(Player owner) {
        this.owner = owner;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public void removeCard(Card card) {
        cards.remove(card);
    }

    public List<Card> getCards() {
        return cards;
    }
}
