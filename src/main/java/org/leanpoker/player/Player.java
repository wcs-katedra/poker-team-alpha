package org.leanpoker.player;

import com.wcs.poker.gamestate.Card;
import com.wcs.poker.gamestate.GameState;
import java.util.List;

public class Player {

    static final String VERSION = "Default Java folding player";

    public static int betRequest(GameState gameState) {
        // Get current player from game state
        com.wcs.poker.gamestate.Player currentPlayer = 
                gameState.getPlayers().get(gameState.getInAction());
        
        // Get current player's hold cards
        List<Card> currentHoleCards = currentPlayer.getHoleCards();
        
        // Get bigBlind
        Integer bigBlind = gameState.getSmallBlind()*2;
        
        return 0;
    }

    public static void showdown(GameState gameState) {
    }
}
