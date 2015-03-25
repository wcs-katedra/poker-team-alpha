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
public class MidAcesIT {
    private MidAces midAces;
    private PreFlopContoller pfc;
    
    public MidAcesIT() {
    }
    
    @Before
    public void setUp() {
        midAces = new MidAces();
        pfc = new PreFlopContoller();
        midAces.setPreFlopController(pfc);
        midAces.setMinimum_raise(1000);
        midAces.setCall(300);
    }

    @Test
    public void AQAtEarlyPosition() {
        midAces.setCard1(new Card("A", "hearts"));
        midAces.setCard2(new Card("Q", "spades"));
        midAces.setMyPositionCat("Early");
        assertEquals((Integer)0, midAces.betRequest());
    }
    
    @Test
    public void ATMidPositionFolded() {
        midAces.setCard1(new Card("A", "hearts"));
        midAces.setCard2(new Card("10", "spades"));
        midAces.setMyPositionCat("Mid");
        midAces.setWhatHappenedBeforeMe("Everybody folded");
        assertEquals((Integer)1000, midAces.betRequest());
    }
    
    @Test
    public void JAMidPositionRaised() {
        midAces.setCard1(new Card("J", "hearts"));
        midAces.setCard2(new Card("A", "diamonds"));
        midAces.setMyPositionCat("Mid");
        midAces.setWhatHappenedBeforeMe("Somebody raised");
        assertEquals((Integer)0, midAces.betRequest());
    }
    
    @Test
    public void AQLateFolded() {
        midAces.setCard1(new Card("A", "spades"));
        midAces.setCard2(new Card("Q", "diamonds"));
        midAces.setMyPositionCat("Late");
        midAces.setWhatHappenedBeforeMe("Everybody folded");
        assertEquals((Integer)1000, midAces.betRequest());
    }
    
    @Test
    public void ATBlindCalled() {
        midAces.setCard1(new Card("A", "hearts"));
        midAces.setCard2(new Card("10", "spades"));
        midAces.setMyPositionCat("Blinds");
        midAces.setWhatHappenedBeforeMe("Somebody called");
        assertEquals((Integer)300, midAces.betRequest());
    }
    
    @Test
    public void otherCards() {
        midAces.setCard1(new Card("A", "hearts"));
        midAces.setCard2(new Card("2", "spades"));
        midAces.setMyPositionCat("Blinds");
        midAces.setWhatHappenedBeforeMe("Somebody called");
        assertEquals((Integer)0, midAces.betRequest());
    }
}
