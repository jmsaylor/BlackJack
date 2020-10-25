package hand;

import deck.Card;
import game.BlackJackRules;
import game.Rules;
import players.Player;
import players.PlayerTemplate;

import java.util.ArrayList;
import java.util.List;

public class Hand {
    public Player owner;
    Rules gameRules;
    private List<Card> cards = new ArrayList<>();

    public Hand(Player owner, Rules gameRules) {
        this.gameRules = gameRules;
        this.owner = owner;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public Card removeCard(Card card) {
        cards.remove(card);
        return card;
    }

    public int[] getPotentialTotals() {
        return gameRules.getHandValue(this);
    }

    public List<Card> getCards() {
        return cards;
    }

    public Player getOwner() {
        return owner;
    }

}
