package org.leanpoker.player.holecards;

import com.wcs.poker.gamestate.Card;
import org.leanpoker.player.PreFlopContoller;


public class MidAces {
    private static final String PATTERN = "[QJT]";

    private PreFlopContoller preFlopController;
    private String myPositionCat;
    private Card card1;
    private Card card2;
    private Integer minimum_raise;
    private String whatHappenedBeforeMe;
    private Integer call;
    
    public MidAces() {
    }

    public MidAces(String myPositionCat, String whatHappenedBeforeMe, PreFlopContoller preFlopController) {
        this.preFlopController = preFlopController;
        this.myPositionCat = myPositionCat;
        this.whatHappenedBeforeMe = whatHappenedBeforeMe;
    }

    public Integer start() {
        card1 = preFlopController.getCurrentHoleCards().get(0);
        card2 = preFlopController.getCurrentHoleCards().get(1);
        minimum_raise = preFlopController.getMinimum_raise();
        call = preFlopController.getCall();
        return betRequest();
    }

    public Integer betRequest() {
        if (card1.getRank().equals("A")
                && card2.getRank().matches(PATTERN)) {
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

    //getters
    public String getWhatHappenedBeforeMe() {
        return whatHappenedBeforeMe;
    }

    public Integer getCall() {
        return call;
    }

    public PreFlopContoller getPreFlopController() {
        return preFlopController;
    }

    public String getMyPositionCat() {
        return myPositionCat;
    }

    public Card getCard1() {
        return card1;
    }

    public Card getCard2() {
        return card2;
    }

    public Integer getMinimum_raise() {
        return minimum_raise;
    }

    //setters
    public void setPreFlopController(PreFlopContoller preFlopController) {
        this.preFlopController = preFlopController;
    }

    public void setMyPositionCat(String myPositionCat) {
        this.myPositionCat = myPositionCat;
    }

    public void setCard1(Card card1) {
        this.card1 = card1;
    }

    public void setCard2(Card card2) {
        this.card2 = card2;
    }

    public void setMinimum_raise(Integer minimum_raise) {
        this.minimum_raise = minimum_raise;
    }

    public void setWhatHappenedBeforeMe(String whatHappenedBeforeMe) {
        this.whatHappenedBeforeMe = whatHappenedBeforeMe;
    }

    public void setCall(Integer call) {
        this.call = call;
    }
}