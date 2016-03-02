package ch.bbv.mockingshowcase.backoffice;

import ch.bbv.mockingshowcase.media.Book;
import mockit.*;
import org.hamcrest.CustomMatcher;
import org.junit.Test;


import java.math.BigDecimal;
import java.util.Currency;


/**
 * Created by AndreasMeister on 01.03.2016.
 */
public class AmountCalculatorJMockitMatcherTest {

    @Injectable
    private Invoice invoiceMock;

    @Tested
    private AmountCalculator testSubject;

    @Test
    public void aBook_visit_currencyMatches(@Mocked Book bookMock) throws Exception {
        //given
        new Expectations(){{
            bookMock.getPrice(withInstanceOf(Currency.class)); returns(BigDecimal.TEN);
        }};
        //when
        testSubject.visit(bookMock);
        //then
        new Verifications(){{
            bookMock.getPrice(withArgThat(new CurrencyMatcher()));
        }};
    }

    private static class CurrencyMatcher extends CustomMatcher<Currency> {
        public CurrencyMatcher() {
            super("CHF Currency expected");
        }

        @Override
        public boolean matches(Object argument) {
            if (argument instanceof Currency) {
                Currency currency = (Currency) argument;
                if (Currency.getInstance("CHF").equals(currency)) {
                    return true;
                }
            }
            return false;
        }
    }
}