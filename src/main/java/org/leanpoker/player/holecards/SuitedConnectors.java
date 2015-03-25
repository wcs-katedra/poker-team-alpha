/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.leanpoker.player.holecards;

import com.wcs.poker.gamestate.Card;
import org.leanpoker.player.PreFlopContoller;

/**
 *
 * @author Pali
 */
public class SuitedConnectors {
    //private static final String PATTERN= "[98765432]";
    
    private static final String[] Minta ={"J","10","9","8","7","6","5","4","3","2"};
    
    private PreFlopContoller preFlopController;
    private String myPositionCat;
    private Card card1;
    private Card card2;
    private Integer minimum_raise;
    private String whatHappenedBeforeMe;
    private Integer call;
    
    public SuitedConnectors(){}
    
    public SuitedConnectors(String myPositionCat, String whatHappenedBeforeMe, PreFlopContoller preFlopController) {
        this.preFlopController = preFlopController;
        //this.players = players;
        this.myPositionCat = myPositionCat;
        this.whatHappenedBeforeMe = whatHappenedBeforeMe;
    }
    
    public Integer start() {
        card1 = preFlopController.getCurrentHoleCards().get(0);
        card2 = preFlopController.getCurrentHoleCards().get(1);
        minimum_raise = preFlopController.getMinimum_raise();
        call=preFlopController.getCall();
        return betRequest();
    }

    private Integer betRequest() {
         if ((preFlopController.isTheSameSuit(card1, card2))
               && checkIfTheCardsAreInOrder() == true) {
            switch (myPositionCat) {
                case "Early": return 0;
                case "Mid": return 0;
                case "Late": {
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
                case "Blinds":{
                switch (whatHappenedBeforeMe) {
                        case "Everybody folded":
                            return 0;
                        case "Somebody called":
                            return call;
                        case "Somebody raised":
                            return 0;
                    }}
                default:
                    return 0;
            }
        } else {
            return 0;
        }
    }

    public PreFlopContoller getPreFlopController() {
        return preFlopController;
    }

    public void setPreFlopController(PreFlopContoller preFlopController) {
        this.preFlopController = preFlopController;
    }

    public String getMyPositionCat() {
        return myPositionCat;
    }

    public void setMyPositionCat(String myPositionCat) {
        this.myPositionCat = myPositionCat;
    }

    public Card getCard1() {
        return card1;
    }

    public void setCard1(Card card1) {
        this.card1 = card1;
    }

    public Card getCard2() {
        return card2;
    }

    public void setCard2(Card card2) {
        this.card2 = card2;
    }

    public Integer getMinimum_raise() {
        return minimum_raise;
    }

    public void setMinimum_raise(Integer minimum_raise) {
        this.minimum_raise = minimum_raise;
    }

    public String getWhatHappenedBeforeMe() {
        return whatHappenedBeforeMe;
    }

    public void setWhatHappenedBeforeMe(String whatHappenedBeforeMe) {
        this.whatHappenedBeforeMe = whatHappenedBeforeMe;
    }

    public Integer getCall() {
        return call;
    }

    public void setCall(Integer call) {
        this.call = call;
    }

    private boolean checkIfTheCardsAreInOrder() {
        for (int i = 0; i < Minta.length; i++) {
            if (card1.getRank() == Minta[i]) {
                if (card2.getRank() == Minta[i-1] || card2.getRank() == Minta[i+1]) {
                    return true;
                }
            }
        }
        return false; 
    }
}
