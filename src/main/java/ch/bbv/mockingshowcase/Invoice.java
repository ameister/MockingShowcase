package ch.bbv.mockingshowcase;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by MeisAnd1 on 04.02.2016.
 */
public class Invoice {

    private List<Item> itemsOnInvoice = new ArrayList();

    public final BigDecimal calculateTotalAmountFinal() {
        final BigDecimal total = BigDecimal.ZERO;
        itemsOnInvoice.forEach(item -> total.add(item.getPrice()));
        return total;
    }
}
