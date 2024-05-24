
package sbu.cs.CalculatePi;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class PiCalculator {

    /**
     * Calculate pi and represent it as a BigDecimal object with the given floating point number (digits after . )
     * There are several algorithms designed for calculating pi, it's up to you to decide which one to implement.
     Experiment with different algorithms to find accurate results.

     * You must design a multithreaded program to calculate pi. Creating a thread pool is recommended.
     * Create as many classes and threads as you need.
     * Your code must pass all of the test cases provided in the test folder.

     //* @param floatingPoint the exact number of digits after the floating point
     * @return pi in string format (the string representation of the BigDecimal object)
     */

    public static class calculateTask implements Runnable {
        MathContext mc = new MathContext(1000);
        BigDecimal value;
        private int k;
        public calculateTask(int k){
            this.k = k;
        }
        @Override
        public void run() {
            BigDecimal c1 = new BigDecimal(1).divide(new BigDecimal(16).pow(k),mc);
            BigDecimal c2 = new BigDecimal(4).divide(new BigDecimal(8*k+1),mc);
            BigDecimal c3 = new BigDecimal(-2).divide(new BigDecimal(8*k+4),mc);
            BigDecimal c4 = new BigDecimal(-1).divide(new BigDecimal(8*k+5),mc);
            BigDecimal c5 = new BigDecimal(-1).divide(new BigDecimal(8*k+6),mc);
            value = c1.multiply((c2.add(c3).add(c4).add(c5)),mc);
            addToSum(value);
        }
    }
    public static BigDecimal pi;

    public static synchronized void addToSum(BigDecimal value) {
        pi = pi.add(value);
    }

    public String calculate (int floatingPoint) {
        // TODO
        pi = new BigDecimal(0);
        ExecutorService threadPool = Executors.newFixedThreadPool(4);  //a power of 2

        for (int i = 0; i <= 10000; i++){     // increasing the number of iterations improves accuracy
            calculateTask task = new calculateTask(i);
            threadPool.execute(task);
        }
        threadPool.shutdown();
//        try {
//            threadPool.awaitTermination(10000, TimeUnit.MILLISECONDS);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        //------------------------------------------------------------------
        long timeoutMillis = 10000;
        long startTime = System.currentTimeMillis();

        while (!threadPool.isTerminated()) {
            if (System.currentTimeMillis() - startTime > timeoutMillis) {
                System.out.println("Timeout: not all tasks completed in the given time.");
                break;
            }

            try {
                Thread.sleep(100); // Sleep for a short while before checking again
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Interrupted while waiting for termination.");
                break;
            }
        }
        //---------------------------------------------------------------------
        //What I was supposed to do is written in the ncommented section.The TimeUnit.MILLISECONDS was getting and error cause the related package couldn't be imported ,so I decided to copy the marked section that does the same thing as the commented one from GPT.
        pi = pi.setScale(floatingPoint,BigDecimal.ROUND_DOWN);
        return pi.toPlainString();
    }

    public static void main(String[] args) {
        // Use the main function to test the code yourself
        PiCalculator pi = new PiCalculator();
        System.out.println(pi.calculate(1000));
    }
}
