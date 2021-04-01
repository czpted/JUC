import java.util.concurrent.CountDownLatch;

/**
 * @ClassName CountDownLatchTest
 * @Description CountDownLatch锁
 * @Author CZPTED
 * @Date 2021/4/1 15:53
 * @Version 1.0
 **/
public class CountDownLatchTest {
    public static void main(String[] args) throws Exception {
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i=0; i<6; i++){
            new Thread(()->{
                System.out.println("第"+Thread.currentThread().getName()+"个同学离开教室。");
                countDownLatch.countDown();
            },String.valueOf(i)){}.start();
        }
        countDownLatch.await();
        System.out.println("班长离开教室。");
    }
}
