import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @ClassName AtomicStampReferenceTest1
 * @Description CAS的ABA问题的解决
 * @Author CZPTED
 * @Date 2021/3/26 1:18
 * @Version 1.0
 **/
public class AtomicStampReferenceTest1 {
    public static void main(String[] args){
        AtomicStampedReference<Integer> integerAtomicStampedReference = new AtomicStampedReference<>(1, 1);

        new Thread(()->{
            int stamp = integerAtomicStampedReference.getStamp();
            System.out.println("线程A获取的第一次版本号：" + stamp);

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            };

            boolean b1 = integerAtomicStampedReference.compareAndSet(1, 2, integerAtomicStampedReference.getStamp(), integerAtomicStampedReference.getStamp() + 1);
            System.out.println(Thread.currentThread().getName() +"第一次更新结果："+b1+"，版本号："+ integerAtomicStampedReference.getStamp());
            boolean b2 = integerAtomicStampedReference.compareAndSet(2, 1, integerAtomicStampedReference.getStamp(), integerAtomicStampedReference.getStamp() + 1);
            System.out.println(Thread.currentThread().getName() +"第二次更新结果："+b2+"，版本号："+ integerAtomicStampedReference.getStamp());
        },"A"){}.start();

        new Thread(()->{
            int stamp = integerAtomicStampedReference.getStamp();
            System.out.println("线程B获取的第一次版本号：" + stamp);

            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            };

            boolean b1 = integerAtomicStampedReference.compareAndSet(1, 2, stamp, stamp + 1);
            System.out.println(Thread.currentThread().getName() +"第二次更新结果："+b1+"，版本号："+ integerAtomicStampedReference.getStamp());
            System.out.println(Thread.currentThread().getName() +"当前实际值为："+integerAtomicStampedReference.getReference());

        },"B"){}.start();

    }
}
