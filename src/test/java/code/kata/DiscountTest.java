package code.kata;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DiscountTest {
    @Test
    public void testOrderDiscountForOrderWithoutDiscount() {
        final List<Product> products = Collections.singletonList(
                new Product("1", "Coffee", 100.00)
        );
        Order order = new Order(products, p -> p > 200.00, p -> p * 5 / 100);
        Assert.assertEquals(0, order.calculateDiscount(), 0.49);
    }

    @Test
    public void testOrderDiscountForOrderWithDiscountAndPercentageDiscounter() {
        final List<Product> products = Arrays.asList(
                new Product("1", "Coffee", 100.00),
                new Product("2", "Rice", 200.00)
        );
        Order order = new Order(products, p -> p > 200.00, p -> p * 5 /100);
        Assert.assertEquals(15.0, order.calculateDiscount(), 0.49);
    }

}
