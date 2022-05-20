package com.zjh.chapter2;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * DirectMemoryOOM class
 *
 * @author zjh
 * @date 2022/5/19 13:21
 */
public class DirectMemoryOOM {
    private static final int _1MB = 1024 * 1024;
    public static void main(String[] args) throws Exception {
        Field unsafeField = Unsafe.class.getDeclaredFields()[0];
        unsafeField.setAccessible(true);
        Unsafe unsafe = (Unsafe) unsafeField.get(null);
        System.gc();
        while (true) {
            unsafe.allocateMemory(_1MB);
        }
        
        
    }
}
