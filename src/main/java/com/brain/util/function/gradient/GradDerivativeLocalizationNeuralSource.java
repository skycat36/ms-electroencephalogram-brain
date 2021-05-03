package com.brain.util.function.gradient;

import com.brain.util.function.CoefficientUtils;

import java.util.function.Function;

public class GradDerivativeLocalizationNeuralSource {

    public static double dFMx(int n, double teta, double fi, double teta0, double fi0, double mX, double mY, double mZ, double R1, double rD) throws RuntimeException {
        return 2 * CoefficientUtils.coefficientTL(n, teta, fi, teta0, fi0, mX, mY, mZ, R1, rD) *
                dTLdMx(n, teta, fi, teta0, fi0, R1, rD);
    }

    private static double dTLdMx(int n, double teta, double fi, double teta0, double fi0, double R1, double rD) {
        Function<Integer, Double> subFunction = (L) -> (-L - 1) / R1;
        return CoefficientUtils.coefficientSum(n, rD, (L) -> subFunction.apply(L) * CoefficientUtils.coefficientAL(teta, fi, teta0, fi0, L));
    }

    public static double d2FMx(int n, double teta, double fi, double teta0, double fi0, double R1, double rD) {
        return 2 * Math.pow(dTLdMx(n, teta, fi, teta0, fi0, R1, rD), 2);
    }

    public static double dFMxMy(int n, double teta, double fi, double teta0, double fi0, double R1, double rD) {
        return 2 * dTLdMx(n, teta, fi, teta0, fi0, R1, rD) * dTLdMy(n, teta, fi, teta0, fi0, R1, rD);
    }

    public static double dFMxMz(int n, double teta, double fi, double teta0, double fi0, double R1, double rD) {
        return 2 * dTLdMx(n, teta, fi, teta0, fi0, R1, rD) * dTLdMz(n, teta, fi, teta0, fi0, R1, rD);
    }

    public static double dFMxRd(int n, double teta, double fi, double teta0, double fi0, double R1, double rD) {
        return dFRdMx(n, teta, fi, teta0, fi0, R1, rD);
    }


    public static double dFMy(int n, double teta, double fi, double teta0, double fi0, double mX, double mY, double mZ, double R1, double rD) {
        return 2 * CoefficientUtils.coefficientTL(n, teta, fi, teta0, fi0, mX, mY, mZ, R1, rD) *
                dTLdMy(n, teta, fi, teta0, fi0, R1, rD);
    }

    private static double dTLdMy(int n, double teta, double fi, double teta0, double fi0, double R1, double rD) {
        Function<Integer, Double> subFunction = (L) -> (-L - 1) / R1;
        return CoefficientUtils.coefficientSum(n, rD, (L) -> subFunction.apply(L) * CoefficientUtils.coefficientCL(teta, fi, teta0, fi0, L));
    }

    public static double d2FMy(int n, double teta, double fi, double teta0, double fi0, double R1, double rD) {
        return 2 * Math.pow(dTLdMy(n, teta, fi, teta0, fi0, R1, rD), 2);
    }

    public static double dFMyMx(int n, double teta, double fi, double teta0, double fi0, double R1, double rD) {
        return dFMxMy(n, teta, fi, teta0, fi0, R1, rD);
    }

    public static double dFMyMz(int n, double teta, double fi, double teta0, double fi0, double R1, double rD) {
        return 2 * dTLdMy(n, teta, fi, teta0, fi0, R1, rD) * dTLdMz(n, teta, fi, teta0, fi0, R1, rD);
    }

    public static double dFMyRd(double expU, int n, double teta, double fi, double teta0, double fi0, double R1, double rD) {
        return dFRdMy(n, teta, fi, teta0, fi0, R1, rD);
    }


    public static double dFMz(int n, double teta, double fi, double teta0, double fi0, double mX, double mY, double mZ, double R1, double rD) {
        return 2 * CoefficientUtils.coefficientTL(n, teta, fi, teta0, fi0, mX, mY, mZ, R1, rD) *
                dTLdMz(n, teta, fi, teta0, fi0, R1, rD);
    }

    private static double dTLdMz(int n, double teta, double fi, double teta0, double fi0, double R1, double rD) {
        Function<Integer, Double> subFunction = (L) -> ((-L - 1) * L) / R1;
        return CoefficientUtils.coefficientSum(n, rD, (L) -> subFunction.apply(L) * CoefficientUtils.coefficientBL(teta, fi, teta0, fi0, L));
    }

    public static double d2FMz(int n, double teta, double fi, double teta0, double fi0, double R1, double rD) {
        return 2 * Math.pow(dTLdMz(n, teta, fi, teta0, fi0, R1, rD), 2);
    }

