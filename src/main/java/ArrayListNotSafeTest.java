import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @ClassName ArrayListNotSafeTest
 * @Description ArrayList线程不安全案例
 * @Author CZPTED
 * @Date 2021/4/1 8:25
 * @Version 1.0
 **/
public class ArrayListNotSafeTest {
    public static void main(String[] args){
        //ArrayList线程不安全案例。
        //ArrayListNotSafe();
        //解决方法1
        //ArrayListNotSafeSolution1();
        //解决方法2
        ArrayListNotSafeSolution2();

    }

    public static  void ArrayListNotSafe(){
        List<String> list = new ArrayList<>();
        for (int i =0; i<30; i++){
            new Thread(()->{
                list.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(list);
            },String.valueOf(i)).start();
        }
    }

    public static  void ArrayListNotSafeSolution1(){
        List<String> list = Collections.synchronizedList(new ArrayList<>());
        for (int i =0; i<30; i++){
            new Thread(()->{
                list.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(list);
            },String.valueOf(i)).start();
        }
    }

    public static  void ArrayListNotSafeSolution2(){
        List<String> list = new CopyOnWriteArrayList<>();
        for (int i =0; i<30; i++){
            new Thread(()->{
                list.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(list);
            },String.valueOf(i)).start();
        }
    }

}
