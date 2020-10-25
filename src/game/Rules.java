package game;

import deck.Card;
import deck.Value;
import hand.Hand;

public interface Rules {
    int[] getHandValue(Hand hand);
    boolean isBlackJack(Hand hand);
    boolean isTwentyOne(Hand hand);
    boolean isBust(Hand hand);

    default boolean hasAce(Hand hand) {
        boolean hasAce = false;
        for (Card card : hand.getCards()) {
            if (card.VALUE == Value.ACE) {
                hasAce = true;
            }
        }
        return hasAce;
    }

    default int aceCount(Hand hand) {
        int aceCount = 0;
        for (Card card: hand.getCards()) {
            if (card.VALUE == Value.ACE) {
                aceCount++;
            }
        }
        return aceCount;
    }
}
