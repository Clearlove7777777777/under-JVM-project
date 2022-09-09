package com.zjh.chapter12;

/**
 * ThreadLocalTest class
 *
 * @author zjh
 * @date 2022/7/13 13:17
 */
public class ThreadLocalTest {
    public static void main(String[] args) {
        Thread[] threads = new Thread[2];
        
        threads[0] = new Thread();
        Runnable runnable1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("1");
            }
        };
        ThreadLocal<String> threadLocal = new ThreadLocal<>();
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                threadLocal.set("111");
                System.out.println(threadLocal.get());
            }
        });
        Thread thread2 = new Thread(() -> {
            threadLocal.set("222");
            System.out.println(threadLocal.get());
        });
        
        thread1.start();
        thread2.start();
    }
}
