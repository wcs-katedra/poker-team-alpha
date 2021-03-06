package com.wcs.poker.hand;

import com.wcs.poker.gamestate.Suit;
import com.wcs.poker.gamestate.Card;
import com.wcs.poker.gamestate.Rank;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author thomas
 */
public class HandRankingService {

    private List<Card> loadCards;
    private HandRank handRank;

    public Hand evaluate(List<Card> loadCards) {
        this.loadCards = loadCards;
        Collections.sort(this.loadCards);
        evaluateHighCard();
        evaluatePair();
        evaluateTwoPair();
        evaluateThreeOfKind();
        evaluateStraight();
        evaluateFlush();
        evaluateFullHouse();
        evaluateFourOfKind();
        evaluateStraightFlush();
        evaluateRoyalFlush();
        resizeLoadCards();
        return new Hand(handRank, this.loadCards);
    }

    private void evaluateHighCard() {
        handRank = HandRank.HIGH_CARD;
    }

    private void evaluatePair() {
        for (int i = 0; i < loadCards.size() - 1; i++) {
            Card card0 = loadCards.get(i);
            Card card1 = loadCards.get(i + 1);
            if (card0.isPair(card1)) {
                handRank = HandRank.PAIR;
                relocateElement(card0, 0);
                relocateElement(card1, 1);
                return;
            }
        }
    }

    private void evaluateTwoPair() {
        for (int i = 0 + 2; i < loadCards.size() - 1; i++) {
            Card card0 = loadCards.get(i);
            Card card1 = loadCards.get(i + 1);
            if (card0.isPair(card1)) {
                handRank = HandRank.TWO_PAIRS;
                relocateElement(card0, 2);
                relocateElement(card1, 3);
                return;
            }
        }
    }

    private void evaluateThreeOfKind() {
        Card card0 = loadCards.get(1);
        for (int i = 0 + 2; i < loadCards.size(); i++) {
            Card card1 = loadCards.get(i);
            if (card0.isPair(card1)) {
                handRank = HandRank.THREE_OF_A_KIND;
                relocateElement(card1, 2);
                return;
            }
        }
    }

    private void evaluateFlush() {
        int[] counters = {0, 0, 0, 0};
        for (int i = 0; i < loadCards.size(); i++) {
            Card card0 = loadCards.get(i);
            if (card0.getSuit().equals(Suit.HEARTS.getValue()))counters[0]++;
            if (card0.getSuit().equals(Suit.SPADES.getValue()))counters[1]++;
            if (card0.getSuit().equals(Suit.CLUBS.getValue()))counters[2]++;
            if (card0.getSuit().equals(Suit.DIAMONDS.getValue()))counters[3]++;
        }
        int suitNumber = 0;
        for (int i = 0; i < counters.length; i++) {
            if (counters[i] >= 5) {
                handRank = HandRank.FLUSH;
                suitNumber = i;
                return;
            }
        }
        if (counters[suitNumber] == 5) {
            Suit winnerSuit = null;
            switch (suitNumber) {
                case 0:
                    winnerSuit = Suit.HEARTS;
                    break;
                case 1:
                    winnerSuit = Suit.SPADES;
                    break;
                case 2:
                    winnerSuit = Suit.CLUBS;
                    break;
                case 3:
                    winnerSuit = Suit.DIAMONDS;
                    break;
                default:
                    break;
            }

            Card card0 = null;
            for (Card card : loadCards) {
                if (!card.getSuit().equals(winnerSuit.getValue())) {
                    card0 = card;
                }
            }
            loadCards.remove(card0);
        }

    }
    
    private void evaluateStraight() {
        int counter=0;Card card0,card1;int a=0;
        List<Card> localCards=new ArrayList<>(loadCards);
        evaluateStraightA(localCards);
        
        
        for (int i = 0; i < localCards.size()-1; i++) {
            card0=localCards.get(i+a);
            card1=localCards.get(i+1+a);
            if (compareNeighbours(card0, card1)) {
                localCards=relocateElement(card0, counter,localCards);
                localCards=relocateElement(card1, counter + 1,localCards);
                counter++;
                if (counter == 4) {
                    handRank = HandRank.STRAIGHT;
                    this.loadCards.clear();
                    this.loadCards.addAll(localCards);
                }
            } else {
                localCards=relocateElement(card0, localCards.size() - 1,localCards);
                a=-1;
            }
        }
    }

