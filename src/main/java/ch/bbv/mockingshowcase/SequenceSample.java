package ch.bbv.mockingshowcase;

/**
 * Created by MeisAnd1 on 04.02.2016.
 */
public final class SequenceSample<T, V extends Parameter> {
    private final Processor<T, V> processor;

    public SequenceSample(final Processor<T, V> processor) {
        this.processor = processor;
    }

    public T doSequence(V parameter) {
        // Muss in genau dieser Reihenfolge ausgef�hrt werden
        parameter.prepare();
        processor.preProcess(parameter);
        final T result = processor.process(parameter);
        processor.cleanup();
        return result;
    }
}

