package ch.bbv.mockingshowcase;

import java.math.BigDecimal;

/**
 * Created by MeisAnd1 on 04.02.2016.
 */
public class Book implements PriceAware{
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public BigDecimal getPrice() {
        return BigDecimal.TEN;
    }
}
