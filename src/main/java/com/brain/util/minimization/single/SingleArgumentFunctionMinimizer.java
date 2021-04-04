package com.brain.util.minimization.single;

import java.util.function.DoubleFunction;


public interface SingleArgumentFunctionMinimizer {
    public double minimize(DoubleFunction<Double> f, double lowerBound, double higherBound, double precision);
    public double minimize(DoubleFunction<Double> f, double lowerBound, double higherBound, int iterations);
    public double minimize(DoubleFunction<Double> f, double lowerBound, double higherBound, double precision, double L);
}
