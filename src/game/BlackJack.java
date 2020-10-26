package game;

import deck.Card;
import deck.Deck;
import deck.Deck52;
import hand.Hand;
import players.Human;
import players.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BlackJack {
    private final Integer INITIAL_CARDS = 2;
    Rules gameRules = new CasinoStandardRules();
    Deck deck;

    private List<Hand> activeHands = new ArrayList<>();

    private Integer minimumBet;
    private Map<Hand, Integer> bets = new HashMap<>();

    public BlackJack(int minimumBet) {
        this.minimumBet = minimumBet;
        deck = new Deck52();
        deck.shuffle();
    }

    public void play() {
        dealStartGame();
        showGameIfHuman();
        for (int handIndex = 0; handIndex < getActiveHands().size(); handIndex++) {
            Hand hand = getActiveHands().get(handIndex);
            if (gameRules.isBlackJack(hand)) {
                handleBlackJack(hand);
                continue;
            }
            handleSplit(hand);
            showHandIfHuman(hand);
            while (wantsHit(hand)) {
                if (gameRules.isTwentyOne(hand)) {
                    handleTwentyOne(hand);
                    break;
                }
                hit(hand);
                showHandIfHuman(hand);
                if (gameRules.isBust(hand)) {
                    handleBust(hand);
                    break;
                }
            }
        }
        finalReveal();
        System.out.println(determineWinners());
    }

    private List<Hand> determineWinners() {
        List<Hand> winners = new ArrayList<>();
        for (Hand hand : getActiveHands()) {
            if (hand.isWinner(getActiveHands())) {
                winners.add(hand);
            }
        }
        return winners;
    }

    public int[] getHandValue(Hand hand){
        return gameRules.getHandValues(hand);
    }

    private boolean wantsHit(Hand hand) {
        return hand.getOwner().hit(hand);
    }

    private void hit(Hand hand) {
        Card card = deck.pull();
        hand.addCard(card);
    }

    public void dealStartGame() {
        for (int i = 0; i < INITIAL_CARDS; i++) {
            for (Hand hand : getActiveHands()) {
                Card card = deck.pull();
                hand.addCard(card);
            }
        }
    }

    public void showGameIfHuman() {
        for (Hand hand : getActiveHands()) {
            if (Player.isHuman(hand.getOwner())) {
                ((Human) hand.getOwner()).ui.showAllHandsInGame(getActiveHands());
            }
        }
    }

    public void showHandIfHuman(Hand hand) {
        if (Player.isHuman(hand.getOwner())) {
            Human player = ((Human) hand.getOwner());
            player.ui.showHandValues(getHandValue(hand));
            player.ui.showHandAllCards(hand);
        }
    }

    public void registerHand(Player player) {
        Hand hand = new Hand(player, gameRules);
        takeAnte(hand);
        activeHands.add(hand);
    }

    public void takeAnte(Hand hand) {
        hand.getOwner().ante(minimumBet);
        bets.put(hand, minimumBet);
    }

    public void takeBet(Hand hand) {
        int currentBetAmount = bets.get(hand);
        currentBetAmount += hand.getOwner().bet();
        bets.put(hand, currentBetAmount);
    }

    private void handleSplit(Hand hand) {
        if (isSplitCondition(hand) && hand.getOwner().split()) {
            Card cardForNewHand = hand.removeCard(hand.getCards().get(1));
            Hand newHand = new Hand(hand.getOwner(), gameRules);
            newHand.addCard(cardForNewHand);
            final int INDEX = getActiveHands().indexOf(hand) + 1;
            getActiveHands().add(INDEX, newHand);
            hand.addCard(deck.pull());
            newHand.addCard(deck.pull());
        }
    }

    private boolean isSplitCondition(Hand hand) {
        List<Card> cards = hand.getCards();
        return cards.get(0).VALUE == cards.get(1).VALUE;
    }

    public void finalReveal(){
        for (Hand hand : getActiveHands()) {
            if (Player.isHuman(hand.getOwner())) {
                ((Human) hand.getOwner()).ui.showAllHandsFinalReveal(getActiveHands());
            }
        }
    }

    private void handleBlackJack(Hand hand) {
        if (gameRules.isBlackJack(hand)) {
            if (Player.isHuman(hand.getOwner())) {
                ((Human) hand.getOwner()).ui.showBlackJack();
            }

        }
    }

    private void handleTwentyOne(Hand hand) {
        if (gameRules.isTwentyOne(hand)){
            if (Player.isHuman(hand.getOwner())) {
                ((Human) hand.getOwner()).ui.showTwentyOne();
            }
        }
    }

    private void handleBust(Hand hand) {
        if (gameRules.isBust(hand)) {
            if (Player.isHuman(hand.getOwner())) {
                ((Human) hand.getOwner()).ui.showBust();
            }
        }
    }

    public List<Hand> getActiveHands() {
        return activeHands;
    }
}
