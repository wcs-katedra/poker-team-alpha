
package org.leanpoker.player.holecards;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.wcs.poker.gamestate.Card;
import org.leanpoker.player.PreFlopContoller;

/**
 *
 * @author SAVUAAP.PTE
 */
public class HighPairs {
    
    private String pattern = "[AKQ]";
    
    private PreFlopContoller preFlopController;
    private Card card1;
    private Card card2;
    private Integer minimum_raise;

    public HighPairs(){}
    
    public HighPairs(PreFlopContoller aThis) {
        this.preFlopController=aThis;
    }

    public Integer start() {
        card1 = preFlopController.getCurrentHoleCards().get(0);
        card2 = preFlopController.getCurrentHoleCards().get(1);
        minimum_raise = preFlopController.getMinimum_raise();
        return betRequest();
    }

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
    
    //setters

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

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
    
}
