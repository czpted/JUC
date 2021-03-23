/**
 * @ClassName DeathLock
 * @Description 死锁问题
 * @Author CZPTED
 * @Date 2021/3/23 23:43
 * @Version 1.0
 **/
public class DeathLock {

    public static void main(String[] args){

        final Object o1 = new Object();
        final Object o2 = new Object();

        new Thread(){
            @Override
            public void run() {
                synchronized (o1){
                    System.out.println("1");

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    synchronized (o2){
                        System.out.println("2");
                    }
                }
            }
        }.start();

        new Thread(){
            @Override
            public void run() {
                synchronized (o2){
                    System.out.println("3");

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    synchronized (o1){
                        System.out.println("4");
                    }
                }
            }
        }.start();

    }
}
