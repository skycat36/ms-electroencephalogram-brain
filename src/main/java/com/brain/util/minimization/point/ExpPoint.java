package com.brain.util.minimization.point;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Objects;

@Data
@EqualsAndHashCode
public class ExpPoint<T> {

    private T pointX;
    private T pointY;
    private T result;

    /**
     * Creates a new triple
     *
     * @param pointX   The key for this pair
     * @param pointY     The value to use for this pair
     * @param result The value to use for this pair
     */
    public ExpPoint(T pointX, T pointY, T result) {
        this.pointX = pointX;
        this.pointY = pointY;
        this.result = result;
    }

}
