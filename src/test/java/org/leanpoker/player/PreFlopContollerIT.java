/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.leanpoker.player;

import com.wcs.poker.gamestate.Card;
import com.wcs.poker.gamestate.Player;
import com.wcs.poker.gamestate.GameState;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.leanpoker.player.holecards.BetEvent;
import org.leanpoker.player.holecards.Position;

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
    }
    
    /**
     * Test of start method, of class PreFlopContoller.
     */
    //@Test
    public void testStart() {
        
    }
    
    @Test
    public void testWhatHappenedBefore() {
        preFlopController.setPot(1000);
        preFlopController.setSmallBlind(10);
        preFlopController.setEverybodyFolded(true);
        Integer expectedPot=500;
        assertEquals(BetEvent.EVERYBODY_FOLDED, preFlopController.whatHappenedBeforeMe(expectedPot));
        
        preFlopController.setPot(50);
        preFlopController.setSmallBlind(10);
        preFlopController.setEverybodyFolded(false);
        expectedPot=120;
        assertEquals(BetEvent.SOMEBODY_CALLED, preFlopController.whatHappenedBeforeMe(expectedPot));
        
        preFlopController.setPot(1000);
        preFlopController.setSmallBlind(10);
        preFlopController.setEverybodyFolded(false);
        expectedPot=500;
        assertEquals(BetEvent.SOMEBODY_RAISED, preFlopController.whatHappenedBeforeMe(expectedPot));
    }
    
    @Test
    public void testCountExpectedPot() {
        List<Player> players=new ArrayList<>();
        preFlopController.setCurrentDealerPosition(0);
        preFlopController.setCurrentPlayerLoc(9);
        preFlopController.setBigBlind(20);
        preFlopController.setSmallBlind(10);
        Player player1,player2,player3;
        Random rand = new Random();
        for (int i = 0; i < 3; i++) {
            player1=new Player(i, "Dani", "active", "Default", 1000, 20);
            players.add(player1);
        }
        for (int i = 0; i < 2; i++) {
            player2=new Player(i, "Dávid", "folded", "Default", 1000, 0);
            players.add(player2);
            player1=new Player(i+1, "Erzsi", "active", "Default", 1000, 20);
            players.add(player1);
            player3=new Player(i+2, "Vince", "out", "Default", 1000, 0);
            players.add(player3);
        }
        preFlopController.setPlayers(players);
        preFlopController.setPlayersNumber(players.size());
        
        assertTrue((30+2*20)==preFlopController.countExpectedPot());
        assertFalse(preFlopController.isEverybodyFolded());
        assertTrue(preFlopController.getFolded()==2);
        
        players.clear();
        for (int i = 0; i < 3; i++) {
            player1=new Player(i, "Dani", "active", "Default", 1000, 20);
            players.add(player1);
        }
        for (int i = 0; i < 6; i++) {
            player1=new Player(i, "Üres", "folded", "Default", 1000, 20);
            players.add(player1);
        }
        preFlopController.setPlayers(players);
        preFlopController.setPlayersNumber(players.size());
        
        assertTrue(30==preFlopController.countExpectedPot());
        assertTrue(preFlopController.getFolded()==6);
        assertTrue(preFlopController.isEverybodyFolded());
    }
    
    @Test
    public void testWhatPositionIhave() {
        preFlopController.setCurrentDealerPosition(0);
        preFlopController.setCurrentPlayerLoc(1);
        preFlopController.setPlayersNumber(10);
        preFlopController.setMiddlePosition(33);
        preFlopController.setLatePosition(66);
        
        assertEquals(Position.BLINDS, preFlopController.whatPositionIhave());
        preFlopController.setCurrentPlayerLoc(3);
        assertEquals(Position.EARLY, preFlopController.whatPositionIhave());
        preFlopController.setCurrentPlayerLoc(6);
        assertEquals(Position.MIDDLE, preFlopController.whatPositionIhave());
        preFlopController.setCurrentPlayerLoc(8);
        assertEquals(Position.LATE, preFlopController.whatPositionIhave());
        preFlopController.setCurrentPlayerLoc(0);
        assertEquals(Position.LATE, preFlopController.whatPositionIhave());
    }
    
    @Test
    public void testAmIBlind() {
        preFlopController.setCurrentDealerPosition(0);
        preFlopController.setPlayersNumber(10);
        
        preFlopController.setCurrentPlayerLoc(2);
        assertTrue(preFlopController.amIblind());
        
        preFlopController.setCurrentPlayerLoc(3);
        assertFalse(preFlopController.amIblind());
    }

}
