import java.util.concurrent.TimeUnit;

/**
 * @ClassName VolatileTest
 * @Description Volatile特性 —— 可见性
 * @Author CZPTED
 * @Date 2021/3/24 14:32
 * @Version 1.0
 **/

class MyVolatileThread1{
    volatile int i = 0;
    public  void  change(){
        this.i = 60;
    }
}

public class VolatileTest1 {
    public static void main(String[] args){
        MyVolatileThread1 myVolatileThread = new MyVolatileThread1();

        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            myVolatileThread.change();
            System.out.println(myVolatileThread.i);
        }){}.start();

        while (myVolatileThread.i == 0){}

        System.out.println("=================");
    }
}
