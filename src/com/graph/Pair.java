package com.graph;

/**
 * Used for saving the duplicated key value pairs for saving edges
 * @param <T> Key
 * @param <U> value
 */
public class Pair<T, U> {
    public final T key;
    public final U value;

    public Pair(T key, U value) {
        this.key = key;
        this.value = value;
    }
}
