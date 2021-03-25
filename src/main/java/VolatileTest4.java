/**
 * @ClassName VolatileTest4
 * @Description volatile在单例的使用
 * @Author CZPTED
 * @Date 2021/3/24 17:43
 * @Version 1.0
 **/

class Singleton{
    private static volatile Singleton instance;
    private static Object o = new Object();

    private Singleton(){
        System.out.println("我是构造方法");
    };
    public static Singleton getInstance(){

//        if(instance == null){
//            return new Singleton();
//        }

        if(instance == null){
            synchronized (o){
                if (instance == null){
                    instance =  new Singleton();
                }
            }
        }

        return  instance;
    }
}

public class VolatileTest4 {
    public static void main(String [] args){
        for (int i=0; i<10; i++){
            new Thread(()->{
                Singleton instance = Singleton.getInstance();
                instance.getClass();
            },String.valueOf(i)){}.start();
        }
    }
}
