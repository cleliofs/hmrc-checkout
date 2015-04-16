package test.java.hmrc;

import com.google.common.collect.ImmutableList;
import main.java.hmrc.CheckoutService;
import main.java.hmrc.CheckoutServiceImpl;
import main.java.hmrc.Item;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import java.util.List;

import static main.java.hmrc.Item.APPLE;
import static main.java.hmrc.Item.ORANGE;
import static org.junit.Assert.assertEquals;

/**
 * Created by csouza on 16/04/2015.
 */
@RunWith(BlockJUnit4ClassRunner.class)
public class CheckoutServiceTest {

    private CheckoutService underTest;

    @Before
    public void setup() {
        underTest = new CheckoutServiceImpl();
    }

    @Test
    public void testPerformCheckout1() {
        final List<Item> items = new ImmutableList.Builder<Item>().add(APPLE, APPLE, ORANGE, APPLE).build();
        final String totalPrice = underTest.performCheckout(items);
        assertEquals("£2.05", totalPrice);
    }

    @Test
    public void testPerformCheckout2() {
        final List<Item> items = new ImmutableList.Builder<Item>().add(APPLE, ORANGE).build();
        final String totalPrice = underTest.performCheckout(items);
        assertEquals("£0.85", totalPrice);
    }

    @Test
    public void testPerformCheckout3() {
        final List<Item> items = new ImmutableList.Builder<Item>().add(APPLE, ORANGE, APPLE, APPLE, ORANGE).build();
        final String totalPrice = underTest.performCheckout(items);
        assertEquals("£2.30", totalPrice);
    }

    @Test
    public void testPerformCheckout4() {
        final List<Item> items = new ImmutableList.Builder<Item>().add(APPLE, ORANGE, APPLE, APPLE, ORANGE, APPLE, APPLE).build();
        final String totalPrice = underTest.performCheckout(items);
        assertEquals("£3.50", totalPrice);
    }

    @Test
    public void testPerformCheckout5() {
        final List<Item> items = new ImmutableList.Builder<Item>().add(APPLE, ORANGE, APPLE, APPLE, ORANGE, APPLE, APPLE, ORANGE).build();
        final String totalPrice = underTest.performCheckout(items);
        assertEquals("£3.75", totalPrice);
    }
}
