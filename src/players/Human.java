package players;

import ui.UI;

public class Human extends PlayerTemplate implements Player{
    private String name;
    private Integer accountBalance = 0;

    public UI ui;

    public Human(String name, UI ui) {
        super();
        this.name = name;
        this.ui = ui;
    }

    @Override
    public void ante(int amt) {
        accountBalance -= amt;
    }

    @Override
    public int bet() {
        int betAmount = ui.inputBet();
        deductFromBalance(betAmount);
        return betAmount;
    }

    public void addFundsToBalance(int amt) {
        accountBalance += amt;
    }

    public void deductFromBalance(int amt) {
        accountBalance -= amt;
    }

    public Integer getAccountBalance() {
        return accountBalance;
    }

    public String getName() {
        return name;
    }
}
