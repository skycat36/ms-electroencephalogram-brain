package com.brain.util.function.gradient;

import com.brain.util.function.CoefficientUtils;
import com.brain.util.minimization.point.ExpPoint;

import java.util.List;

public class GradLocalizationNeuralSource {

    public static double dFMx(List<ExpPoint<Double>> expU, int n, double teta0, double fi0, double mX, double mY, double mZ, double rD) {
        return - expU.stream()
                .mapToDouble(tr ->
                        CoefficientUtils.coefficientSum(n, rD, (L) ->
                                CoefficientUtils.coefficientAL(tr.getPointX(), tr.getPointY(), teta0, fi0, L)) *
                                CoefficientUtils.coefficientEL(tr.getResult(), n, tr.getPointX(), tr.getPointY(), teta0, fi0, mX, mY, mZ, rD)
                ).sum();
    }

    public static double d2FMx(List<ExpPoint<Double>> expU, int n, double teta0, double fi0, double rD) {
        return 2 * expU.stream()
                .mapToDouble(tr -> Math.pow(
                        CoefficientUtils.coefficientSum(n, rD, (L) -> CoefficientUtils.coefficientAL(tr.getPointX(), tr.getPointY(), teta0, fi0, L)), 2)
                ).sum();
    }

    public static double dFMxMy(List<ExpPoint<Double>> expU, int n, double teta0, double fi0, double rD) {
        return 2 * expU.stream()
                .mapToDouble(tr ->
                        CoefficientUtils.coefficientSum(n, rD, (L) -> CoefficientUtils.coefficientAL(tr.getPointX(), tr.getPointY(), teta0, fi0, L)) *
                        CoefficientUtils.coefficientSum(n, rD, (L) -> CoefficientUtils.coefficientCL(tr.getPointX(), tr.getPointY(), teta0, fi0, L))
                ).sum();
    }

    public static double dFMxMz(List<ExpPoint<Double>> expU, int n, double teta0, double fi0, double rD) {
        return 2 * expU.stream()
                .mapToDouble(tr ->
                        CoefficientUtils.coefficientSum(n, rD, (L) ->     CoefficientUtils.coefficientAL(tr.getPointX(), tr.getPointY(), teta0, fi0, L)) *
                        CoefficientUtils.coefficientSum(n, rD, (L) -> L * CoefficientUtils.coefficientBL(tr.getPointX(), tr.getPointY(), teta0, fi0, L))
                ).sum();
    }

    public static double dFMxRd(List<ExpPoint<Double>> expU, int n, double teta0, double fi0, double mX, double mY, double mZ, double rD) {
        return dFRdMx(expU, n, teta0, fi0, mX, mY, mZ, rD);
    }

    public static double dFMy(List<ExpPoint<Double>> expU, int n, double teta0, double fi0, double mX, double mY, double mZ, double rD) {
        return - expU.stream()
                .mapToDouble(tr ->
                        CoefficientUtils.coefficientSum(n, rD, (L) ->
                                CoefficientUtils.coefficientCL(tr.getPointX(), tr.getPointY(), teta0, fi0, L)) *
                                CoefficientUtils.coefficientEL(tr.getResult(), n, tr.getPointX(), tr.getPointY(), teta0, fi0, mX, mY, mZ, rD)
                ).sum();
    }

    public static double d2FMy(List<ExpPoint<Double>> expU, int n, double teta0, double fi0, double rD) {
        return 2 * expU.stream()
                .mapToDouble(tr -> Math.pow(
                        CoefficientUtils.coefficientSum(n, rD, (L) -> CoefficientUtils.coefficientCL(tr.getPointX(), tr.getPointY(), teta0, fi0, L)), 2)
                ).sum();
    }

    public static double dFMyMx(List<ExpPoint<Double>> expU, int n, double teta0, double fi0, double rD) {
        return dFMxMy(expU, n, teta0, fi0, rD);
    }

    public static double dFMyMz(List<ExpPoint<Double>> expU, int n, double teta0, double fi0, double rD) {
        return 2 * expU.stream()
                .mapToDouble(tr ->
                        CoefficientUtils.coefficientSum(n, rD, (L) -> CoefficientUtils.coefficientCL(tr.getPointX(), tr.getPointY(), teta0, fi0, L)) *
                        CoefficientUtils.coefficientSum(n, rD, (L) -> L * CoefficientUtils.coefficientBL(tr.getPointX(), tr.getPointY(), teta0, fi0, L))
                ).sum();
    }

