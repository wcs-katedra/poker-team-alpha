package org.leanpoker.player.holecards;

import org.leanpoker.player.PreFlopController;

public class FaceCards extends AbstractHand{

    private static final String[] RANKS = {"K", "Q", "J", "10"};

    public FaceCards() {
    }

    public FaceCards(PreFlopController preFlopController, Position myPositionCat, BetEvent whatHappenedBeforeMe) {
        super(preFlopController, myPositionCat, whatHappenedBeforeMe);
    }

    @Override
    public boolean ruleIsApplicable() {
        for (int i = 0; i < RANKS.length - 1; i++) {
            for (int j = i + 1; j < RANKS.length; j++) {
                if ((card1.getRank().equals(RANKS[i])
                        && card2.getRank().equals(RANKS[j]))
                    || (card2.getRank().equals(RANKS[i])
                        && card1.getRank().equals(RANKS[j]))) {
                    return true;
                }
            }
        }
        return false;
    }
    
    @Override
    public int betRequest() {
        switch (myPositionCat) {
            case LATE:
            case BLINDS: 
                return betForLateAndBlinds();
            case EARLY:
            case MIDDLE: 
            default:
                return 0;
        }
    }

    private int betForLateAndBlinds() {
        switch (whatHappenedBeforeMe) {
            case EVERYBODY_FOLDED:
                return minimumRaise;
            case SOMEBODY_CALLED:
                return call;
            case SOMEBODY_RAISED:
            default: return 0;
        }
    }
}
