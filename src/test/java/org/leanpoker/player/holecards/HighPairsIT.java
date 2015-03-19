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
public class HighPairsIT {
    
    private HighPairs highPairs;
    private PreFlopContoller preFlopController;
    
    public HighPairsIT() {
    }
    
    @Before
    public void setUp() {
        highPairs=new HighPairs();
        preFlopController=new PreFlopContoller();
        highPairs.setPreFlopController(preFlopController);
    }

    /**
     * Test of betRequest method, of class HighPairs.
     */
    @Test
    public void testBetRequest() {
        highPairs.setMinimum_raise(100);
        
        highPairs.setCard1(new Card("A","spades"));
        highPairs.setCard2(new Card("A","clubs"));
        assertTrue(100==highPairs.betRequest());
        
        highPairs.setCard1(new Card("K","spades"));
        highPairs.setCard2(new Card("K","clubs"));
        assertTrue(100==highPairs.betRequest());
        
        highPairs.setCard1(new Card("Q","spades"));
        highPairs.setCard2(new Card("Q","clubs"));
        assertTrue(100==highPairs.betRequest());
        
        highPairs.setCard1(new Card("A","spades"));
        highPairs.setCard2(new Card("Q","clubs"));
        assertTrue(0==highPairs.betRequest());
        
        highPairs.setCard1(new Card("T","spades"));
        highPairs.setCard2(new Card("9","clubs"));
        assertTrue(0==highPairs.betRequest());
    }

}
