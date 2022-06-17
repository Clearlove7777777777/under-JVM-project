package com.zjh.chapter3;

/**
 * FalseSharingTest class
 *
 * @author zjh
 * @date 2022/5/23 16:02
 */
public class FalseSharingTest {

    public static void main(String[] args) throws InterruptedException {
        // testPointer(new Pointer());
        testAllocation();
    }

    private static final int _1MB = 1024 * 1024;
    public static void testAllocation() {
        byte[] allocation1, allocation2, allocation3, allocation4;
        // allocation1 = new byte[2 * _1MB];
        // allocation2 = new byte[2 * _1MB];
        // allocation3 = new byte[2 * _1MB];
        allocation4 = new byte[9 * _1MB]; // 出现一次Minor GC
    }


    private static void testPointer(Pointer pointer) throws InterruptedException {
        long start = System.currentTimeMillis();
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 100000000; i++) {
                pointer.x++;
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 100000000; i++) {
                pointer.y++;
            }
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println(System.currentTimeMillis() - start);
        System.out.println(pointer);
    }
}

class Pointer {
    volatile long x;
    long p1, p2, p3, p4, p5, p6, p7;
    volatile long y;
}
