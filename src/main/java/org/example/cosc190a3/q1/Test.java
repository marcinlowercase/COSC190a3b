package org.example.cosc190a3.q1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Test {

    static Semaphore semaphore = new Semaphore(1);
    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(3);





        for (int i = 0; i < 2; i++){

            try {
                semaphore.acquire();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            pool.execute(() -> {
                dealer.run();
                semaphore.release();
            });

            pool.execute(() -> {
                try {
                    semaphore.acquire();
                    player.run();
                    semaphore.release();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });

            pool.execute(() -> {
                try {
                    semaphore.acquire();
                    player.run();
                    semaphore.release();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });


            pool.execute(new Hi());
            pool.execute(new Ba());


            try {
                pool.awaitTermination(1, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            System.out.println("Done a round!");
            System.out.println();


        }

        pool.shutdown();
    }

    private static Lock lock = new ReentrantLock();
    private static Condition two = lock.newCondition();
    private static Condition one = lock.newCondition();

    private static boolean isDealerDone = false;
    private static boolean isPlayerDone = true;

    private static Condition waitForCompare = lock.newCondition();








    static class Hi implements Runnable{

        @Override
        public void run() {
            try {
                semaphore.acquire();
                System.out.println("Hiiiiiiiiiiiiiiiiiiiiii");;
                semaphore.release();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    }
    static class Ba implements Runnable{

        @Override
        public void run() {
            try {
                semaphore.acquire();
                System.out.println("Baaaaaaaaaaaaaaaaaa");;
                semaphore.release();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    }


    static Runnable dealer = () -> {
//        lock.lock();


        System.out.print("Dealer ");
        draw();

//        lock.unlock();
    };

    static Runnable player = () -> {
//        lock.lock();


        System.out.print("Player ");
        draw();

        compare();

//        lock.unlock();
    };




    public static void draw(){
        lock.lock();
        System.out.println("Draw");
        lock.unlock();

    }

    public static void compare(){
        System.out.println("Compare");
    };
}
