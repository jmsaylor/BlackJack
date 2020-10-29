package game;

import hand.Hand;

import java.util.List;

public interface Rules {
    int[] getHandValues(Hand hand);
    int getBestHandValue(Hand hand);
    boolean isBlackJack(Hand hand);
    boolean isTwentyOne(Hand hand);
    boolean isBust(Hand hand);
    boolean isWinner(Hand hand, List<Hand> allHands);
}
