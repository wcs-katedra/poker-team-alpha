/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.leanpoker.player.holecards;

import org.leanpoker.player.PreFlopController;

/**
 *
 * @author SAVUAAP.PTE
 */
public class MidPairs extends AbstractHand {

    private static final String PATTERN = "[J9]|10";

    public MidPairs() {
    }

    public MidPairs(PreFlopController preFlopController, Position myPositionCat, BetEvent whatHappenedBeforeMe) {
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
            case BLINDS: 
            case MIDDLE:
            case LATE:
                return betByEvent();
            case EARLY: return betForEarly();
            default: return 0;
        }
    }

    private int betForEarly() {
        switch (whatHappenedBeforeMe) {
            case SOMEBODY_CALLED:
                return minimumRaise;
            case SOMEBODY_RAISED:
                return call;
            case EVERYBODY_FOLDED:
            default: return 0;
        }
    }

    private int betByEvent() {
        switch (whatHappenedBeforeMe) {
            case EVERYBODY_FOLDED:
            case SOMEBODY_CALLED:
                return minimumRaise;
            case SOMEBODY_RAISED:
                return call;
            default: return 0;
        }
    }

}
