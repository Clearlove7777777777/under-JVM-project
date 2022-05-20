package com.zjh.chapter2;

import java.util.ArrayList;
import java.util.List;

/**
 * HeepOOM class
 *
 * @author zjh
 * @date 2022/5/18 16:20
 */
public class HeepOOM {
    static class OOMObject{}

    public static void main(String[] args) {
        List<OOMObject> list = new ArrayList<>();

        while (true) {
            list.add(new OOMObject());
        }
    }
}
