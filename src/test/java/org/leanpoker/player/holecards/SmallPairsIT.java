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
public class SmallPairsIT {
    private SmallPairs smallPairs;
    private PreFlopContoller pfc;
    
    public SmallPairsIT() {
    }
    
    @Before
    public void setUp() {
        smallPairs = new SmallPairs();
        pfc = new PreFlopContoller();
        smallPairs.setPreFlopController(pfc);
        smallPairs.setMinimum_raise(1000);
        smallPairs.setCall(300);
    }
    
    @Test
    public void pairOfFoursAtEarlyPosition() {
        smallPairs.setCard1(new Card("4", "hearts"));
        smallPairs.setCard2(new Card("4", "spades"));
        smallPairs.setMyPositionCat("Early");
        smallPairs.setWhatHappenedBeforeMe("Somebody raised");
        assertEquals((Integer)300, smallPairs.betRequest());
    }
    
    @Test
    public void pairOfFoursAtEarlyPositionFolded() {
        smallPairs.setCard1(new Card("4", "hearts"));
        smallPairs.setCard2(new Card("4", "spades"));
        smallPairs.setMyPositionCat("Early");
        smallPairs.setWhatHappenedBeforeMe("Everybody folded");
        assertEquals((Integer)0, smallPairs.betRequest());
    }
    
    @Test
    public void pairOfSevensAtMiddlePositionRaised() {
        smallPairs.setCard1(new Card("7", "hearts"));
        smallPairs.setCard2(new Card("7", "diamonds"));
        smallPairs.setMyPositionCat("Mid");
        smallPairs.setWhatHappenedBeforeMe("Somebody raised");
        assertEquals((Integer)300, smallPairs.betRequest());
    }
    
    @Test
    public void pairOfTwosAtLatePositionRaised() {
        smallPairs.setCard1(new Card("2", "spades"));
        smallPairs.setCard2(new Card("2", "diamonds"));
        smallPairs.setMyPositionCat("Late");
        smallPairs.setWhatHappenedBeforeMe("Somebody raised");
        assertEquals((Integer)300, smallPairs.betRequest());
    }
    
    @Test
    public void pairOfEightsAtBlindPosition() {
        smallPairs.setCard1(new Card("8", "hearts"));
        smallPairs.setCard2(new Card("8", "spades"));
        smallPairs.setMyPositionCat("Blinds");
        smallPairs.setWhatHappenedBeforeMe("Somebody raised");
        assertEquals((Integer)300, smallPairs.betRequest());
    }
}
