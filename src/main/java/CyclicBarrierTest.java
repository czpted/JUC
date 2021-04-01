import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @ClassName CyclicBarrierTest
 * @Description CyclicBarrierTest锁
 * @Author CZPTED
 * @Date 2021/4/1 15:59
 * @Version 1.0
 **/
public class CyclicBarrierTest {
    public static void main(String[] args){
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7, () -> {
            System.out.println("集齐七颗龙珠");
        });

        for (int i=1; i<8; i++){
            final Integer tempInt = i;
            new Thread(()->{
                System.out.println("线程"+Thread.currentThread().getName()+"集齐第"+tempInt+"颗龙珠。");
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            },String.valueOf(i)).start();

        }
    }
}
