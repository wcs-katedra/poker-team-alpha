/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.leanpoker.player;

import com.wcs.poker.gamestate.Card;
import com.wcs.poker.gamestate.GameState;
import com.wcs.poker.gamestate.Player;
import java.util.List;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author SAVUAAP.PTE
 */
public class PreFlopContollerIT {
    
    PreFlopContoller preFlopController;
    
    public PreFlopContollerIT() {
    }
    
    @Before
    public void setUpClass() {
        preFlopController=new PreFlopContoller();
    }
    
    
    /**
     * Test of start method, of class PreFlopContoller.
     */
    //@Test
    public void testStart() {
        
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
        assertTrue(preFlopController.amIblink());
        
        preFlopController.setCurrentPlayerLoc(3);
        assertFalse(preFlopController.amIblink());
    }
    
}
