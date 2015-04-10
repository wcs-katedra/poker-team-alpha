
package com.wcs.poker.gamestate;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class GameState {

    @SerializedName("small_blind")
    @Expose
    private int smallBlind;
    @SerializedName("current_buy_in")
    @Expose
    private int currentBuyIn;
    @Expose
    private int pot;
    @SerializedName("minimum_raise")
    @Expose
    private int minimumRaise;
    @Expose
    private int dealer;
    @Expose
    private int orbits;
    @SerializedName("in_action")
    @Expose
    private int inAction;
    @Expose
    private List<Player> players = new ArrayList<Player>();
    @SerializedName("community_cards")
    @Expose
    private List<Card> communityCards = new ArrayList<Card>();

    /**
     * 
     * @return
     *     The smallBlind
     */
    public int getSmallBlind() {
        return smallBlind;
    }

    /**
     * 
     * @param smallBlind
     *     The small_blind
     */
    public void setSmallBlind(int smallBlind) {
        this.smallBlind = smallBlind;
    }

    /**
     * 
     * @return
     *     The currentBuyIn
     */
    public int getCurrentBuyIn() {
        return currentBuyIn;
    }

    /**
     * 
     * @param currentBuyIn
     *     The current_buy_in
     */
    public void setCurrentBuyIn(int currentBuyIn) {
        this.currentBuyIn = currentBuyIn;
    }

    /**
     * 
     * @return
     *     The pot
     */
    public int getPot() {
        return pot;
    }

    /**
     * 
     * @param pot
     *     The pot
     */
    public void setPot(int pot) {
        this.pot = pot;
    }

    /**
     * 
     * @return
     *     The minimumRaise
     */
    public int getMinimumRaise() {
        return minimumRaise;
    }

    /**
     * 
     * @param minimumRaise
     *     The minimum_raise
     */
    public void setMinimumRaise(int minimumRaise) {
        this.minimumRaise = minimumRaise;
    }

    /**
     * 
     * @return
     *     The dealer
     */
    public int getDealer() {
        return dealer;
    }

    /**
     * 
     * @param dealer
     *     The dealer
     */
    public void setDealer(int dealer) {
        this.dealer = dealer;
    }

    /**
     * 
     * @return
     *     The orbits
     */
    public int getOrbits() {
        return orbits;
    }

    /**
     * 
     * @param orbits
     *     The orbits
     */
    public void setOrbits(int orbits) {
        this.orbits = orbits;
    }

    /**
     * 
     * @return
     *     The inAction
     */
    public int getInAction() {
        return inAction;
    }

    /**
     * 
     * @param inAction
     *     The in_action
     */
    public void setInAction(int inAction) {
        this.inAction = inAction;
    }

    /**
     * 
     * @return
     *     The players
     */
    public List<Player> getPlayers() {
        return players;
    }

    /**
     * 
     * @param players
     *     The players
     */
    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    /**
     * 
     * @return
     *     The communityCards
     */
    public List<Card> getCommunityCards() {
        return communityCards;
    }

    /**
     * 
     * @param communityCards
     *     The community_cards
     */
    public void setCommunityCards(List<Card> communityCards) {
        this.communityCards = communityCards;
    }

}
