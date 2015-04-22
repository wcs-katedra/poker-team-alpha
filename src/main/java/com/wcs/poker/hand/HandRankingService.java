package com.wcs.poker.hand;

import com.wcs.poker.gamestate.Card;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author thomas
 */
public class HandRankingService {

    public Hand evaulate(List<Card> loadCards) {
        return straightHand(loadCards);
    }

    private Hand straightHand(List<Card> loadCards) {
        Collections.sort(loadCards);
        Card previousCard = null;
        int counter=0;
        for (Card card : loadCards) {
            if (previousCard!=null && compareNeighbours(previousCard, card)) {
                counter++;
            } 
            previousCard = card;
        }
        if(counter==5) {
            return new Hand(HandRank.STRAIGHT, loadCards);
        }
        return null;
    }

    private boolean compareNeighbours(Card previousCard, Card card) {
        return previousCard.getRankEnum().ordinal()+1==card.getRankEnum().ordinal();
    }
    
}
