package com.zjh.chapter11;

/**
 * TestRunTimeCompile class
 *
 * @author zjh
 * @date 2022/6/29 14:23
 */
public class TestRunTimeCompile {
    public static final int NUM = 15000;
    public static int doubleValue(int i) {
    // 这个空循环用于后面演示JIT代码优化过程
        for(int j=0; j<100000; j++);
        return i * 2;
    }
    public static long calcSum() {
        long sum = 0;
        for (int i = 1; i <= 100; i++) {
            sum += doubleValue(i);
        }
        return sum;
    }
    public static void main(String[] args) {
        for (int i = 0; i < NUM; i++) {
            calcSum();
        }
    }
}

class Parent{
    public void test(){
        System.out.println("im parent");
    }
}

class Son extends Parent{
    public void sonMethod(){
        System.out.println("son`s method");
    }
    
    @Override
    public void test(){
        System.out.println("im son");
    }

    public static void main(String[] args) {
        Parent obj = new Son();
        // 在编译时，编译器只会将方法调用精确到符号引用，此处的符号引用类似于Parent::test()
        // 精确到符号引用的过程使用了静态分派，根据调用者的声明类型以及方法名称、方法签名来确定
        // 这个过程被称为静态分派，因为判断需要用到生命类型以及方法签名两个宗量，因此称java为静态多分派的
        // 而具体执行的是父类的test方法还是子类的test方法是需要等到运行时确定obj的实际类型才可得知的
        // 运行时发现obj实际类型是子类，因此调用子类的test方法，因为判断只需要确定实际类型这一个宗量
        // 因此称java为动态单分派的
        obj.test();
    }
}

interface ParentInterface{
    void test1();
    void test2();
}

interface SonInterface extends ParentInterface{
    @Override
    void test1();
}

class TP implements ParentInterface{
    @Override
    public void test1() {
        System.out.println("tp test1");
    }
    @Override
    public void test2() {
        System.out.println("tp test2");
    }
}

class TS extends TP implements SonInterface{
    @Override
    public void test2() {
        System.out.println("ts test2");
    }
    @Override
    public void test1() {
        System.out.println("ts test1");
    }
}

class Test{
    public static void main(String[] args) {
        ParentInterface obj = new TS();
        obj.test1();
    }
}