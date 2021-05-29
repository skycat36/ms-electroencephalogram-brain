package com.brain.util.function.gradient;

import com.brain.util.function.CoefficientUtils;
import com.brain.util.minimization.point.Triple;

import java.util.List;

public class GradLocalizationNeuralSource {

    public static double dFMx(List<Triple<Double, Double, Double>> expU, int n, double teta0, double fi0, double mX, double mY, double mZ, double rD) {
        double result = 0;
        for (Triple<Double, Double, Double> triple: expU) {
            result += CoefficientUtils.coefficientSum(n, rD, (L) ->
                    CoefficientUtils.coefficientAL(triple.getKey(), triple.getValue(), teta0, fi0, L)) *
                    CoefficientUtils.coefficientEL(triple.getResult(), n, triple.getKey(), triple.getValue(), teta0, fi0, mX, mY, mZ, rD);
        }
        return - result;
    }

    public static double d2FMx(int n, double teta, double fi, double teta0, double fi0, double rD) {
        double subFunction = CoefficientUtils.coefficientSum(n, rD, (L) -> CoefficientUtils.coefficientAL(teta, fi, teta0, fi0, L));

        return 2 * Math.pow(subFunction, 2);
    }

    public static double dFMxMy(int n, double teta, double fi, double teta0, double fi0, double rD) {
        double subFunctionAl = CoefficientUtils.coefficientSum(n, rD, (L) -> CoefficientUtils.coefficientAL(teta, fi, teta0, fi0, L));

        double subFunctionCl = CoefficientUtils.coefficientSum(n, rD, (L) -> CoefficientUtils.coefficientCL(teta, fi, teta0, fi0, L));

        return 2 * subFunctionAl * subFunctionCl;
    }

    public static double dFMxMz(int n, double teta, double fi, double teta0, double fi0, double rD) {
        double subFunctionAl = CoefficientUtils.coefficientSum(n, rD, (L) -> CoefficientUtils.coefficientAL(teta, fi, teta0, fi0, L));

        double subFunctionBl = CoefficientUtils.coefficientSum(n, rD, (L) -> L * CoefficientUtils.coefficientBL(teta, fi, teta0, fi0, L));

        return 2 * subFunctionAl * subFunctionBl;
    }

    public static double dFMxRd(double expU, int n, double teta, double fi, double teta0, double fi0, double mX, double mY, double mZ, double rD) {
        return dFRdMx(expU, n, teta, fi, teta0, fi0, mX, mY, mZ, rD);
    }

    public static double dFMy(List<Triple<Double, Double, Double>> expU, int n, double teta0, double fi0, double mX, double mY, double mZ, double rD) {
        double result = 0;
        for (Triple<Double, Double, Double> triple: expU) {
            result += CoefficientUtils.coefficientSum(n, rD, (L) ->
                    CoefficientUtils.coefficientCL(triple.getKey(), triple.getValue(), teta0, fi0, L)) *
                    CoefficientUtils.coefficientEL(triple.getResult(), n, triple.getKey(), triple.getValue(), teta0, fi0, mX, mY, mZ, rD);
        }
        return - result;
    }

    public static double d2FMy(int n, double teta, double fi, double teta0, double fi0, double rD) {
        double subFunction = CoefficientUtils.coefficientSum(n, rD, (L) -> CoefficientUtils.coefficientCL(teta, fi, teta0, fi0, L));

        return 2 * Math.pow(subFunction, 2);
    }

    public static double dFMyMx(int n, double teta, double fi, double teta0, double fi0, double rD) {
        return dFMxMy(n, teta, fi, teta0, fi0, rD);
    }

    public static double dFMyMz(int n, double teta, double fi, double teta0, double fi0, double rD) {
        double subFunctionCl = CoefficientUtils.coefficientSum(n, rD, (L) -> CoefficientUtils.coefficientCL(teta, fi, teta0, fi0, L));

        double subFunctionBl = CoefficientUtils.coefficientSum(n, rD, (L) -> L * CoefficientUtils.coefficientBL(teta, fi, teta0, fi0, L));

        return 2 * subFunctionCl * subFunctionBl;
    }

