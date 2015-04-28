package com.wcs.poker.gamestate;

public enum Suit {

    HEARTS("hearts"),
    SPADES("spades"),
    CLUBS("clubs"),
    DIAMONDS("diamonds");

    private String value;

    private Suit(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
