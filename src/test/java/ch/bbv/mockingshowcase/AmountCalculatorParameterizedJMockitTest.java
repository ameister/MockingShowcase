package ch.bbv.mockingshowcase;

import mockit.Mock;
import mockit.MockUp;
import mockit.Mocked;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by MeisAnd1 on 04.02.2016.
 */
@RunWith(Parameterized.class)
public class AmountCalculatorParameterizedJMockitTest {

    private final BigDecimal price;
    private final PriceAware media;

    public AmountCalculatorParameterizedJMockitTest(BigDecimal price, PriceAware media) {
        this.price = price;
        this.media = media;
    }

    @Parameterized.Parameters
    public static List<Object[]> balanceRates() {
        /*
        * Hier werden MockUps verwendet, wo jede Methode die gemockt werden soll, deklariert werden muss.
        * http://jmockit.org/api1x/mockit/MockUp.html
        * Leider ist das instanzieren wie bei Mockito oder JMock nicht moeglich
        * z.B: Book = mock(Book.class) geht nicht
        * */
        final Book book = new MockUp<Book>() {
            @Mock
            BigDecimal getPrice() {
                return BigDecimal.TEN;
            }
        }.getMockInstance();
        final CD cd = new MockUp<CD>() {
            @Mock
            BigDecimal getPrice() {
                return new BigDecimal("55");
            }
        }.getMockInstance();
        final DVD dvd = new MockUp<DVD>() {
            @Mock
            BigDecimal getPrice() {
                return BigDecimal.ONE;
            }
        }.getMockInstance();

        return Arrays.asList(new Object[][]{
                {BigDecimal.TEN, book},
                {BigDecimal.ONE, dvd},
                {new BigDecimal("55"), cd}
        });
    }

    @Test
    @Ignore("funktioniert nur mit IntelliJ VM...")
    // WICHTIG: Das funktioniert nur, wenn JMockit vor JUnit im Klassenpafad liegt
    public void media_getAmount_priceCorrectCalculated(@Mocked Invoice invoice) throws Exception {
        //given
        final AmountCalculator testSubject = new AmountCalculator(invoice);
        testSubject.visit(media);
        //when
        final BigDecimal result = testSubject.getAmount();
        //then
        assertThat(result, is(price));
    }
}