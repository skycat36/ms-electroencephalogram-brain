package com.brain.service.layer;

import com.brain.util.function.CoefficientUtils;

import java.util.function.Function;

public class SolvingInverseProblem {

    public static double localizationNeuralSource(double expU, int n, double teta, double fi, double teta0, double fi0, double mX, double mY, double mZ, double rD) throws Exception {

        double result = (- mX * CoefficientUtils.coefficientSum(n, rD, (L) -> CoefficientUtils.coefficientAL(teta, fi, teta0, fi0, L)) -
                        mY * CoefficientUtils.coefficientSum(n, rD, (L) -> CoefficientUtils.coefficientCL(teta, fi, teta0, fi0, L)) -
                        mZ * CoefficientUtils.coefficientSum(n, rD, (L) -> L * CoefficientUtils.coefficientBL(teta, fi, teta0, fi0, L)));

        return Math.pow(expU + result, 2);
    }


    public static double derivativeLocalizationNeuralSource(int n, double teta, double fi, double teta0, double fi0, double mX, double mY, double mZ, double R1, double rD) throws Exception {

        Function<Integer, Double> subFunction = (L) -> (-L - 1) / Math.pow(R1, L + 2);

        double result =  (mX * CoefficientUtils.coefficientSum(n, rD, (L) -> subFunction.apply(L) * CoefficientUtils.coefficientAL(teta, fi, teta0, fi0, L)) +
                mY * CoefficientUtils.coefficientSum(n, rD, (L) -> subFunction.apply(L) * CoefficientUtils.coefficientCL(teta, fi, teta0, fi0, L)) +
                mZ * CoefficientUtils.coefficientSum(n, rD, (L) -> subFunction.apply(L) * L * CoefficientUtils.coefficientBL(teta, fi, teta0, fi0, L)));
        return Math.pow(result, 2);
    }
}