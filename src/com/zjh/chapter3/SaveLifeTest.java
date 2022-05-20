package com.zjh.chapter3;

/**
 * SaveLifeTest class
 *
 * @author zjh
 * @date 2022/5/20 11:35
 */
public class SaveLifeTest {
    public static SaveLifeTest instance = null;

    /**
     * 如果这个对象被判定为确有必要执行finalize()方法，那么该对象将会被放置在一个名为F-Queue的
     * 队列之中，并在稍后由一条由虚拟机自动建立的、低调度优先级的Finalizer线程去执行它们的finalize()
     * 方法。这里所说的“执行”是指虚拟机会触发这个方法开始运行，但并不承诺一定会等待它运行结束。
     * 这样做的原因是，如果某个对象的finalize()方法执行缓慢，或者更极端地发生了死循环，将很可能导
     * 致F-Queue队列中的其他对象永久处于等待，甚至导致整个内存回收子系统的崩溃。finalize()方法是对
     * 象逃脱死亡命运的最后一次机会，稍后收集器将对F-Queue中的对象进行第二次小规模的标记，如果对
     * 象要在finalize()中成功拯救自己——只要重新与引用链上的任何一个对象建立关联即可
     * @throws Throwable
     */
    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("123");
        SaveLifeTest.instance = this;
    }
    
    public static void main(String[] args) throws InterruptedException {
        SaveLifeTest lifeTest = new SaveLifeTest();
        lifeTest = null;
        System.gc();    // 手动调用gc
        Thread.sleep(500);
        if (lifeTest != null){
            System.out.println("im alive!!!");
        }else {
            System.out.println("im dead!!! :(");
        }

        System.gc();
        Thread.sleep(500);
        if (lifeTest != null){
            System.out.println("im alive!!!");
        }else {
            System.out.println("im dead!!! :(");
        }
    }
}
