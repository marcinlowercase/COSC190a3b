package org.example.cosc190a3.q1;

import java.util.ArrayList;
import java.util.List;

public class Q1Deck {
    private List<Q1Card> cards = new ArrayList<>();

    public Q1Deck() {
        for (int iSuit = 0; iSuit < 4; iSuit++) {
            for (int iRank = 1; iRank <= 13; iRank++) {
                this.cards.add(new Q1Card(Q1Suit.values()[iSuit], iRank));
            }
        }
    }

    public List<Q1Card> getCards() {
        return cards;
    }

    public Q1Card removeCards() {
        return this.cards.remove((int) (Math.random() * this.cards.size()));
    }


    @Override
    public String toString() {
        return this.cards.toString();
    }

}
