import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @ClassName ReadWriteLockTest2
 * @Description 读写锁
 * @Author CZPTED
 * @Date 2021/4/1 15:36
 * @Version 1.0
 **/
class MyCache2{
    private volatile Map<String,Object> map = new HashMap<>();
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public void put(String key, Object obj){
        lock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + ",开始写入："+key);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            map.put(key,obj);
            System.out.println(Thread.currentThread().getName() + ",写入完成");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.writeLock().unlock();
        }
    }

    public void get(String key){
        lock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + ",开始读取");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Object o = map.get(key);
            System.out.println(Thread.currentThread().getName() + ",读取完成："+o);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.readLock().unlock();
        }
    }
}

public class ReadWriteLockTest2 {
    public static void main(String[] args){
        MyCache2 myCache = new MyCache2();

        //五个写线程
        for (int i=0; i<5; i++){
            final int tempInt = i;
            new Thread(()->{
                myCache.put(tempInt+"",tempInt);
            },String.valueOf(i)).start();
        }

        //五个读线程
        for (int i=0; i<5; i++){
            final int tempInt = i;
            new Thread(()->{
                myCache.get(tempInt+"");
            },String.valueOf(i)).start();
        }


    }
}
