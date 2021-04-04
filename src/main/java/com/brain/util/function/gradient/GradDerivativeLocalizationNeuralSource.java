package com.brain.util.function.gradient;

import com.brain.util.function.CoefficientUtils;

import java.util.function.Function;

public class GradDerivativeLocalizationNeuralSource {

    public static double dFMx(int n, double teta, double fi, double teta0, double fi0, double mX, double mY, double mZ, double R1, double rD) throws Exception {
        Function<Integer, Double> subFunction = (L) -> (-L - 1) / Math.pow(R1, L + 2);

        return CoefficientUtils.coefficientTL(n, teta, fi, teta0, fi0, mX, mY, mZ, R1, rD) *
                CoefficientUtils.coefficientSum(n, rD, (L) -> subFunction.apply(L) * CoefficientUtils.coefficientAL(teta, fi, teta0, fi0, L));
    }

    public static double dFMy(int n, double teta, double fi, double teta0, double fi0, double mX, double mY, double mZ, double R1, double rD) throws Exception {
        Function<Integer, Double> subFunction = (L) -> (-L - 1) / Math.pow(R1, L + 2);

        return CoefficientUtils.coefficientTL(n, teta, fi, teta0, fi0, mX, mY, mZ, R1, rD) *
                CoefficientUtils.coefficientSum(n, rD, (L) -> subFunction.apply(L) * CoefficientUtils.coefficientCL(teta, fi, teta0, fi0, L));
    }

    public static double dFMz(int n, double teta, double fi, double teta0, double fi0, double mX, double mY, double mZ, double R1, double rD) throws Exception {
        Function<Integer, Double> subFunction = (L) -> (-L - 1) / Math.pow(R1, L + 2);

        return CoefficientUtils.coefficientTL(n, teta, fi, teta0, fi0, mX, mY, mZ, R1, rD) *
                CoefficientUtils.coefficientSum(n, rD, (L) -> subFunction.apply(L) * L * CoefficientUtils.coefficientBL(teta, fi, teta0, fi0, L));
    }

    public static double dFrd(int n, double teta, double fi, double teta0, double fi0, double mX, double mY, double mZ, double R1, double rD) throws Exception {
        Function<Integer, Double> subFunction = (L) -> (1 - Math.pow(L, 2)) / Math.pow(R1, L + 2);

        double result =
               (mX * CoefficientUtils.coefficientSum(2, n, 1. / rD, (L) -> subFunction.apply(L) * CoefficientUtils.coefficientAL(teta, fi, teta0, fi0, L)) +
                mY * CoefficientUtils.coefficientSum(2, n, 1. / rD, (L) -> subFunction.apply(L) * CoefficientUtils.coefficientCL(teta, fi, teta0, fi0, L)) +
                mZ * CoefficientUtils.coefficientSum(2, n, 1. / rD, (L) -> subFunction.apply(L) * L * CoefficientUtils.coefficientBL(teta, fi, teta0, fi0, L)));

        return CoefficientUtils.coefficientTL(n, teta, fi, teta0, fi0, mX, mY, mZ, R1, rD) * result;
    }

}
