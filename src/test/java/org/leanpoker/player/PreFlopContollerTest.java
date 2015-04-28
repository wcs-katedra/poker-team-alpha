/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.leanpoker.player;

import com.wcs.poker.gamestate.Player;
import com.wcs.poker.gamestate.GameState;
import com.wcs.poker.jsonconverter.JsonConverter;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.leanpoker.player.holecards.BetEvent;
import org.leanpoker.player.holecards.Position;

/**
 *
 * @author SAVUAAP.PTE
 */
public class PreFlopContollerTest {
    
    private PreFlopContoller preFlopController;
    private GameState gameState;
    private static final String SOURCE = "PreFlopControllerTest.json";
    private static final String SOURCE2 = "PreFlopControllerTest2.json";
    
    public PreFlopContollerTest() {
    }
    
    @Before
    public void setUpClass() {
        readJson(SOURCE);
        
    }
    
    @Test
    public void testWhatHappenedBefore() {
        int expectedPot=500;
        
        preFlopController.setEverybodyFolded(true);
        assertEquals(BetEvent.EVERYBODY_FOLDED, preFlopController.whatHappenedBeforeMe(expectedPot));
        
        preFlopController.setPot(50);
        preFlopController.setEverybodyFolded(false);
        expectedPot=120;
        assertEquals(BetEvent.SOMEBODY_CALLED, preFlopController.whatHappenedBeforeMe(expectedPot));
        
        preFlopController.setPot(1000);
        expectedPot=500;
        assertEquals(BetEvent.SOMEBODY_RAISED, preFlopController.whatHappenedBeforeMe(expectedPot));
    }
    
    @Test
    public void testCountExpectedPot() {
        assertTrue((30+2*20)==preFlopController.countExpectedPot());
        assertFalse(preFlopController.isEverybodyFolded());
        assertTrue(preFlopController.getFolded()==2);
        
        readJson(SOURCE2);
        
        assertTrue(30==preFlopController.countExpectedPot());
        assertTrue(preFlopController.getFolded()==6);
        assertTrue(preFlopController.isEverybodyFolded());
    }
    
    @Test
    public void testWhatPositionIhave() {
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
        preFlopController.setPlayersNumber(10);
        
        preFlopController.setCurrentPlayerLoc(2);
        assertTrue(preFlopController.amIblind());
        
        preFlopController.setCurrentPlayerLoc(3);
        assertFalse(preFlopController.amIblind());
    }

    private void readJson(String source) {
        try (InputStream resource = getClass().getResourceAsStream(source)) {
            String json = IOUtils.toString(resource);
            gameState = new JsonConverter<>(GameState.class).fromJson(json);
        } catch (Exception e) {
            e.printStackTrace();
        }
        preFlopController=new PreFlopContoller(gameState);
    }
}
