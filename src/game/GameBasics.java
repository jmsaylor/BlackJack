package game;

import deck.Card;
import deck.Value;
import hand.Hand;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;

public abstract class GameBasics {
    EnumMap<Value, Integer> valueMap = new EnumMap<Value, Integer>(Value.class);

    public GameBasics() {
        valueMap.put(Value.TWO,2);
        valueMap.put(Value.THREE, 3);
        valueMap.put(Value.FOUR, 4);
        valueMap.put(Value.FIVE, 5);
        valueMap.put(Value.SIX, 6);
        valueMap.put(Value.SEVEN, 7);
        valueMap.put(Value.EIGHT, 8);
        valueMap.put(Value.NINE, 9);
        valueMap.put(Value.TEN, 10);
        valueMap.put(Value.JACK, 10);
        valueMap.put(Value.QUEEN, 10);
        valueMap.put(Value.KING, 10);
        countAceAsEleven();
    }

    public int[] getHandValues(Hand hand) {
        //not yet dealing with more than one ace properly
        final int POSSIBLE_VALUES = aceCount(hand) + 1;
        int[] result = new int[POSSIBLE_VALUES];
        for (int i = 0; i < POSSIBLE_VALUES; i++) {
            if (i == 1) {
                countAceAsOne();
            }
            for (Card card : hand.getCards()) {
                result[i] += getCardValue(card);
            }
            countAceAsEleven();
        }
        return result;
    }

    public void countAceAsOne() {
        valueMap.put(Value.ACE, 1);
    }

    public void countAceAsEleven() {
        valueMap.put(Value.ACE, 11);
    }

    public int aceCount(Hand hand) {
        int aceCount = 0;
        for (Card card: hand.getCards()) {
            if (card.VALUE == Value.ACE) {
                aceCount++;
            }
        }
        return aceCount;
    }

    public int getCardValue(Card card) {
        return valueMap.get(card.VALUE);
    }

    public boolean isTwentyOne(Hand hand) {
        return isBlackJack(hand);
    }

    public boolean isBlackJack(Hand hand) {
        for (Integer handTotal : hand.getPotentialTotals()) {
            if (handTotal == 21) {
                return true;
            }
        }
        return false;
    }

    public int getBestHandValue(Hand hand){
        int bestHandValue;
        List<Integer> temp = new ArrayList<>();
        for (Integer value : getHandValues(hand)) {
            if (value <= 21) temp.add(value);
        }
        if (temp.size() == 0) {
            return getHandValues(hand)[0];
        }
        bestHandValue = temp.get(0);
        for (Integer value : temp) {
            if (value > bestHandValue) {
                bestHandValue = value;
            }
        }
        return bestHandValue;
    }

    public boolean isBust(Hand hand) {
        for (Integer handTotal : hand.getPotentialTotals()) {
            if (handTotal <= 21) {
                return false;
            }
        }
        return true;
    }
}