    public static double dFMzMx(int n, double teta, double fi, double teta0, double fi0, double R1, double rD) {
        return dFMxMz(n, teta, fi, teta0, fi0, R1, rD);
    }

    public static double dFMzMy(int n, double teta, double fi, double teta0, double fi0, double R1, double rD) {
        return dFMyMz(n, teta, fi, teta0, fi0, R1, rD);
    }

    public static double dFMzRd(int n, double teta, double fi, double teta0, double fi0, double R1, double rD) {
        return dFRdMz(n, teta, fi, teta0, fi0, R1, rD);
    }


    public static double dFrd(int n, double teta, double fi, double teta0, double fi0, double mX, double mY, double mZ, double R1, double rD) {
        return 2 * CoefficientUtils.coefficientTL(n, teta, fi, teta0, fi0, mX, mY, mZ, R1, rD) *
                dTLdRd(n, teta, fi, teta0, fi0, mX, mY, mZ, R1, rD);
    }

    private static double dTLdRd(int n, double teta, double fi, double teta0, double fi0, double mX, double mY, double mZ, double R1, double rD) {
        Function<Integer, Double> subFunction = (L) -> ((L * L)- 1) / R1;

        double result =
                (mX * CoefficientUtils.coefficientSum(2, n, 1. / rD, (L) -> subFunction.apply(L) *     CoefficientUtils.coefficientAL(teta, fi, teta0, fi0, L)) +
                 mY * CoefficientUtils.coefficientSum(2, n, 1. / rD, (L) -> subFunction.apply(L) *     CoefficientUtils.coefficientCL(teta, fi, teta0, fi0, L)) +
                 mZ * CoefficientUtils.coefficientSum(2, n, 1. / rD, (L) -> subFunction.apply(L) * L * CoefficientUtils.coefficientBL(teta, fi, teta0, fi0, L)));

        return -result;
    }

    public static double d2FRd(double expU, int n, double teta, double fi, double teta0, double fi0, double mX, double mY, double mZ, double R1, double rD) {
        Function<Integer, Double> subFunction = (L) -> (((L * L) - 1) * (L - 2)) / R1;

        double dTLd2Rd = -(mX * CoefficientUtils.coefficientSum(3, n, 1. / (rD * rD), (L) -> subFunction.apply(L) *     CoefficientUtils.coefficientAL(teta, fi, teta0, fi0, L)) +
                           mY * CoefficientUtils.coefficientSum(3, n, 1. / (rD * rD), (L) -> subFunction.apply(L) *     CoefficientUtils.coefficientCL(teta, fi, teta0, fi0, L)) +
                           mZ * CoefficientUtils.coefficientSum(3, n, 1. / (rD * rD), (L) -> subFunction.apply(L) * L * CoefficientUtils.coefficientBL(teta, fi, teta0, fi0, L)));

        return  2 * Math.pow(dTLdRd(n, teta, fi, teta0, fi0, mX, mY, mZ, R1, rD), 2) +
                CoefficientUtils.coefficientTL(n, teta, fi, teta0, fi0, mX, mY, mZ, R1, rD) * dTLd2Rd;
    }

    public static double dFRdMx(int n, double teta, double fi, double teta0, double fi0, double R1, double rD) {
        Function<Integer, Double> subFunction = (L) -> ((L * L) - 1) / R1;
        double dTLdRdMx = - CoefficientUtils.coefficientSum(2, n, 1. / rD, (L) -> subFunction.apply(L) * CoefficientUtils.coefficientAL(teta, fi, teta0, fi0, L));

        return 2 * dTLdMx(n, teta, fi, teta0, fi0, R1, rD) + dTLdRdMx;
    }

    public static double dFRdMy(int n, double teta, double fi, double teta0, double fi0, double R1, double rD) {
        Function<Integer, Double> subFunction = (L) -> ((L * L) - 1) / R1;
        double dTLdRdMy = - CoefficientUtils.coefficientSum(2, n, 1. / rD, (L) -> subFunction.apply(L) * CoefficientUtils.coefficientCL(teta, fi, teta0, fi0, L));

        return 2 * dTLdMy(n, teta, fi, teta0, fi0, R1, rD) + dTLdRdMy;
    }

    public static double dFRdMz(int n, double teta, double fi, double teta0, double fi0, double R1, double rD) {
        Function<Integer, Double> subFunction = (L) -> (((L * L) - 1) * L) / R1;
        double dTLdRdMz = - CoefficientUtils.coefficientSum(2, n, 1. / rD, (L) -> subFunction.apply(L) * CoefficientUtils.coefficientBL(teta, fi, teta0, fi0, L));

        return 2 * dTLdMz(n, teta, fi, teta0, fi0, R1, rD) + dTLdRdMz;
    }

}
