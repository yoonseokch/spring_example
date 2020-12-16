package hello.core.singleton;

public class StatefulService {

    private int price;
    public void order(String name,int price)
    {
        System.out.println("이름 "+name+" 가격 : "+price);
        this.price=price;
    }
    public int getPrice()
    {
        return this.price;
    }
}
