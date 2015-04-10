package org.leanpoker.player.holecards;

import org.leanpoker.player.PreFlopContoller;

public class MidAces extends AbstractHand{
    private static final String PATTERN = "[QJ]|10";
    
    public MidAces() {
    }

    public MidAces(PreFlopContoller preFlopController, Position myPositionCat, BetEvent whatHappenedBeforeMe) {
        super(preFlopController, myPositionCat, whatHappenedBeforeMe);
    }
    
    @Override
    public boolean ruleIsApplicable() {
        if ((card1.getRank().equals("A") && card2.getRank().matches(PATTERN)) ||
            (card1.getRank().matches(PATTERN) && card2.getRank().equals("A"))) {
            return true;
        }
        return false;
    }

    @Override
    public int betRequest() {
        switch (myPositionCat) {
            case EARLY: return 0;
            case MIDDLE: {
                switch (whatHappenedBeforeMe) {
                    case EVERYBODY_FOLDED:
                        return minimumRaise;
                    case SOMEBODY_CALLED:
                        return call;
                    case SOMEBODY_RAISED:
                        return 0;
                }
            }
            case LATE: {
                switch (whatHappenedBeforeMe) {
                    case EVERYBODY_FOLDED:
                        return minimumRaise;
                    case SOMEBODY_CALLED:
                        return minimumRaise;
                    case SOMEBODY_RAISED:
                        return 0;
                }
            }
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
