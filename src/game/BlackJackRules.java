package game;

import deck.Card;
import deck.Value;
import hand.Hand;

import java.util.EnumMap;

public class BlackJackRules implements Rules{
    EnumMap<Value, Integer> valueMap = new EnumMap<Value, Integer>(Value.class);

    public BlackJackRules() {
        setValueMap();
    }

    public int[] getHandValue(Hand hand) {
        final int POSSIBLE_VALUES = aceCount(hand) + 1;
        int[] result = new int[POSSIBLE_VALUES];
        for (int i = 0; i < POSSIBLE_VALUES; i++) {
            for (Card card : hand.getCards()) {
                result[i] += getCardValue(card);
            }
        }
        return result;
    }

    @Override
    public boolean isBlackJack(Hand hand) {
        for (Integer handTotal : hand.getPotentialTotals()) {
            if (handTotal == 21) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isTwentyOne(Hand hand) {
        return isBlackJack(hand);
    }

    @Override
    public boolean isBust(Hand hand) {
        for (Integer handTotal : hand.getPotentialTotals()) {
            if (handTotal <= 21) {
                return false;
            }
        }
        return true;
    }

    public int getCardValue(Card card) {
        return valueMap.get(card.VALUE);
    }

    private void setValueMap() {
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

    public void countAceAsOne() {
        valueMap.put(Value.ACE, 1);
    }

    public void countAceAsEleven() {
        valueMap.put(Value.ACE, 11);
    }
}
