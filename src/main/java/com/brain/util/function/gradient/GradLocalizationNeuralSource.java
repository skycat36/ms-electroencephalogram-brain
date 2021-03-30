package com.brain.util.function.gradient;

import com.brain.util.function.CoefficientUtils;

public class GradLocalizationNeuralSource {

    public static double dFMx(double expU, int n, double teta, double fi, double teta0, double fi0, double mX, double mY, double mZ, double rD) {
        double subFunction = - CoefficientUtils.coefficientSum(n, rD, (L) -> CoefficientUtils.coefficientAL(teta, fi, teta0, fi0, L));

        return CoefficientUtils.coefficientEL(expU, n, teta, fi, teta0, fi0, mX, mY, mZ, rD) * subFunction;
    }

    public static double dFMy(double expU, int n, double teta, double fi, double teta0, double fi0, double mX, double mY, double mZ, double rD) {
        double subFunction = - CoefficientUtils.coefficientSum(n, rD, (L) -> CoefficientUtils.coefficientCL(teta, fi, teta0, fi0, L));

        return CoefficientUtils.coefficientEL(expU, n, teta, fi, teta0, fi0, mX, mY, mZ, rD) * subFunction;
    }

    public static double dFMz(double expU, int n, double teta, double fi, double teta0, double fi0, double mX, double mY, double mZ, double rD) {
        double subFunction = - CoefficientUtils.coefficientSum(n, rD, (L) -> CoefficientUtils.coefficientBL(teta, fi, teta0, fi0, L));

        return CoefficientUtils.coefficientEL(expU, n, teta, fi, teta0, fi0, mX, mY, mZ, rD) * subFunction;
    }

    //TODO  CoefficientUtils.coefficientSum здесь сумма начинается с 2
    public static double dFrd(double expU, int n, double teta, double fi, double teta0, double fi0, double mX, double mY, double mZ, double rD) {
        double subFunction =
                ( - mX * CoefficientUtils.coefficientSum(n,1. / rD, (L) -> (L - 1) * CoefficientUtils.coefficientAL(teta, fi, teta0, fi0, L)) -
                mY * CoefficientUtils.coefficientSum(n, 1. / rD, (L) -> (L - 1) * CoefficientUtils.coefficientCL(teta, fi, teta0, fi0, L)) -
                mZ * CoefficientUtils.coefficientSum(n, 1. / rD, (L) -> (L - 1) * CoefficientUtils.coefficientBL(teta, fi, teta0, fi0, L)));

        return CoefficientUtils.coefficientEL(expU, n, teta, fi, teta0, fi0, mX, mY, mZ, rD) * subFunction;
    }
}
