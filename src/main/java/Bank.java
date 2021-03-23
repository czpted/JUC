/**
 * @ClassName Bank
 * @Description 线程安全的单例模式
 * @Author CZPTED
 * @Date 2021/3/23 23:10
 * @Version 1.0
 **/
public class Bank {
    private Bank(){};
    private static Bank instanst = null;
    private static Bank getInstanst(){
            if(instanst == null){
                synchronized (Bank.class){
                    if (instanst == null){
                        return new Bank();
                    }
                }
            }
        return  instanst;
    }
}
