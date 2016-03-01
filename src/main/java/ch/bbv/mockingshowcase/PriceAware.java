package ch.bbv.mockingshowcase;

import java.math.BigDecimal;

/**
 * Created by MeisAnd1 on 04.02.2016.
 */
public interface PriceAware extends Visitable {

    BigDecimal getPrice();
}
