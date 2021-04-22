package com.brain.service.layer;

import com.brain.util.function.CoefficientUtils;
import com.brain.util.function.FunctionHelper;
import com.brain.util.function.gradient.GradDerivativeLocalizationNeuralSource;
import com.brain.util.function.gradient.GradLocalizationNeuralSource;
import com.brain.util.minimization.multiple.BitStep;
import com.brain.util.minimization.point.Point4D;
import com.brain.util.minimization.point.PointMinimization;
import com.brain.util.minimization.point.ResultPoint;
import com.brain.util.minimization.single.GoldenRatioMinimizer;

import java.util.Arrays;
import java.util.function.Function;

public class SolvingInverseProblem {

    /**
     * Решение обратной задачи
     * @param n количество слоев
     * @param R1 значение ограничивающие возникновение импульса (ед. изм. )
     * @return возвращает силу потенцала в точке
     */
    public static ResultPoint calculate(double expU, int n, double step, double area, double teta0, double fi0, double R1, double eps, double alfa) {

        Function<PointMinimization, Double> funk = (point4D) -> {
            Point4D point = (Point4D)point4D;
            return FunctionHelper.iterateByArrayFunction(0, area, 2 * area, step,
                    (i, j) -> localizationNeuralSource(expU, n, i, j, teta0, fi0, point.getTeta(), point.getFi(), point.getRou(), point.getWi()) +
                            derivativeLocalizationNeuralSource(n, i, j, teta0, fi0, point.getTeta(), point.getFi(), point.getRou(), R1, point.getWi())
            );
        };

        Function<PointMinimization, Double> gradX = (point4D) -> {
            Point4D point = (Point4D) point4D;
            return FunctionHelper.iterateByArrayFunction(0, area, 2 * area, step,
                    (i, j) -> GradDerivativeLocalizationNeuralSource.dFMx(n, i, j, teta0, fi0, point.getTeta(), point.getFi(), point.getRou(), R1, point.getWi()) +
                    GradLocalizationNeuralSource.dFMx(expU, n, i, j, teta0, fi0, point.getTeta(), point.getFi(), point.getRou(), point.getWi())
            );
        };

        Function<PointMinimization, Double> gradY = (point4D) -> {
            Point4D point = (Point4D) point4D;
            return FunctionHelper.iterateByArrayFunction(0, area, 2 * area, step,
                    (i, j) -> GradDerivativeLocalizationNeuralSource.dFMy(n, i, j, teta0, fi0, point.getTeta(), point.getFi(), point.getRou(), R1, point.getWi()) +
                    GradLocalizationNeuralSource.dFMy(expU, n, i, j, teta0, fi0, point.getTeta(), point.getFi(), point.getRou(), point.getWi())
            );
        };

        Function<PointMinimization, Double> gradZ = (point4D) -> {
            Point4D point = (Point4D) point4D;
            return FunctionHelper.iterateByArrayFunction(0, area, 2 * area, step,
                    (i, j) -> GradDerivativeLocalizationNeuralSource.dFMz(n, i, j, teta0, fi0, point.getTeta(), point.getFi(), point.getRou(), R1, point.getWi()) +
                    GradLocalizationNeuralSource.dFMz(expU, n, i, j, teta0, fi0, point.getTeta(), point.getFi(), point.getRou(), point.getWi())
            );
        };

        Function<PointMinimization, Double> gradW = (point4D) -> {
            Point4D point = (Point4D) point4D;
            return FunctionHelper.iterateByArrayFunction(0, area, 2 * area, step,
                    (i, j) -> GradDerivativeLocalizationNeuralSource.dFrd(n, i, j, teta0, fi0, point.getTeta(), point.getFi(), point.getRou(), R1, point.getWi()) +
                    GradLocalizationNeuralSource.dFrd(expU, n, i, j, teta0, fi0, point.getTeta(), point.getFi(), point.getRou(), point.getWi())
            );
        };

        Function<PointMinimization, Double> norma = (point4D) -> Math.sqrt(
                Math.pow(gradX.apply(point4D), 2.) + Math.pow(gradY.apply(point4D), 2.) +
                        Math.pow(gradZ.apply(point4D), 2.) + Math.pow(gradW.apply(point4D), 2.)
        );
        BitStep bitStepMinimization = new BitStep(funk, norma, Arrays.asList(gradX, gradY, gradZ, gradW));

        // Start point
        Point4D point4D = new Point4D(1. , 2. , 1., 1.);

        return bitStepMinimization.minimization(point4D, eps, alfa, new GoldenRatioMinimizer());
    }

    private static double localizationNeuralSource(double expU, int n, double teta, double fi, double teta0, double fi0, double mX, double mY, double mZ, double rD) {

        double result = (- mX * CoefficientUtils.coefficientSum(n, rD, (L) -> CoefficientUtils.coefficientAL(teta, fi, teta0, fi0, L)) -
                        mY * CoefficientUtils.coefficientSum(n, rD, (L) -> CoefficientUtils.coefficientCL(teta, fi, teta0, fi0, L)) -
                        mZ * CoefficientUtils.coefficientSum(n, rD, (L) -> L * CoefficientUtils.coefficientBL(teta, fi, teta0, fi0, L)));

        return Math.pow(expU + result, 2);
    }


    private static double derivativeLocalizationNeuralSource(int n, double teta, double fi, double teta0, double fi0, double mX, double mY, double mZ, double R1, double rD) {

        Function<Integer, Double> subFunction = (L) -> (-L - 1) / Math.pow(R1, L + 2);

        double result =  (mX * CoefficientUtils.coefficientSum(n, rD, (L) -> subFunction.apply(L) * CoefficientUtils.coefficientAL(teta, fi, teta0, fi0, L)) +
                mY * CoefficientUtils.coefficientSum(n, rD, (L) -> subFunction.apply(L) * CoefficientUtils.coefficientCL(teta, fi, teta0, fi0, L)) +
                mZ * CoefficientUtils.coefficientSum(n, rD, (L) -> subFunction.apply(L) * L * CoefficientUtils.coefficientBL(teta, fi, teta0, fi0, L)));
        return Math.pow(result, 2);
    }
}