package com.brain.service.layer.inverse;

import com.brain.util.function.gradient.GradLocalizationNeuralSource;
import com.brain.util.minimization.multiple.BitStep;
import com.brain.util.minimization.multiple.MultipleMinimization;
import com.brain.util.minimization.point.Point4D;
import com.brain.util.minimization.point.PointMinimization;
import com.brain.util.minimization.point.ExpPoint;
import com.brain.util.minimization.single.GoldenRatioMinimizer;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class BitStepInverseResolveProcessor extends AbstractResolveInverseTask {

    @Override
    public MultipleMinimization calculate(List<ExpPoint<Double>> expU, int n, double teta0, double fi0, double R1, double eps) {

        Function<PointMinimization, Double> funk = (point4D) -> {
            Point4D point = (Point4D)point4D;
            return localizationNeuralSource(expU, n,  teta0, fi0, point.getPointX(), point.getPointY(), point.getPointZ(), point.getPointW());// +
                            //derivativeLocalizationNeuralSource(n, i, j, teta0, fi0, point.getPointX(), point.getPointY(), point.getPointZ(), R1, point.getPointW())

        };

        Function<PointMinimization, Double> gradX = (point4D) -> {
            Point4D point = (Point4D) point4D;
            return //GradDerivativeLocalizationNeuralSource.dFMx(n, i, j, teta0, fi0, point.getPointX(), point.getPointY(), point.getPointZ(), R1, point.getPointW()) +
                            GradLocalizationNeuralSource.dFMx(expU, n, teta0, fi0, point.getPointX(), point.getPointY(), point.getPointZ(), point.getPointW());
        };

        Function<PointMinimization, Double> gradY = (point4D) -> {
            Point4D point = (Point4D) point4D;
            return //GradDerivativeLocalizationNeuralSource.dFMy(n, i, j, teta0, fi0, point.getPointX(), point.getPointY(), point.getPointZ(), R1, point.getPointW()) +
                            GradLocalizationNeuralSource.dFMy(expU, n, teta0, fi0, point.getPointX(), point.getPointY(), point.getPointZ(), point.getPointW());
        };

        Function<PointMinimization, Double> gradZ = (point4D) -> {
            Point4D point = (Point4D) point4D;
            return //GradDerivativeLocalizationNeuralSource.dFMz(n, i, j, teta0, fi0, point.getPointX(), point.getPointY(), point.getPointZ(), R1, point.getPointW()) +
                            GradLocalizationNeuralSource.dFMz(expU, n, teta0, fi0, point.getPointX(), point.getPointY(), point.getPointZ(), point.getPointW());
        };

        Function<PointMinimization, Double> gradW = (point4D) -> {
            Point4D point = (Point4D) point4D;
            return //GradDerivativeLocalizationNeuralSource.dFrd(n, i, j, teta0, fi0, point.getPointX(), point.getPointY(), point.getPointZ(), R1, point.getPointW()) +
                            GradLocalizationNeuralSource.dFRd(expU, n, teta0, fi0, point.getPointX(), point.getPointY(), point.getPointZ(), point.getPointW());
        };

        Function<PointMinimization, Double> norma = (point4D) -> Math.sqrt(
                Math.pow(gradX.apply(point4D), 2.) + Math.pow(gradY.apply(point4D), 2.) +
                        Math.pow(gradZ.apply(point4D), 2.) + Math.pow(gradW.apply(point4D), 2.)
        );

        return new BitStep(funk, norma, Arrays.asList(gradX, gradY, gradZ, gradW),
                new GoldenRatioMinimizer(), eps);
    }

}
