package code.kata;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class DiscountTest {
    @Test
    public void testOrderDiscountForOrderWithoutDiscount() {
        final List<Product> products = Arrays.asList(
                new Product("1", "Coffee", 100.00)
        );
        Order order = new Order(products, new PriceEligibility(200.00), new PercentageDiscounter(5));
        Assert.assertEquals(0, order.calculateDiscount(), 0.49);
    }

}
