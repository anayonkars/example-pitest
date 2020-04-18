package code.kata;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

import static java.lang.Double.min;
import static java.util.Collections.singletonList;
import static org.junit.Assert.assertEquals;

public class DiscountTest {

    public static final Predicate<Double> PRICE_GREATER_THAN_TWO_HUNDRED = p -> p > 200.00;
    public static final Function<Double, Double> FIVE_PERCENT = p -> p * 5 / 100;
    public static final Predicate<Double> PRICE_GREATER_THAN_FIVE_HUNDRED = p -> p > 500.00;
    public static final Function<Double, Double> FIFTEEN_PERCENT_OR_HUNDRED_WHICHEVER_IS_LESS = p -> {
        double discount = p * 15 / 100;
        return min(discount, 100.00);
    };
    public static final Product COFFEE = new Product("1", "Coffee", 100.00);
    public static final Product RICE = new Product("2", "Rice", 200.00);
    public static final Product SUGAR = new Product("3", "Sugar", 300.00);
    public static final Product BUTTER = new Product("4", "Butter", 400.00);

    @Test
    public void testOrderDiscountForOrderWithoutDiscount() {
        final List<Product> products = singletonList(
                COFFEE
        );
        Order order = new Order(products, PRICE_GREATER_THAN_TWO_HUNDRED, FIVE_PERCENT);
        assertEquals(0, order.calculateDiscount(), 0.49);
    }

    @Test
    public void testOrderDiscountForOrderWithDiscountAndPercentageDiscounter() {
        final List<Product> products = Arrays.asList(
                COFFEE,
                RICE
        );
        Order order = new Order(products, PRICE_GREATER_THAN_TWO_HUNDRED, FIVE_PERCENT);
        assertEquals(15.0, order.calculateDiscount(), 0.49);
    }

    @Test
    public void testOrderDiscountForOrderWithDiscountAndPercentageOrMaxDiscount() {
        final List<Product> products = Arrays.asList(
                COFFEE,
                RICE,
                SUGAR,
                BUTTER
        );
        Order order = new Order(products, PRICE_GREATER_THAN_FIVE_HUNDRED, FIFTEEN_PERCENT_OR_HUNDRED_WHICHEVER_IS_LESS);
        assertEquals(100.00, order.calculateDiscount(), 0.49);
    }

}
