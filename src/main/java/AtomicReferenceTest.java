import java.util.concurrent.atomic.AtomicReference;

/**
 * @ClassName AtomicReferenceTest
 * @Description AtomicReference的使用
 * @Author CZPTED
 * @Date 2021/3/26 0:53
 * @Version 1.0
 **/

class User {

    private String name;
    private String age;

    public User() {
    }

    public User(String name, String age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                '}';
    }

}

public class AtomicReferenceTest {

   public static void main(String[] args){

       AtomicReference<User> userAtomicReference = new AtomicReference<>();

       User a = new User("A", "20");
       User b = new User("B", "21");
       userAtomicReference.set(a);
       boolean b1 = userAtomicReference.compareAndSet(a, b);
       boolean b2 = userAtomicReference.compareAndSet(a, b);
       System.out.println("b1的交换结果：" + b1 + "，交换后的用户是："+ userAtomicReference.get().toString());
       System.out.println("b2的交换结果： " + b2 +"，交换后的用户是："+ userAtomicReference.get().toString());

   }
}
