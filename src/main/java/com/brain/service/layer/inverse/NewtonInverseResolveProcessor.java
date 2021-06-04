package com.brain.service.layer.inverse;

import com.brain.util.function.gradient.GradLocalizationNeuralSource;
import com.brain.util.minimization.multiple.MultipleMinimization;
import com.brain.util.minimization.multiple.Newton;
import com.brain.util.minimization.point.ExpPoint;
import com.brain.util.minimization.point.Point4D;
import com.brain.util.minimization.point.PointMinimization;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class NewtonInverseResolveProcessor extends AbstractResolveInverseTask {

    @Override
    public MultipleMinimization calculate(List<ExpPoint<Double>> expU, int n, double teta0, double fi0, double R1, double eps) {

        Function<PointMinimization, Double> funk = (point4D) -> {
            Point4D point = (Point4D)point4D;
            return localizationNeuralSource(expU, n,  teta0, fi0, point.getPointX(), point.getPointY(), point.getPointZ(), point.getPointW());// +
            //derivativeLocalizationNeuralSource(expU, n, teta0, fi0, point.getPointX(), point.getPointY(), point.getPointZ(), R1, point.getPointW())

        };

        Function<PointMinimization, Double> gradX = (point4D) -> {
            Point4D point = (Point4D) point4D;
            return //GradDerivativeLocalizationNeuralSource.dFMx(expU, n, teta0, fi0, point.getPointX(), point.getPointY(), point.getPointZ(), R1, point.getPointW()) +
                    GradLocalizationNeuralSource.dFMx(expU, n, teta0, fi0, point.getPointX(), point.getPointY(), point.getPointZ(), point.getPointW());
        };

        Function<PointMinimization, Double> gradY = (point4D) -> {
            Point4D point = (Point4D) point4D;
            return //GradDerivativeLocalizationNeuralSource.dFMy(expU, n, teta0, fi0, point.getPointX(), point.getPointY(), point.getPointZ(), R1, point.getPointW()) +
                    GradLocalizationNeuralSource.dFMy(expU, n, teta0, fi0, point.getPointX(), point.getPointY(), point.getPointZ(), point.getPointW());
        };

        Function<PointMinimization, Double> gradZ = (point4D) -> {
            Point4D point = (Point4D) point4D;
            return //GradDerivativeLocalizationNeuralSource.dFMz(expU, n, teta0, fi0, point.getPointX(), point.getPointY(), point.getPointZ(), R1, point.getPointW()) +
                    GradLocalizationNeuralSource.dFMz(expU, n, teta0, fi0, point.getPointX(), point.getPointY(), point.getPointZ(), point.getPointW());
        };

        Function<PointMinimization, Double> gradW = (point4D) -> {
            Point4D point = (Point4D) point4D;
            return //GradDerivativeLocalizationNeuralSource.dFrd(expU, n, teta0, fi0, point.getPointX(), point.getPointY(), point.getPointZ(), R1, point.getPointW()) +
                    GradLocalizationNeuralSource.dFRd(expU, n, teta0, fi0, point.getPointX(), point.getPointY(), point.getPointZ(), point.getPointW());
        };

        // dF/d^2(X)
        Function<PointMinimization, Double> dfD2x = (point4D) -> {
            Point4D point = (Point4D)point4D;
            return //GradDerivativeLocalizationNeuralSource.d2FMx(expU, n, teta0, fi0, R1, point.getPointW()) +
                            GradLocalizationNeuralSource.d2FMx(expU, n, teta0, fi0, point.getPointW());
        };

        // dF/d^2(Y)
        Function<PointMinimization, Double> dfD2y = (point4D) -> {
            Point4D point = (Point4D)point4D;
            return //GradDerivativeLocalizationNeuralSource.d2FMy(expU, n, teta0, fi0, R1, point.getPointW()) +
                            GradLocalizationNeuralSource.d2FMy(expU, n, teta0, fi0, point.getPointW());
        };

        // dF/d^2(Z)
        Function<PointMinimization, Double> dfD2z = (point4D) -> {
            Point4D point = (Point4D)point4D;
            return //GradDerivativeLocalizationNeuralSource.d2FMz(expU, n, teta0, fi0, R1, point.getPointW()) +
                            GradLocalizationNeuralSource.d2FMz(expU, n, teta0, fi0, point.getPointW());
        };

        // dF/d^2(W)
        Function<PointMinimization, Double> dfD2w = (point4D) -> {
            Point4D point = (Point4D)point4D;
            return //GradDerivativeLocalizationNeuralSource.d2FRd(expU, n, teta0, fi0, R1,  point.getPointY(), point.getPointZ(), point.getPointW(), point.getPointW()) +
                            GradLocalizationNeuralSource.d2FRd(expU, n, teta0, fi0, point.getPointX(), point.getPointY(), point.getPointZ(), point.getPointW());
        };


        // dF/dXdY
        Function<PointMinimization, Double> dfDxDy = (point4D) -> {
            Point4D point = (Point4D)point4D;
            return //GradDerivativeLocalizationNeuralSource.dFMxMy(expU, n, teta0, fi0, R1, point.getPointW()) +
                            GradLocalizationNeuralSource.dFMxMy(expU, n, teta0, fi0, point.getPointW());
        };

        // dF/dYdX
        Function<PointMinimization, Double> dfDyDx = (point4D) -> {
            Point4D point = (Point4D)point4D;
            return //GradDerivativeLocalizationNeuralSource.dFMyMx(expU, n, teta0, fi0, R1, point.getPointW()) +
                            GradLocalizationNeuralSource.dFMyMx(expU, n, teta0, fi0, point.getPointW());
        };

        // dF/dXdZ
        Function<PointMinimization, Double> dfDxDz = (point4D) -> {
            Point4D point = (Point4D)point4D;
            return //GradDerivativeLocalizationNeuralSource.dFMxMz(expU, n, teta0, fi0, R1, point.getPointW()) +
                            GradLocalizationNeuralSource.dFMxMz(expU, n, teta0, fi0, point.getPointW());
        };

        // dF/dZdX
        Function<PointMinimization, Double> dfDzDx = (point4D) -> {
            Point4D point = (Point4D)point4D;
            return //GradDerivativeLocalizationNeuralSource.dFMzMx(expU, n, teta0, fi0, R1, point.getPointW()) +
                            GradLocalizationNeuralSource.dFMzMx(expU, n, teta0, fi0, point.getPointW());
        };

        // dF/dYdZ
        Function<PointMinimization, Double> dfDyDz = (point4D) -> {
            Point4D point = (Point4D)point4D;
            return //GradDerivativeLocalizationNeuralSource.dFMyMz(expU, n, teta0, fi0, R1, point.getPointW()) +
                            GradLocalizationNeuralSource.dFMyMz(expU, n, teta0, fi0, point.getPointW());
        };

        // dF/dZdY
        Function<PointMinimization, Double> dfDzDy = (point4D) -> {
            Point4D point = (Point4D)point4D;
            return //GradDerivativeLocalizationNeuralSource.dFMzMy(expU, n, teta0, fi0, R1, point.getPointW()) +
                            GradLocalizationNeuralSource.dFMzMy(expU, n, teta0, fi0, point.getPointW());
        };

//      dF/dWdX
        Function<PointMinimization, Double> dfDwDx = (point4D) -> {
            Point4D point = (Point4D)point4D;
            return //GradDerivativeLocalizationNeuralSource.dFRdMx(expU, n, teta0, fi0, R1, point.getPointW()) +
                            GradLocalizationNeuralSource.dFRdMx(expU, n, teta0, fi0, point.getPointX(), point.getPointY(), point.getPointZ(), point.getPointW());
        };

        // dF/dWdZ
        Function<PointMinimization, Double> dfDwDz = (point4D) -> {
            Point4D point = (Point4D)point4D;
            return //GradDerivativeLocalizationNeuralSource.dFRdMz(expU, n, teta0, fi0, R1, point.getPointW()) +
                            GradLocalizationNeuralSource.dFRdMz(expU, n, teta0, fi0, point.getPointX(), point.getPointY(), point.getPointZ(), point.getPointW());
        };

        // dF/dWdY
        Function<PointMinimization, Double> dfDwDy = (point4D) -> {
            Point4D point = (Point4D)point4D;
            return //GradDerivativeLocalizationNeuralSource.dFRdMy(expU, n, teta0, fi0, R1, point.getPointW()) +
                            GradLocalizationNeuralSource.dFRdMy(expU, n, teta0, fi0, point.getPointX(), point.getPointY(), point.getPointZ(), point.getPointW());
        };

        // dF/dXdW
        Function<PointMinimization, Double> dfDxDw = (point4D) -> {
            Point4D point = (Point4D)point4D;
            return //GradDerivativeLocalizationNeuralSource.dFMxRd(expU, n, teta0, fi0, R1, point.getPointW()) +
                            GradLocalizationNeuralSource.dFMxRd(expU, n, teta0, fi0, point.getPointX(), point.getPointY(), point.getPointZ(), point.getPointW());
        };

        // dF/dYdW
        Function<PointMinimization, Double> dfDyDw = (point4D) -> {
            Point4D point = (Point4D)point4D;
            return //GradDerivativeLocalizationNeuralSource.dFMyRd(expU, n, teta0, fi0, R1, point.getPointW()) +
                            GradLocalizationNeuralSource.dFMyRd(expU, n, teta0, fi0, point.getPointX(), point.getPointY(), point.getPointZ(), point.getPointW());
        };

        // dF/dZdW
        Function<PointMinimization, Double> dfDzDw = (point4D) -> {
            Point4D point = (Point4D)point4D;
            return //GradDerivativeLocalizationNeuralSource.dFMzRd(expU, n, teta0, fi0, R1, point.getPointW()) +
                            GradLocalizationNeuralSource.dFMzRd(expU, n, teta0, fi0, point.getPointX(), point.getPointY(), point.getPointZ(), point.getPointW());
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
