package com.zjh;

/**
 * Test2 class
 *
 * @author zjh
 * @date 2022/6/28 13:48
 */
public class AutoPackageTest {
    public static void main(String[] args) {
        Integer x1 = new Integer(1);   // new出来的包装类是直接在堆中的新对象，与常量池无关
        Integer x2 = new Integer(1);
        Integer x3 = new Integer(2);

        Integer a = 1;  // 如果是这种形式的声明，且数值在-128~127之间，则是直接返回常量池中的引用对象
        Integer b = 1;  // a、b是同一个对象

        Integer c = 3000;   // 数值不在-128~127之间，先查看常量池中是否有该数值的对象，有则返回，没有则创建后放入常量池后返回
        Integer d = 3000;   // 因此c、d是同一个对象
        System.out.println(c == d);

        System.out.println(a == b); // 当==两侧都是包装类型时比较的是两变量是否指向同一个地址
        System.out.println(x1+ x2 == x3);   // 当==两侧任意一侧是数学表达式，那么两边的包装类型自动拆箱，结果为值大小是否相等
        System.out.println(a.hashCode());
        System.out.println(x1 == x2);
        System.out.println(x1.equals(x2));
        System.out.println(x1);
        System.out.println(x2);
    }
}
