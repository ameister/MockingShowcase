package ch.bbv.mockingshowcase.backoffice;

import ch.bbv.mockingshowcase.Visitor;

import java.math.BigDecimal;
import java.util.Currency;

/**
 * Created by MeisAnd1 on 17.02.2016.
 */
public class Item implements PriceAware {

    private final PriceAware product;

    public Item(PriceAware product) {
        this.product = product;
    }

    @Override
    public BigDecimal getPrice(Currency currency) {
        return product.getPrice(currency);
    }

    @Override
    public void accept(Visitor visitor) {
    }
}
