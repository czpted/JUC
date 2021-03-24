import java.util.concurrent.TimeUnit;

/**
 * @ClassName VolatileTest
 * @Description Volatile可见性特性
 * @Author CZPTED
 * @Date 2021/3/24 14:32
 * @Version 1.0
 **/

class MyVolatileThread{
    volatile int i = 0;
    public  void  change(){
        this.i = 60;
    }
}

public class VolatileTest {
    public static void main(String[] args){
        MyVolatileThread myVolatileThread = new MyVolatileThread();

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