    public static double dFMyRd(double expU, int n, double teta, double fi, double teta0, double fi0, double mX, double mY, double mZ, double rD) {
        return dFRdMy(expU, n, teta, fi, teta0, fi0, mX, mY, mZ, rD);
    }

    public static double dFMz(List<Triple<Double, Double, Double>> expU, int n, double teta0, double fi0, double mX, double mY, double mZ, double rD) {
        double result = 0;
        for (Triple<Double, Double, Double> triple: expU) {
            result += CoefficientUtils.coefficientSum(n, rD, (L) ->
                    L * CoefficientUtils.coefficientBL(triple.getKey(), triple.getValue(), teta0, fi0, L)) *
                    CoefficientUtils.coefficientEL(triple.getResult(), n, triple.getKey(), triple.getValue(), teta0, fi0, mX, mY, mZ, rD);
        }
        return - result;
    }

    public static double d2FMz(int n, double teta, double fi, double teta0, double fi0, double rD) {
        double subFunction = CoefficientUtils.coefficientSum(n, rD, (L) -> L * CoefficientUtils.coefficientBL(teta, fi, teta0, fi0, L));

        return 2 * Math.pow(subFunction, 2);
    }

    public static double dFMzMx(int n, double teta, double fi, double teta0, double fi0, double rD) {
        return dFMxMz(n, teta, fi, teta0, fi0, rD);
    }

    public static double dFMzMy(int n, double teta, double fi, double teta0, double fi0, double rD) {
        return dFMyMz(n, teta, fi, teta0, fi0, rD);
    }

    public static double dFMzRd(double expU, int n, double teta, double fi, double teta0, double fi0, double mX, double mY, double mZ, double rD) {
        return dFRdMz(expU, n, teta, fi, teta0, fi0, mX, mY, mZ, rD);
    }

    public static double dFRd(List<Triple<Double, Double, Double>> expU, int n, double teta0, double fi0, double mX, double mY, double mZ, double rD) {
        double result = 0;
        for (Triple<Double, Double, Double> triple: expU) {
            double temp =
                    ( - mX * CoefficientUtils.coefficientSum(2, n, rD, (L) -> (1. / rD) * (L - 1) *     CoefficientUtils.coefficientAL(triple.getKey(), triple.getValue(), teta0, fi0, L)) -
                            mY * CoefficientUtils.coefficientSum(2, n, rD, (L) -> (1. / rD) * (L - 1) *     CoefficientUtils.coefficientCL(triple.getKey(), triple.getValue(), teta0, fi0, L)) -
                            mZ * CoefficientUtils.coefficientSum(2, n, rD, (L) -> (1. / rD) * (L - 1) * L * CoefficientUtils.coefficientBL(triple.getKey(), triple.getValue(), teta0, fi0, L)));

            result += temp *
                    CoefficientUtils.coefficientEL(triple.getResult(), n, triple.getKey(), triple.getValue(), teta0, fi0, mX, mY, mZ, rD);
        }

        return result;
    }

    private static double dERd(int n, double teta, double fi, double teta0, double fi0, double mX, double mY, double mZ, double rD){
        return -2 * (mX * CoefficientUtils.coefficientSum(2, n, rD, (L) -> (1. / rD) *     (L - 1) *  CoefficientUtils.coefficientAL(teta, fi, teta0, fi0, L)) +
                     mY * CoefficientUtils.coefficientSum(2, n, rD, (L) -> (1. / rD) *     (L - 1) * CoefficientUtils.coefficientCL(teta, fi, teta0, fi0, L)) +
                     mZ * CoefficientUtils.coefficientSum(2, n, rD, (L) -> (1. / rD) * L * (L - 1) * CoefficientUtils.coefficientBL(teta, fi, teta0, fi0, L)));
    }

