package main.java.hmrc;

import java.util.List;

/**
 * Created by csouza on 16/04/2015.
 */
public interface CheckoutService {

    String performCheckout(List<Item> items);

    String performCheckoutWithOffers(List<Item> items);
}
