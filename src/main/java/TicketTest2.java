
class Ticket1 implements Runnable{
    private int number = 100;
    private boolean isflag = true;

    public void run() {
        while (isflag){
            saleTicket();
        }
    }

    public synchronized void saleTicket(){
            if(number > 0){
                System.out.println(Thread.currentThread().getName() + "卖出第" + number-- + "张票");
            }else {
                isflag = false;
            }
    }
}


public class TicketTest2 {
    public static void main(String[] args){
        Ticket1 ticket1 = new Ticket1();
        Thread thread1 = new Thread(ticket1);
        Thread thread2 = new Thread(ticket1);
        Thread thread3 = new Thread(ticket1);
        thread1.setName("窗口一");
        thread2.setName("窗口二");
        thread3.setName("窗口三");
        thread1.start();
        thread2.start();
        thread3.start();
    }
}
