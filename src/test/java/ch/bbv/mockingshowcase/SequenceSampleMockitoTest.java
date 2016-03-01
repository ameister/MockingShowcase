package ch.bbv.mockingshowcase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;

/**
 * Created by MeisAnd1 on 04.02.2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class SequenceSampleMockitoTest {
    @Mock
    private Processor<TestT, TestV> processor;
    @InjectMocks
    private SequenceSample<TestT, TestV> testSubject;

    @Test
    public void anyParam_doSequence_callsInCorrectOrder() {
        //given
        final TestV parameter = mock(TestV.class);
        //when
        testSubject.doSequence(parameter);
        //then
        //Mit Mockito sind die Fehlerausgaben sch�ner als bei JMockit, wenn die Reihenfolge nicht eingehalten ist
        // Reihenfolge InOrder relevant?
        // Die Reihenfolge wie die Mocks hier �bergeben werden ist irrelevant
        InOrder inOrder = inOrder(processor, parameter);
        // hier wird die Reienfolge festgelegt:
        inOrder.verify(parameter).prepare();
        inOrder.verify(processor).preProcess(parameter);
        inOrder.verify(processor).process(parameter);
        inOrder.verify(processor).cleanup();
    }
}