
package org.leanpoker.player.holecards;

import org.leanpoker.player.PreFlopContoller;

/**
 *
 * @author SAVUAAP.PTE
 */
public class HighPairs extends AbstractHand{
    
    private static final String PATTERN = "[AKQ]";

    public HighPairs(){}
    
    public HighPairs(PreFlopContoller preFlopContoller) {
        super(preFlopContoller);
    }
    
    @Override
    public boolean ruleIsApplicable() {
        if(card1.isPair(card2) && card1.getRank().matches(PATTERN)) {
            return true;
        }
        return false;
    }

    @Override
    public int betRequest() {
        return minimumRaise;
    }
    
}
