import java.util.concurrent.*;

/**
 * @ClassName MyThreadPoolTest
 * @Description 线程池
 * @Author CZPTED
 * @Date 2021/4/4 15:24
 * @Version 1.0
 **/
public class MyThreadPoolTest {
    public static void main(String[] args){
        MyThreadPoolTest myThreadPoolTest = new MyThreadPoolTest();
        //采用executors创建线程池
//        myThreadPoolTest.executorsPoolTest();
        //用户自定义线程池
        myThreadPoolTest.userPoolTest();
    }

    public void executorsPoolTest(){
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

    public void userPoolTest(){

        final Integer corePoolSize = 2;
        final Integer maximumPoolSize = 5;
        final Long keepAliveTime = 1L;

        ThreadPoolExecutor executor = new ThreadPoolExecutor(corePoolSize,
                maximumPoolSize,
                keepAliveTime,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());

        try {
            for (int i=0; i<6; i++){
                final Integer tempInt = i;
                executor.execute(()->{
                    System.out.println(Thread.currentThread().getName()+"\t给用户："+tempInt+"办理业务");
                    try {
                        TimeUnit.SECONDS.sleep(4);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            executor.shutdown();
        }
    }

}
