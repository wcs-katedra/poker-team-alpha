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
import org.leanpoker.player.PreFlopController;

/**
 *
 * @author SAVUAAP.PTE
 */
public class BigAcesTest {
    
    private PreFlopController preFlopController;
    private BigAces bigAces;
    
    public BigAcesTest() {
    }
    
    @Before
    public void setUp() {
        bigAces = new BigAces();
        preFlopController = new PreFlopController();
        bigAces.setPreFlopController(preFlopController);
        bigAces.setMinimumRaise(100);
    }

    @Test
    public void testAK() {
        bigAces.setCard1(new Card("A","spades"));
        bigAces.setCard2(new Card("K","clubs"));
        assertEquals(100, bigAces.betRequest());
    }
    
    @Test
    public void testKA() {
        bigAces.setCard1(new Card("K","spades"));
        bigAces.setCard2(new Card("A","clubs"));
        assertEquals(100, bigAces.betRequest());
    }
    
    @Test
    public void testKK() {
        bigAces.setCard1(new Card("K","spades"));
        bigAces.setCard2(new Card("K","clubs"));
        assertFalse(bigAces.ruleIsApplicable());
    }
    
    @Test
    public void testAA() {
        bigAces.setCard1(new Card("A","spades"));
        bigAces.setCard2(new Card("A","clubs"));
        assertFalse(bigAces.ruleIsApplicable());
    }
    
    @Test
    public void testA3() {
        bigAces.setCard1(new Card("A","spades"));
        bigAces.setCard2(new Card("3","clubs"));
        assertFalse(bigAces.ruleIsApplicable());
    }

}
