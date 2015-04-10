package org.leanpoker.player.holecards;

import org.leanpoker.player.PreFlopContoller;

public class FaceCards extends AbstractHand{

    private static final String[] RANKS = {"K", "Q", "J", "10"};

    public FaceCards() {
    }

    public FaceCards(PreFlopContoller preFlopController, Position myPositionCat, BetEvent whatHappenedBeforeMe) {
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
            case EARLY:
            case MIDDLE: 
                return 0;
            case LATE:
            case BLINDS: {
                switch (whatHappenedBeforeMe) {
                    case EVERYBODY_FOLDED:
                        return minimumRaise;
                    case SOMEBODY_CALLED:
                        return call;
                    case SOMEBODY_RAISED:
                        return 0;
                }
            }
            default:
                return 0;
        }
    }
}
