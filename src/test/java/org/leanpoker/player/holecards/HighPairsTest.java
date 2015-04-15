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
public class HighPairsTest {
    
    private HighPairs highPairs;
    private PreFlopContoller preFlopController;
    
    public HighPairsTest() {
    }
    
    @Before
    public void setUp() {
        highPairs = new HighPairs();
        preFlopController = new PreFlopContoller();
        highPairs.setPreFlopController(preFlopController);
        highPairs.setMinimumRaise(100);
    }

    @Test
    public void testAA() {
        highPairs.setCard1(new Card("A","spades"));
        highPairs.setCard2(new Card("A","clubs"));
        assertEquals(100, highPairs.betRequest());
    }
    
    @Test
    public void testKK() {
        highPairs.setCard1(new Card("K","spades"));
        highPairs.setCard2(new Card("K","clubs"));
        assertEquals(100, highPairs.betRequest());
    }
    
    @Test
    public void testQQ() {
        highPairs.setCard1(new Card("Q","spades"));
        highPairs.setCard2(new Card("Q","clubs"));
        assertEquals(100, highPairs.betRequest());
    }
    
    @Test
    public void testAQ() {
        highPairs.setCard1(new Card("A","spades"));
        highPairs.setCard2(new Card("Q","clubs"));
        assertFalse(highPairs.ruleIsApplicable());
    }
    
    @Test
    public void testT9() {
        highPairs.setCard1(new Card("10","spades"));
        highPairs.setCard2(new Card("9","clubs"));
        assertFalse(highPairs.ruleIsApplicable());
    }

}
