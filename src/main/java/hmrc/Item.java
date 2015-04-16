package main.java.hmrc;

import java.math.BigDecimal;

/**
 * Created by csouza on 16/04/2015.
 */
public enum Item {

    APPLE(new BigDecimal(0.60)), ORANGE(new BigDecimal(0.25));

    private BigDecimal price;

    Item(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
