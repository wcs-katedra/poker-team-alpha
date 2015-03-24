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
public class FaceCardsIT {
    private FaceCards faceCards;
    private PreFlopContoller pfc;
    
    public FaceCardsIT() {
    }
    
    @Before
    public void setUp() {
        faceCards = new FaceCards();
        pfc = new PreFlopContoller();
        faceCards.setPreFlopController(pfc);
        faceCards.setMinimum_raise(1000);
        faceCards.setCall(300);
    }

    @Test
    public void KQAtEarlyPosition() {
        faceCards.setCard1(new Card("K", "hearts"));
        faceCards.setCard2(new Card("Q", "spades"));
        faceCards.setMyPositionCat("Early");
        assertEquals((Integer)0, faceCards.betRequest());
    }
    
    @Test
    public void JTMidPosition() {
        faceCards.setCard1(new Card("J", "hearts"));
        faceCards.setCard2(new Card("T", "spades"));
        faceCards.setMyPositionCat("Mid");
        assertEquals((Integer)0, faceCards.betRequest());
    }
    
    @Test
    public void TJLateFolded() {
        faceCards.setCard1(new Card("T", "spades"));
        faceCards.setCard2(new Card("J", "diamonds"));
        faceCards.setMyPositionCat("Late");
        faceCards.setWhatHappenedBeforeMe("Everybody folded");
        assertEquals((Integer)1000, faceCards.betRequest());
    }
    
    @Test
    public void QTBlindCalled() {
        faceCards.setCard1(new Card("Q", "hearts"));
        faceCards.setCard2(new Card("T", "spades"));
        faceCards.setMyPositionCat("Blinds");
        faceCards.setWhatHappenedBeforeMe("Somebody called");
        assertEquals((Integer)300, faceCards.betRequest());
    }
    
    @Test
    public void TKBlindRaised() {
        faceCards.setCard1(new Card("T", "hearts"));
        faceCards.setCard2(new Card("K", "spades"));
        faceCards.setMyPositionCat("Blinds");
        faceCards.setWhatHappenedBeforeMe("Somebody raised");
        assertEquals((Integer)0, faceCards.betRequest());
    }
    
    @Test
    public void otherCards() {
        faceCards.setCard1(new Card("Q", "hearts"));
        faceCards.setCard2(new Card("2", "spades"));
        faceCards.setMyPositionCat("Blinds");
        faceCards.setWhatHappenedBeforeMe("Somebody called");
        assertEquals((Integer)0, faceCards.betRequest());
    }
}
