
class MyTicket extends Thread{

    static int number = 100;
    static Object o = new Object();

    @Override
    public void run() {
        while (true){
            synchronized (o){
                if (number > 0){
                    System.out.println(this.getName() + "卖出第" +number+"张票");
                }else {
                    break;
                }
            }
        }
    }


}


public class TicketDemo0 {
    public static void main(String[] args){
        MyTicket myTicket1 = new MyTicket();
        MyTicket myTicket2 = new MyTicket();
        MyTicket myTicket3 = new MyTicket();
        myTicket1.setName("窗口一");
        myTicket2.setName("窗口二");
        myTicket3.setName("窗口三");
        myTicket1.start();
        myTicket2.start();
        myTicket3.start();


    }
}
