package com.brain.util.minimization.single;

import java.util.function.DoubleFunction;


public interface SingleArgumentFunctionMinimizer {
    double minimize(DoubleFunction<Double> f, double lowerBound, double higherBound, double precision);
}
