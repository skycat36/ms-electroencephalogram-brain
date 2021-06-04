package com.brain.util.function.gradient;

import com.brain.util.function.CoefficientUtils;
import com.brain.util.minimization.point.ExpPoint;

import java.util.List;
import java.util.function.Function;

public class GradDerivativeLocalizationNeuralSource {

    public static double dFMx(List<ExpPoint<Double>> expU,  int n, double teta0, double fi0, double mX, double mY, double mZ, double R1, double rD) throws RuntimeException {
        return expU.stream()
                .mapToDouble(tr ->
                        2 * CoefficientUtils.coefficientTL(n, tr.getPointX(), tr.getPointY(), teta0, fi0, mX, mY, mZ, R1, rD) *
                        dTLdMx(n, tr.getPointX(), tr.getPointY(), teta0, fi0, R1, rD)
                ).sum();
    }

    private static double dTLdMx(int n, double teta, double fi, double teta0, double fi0, double R1, double rD) {
        Function<Integer, Double> subFunction = (L) -> (-L - 1) / R1;
        return CoefficientUtils.coefficientSum(n, rD, (L) -> subFunction.apply(L) * CoefficientUtils.coefficientAL(teta, fi, teta0, fi0, L));
    }

    public static double d2FMx(List<ExpPoint<Double>> expU, int n, double teta0, double fi0, double R1, double rD) {
        return 2 * expU.stream()
                .mapToDouble(tr ->
                        Math.pow(dTLdMx(n, tr.getPointX(), tr.getPointY(), teta0, fi0, R1, rD), 2)
                ).sum();
    }

    public static double dFMxMy(List<ExpPoint<Double>> expU, int n, double teta0, double fi0, double R1, double rD) {
        return 2 * expU.stream()
                .mapToDouble(tr ->
                        dTLdMx(n, tr.getPointX(), tr.getPointY(), teta0, fi0, R1, rD) * dTLdMy(n, tr.getPointX(), tr.getPointY(), teta0, fi0, R1, rD)
                ).sum();
    }

    public static double dFMxMz(List<ExpPoint<Double>> expU, int n, double teta0, double fi0, double R1, double rD) {
        return 2 * expU.stream()
                .mapToDouble(tr ->
                        dTLdMx(n, tr.getPointX(), tr.getPointY(), teta0, fi0, R1, rD) * dTLdMz(n, tr.getPointX(), tr.getPointY(), teta0, fi0, R1, rD)
                ).sum();
    }

    public static double dFMxRd(List<ExpPoint<Double>> expU, int n, double teta0, double fi0, double R1, double rD) {
        return dFRdMx(expU, n, teta0, fi0, R1, rD);
    }


    public static double dFMy(List<ExpPoint<Double>> expU, int n, double teta0, double fi0, double mX, double mY, double mZ, double R1, double rD) {
        return 2 * expU.stream()
                .mapToDouble(tr ->
                        2 * CoefficientUtils.coefficientTL(n, tr.getPointX(), tr.getPointY(), teta0, fi0, mX, mY, mZ, R1, rD) *
                                dTLdMy(n, tr.getPointX(), tr.getPointY(), teta0, fi0, R1, rD)
                ).sum();
    }

    private static double dTLdMy(int n, double teta, double fi, double teta0, double fi0, double R1, double rD) {
        Function<Integer, Double> subFunction = (L) -> (-L - 1) / R1;
        return CoefficientUtils.coefficientSum(n, rD, (L) -> subFunction.apply(L) * CoefficientUtils.coefficientCL(teta, fi, teta0, fi0, L));
    }

    public static double d2FMy(List<ExpPoint<Double>> expU, int n, double teta, double fi, double teta0, double fi0, double R1, double rD) {
        return 2 * expU.stream()
                .mapToDouble(tr ->
                        Math.pow(dTLdMy(n, tr.getPointX(), tr.getPointY(), teta0, fi0, R1, rD), 2)
                ).sum();
    }

