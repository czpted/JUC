import java.util.concurrent.atomic.AtomicReference;

/**
 * @ClassName AtomicReferenceTest2
 * @Description CAS的ABA问题
 * @Author CZPTED
 * @Date 2021/3/26 1:05
 * @Version 1.0
 **/
public class AtomicReferenceTest2 {

    public static void main(String[] args){

        AtomicReference<Integer> integerAtomicReference = new AtomicReference<>(1);

        new Thread(()->{
            boolean b1 = integerAtomicReference.compareAndSet(1, 2);
            boolean b2 = integerAtomicReference.compareAndSet(2, 1);
            System.out.println(Thread.currentThread().getName() +"的交换结果: "+b2);
        },"A"){}.start();

        new Thread(()->{
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            };
            boolean b = integerAtomicReference.compareAndSet(1, 3);
            System.out.println(Thread.currentThread().getName() +"的交换结果: "+b+",交换后的结果为："+integerAtomicReference.get());
        },"B"){}.start();
    }

}
