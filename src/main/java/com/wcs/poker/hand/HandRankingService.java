package com.wcs.poker.hand;

import com.wcs.poker.gamestate.Card;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author thomas
 */
public class HandRankingService {

    private List<Card> loadCards;
    
    public Hand evaulate(List<Card> loadCards) {
        this.loadCards=loadCards;
        Hand hand = null;
        if (isPair()) {
            hand = new Hand(HandRank.PAIR, loadCards);
        }
        Collections.sort(loadCards);
        loadCards.subList(0, 5);
        return hand;
        //return straightHand(loadCards);
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

    private boolean isPair() {
        for (int i = 0; i < loadCards.size(); i++) {
            Card card0 = loadCards.get(i);
            for (int j = i + 1; j < loadCards.size(); j++) {
                Card card1 = loadCards.get(j);
                if (card0.isPair(card1)) {
                    return true;
                }
            }
        }
        return false;
    }
}
