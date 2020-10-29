package players;

import hand.Hand;

public class AI extends PlayerTemplate implements Player {
    public AI() {
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
            if (handTotal > 17) {
                willHit = false;
            }
        }
        return willHit;
    }

    @Override
    public boolean split() {
        return true;
    }
}
