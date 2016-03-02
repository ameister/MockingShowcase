package ch.bbv.mockingshowcase.media;

import ch.bbv.mockingshowcase.backoffice.PriceAware;
import ch.bbv.mockingshowcase.Visitor;

/**
 * Created by MeisAnd1 on 04.02.2016.
 */
public abstract class Media implements PriceAware {

    private final String name;

    public Media(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
