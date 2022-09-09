package com.zjh.chapter12;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * VolatileTest class
 *
 * @author zjh
 * @date 2022/7/4 11:10
 */
public class VolatileTest {
    public static volatile AtomicInteger race = new AtomicInteger(0);
    
    public static final Integer x = new Integer(10);
    
    public static void increase() {
        race.addAndGet(1);
    }
    private static final int THREADS_COUNT = 20;
    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[THREADS_COUNT];
        for (int i = 0; i < THREADS_COUNT; i++) {
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 10000; i++) {
                        increase();
                    }
                }
            });
            threads[i].start();
        }
        for (int i = 0; i < threads.length; i++) {
            threads[i].join();
        }
        System.out.println(race);
        
    } 

    public void test(){
        // synchronized (){
        //     System.out.println(VolatileTest.this.race);
        // }
        //
    }
}

class A{
    private List<Object> list = new ArrayList<>(10);

    public static void main(String[] args) {
        // int[] ints = new int[15];
        // ints[10] = 10;
        // // System.out.println(ints[10]);
        // for (int i : ints) {
        //     System.out.println(i);
        // }
        
        List<List> lists =  new ArrayList<>(15);

        for (List list : lists) {
            System.out.println(list.size());
        }

        String[] s = "123_456_13".split("_", 2);
        for (String s1 : s) {
            System.out.println(s1);
        }
    }
}
