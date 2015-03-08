package org.leanpoker.player;

import com.wcs.poker.gamestate.Card;
import com.wcs.poker.gamestate.GameState;
import java.util.List;

public class Player {

    static final String VERSION = "Default Java folding player";
    static final String[] RANKS={"A","K","Q","J","T","9","8","7","6","5","4","3","2"};
    static final String[] SUITS={"spades","clubs","diamonds","hearts"};
    
    
    public static int betRequest(GameState gameState) {
        int bet=0;
        // Get current player from game state
        com.wcs.poker.gamestate.Player currentPlayer = 
                gameState.getPlayers().get(gameState.getInAction());
        
        // Get current player's hold cards
        List<Card> currentHoleCards = currentPlayer.getHoleCards();
        
        // Get bigBlind
        Integer bigBlind = gameState.getSmallBlind()*2;
        
        // Get call
        int call = gameState.getCurrentBuyIn() - currentPlayer.getBet();
        
        //minimum_bet
        int minimum_raise=0;
        
        for (int i = 0; i < 3; i++) {
            if (currentHoleCards.get(0).getRank() == RANKS[i] && currentHoleCards.get(1).getRank() == RANKS[i]) {
                return minimum_raise + 2 * bigBlind;
            }
        }
        if ((currentHoleCards.get(0).getRank() == RANKS[0] && currentHoleCards.get(0).getRank() == RANKS[1])
                || (currentHoleCards.get(0).getRank() == RANKS[1] && currentHoleCards.get(0).getRank() == RANKS[0])) {
                return minimum_raise + 2 * bigBlind;
        }
        
        if (gameState.getInAction() == gameState.getDealer() + 1
                || gameState.getInAction() == gameState.getDealer() + 2) {
            //Blinds
        } else if (gameState.getInAction() == gameState.getDealer() + 3) {
            //Early postion
            
        } else if (gameState.getInAction() == gameState.getDealer() + 4) {
            //Middle postion
        } else {
            //Late postion
        }
        
        
        return bet;
    }

    public static void showdown(GameState gameState) {
    }
}
