/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wcs.poker.gamestate;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Péter
 */
public class CardTest {
    
    public CardTest() {
    }
    
    @Before
    public void setUp() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
        
    @Test
    public void testIsTheSameSuit() {
        Card card1=new Card("A", "clubs");
        Card card2=new Card("A", "spades");
        assertFalse(card1.isTheSameSuit(card2));
        
        card1.setSuit("spades");
        assertTrue(card1.isTheSameSuit(card2));
    }
    
    @Test
    public void testIsPair(){
        Card card1=new Card("A", "clubs");
        Card card2=new Card("A", "spades");
        assertTrue(card1.isPair(card2));
        
        card1.setRank("Q");
        assertFalse(card1.isPair(card2));
    }
}
