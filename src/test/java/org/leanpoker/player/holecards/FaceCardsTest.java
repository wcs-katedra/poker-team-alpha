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
public class FaceCardsTest {
    private FaceCards faceCards;
    private PreFlopController preFlopController;
    
    public FaceCardsTest() {
    }
    
    @Before
    public void setUp() {
        faceCards = new FaceCards();
        preFlopController = new PreFlopController();
        faceCards.setPreFlopController(preFlopController);
        faceCards.setMinimumRaise(1000);
        faceCards.setCall(300);
    }

    @Test
    public void KQAtEarlyPosition() {
        faceCards.setCard1(new Card("K", "hearts"));
        faceCards.setCard2(new Card("Q", "spades"));
        faceCards.setMyPositionCat(Position.EARLY);
        assertEquals(0, faceCards.betRequest());
    }
    
    @Test
    public void JTMidPosition() {
        faceCards.setCard1(new Card("J", "hearts"));
        faceCards.setCard2(new Card("10", "spades"));
        faceCards.setMyPositionCat(Position.MIDDLE);
        assertEquals(0, faceCards.betRequest());
    }
    
    @Test
    public void TJLateFolded() {
        faceCards.setCard1(new Card("10", "spades"));
        faceCards.setCard2(new Card("J", "diamonds"));
        faceCards.setMyPositionCat(Position.LATE);
        faceCards.setWhatHappenedBeforeMe(BetEvent.EVERYBODY_FOLDED);
        assertEquals(1000, faceCards.betRequest());
    }
    
    @Test
    public void QTBlindCalled() {
        faceCards.setCard1(new Card("Q", "hearts"));
        faceCards.setCard2(new Card("10", "spades"));
        faceCards.setMyPositionCat(Position.BLINDS);
        faceCards.setWhatHappenedBeforeMe(BetEvent.SOMEBODY_CALLED);
        assertEquals(300, faceCards.betRequest());
    }
    
    @Test
    public void TKBlindRaised() {
        faceCards.setCard1(new Card("10", "hearts"));
        faceCards.setCard2(new Card("K", "spades"));
        faceCards.setMyPositionCat(Position.BLINDS);
        faceCards.setWhatHappenedBeforeMe(BetEvent.SOMEBODY_RAISED);
        assertEquals(0, faceCards.betRequest());
    }
    
    @Test
    public void otherCards() {
        faceCards.setCard1(new Card("Q", "hearts"));
        faceCards.setCard2(new Card("2", "spades"));
        faceCards.setMyPositionCat(Position.BLINDS);
        faceCards.setWhatHappenedBeforeMe(BetEvent.SOMEBODY_CALLED);
        assertFalse(faceCards.ruleIsApplicable());
    }
}
