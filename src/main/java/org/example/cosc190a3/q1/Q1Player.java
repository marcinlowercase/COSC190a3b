package org.example.cosc190a3.q1;

public class Q1Player extends Q1GeneralPlayer {

    private int winCount;
    private int id;

    public Q1Player(Q1PlayStyle style, int id) {
        super(style);
        this.winCount = 0;
        this.id = id;
    }

    @Override
    public String printType() {
        return "Player " + this.id;
    }

    public int getWinCount() {
        return winCount;
    }

    public void plusOneWinCount() {
        this.winCount++;
    }

    public int getId() {
        return id;
    }

    public void compare(Q1Dealer dealer) {
        if (this.isBlackJack()) {
            System.out.println("BLACKJACK!");
            System.out.println("Player " + this.id + " Win!");
            this.plusOneWinCount();
        } else {
            if (this.isBust()) {
                System.out.println("Player " + this.id + " Lose!");
            } else {
                if (dealer.isBust()) {
                    System.out.println("Player " + this.id + " Win!");
                    this.plusOneWinCount();
                } else {
                    if (this.getScore() > dealer.getScore()) {
                        System.out.println("Player " + this.id + " Win!");
                        this.plusOneWinCount();
                    } else {
                        System.out.println("Player " + this.id + " Lose!");
                    }
                }
            }
        }
    }

}
