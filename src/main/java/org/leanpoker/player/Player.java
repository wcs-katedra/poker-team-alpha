package org.leanpoker.player;

import com.wcs.poker.gamestate.Card;
import com.wcs.poker.gamestate.GameState;
import java.util.List;

public class Player {

    static final String VERSION = "Default Java folding player";
    static final String[] RANKS={"A","K","Q","J","T","9","8","7","6","5","4","3","2"};
    static final String[] SUITS={"spades","clubs","diamonds","hearts"};
    
    
    public static int betRequest(GameState gameState) {
        // Get current player from game state
        com.wcs.poker.gamestate.Player currentPlayer = 
                gameState.getPlayers().get(gameState.getInAction());
        
        // Get current player's hole cards
        List<Card> currentHoleCards = currentPlayer.getHoleCards();
        
        // Get bigBlind
        Integer bigBlind = gameState.getSmallBlind()*2;
        
        // Get call
        int call = gameState.getCurrentBuyIn() - currentPlayer.getBet();
        
        // Minimum raise
        int minimum_raise = call + gameState.getMinimumRaise();
        
        // Hole cards
        Card card1 = currentHoleCards.get(0);
        Card card2 = currentHoleCards.get(1);
        
        // Pre-flop strategy
        for (int i = 0; i < 3; i++) {
            if (card1.getRank() == RANKS[i] && card2.getRank() == RANKS[i]) {
                //AA,KK,QQ
                return minimum_raise + 2 * bigBlind;
            }
        }
        if ((card1.getRank() == RANKS[0] && card2.getRank() == RANKS[1])
                || (card1.getRank() == RANKS[1] && card2.getRank() == RANKS[0])) {
                //AK
                return minimum_raise + bigBlind;
        }
        
        if (gameState.getInAction() == gameState.getDealer() + 1
                || gameState.getInAction() == gameState.getDealer() + 2) {
            //Blinds
            
        } else if (gameState.getInAction() == gameState.getDealer() + 3) {
            //Early postion
            return 0;
        } else {
            //Middle && Late postion
            //Raise
            for (int i = 3; i < 6; i++) {
                //JJ,TT,99
                if (card1.getRank() == RANKS[i] && card2.getRank() == RANKS[i]) {
                    return minimum_raise;
                }
                //AQ,AJ,AT
                if (card1.getRank() == RANKS[0] && card2.getRank() == RANKS[i - 1]) {
                    return minimum_raise;
                }
            }
            for (int i = 5; i <= RANKS.length; i++) {
                //A9s to A2s
                if (card1.getRank() == RANKS[0] && card2.getRank() == RANKS[i] 
                        && card1.getSuit().equals(card2.getSuit())) {
                    return minimum_raise;
                }
            }
            for (int i = 6; i <= RANKS.length; i++) {
                //88 to 22
                if (card1.getRank() == RANKS[i] && card2.getRank() == RANKS[i] 
                        && gameState.getInAction() == gameState.getDealer() + 4) {
                    return call;
                } else if (card1.getRank() == RANKS[i] && card2.getRank() == RANKS[i]){
                    return minimum_raise;
                }
            }
            
            //ONLY late position
            //Raise
            if ((gameState.getInAction() < gameState.getDealer() + 4)) {
                for (int i = 2; i < 5; i++) {
                    //KQ,KJ,KT
                    if ((card1.getRank() == RANKS[i] && card2.getRank() == RANKS[1] 
                            || card1.getRank() == RANKS[1]
                            && card2.getRank() == RANKS[i])) {
                        return minimum_raise;
                    }
                }
                for (int i = 4; i < 5; i++) {
                    //QJ,QT
                    if ((card1.getRank() == RANKS[i] && card2.getRank() == RANKS[2] 
                            || card1.getRank() == RANKS[2]
                            && card2.getRank() == RANKS[i])) {
                        return minimum_raise;
                    }
                }
                    //JT
                    if ((card1.getRank() == RANKS[3] && card2.getRank() == RANKS[4] 
                            || card1.getRank() == RANKS[4]
                            && card2.getRank() == RANKS[3])) {
                        return minimum_raise;
                    }
                for (int i = 3; i <9; i++) {
                    //JTs to 54s
                    if ((card1.getRank() == RANKS[i] && card2.getRank() == RANKS[i+1] 
                            || card1.getRank() == RANKS[i+1]
                            && card2.getRank() == RANKS[i])) {
                        return minimum_raise;
                    }
                }
            }
            
        }
        return 0;
    }

    public static void showdown(GameState gameState) {
    }
}
