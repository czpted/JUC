/**
 * @ClassName VolatileTest3
 * @Description Volatile特性 —— 禁止指令重排
 * @Author CZPTED
 * @Date 2021/3/24 16:45
 * @Version 1.0
 **/

class MyResortSeqThread {
    int a= 0;
    boolean flag = false;
    public void method01() {

        flag = true;

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        a = 1;
    }
    public void method02() {
        if(flag) {
            a = a + 5;
            System.out.println("reValue:" + a);
        }
    }
}

public class VolatileTest3 {
    public static void main(String[] args){

        MyResortSeqThread myResortSeqThread = new MyResortSeqThread();

        new Thread(()->{
            myResortSeqThread.method01();
//            myResortSeqThread.method02();
        },"A"){}.start();

        new Thread(()->{
//            myResortSeqThread.method01();
            myResortSeqThread.method02();
        },"B"){}.start();

    }

}
