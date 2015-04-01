package org.leanpoker.player;

import com.wcs.poker.gamestate.Card;
import com.wcs.poker.gamestate.GameState;
import com.wcs.poker.gamestate.Player;
import java.util.ArrayList;
import java.util.List;
import org.leanpoker.player.holecards.*;

/**
 *
 * @author SAVUAAP.PTE
 */
public class PreFlopContoller {

    private GameState gameState;
    private com.wcs.poker.gamestate.Player currentPlayer;
    private List<Card> currentHoleCards;
    private Integer bigBlind;
    private Integer call;
    private Integer minimum_raise;
    private Integer pot;
    private Integer currentDealerPosition;
    private Integer expectedPot;

    private String myPositionCat;

    private Integer middlePosition = 45;
    private Integer latePosition = 70;
    private int playersNumber;
    private Integer currentPlayerLoc;
    private List<Player> players;
    private boolean everybodyFolded;
    private Integer smallBlind;
    private int folded;
    private String whatHappenedBeforeMe;

    private List<AbstractHand> handList;

    public PreFlopContoller() {
    }

    PreFlopContoller(GameState gameState) {
        this.gameState = gameState;
    }

    int betRequest() {
        divideUp();
        handList = new ArrayList<>();
        myPositionCat = whatPositionIhave();
        expectedPot = countExpectedPot();
        whatHappenedBeforeMe = whatHappenedBeforeMe(expectedPot);
        addHands();

        Integer bet = 0;

        for (AbstractHand hand : handList) {
            bet += hand.betRequest();
        }

        return bet;
    }

    private void divideUp() {
        currentPlayerLoc = gameState.getInAction();
        currentPlayer = gameState.getPlayers().get(currentPlayerLoc);
        currentHoleCards = currentPlayer.getHoleCards();
        smallBlind = gameState.getSmallBlind();
        bigBlind = smallBlind * 2;
        call = gameState.getCurrentBuyIn() - currentPlayer.getBet();
        minimum_raise = call + gameState.getMinimumRaise();
        pot = gameState.getPot();
        currentDealerPosition = gameState.getDealer();
        players = gameState.getPlayers();
        playersNumber = players.size();
    }

    private void addHands() {
        handList.add(new HighPairs(this));
        handList.add(new BigAces(this));
        handList.add(new MidPairs(myPositionCat, whatHappenedBeforeMe, this));
        handList.add(new SmallPairs(myPositionCat, whatHappenedBeforeMe, this));
        handList.add(new MidAces(myPositionCat, whatHappenedBeforeMe, this));
//      handList.add(new SuitedAces());
        handList.add(new FaceCards(myPositionCat, whatHappenedBeforeMe, this));
        handList.add(new SuitedConnectors(myPositionCat, whatHappenedBeforeMe, this));
    }

    public String whatPositionIhave() {
        double relativPos = (double) currentPlayerLoc / playersNumber * 100;

        if (amIblind()) {
            return "Blinks";
        }
        if (relativPos < middlePosition && relativPos != 0) {
            return "Early";
        }
        if (relativPos > middlePosition
                && relativPos < latePosition) {
            return "Middle";
        }
        return "Late";
    }

    public boolean amIblind() {
        Integer smallBlindLoc = (currentDealerPosition + 1) % playersNumber;
        Integer bigBlindLoc = (currentDealerPosition + 2) % playersNumber;

        return currentPlayerLoc == smallBlindLoc
                || currentPlayerLoc == bigBlindLoc;
    }

    public boolean isPair(Card card1, Card card2) {
        return card1.equals(card2);
    }

    public boolean isTheSameSuit(Card card1, Card card2) {
        return card1.getSuit().equals(card2.getSuit());
    }

    public Integer countExpectedPot() {
        Integer expectedPot = smallBlind * 3;
        folded = 0;
        for (int i = (currentDealerPosition + 3) % playersNumber; i < currentPlayerLoc; i++) {
            if (players.get(i).getStatus().equals("active")) {
                expectedPot += bigBlind;
            } else if (players.get(i).getStatus().equals("folded")) {
                folded++;
            }
        }
        if (expectedPot == (smallBlind * 3)) {
            everybodyFolded = true;
        }
        return expectedPot;
    }

    public String whatHappenedBeforeMe(Integer expectedPot) {
        if (everybodyFolded) {
            return "Everybody folded";
        }
        if (expectedPot < pot) {
            return "Somebody raised";
        }
        if (pot > 3 * smallBlind) {
            return "Somebody called";
        }
        return "";
    }

    //getters
    public GameState getGameState() {
        return gameState;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public List<Card> getCurrentHoleCards() {
        return currentHoleCards;
    }

    public Integer getBigBlind() {
        return bigBlind;
    }

    public Integer getCall() {
        return call;
    }

    public Integer getMinimum_raise() {
        return minimum_raise;
    }

    public Integer getPot() {
        return pot;
    }

    public Integer getCurrentDealerPosition() {
        return currentDealerPosition;
    }

    public String getMyPositionCat() {
        return myPositionCat;
    }

    public Integer getMiddlePosition() {
        return middlePosition;
    }

    public Integer getLatePosition() {
        return latePosition;
    }

    public int getPlayersNumber() {
        return playersNumber;
    }

    public Integer getCurrentPlayerLoc() {
        return currentPlayerLoc;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Integer getExpectedPot() {
        return expectedPot;
    }

    public boolean isEverybodyFolded() {
        return everybodyFolded;
    }

    public Integer getSmallBlind() {
        return smallBlind;
    }

    public int getFolded() {
        return folded;
    }

    //setters
    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public void setCurrentHoleCards(List<Card> currentHoleCards) {
        this.currentHoleCards = currentHoleCards;
    }

    public void setBigBlind(Integer bigBlind) {
        this.bigBlind = bigBlind;
    }

    public void setCall(Integer call) {
        this.call = call;
    }

    public void setMinimum_raise(Integer minimum_raise) {
        this.minimum_raise = minimum_raise;
    }

    public void setPot(Integer pot) {
        this.pot = pot;
    }

    public void setCurrentDealerPosition(Integer currentDealerPosition) {
        this.currentDealerPosition = currentDealerPosition;
    }

    public void setMyPositionCat(String myPositionCat) {
        this.myPositionCat = myPositionCat;
    }

    public void setMiddlePosition(Integer middlePosition) {
        this.middlePosition = middlePosition;
    }

    public void setLatePosition(Integer latePosition) {
        this.latePosition = latePosition;
    }

    public void setPlayersNumber(int playersNumber) {
        this.playersNumber = playersNumber;
    }

    public void setCurrentPlayerLoc(Integer currentPlayerLoc) {
        this.currentPlayerLoc = currentPlayerLoc;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public void setExpectedPot(Integer expectedPot) {
        this.expectedPot = expectedPot;
    }

    public void setEverybodyFolded(boolean everybodyFolded) {
        this.everybodyFolded = everybodyFolded;
    }

    public void setSmallBlind(Integer smallBlind) {
        this.smallBlind = smallBlind;
    }

    public void setFolded(int folded) {
        this.folded = folded;
    }

}
