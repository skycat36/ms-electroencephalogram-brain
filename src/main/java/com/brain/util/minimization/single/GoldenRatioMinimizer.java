package com.brain.util.minimization.single;

import java.util.function.DoubleFunction;


public class GoldenRatioMinimizer implements SingleArgumentFunctionMinimizer {
    private final double GOLDEN_RATIO = (Math.sqrt(5) + 1) / 2;

    @Override
    public double minimize(DoubleFunction<Double> f, double lowerBound, double higherBound, double precision) {
        int counter = 0;

        while (!(Math.abs(higherBound - lowerBound) < precision)) {
            double left = higherBound - ((higherBound - lowerBound) / GOLDEN_RATIO);
            double right = lowerBound + ((higherBound - lowerBound) / GOLDEN_RATIO);

            double fLeft = f.apply(left);
            counter++;
            double fRight = f.apply(right);
            counter++;
            if (fLeft >= fRight) {
                lowerBound = left;
            } else {
                higherBound = right;
            }
        }

        double result = f.apply((lowerBound + higherBound) / 2);
        return Double.isNaN(result) || Double.isInfinite(result) ? precision : result;
    }

    public double argmin(DoubleFunction<Double> f, double lowerBound, double higherBound, double precision) {
        while (!(Math.abs(higherBound - lowerBound) < precision)) {
            double left = higherBound - ((higherBound - lowerBound) / GOLDEN_RATIO);
            double right = lowerBound + ((higherBound - lowerBound) / GOLDEN_RATIO);

            double fLeft = f.apply(left);
            double fRight = f.apply(right);

            if (fLeft >= fRight) {
                lowerBound = left;
            } else {
                higherBound = right;
            }
        }
        return (lowerBound + higherBound) / 2;
    }
}
