class MyRunnable implements Runnable{
    public void run() {
        for (int i = 0; i<100; i++){
            if (i % 2 == 0){
                System.out.println(i);
            }
        }
    }
}

public class RunnableTest{
    public static void main (String[] args){
        MyRunnable myRunnable = new MyRunnable();
        Thread thread = new Thread(myRunnable);
        thread.start();
    }
}
