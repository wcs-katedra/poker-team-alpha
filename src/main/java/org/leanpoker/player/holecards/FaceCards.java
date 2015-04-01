package org.leanpoker.player.holecards;

import org.leanpoker.player.PreFlopContoller;

public class FaceCards extends AbstractHand{

    private static final String[] RANKS = {"K", "Q", "J", "10"};

    public FaceCards() {
    }

    public FaceCards(String myPositionCat, String whatHappenedBeforeMe, PreFlopContoller preFlopController) {
        super(preFlopController);
        this.myPositionCat = myPositionCat;
        this.whatHappenedBeforeMe = whatHappenedBeforeMe;
    }

    @Override
    public Integer betRequest() {
        for (int i = 0; i < RANKS.length - 1; i++) {
            for (int j = i + 1; j < RANKS.length; j++) {
                if ((card1.getRank().equals(RANKS[i])
                        && card2.getRank().equals(RANKS[j]))
                    || (card2.getRank().equals(RANKS[i])
                        && card1.getRank().equals(RANKS[j]))) {
                    switch (myPositionCat) {
                        case "Early":
                        case "Mid": 
                            return 0;
                        case "Late":
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
                        default:
                            return 0;
                    }
                }
            }
        }
        return 0;
    }
}
