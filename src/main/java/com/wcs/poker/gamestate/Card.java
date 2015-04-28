
package com.wcs.poker.gamestate;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import java.util.Objects;

@Generated("org.jsonschema2pojo")
public class Card implements Comparable<Card>{

    @Expose
    private String rank;
    @Expose
    private String suit;

    /**
     * 
     * @param rank
     * @param suit 
     */
    public Card(String rank, String suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public boolean isPair(Card card2) {
        return equals(card2);
    }

    public boolean isTheSameSuit(Card card2) {
        return suit.equals(card2.getSuit());
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.rank);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Card other = (Card) obj;
        if (!Objects.equals(this.rank, other.rank)) {
            return false;
        }
        return true;
    }
    
    @Override
    public int compareTo(Card o) {
        return getRankEnum().compareTo(o.getRankEnum());
    }
    
    public Rank getRankEnum() {
        return Rank.getRank(rank);
    }
    
    /**
     * 
     * @return
     *     The rank
     */
    public String getRank() {
        return rank;
    }

    /**
     * 
     * @param rank
     *     The rank
     */
    public void setRank(String rank) {
        this.rank = rank;
    }

    /**
     * 
     * @return
     *     The suit
     */
    public String getSuit() {
        return suit;
    }

    /**
     * 
     * @param suit
     *     The suit
     */
    public void setSuit(String suit) {
        this.suit = suit;
    }

    

    
}
