package org.leanpoker.player.holecards;

import org.leanpoker.player.PreFlopContoller;

public class MidAces extends AbstractHand{
    private static final String PATTERN = "[QJ]|10";
    
    public MidAces() {
    }

    public MidAces(String myPositionCat, String whatHappenedBeforeMe, PreFlopContoller preFlopController) {
        super(preFlopController);
        this.myPositionCat = myPositionCat;
        this.whatHappenedBeforeMe = whatHappenedBeforeMe;
    }

    @Override
    public Integer betRequest() {
        if ((card1.getRank().equals("A")
                && card2.getRank().matches(PATTERN))
                || ( card1.getRank().matches(PATTERN)
                && card2.getRank().equals("A"))) {
            switch (myPositionCat) {
                case "Early": return 0;
                case "Mid": {
                    switch (whatHappenedBeforeMe) {
                        case "Everybody folded":
                            return minimum_raise;
                        case "Somebody called":
                            return call;
                        case "Somebody raised":
                            return 0;
                    }
                }
                ;
                case "Late": {
                    switch (whatHappenedBeforeMe) {
                        case "Everybody folded":
                            return minimum_raise;
                        case "Somebody called":
                            return minimum_raise;
                        case "Somebody raised":
                            return 0;
                    }
                }
                ;
                case "Blinds": {
                    switch (whatHappenedBeforeMe) {
                        case "Everybody folded":
                            return minimum_raise;
                        case "Somebody called":
                            return call;
                        case "Somebody raised":
                            return 0;
                    }
                }
                ;
                default:
                    return 0;
            }
        } else {
            return 0;
        }
    }

}
