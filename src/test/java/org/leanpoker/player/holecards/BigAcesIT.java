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
public class BigAcesIT {
    
    private PreFlopContoller preFlopController;
    private BigAces bigAces;
    public BigAcesIT() {
    }
    
    @Before
    public void setUp() {
        bigAces=new BigAces();
        preFlopController=new PreFlopContoller();
        bigAces.setPreFlopController(preFlopController);
    }


    /**
     * Test of betRequest method, of class BigAces.
     */
    @Test
    public void testBetRequest() {
        bigAces.setMinimum_raise(100);
        
        bigAces.setCard1(new Card("A","spades"));
        bigAces.setCard2(new Card("K","clubs"));
        assertTrue(100==bigAces.betRequest());
        
        bigAces.setCard1(new Card("K","spades"));
        bigAces.setCard2(new Card("A","clubs"));
        assertTrue(100==bigAces.betRequest());
        
        bigAces.setCard1(new Card("K","spades"));
        bigAces.setCard2(new Card("K","clubs"));
        assertTrue(0==bigAces.betRequest());
        
        bigAces.setCard1(new Card("A","spades"));
        bigAces.setCard2(new Card("A","clubs"));
        assertTrue(0==bigAces.betRequest());
        
        bigAces.setCard1(new Card("A","spades"));
        bigAces.setCard2(new Card("3","clubs"));
        assertTrue(0==bigAces.betRequest());
    }

    
    
}
