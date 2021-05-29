package com.brain.util.minimization.point;

import javafx.util.Pair;

import java.util.Objects;

public class Triple<K,V,T> extends Pair<K,V> {

    T result;

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    /**
     * Creates a new pair
     *
     * @param key   The key for this pair
     * @param value The value to use for this pair
     */
    public Triple(K key, V value, T result) {
        super(key, value);
        this.result = result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Triple<?, ?, ?> triple = (Triple<?, ?, ?>) o;
        return Objects.equals(result, triple.result);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), result);
    }
}
