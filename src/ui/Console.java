package ui;

import deck.Card;
import hand.Hand;
import players.Human;
import players.Player;

import java.util.List;
import java.util.Scanner;

public class Console implements UI{
    private Scanner scanner = new Scanner(System.in);


    @Override
    public void showAllHandsFinalReveal(List<Hand> hands) {
        for (Hand hand : hands) {
            printPlayer(hand.owner);
            publicShowHand(hand);
            System.out.println();
        }
        System.out.println();
    }

    @Override
    public void publicShowHand(Hand hand) {
        final int FIRST_CARD = 0;
        List<Card> cards = hand.getCards();
        printCard(cards.get(FIRST_CARD));
        int restOfCards = cards.size() - 1;
        for (int i = 1; i <= restOfCards; i++) {
            System.out.print("X ");
        }
    }

    @Override
    public void privateShowHand(Hand hand) {
        List<Card> cards = hand.getCards();
        final Card FIRST_CARD = cards.get(0);
        int restOfCards = cards.size() - 1;
        for (int i = 1; i <= restOfCards - 1; i++) {
            System.out.print(cards.get(i).toString() + " ");
        }
    }

    @Override
    public void showAllHandsInGame(List<Hand> hands) {
        for (Hand hand : hands) {
            System.out.print(hand.owner.getClass().toString());
            System.out.print("  ");
            if (Player.isHuman(hand.owner)) {
                System.out.print(((Human) hand.owner).getName());
                System.out.print("  ");
            }
            privateShowHand(hand);
        }
        System.out.println();
    }

    @Override
    public boolean promptForHit() {
        System.out.println("Hit?");
        return scanner.next().toUpperCase().startsWith("Y");
    }

    @Override
    public int inputBet() {
        System.out.print("Bet Amount: ");
        return scanner.nextInt();
    }

    public void printPlayer(Player player) {
        System.out.print(Player.getPlayerType(player) + " ");
        if (Player.isHuman(player)) {
            System.out.print(((Human) player).getName() + " ");
        }
    }

    public void printCard(Card card) {
        printCardValue(card);
        printCardSuite(card);
    }

    public void printCardValue(Card card) {
        System.out.print(card.VALUE);
    }

    public void printCardSuite(Card card) {
        switch (card.SUITE)
        {
            case CLUB:
                System.out.print("♣");
            case HEART:
                System.out.print("❤️");
            case SPADE:
                System.out.print("♠️");
            case DIAMOND:
                System.out.print("♦️");
        }
    }

}
