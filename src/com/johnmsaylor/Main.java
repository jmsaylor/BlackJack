package com.johnmsaylor;

import deck.Deck52;
import game.BlackJack;
import players.AI;
import players.Human;
import players.Player;
import ui.Console;

public class Main {

    public static void main(String[] args) {
        var game = new BlackJack(10);
        Console console = new Console();

        Player humanPlayer = new Human("John", console);
        Player aiPlayer = new AI();

        ((Human) humanPlayer).addFundsToBalance(50);

        game.registerHand(humanPlayer);
        game.registerHand(aiPlayer);

        game.play();

    }
}
