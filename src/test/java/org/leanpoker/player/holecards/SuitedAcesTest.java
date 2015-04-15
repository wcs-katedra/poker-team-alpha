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
 * @author Péter
 */
public class SuitedAcesTest {
    private SuitedAces suitedAces;
    private PreFlopContoller preFlopContoller;
    
    public SuitedAcesTest() {
    }
    
    @Before
    public void setUp() {
        suitedAces = new SuitedAces();
        preFlopContoller = new PreFlopContoller();
        suitedAces.setPreFlopController(preFlopContoller);
        suitedAces.setMinimumRaise(1000);
        suitedAces.setCall(300);
    }

    @Test
    public void test2AEarlyFolded() {
        suitedAces.setCard1(new Card("2", "hearts"));
        suitedAces.setCard2(new Card("A", "hearts"));
        suitedAces.setMyPositionCat(Position.EARLY);
        suitedAces.setWhatHappenedBeforeMe(BetEvent.EVERYBODY_FOLDED);
        assertTrue(suitedAces.ruleIsApplicable());
        assertEquals(0, suitedAces.betRequest());
    }
    
    @Test
    public void testA5MiddleCalled() {
        suitedAces.setCard1(new Card("A", "diamonds"));
        suitedAces.setCard2(new Card("5", "diamonds"));
        suitedAces.setMyPositionCat(Position.MIDDLE);
        suitedAces.setWhatHappenedBeforeMe(BetEvent.SOMEBODY_CALLED);
        assertTrue(suitedAces.ruleIsApplicable());
        assertEquals(300, suitedAces.betRequest());
    }
    
    @Test
    public void testA6LateFolded() {
        suitedAces.setCard1(new Card("A", "spades"));
        suitedAces.setCard2(new Card("6", "spades"));
        suitedAces.setMyPositionCat(Position.LATE);
        suitedAces.setWhatHappenedBeforeMe(BetEvent.EVERYBODY_FOLDED);
        assertTrue(suitedAces.ruleIsApplicable());
        assertEquals(1000, suitedAces.betRequest());
    }
    
    @Test
    public void testOtherCards() {
        suitedAces.setCard1(new Card("K", "spades"));
        suitedAces.setCard2(new Card("10", "clubs"));
        suitedAces.setMyPositionCat(Position.LATE);
        suitedAces.setWhatHappenedBeforeMe(BetEvent.EVERYBODY_FOLDED);
        assertFalse(suitedAces.ruleIsApplicable());
    }
    
}
