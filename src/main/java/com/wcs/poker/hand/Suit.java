package com.wcs.poker.hand;

public enum Suit {

    HEARTS("hearts"),
    SPADES("spades"),
    CLUBS("clubs"),
    DIAMONDS("diamonds");

    String value;

    private Suit(String value) {
        this.value = value;
    }
}