    private void evaluateFullHouse() {
        Card card0=loadCards.get(0);
        Card card1=loadCards.get(1);
        Card card2=loadCards.get(2);
        Card card3=loadCards.get(3);
        for (int i = 4; i < loadCards.size(); i++) {
            Card cardI=loadCards.get(i);
            if(card0.isPair(cardI)){
                handRank=HandRank.FULL_HOUSE;
                relocateElement(card0, 0);
                relocateElement(card1, 1);
                relocateElement(cardI, 2);
            }
            if(card2.isPair(cardI)){
                handRank=HandRank.FULL_HOUSE;
                relocateElement(card2, 0);
                relocateElement(card3, 1);
                relocateElement(cardI, 2);
            }
        }   
    }
    
    private void evaluateFourOfKind() {
        if(handRank==HandRank.FULL_HOUSE || handRank==HandRank.THREE_OF_A_KIND){
            Card card0=loadCards.get(0);
            for (int i = 3; i < loadCards.size(); i++) {
                Card card1=loadCards.get(i);
                if (card0.isPair(card1)) {
                    relocateElement(card1, 3);
                    handRank=HandRank.FOUR_OF_A_KIND;
                }
            }
        }
    }
    
    private void evaluateStraightFlush() {
        if (handRank == HandRank.FLUSH) {
            evaluateStraight();
            if (handRank == HandRank.STRAIGHT) {
                handRank=HandRank.STRAIGHT_FLUSH;
            }else{
                evaluateFlush();
            }
        }
    }
    
    private void evaluateRoyalFlush() {
        if(handRank==HandRank.STRAIGHT_FLUSH){
           Card card0=loadCards.get(0);
           if(card0.getRank().equals(Rank.A.getValue())){
            handRank=HandRank.ROYAL_FLUSH;
           }
        }
    }
    
    private void relocateElement(Card card, int index) {
        for (int i = 0; i < loadCards.size(); i++) {
            if (loadCards.get(i).equals(card)) {
                if (loadCards.get(i).getSuit().equals(card.getSuit())) {
                    loadCards.remove(i);
                    break;
                }
            }
        }
        loadCards.add(index, card);
    }
    
    private List<Card> relocateElement(Card card, int index, List<Card> cards){
        for (int i = 0; i < cards.size(); i++) {
            if (cards.get(i).equals(card)) {
                if (cards.get(i).getSuit().equals(card.getSuit())) {
                    cards.remove(i);
                    break;
                }
            }
        }
        cards.add(index, card);
        return cards;
    }
    
     private boolean compareNeighbours(Card upperCard, Card lowerCard) {
        return upperCard.getRankEnum().ordinal() == lowerCard.getRankEnum().ordinal() - 1;
    }
    
    private void resizeLoadCards() {
        loadCards.subList(5, loadCards.size()).clear();
    }
//------------------------------------------------------------------------------

//    private void evaluateStraight() {
//        Collections.sort(loadCards);
//        Card previousCard = null;
//        int counter = 0;
//        for (Card card : loadCards) {
//            if (previousCard != null && compareNeighbours(previousCard, card)) {
//                counter++;
//            }
//            previousCard = card;
//        }
//        if (counter == 5) {
//            handRank = HandRank.STRAIGHT;
//        }
//    }
//
//    private boolean compareNeighbours(Card previousCard, Card card) {
//        return previousCard.getRankEnum().ordinal() == card.getRankEnum().ordinal() + 1;
//    }

    

    private void evaluateStraightA(List<Card> localCards) {
        Card card0=localCards.get(localCards.size()-1);
        if (card0.getRank().equals(Rank.TWO.getValue())||
                card0.getRank().equals(Rank.A.getValue())) {
            card0 = localCards.get(localCards.size() - 2);
            if (card0.getRank().equals(Rank.TWO.getValue())||
                    card0.getRank().equals(Rank.THREE.getValue())) {
                card0 = localCards.get(localCards.size() - 3);
                if (card0.getRank().equals(Rank.THREE.getValue())||
                        card0.getRank().equals(Rank.FOUR.getValue())) {
                    card0 = localCards.get(localCards.size() - 4);
                    if ((card0.getRank().equals(Rank.FOUR.getValue())&&
                            localCards.get(localCards.size() - 5).getRank().equals(Rank.FIVE.getValue()))||
                            card0.getRank().equals(Rank.FIVE.getValue())) {
                        for (int i = 0; i < localCards.size(); i++) {
                            card0=localCards.get(i);
                            if(card0.getRank().equals(Rank.A.getValue())){
                                relocateElement(card0, localCards.size()-1,localCards);
                                localCards.subList(0,localCards.size()-5).clear();
                                handRank=HandRank.STRAIGHT;
                                this.loadCards.clear();
                                this.loadCards.addAll(localCards);
                                return;
                            }
                        }
                    }
                }
            }
        }
    }

    

    

    

    

    

}
