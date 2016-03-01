package ch.bbv.mockingshowcase;

import java.math.BigDecimal;

/**
 * Created by MeisAnd1 on 17.02.2016.
 */
public class Item implements PriceAware {

    private final PriceAware product;

    public Item(PriceAware product) {
        this.product = product;
    }

    @Override
    public BigDecimal getPrice() {
        return product.getPrice();
    }

    @Override
    public void accept(Visitor visitor) {
    }
}
