package com.brain.util.minimization.multiple;

import com.brain.util.minimization.point.Point2D;
import com.brain.util.minimization.point.Point4D;
import com.brain.util.minimization.point.PointMinimization;
import com.brain.util.minimization.point.ResultPoint;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Arrays;
import java.util.function.Function;

public class BitStepTest {

    @Test
    public void minimizationTestPoint2D(){

        // F(x,y) = (x-1)^2 + (y-2)^2
        Function<PointMinimization, Double> funk = (point2D) -> Math.pow(((Point2D)point2D).getTeta() - 1, 2) + Math.pow(((Point2D)point2D).getFi() - 2, 2);
        // dF/dX = 2 * (x-1)
        Function<PointMinimization, Double> gradX = (point2D) -> 2 * (((Point2D)point2D).getTeta() - 1);
        // dF/dY = 2 * (y-2)
        Function<PointMinimization, Double> gradY = (point2D) -> 2 * (((Point2D)point2D).getFi() - 2);
        // norma = sqrt((dF/dX)^2 + (dF/dY)^2)
        Function<PointMinimization, Double> norma = (point2D) -> Math.sqrt(Math.pow(gradX.apply(point2D), 2) + Math.pow(gradY.apply(point2D), 2));
        BitStep bitStepMinimization = new BitStep(funk, norma, Arrays.asList(gradX, gradY));

        // inaccuracy
        double eps = 0.5;
        // Firs step
        double alfa = 0.1;

        // Start point
        Point2D point2D = new Point2D(4. , 5. );

        ResultPoint resultPoint = bitStepMinimization.minimization(point2D, eps, alfa);

        boolean result = false;

        // Resolve equation : x = 1, y = 2
        if ((1 - eps) < resultPoint.getTeta() &&  resultPoint.getTeta() < (1 + eps) ){
            if ((2 - eps) < resultPoint.getFi() &&  resultPoint.getFi() < (2 + eps) ){
                result = true;
            }
        }
        Assert.assertTrue(result);
    }

    @Test
    public void minimizationTestPoint4DExample1(){

        // F(x,y,z,w) = (x-1)^2 + (y-7)^2 + (z-5)^2 + (w-10)^2
        Function<PointMinimization, Double> funk = (point4D) -> {
            Point4D point = (Point4D)point4D;
            return Math.pow(point.getTeta() - 1, 2) + Math.pow(point.getFi() - 7, 2) +
                    Math.pow(point.getRou() - 5, 2) + Math.pow(point.getWi() - 10, 2);
        };
        // dF/dX = 2 * (x-1)
        Function<PointMinimization, Double> gradX = (point4D) -> 2 * (((Point4D)point4D).getTeta() - 1);
        // dF/dY = 2 * (y-7)
        Function<PointMinimization, Double> gradY = (point4D) -> 2 * (((Point4D)point4D).getFi() - 7);
        // dF/dZ = 2 * (z-5)
        Function<PointMinimization, Double> gradZ = (point4D) -> 2 * (((Point4D)point4D).getRou() - 5);
        // dF/dW = 2 * (w-10)
        Function<PointMinimization, Double> gradW = (point4D) -> 2 * (((Point4D)point4D).getWi() - 10);

        // norma = sqrt((dF/dX)^2 + (dF/dY)^2 + (dF/dZ)^2 + (dF/dW)^2)
        Function<PointMinimization, Double> norma = (point4D) -> Math.sqrt(
                Math.pow(gradX.apply(point4D), 2) + Math.pow(gradY.apply(point4D), 2) +
                        Math.pow(gradZ.apply(point4D), 2) + Math.pow(gradW.apply(point4D), 2)
        );
        BitStep bitStepMinimization = new BitStep(funk, norma, Arrays.asList(gradX, gradY, gradZ, gradW));

        // Inaccuracy
        double eps = 0.5;
        // Firs step
        double alfa = 0.1;

        // Start point
        Point4D point4D = new Point4D(4. , 5. , 3., 1.);

        ResultPoint resultPoint = bitStepMinimization.minimization(point4D, eps, alfa);

        boolean result = false;

        // Resolve equation : x = 1, y = 7, z = 5, w = 10
        if ((1 - eps) < resultPoint.getTeta() &&  resultPoint.getTeta() < (1 + eps) )
            if ((7 - eps) < resultPoint.getFi() &&  resultPoint.getFi() < (7 + eps) )
                if ((5 - eps) < resultPoint.getRou() &&  resultPoint.getRou() < (5 + eps) )
                    if ((10 - eps) < resultPoint.getWi() &&  resultPoint.getWi() < (10 + eps) )
                        result = true;

        Assert.assertTrue(result);
    }

