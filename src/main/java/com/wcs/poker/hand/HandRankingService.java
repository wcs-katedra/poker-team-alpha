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
