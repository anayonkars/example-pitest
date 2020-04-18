package code.kata;

public class PriceEligibility {

    private final double price;

    public PriceEligibility(double price) {
        this.price = price;
    }

    public boolean isEligible(double price) {
        return price > this.price;
    }
}
