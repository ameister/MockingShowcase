package ch.bbv.mockingshowcase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

/**
 * Created by MeisAnd1 on 04.02.2016.
 */
@RunWith(Parameterized.class)
public class AmountCalculatorMockitoTest {

    private final BigDecimal price;
    private final PriceAware media;

    public AmountCalculatorMockitoTest(BigDecimal price, PriceAware media) {
        this.price = price;
        this.media = media;
    }

    @Parameterized.Parameters
    public static List<Object[]> balanceRates() {
        final Book book = mock(Book.class);
        given(book.getPrice()).willReturn(BigDecimal.TEN);
        final CD cd = mock(CD.class);
        given(cd.getPrice()).willReturn(new BigDecimal("55"));
        final DVD dvd = mock(DVD.class);
        given(dvd.getPrice()).willReturn(BigDecimal.ONE);

        return Arrays.asList(new Object[][]{
                {BigDecimal.TEN, book},
                {BigDecimal.ONE, dvd},
                {new BigDecimal("55"), cd}
        });
    }

    @Test
    public void media_getAmount_priceCorrectCalculated() throws Exception {
        //given
        final AmountCalculator testSubject = new AmountCalculator(mock(Invoice.class));
        testSubject.visit(media);
        //when
        final BigDecimal result = testSubject.getAmount();
        //then
        assertThat(result, is(price));
    }
}