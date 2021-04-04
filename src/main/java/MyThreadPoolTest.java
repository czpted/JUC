import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName MyThreadPoolTest
 * @Description 线程池
 * @Author CZPTED
 * @Date 2021/4/4 15:24
 * @Version 1.0
 **/
public class MyThreadPoolTest {
    public static void main(String[] args){
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        try {

            for(int i = 0; i<10; i++){
                final int tempInt = i;
                executorService.execute(()->{
                    System.out.println(Thread.currentThread().getName()+"\t 给用户"+tempInt+"\t办理业务。");
                });
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            executorService.shutdown();
        }
    }
}