    public static double dFMyRd(List<ExpPoint<Double>> expU, int n, double teta0, double fi0, double mX, double mY, double mZ, double rD) {
        return dFRdMy(expU, n, teta0, fi0, mX, mY, mZ, rD);
    }

    public static double dFMz(List<ExpPoint<Double>> expU, int n, double teta0, double fi0, double mX, double mY, double mZ, double rD) {
        return - expU.stream()
                .mapToDouble(tr ->
                        CoefficientUtils.coefficientSum(n, rD, (L) -> L * CoefficientUtils.coefficientBL(tr.getPointX(), tr.getPointY(), teta0, fi0, L)) *
                        CoefficientUtils.coefficientEL(tr.getResult(), n, tr.getPointX(), tr.getPointY(), teta0, fi0, mX, mY, mZ, rD)
                ).sum();
    }

    public static double d2FMz(List<ExpPoint<Double>> expU, int n, double teta0, double fi0, double rD) {
        return 2 * expU.stream()
                .mapToDouble(tr -> Math.pow(
                        CoefficientUtils.coefficientSum(n, rD, (L) -> L * CoefficientUtils.coefficientBL(tr.getPointX(), tr.getPointY(), teta0, fi0, L)), 2)
                ).sum();
    }

    public static double dFMzMx(List<ExpPoint<Double>> expU, int n, double teta0, double fi0, double rD) {
        return dFMxMz(expU, n, teta0, fi0, rD);
    }

    public static double dFMzMy(List<ExpPoint<Double>> expU, int n, double teta0, double fi0, double rD) {
        return dFMyMz(expU, n, teta0, fi0, rD);
    }

    public static double dFMzRd(List<ExpPoint<Double>> expU, int n, double teta0, double fi0, double mX, double mY, double mZ, double rD) {
        return dFRdMz(expU, n, teta0, fi0, mX, mY, mZ, rD);
    }

    public static double dFRd(List<ExpPoint<Double>> expU, int n, double teta0, double fi0, double mX, double mY, double mZ, double rD) {
        return expU.stream()
                .mapToDouble(tr -> {
                    double temp =
                            ( - mX * CoefficientUtils.coefficientSum(2, n, rD, (L) -> (1. / rD) * (L - 1) *     CoefficientUtils.coefficientAL(tr.getPointX(), tr.getPointY(), teta0, fi0, L)) -
                                mY * CoefficientUtils.coefficientSum(2, n, rD, (L) -> (1. / rD) * (L - 1) *     CoefficientUtils.coefficientCL(tr.getPointX(), tr.getPointY(), teta0, fi0, L)) -
                                mZ * CoefficientUtils.coefficientSum(2, n, rD, (L) -> (1. / rD) * (L - 1) * L * CoefficientUtils.coefficientBL(tr.getPointX(), tr.getPointY(), teta0, fi0, L)));

                    return temp * CoefficientUtils.coefficientEL(tr.getResult(), n, tr.getPointX(), tr.getPointY(), teta0, fi0, mX, mY, mZ, rD);
                }).sum();
    }

    private static double dERd(List<ExpPoint<Double>> expU, int n, double teta0, double fi0, double mX, double mY, double mZ, double rD){
        return -2 * expU.stream()
                .mapToDouble(tr ->
                        mX * CoefficientUtils.coefficientSum(2, n, rD, (L) -> (1. / rD) *     (L - 1) * CoefficientUtils.coefficientAL(tr.getPointX(), tr.getPointY(), teta0, fi0, L)) +
                        mY * CoefficientUtils.coefficientSum(2, n, rD, (L) -> (1. / rD) *     (L - 1) * CoefficientUtils.coefficientCL(tr.getPointX(), tr.getPointY(), teta0, fi0, L)) +
                        mZ * CoefficientUtils.coefficientSum(2, n, rD, (L) -> (1. / rD) * L * (L - 1) * CoefficientUtils.coefficientBL(tr.getPointX(), tr.getPointY(), teta0, fi0, L))
                ).sum();
    }

    private static double variableG(List<ExpPoint<Double>> expU, int n, double teta0, double fi0, double mX, double mY, double mZ, double rD){
        return Math.abs(dERd(expU, n, teta0, fi0, mX, mY, mZ, rD) / 2);
    }

