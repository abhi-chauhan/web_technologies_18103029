import java.util.*;

public class A2 {
    private final static int MAX = 100000;
    private volatile static int intWithMaxDivisorcount;
    private volatile static int maxDivisorcount = 0;
    synchronized private static void report(int maxcountFromThread,
                                            int intWithMaxFromThread) {
        if (maxcountFromThread > maxDivisorcount) {
            maxDivisorcount = maxcountFromThread;
            intWithMaxDivisorcount = intWithMaxFromThread;
        }
    }
    private static class countDivisorsThread extends Thread {
        int min, max;
        public countDivisorsThread(int min, int max) {
            this.min = min;
            this.max = max;
        }
        public void run() {
            int maxDivisors = 0;
            int whichInt = 0;
            for (int i = min; i < max; i++) {
                int divisors = countDivisors(i);
                if (divisors > maxDivisors) {
                    maxDivisors = divisors;
                    whichInt = i;
                }
            }
            report(maxDivisors,whichInt);
        }
    }

    private static void countDivisorsWithThreads(int noOfThreads) {
        System.out.println("\ncounting divisors using " +
                noOfThreads + " threads");
        long startTime = System.currentTimeMillis();
        countDivisorsThread[] worker = new countDivisorsThread[noOfThreads];
        int integersPerThread = MAX/noOfThreads;
        int start = 1;
        int end = start + integersPerThread - 1;
        for (int i = 0; i < noOfThreads; i++) {
            if (i == noOfThreads - 1) {
                end = MAX;
            }
            worker[i] = new countDivisorsThread( start, end );
            start = end+1;
            end = start + integersPerThread - 1;
        }
        maxDivisorcount = 0;
        for (int i = 0; i < noOfThreads; i++)
            worker[i].start();
        for (int i = 0; i < noOfThreads; i++) {
            while (worker[i].isAlive()) {
                try {
                    worker[i].join();
                }
                catch (InterruptedException e) {
                }
            }
        }
        long elapsedTime = System.currentTimeMillis() - startTime;
        System.out.println("\nThe largest number of divisors " +
                "between 1 and " + MAX + " is " + maxDivisorcount);
        System.out.println("An integer with that many divisors is " +
                intWithMaxDivisorcount);
        System.out.println("Total elapsed time:  " +
                (elapsedTime/1000.0) + " seconds.\n");
    }


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int noOfThreads = 0;
        while (noOfThreads < 1 || noOfThreads > 10) {
            System.out.print("How many threads do you want to use from 1 to 10?  ");
            noOfThreads = in.nextInt();
            if (noOfThreads < 1 || noOfThreads > 10)
                System.out.println("Please enter a number from 1 to 10 !");
        }
        countDivisorsWithThreads(noOfThreads);
    }
    public static int countDivisors(int n) {
        int cnt = 0;
        for (int i = 1; i <= n ; i++) {
            if ( n % i == 0 )
                cnt ++;
        }
        return cnt;
    }

}