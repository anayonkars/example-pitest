package code.kata;

public class Product {
    String id;
    String name;
    Double price;

    public Product(String id, String name, Double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public Double getPrice() {
        return price;
    }

}
