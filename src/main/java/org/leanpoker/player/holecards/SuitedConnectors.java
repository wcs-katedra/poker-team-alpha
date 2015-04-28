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
public class SuitedConnectors extends AbstractHand {
    
    private static final String[] MINTA ={"J","10","9","8","7","6","5","4"};
    
    public SuitedConnectors(){}
    
    public SuitedConnectors(PreFlopController preFlopController, Position myPositionCat, BetEvent whatHappenedBeforeMe) {
        super(preFlopController, myPositionCat, whatHappenedBeforeMe);
    }

    @Override
    public boolean ruleIsApplicable() {
        return card1.isTheSameSuit(card2) && checkIfTheCardsAreInOrder();
    }
    
    @Override
    public int betRequest() {
        switch (myPositionCat) {
            case EARLY:
            case MIDDLE: return 0;
            case LATE: return betForLate();
            case BLINDS: return betForBlinds();
            default:
                return 0;
        }
    }

    private int betForBlinds() {
        switch (whatHappenedBeforeMe) {
            case SOMEBODY_CALLED:
                return call;
            case EVERYBODY_FOLDED:
            case SOMEBODY_RAISED:
            default:
                return 0;
        }
    }

    private int betForLate() {
        switch (whatHappenedBeforeMe) {
            case EVERYBODY_FOLDED:
                return minimumRaise;
            case SOMEBODY_CALLED:
                return call;
            case SOMEBODY_RAISED:
            default:
                return 0;
        }
    }
    
    private boolean checkIfTheCardsAreInOrder() {
        for (int i = 0; i < MINTA.length - 1; i++) {
            if ((card1.getRank().equals(MINTA[i]) && card2.getRank().equals(MINTA[i+1])) ||
                (card2.getRank().equals(MINTA[i]) && card1.getRank().equals(MINTA[i+1]))) {
                    return true;
            }
        }
        return false; 
    }
}
