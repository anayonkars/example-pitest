package code.kata;

import org.junit.Test;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

import static java.lang.Double.min;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.junit.Assert.assertEquals;

public class DiscountTest {

    public static final double ZERO = 0.00;
    public static final double DELTA = 0.49;
    public static final double FIVE = 5.00;
    public static final double FIFTEEN = 15.00;
    public static final double HUNDRED = 100.00;
    public static final double TWO_HUNDRED = 200.00;
    public static final double THREE_HUNDRED = 300.00;
    public static final double FOUR_HUNDRED = 400.00;
    public static final double FIVE_HUNDRED = 500.00;
    public static final Predicate<Double> PRICE_GREATER_THAN_TWO_HUNDRED = p -> p > TWO_HUNDRED;
    public static final Function<Double, Double> FIVE_PERCENT = p -> p * FIVE / HUNDRED;
    public static final Predicate<Double> PRICE_GREATER_THAN_FIVE_HUNDRED = p -> p > FIVE_HUNDRED;
    public static final Function<Double, Double> FIFTEEN_PERCENT_OR_HUNDRED_WHICHEVER_IS_LESS = p -> {
        double discount = p * FIFTEEN / HUNDRED;
        return min(discount, HUNDRED);
    };
    public static final Product COFFEE = new Product("1", "Coffee", HUNDRED);
    public static final Product RICE = new Product("2", "Rice", TWO_HUNDRED);
    public static final Product SUGAR = new Product("3", "Sugar", THREE_HUNDRED);
    public static final Product BUTTER = new Product("4", "Butter", FOUR_HUNDRED);
    public static final Function<Double, Double> HUNDRED_FLAT = p -> HUNDRED;
    public static final Function<Double, Double> NO_DISCOUNT = p -> ZERO;

    @Test
    public void testOrderDiscountForNonEligibleOrder() {
        final List<Product> products = singletonList(
                COFFEE
        );
        Order order = new Order(products, PRICE_GREATER_THAN_TWO_HUNDRED, FIVE_PERCENT);
        assertEquals(ZERO, order.calculateDiscount(), DELTA);
    }

    @Test
    public void testOrderDiscountForEligibleOrderAndPercentageDiscounter() {
        final List<Product> products = asList(
                COFFEE,
                RICE
        );
        Order order = new Order(products, PRICE_GREATER_THAN_TWO_HUNDRED, FIVE_PERCENT);
        assertEquals(FIFTEEN, order.calculateDiscount(), DELTA);
    }

    @Test
    public void testOrderDiscountForEligibleOrderAndPercentageOrMaxDiscount() {
        final List<Product> products = asList(
                COFFEE,
                RICE,
                SUGAR,
                BUTTER
        );
        Order order = new Order(products, PRICE_GREATER_THAN_FIVE_HUNDRED, FIFTEEN_PERCENT_OR_HUNDRED_WHICHEVER_IS_LESS);
        assertEquals(HUNDRED, order.calculateDiscount(), DELTA);
    }

    @Test
    public void testOrderDiscountForEligibleOrderAndFlatDiscountApplied() {
        List<Product> products = asList(
                COFFEE,
                COFFEE,
                RICE,
                RICE
        );
        Order order = new Order(products, PRICE_GREATER_THAN_TWO_HUNDRED, HUNDRED_FLAT);
        assertEquals(HUNDRED, order.calculateDiscount(), DELTA);
    }

    @Test
    public void testOrderDiscountForEligibleOrderAndNoDiscountApplied() {
        List<Product> products = asList(
                COFFEE,
                RICE,
                SUGAR,
                BUTTER
        );
        Order order = new Order(products, PRICE_GREATER_THAN_TWO_HUNDRED, NO_DISCOUNT);
        assertEquals(ZERO, order.calculateDiscount(), DELTA);
    }

    @Test
    public void testOrderDiscountForNonEligibleOrderAndNoDiscountApplied() {
        List<Product> products = singletonList(
                COFFEE
        );
        Order order = new Order(products, PRICE_GREATER_THAN_TWO_HUNDRED, NO_DISCOUNT);
        assertEquals(ZERO, order.calculateDiscount(), DELTA);
    }

}
