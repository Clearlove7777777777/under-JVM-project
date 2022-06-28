package com.zjh.chapter8;

/**
 * FieldHasNoPolymorphic class
 *
 * @author zjh
 * @date 2022/6/23 10:18
 */
public class Dispatch {
    static class QQ {}
    static class _360 {}
    public static class Father {
        public void hardChoice(QQ arg) {
            System.out.println("father choose qq");
        }
        public void hardChoice(_360 arg) {
            System.out.println("father choose 360");
        }
    }
    public static class Son extends Father {
        @Override
        public final void hardChoice(QQ arg) {
            System.out.println("son choose qq");
        }
        public void hardChoice(_360 arg) {
            System.out.println("son choose 360");
        }
    }

    public static class SonSon extends Son {
        // @Override
        // public void hardChoice(QQ arg) {
        //     System.out.println("son choose qq");
        // }
        public void hardChoice(_360 arg) {
            System.out.println("son choose 360");
        }
    }
    
    
    public static void main(String[] args) {
        Father father = new Father();
        Father son = new Son();
        father.hardChoice(new _360());
        son.hardChoice(new QQ());
        
    }
}