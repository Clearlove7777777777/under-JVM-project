package com.zjh.chapter7;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * SubClass class
 *
 * @author zjh
 * @date 2022/6/9 10:24
 */
class SuperClass {
    static {
        System.out.println("SuperClass init!");
    }
    public static int value = 123;
}
public class SubClass extends SuperClass {
    static {
        System.out.println("SubClass init!");
    }
    static int i = 1;
    static {

    }
    
    protected int x = 10;
    
    protected void test(){
        System.out.println("123");
    }
    
}

class NotInitialization {
    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeyException {


    }
}

interface SuperI1{
    int value1 = 10;
}

interface SubI1 extends SuperI1{
    int value = 10;
}


class ClassLoaderTest {
    public static void main(String[] args) throws Exception {
        ClassLoader myLoader = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                try {
                    String fileName = name.substring(name.lastIndexOf(".") + 1)+".class";
                    InputStream is = getClass().getResourceAsStream(fileName);
                    if (is == null) {
                        return super.loadClass(name);
                    }
                    byte[] b = new byte[is.available()];
                    is.read(b);
                    return defineClass(name, b, 0, b.length);
                } catch (IOException e) {
                    throw new ClassNotFoundException(name);
                }
            }
        };
        Object obj = myLoader.loadClass("com.zjh.chapter7.ClassLoaderTest").newInstance();
        System.out.println(obj.getClass());
        System.out.println(obj instanceof ClassLoaderTest);
    }
}

class TestMap{
    
    protected void test(){
        System.out.println("123");
    }
    public static void main(String[] args) {
        Map<String, String> map = new ConcurrentHashMap<>();
        map.remove("123");
        map.put("123","456");
        map.put("123","123");
        System.out.println(map.get("123"));
        System.out.println(map.get("13"));
    }
}

class TestMapSon extends TestMap{
    @Override
    protected void test() {
        super.test();
    }
}



