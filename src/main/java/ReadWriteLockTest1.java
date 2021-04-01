import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName ReadWriteLockTest1
 * @Description 没有读写锁的情况
 * @Author CZPTED
 * @Date 2021/4/1 15:13
 * @Version 1.0
 **/

class MyCache1{
    private volatile Map<String,Object> map = new HashMap<>();

    public void put(String key, Object obj){
        System.out.println(Thread.currentThread().getName() + ",开始写入："+key);
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        map.put(key,obj);
        System.out.println(Thread.currentThread().getName() + ",写入完成");
    }

    public void get(String key){
        System.out.println(Thread.currentThread().getName() + ",开始读取");
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Object o = map.get(key);
        System.out.println(Thread.currentThread().getName() + ",读取完成："+o);
    }
}


public class ReadWriteLockTest1 {
    public static void main(String[] args){
        MyCache1 myCache = new MyCache1();

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
