package ch.bbv.mockingshowcase.media;

import java.math.BigDecimal;
import java.util.Currency;

/**
 * Created by MeisAnd1 on 04.02.2016.
 */
public class CD extends Media{

    public CD(String name) {
        super(name);
    }

    @Override
    public BigDecimal getPrice(Currency currency) {
        return new BigDecimal("14");
    }
}
