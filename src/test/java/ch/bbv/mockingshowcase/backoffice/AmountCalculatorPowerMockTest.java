package ch.bbv.mockingshowcase.backoffice;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.math.BigDecimal;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Matchers.anyListOf;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.verifyStatic;

/**
 * Created by MeisAnd1 on 17.02.2016.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest( { FinalInvoice.class})
public class AmountCalculatorPowerMockTest {

    @Mock
    private Invoice invoiceMock;

    @InjectMocks
    private AmountCalculator testSubject;

    @Test
    public void someItems_getAmountOfFinalInvoiceStatic_totalOfInvoce() {
        //given
        mockStatic(FinalInvoice.class);
        given(FinalInvoice.calculateTotalAmount(anyListOf(Item.class))).willReturn(BigDecimal.TEN);
        //when
        BigDecimal result = testSubject.getAmountOfInvoiceStatic();
        //then
        verifyStatic();
        FinalInvoice.calculateTotalAmount(anyListOf(Item.class));
        assertThat(result, equalTo(BigDecimal.TEN));
    }

    @Test
    public void someItems_getAmountOfFinalInvoiceFinal_totalOfInvoce() {
        //given
        given(invoiceMock.calculateTotalAmountFinal()).willReturn(BigDecimal.TEN);
        //when
        BigDecimal result = testSubject.getAmountOfInvoiceFinal();
        //then
        then(invoiceMock).should().calculateTotalAmountFinal();
        assertThat(result, equalTo(BigDecimal.TEN));
    }
}
