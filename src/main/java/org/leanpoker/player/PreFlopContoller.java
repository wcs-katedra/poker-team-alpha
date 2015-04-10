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
    private int bigBlind;
    private int call;
    private int minimumRaise;
    private int pot;
    private int currentDealerPosition;
    private int expectedPot;

    private Position myPositionCat;

    private int middlePosition = 45;
    private int latePosition = 70;
    private int playersNumber;
    private int currentPlayerLoc;
    private List<Player> players;
    private boolean everybodyFolded;
    private int smallBlind;
    private int folded;
    private BetEvent whatHappenedBeforeMe;

    private List<AbstractHand> handList;

    public PreFlopContoller() {
    }

    PreFlopContoller(GameState gameState) {
        this.gameState = gameState;
    }

    public int betRequest() {
        divideUp();
        handList = new ArrayList<>();
        myPositionCat = whatPositionIhave();
        expectedPot = countExpectedPot();
        whatHappenedBeforeMe = whatHappenedBeforeMe(expectedPot);
        addHands();

        int bet = 0;

        for (AbstractHand hand : handList) {
            if (hand.ruleIsApplicable()) {
                bet = hand.betRequest();
            }
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
        minimumRaise = call + gameState.getMinimumRaise();
        pot = gameState.getPot();
        currentDealerPosition = gameState.getDealer();
        players = gameState.getPlayers();
        playersNumber = players.size();
    }

    private void addHands() {
        handList.add(new HighPairs(this));
        handList.add(new BigAces(this));
        handList.add(new MidPairs(this, myPositionCat, whatHappenedBeforeMe));
        handList.add(new SmallPairs(this, myPositionCat, whatHappenedBeforeMe));
        handList.add(new MidAces(this, myPositionCat, whatHappenedBeforeMe));
//      handList.add(new SuitedAces(this, myPositionCat, whatHappenedBeforeMe));
        handList.add(new FaceCards(this, myPositionCat, whatHappenedBeforeMe));
        handList.add(new SuitedConnectors(this, myPositionCat, whatHappenedBeforeMe));
    }

    public Position whatPositionIhave() {
        double relativPos = (double) currentPlayerLoc / playersNumber * 100;

        if (amIblind()) {
            return Position.BLINDS;
        }
        if (relativPos < middlePosition && relativPos != 0) {
            return Position.EARLY;
        }
        if (relativPos > middlePosition
                && relativPos < latePosition) {
            return Position.MIDDLE;
        }
        return Position.LATE;
    }

    public boolean amIblind() {
        int smallBlindLoc = (currentDealerPosition + 1) % playersNumber;
        int bigBlindLoc = (currentDealerPosition + 2) % playersNumber;

        return currentPlayerLoc == smallBlindLoc
                || currentPlayerLoc == bigBlindLoc;
    }

    public int countExpectedPot() {
        int expectedPot = smallBlind * 3;
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

    public BetEvent whatHappenedBeforeMe(int expectedPot) {
        if (everybodyFolded) {
            return BetEvent.EVERYBODY_FOLDED;
        }
        if (expectedPot < pot) {
            return BetEvent.SOMEBODY_RAISED;
        }
        if (pot > 3 * smallBlind) {
            return BetEvent.SOMEBODY_CALLED;
        }
        return null;
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

    public int getBigBlind() {
        return bigBlind;
    }

    public int getCall() {
        return call;
    }

    public int getMinimumRaise() {
        return minimumRaise;
    }

    public int getPot() {
        return pot;
    }

    public int getCurrentDealerPosition() {
        return currentDealerPosition;
    }

    public Position getMyPositionCat() {
        return myPositionCat;
    }

    public int getMiddlePosition() {
        return middlePosition;
    }

    public int getLatePosition() {
        return latePosition;
    }

    public int getPlayersNumber() {
        return playersNumber;
    }

    public int getCurrentPlayerLoc() {
        return currentPlayerLoc;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public int getExpectedPot() {
        return expectedPot;
    }

    public boolean isEverybodyFolded() {
        return everybodyFolded;
    }

    public int getSmallBlind() {
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

    public void setBigBlind(int bigBlind) {
        this.bigBlind = bigBlind;
    }

    public void setCall(int call) {
        this.call = call;
    }

    public void setMinimumRaise(int minimumRaise) {
        this.minimumRaise = minimumRaise;
    }

    public void setPot(int pot) {
        this.pot = pot;
    }

    public void setCurrentDealerPosition(int currentDealerPosition) {
        this.currentDealerPosition = currentDealerPosition;
    }

    public void setMyPositionCat(Position myPositionCat) {
        this.myPositionCat = myPositionCat;
    }

    public void setMiddlePosition(int middlePosition) {
        this.middlePosition = middlePosition;
    }

    public void setLatePosition(int latePosition) {
        this.latePosition = latePosition;
    }

    public void setPlayersNumber(int playersNumber) {
        this.playersNumber = playersNumber;
    }

    public void setCurrentPlayerLoc(int currentPlayerLoc) {
        this.currentPlayerLoc = currentPlayerLoc;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public void setExpectedPot(int expectedPot) {
        this.expectedPot = expectedPot;
    }

    public void setEverybodyFolded(boolean everybodyFolded) {
        this.everybodyFolded = everybodyFolded;
    }

    public void setSmallBlind(int smallBlind) {
        this.smallBlind = smallBlind;
    }

    public void setFolded(int folded) {
        this.folded = folded;
    }

}
