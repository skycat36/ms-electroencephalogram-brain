package com.brain.util.function.gradient;

import com.brain.util.function.CoefficientUtils;

import java.util.function.Function;

public class GradDerivativeLocalizationNeuralSource {

    public static double dFMx(double expU, int n, double teta, double fi, double teta0, double fi0, double mX, double mY, double mZ, double R1, double rD) throws Exception {
        Function<Integer, Double> subFunction = (L) -> (-L - 1) / Math.pow(R1, L + 2);

        return CoefficientUtils.coefficientTL(n, teta, fi, teta0, fi0, mX, mY, mZ, R1, rD) *
                CoefficientUtils.coefficientSum(n, rD, (L) -> subFunction.apply(L) * CoefficientUtils.coefficientAL(teta, fi, teta0, fi0, L));
    }

    public static double dFMy(double expU, int n, double teta, double fi, double teta0, double fi0, double mX, double mY, double mZ, double R1, double rD) throws Exception {
        Function<Integer, Double> subFunction = (L) -> (-L - 1) / Math.pow(R1, L + 2);

        return CoefficientUtils.coefficientTL(n, teta, fi, teta0, fi0, mX, mY, mZ, R1, rD) *
                CoefficientUtils.coefficientSum(n, rD, (L) -> subFunction.apply(L) * CoefficientUtils.coefficientCL(teta, fi, teta0, fi0, L));
    }

    public static double dFMz(double expU, int n, double teta, double fi, double teta0, double fi0, double mX, double mY, double mZ, double R1, double rD) throws Exception {
        Function<Integer, Double> subFunction = (L) -> (-L - 1) / Math.pow(R1, L + 2);

        return CoefficientUtils.coefficientTL(n, teta, fi, teta0, fi0, mX, mY, mZ, R1, rD) *
                CoefficientUtils.coefficientSum(n, rD, (L) -> subFunction.apply(L) * L * CoefficientUtils.coefficientBL(teta, fi, teta0, fi0, L));
    }

    //TODO  CoefficientUtils.coefficientSum здесь сумма начинается с 2
    public static double dFrd(double expU, int n, double teta, double fi, double teta0, double fi0, double mX, double mY, double mZ, double R1, double rD) {
        Function<Integer, Double> subFunction = (L) -> (-L - 1) / Math.pow(R1, L + 2);

        double result =
               (mX * CoefficientUtils.coefficientSum(n, 1. / rD, (L) -> subFunction.apply(L) * CoefficientUtils.coefficientAL(teta, fi, teta0, fi0, L)) +
                mY * CoefficientUtils.coefficientSum(n, 1. / rD, (L) -> subFunction.apply(L) * CoefficientUtils.coefficientCL(teta, fi, teta0, fi0, L)) +
                mZ * CoefficientUtils.coefficientSum(n, 1. / rD, (L) -> subFunction.apply(L) * L * CoefficientUtils.coefficientBL(teta, fi, teta0, fi0, L)));

        return CoefficientUtils.coefficientEL(expU, n, teta, fi, teta0, fi0, mX, mY, mZ, rD) * subFunction;
    }

}
