package com.zjh.chapter4;

/**
 * TestClass class
 *
 * @author zjh
 * @date 2022/6/1 16:53
 */
public class TestClass {
    private int m;
    public int inc() {
        try {
            int x = 10/0;
        }catch (ArithmeticException e){
            e.printStackTrace();
        }
        return m + 1;
    }

    public void setM(int mValue) {
        this.m = mValue;
    }

    public static void main(String[] args) {
        new TestClass().inc();
    }
}

