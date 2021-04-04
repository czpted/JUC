import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName SyncAndReentrantLockTest
 * @Description Synchronized 和 Lock的区别
 * @Author CZPTED
 * @Date 2021/4/4 14:16
 * @Version 1.0
 **/

class ShareResource{
    private  int number = 1;
    private Lock lock = new ReentrantLock();
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();

    public void print5(){
        lock.lock();
        try {
            while (number != 1){
                condition1.await();
            }
            for(int i = 0; i<5; i++){
                System.out.println(Thread.currentThread().getName() +"\t"+number+"\t"+i);
            }
            number = 2;
            condition1.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void print10(){
        lock.lock();
        try {
            while (number != 2){
                condition2.await();
            }
            for (int i=0; i<10; i++){
                System.out.println(Thread.currentThread().getName()+"\t"+number+"\t"+i);
            }
            number = 3;
            condition2.signal();
        }catch (Exception e ){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void print15(){
        lock.lock();
        try {
            while (number != 3){
                condition3.await();
            }
            for (int i=0; i<15; i++){
                System.out.println(Thread.currentThread().getName()+"\t"+number+"\t"+i);
            }
            number = 1;
            condition3.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}


public class SyncAndReentrantLockTest {
    public static void main(String[] args){
        ShareResource shareResource = new ShareResource();
        new Thread(()->{
            for(int i =0; i<10; i++){
                shareResource.print5();
            }
        },"T1").start();

        new Thread(()->{
            for(int i =0; i<10; i++){
                shareResource.print10();
            }
        },"T2").start();

        new Thread(()->{
            for(int i =0; i<10; i++){
                shareResource.print15();
            }
        },"T3").start();
    }
}