    private static double variableG(int n, double teta, double fi, double teta0, double fi0, double mX, double mY, double mZ, double rD){
        return Math.abs(dERd(n, teta, fi, teta0, fi0, mX, mY, mZ, rD) / 2);
    }

    public static double d2FRd(double expU, int n, double teta, double fi, double teta0, double fi0, double mX, double mY, double mZ, double rD) {
        double dGRd =
                -2 * (mX * CoefficientUtils.coefficientSum(3, n, rD, (L) -> (1. / (rD * rD)) * (L - 1) * (L - 2) *     CoefficientUtils.coefficientAL(teta, fi, teta0, fi0, L)) +
                      mY * CoefficientUtils.coefficientSum(3, n, rD, (L) -> (1. / (rD * rD)) * (L - 1) * (L - 2) *     CoefficientUtils.coefficientCL(teta, fi, teta0, fi0, L)) +
                      mZ * CoefficientUtils.coefficientSum(3, n, rD, (L) -> (1. / (rD * rD)) * (L - 1) * (L - 2) * L * CoefficientUtils.coefficientBL(teta, fi, teta0, fi0, L)));

        double El = CoefficientUtils.coefficientEL(expU, n, teta, fi, teta0, fi0, mX, mY, mZ, rD);
        double varG = variableG(n, teta, fi, teta0, fi0, mX, mY, mZ, rD);

        return  -2 * Math.pow(varG, 2) + El * dGRd;
    }

    public static double dFRdMx(double expU, int n, double teta, double fi, double teta0, double fi0, double mX, double mY, double mZ, double rD) {
        double El = CoefficientUtils.coefficientEL(expU, n, teta, fi, teta0, fi0, mX, mY, mZ, rD);
        double varG = variableG(n, teta, fi, teta0, fi0, mX, mY, mZ, rD);

        double dEdMx = -2 * CoefficientUtils.coefficientSum(n, rD, (L) -> CoefficientUtils.coefficientAL(teta, fi, teta0, fi0, L));
        double dGMx =  -2 * CoefficientUtils.coefficientSum(2, n, rD, (L) -> (1. / rD) * (L - 1) * CoefficientUtils.coefficientAL(teta, fi, teta0, fi0, L));

        return dEdMx * varG + dGMx * El;
    }

    public static double dFRdMy(double expU, int n, double teta, double fi, double teta0, double fi0, double mX, double mY, double mZ, double rD) {
        double El = CoefficientUtils.coefficientEL(expU, n, teta, fi, teta0, fi0, mX, mY, mZ, rD);
        double varG = variableG(n, teta, fi, teta0, fi0, mX, mY, mZ, rD);

        double dEdMy = -2 * CoefficientUtils.coefficientSum(n, rD, (L) -> CoefficientUtils.coefficientCL(teta, fi, teta0, fi0, L));
        double dGMy =  -2 * CoefficientUtils.coefficientSum(2, n, rD, (L) -> (1. / rD) * (L - 1) * CoefficientUtils.coefficientCL(teta, fi, teta0, fi0, L));

        return dEdMy * varG + dGMy * El;
    }

    public static double dFRdMz(double expU, int n, double teta, double fi, double teta0, double fi0, double mX, double mY, double mZ, double rD) {
        double El = CoefficientUtils.coefficientEL(expU, n, teta, fi, teta0, fi0, mX, mY, mZ, rD);
        double varG = variableG(n, teta, fi, teta0, fi0, mX, mY, mZ, rD);

        double dEdMz = -2 * CoefficientUtils.coefficientSum(n, rD, (L) -> L * CoefficientUtils.coefficientBL(teta, fi, teta0, fi0, L));
        double dGMz =  -2 * CoefficientUtils.coefficientSum(2, n, rD, (L) -> (1. / rD) * L * (L - 1) * CoefficientUtils.coefficientBL(teta, fi, teta0, fi0, L));

        return dEdMz * varG + dGMz * El;
    }
}
