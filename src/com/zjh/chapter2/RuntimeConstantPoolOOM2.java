package com.zjh.chapter2;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

/**
 * RuntimeConstantPoolOOM class
 *
 * @author zhangjiahuang
 * @date 2022/5/18 17:59
 */
public class RuntimeConstantPoolOOM2 {
    public static void main(String[] args) {
        // JDK7以上 由于运行时（字符串）常量池已经不存储在方法区中的永久代了，而是存储在堆中了，因此intern方法不必再将首次遇到的字符串复制到永久代，只需记录该字符串出现的位置并返回即可
        // JDK6中 由于常量池是分配在方法区中的永久代的，而new出来的字符串是存储在堆区的，所以intern方法会把首次遇到的字符串实例复制到永久代的字符串常量池
        // 中存储，返回的也是永久代里面这个字符串实例的引用，所以肯定是false
        String str1 = new StringBuilder("计算机").append("软件").toString();
        System.out.println(str1.intern() == str1);
        String str2 = new StringBuilder("ja").append("va").toString();
        System.out.println(str2.intern() == str2);  // "java"这个字符串

        System.out.println("====");
        
        String x = "aghjki";
        String x1 = new String("aghjki");
        // System.out.println(x.hashCode());
        // System.out.println(x1.hashCode());
        x = null;
        System.gc();
        // intern()在JDK7以后返回的是第一次遇到创建该字符的对象的堆内地址，
        System.out.println(x1.intern() == new String("aghjki"));
        
        String ss1 = new String("java");
        String ss2 = new String("java");
        System.out.println(ss1  == ss2);
        /**
         * jdk7以后，只要遇到"xxx",在类加载阶段，如果JVM是第一次遇到"xxx"，那么它就会把"xxx"放入堆中的字符串常量池中，后续如果JVM遇到了s1 = new String("xxx")给一个变量，那么该变量与字符串常量池中的"xxx"没有关系
         * s1 = new String("xxx") 和 s2 = new String("xxx")没有任何关系，地址当然也是不一样的
         * 但是如果JVM遇到的是将“xxx”直接赋值给一个变量(s2 = "xxx")或者(s2 = s1.intern())，那么s2的地址就是字符串常量池中"xxx"的地址
         */
        String s1 = "123";
        String s2 = "123";
        String s3 = new String("8989");
        String s4 = new String("8989");
        String intern = "8989".intern();
        System.out.println(s1.hashCode());  // String的hashcode方法是被重写过的 无法表示地址 
        System.out.println(s2.hashCode());
        System.out.println(s3.hashCode());
        System.out.println(s4.hashCode());
        System.out.println(s1 == s2);
        System.out.println(s3 == s4);
        System.out.println(s4.intern() == s4);
        System.out.println(s4.intern() == "8989");

        String s7 = new String("1.8.0_281");
        String s8 = new String("1.8.0_281");
        System.out.println(s7 == s8);
        
        System.out.println(s7.intern() == s7);

        try {
            // Class unsafeClass = Unsafe.class;
            Field unsafeField = Unsafe.class.getDeclaredFields()[0];
            unsafeField.setAccessible(true);
            Unsafe unsafe = (Unsafe) unsafeField.get(null);
            while (true){
                unsafe.allocateMemory(1024*1024);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}

