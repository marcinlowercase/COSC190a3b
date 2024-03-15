package org.example.cosc190a3.q1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public abstract class Q1GeneralPlayer {
    protected List<Q1Card> cardsOnHand = new ArrayList<>();
    protected int safePoint;
    private Lock lock = new ReentrantLock();

    public Q1GeneralPlayer(Q1PlayStyle playStyle) {
        this.safePoint = playStyle.ordinal() + 15;
    }


    public List<Q1Card> getCardsOnHand() {
        return cardsOnHand;
    }

    public int getSafePoint() {
        return safePoint;
    }

    public boolean isBlackJack() {
        return (this.getScore() == 21 && this.haveAce());
    }

    public boolean isBust() {
        return this.getScore() > 21;
    }

    public int getScore() {
        return (this.haveAce() && this.getRawScore() <= 11) ? this.getRawScore() + 10 : this.getRawScore();
    }

    private boolean haveAce() {
        for (Q1Card c : this.cardsOnHand) {
            if (c.getRank() == 1) return true;
        }
        return false;
    }

    private int getRawScore() {
        int temp = 0;
        for (Q1Card c : cardsOnHand) {
            if (c.getRank() > 10) {
                temp += 10;
            } else {
                temp += c.getRank();
            }
        }
        return temp;
    }

    public void drawCard(Q1Shoe shoe) {
        lock.lock();
        this.cardsOnHand.add(shoe.removeCards());
        lock.unlock();
    }

    public void startDraw(Q1Shoe shoe) {
        while (this.getScore() < this.getSafePoint()) {
            this.drawCard(shoe);
        }
    }

    public void newTurn(Q1Shoe shoe) {
        this.cardsOnHand.clear();
        this.drawCard(shoe);
        this.drawCard(shoe);

    }

    public abstract String printType();


}
