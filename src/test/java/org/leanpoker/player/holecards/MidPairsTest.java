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
public class MidPairsTest {
    
    private MidPairs midPairs;
    private PreFlopContoller preFlopController;
    
    public MidPairsTest() {
    }
    
    @Before
    public void setUp() {
        midPairs=new MidPairs();
        preFlopController=new PreFlopContoller();
        midPairs.setPreFlopController(preFlopController);
        midPairs.setMinimumRaise(1000);
        midPairs.setCall(300);
    }

    @Test
    public void testTTBlindFolded() {
        midPairs.setCard1(new Card("10", "spades"));
        midPairs.setCard2(new Card("10", "clubs"));
        midPairs.setMyPositionCat(Position.BLINDS);
        midPairs.setWhatHappenedBeforeMe(BetEvent.EVERYBODY_FOLDED);
        assertTrue(1000==midPairs.betRequest());
    }
    
    @Test
    public void testJJMidRaised() {
        midPairs.setCard1(new Card("J", "spades"));
        midPairs.setCard2(new Card("J", "clubs"));
        midPairs.setMyPositionCat(Position.MIDDLE);
        midPairs.setWhatHappenedBeforeMe(BetEvent.SOMEBODY_RAISED);
        assertTrue(300==midPairs.betRequest());
    }
    
    @Test
    public void test99LateCalled() {
        midPairs.setCard1(new Card("9", "spades"));
        midPairs.setCard2(new Card("9", "clubs"));
        midPairs.setMyPositionCat(Position.LATE);
        midPairs.setWhatHappenedBeforeMe(BetEvent.SOMEBODY_CALLED);
        assertTrue(1000==midPairs.betRequest());
    }
    
    @Test
    public void test99EarlyRaised() {
        midPairs.setCard1(new Card("9", "spades"));
        midPairs.setCard2(new Card("9", "clubs"));
        midPairs.setMyPositionCat(Position.EARLY);
        midPairs.setWhatHappenedBeforeMe(BetEvent.SOMEBODY_RAISED);
        assertTrue(300==midPairs.betRequest());
    }
    
    @Test
    public void testQTBlindRaised() {
        midPairs.setCard1(new Card("Q", "spades"));
        midPairs.setCard2(new Card("10", "clubs"));
        midPairs.setMyPositionCat(Position.BLINDS);
        midPairs.setWhatHappenedBeforeMe(BetEvent.SOMEBODY_RAISED);
        assertFalse(midPairs.ruleIsApplicable());
    }

}
