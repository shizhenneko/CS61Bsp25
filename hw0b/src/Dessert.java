public class Dessert {
    public int flavor;
    public int price;
    public Dessert(int flavor,int price){
        this.flavor = flavor;
        this.price = price;
        numDesserts +=1;
    }
    public static int numDesserts = 0;
    public void printDessert(){
        System.out.println(flavor+" "+price+" "+numDesserts);
    }
    public static void main(String[] args)
    {
       System.out.println("I love dessert!");
    }

}
