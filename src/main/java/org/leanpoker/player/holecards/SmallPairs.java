package org.leanpoker.player.holecards;

import org.leanpoker.player.PreFlopController;

/**
 * @author Péter
 */
public class SmallPairs extends AbstractHand{

    private static final String PATTERN = "[8765432]";

    public SmallPairs() {
    }

    public SmallPairs(PreFlopController preFlopController, Position myPositionCat, BetEvent whatHappenedBeforeMe) {
        super(preFlopController, myPositionCat, whatHappenedBeforeMe);
    }

    @Override
    public boolean ruleIsApplicable() {
        if (card1.isPair(card2) && card1.getRank().matches(PATTERN)) {
            return true;
        }
        return false;
    }
    
    @Override
    public int betRequest() {
        switch (myPositionCat) {
            case EARLY: return betForEarly();
            case LATE: return betForLate();
            case MIDDLE:
            case BLINDS: return call;
            default: return 0;
        }
    }

    private int betForLate() {
        switch (whatHappenedBeforeMe) {
            case EVERYBODY_FOLDED:
                return minimumRaise;
            case SOMEBODY_CALLED:
            case SOMEBODY_RAISED:
                return call;
            default: return 0;
        }
    }

    private int betForEarly() {
        switch (whatHappenedBeforeMe) {
            case EVERYBODY_FOLDED:
                return 0;
            case SOMEBODY_CALLED:
                return 0;
            case SOMEBODY_RAISED:
                return call;
            default: return 0;
        }
    }

}
