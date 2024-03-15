package org.example.cosc190a3.q1;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Q1Simulation {
    private static Q1Shoe shoe = new Q1Shoe();
    private static Q1Dealer dealer = new Q1Dealer();
    private static Q1Player player1 = new Q1Player(Q1PlayStyle.SuperTimid, 1);
    private static Q1Player player2 = new Q1Player(Q1PlayStyle.Timid, 2);
    private static Q1Player player3 = new Q1Player(Q1PlayStyle.DealerMirror, 3);
    private static Q1Player player4 = new Q1Player(Q1PlayStyle.Aggressive, 4);

    private static int round = 0;


    //    I found Semaphore online and it works
    private static Semaphore semaphore = new Semaphore(1);


    //    Main  ///////////////////////////////////////////////////////////////////////////////////
    public static void main(String[] args) {


//        Enter round to run
        int roundToRun = 0;
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("How many round do you want to run the simulation?");
            System.out.print("Enter the round: ");
            roundToRun = scanner.nextInt();
        } catch (Exception e) {
            System.out.println("Enter an integer to run the simulation!");
            System.exit(1);
        }


//        Loop
        ExecutorService table = Executors.newFixedThreadPool(5);

        for (int i = 0; i < roundToRun; i++) {
            try {
                semaphore.acquire();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            table.execute(() -> {
                dealerTurn().run();
                semaphore.release();
            });

            table.execute(() -> {
                try {
                    semaphore.acquire();
                    playerTurn(player1).run();
                    playerTurn(player2).run();
                    playerTurn(player3).run();
                    playerTurn(player4).run();
                    semaphore.release();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            });

            try {
                table.awaitTermination(1, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println();
        }


//        Display result
        System.out.println("Player 1 win " + player1.getWinCount() + " time.");
        System.out.println("Player 2 win " + player2.getWinCount() + " time.");
        System.out.println("Player 3 win " + player3.getWinCount() + " time.");
        System.out.println("Player 4 win " + player4.getWinCount() + " time.");
    }


    private static Runnable dealerTurn() {

        return new Runnable() {
            @Override
            public void run() {

//                Display RESHUFFLE
                if (round % 8 == 0 && round != 0) {
                    shoe.reshuffle();
                    System.out.println("Shoe RESHUFFLE!!!!");
                    System.out.println();
                }


//                Display round
                round++;
                System.out.println();
                System.out.println("ROUND: " + round);
                System.out.println();


                dealer.newTurn(shoe);
                dealer.startDraw(shoe);
                display(dealer);

                System.out.println();

            }
        };

    }

    ;

    private static Runnable playerTurn(Q1Player player) {
        return new Runnable() {
            @Override
            public void run() {

                player.newTurn(shoe);
                player.startDraw(shoe);

                display(player);

                player.compare(dealer);

                System.out.println();

            }
        };
    }


    private static void display(Q1GeneralPlayer player) {
        System.out.println(player.getCardsOnHand());
        System.out.println("Score: " + player.getScore());
        System.out.println("Shoe: " + shoe.getCards().size());
        System.out.println(player.printType());
    }

    ;


}
