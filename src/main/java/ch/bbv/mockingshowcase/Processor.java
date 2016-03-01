package ch.bbv.mockingshowcase;

/**
 * Created by MeisAnd1 on 04.02.2016.
 */
public interface Processor<T, V> {
    T process(V parameter);

    void preProcess(V parameter);

    void cleanup();
}
