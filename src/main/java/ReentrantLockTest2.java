import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName ReentrantLockTest2
 * @Description ReentrantLock可重入锁验证
 * @Author CZPTED
 * @Date 2021/4/1 9:31
 * @Version 1.0
 **/
class MyReentrantLockThread1 implements Runnable{

    Lock lock = new ReentrantLock();

    public void getLock(){
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + ": invoked getLock()");
            setLock();
        }finally {
            lock.unlock();
        }
    }

    public void setLock() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + ": invoked setLock()");
        }finally {
            lock.unlock();
        }
    }

    @Override
    public void run() {
        getLock();
    }

}

public class ReentrantLockTest2 {
    public static void main(String[] args){
        MyReentrantLockThread1 myReentrantLockThread1 = new MyReentrantLockThread1();
        Thread thread1 = new Thread(myReentrantLockThread1,"1");
        Thread thread2 = new Thread(myReentrantLockThread1,"2");
        thread1.start();
        thread2.start();
    }
}
