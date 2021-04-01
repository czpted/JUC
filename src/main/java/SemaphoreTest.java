import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName SemaphoreTest
 * @Description semaphore锁
 * @Author CZPTED
 * @Date 2021/4/1 16:07
 * @Version 1.0
 **/
public class SemaphoreTest {
    public static void main(String[] args){
        Semaphore semaphore = new Semaphore(3);

        for (int i=0; i<6; i++){
            new Thread(()->{
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName()+"抢到车位");
                    TimeUnit.SECONDS.sleep(3);
                    System.out.println(Thread.currentThread().getName()+"离开车位");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    semaphore.release();
                }
            },String.valueOf(i)).start();
        }
    }
}
