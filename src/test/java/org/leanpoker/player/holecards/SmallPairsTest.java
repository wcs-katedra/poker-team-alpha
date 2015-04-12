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
public class SmallPairsTest {
    private SmallPairs smallPairs;
    private PreFlopContoller preFlopContoller;
    
    public SmallPairsTest() {
    }
    
    @Before
    public void setUp() {
        smallPairs = new SmallPairs();
        preFlopContoller = new PreFlopContoller();
        smallPairs.setPreFlopController(preFlopContoller);
        smallPairs.setMinimumRaise(1000);
        smallPairs.setCall(300);
    }
    
    @Test
    public void pairOfFoursAtEarlyPosition() {
        smallPairs.setCard1(new Card("4", "hearts"));
        smallPairs.setCard2(new Card("4", "spades"));
        smallPairs.setMyPositionCat(Position.EARLY);
        smallPairs.setWhatHappenedBeforeMe(BetEvent.SOMEBODY_RAISED);
        assertEquals(300, smallPairs.betRequest());
    }
    
    @Test
    public void pairOfFoursAtEarlyPositionFolded() {
        smallPairs.setCard1(new Card("4", "hearts"));
        smallPairs.setCard2(new Card("4", "spades"));
        smallPairs.setMyPositionCat(Position.EARLY);
        smallPairs.setWhatHappenedBeforeMe(BetEvent.EVERYBODY_FOLDED);
        assertEquals(0, smallPairs.betRequest());
    }
    
    @Test
    public void pairOfSevensAtMiddlePosition() {
        smallPairs.setCard1(new Card("7", "hearts"));
        smallPairs.setCard2(new Card("7", "diamonds"));
        smallPairs.setMyPositionCat(Position.MIDDLE);
        assertEquals(300, smallPairs.betRequest());
    }
    
    @Test
    public void pairOfTwosAtLatePositionRaised() {
        smallPairs.setCard1(new Card("2", "spades"));
        smallPairs.setCard2(new Card("2", "diamonds"));
        smallPairs.setMyPositionCat(Position.LATE);
        smallPairs.setWhatHappenedBeforeMe(BetEvent.SOMEBODY_RAISED);
        assertEquals(300, smallPairs.betRequest());
    }
    
    @Test
    public void pairOfEightsAtBlindPosition() {
        smallPairs.setCard1(new Card("8", "hearts"));
        smallPairs.setCard2(new Card("8", "spades"));
        smallPairs.setMyPositionCat(Position.BLINDS);
        assertEquals(300, smallPairs.betRequest());
    }
}