    public static double dFMyMx(List<ExpPoint<Double>> expU, int n, double teta0, double fi0, double R1, double rD) {
        return dFMxMy(expU, n, teta0, fi0, R1, rD);
    }

    public static double dFMyMz(List<ExpPoint<Double>> expU, int n, double teta0, double fi0, double R1, double rD) {
        return 2 * expU.stream()
                .mapToDouble(tr ->
                        dTLdMy(n, tr.getPointX(), tr.getPointY(), teta0, fi0, R1, rD) * dTLdMz(n, tr.getPointX(), tr.getPointY(), teta0, fi0, R1, rD)
                ).sum();
    }

    public static double dFMyRd(List<ExpPoint<Double>> expU, int n, double teta, double fi, double teta0, double fi0, double R1, double rD) {
        return dFRdMy(expU, n, teta0, fi0, R1, rD);
    }


    public static double dFMz(List<ExpPoint<Double>> expU, int n, double teta0, double fi0, double mX, double mY, double mZ, double R1, double rD) {
        return 2 * expU.stream()
                .mapToDouble(tr ->
                        2 * CoefficientUtils.coefficientTL(n, tr.getPointX(), tr.getPointY(), teta0, fi0, mX, mY, mZ, R1, rD) *
                                dTLdMz(n, tr.getPointX(), tr.getPointY(), teta0, fi0, R1, rD)
                ).sum();
    }

    private static double dTLdMz(int n, double teta, double fi, double teta0, double fi0, double R1, double rD) {
        Function<Integer, Double> subFunction = (L) -> ((-L - 1) * L) / R1;
        return CoefficientUtils.coefficientSum(n, rD, (L) -> subFunction.apply(L) * CoefficientUtils.coefficientBL(teta, fi, teta0, fi0, L));
    }

    public static double d2FMz(List<ExpPoint<Double>> expU, int n, double teta, double fi, double teta0, double fi0, double R1, double rD) {
        return 2 * expU.stream()
                .mapToDouble(tr ->
                        Math.pow(dTLdMz(n, tr.getPointX(), tr.getPointY(), teta0, fi0, R1, rD), 2)
                ).sum();
    }

    public static double dFMzMx(List<ExpPoint<Double>> expU, int n, double teta0, double fi0, double R1, double rD) {
        return dFMxMz(expU, n, teta0, fi0, R1, rD);
    }

    public static double dFMzMy(List<ExpPoint<Double>> expU, int n, double teta0, double fi0, double R1, double rD) {
        return dFMyMz(expU, n, teta0, fi0, R1, rD);
    }

    public static double dFMzRd(List<ExpPoint<Double>> expU, int n, double teta0, double fi0, double R1, double rD) {
        return dFRdMz(expU, n, teta0, fi0, R1, rD);
    }


    public static double dFrd(List<ExpPoint<Double>> expU, int n, double teta0, double fi0, double mX, double mY, double mZ, double R1, double rD) {
        return 2 * expU.stream()
                .mapToDouble(tr ->
                        2 * CoefficientUtils.coefficientTL(n, tr.getPointX(), tr.getPointY(), teta0, fi0, mX, mY, mZ, R1, rD) *
                                dTLdRd(n, tr.getPointX(), tr.getPointY(), teta0, fi0, mX, mY, mZ, R1, rD)
                ).sum();
    }

    private static double dTLdRd(int n, double teta, double fi, double teta0, double fi0, double mX, double mY, double mZ, double R1, double rD) {
        Function<Integer, Double> subFunction = (L) -> ((L * L) - 1) / R1;

        double result =
                (mX * CoefficientUtils.coefficientSum(2, n, rD, (L) -> (1. / rD) * subFunction.apply(L) *     CoefficientUtils.coefficientAL(teta, fi, teta0, fi0, L)) +
                 mY * CoefficientUtils.coefficientSum(2, n, rD, (L) -> (1. / rD) * subFunction.apply(L) *     CoefficientUtils.coefficientCL(teta, fi, teta0, fi0, L)) +
                 mZ * CoefficientUtils.coefficientSum(2, n, rD, (L) -> (1. / rD) * subFunction.apply(L) * L * CoefficientUtils.coefficientBL(teta, fi, teta0, fi0, L)));
        return -result;
    }

