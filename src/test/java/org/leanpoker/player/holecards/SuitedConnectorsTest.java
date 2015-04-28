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
 * @author Péter
 */
public class SuitedConnectorsTest {
    private SuitedConnectors suitedConnectors;
    private PreFlopController preFlopController;
    
    public SuitedConnectorsTest() {
    }
    
    @Before
    public void setUp() {
        suitedConnectors = new SuitedConnectors();
        preFlopController = new PreFlopController();
        suitedConnectors.setPreFlopController(preFlopController);
        suitedConnectors.setMinimumRaise(1000);
        suitedConnectors.setCall(300);
    }
    
    @Test
    public void testJTEarlyFolded() {
        suitedConnectors.setCard1(new Card("J", "hearts"));
        suitedConnectors.setCard2(new Card("10", "hearts"));
        suitedConnectors.setMyPositionCat(Position.EARLY);
        suitedConnectors.setWhatHappenedBeforeMe(BetEvent.EVERYBODY_FOLDED);
        assertTrue(suitedConnectors.ruleIsApplicable());
        assertEquals(0, suitedConnectors.betRequest());
    }
    
    @Test
    public void test45Middle() {
        suitedConnectors.setCard1(new Card("4", "spades"));
        suitedConnectors.setCard2(new Card("5", "spades"));
        suitedConnectors.setMyPositionCat(Position.MIDDLE);
        assertTrue(suitedConnectors.ruleIsApplicable());
        assertEquals(0, suitedConnectors.betRequest());
    }
    
    @Test
    public void test76LateCalled() {
        suitedConnectors.setCard1(new Card("4", "spades"));
        suitedConnectors.setCard2(new Card("5", "spades"));
        suitedConnectors.setMyPositionCat(Position.LATE);
        suitedConnectors.setWhatHappenedBeforeMe(BetEvent.SOMEBODY_CALLED);
        assertTrue(suitedConnectors.ruleIsApplicable());
        assertEquals(300, suitedConnectors.betRequest());
    }
    
    @Test
    public void testOtherCards() {
        suitedConnectors.setCard1(new Card("4", "spades"));
        suitedConnectors.setCard2(new Card("5", "hearts"));
        suitedConnectors.setMyPositionCat(Position.LATE);
        suitedConnectors.setWhatHappenedBeforeMe(BetEvent.SOMEBODY_CALLED);
        assertFalse(suitedConnectors.ruleIsApplicable());
    }
}
