package com.zjh.chapter8;

/**
 * Test8One class
 *
 * @author zjh
 * @date 2022/6/17 13:06
 */
public class Test8One {
    public static void main(String[] args) {
        {
            byte[] placeholder = new byte[64 * 1024 * 1024];
        }
        int a;
        System.gc();
    }
    
    public static void test(){
        System.out.println("1");
    }
}

class Sub extends Test8One{
    
    public static void test(){
        System.out.println("2");
    }
}

