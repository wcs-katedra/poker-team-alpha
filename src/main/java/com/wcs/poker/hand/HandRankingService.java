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
    private HandRank handRank;

    public Hand evaulate(List<Card> loadCards) {
        this.loadCards = loadCards;
        Collections.sort(loadCards);
        evaulateHighCard();
//        evaulatePair();
//        evaulateTwoPair();
//        evaulateThreeOfKind();
//        evaulateStraight();
//        evaulateFlush();
//        evaulateFullHouse();
//        evaulateFourOfKind();
//        evaulateStraightFlush();
//        evaulateRoyalFlush();
        resizeLoadCards();
        return new Hand(handRank, loadCards);
        //return straightHand(loadCards);
    }

    private void evaulateHighCard() {
        handRank = HandRank.HIGH_CARD;
    }

    
    private void evaulatePair() {
        for (int i = 0; i < loadCards.size(); i++) {
            Card card0=loadCards.get(i);
            for (int j = i+1; j < loadCards.size(); j++) {
                Card card1=loadCards.get(j);
                if (card0.isPair(card1)) {
                    handRank=HandRank.PAIR;
                    
                }
            }
        }
    }
    
    private void resizeLoadCards() {
        loadCards.subList(5, loadCards.size()).clear();
    }
//------------------------------------------------------------------------------
    private Hand straightHand(List<Card> loadCards) {
        Collections.sort(loadCards);
        Card previousCard = null;
        int counter = 0;
        for (Card card : loadCards) {
            if (previousCard != null && compareNeighbours(previousCard, card)) {
                counter++;
            }
            previousCard = card;
        }
        if (counter == 5) {
            return new Hand(HandRank.STRAIGHT, loadCards);
        }
        return null;
    }

    private boolean compareNeighbours(Card previousCard, Card card) {
        return previousCard.getRankEnum().ordinal() + 1 == card.getRankEnum().ordinal();
    }

    
}
