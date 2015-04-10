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
    protected int minimumRaise;
    protected int call;
    protected Position myPositionCat;
    protected BetEvent whatHappenedBeforeMe;

    public AbstractHand() {
    }

    public AbstractHand(PreFlopContoller preFlopContoller) {
        this.preFlopController = preFlopContoller;
        card1 = preFlopController.getCurrentHoleCards().get(0);
        card2 = preFlopController.getCurrentHoleCards().get(1);
        minimumRaise = preFlopController.getMinimumRaise();
        call = preFlopController.getCall();
    }

    public AbstractHand(PreFlopContoller preFlopController, Position myPositionCat, BetEvent whatHappenedBeforeMe) {
        this(preFlopController);
        this.myPositionCat = myPositionCat;
        this.whatHappenedBeforeMe = whatHappenedBeforeMe;
    }
    
    public abstract boolean ruleIsApplicable();
    
    public abstract int betRequest();
    
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

    public int getMinimumRaise() {
        return minimumRaise;
    }

    public Position getMyPositionCat() {
        return myPositionCat;
    }

    public BetEvent getWhatHappenedBeforeMe() {
        return whatHappenedBeforeMe;
    }

    public int getCall() {
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

    public void setMinimumRaise(int minimumRaise) {
        this.minimumRaise = minimumRaise;
    }

    public void setMyPositionCat(Position myPositionCat) {
        this.myPositionCat = myPositionCat;
    }

    public void setWhatHappenedBeforeMe(BetEvent whatHappenedBeforeMe) {
        this.whatHappenedBeforeMe = whatHappenedBeforeMe;
    }

    public void setCall(int call) {
        this.call = call;
    }
    
}
