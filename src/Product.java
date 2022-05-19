public class Product {

    public int cost;
    public int quantity;

    public Product (int cost, int quantity){
        this.cost = cost;
        this.quantity = quantity;
    }

    @Override
    public String toString(){

        return "\nцена = " + cost +  " руб.\nколичество = "+ quantity +" шт.";
    }


}

