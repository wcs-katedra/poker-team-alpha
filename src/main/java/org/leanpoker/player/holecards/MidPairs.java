/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.leanpoker.player.holecards;

import com.wcs.poker.gamestate.Card;
import com.wcs.poker.gamestate.Player;
import org.leanpoker.player.PreFlopContoller;

import java.util.List;

/**
 *
 * @author SAVUAAP.PTE
 */
public class MidPairs {
    
    
    private PreFlopContoller preFlopController;
    private List<Card> currentHoleCards;
    private List<Player> players;
    private  String myPositionCat;
    

    public MidPairs(List<Card> currentHoleCards, List<Player> players, String myPositionCat,PreFlopContoller preFlopController) {
        this.preFlopController=preFlopController;
        this.players=players;
        this.currentHoleCards=currentHoleCards;
        this.myPositionCat=myPositionCat;
    }
    
    public Integer start() {
        if()
    }
    
}
