package ch.bbv.mockingshowcase.backoffice;

import ch.bbv.mockingshowcase.media.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatcher;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.Currency;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.argThat;
import static org.mockito.Mockito.mock;

/**
 * Created by AndreasMeister on 01.03.2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class AmountCalculatorMockitoMatcherTest {

    @Mock
    private Invoice invoiceMock;

    @InjectMocks
    private AmountCalculator testSubject;

    @Test
    public void aBook_visit_currencyMatches() throws Exception {
        //given
        Book bookMock = mock(Book.class);
        given(bookMock.getPrice(any(Currency.class))).willReturn(BigDecimal.TEN);
        //when
        testSubject.visit(bookMock);
        //then
        then(bookMock).should().getPrice(argThat(new CurrencyMatcher()));
    }

    private static class CurrencyMatcher extends ArgumentMatcher<Currency> {
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