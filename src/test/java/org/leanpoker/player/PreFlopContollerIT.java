/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.leanpoker.player;

import com.wcs.poker.gamestate.Card;
import com.wcs.poker.gamestate.GameState;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author SAVUAAP.PTE
 */
public class PreFlopContollerIT {
    
    PreFlopContoller preFlopController;
    GameState gamestate;
    
    public PreFlopContollerIT() {
    }
    
    @Before
    public void setUpClass() {
        preFlopController=new PreFlopContoller();
        gamestate=new GameState();
        gamestate.setSmallBlind(10);
    }
    
    
    /**
     * Test of start method, of class PreFlopContoller.
     */
    //@Test
    public void testStart() {
        
    }
    
    @Test
    public void testCountExpectedPot() {
        List<com.wcs.poker.gamestate.Player> players=new ArrayList<>();
        preFlopController.setCurrentDealerPosition(0);
        preFlopController.setCurrentPlayerLoc(7);
        com.wcs.poker.gamestate.Player player;
        String[] statuses= {"active","folded","out"};
        Random rand = new Random();
        for (int i = 0; i < 10; i++) {
            player=new com.wcs.poker.gamestate.Player();
            player.setStatus(statuses[rand.nextInt(statuses.length)]);
            players.add(player);
        }
        preFlopController.setPlayers(players);
        preFlopController.setPlayersNumber(players.size());
    }
    
    @Test
    public void testWhatPositionIhave() {
        preFlopController.setCurrentDealerPosition(0);
        preFlopController.setCurrentPlayerLoc(1);
        preFlopController.setPlayersNumber(10);
        preFlopController.setMiddlePosition(33);
        preFlopController.setLatePosition(66);
        
        assertEquals("Blinks", preFlopController.whatPositionIhave());
        preFlopController.setCurrentPlayerLoc(3);
        assertEquals("Early", preFlopController.whatPositionIhave());
        preFlopController.setCurrentPlayerLoc(6);
        assertEquals("Middle", preFlopController.whatPositionIhave());
        preFlopController.setCurrentPlayerLoc(8);
        assertEquals("Late", preFlopController.whatPositionIhave());
        preFlopController.setCurrentPlayerLoc(0);
        assertEquals("Late", preFlopController.whatPositionIhave());
    }
    
    @Test
    public void testAmIblink() {
        preFlopController.setCurrentDealerPosition(0);
        preFlopController.setPlayersNumber(10);
        
        preFlopController.setCurrentPlayerLoc(2);
        assertTrue(preFlopController.amIblind());
        
        preFlopController.setCurrentPlayerLoc(3);
        assertFalse(preFlopController.amIblind());
    }
    
    @Test
    public void testIsPair(){
        Card card1=new Card("A", "clubs");
        Card card2=new Card("A", "spades");
        assertTrue(preFlopController.isPair(card1, card2));
        
        card1.setRank("Q");
        assertFalse(preFlopController.isPair(card1, card2));
    }
}
