package hand;

import deck.Card;
import game.Rules;
import players.Player;

import java.util.ArrayList;
import java.util.List;

public class Hand {
    public Player owner;
    private Rules gameRules;
    private List<Card> cards = new ArrayList<>();

    public Hand(Player owner, Rules gameRules) {
        this.gameRules = gameRules;
        this.owner = owner;
    }

    public Hand(Player owner, Rules gameRules, Card[] cards) {
        this.gameRules = gameRules;
        this.owner = owner;
        for (Card card : cards) addCard(card);
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public Card removeCard(Card card) {
        cards.remove(card);
        return card;
    }

    public boolean isWinner(List<Hand> allHands){ return gameRules.isWinner(this, allHands); }

    public int[] getPotentialTotals() {
        return gameRules.getHandValues(this);
    }

    public int getBestValue() { return gameRules.getBestHandValue(this); }

    public boolean isTwentyOne() { return gameRules.isTwentyOne(this); }

    public boolean isBust() { return gameRules.isBust(this); }

    public List<Card> getCards() {
        return cards;
    }

    public Player getOwner() {
        return owner;
    }


}
