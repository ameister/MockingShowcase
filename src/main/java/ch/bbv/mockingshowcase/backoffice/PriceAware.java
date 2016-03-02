package ch.bbv.mockingshowcase.backoffice;

import ch.bbv.mockingshowcase.Visitable;

import java.math.BigDecimal;
import java.util.Currency;

/**
 * Created by MeisAnd1 on 04.02.2016.
 */
public interface PriceAware extends Visitable {

    BigDecimal getPrice(Currency currency);
}
