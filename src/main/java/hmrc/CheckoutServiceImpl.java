package main.java.hmrc;

import main.java.hmrc.Item;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static main.java.hmrc.Item.APPLE;
import static main.java.hmrc.Item.ORANGE;

/**
 * Created by csouza on 16/04/2015.
 */
public class CheckoutServiceImpl implements main.java.hmrc.CheckoutService {

    @Override
    public String performCheckout(List<Item> items) {
        final BigDecimal totalPrice = sumItems(items);
        return formatPrice(totalPrice);
    }

    @Override
    public String performCheckoutWithOffers(List<Item> items) {
        final BigDecimal totalPrice = sumItemsWithOffers(items);
        return formatPrice(totalPrice);
    }

    private BigDecimal sumItemsWithOffers(List<Item> items) {
        Map<Item, Integer> itemsCount = new HashMap<>();
        for (Item i: items) {
            if (itemsCount.get(i) == null) {
                itemsCount.put(i, 1);
                continue;
            }
            itemsCount.put(i, itemsCount.get(i) + 1);
        }
        final int totalApplesToPay = itemsCount.get(APPLE) / 2 + itemsCount.get(APPLE) % 2;
        final BigDecimal totalPriceForApples = APPLE.getPrice().multiply(new BigDecimal(totalApplesToPay));

        int totalOrangesToPay = itemsCount.get(ORANGE);
        if (totalOrangesToPay == 3) {
            totalOrangesToPay = 2;
        } else if (totalOrangesToPay > 3) {
            totalOrangesToPay = totalOrangesToPay - (totalOrangesToPay % 3);
        }
        final BigDecimal totalPriceForOranges = ORANGE.getPrice().multiply(new BigDecimal(totalOrangesToPay));

        BigDecimal sum = new BigDecimal(0);
        sum = sum.add(totalPriceForApples).add(totalPriceForOranges);
        return sum;
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
