package org.leanpoker.player.holecards;

import org.leanpoker.player.PreFlopContoller;

/**
 * @author Péter
 */
public class SmallPairs extends AbstractHand{

    private static final String PATTERN = "[8765432]";

    public SmallPairs() {
    }

    public SmallPairs(String myPositionCat, String whatHappenedBeforeMe, PreFlopContoller preFlopController) {
        super(preFlopController);
        this.myPositionCat = myPositionCat;
        this.whatHappenedBeforeMe = whatHappenedBeforeMe;
    }

    @Override
    public Integer betRequest() {
        if (preFlopController.isPair(card1, card2)
                && card1.getRank().matches(PATTERN)) {
            switch (myPositionCat) {
                case "Early": {
                    switch (whatHappenedBeforeMe) {
                        case "Everybody folded":
                            return 0;
                        case "Somebody called":
                            return 0;
                        case "Somebody raised":
                            return call;
                    }
                }
                ;
                case "Mid": return call;
                case "Late": {
                    switch (whatHappenedBeforeMe) {
                        case "Everybody folded":
                            return minimum_raise;
                        case "Somebody called":
                            return call;
                        case "Somebody raised":
                            return call;
                    }
                }
                ;
                case "Blinds": return call;
                default:
                    return 0;
            }
        } else {
            return 0;
        }
    }

}
