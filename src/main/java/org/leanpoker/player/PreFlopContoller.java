package org.leanpoker.player;

import com.wcs.poker.gamestate.Card;
import com.wcs.poker.gamestate.GameState;
import com.wcs.poker.gamestate.Player;
import java.util.List;
import org.leanpoker.player.holecards.MidPairs;

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

    //private static final String[] RANKS = {"A", "K", "Q", "J", "T", "9", "8", "7", "6", "5", "4", "3", "2"};
    //private static final String[] SUITS = {"spades", "clubs", "diamonds", "hearts"};

    public PreFlopContoller() {
    }
    
    PreFlopContoller(GameState gameState) {
        this.gameState = gameState;
    }

    int start() {
        divideUp();
        myPositionCat = whatPositionIhave();
        expectedPot = countExpectedPot();
        Integer bet=0;
//        bet+=new HighPairs().start();
//        bet+=new BigAces().start();
        bet+=new MidPairs(myPositionCat,this).start();
//        bet+=new SmallPairs().start();
//        bet+=new MidAces().start();
//        bet+=new SuitedAces().start();
//        bet+=new FaceCards().start();
//        bet+=new SuitedConnectors().start();
        return 0;
    }

    private void divideUp() {
        currentPlayerLoc=gameState.getInAction();
        currentPlayer = gameState.getPlayers().get(currentPlayerLoc);
        currentHoleCards = currentPlayer.getHoleCards();
        bigBlind = gameState.getSmallBlind() * 2;
        call = gameState.getCurrentBuyIn() - currentPlayer.getBet();
        minimum_raise = call + gameState.getMinimumRaise();
        pot = gameState.getPot();
        currentDealerPosition = gameState.getDealer();
        players=gameState.getPlayers();
        playersNumber=players.size();
    }

    public String whatPositionIhave() {
        double relativPos = (double)currentPlayerLoc / playersNumber*100;

        if (amIblind()){return "Blinks";}
        if (relativPos < middlePosition && relativPos!=0) {return "Early";}
        if (relativPos > middlePosition 
                && relativPos < latePosition) {return "Middle";}
        return "Late";
    }

    public boolean amIblind() {
        Integer smallBlindLoc=(currentDealerPosition+1)%playersNumber;
        Integer bigBlindLoc=(currentDealerPosition+2)%playersNumber;
        
        if(currentPlayerLoc==smallBlindLoc
                ||currentPlayerLoc==bigBlindLoc){
            return true;
        }
        return false;
    }

    public boolean isPair(Card card1, Card card2){
        if(card1.equals(card2))return true;
        return false;
        }
    
     private Integer countExpectedPot() {
         Integer expectedPot=gameState.getSmallBlind()*3;
         Integer folded=0;
         for (int i = (currentDealerPosition+3)%playersNumber; i < currentPlayerLoc; i++) {
             if (players.get(i).getStatus().equals("active")) {
                 expectedPot+=bigBlind;
             } else if (players.get(i).getStatus().equals("folded")){
                 folded++;
             }
         }
         if (expectedPot==(gameState.getSmallBlind()*3)){everybodyFolded=true;}
         return expectedPot;
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

   
    
    
}
