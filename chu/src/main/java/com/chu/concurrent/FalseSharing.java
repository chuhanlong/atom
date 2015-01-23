package com.chu.concurrent;


/**
 * 伪共享与缓存行填充
 * Created by Aaron on 14-8-26.
 */
public final class FalseSharing implements Runnable {

    public final static int NUM_THREADS = 2; // change
    public final static long ITERATIONS = 500L * 1000L * 1000L;
    private final int arrayIndex;

    private static VolatileLong[] longs = new VolatileLong[NUM_THREADS];

    static {
        for (int i = 0; i < longs.length; i++) {
            longs[i] = new VolatileLong();
        }
    }

    public FalseSharing(final int arrayIndex) {
        this.arrayIndex = arrayIndex;
    }

    public static void main(final String[] args) throws Exception {
        final long start = System.nanoTime();
        runTest();
        System.out.println("duration = " + (System.nanoTime() - start));
    }

    private static void runTest() throws InterruptedException {
        Thread[] threads = new Thread[NUM_THREADS];

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new FalseSharing(i));
        }

        for (Thread t : threads) {
            t.start();
        }

        for (Thread t : threads) {
            t.join();
        }
    }

    public void run() {
        long i = ITERATIONS + 1;
        while (0 != --i) {
            longs[arrayIndex].value = i;
        }
    }

}

class LPad {
    public long p1, p2, p3, p4, p5, p6, p7;
}

//为了演示缓存行填充的效果需将extends LPad的注释解除
class VolatileLong /*extends LPad*/ {
    public volatile long value = 0L;
}

class RPad extends VolatileLong {
    public long p8, p9, p10, p11, p12, p13, p14;
}
