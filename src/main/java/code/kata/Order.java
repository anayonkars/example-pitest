package code.kata;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class Order {

    private final List<Product> products;
    private final Predicate<Double> priceEligibility;
    private final Function<Double, Double> percentageDiscounter;

    public Order(List<Product> products,
                 Predicate<Double> priceEligibility,
                 Function<Double, Double> percentageDiscounter) {

        this.products = products;
        this.priceEligibility = priceEligibility;
        this.percentageDiscounter = percentageDiscounter;
    }

    public double calculateDiscount() {
        double totalPrice = getTotalPrice();
        if(priceEligibility.test(totalPrice)) {
            return percentageDiscounter.apply(totalPrice);
        }
        return 0;
    }

    private double getTotalPrice() {
        return products.stream().mapToDouble(Product::getPrice).sum();
    }
}
