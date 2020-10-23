package deck;

import java.util.*;

public class Deck52 implements Deck{
    private final Integer NUMBER_OF_CARDS = 52;
    public Queue<Card> cards = new LinkedList<>();

    public Deck52() {
        for (Suite suite : Suite.values()) {
            for (Value value : Value.values()) {
                cards.add(new Card(suite, value));
            }
        }
    }

    @Override
    public void shuffle() {
        Random random = new Random();
        List<Card> temp = new ArrayList<>();

        while (cards.size() > 0) {
            Card card = cards.poll();
            temp.add(card);
        }

        while (temp.size() > 0) {
            int remainingCards = temp.size();
            int randomIndex = random.nextInt(remainingCards);
            Card randomCard = temp.get(randomIndex);
            cards.add(randomCard);
            temp.remove(randomCard);
        }

    }

    @Override
    public Card pullfromTop() {
        return cards.poll();
    }

    @Override
    public Card pullfromIndex(int index) {
        return null;
    }
}
