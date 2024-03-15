package org.example.cosc190a3.q1;

public class Q1Card {
    private Q1Suit suit;
    private int rank;

    public Q1Card(Q1Suit suit, int rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public int getRank() {
        return rank;
    }

    @Override
    public String toString() {
        return suit + "" + rank;
    }
}
