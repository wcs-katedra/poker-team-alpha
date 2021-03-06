
package org.leanpoker.player.holecards;

import org.leanpoker.player.PreFlopController;

/**
 *
 * @author SAVUAAP.PTE
 */
public class BigAces extends AbstractHand{
    
    private static final String PATTERN = "[AK]";

    public BigAces(){}
    
    public BigAces(PreFlopController preFlopContoller) {
        super(preFlopContoller);
    }

    @Override
    public boolean ruleIsApplicable() {
        if(!card1.isPair(card2)
                && card1.getRank().matches(PATTERN)
                && card2.getRank().matches(PATTERN)) {
            return true;
        }
        return false;
    }
    
    @Override
    public int betRequest() {
        return minimumRaise;
    }
    
}
