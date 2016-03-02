package ch.bbv.mockingshowcase.backoffice;

import mockit.*;
import mockit.integration.junit4.JMockit;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.math.BigDecimal;
import java.util.List;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Created by MeisAnd1 on 17.02.2016.
 */
@RunWith(JMockit.class)
public class AmountCalculatorJMockitStaticFinalTest {

    @Test
    public void someItems_getAmountOfInvoiceStatic_totalOfInvoceFirst(@Mocked FinalInvoice invoiceMock) {
        //given
        new Expectations() {
            {
                FinalInvoice.calculateTotalAmount(withInstanceOf(List.class));
                returns(BigDecimal.TEN); times = 1;
            }
        };
        AmountCalculator testSubject = new AmountCalculator();
        //when
        BigDecimal result = testSubject.getAmountOfInvoiceStatic();
        //then
        //Verifications kann nicht verwendet werden
        assertThat(result, equalTo(BigDecimal.TEN));
    }

    @Test
    public void someItems_getAmountOfInvoiceStatic_totalOfInvoceSecond() {
        //given
        new MockUp<FinalInvoice>() {
            @Mock(invocations = 1)
            public BigDecimal calculateTotalAmount(List<Item> items) {
                return BigDecimal.TEN;
            }
        };
        AmountCalculator testSubject = new AmountCalculator();
        //when
        BigDecimal result = testSubject.getAmountOfInvoiceStatic();
        //then
        //Verifications kann nicht verwendet werden
        assertThat(result, equalTo(BigDecimal.TEN));
    }

    @Test
    public void someItems_getAmountOfInvoiceFinal_totalOfInvoce(@Mocked Invoice invoiceMock) {
        //given
        new Expectations() {
            {
                invoiceMock.calculateTotalAmountFinal();
                returns(BigDecimal.TEN);
            }
        };
        AmountCalculator testSubject = new AmountCalculator(invoiceMock);
        //when
        BigDecimal result = testSubject.getAmountOfInvoiceFinal();
        //then
        new Verifications() {
            {
                invoiceMock.calculateTotalAmountFinal(); times = 1;
            }
        };
        assertThat(result, equalTo(BigDecimal.TEN));
    }
}
