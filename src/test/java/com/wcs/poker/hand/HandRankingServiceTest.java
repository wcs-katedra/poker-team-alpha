package com.wcs.poker.hand;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wcs.poker.gamestate.Card;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import org.apache.commons.io.IOUtils;
import org.hamcrest.core.Is;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;


/**
 *
 * @author thomas
 */
public class HandRankingServiceTest {
    
    private HandRankingService handRankingService;
    
    @Before
    public void setUp() {
        handRankingService = new HandRankingService();
    }
    
    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void testNotLessThenFiveCardsAreAccepted() {
        handRankingService.evaluate(Collections.nCopies(4, (Card) null));
    }
    
    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void testNotMoreThenSevenCardsAreAccepted() {
        handRankingService.evaluate(Collections.nCopies(8, (Card) null));
    }

    @Ignore
    @Test
    public void testEvaluateRoyalFlushHand() throws Exception {
        Hand hand = handRankingService.evaluate(loadCards("royalFlushHandTestDataSet.json"));
        
        assertThat(hand.getRank(), Is.is(HandRank.ROYAL_FLUSH));
        assertThat(hand.getCards(), Is.is((Collection) loadCards("royalFlushHand.json")));
    }
    
//    @Ignore
    @Test
    public void testEvaluateStraightFlush() throws Exception {
        Hand hand = handRankingService.evaluate(loadCards("straightFlushHandTestDataSet.json"));
        
        assertThat(hand.getRank(), Is.is(HandRank.STRAIGHT_FLUSH));
        assertThat(hand.getCards(), Is.is((Collection) loadCards("straightFlush.json")));
    }
    
//    @Ignore
    @Test
    public void testEvaluateFourOfAKind() throws Exception {
        Hand hand = handRankingService.evaluate(loadCards("fourOfAKindTestDataSet.json"));
        
        assertThat(hand.getRank(), Is.is(HandRank.FOUR_OF_A_KIND));
        assertThat(hand.getCards(), Is.is((Collection) loadCards("fourOfAKindHand.json")));
    }
    
//    @Ignore
    @Test
    public void testEvaluateFullHouse() throws Exception {
        Hand hand = handRankingService.evaluate(loadCards("fullHouseTestDataSet.json"));
        
        assertThat(hand.getRank(), Is.is(HandRank.FULL_HOUSE));
        assertThat(hand.getCards(), Is.is((Collection) loadCards("fullHouseHand.json")));
    }
    
//    @Ignore
    @Test
    public void testEvaluateFlush() throws Exception {
        Hand hand = handRankingService.evaluate(loadCards("flushTestDataSet.json"));
        
        assertThat(hand.getRank(), Is.is(HandRank.FLUSH));
        assertThat(hand.getCards(), Is.is((Collection) loadCards("flushHand.json")));
    }
    
    //    @Ignore
    @Test
    public void testEvaluateStraightA() throws Exception {
        Hand hand = handRankingService.evaluate(loadCards("straightTestDataSet_A.json"));
        
        assertThat(hand.getRank(), Is.is(HandRank.STRAIGHT));
        assertThat(hand.getCards(), Is.is((Collection) loadCards("straightHand_A.json")));
    }
    
//    @Ignore
    @Test
    public void testEvaluateStraight() throws Exception {
        Hand hand = handRankingService.evaluate(loadCards("straightTestDataSet.json"));
        
        assertThat(hand.getRank(), Is.is(HandRank.STRAIGHT));
        assertThat(hand.getCards(), Is.is((Collection) loadCards("straightHand.json")));
    }
//    @Ignore
    @Test
    public void testEvaluateThreeOfAKind() throws Exception {
        Hand hand = handRankingService.evaluate(loadCards("threeOfAKindTestDataSet.json"));
        
        assertThat(hand.getRank(), Is.is(HandRank.THREE_OF_A_KIND));
        assertThat(hand.getCards(), Is.is((Collection) loadCards("threeOfAKindHand.json")));
    }
    
//    @Ignore
    @Test
    public void testEvaluateTwoPairs() throws Exception {
        Hand hand = handRankingService.evaluate(loadCards("twoPairsTestDataSet.json"));
        
        assertThat(hand.getRank(), Is.is(HandRank.TWO_PAIRS));
        assertThat(hand.getCards(), Is.is((Collection) loadCards("twoPairsHand.json")));
    }
//    @Ignore 
    @Test
    public void testEvaluatePair() throws Exception {
        Hand hand = handRankingService.evaluate(loadCards("pairTestDataSet.json"));
        
        assertThat(hand.getRank(), Is.is(HandRank.PAIR));
        assertThat(hand.getCards(), Is.is((Collection) loadCards("pairHand.json")));
    }
    
//    @Ignore
    @Test
    public void testEvaluateHighCard() throws Exception {
        Hand hand = handRankingService.evaluate(loadCards("highCardDataSet.json"));
        
        assertThat(hand.getRank(), Is.is(HandRank.HIGH_CARD));
        assertThat(hand.getCards(), Is.is((Collection) loadCards("highCardHand.json")));
    }

    private List<Card> loadCards(String name) throws IOException {
        InputStream resource = getClass().getResourceAsStream(name);
        String json = IOUtils.toString(resource);
        
        Type cardListType = new TypeToken<List<Card>>(){}.getType();
        return new Gson().fromJson(json, cardListType);
    }
}
