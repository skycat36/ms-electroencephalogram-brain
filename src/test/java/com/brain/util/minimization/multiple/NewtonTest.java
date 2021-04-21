package com.brain.util.minimization.multiple;

import com.brain.util.minimization.point.Point2D;
import com.brain.util.minimization.point.Point4D;
import com.brain.util.minimization.point.PointMinimization;
import com.brain.util.minimization.point.ResultPoint;
import com.brain.util.minimization.single.GoldenRatioMinimizer;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class NewtonTest {

    @Test
    public void minimizationTestPoint2D(){

        // F(x,y) = (x-1)^2 + (y-2)^2
        Function<PointMinimization, Double> funk = (point2D) -> Math.pow(((Point2D)point2D).getTeta() - 1., 2.) + Math.pow(((Point2D)point2D).getFi() - 2., 2.);
        // dF/dX = 2 * (x-1)
        Function<PointMinimization, Double> gradX = (point2D) -> 2. * (((Point2D)point2D).getTeta() - 1.);
        // dF/dY = 2 * (y-2)
        Function<PointMinimization, Double> gradY = (point2D) -> 2. * (((Point2D)point2D).getFi() - 2.);

        // dF/dXdY = 2
        Function<PointMinimization, Double> dfDxDy = (point2D) -> 0.;
        // dF/dYdX = 2
        Function<PointMinimization, Double> dfDyDx = (point2D) -> 0.;

        // dF/d^2(X) = 2
        Function<PointMinimization, Double> dfD2x = (point2D) -> 2.;
        // dF/d^2(Y) = 2
        Function<PointMinimization, Double> dfD2y = (point2D) -> 2.;

        List<List<Function<PointMinimization, Double>>> matrixGeese = Arrays.asList(
                Arrays.asList(dfD2x, dfDxDy),
                Arrays.asList(dfDyDx, dfD2y)
        );

        // norma = sqrt((dF/dX)^2 + (dF/dY)^2)
        Function<PointMinimization, Double> norma = (point2D) -> Math.sqrt(Math.pow(gradX.apply(point2D), 2) + Math.pow(gradY.apply(point2D), 2));
        Newton newtonMini = new Newton(funk, norma, Arrays.asList(gradX, gradY), matrixGeese);

        // inaccuracy
        double eps = 0.0001;
        // Firs step

        // Start point
        Point2D point2D = new Point2D(4. , 5. );

        ResultPoint resultPoint = newtonMini.minimization(point2D, eps);

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

        // dF/d^2(X) = 2
        Function<PointMinimization, Double> dfD2x = (point2D) -> 2.;
        // dF/d^2(Y) = 2
        Function<PointMinimization, Double> dfD2y = (point2D) -> 2.;
        // dF/d^2(Z) = 2
        Function<PointMinimization, Double> dfD2z = (point2D) -> 2.;
        // dF/d^2(W) = 2
        Function<PointMinimization, Double> dfD2w = (point2D) -> 2.;

        // dF/dXdY = 0
        Function<PointMinimization, Double> dfDxDy = (point2D) -> 0.;
        // dF/dYdX = 0
        Function<PointMinimization, Double> dfDyDx = (point2D) -> 0.;
        // dF/dXdZ = 0
        Function<PointMinimization, Double> dfDxDz = (point2D) -> 0.;
        // dF/dZdX = 0
        Function<PointMinimization, Double> dfDzDx = (point2D) -> 0.;
        // dF/dYdZ = 0
        Function<PointMinimization, Double> dfDyDz = (point2D) -> 0.;
        // dF/dZdY = 0
        Function<PointMinimization, Double> dfDzDy = (point2D) -> 0.;
        // dF/dWdX = 0
        Function<PointMinimization, Double> dfDwDx = (point2D) -> 0.;
        // dF/dWdZ = 0
        Function<PointMinimization, Double> dfDwDz = (point2D) -> 0.;
        // dF/dWdY = 0
        Function<PointMinimization, Double> dfDwDy = (point2D) -> 0.;
        // dF/dXdW = 0
        Function<PointMinimization, Double> dfDxDw = (point2D) -> 0.;
        // dF/dYdW = 0
        Function<PointMinimization, Double> dfDyDw = (point2D) -> 0.;
        // dF/dZdW = 0
        Function<PointMinimization, Double> dfDzDw = (point2D) -> 0.;


        List<List<Function<PointMinimization, Double>>> matrixGeese = Arrays.asList(
                Arrays.asList(dfD2x, dfDxDy, dfDxDz, dfDxDw),
                Arrays.asList(dfDyDx, dfD2y, dfDyDz, dfDyDw),
                Arrays.asList(dfDzDx, dfDzDy, dfD2z, dfDzDw),
                Arrays.asList(dfDwDx, dfDwDy, dfDwDz, dfD2w)
        );

        // norma = sqrt((dF/dX)^2 + (dF/dY)^2 + (dF/dZ)^2 + (dF/dW)^2)
        Function<PointMinimization, Double> norma = (point4D) -> Math.sqrt(
                Math.pow(gradX.apply(point4D), 2) + Math.pow(gradY.apply(point4D), 2) +
                        Math.pow(gradZ.apply(point4D), 2) + Math.pow(gradW.apply(point4D), 2)
        );

        Newton newtonMini = new Newton(funk, norma, Arrays.asList(gradX, gradY, gradZ, gradW), matrixGeese);

        // Inaccuracy
        double eps = 0.001;

        // Start point
        Point4D point4D = new Point4D(4. , 5. , 3., 1.);

        ResultPoint resultPoint = newtonMini.minimization(point4D, eps);

        boolean result = false;

        // Resolve equation : x = 1, y = 7, z = 5, w = 10
        if ((1 - eps) < resultPoint.getTeta() &&  resultPoint.getTeta() < (1 + eps) )
            if ((7 - eps) < resultPoint.getFi() &&  resultPoint.getFi() < (7 + eps) )
                if ((5 - eps) < resultPoint.getRou() &&  resultPoint.getRou() < (5 + eps) )
                    if ((10 - eps) < resultPoint.getWi() &&  resultPoint.getWi() < (10 + eps) )
                        result = true;

        Assert.assertTrue(result);
    }

    //Полученная в итоге матрица Гессе сингулярна
    @Ignore
    @Test
    public void minimizationTestPoint4DExample2(){

        // F(x,y,z,w) = exp^((x-y)^2) + exp^((z-w)^2) + x^2 + z^2
        Function<PointMinimization, Double> funk = (point4D) -> {
            Point4D point = (Point4D)point4D;
            return Math.exp(Math.pow(point.getTeta() - point.getFi(), 2.)) + Math.exp(Math.pow(point.getRou() - point.getWi(), 2.)) +
                    Math.pow(point.getTeta(), 2.) + Math.pow(point.getRou(), 2.);
        };

        //______________________________________________________________________________________________________________
        // dF/dX = 2 * (x-y) * exp^((x-y)^2) + (2 * x)
        Function<PointMinimization, Double> gradX = (point4D) -> {
            Point4D point = (Point4D)point4D;
            return 2. * (point.getTeta() - point.getFi()) * Math.exp(Math.pow(point.getTeta() - point.getFi(), 2.)) + 2.*point.getTeta();
        };
        // dF/dY = - (2 * (x-y) * exp^((x-y)^2) )
        Function<PointMinimization, Double> gradY = (point4D) -> {
            Point4D point = (Point4D)point4D;
            return -2. * (point.getTeta() - point.getFi()) * Math.exp(Math.pow(point.getTeta() - point.getFi(), 2.));
        };
        // dF/dZ = 2 * (z-w) * exp^((z-w)^2) + 27
        Function<PointMinimization, Double> gradZ = (point4D) -> {
            Point4D point = (Point4D)point4D;
            return 2. * (point.getRou() - point.getWi()) * Math.exp(Math.pow(point.getRou() - point.getWi(), 2.)) + 27.;
        };
        // dF/dW = - (2 * (z-w) * exp^((z-w)^2) )
        Function<PointMinimization, Double> gradW = (point4D) -> {
            Point4D point = (Point4D)point4D;
            return -2. * (point.getRou() - point.getWi()) * Math.exp(Math.pow(point.getRou() - point.getWi(), 2.));
        };

        //______________________________________________________________________________________________________________
        // dF/d^2(X) = 4 * (x - y)^2 * exp((x - y)^2) + 2 * exp((x - y)^2) + 2
        Function<PointMinimization, Double> dfD2x = (point4D) -> {
            Point4D point = (Point4D)point4D;
            return 4. * Math.pow(point.getTeta() - point.getFi(), 2.) * Math.exp(Math.pow(point.getTeta() - point.getFi(), 2.)) +
                    2. * Math.exp(Math.pow(point.getTeta() - point.getFi(), 2.)) + 2.;
        };

        // dF/d^2(Y) = 4 * (x - y)^2 * exp((x - y)^2) + 2 * exp((x - y)^2)
        Function<PointMinimization, Double> dfD2y = (point4D) -> {
            Point4D point = (Point4D)point4D;
            return 4. * Math.pow(point.getTeta() - point.getFi(), 2.) * Math.exp(Math.pow(point.getTeta() - point.getFi(), 2.)) +
                    2. * Math.exp(Math.pow(point.getTeta() - point.getFi(), 2.));
        };

        // dF/d^2(Z) = 4 * (w - z)^2 * exp((-w + z)^2) + 2 * exp((-w + z)^2)
        Function<PointMinimization, Double> dfD2z = (point4D) -> {
            Point4D point = (Point4D)point4D;
            return 4. * Math.pow(point.getWi() - point.getRou(), 2.) * Math.exp(Math.pow(-point.getWi() + point.getRou(), 2.)) +
                    2. * Math.exp(Math.pow(-point.getWi() + point.getRou(), 2.));
        };

        // dF/d^2(W) = 4 * (w - z)^2 * exp((-w + z)^2) + 2 * exp((-w + z)^2)
        Function<PointMinimization, Double> dfD2w = (point4D) -> {
            Point4D point = (Point4D)point4D;
            return 4. * Math.pow(point.getWi() - point.getRou(), 2.) * Math.exp(Math.pow(-point.getWi() + point.getRou(), 2.)) +
                    2. * Math.exp(Math.pow(-point.getWi() + point.getRou(), 2.));
        };

        //______________________________________________________________________________________________________________
        // dF/dXdY = -4 * (x - y)^2 * exp((x - y)^2) - 2 * exp((x - y)^2)
        Function<PointMinimization, Double> dfDxDy = (point4D) -> {
            Point4D point = (Point4D)point4D;
            return -4. * Math.pow(point.getTeta() - point.getFi(), 2.) * Math.exp(Math.pow(point.getTeta() - point.getFi(), 2.)) -
                    2. * Math.exp(Math.pow(point.getTeta() - point.getFi(), 2.));
        };
        // dF/dXdZ = 0
        Function<PointMinimization, Double> dfDxDz = (point4D) -> 0.;
        // dF/dXdW = 0
        Function<PointMinimization, Double> dfDxDw = (point4D) -> 0.;

        //______________________________________________________________________________________________________________
        // dF/dYdX = -4 * (x - y)^2 * exp((x - y)^2) - 2 * exp((x - y)^2)
        Function<PointMinimization, Double> dfDyDx = (point4D) -> {
            Point4D point = (Point4D)point4D;
            return -4. * Math.pow(point.getTeta() - point.getFi(), 2.) * Math.exp(Math.pow(point.getTeta() - point.getFi(), 2.)) -
                    2. * Math.exp(Math.pow(point.getTeta() - point.getFi(), 2.));
        };
        // dF/dYdZ = 0
        Function<PointMinimization, Double> dfDyDz = (point4D) -> 0.;
        // dF/dYdW = 0
        Function<PointMinimization, Double> dfDyDw = (point4D) -> 0.;

        //______________________________________________________________________________________________________________
        // dF/dZdX = 0
        Function<PointMinimization, Double> dfDzDx = (point4D) -> 0.;
        // dF/dZdY = 0
        Function<PointMinimization, Double> dfDzDy = (point4D) -> 0.;
        // dF/dZdW = -4 * (w - z)^2 * exp((-w + z)^2) - 2 * exp((-w + z)^2)
        Function<PointMinimization, Double> dfDzDw = (point4D) -> {
            Point4D point = (Point4D)point4D;
            return -4. * Math.pow(point.getWi() - point.getRou(), 2.) * Math.exp(Math.pow(-point.getWi() + point.getRou(), 2.)) -
                    2. * Math.exp(Math.pow(-point.getWi() + point.getRou(), 2.));
        };

        //______________________________________________________________________________________________________________
        // dF/dWdX = 0
        Function<PointMinimization, Double> dfDwDx = (point4D) -> 0.;
        // dF/dWdY = 0
        Function<PointMinimization, Double> dfDwDy = (point4D) -> 0.;
        // dF/dWdZ = -4 * (w - z)^2 * exp((-w + z)^2) - 2 * exp((-w + z)^2)
        Function<PointMinimization, Double> dfDwDz = (point4D) -> {
            Point4D point = (Point4D)point4D;
            return -4. * Math.pow(point.getWi() - point.getRou(), 2.) * Math.exp(Math.pow(-point.getWi() + point.getRou(), 2.)) -
                    2. * Math.exp(Math.pow(-point.getWi() + point.getRou(), 2.));
        };
        //______________________________________________________________________________________________________________

        List<List<Function<PointMinimization, Double>>> matrixGeese = Arrays.asList(
                Arrays.asList(dfD2x, dfDxDy, dfDxDz, dfDxDw),
                Arrays.asList(dfDyDx, dfD2y, dfDyDz, dfDyDw),
                Arrays.asList(dfDzDx, dfDzDy, dfD2z, dfDzDw),
                Arrays.asList(dfDwDx, dfDwDy, dfDwDz, dfD2w)
        );

        // norma = sqrt((dF/dX)^2 + (dF/dY)^2 + (dF/dZ)^2 + (dF/dW)^2)
        Function<PointMinimization, Double> norma = (point4D) -> Math.sqrt(
                Math.pow(gradX.apply(point4D), 2.) + Math.pow(gradY.apply(point4D), 2.) +
                        Math.pow(gradZ.apply(point4D), 2.) + Math.pow(gradW.apply(point4D), 2.)
        );

        Newton newtonMini = new Newton(funk, norma, Arrays.asList(gradX, gradY, gradZ, gradW), matrixGeese);

        // Inaccuracy
        double eps = 0.5;

        // Start point
        Point4D point4D = new Point4D(7. ,4. ,4.,3.);

        ResultPoint resultPoint = newtonMini.minimization(point4D, eps);

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