    public static double d2FRd(List<ExpPoint<Double>> expU, int n, double teta0, double fi0, double mX, double mY, double mZ, double rD) {
        return expU.stream()
                .mapToDouble(tr -> {
                    double dGRd =
                            -2 * (mX * CoefficientUtils.coefficientSum(3, n, rD, (L) -> (1. / (rD * rD)) * (L - 1) * (L - 2) *     CoefficientUtils.coefficientAL(tr.getPointX(), tr.getPointY(), teta0, fi0, L)) +
                                  mY * CoefficientUtils.coefficientSum(3, n, rD, (L) -> (1. / (rD * rD)) * (L - 1) * (L - 2) *     CoefficientUtils.coefficientCL(tr.getPointX(), tr.getPointY(), teta0, fi0, L)) +
                                  mZ * CoefficientUtils.coefficientSum(3, n, rD, (L) -> (1. / (rD * rD)) * (L - 1) * (L - 2) * L * CoefficientUtils.coefficientBL(tr.getPointX(), tr.getPointY(), teta0, fi0, L)));

                    double El = CoefficientUtils.coefficientEL(tr.getResult(), n, tr.getPointX(), tr.getPointY(), teta0, fi0, mX, mY, mZ, rD);
                    double varG = variableG(expU, n, teta0, fi0, mX, mY, mZ, rD);
                    return -2 * Math.pow(varG, 2) + El * dGRd;
                }).sum();
    }

    public static double dFRdMx(List<ExpPoint<Double>> expU, int n, double teta0, double fi0, double mX, double mY, double mZ, double rD) {
        return expU.stream()
                .mapToDouble(tr -> {
                    double El = CoefficientUtils.coefficientEL(tr.getResult(), n, tr.getPointX(), tr.getPointY(), teta0, fi0, mX, mY, mZ, rD);
                    double varG = variableG(expU, n, teta0, fi0, mX, mY, mZ, rD);

                    double dEdMx = -2 * CoefficientUtils.coefficientSum(n, rD, (L) -> CoefficientUtils.coefficientAL(tr.getPointX(), tr.getPointY(), teta0, fi0, L));
                    double dGMx =  -2 * CoefficientUtils.coefficientSum(2, n, rD, (L) -> (1. / rD) * (L - 1) * CoefficientUtils.coefficientAL(tr.getPointX(), tr.getPointY(), teta0, fi0, L));
                    return dEdMx * varG + dGMx * El;
                }).sum();
    }

    public static double dFRdMy(List<ExpPoint<Double>> expU, int n, double teta0, double fi0, double mX, double mY, double mZ, double rD) {
        return expU.stream()
                .mapToDouble(tr -> {
                    double El = CoefficientUtils.coefficientEL(tr.getResult(), n, tr.getPointX(), tr.getPointY(), teta0, fi0, mX, mY, mZ, rD);
                    double varG = variableG(expU, n, teta0, fi0, mX, mY, mZ, rD);

                    double dEdMy = -2 * CoefficientUtils.coefficientSum(n, rD, (L) -> CoefficientUtils.coefficientCL(tr.getPointX(), tr.getPointY(), teta0, fi0, L));
                    double dGMy =  -2 * CoefficientUtils.coefficientSum(2, n, rD, (L) -> (1. / rD) * (L - 1) * CoefficientUtils.coefficientCL(tr.getPointX(), tr.getPointY(), teta0, fi0, L));

                    return dEdMy * varG + dGMy * El;
                }).sum();
    }

    public static double dFRdMz(List<ExpPoint<Double>> expU, int n, double teta0, double fi0, double mX, double mY, double mZ, double rD) {
        return expU.stream()
                .mapToDouble(tr -> {
                    double El = CoefficientUtils.coefficientEL(tr.getResult(), n, tr.getPointX(), tr.getPointY(), teta0, fi0, mX, mY, mZ, rD);
                    double varG = variableG(expU, n, teta0, fi0, mX, mY, mZ, rD);

                    double dEdMz = -2 * CoefficientUtils.coefficientSum(n, rD, (L) -> L * CoefficientUtils.coefficientBL(tr.getPointX(), tr.getPointY(), teta0, fi0, L));
                    double dGMz =  -2 * CoefficientUtils.coefficientSum(2, n, rD, (L) -> (1. / rD) * L * (L - 1) * CoefficientUtils.coefficientBL(tr.getPointX(), tr.getPointY(), teta0, fi0, L));

                    return dEdMz * varG + dGMz * El;
                }).sum();
    }
}
