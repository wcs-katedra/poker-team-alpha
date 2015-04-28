/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.leanpoker.player.holecards;

import org.leanpoker.player.PreFlopController;

/**
 *
 * @author Pali
 */
public class SuitedAces extends AbstractHand{
    
    public static final String PATTERN = "[98765432]";

    public SuitedAces() {
    }

    public SuitedAces(PreFlopController preFlopController, Position myPositionCat, BetEvent whatHappenedBeforeMe) {
        super(preFlopController, myPositionCat, whatHappenedBeforeMe);
    }

    @Override
    public boolean ruleIsApplicable() {
        if (card1.isTheSameSuit(card2) && 
           ((card1.getRank().equals("A") && card2.getRank().matches(PATTERN)) ||
           (card2.getRank().equals("A") && card1.getRank().matches(PATTERN)))) {
            return true;
        }
        return false;
    }
    
    @Override
    public int betRequest() {
        switch (myPositionCat) {
            case EARLY: return 0;
            case BLINDS:
            case MIDDLE: return betForBlindsAndMiddle();
            case LATE: return betForLate();
            default: return 0;
        }
    }

    private int betForBlindsAndMiddle() {
        switch(whatHappenedBeforeMe) {
            case EVERYBODY_FOLDED: return minimumRaise;
            case SOMEBODY_CALLED: return call;
            case SOMEBODY_RAISED:
            default: return 0;
        }
    }

    private int betForLate() {
        switch(whatHappenedBeforeMe) {
            case EVERYBODY_FOLDED:
            case SOMEBODY_CALLED: return minimumRaise;
            case SOMEBODY_RAISED:
            default: return 0;
        }
    }

}
