package com.zjh.chapter2;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * RuntimeConstantPoolOOM class
 *
 * @author zhangjiahuang
 * @date 2022/5/18 17:59
 */
public class RuntimeConstantPoolOOM {
    public static void main(String[] args) {
        // 使用Set保持着常量池引用，避免Full GC回收常量池行为
        Set<String> set = new HashSet<String>();
        // 在short范围内足以让6MB的PermSize产生OOM了
        short i = 0;
        while (true) {
            set.add(String.valueOf(i++).intern());
        }

        // Map<String,Integer> map = new HashMap<>();
        //
        // map.put("aa",123);
        // map.put("aa",456);
        //
        // for (String s : map.keySet()) {
        //     System.out.println(map.get(s));
        // }
        //
        // throw new NullPointerException();
        // new StringBuilder().append("a").toString()
    }
}
