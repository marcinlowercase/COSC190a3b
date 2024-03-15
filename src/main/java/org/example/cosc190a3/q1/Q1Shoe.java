package org.example.cosc190a3.q1;


import java.util.ArrayList;
import java.util.List;

public class Q1Shoe {
    private List<Q1Card> cards = new ArrayList<>();


    public Q1Shoe() {
        for (int i = 0; i < 4; i++) {
            this.cards.addAll(new Q1Deck().getCards());
        }
    }

    public void reshuffle() {
        this.cards.clear();
        for (int i = 0; i < 4; i++) {
            this.cards.addAll(new Q1Deck().getCards());
        }
    }

    public List<Q1Card> getCards() {
        return cards;
    }

    public Q1Card removeCards() {
        return this.cards.remove((int) (Math.random() * this.cards.size()));
    }


}
