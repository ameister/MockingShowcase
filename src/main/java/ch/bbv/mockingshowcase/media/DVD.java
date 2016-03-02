package ch.bbv.mockingshowcase.media;

import ch.bbv.mockingshowcase.Visitor;

import java.math.BigDecimal;
import java.util.Currency;

/**
 * Created by MeisAnd1 on 04.02.2016.
 */
public class DVD extends Media{

    public DVD(String name) {
        super(name);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public BigDecimal getPrice(Currency currency) {
        return new BigDecimal("33");
    }
}
