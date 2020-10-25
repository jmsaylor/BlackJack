package players;

import hand.Hand;

public class Dealer extends PlayerTemplate implements Player {

    public Dealer() {
        super();
    }

    @Override
    public void ante(int amt) {

    }

    @Override
    public int bet() {
        return 0;
    }

    @Override
    public boolean hit(Hand hand) {
        boolean willHit = true;

        for (Integer handTotal : hand.getPotentialTotals()) {
            if (handTotal > 16) {
                willHit = false;
            }
        }
        return willHit;
    }

    @Override
    public boolean split() {
        return false;
    }
}
