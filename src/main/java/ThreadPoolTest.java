import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @ClassName ThreadPoolTest
 * @Description 线程池
 * @Author CZPTED
 * @Date 2021/3/24 1:46
 * @Version 1.0
 **/

class MyThreadPoolThread1 implements Runnable{
    public void run() {
        System.out.println("1");
    }
}
class MyThreadPoolThread2 implements Runnable{
    public void run() {
        System.out.println("2");
    }
}


public class ThreadPoolTest {
    public static void main(String[] args){
        MyThreadPoolThread1 myThreadPoolThread1 = new MyThreadPoolThread1();
        MyThreadPoolThread2 myThreadPoolThread2 = new MyThreadPoolThread2();
        ExecutorService service = Executors.newFixedThreadPool(10);
        ThreadPoolExecutor executor = (ThreadPoolExecutor)service;
        executor.execute(myThreadPoolThread1);
        executor.execute(myThreadPoolThread2);
        executor.shutdown();
    }
}
