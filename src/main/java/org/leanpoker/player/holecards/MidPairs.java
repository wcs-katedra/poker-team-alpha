/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.leanpoker.player.holecards;

import org.leanpoker.player.PreFlopContoller;

/**
 *
 * @author SAVUAAP.PTE
 */
public class MidPairs extends AbstractHand{

    private String pattern = "[J9]|10";

    public MidPairs() {
    }

    public MidPairs(String myPositionCat, String whatHappenedBeforeMe, PreFlopContoller preFlopController) {
        super(preFlopController);
        this.myPositionCat = myPositionCat;
        this.whatHappenedBeforeMe = whatHappenedBeforeMe;
    }

    @Override
    public Integer betRequest() {
        if (preFlopController.isPair(card1, card2)
                && card1.getRank().matches(pattern)) {
            switch (myPositionCat) {
                case "Blinds": {
                    switch (whatHappenedBeforeMe) {
                        case "Everybody folded":return minimum_raise;
                        case "Somebody called":return minimum_raise;
                        case "Somebody raised":return call;
                    }
                };
                case "Early":{
                    switch (whatHappenedBeforeMe) {
                        case "Everybody folded":return 0;
                        case "Somebody called":return minimum_raise;
                        case "Somebody raised":return call;
                    }
                };
                case "Mid":{
                    switch (whatHappenedBeforeMe) {
                        case "Everybody folded":return minimum_raise;
                        case "Somebody called":return minimum_raise;
                        case "Somebody raised":return call;
                    }
                };
                case "Late":{
                    switch (whatHappenedBeforeMe) {
                        case "Everybody folded":return minimum_raise;
                        case "Somebody called":return minimum_raise;
                        case "Somebody raised":return call;
                    }
                };
                default:
                    return 0;
            }
        } else {
            return 0;
        }
    }
    
}
