/**
 * @ClassName ReenterLockTest1
 * @Description Synchronized可重入锁验证
 * @Author CZPTED
 * @Date 2021/4/1 9:17
 * @Version 1.0
 **/

class Phone{
    public synchronized void sendEmain(){
        System.out.println("发送邮件");
        sendSMS();
    }
    public synchronized void sendSMS(){
        System.out.println("发送短信");
    }
}

public class ReentrantLockTest1 {
    public static void main(String[] args){
        Phone phone = new Phone();
        for (int i = 0; i<2; i++){
            new Thread(()->{
                phone.sendEmain();
            },String.valueOf(i)).start();
        }
    }
}
