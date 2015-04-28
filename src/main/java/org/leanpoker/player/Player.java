package org.leanpoker.player;

import com.wcs.poker.gamestate.GameState;

public class Player {

    static final String VERSION = "Chuck Norris"; //mert legyőzhetetlen :D

    public static int betRequest(GameState gameState) {
        return new PreFlopController(gameState).betRequest();
    }

    public static void showdown(GameState gameState) {
    }
}
