package code.kata;

import java.util.List;
import java.util.function.Predicate;

public class Order {

    private final List<Product> products;
    private final Predicate<Double> priceEligibility;
    private final PercentageDiscounter percentageDiscounter;

    public Order(List<Product> products,
                 Predicate<Double> priceEligibility,
                 PercentageDiscounter percentageDiscounter) {

        this.products = products;
        this.priceEligibility = priceEligibility;
        this.percentageDiscounter = percentageDiscounter;
    }

    public double calculateDiscount() {
        double totalPrice = getTotalPrice();
        if(priceEligibility.test(totalPrice)) {
            return totalPrice * percentageDiscounter.getPercentage() / 100;
        }
        return 0;
    }

    private double getTotalPrice() {
        return products.stream().mapToDouble(p -> p.getPrice()).sum();
    }
}
