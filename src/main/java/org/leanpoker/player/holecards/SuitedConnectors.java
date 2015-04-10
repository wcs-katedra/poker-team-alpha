/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.leanpoker.player.holecards;

import org.leanpoker.player.PreFlopContoller;

/**
 *
 * @author Pali
 */
public class SuitedConnectors extends AbstractHand{
    
    private static final String[] Minta ={"J","10","9","8","7","6","5","4","3","2"};
    
    public SuitedConnectors(){}
    
    public SuitedConnectors(String myPositionCat, String whatHappenedBeforeMe, PreFlopContoller preFlopController) {
        super(preFlopController);
        this.myPositionCat = myPositionCat;
        this.whatHappenedBeforeMe = whatHappenedBeforeMe;
    }

    @Override
    public Integer betRequest() {
         if ((preFlopController.isTheSameSuit(card1, card2))
               && checkIfTheCardsAreInOrder() == true) {
            switch (myPositionCat) {
                case "Early": return 0;
                case "Mid": return 0;
                case "Late": {
                    switch (whatHappenedBeforeMe) {
                        case "Everybody folded":
                            return minimum_raise;
                        case "Somebody called":
                            return call;
                        case "Somebody raised":
                            return 0;
                    }
                }
                ;
                case "Blinds":{
                switch (whatHappenedBeforeMe) {
                        case "Everybody folded":
                            return 0;
                        case "Somebody called":
                            return call;
                        case "Somebody raised":
                            return 0;
                    }}
                default:
                    return 0;
            }
        } else {
            return 0;
        }
    }

    private boolean checkIfTheCardsAreInOrder() {
        for (int i = 0; i < Minta.length; i++) {
            if (Minta[i+1]!=null) {
                if ((card1.getRank().equals(Minta[i]) && card2.getRank().equals(Minta[i+1])) ||
                    (card2.getRank().equals(Minta[i+1]) && card2.getRank().equals(Minta[i])))
                        return true;
            }
        }
        return false; 
    }
}
