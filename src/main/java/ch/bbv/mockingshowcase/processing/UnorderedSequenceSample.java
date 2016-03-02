package ch.bbv.mockingshowcase.processing;

import java.util.*;

/**
 * Created by MeisAnd1 on 04.02.2016.
 */
public final class UnorderedSequenceSample<T, V extends Parameter> {
    private final Processor<T, V> processor;

    public UnorderedSequenceSample(Processor<T, V> processor) {
        this.processor = processor;
    }

    public Collection<T> doUnordered(Collection<V> parameters) {
        final Collection<T> results = new HashSet<>();

        // Reihenfolge der Parameter bei der Verarbeitung spielt keine
        // Rolle, aber f�r jeden Parameter immer erst prepare, dann
        // preProcess, dann process
        final List<V> parametersRandom = new ArrayList<>(parameters);
        Collections.shuffle(parametersRandom);
        for (V parameter : parametersRandom) {
            parameter.prepare();
        }

        Collections.shuffle(parametersRandom);
        for (V parameter : parametersRandom) {
            processor.preProcess(parameter);
            final T result = processor.process(parameter);
            results.add(result);
        }

        // das darf erst als allerletztes passieren:
        processor.cleanup();

        return results;
    }
}

