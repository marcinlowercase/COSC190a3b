package org.example.cosc190a3.q1;

public class Q1Dealer extends Q1GeneralPlayer implements Comparable<Q1GeneralPlayer> {
    public Q1Dealer() {
        super(Q1PlayStyle.DealerMirror);
    }

    @Override
    public int compareTo(Q1GeneralPlayer o) {
        return this.getScore() - o.getScore();
    }

    @Override
    public String printType() {
        return "Dealer";
    }
}
