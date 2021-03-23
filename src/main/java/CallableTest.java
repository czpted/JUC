import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @ClassName CallableTest
 * @Description 线程的第三种创建方式——继承Callable接口
 * @Author CZPTED
 * @Date 2021/3/24 1:37
 * @Version 1.0
 **/

class MyCallableThread implements Callable{

    public Object call() throws Exception {
        int sum = 0;
        for(int i = 0; i<100; i++){
            if (i % 2 == 0){
                System.out.println(i);
                sum += i;
            }
        }
        return sum;
    }
}


public class CallableTest {
    public static void main(String[] args){

        MyCallableThread myCallableThread = new MyCallableThread();
        FutureTask futureTask = new FutureTask(myCallableThread);
        Thread thread = new Thread(futureTask);
        thread.start();

        try {
            Integer i = (Integer)futureTask.get();
            System.out.println(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
}
