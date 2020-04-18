package code.kata;

import java.util.List;

public class Order {

    private final List<Product> products;
    private final PriceEligibility priceEligibility;
    private final PercentageDiscounter percentageDiscounter;

    public Order(List<Product> products,
                 PriceEligibility priceEligibility,
                 PercentageDiscounter percentageDiscounter) {

        this.products = products;
        this.priceEligibility = priceEligibility;
        this.percentageDiscounter = percentageDiscounter;
    }

    public double calculateDiscount() {
        double totalPrice = getTotalPrice();
        if(priceEligibility.isEligible(totalPrice)) {
            return totalPrice * percentageDiscounter.getPercentage() / 100;
        }
        return 0;
    }

    private double getTotalPrice() {
        return products.stream().mapToDouble(p -> p.getPrice()).sum();
    }
}
