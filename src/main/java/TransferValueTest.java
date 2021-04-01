/**
 * @ClassName TransferValueTest
 * @Description JAVA的值传递和引用传递
 * @Author CZPTED
 * @Date 2021/4/1 8:45
 * @Version 1.0
 **/

class Person{
    private int id;
    private String name;

    public Person(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

public class TransferValueTest {

    public  void change1(int i){
        i = 30;
    }

    public  void change2(String str){
        str = "hello";
    }

    public  void change3(Person p){
        p.setName("xiaoming");
    }


    public static void main(String[] args){

        TransferValueTest transferValueTest = new TransferValueTest();

        int i = 0;
        transferValueTest.change1(i);
        System.out.println(i);

        String str = "0";
        transferValueTest.change2(str);
        System.out.println(str);

        Person person = new Person(0, "0");
        transferValueTest.change3(person);
        System.out.println(person);

    }
}
