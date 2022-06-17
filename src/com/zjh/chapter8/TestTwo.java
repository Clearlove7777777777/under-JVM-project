package com.zjh.chapter8;

/**
 * TestTwo class
 *
 * @author zjh
 * @date 2022/6/17 15:05
 */
public class TestTwo {
    public static void test(){
        System.out.println("1");
    }
    
    public final void testFinalMethod(){
        System.out.println("123");
    }

    public static void main(String[] args) {
        TestTwo.test();
    }
}

class TestTwoSub extends TestTwo{
    
    public void test1(int a){
        System.out.println(1);
    }

    public void test1(int... a){
        System.out.println(2);
    }

    // 子类无法重写final方法
    // public final void testFinalMethod(){
    //     System.out.println("123");
    // }
    public static void main(String[] args) {
        new TestTwoSub().testFinalMethod();
        new TestTwoSub().test1(1);
    }
}


class FieldHasNoPolymorphic{
    static class Father {
        public int money = 1;
        public Father() {
            money = 2;
            showMeTheMoney();
        }
        public void showMeTheMoney() {
            System.out.println("I am Father, i have $" + money);
        }

        public int getMoney() {
            return money;
        }
    }
    static class Son extends Father {
        public int money = 3;
        public Son() {
            money = 4;
            showMeTheMoney();
        }
        public void showMeTheMoney() {
            
            // 会隐式调用父类的构造方法 
            // 而Father构造函数中对showMeTheMoney()的调用是一次虚方法调用，实际执行的版本是
            // Son::showMeTheMoney()方法，所以输出的是“I am Son”
            System.out.println("I am Son, i have $" + money);
        }

        @Override
        public int getMoney() {
            return money;
        }
    }
    public static void main(String[] args) {
        Father gay = new Son();
        System.out.println("This gay has $" + gay.money);
        System.out.println(gay.getMoney());
    }

}

class A{
    int x;
    public A() {
        test();
    }
    
    public void test(){
        System.out.println(this.x);
    }

    public static void main(String[] args) {
        new A();
    }
}