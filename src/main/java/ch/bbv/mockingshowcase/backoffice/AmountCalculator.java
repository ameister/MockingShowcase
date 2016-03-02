package ch.bbv.mockingshowcase.backoffice;

import ch.bbv.mockingshowcase.Visitor;
import ch.bbv.mockingshowcase.media.Media;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Currency;

/**
 * Created by MeisAnd1 on 04.02.2016.
 */
public class AmountCalculator implements Visitor {
    private final Invoice invoice;
    private BigDecimal amount = BigDecimal.ZERO;

    public AmountCalculator(Invoice invoice) {
        this.invoice = invoice;
    }

    public AmountCalculator() {
        this(null);
    }

    @Override
    public void visit(Media media) {
        amount = amount.add(media.getPrice(Currency.getInstance("CHF")));
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
