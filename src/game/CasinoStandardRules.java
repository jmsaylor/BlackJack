package game;

import hand.Hand;
import players.Dealer;
import players.Player;

import java.util.List;

public class CasinoStandardRules extends GameBasics implements Rules{


    @Override
    public boolean isWinner(Hand playerHand, List<Hand> allHands) {
        Hand dealerHand = null;
        for (Hand hand : allHands) {
            if (hand.getOwner() instanceof Dealer) {
                dealerHand = hand;
            }
        }
        boolean isLessThan21 = getBestHandValue(playerHand) <= 21;
        boolean beatsDealer = getBestHandValue(playerHand) > getBestHandValue(dealerHand);

        boolean isDealerBust = dealerHand.isBust();

        return isLessThan21 && beatsDealer || isLessThan21 && isDealerBust;
    }
}
