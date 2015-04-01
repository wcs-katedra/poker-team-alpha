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
 * @author wolfman
 */
public abstract class AbstractHand {
    
    protected PreFlopContoller preFlopController;
    protected Card card1;
    protected Card card2;
    protected Integer minimum_raise;
    protected Integer call;
    protected String myPositionCat;
    protected String whatHappenedBeforeMe;

    public AbstractHand() {
    }

    public AbstractHand(PreFlopContoller preFlopContoller) {
        this.preFlopController=preFlopContoller;
        card1 = preFlopController.getCurrentHoleCards().get(0);
        card2 = preFlopController.getCurrentHoleCards().get(1);
        minimum_raise = preFlopController.getMinimum_raise();
        call = preFlopController.getCall();
    }
    
    public abstract Integer betRequest();
    
    //getters

    public PreFlopContoller getPreFlopController() {
        return preFlopController;
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

    public String getMyPositionCat() {
        return myPositionCat;
    }

    public String getWhatHappenedBeforeMe() {
        return whatHappenedBeforeMe;
    }

    public Integer getCall() {
        return call;
    }
        
    //setters

    public void setPreFlopController(PreFlopContoller preFlopController) {
        this.preFlopController = preFlopController;
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

    public void setMyPositionCat(String myPositionCat) {
        this.myPositionCat = myPositionCat;
    }

    public void setWhatHappenedBeforeMe(String whatHappenedBeforeMe) {
        this.whatHappenedBeforeMe = whatHappenedBeforeMe;
    }

    public void setCall(Integer call) {
        this.call = call;
    }
    
}
