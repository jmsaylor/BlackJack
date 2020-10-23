package game;

import deck.Card;
import deck.Deck;
import deck.Deck52;
import hand.Hand;
import players.Human;
import players.Player;
import players.PlayerTemplate;

import java.util.ArrayList;
import java.util.List;

public class BlackJack {
    private final Integer INITIAL_CARDS = 2;
    Deck deck;

    private List<Hand> activeHands = new ArrayList<>();

    private Integer minimumBet;
    private Integer totalBets = 0;

    public BlackJack(int minimumBet) {
        this.minimumBet = minimumBet;
        deck = new Deck52();
        deck.shuffle();
    }

    public void dealStartGame() {
        for (int i = 0; i < INITIAL_CARDS; i++) {
            for (Hand hand : activeHands) {
                Card card = deck.pullfromTop();
                hand.addCard(card);
            }
        }
    }

    public void registerHand(Player player) {
        takeAnte(player);
        activeHands.add(new Hand(player));
    }

    public void takeAnte(Player player) {
        player.ante(minimumBet);
        totalBets += minimumBet;
    }

    public void takeBet(Player player) {
       totalBets += player.bet();
    }

    public List<Hand> getActiveHands() {
        return activeHands;
    }
}
