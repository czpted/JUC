import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName ProductConsumerTraditonTest
 * @Description 传统的生产消费者代码
 * @Author CZPTED
 * @Date 2021/4/4 2:53
 * @Version 1.0
 **/

class ShareDate{
    private int number = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void increment(){
        lock.lock();
        try {
            while (number != 0){
                condition.await();
            }
            number++;
            System.out.println(Thread.currentThread().getName() +"\t"+ number);
            condition.signalAll();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void decrement(){
        lock.lock();
        try {
            while (number == 0){
                condition.await();
            }
            number--;
            System.out.println(Thread.currentThread().getName() +"\t"+ number);
            condition.signalAll();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}

public class ProductConsumerTraditonTest {
    public static void main(String[] args){
        ShareDate shareDate = new ShareDate();
        new Thread(()->{
            for (int i=0; i<5; i++){
                shareDate.increment();
            }
        },"T1").start();

        new Thread(()->{
            for (int i=0; i<5; i++){
                shareDate.decrement();
            }
        },"T2").start();
    }
}
