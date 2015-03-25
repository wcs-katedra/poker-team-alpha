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
    
    private MidPairs midPairs;
    private PreFlopContoller preFlopController;
    
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
        midPairs.setCall(300);
        
        midPairs.setCard1(new Card("10", "spades"));
        midPairs.setCard2(new Card("10", "clubs"));
        midPairs.setMyPositionCat("Blinds");
        midPairs.setWhatHappenedBeforeMe("Everybody folded");
        assertTrue(1000==midPairs.betRequest());
        
        midPairs.setCard1(new Card("J", "spades"));
        midPairs.setCard2(new Card("J", "clubs"));
        midPairs.setMyPositionCat("Mid");
        midPairs.setWhatHappenedBeforeMe("Somebody raised");
        assertTrue(300==midPairs.betRequest());
        
        midPairs.setCard1(new Card("9", "spades"));
        midPairs.setCard2(new Card("9", "clubs"));
        midPairs.setMyPositionCat("Late");
        midPairs.setWhatHappenedBeforeMe("Somebody called");
        assertTrue(1000==midPairs.betRequest());
        
        midPairs.setCard1(new Card("9", "spades"));
        midPairs.setCard2(new Card("9", "clubs"));
        midPairs.setMyPositionCat("Early");
        midPairs.setWhatHappenedBeforeMe("Somebody raised");
        assertTrue(300==midPairs.betRequest());
        
        midPairs.setCard1(new Card("Q", "spades"));
        midPairs.setCard2(new Card("10", "clubs"));
        midPairs.setMyPositionCat("Blinds");
        midPairs.setWhatHappenedBeforeMe("Somebody raised");
        assertTrue(0==midPairs.betRequest());
    }
    
    
}
