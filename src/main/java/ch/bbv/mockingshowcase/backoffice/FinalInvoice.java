package ch.bbv.mockingshowcase.backoffice;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.List;
import java.util.Locale;

/**
 * Created by MeisAnd1 on 04.02.2016.
 */
public final class FinalInvoice extends Invoice {
    public static BigDecimal calculateTotalAmount(List<Item> items) {
        final BigDecimal total = BigDecimal.ZERO;
        items.forEach(item -> total.add(item.getPrice(Currency.getInstance(Locale.getDefault()))));
        return total;
    }
}
