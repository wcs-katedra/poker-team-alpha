/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.wcs.poker.gamestate;

/**
 *
 * @author poker01
 */
public enum Rank {
    A("A"),K("K"),Q("Q"),J("J"),TEN("10"),NINE("9"),EIGHT("8"),SEVEN("7"),
    SIX("6"),FIVE("5"),FOUR("4"),THREE("3"),TWO("2");
    
    String value;
    
    private Rank(String value) {
        this.value=value;
        
    }
    
    public static Rank getRank(String rankString) {
        for (Rank rank : values()) {
            if (rankString.equals(rank.value)) {
                return rank;
            }
        }
        return null;
    }
    
    public String getValue(){
        return value;
    }
}
