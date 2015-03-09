package org.leanpoker.player;

import com.wcs.poker.gamestate.Card;
import com.wcs.poker.gamestate.GameState;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import org.junit.Before;

public class PlayerTest {

    private final GameState gameState = new GameState();
    
    @Before
    public void before(){
        com.wcs.poker.gamestate.Player player1 = 
                new com.wcs.poker.gamestate.Player(0,"Jóska","active",Player.VERSION,5000,0);
        com.wcs.poker.gamestate.Player player2 = 
                new com.wcs.poker.gamestate.Player(1,"Pista","active",Player.VERSION,5000,0);
        com.wcs.poker.gamestate.Player player3 = 
                new com.wcs.poker.gamestate.Player(2,"Mariska","active",Player.VERSION,5000,0);
        com.wcs.poker.gamestate.Player player4 = 
                new com.wcs.poker.gamestate.Player(3,"Irénke","active",Player.VERSION,5000,0);
        com.wcs.poker.gamestate.Player player5 = 
                new com.wcs.poker.gamestate.Player(4,"Terike","active",Player.VERSION,5000,0);
        
        List<com.wcs.poker.gamestate.Player> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        players.add(player3);
        players.add(player4);
        players.add(player5);
        
        gameState.setPlayers(players);
        gameState.setCurrentBuyIn(100);
        gameState.setInAction(0);
        gameState.setSmallBlind(10);
        gameState.setMinimumRaise(50);
        
        Card card1 = new Card("2", "hearts");
        Card card2 = new Card("3", "diamonds");
        List<Card> holeCards = new ArrayList<>();
        holeCards.add(card1);
        holeCards.add(card2);
        player1.setHoleCards(holeCards);
        
        gameState.setDealer(2);
    }

    @Test
    public void testBetRequest() throws Exception {
        assertEquals(0, Player.betRequest(gameState));
    }
}
