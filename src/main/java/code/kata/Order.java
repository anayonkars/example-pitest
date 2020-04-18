package code.kata;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class Order {

    private final List<Product> products;
    private final Predicate<Double> priceEligibility;
    private final Function<Double, Double> discounter;

    public Order(List<Product> products,
                 Predicate<Double> priceEligibility,
                 Function<Double, Double> discounter) {

        this.products = products;
        this.priceEligibility = priceEligibility;
        this.discounter = discounter;
    }

    public double calculateDiscount() {
        double totalPrice = getTotalPrice();
        return priceEligibility.test(totalPrice) ? discounter.apply(totalPrice) : 0;
    }

    private double getTotalPrice() {
        return products.stream().mapToDouble(Product::getPrice).sum();
    }
}
