
package org.leanpoker.player.holecards;

import org.leanpoker.player.PreFlopContoller;

/**
 *
 * @author SAVUAAP.PTE
 */
public class HighPairs extends AbstractHand{
    
    private String pattern = "[AKQ]";

    public HighPairs(){}
    
    public HighPairs(PreFlopContoller pfc) {
        super(pfc);
    }

    @Override
    public Integer betRequest() {
        if(preFlopController.isPair(card1, card2)
                && card1.getRank().matches(pattern)){
            return minimum_raise;
        }
        return 0;
    }
    
    //getters

    public String getPattern() {
        return pattern;
    }
    
    //setters

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }
    
}
