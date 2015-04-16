package main.java.hmrc;

import main.java.hmrc.Item;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

/**
 * Created by csouza on 16/04/2015.
 */
public class CheckoutServiceImpl implements main.java.hmrc.CheckoutService {

    @Override
    public String performCheckout(List<Item> items) {
        final BigDecimal totalPrice = sumItems(items);
        return formatPrice(totalPrice);
    }

    private String formatPrice(BigDecimal totalPrice) {
        StringBuffer sb = new StringBuffer("£");
        sb.append(totalPrice.setScale(2, RoundingMode.CEILING));
        return sb.toString();
    }

    private BigDecimal sumItems(List<Item> items) {
        BigDecimal sum = new BigDecimal(0);
        for (Item i: items) {
            sum = sum.add(i.getPrice());
        }
        return sum;
    }
}
