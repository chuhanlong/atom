package com.chu.concurrent;


/**
 * Created by Aaron on 14-8-18.
 */
public class Test {

    public static void main(String[] args) {
//        final AtomicInteger atomicInteger = new AtomicInteger(0);
//        for (int i = 0; i < 1000; i++) {
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    int value = atomicInteger.getAndIncrement();
//                    System.out.println("线程[" + Thread.currentThread().getName() + "]获取值为：" + value);
//                }
//            }).start();
//        }
//        System.out.println("最终的结果为：" + atomicInteger.get());
        final Counter counter = new Counter();
        long t1 = System.currentTimeMillis();
        for (int i = 0; i < 2; i++) {
            new Thread(new Runnable() {
//                @Override
                public void run() {
                    for (int j = 0; j < 250000000; j++) {
                        synchronized (counter) {
                            counter.increase();
                        }
                    }
                }
            }).start();

        }
        System.out.println(counter.get() + " 耗时：" + (System.currentTimeMillis() - t1) + "毫秒");
    }

}

class Counter {
    int value = 0;

    public int get() {
        return value;
    }

    public void increase() {
        value++;
    }
}