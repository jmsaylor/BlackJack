package players;

import hand.Hand;

public interface Player {
    void ante(int amt);
    int bet();
    boolean hit();
    boolean hit(Hand hand);
    boolean split();

    static boolean isHuman(Player player) {
        return player instanceof Human;
    }

    static String getPlayerType(Player player) {
        return player.getClass().toString().substring(player.getClass().toString().indexOf(".") + 1);

    }
}
