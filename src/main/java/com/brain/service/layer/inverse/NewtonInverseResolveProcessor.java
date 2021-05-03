package com.brain.service.layer.inverse;

import com.brain.util.function.FunctionHelper;
import com.brain.util.function.gradient.GradDerivativeLocalizationNeuralSource;
import com.brain.util.function.gradient.GradLocalizationNeuralSource;
import com.brain.util.minimization.multiple.BitStep;
import com.brain.util.minimization.multiple.MultipleMinimization;
import com.brain.util.minimization.multiple.Newton;
import com.brain.util.minimization.point.Point4D;
import com.brain.util.minimization.point.PointMinimization;
import com.brain.util.minimization.single.GoldenRatioMinimizer;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class NewtonInverseResolveProcessor extends AbstractResolveInverseTask {

    @Override
    public MultipleMinimization calculate(double expU, int n, double step, double area, double teta0, double fi0, double R1, double eps) {
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
                            GradLocalizationNeuralSource.dFRd(expU, n, i, j, teta0, fi0, point.getTeta(), point.getFi(), point.getRou(), point.getWi())
            );
        };

        // dF/d^2(X)
        Function<PointMinimization, Double> dfD2x = (point4D) -> {
            Point4D point = (Point4D) point4D;
            return FunctionHelper.iterateByArrayFunction(0, area, 2 * area, step,
                    (i, j) -> GradDerivativeLocalizationNeuralSource.d2FMx(n, i, j, teta0, fi0, R1, point.getWi()) +
                            GradLocalizationNeuralSource.d2FMx(n, i, j, teta0, fi0, point.getTeta(), point.getFi(), point.getRou(), point.getWi())
            );
        };

        // dF/d^2(Y)
        Function<PointMinimization, Double> dfD2y = (point4D) -> {
            Point4D point = (Point4D) point4D;
            return FunctionHelper.iterateByArrayFunction(0, area, 2 * area, step,
                    (i, j) -> GradDerivativeLocalizationNeuralSource.d2FMy(n, i, j, teta0, fi0, R1, point.getWi()) +
                            GradLocalizationNeuralSource.d2FMy(n, i, j, teta0, fi0, point.getTeta(), point.getFi(), point.getRou(), point.getWi())
            );
        };

        // dF/d^2(Z)
        Function<PointMinimization, Double> dfD2z = (point4D) -> {
            Point4D point = (Point4D) point4D;
            return FunctionHelper.iterateByArrayFunction(0, area, 2 * area, step,
                    (i, j) -> GradDerivativeLocalizationNeuralSource.d2FMz(n, i, j, teta0, fi0, R1, point.getWi()) +
                            GradLocalizationNeuralSource.d2FMz(n, i, j, teta0, fi0, point.getTeta(), point.getFi(), point.getRou(), point.getWi())
            );
        };

        // dF/d^2(W)
        Function<PointMinimization, Double> dfD2w = (point4D) -> {
            Point4D point = (Point4D) point4D;
            return FunctionHelper.iterateByArrayFunction(0, area, 2 * area, step,
                    (i, j) -> GradDerivativeLocalizationNeuralSource.d2FRd(expU, n, i, j, teta0, fi0, R1,  point.getFi(), point.getRou(), point.getWi(), point.getWi()) +
                            GradLocalizationNeuralSource.d2FRd(expU, n, i, j, teta0, fi0, point.getTeta(), point.getFi(), point.getRou(), point.getWi())
            );
        };


        // dF/dXdY
        Function<PointMinimization, Double> dfDxDy = (point4D) -> {
            Point4D point = (Point4D) point4D;
            return FunctionHelper.iterateByArrayFunction(0, area, 2 * area, step,
                    (i, j) -> GradDerivativeLocalizationNeuralSource.dFMxMy(n, i, j, teta0, fi0, R1, point.getWi()) +
                            GradLocalizationNeuralSource.dFMxMy(n, i, j, teta0, fi0, point.getTeta(), point.getFi(), point.getRou(), point.getWi())
            );
        };

        // dF/dYdX
        Function<PointMinimization, Double> dfDyDx = (point4D) -> {
            Point4D point = (Point4D) point4D;
            return FunctionHelper.iterateByArrayFunction(0, area, 2 * area, step,
                    (i, j) -> GradDerivativeLocalizationNeuralSource.dFMyMx(n, i, j, teta0, fi0, R1, point.getWi()) +
                            GradLocalizationNeuralSource.dFMyMx(n, i, j, teta0, fi0, point.getTeta(), point.getFi(), point.getRou(), point.getWi())
            );
        };

        // dF/dXdZ
        Function<PointMinimization, Double> dfDxDz = (point4D) -> {
            Point4D point = (Point4D) point4D;
            return FunctionHelper.iterateByArrayFunction(0, area, 2 * area, step,
                    (i, j) -> GradDerivativeLocalizationNeuralSource.dFMxMz(n, i, j, teta0, fi0, R1, point.getWi()) +
                            GradLocalizationNeuralSource.dFMxMz(n, i, j, teta0, fi0, point.getTeta(), point.getFi(), point.getRou(), point.getWi())
            );
        };

        // dF/dZdX
        Function<PointMinimization, Double> dfDzDx = (point4D) -> {
            Point4D point = (Point4D) point4D;
            return FunctionHelper.iterateByArrayFunction(0, area, 2 * area, step,
                    (i, j) -> GradDerivativeLocalizationNeuralSource.dFMzMx(n, i, j, teta0, fi0, R1, point.getWi()) +
                            GradLocalizationNeuralSource.dFMzMx(n, i, j, teta0, fi0, point.getTeta(), point.getFi(), point.getRou(), point.getWi())
            );
        };

        // dF/dYdZ
        Function<PointMinimization, Double> dfDyDz = (point4D) -> {
            Point4D point = (Point4D) point4D;
            return FunctionHelper.iterateByArrayFunction(0, area, 2 * area, step,
                    (i, j) -> GradDerivativeLocalizationNeuralSource.dFMyMz(n, i, j, teta0, fi0, R1, point.getWi()) +
                            GradLocalizationNeuralSource.dFMyMz(n, i, j, teta0, fi0, point.getTeta(), point.getFi(), point.getRou(), point.getWi())
            );
        };

        // dF/dZdY
        Function<PointMinimization, Double> dfDzDy = (point4D) -> {
            Point4D point = (Point4D) point4D;
            return FunctionHelper.iterateByArrayFunction(0, area, 2 * area, step,
                    (i, j) -> GradDerivativeLocalizationNeuralSource.dFMzMy(n, i, j, teta0, fi0, R1, point.getWi()) +
                            GradLocalizationNeuralSource.dFMzMy(n, i, j, teta0, fi0, point.getTeta(), point.getFi(), point.getRou(), point.getWi())
            );
        };

        // dF/dWdX
        Function<PointMinimization, Double> dfDwDx = (point4D) -> {
            Point4D point = (Point4D) point4D;
            return FunctionHelper.iterateByArrayFunction(0, area, 2 * area, step,
                    (i, j) -> GradDerivativeLocalizationNeuralSource.dFRdMx(n, i, j, teta0, fi0, R1, point.getWi()) +
                            GradLocalizationNeuralSource.dFRdMx(expU, n, i, j, teta0, fi0, point.getTeta(), point.getFi(), point.getRou(), point.getWi())
            );
        };

        // dF/dWdZ
        Function<PointMinimization, Double> dfDwDz = (point4D) -> {
            Point4D point = (Point4D) point4D;
            return FunctionHelper.iterateByArrayFunction(0, area, 2 * area, step,
                    (i, j) -> GradDerivativeLocalizationNeuralSource.dFRdMz(n, i, j, teta0, fi0, R1, point.getWi()) +
                            GradLocalizationNeuralSource.dFRdMz(expU, n, i, j, teta0, fi0, point.getTeta(), point.getFi(), point.getRou(), point.getWi())
            );
        };

        // dF/dWdY
        Function<PointMinimization, Double> dfDwDy = (point4D) -> {
            Point4D point = (Point4D) point4D;
            return FunctionHelper.iterateByArrayFunction(0, area, 2 * area, step,
                    (i, j) -> GradDerivativeLocalizationNeuralSource.dFRdMy(n, i, j, teta0, fi0, R1, point.getWi()) +
                            GradLocalizationNeuralSource.dFRdMy(expU, n, i, j, teta0, fi0, point.getTeta(), point.getFi(), point.getRou(), point.getWi())
            );
        };

        // dF/dXdW
        Function<PointMinimization, Double> dfDxDw = (point4D) -> {
            Point4D point = (Point4D) point4D;
            return FunctionHelper.iterateByArrayFunction(0, area, 2 * area, step,
                    (i, j) -> GradDerivativeLocalizationNeuralSource.dFMxRd(n, i, j, teta0, fi0, R1, point.getWi()) +
                            GradLocalizationNeuralSource.dFMxRd(expU, n, i, j, teta0, fi0, point.getTeta(), point.getFi(), point.getRou(), point.getWi())
            );
        };

        // dF/dYdW
        Function<PointMinimization, Double> dfDyDw = (point4D) -> {
            Point4D point = (Point4D) point4D;
            return FunctionHelper.iterateByArrayFunction(0, area, 2 * area, step,
                    (i, j) -> GradDerivativeLocalizationNeuralSource.dFMyRd(expU, n, i, j, teta0, fi0, R1, point.getWi()) +
                            GradLocalizationNeuralSource.dFMyRd(expU, n, i, j, teta0, fi0, point.getTeta(), point.getFi(), point.getRou(), point.getWi())
            );
        };

        // dF/dZdW
        Function<PointMinimization, Double> dfDzDw = (point4D) -> {
            Point4D point = (Point4D) point4D;
            return FunctionHelper.iterateByArrayFunction(0, area, 2 * area, step,
                    (i, j) -> GradDerivativeLocalizationNeuralSource.dFMzRd(n, i, j, teta0, fi0, R1, point.getWi()) +
                            GradLocalizationNeuralSource.dFMzRd(expU, n, i, j, teta0, fi0, point.getTeta(), point.getFi(), point.getRou(), point.getWi())
            );
        };


        List<List<Function<PointMinimization, Double>>> matrixGeese = Arrays.asList(
                Arrays.asList(dfD2x, dfDxDy, dfDxDz, dfDxDw),
                Arrays.asList(dfDyDx, dfD2y, dfDyDz, dfDyDw),
                Arrays.asList(dfDzDx, dfDzDy, dfD2z, dfDzDw),
                Arrays.asList(dfDwDx, dfDwDy, dfDwDz, dfD2w)
        );

        Function<PointMinimization, Double> norma = (point4D) -> Math.sqrt(
                Math.pow(gradX.apply(point4D), 2.) + Math.pow(gradY.apply(point4D), 2.) +
                        Math.pow(gradZ.apply(point4D), 2.) + Math.pow(gradW.apply(point4D), 2.)
        );


        return new Newton(funk, norma, Arrays.asList(gradX, gradY, gradZ, gradW),
                matrixGeese, eps);
    }
}
