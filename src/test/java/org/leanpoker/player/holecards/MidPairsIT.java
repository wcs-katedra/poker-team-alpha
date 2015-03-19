/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.leanpoker.player.holecards;

import com.wcs.poker.gamestate.Card;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.leanpoker.player.PreFlopContoller;

/**
 *
 * @author SAVUAAP.PTE
 */
public class MidPairsIT {
    
    MidPairs midPairs;
    PreFlopContoller preFlopController;
    
    public MidPairsIT() {
    }
    
    @Before
    public void setUp() {
        midPairs=new MidPairs();
        preFlopController=new PreFlopContoller();
        midPairs.setPreFlopController(preFlopController);
    }

    /**
     * Test of start method, of class MidPairs.
     */
    @Test
    public void testBetRequest() {
        midPairs.setMinimum_raise(1000);
        
        midPairs.setCard1(new Card("T", "spades"));
        midPairs.setCard2(new Card("T", "clubs"));
        midPairs.setMyPositionCat("Blinds");
        assertTrue(1000==midPairs.betRequest());
        
        midPairs.setCard1(new Card("J", "spades"));
        midPairs.setCard2(new Card("J", "clubs"));
        midPairs.setMyPositionCat("Mid");
        assertTrue(1000==midPairs.betRequest());
        
        midPairs.setCard1(new Card("9", "spades"));
        midPairs.setCard2(new Card("9", "clubs"));
        midPairs.setMyPositionCat("Late");
        assertTrue(1000==midPairs.betRequest());
        
        midPairs.setCard1(new Card("9", "spades"));
        midPairs.setCard2(new Card("9", "clubs"));
        midPairs.setMyPositionCat("Early");
        assertTrue(0==midPairs.betRequest());
        
        midPairs.setCard1(new Card("Q", "spades"));
        midPairs.setCard2(new Card("T", "clubs"));
        midPairs.setMyPositionCat("Blinds");
        assertTrue(0==midPairs.betRequest());
    }
    
    
}
