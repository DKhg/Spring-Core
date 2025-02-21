package hello.core.singleton;

public class StatefulService {

    //싱글톤 문제
    /*private int price;  //상태를 유지하는 필드

    public void order(String name, int price) {
        System.out.println("name = " + name + "price = " +price);
        this.price = price; //여기가 문제
    }*/

    public int order(String name, int price) {
        System.out.println("name = " + name + "price = " +price);
        return price;
    }

}
