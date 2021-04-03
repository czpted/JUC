import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName ProductConsumerBlockingQueueTest
 * @Description 阻塞队列版本的生产消费者代码
 * @Author CZPTED
 * @Date 2021/4/4 3:04
 * @Version 1.0
 **/
class MyResource{
    private volatile boolean flag = true;
    private AtomicInteger atomicInteger = new AtomicInteger();
    BlockingQueue<String> blockingQueue = null;
    public MyResource(BlockingQueue<String> blockingQueue){
        this.blockingQueue = blockingQueue;
        System.out.println(blockingQueue.getClass().getName());
    }

    public void product() throws Exception {
        String data = null;
        boolean retValue;
        while (flag){
            data = atomicInteger.incrementAndGet()+"";
            retValue = blockingQueue.offer(data,2L,TimeUnit.SECONDS);
            if(retValue){
                System.out.println(Thread.currentThread().getName()+"\t 插入队列"+ data + "成功");
            }else {
                System.out.println(Thread.currentThread().getName()+"\t 插入队列"+ data + "失败");
            }
            try {
                TimeUnit.SECONDS.sleep(1);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName()+"\t 停止生产，表示FLAG=FALSE,生产结束。");
    }

    public void consumer() throws InterruptedException {
        String retValue;
        while (flag){
            retValue = blockingQueue.poll(2L,TimeUnit.SECONDS);
            if(retValue !=null && retValue != ""){
                System.out.println(Thread.currentThread().getName() +"\t 消费队列："+retValue+"成功。");
            }else {
                flag = false;
                System.out.println(Thread.currentThread().getName()+"\t 消费失败，队列已为空，退出");
                return;
            }
        }
    }

    public void stop(){
        this.flag = false;
    }

}

public class ProductConsumerBlockingQueueTest {
    public static void main(String[] args){
        MyResource myResource = new MyResource(new ArrayBlockingQueue<String>(10));

        new Thread(()->{
            System.out.println(Thread.currentThread().getName() +"\t 生产线程启动。");
            try {
                myResource.product();
                System.out.println("");
                System.out.println("");
            }catch (Exception e){
                e.printStackTrace();
            }
        },"product").start();

        new Thread(()->{
            System.out.println(Thread.currentThread().getName() +"\t 消费线程启动。");
            System.out.println("");
            System.out.println("");

            try {
                myResource.consumer();
            }catch (Exception e){
                e.printStackTrace();
            }
        },"consumer").start();

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("");
        System.out.println("");
        System.out.println("5秒过后，生产和消费线程停止，线程结束");
        myResource.stop();
    }
}
