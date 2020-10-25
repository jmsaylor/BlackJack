package ui;

import hand.Hand;

import java.util.List;

public interface UI {
    void publicShowHand(Hand hand);
    void showHandAllCards(Hand hand);
    void showAllHandsInGame(List<Hand> hands);
    void showAllHandsFinalReveal(List<Hand> hands);
    boolean promptForHit();
    int inputBet();
    boolean getAnswer(String string);
    void showHandValues(int[] values);
}
