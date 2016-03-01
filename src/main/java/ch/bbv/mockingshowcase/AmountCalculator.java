package ch.bbv.mockingshowcase;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * Created by MeisAnd1 on 04.02.2016.
 */
public class AmountCalculator implements Visitor{
    private final Invoice invoice;
    private BigDecimal amount = BigDecimal.ZERO;

    public AmountCalculator(Invoice invoice) {
        this.invoice = invoice;
    }

    public AmountCalculator() {
        this(null);
    }

    @Override
    public void visit(PriceAware media) {
        amount = amount.add(media.getPrice());
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public BigDecimal getAmountOfInvoiceStatic() {
        return FinalInvoice.calculateTotalAmount(new ArrayList<>());
    }

    public BigDecimal getAmountOfInvoiceFinal() {
        return invoice.calculateTotalAmountFinal();
    }

}
