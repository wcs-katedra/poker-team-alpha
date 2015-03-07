package org.leanpoker.player;

import com.wcs.poker.gamestate.GameState;

public class Player {

    static final String VERSION = "Default Java folding player";

    public static int betRequest(GameState gameState) {
        // Get current player from game state
        com.wcs.poker.gamestate.Player currentPlayer = 
                gameState.getPlayers().get(gameState.getInAction());
        
        return 0;
    }

    public static void showdown(GameState gameState) {
    }
}
