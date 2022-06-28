package com.zjh.chapter8;
import static java.lang.invoke.MethodHandles.lookup;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Field;

/**
 * Test2 class
 *
 * @author zjh
 * @date 2022/6/23 17:41
 */
public class Test2 {
}

class Test {
    class GrandFather {
        void thinking() {
            System.out.println("i am grandfather");
        }
    }
    class Father extends GrandFather {
        void thinking() {
            System.out.println("i am father");
        }
    }
    class Son extends Father {
        void thinking() {
            // try {
            //     MethodType mt = MethodType.methodType(void.class);
            //     MethodHandle mh = lookup().findSpecial(GrandFather.class,
            //             "thinking", mt, getClass());
            //     mh.invoke(this);
            // } catch (Throwable e) {
            // }

            try {
                MethodType mt = MethodType.methodType(void.class);
                Field lookupImpl = MethodHandles.Lookup.class.getDeclaredField("IMPL_LOOKUP");
                lookupImpl.setAccessible(true);
                MethodHandle mh = ((MethodHandles.Lookup) lookupImpl.get(null)).findSpecial(GrandFather.class,"thinking", mt, GrandFather.class);
                mh.invoke(this);
            } catch (Throwable e) {
            }

        }
    }
    public static void main(String[] args) {
        (new Test().new Son()).thinking();
    }
}