    public static double d2FRd(List<ExpPoint<Double>> expU, int n, double teta0, double fi0, double mX, double mY, double mZ, double R1, double rD) {
        return 2 * expU.stream()
                .mapToDouble(tr -> {
                    Function<Integer, Double> subFunction = (L) -> (((L * L) - 1) * (L - 2)) / R1;

                    double dTLd2Rd =
                           - (mX * CoefficientUtils.coefficientSum(3, n, rD, (L) -> (1. / (rD * rD)) * subFunction.apply(L) *     CoefficientUtils.coefficientAL(tr.getPointX(), tr.getPointY(), teta0, fi0, L)) +
                              mY * CoefficientUtils.coefficientSum(3, n, rD, (L) -> (1. / (rD * rD)) * subFunction.apply(L) *     CoefficientUtils.coefficientCL(tr.getPointX(), tr.getPointY(), teta0, fi0, L)) +
                              mZ * CoefficientUtils.coefficientSum(3, n, rD, (L) -> (1. / (rD * rD)) * subFunction.apply(L) * L * CoefficientUtils.coefficientBL(tr.getPointX(), tr.getPointY(), teta0, fi0, L)));

                    return  Math.pow(dTLdRd(n, tr.getPointX(), tr.getPointY(), teta0, fi0, mX, mY, mZ, R1, rD), 2) +
                            CoefficientUtils.coefficientTL(n, tr.getPointX(), tr.getPointY(), teta0, fi0, mX, mY, mZ, R1, rD) * dTLd2Rd;
                        }
                ).sum();


    }

    public static double dFRdMx(List<ExpPoint<Double>> expU, int n, double teta0, double fi0, double R1, double rD) {
        return 2 * expU.stream()
                .mapToDouble(tr -> {
                    Function<Integer, Double> subFunction = (L) -> ((L * L) - 1) / R1;
                    double dTLdRdMx = - CoefficientUtils.coefficientSum(2, n, rD, (L) ->
                            (1. / rD) * subFunction.apply(L) * CoefficientUtils.coefficientAL(tr.getPointX(), tr.getPointY(), teta0, fi0, L));

                    return dTLdMx(n, tr.getPointX(), tr.getPointY(), teta0, fi0, R1, rD) + dTLdRdMx;
                }).sum();
    }

    public static double dFRdMy(List<ExpPoint<Double>> expU, int n, double teta0, double fi0, double R1, double rD) {
        return 2 * expU.stream()
                .mapToDouble(tr -> {
                    Function<Integer, Double> subFunction = (L) -> ((L * L) - 1) / R1;
                    double dTLdRdMy = - CoefficientUtils.coefficientSum(2, n, rD, (L) ->
                            (1. / rD) * subFunction.apply(L) * CoefficientUtils.coefficientCL(tr.getPointX(), tr.getPointY(), teta0, fi0, L));

                    return dTLdMy(n, tr.getPointX(), tr.getPointY(), teta0, fi0, R1, rD) + dTLdRdMy;
                }).sum();
    }

    public static double dFRdMz(List<ExpPoint<Double>> expU, int n, double teta0, double fi0, double R1, double rD) {
        return 2 * expU.stream()
                .mapToDouble(tr -> {
                    Function<Integer, Double> subFunction = (L) -> (((L * L) - 1) * L) / R1;
                    double dTLdRdMz = - CoefficientUtils.coefficientSum(2, n, rD, (L) ->
                            (1. / rD) * subFunction.apply(L) * CoefficientUtils.coefficientBL(tr.getPointX(), tr.getPointY(), teta0, fi0, L));

                    return dTLdMz(n, tr.getPointX(), tr.getPointY(), teta0, fi0, R1, rD) + dTLdRdMz;
                }).sum();
    }

}