    @Ignore
    @Test
    public void minimizationTestPoint4DExample2(){

        // F(x,y,z,w) = exp^((x-y)^2) + exp^((z-w)^2) + x^2 + z^2
        Function<PointMinimization, Double> funk = (point4D) -> {
            Point4D point = (Point4D)point4D;
            return Math.exp(Math.pow(point.getTeta() - point.getFi(), 2)) + Math.exp(Math.pow(point.getRou() - point.getWi(), 2)) +
                    Math.pow(point.getTeta(), 2) + Math.pow(point.getRou(), 2);
        };
        // dF/dX = 2 * (x-y) * exp^((x-y)^2) + (2 * x)
        Function<PointMinimization, Double> gradX = (point4D) -> {
            Point4D point = (Point4D)point4D;
            return 2 * (point.getTeta() - point.getFi()) * Math.exp(Math.pow(point.getTeta() - point.getFi(), 2)) + 2*point.getTeta();
        };
        // dF/dY = - (2 * (x-y) * exp^((x-y)^2) )
        Function<PointMinimization, Double> gradY = (point4D) -> {
            Point4D point = (Point4D)point4D;
            return -2 * (point.getTeta() - point.getFi()) * Math.exp(Math.pow(point.getTeta() - point.getFi(), 2));
        };
        // dF/dZ = 2 * (z-w) * exp^((z-w)^2) + 27
        Function<PointMinimization, Double> gradZ = (point4D) -> {
            Point4D point = (Point4D)point4D;
            return 2 * (point.getRou() - point.getWi()) * Math.exp(Math.pow(point.getRou() - point.getWi(), 2)) + 27;
        };
        // dF/dW = - (2 * (z-w) * exp^((z-w)^2) )
        Function<PointMinimization, Double> gradW = (point4D) -> {
            Point4D point = (Point4D)point4D;
            return -2 * (point.getRou() - point.getWi()) * Math.exp(Math.pow(point.getRou() - point.getWi(), 2));
        };

        // norma = sqrt((dF/dX)^2 + (dF/dY)^2 + (dF/dZ)^2 + (dF/dW)^2)
        Function<PointMinimization, Double> norma = (point4D) -> Math.sqrt(
                Math.pow(gradX.apply(point4D), 2) + Math.pow(gradY.apply(point4D), 2) +
                        Math.pow(gradZ.apply(point4D), 2) + Math.pow(gradW.apply(point4D), 2)
        );
        BitStep bitStepMinimization = new BitStep(funk, norma, Arrays.asList(gradX, gradY, gradZ, gradW));

        // Inaccuracy
        double eps = 0.9;
        // Firs step
        double alfa = 100;

        // Start point
        Point4D point4D = new Point4D(0. ,0. ,0.,0.);

        ResultPoint resultPoint = bitStepMinimization.minimization(point4D, eps, alfa);

        boolean result = false;

        // Resolve equation : x = 1, y = 7, z = 5, w = 10
        if ((1 - eps) < resultPoint.getTeta() &&  resultPoint.getTeta() < (1 + eps) )
            if ((7 - eps) < resultPoint.getFi() &&  resultPoint.getFi() < (7 + eps) )
                if ((5 - eps) < resultPoint.getRou() &&  resultPoint.getRou() < (5 + eps) )
                    if ((10 - eps) < resultPoint.getWi() &&  resultPoint.getWi() < (10 + eps) )
                        result = true;

        Assert.assertTrue(result);
    }
}
