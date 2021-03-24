import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName VolatileTest2
 * @Description Volatile特性 —— 不保证原子性
 * @Author CZPTED
 * @Date 2021/3/24 16:00
 * @Version 1.0
 **/

class MyVolatileTestThread2{
    volatile int i = 0;
    volatile int syncInt = 0;
    //解决方案，用AtomicInteger代替int
    volatile AtomicInteger ai = new AtomicInteger(0);

    public void plus(){
        i++;
    }

    //解决方案，用同步方法
    public synchronized void syncPlus(){
        syncInt++;
    }

    public void AtomicPlus(){
        ai.getAndIncrement();
    }
}

public class VolatileTest2 {
    public static void main(String[] args){
        MyVolatileTestThread2 myVolatileTestThread2 = new MyVolatileTestThread2();

        for (int i = 0; i<20; i++){
            new Thread(()->{
                for (int j = 0; j<1000; j++){
                    myVolatileTestThread2.plus();
                    myVolatileTestThread2.AtomicPlus();
                    myVolatileTestThread2.syncPlus();
                }
            },String.valueOf(i)){}.start();
        }

        System.out.println("前 "+Thread.activeCount());
        while (Thread.activeCount() > 2){
            Thread.yield();
        }
        System.out.println("后 "+Thread.activeCount());

//        try {
//            Thread.sleep(100);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        System.out.println(myVolatileTestThread2.i);
        System.out.println(myVolatileTestThread2.ai);
        System.out.println(myVolatileTestThread2.syncInt);

    }
}
