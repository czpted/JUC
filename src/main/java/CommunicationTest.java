/**
 * @ClassName CommunicationTest
 * @Description 线程通讯
 * @Author CZPTED
 * @Date 2021/3/24 1:20
 * @Version 1.0
 **/

class MyCommunicationThread implements  Runnable{
    private int number = 1;
    private Object o = new Object();

    public void run() {

        while (true){
            synchronized (o){
                o.notify();
                if(number <= 100){
                    System.out.println(Thread.currentThread().getName() + number);
                    number++;
                    try {
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }else {
                    break;
                }
            }
        }

    }
}


public class CommunicationTest {
    public static void main(String[] args){
        MyCommunicationThread myCommunicationThread = new MyCommunicationThread();
        Thread thread1 = new Thread(myCommunicationThread);
        Thread thread2 = new Thread(myCommunicationThread);
        thread1.setName("线程一");
        thread2.setName("线程二");
        thread1.start();
        thread2.start();
    }
}
