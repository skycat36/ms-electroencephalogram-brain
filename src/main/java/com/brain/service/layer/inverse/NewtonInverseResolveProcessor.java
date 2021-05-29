//package com.brain.service.layer.inverse;
//
//import com.brain.util.function.FunctionHelper;
//import com.brain.util.function.gradient.GradDerivativeLocalizationNeuralSource;
//import com.brain.util.function.gradient.GradLocalizationNeuralSource;
//import com.brain.util.minimization.multiple.BitStep;
//import com.brain.util.minimization.multiple.MultipleMinimization;
//import com.brain.util.minimization.multiple.Newton;
//import com.brain.util.minimization.point.Point3D;
//import com.brain.util.minimization.point.Point4D;
//import com.brain.util.minimization.point.PointMinimization;
//import com.brain.util.minimization.single.GoldenRatioMinimizer;
//
//import java.util.Arrays;
//import java.util.List;
//import java.util.function.Function;
//
//public class NewtonInverseResolveProcessor extends AbstractResolveInverseTask {
//
//    @Override
//    public MultipleMinimization calculate(double expU, int n, double step, double area, double teta0, double fi0, double R1, double eps) {
//        Function<PointMinimization, Double> funk = (point3D) -> {
//            Point3D point = (Point3D)point3D;
//            return FunctionHelper.iterateByArrayFunction(0, area, 2 * area, step,
//                    (i, j) -> localizationNeuralSource(expU, n, i, j, teta0, fi0, point.getTeta(), point.getFi(), point.getRou(), 1.)
////                            +  derivativeLocalizationNeuralSource(n, i, j, teta0, fi0, point.getTeta(), point.getFi(), point.getRou(), R1, 1.)
//            );
//        };
//
//        Function<PointMinimization, Double> gradX = (point3D) -> {
//            Point3D point = (Point3D)point3D;
//            return FunctionHelper.iterateByArrayFunction(0, area, 2 * area, step,
//                    (i, j) -> //GradDerivativeLocalizationNeuralSource.dFMx(n, i, j, teta0, fi0, point.getTeta(), point.getFi(), point.getRou(), R1, 1.) +
//                            GradLocalizationNeuralSource.dFMx(expU, n, i, j, teta0, fi0, point.getTeta(), point.getFi(), point.getRou(), 1.)
//            );
//        };
//
//        Function<PointMinimization, Double> gradY = (point3D) -> {
//            Point3D point = (Point3D)point3D;
//            return FunctionHelper.iterateByArrayFunction(0, area, 2 * area, step,
//                    (i, j) -> //GradDerivativeLocalizationNeuralSource.dFMy(n, i, j, teta0, fi0, point.getTeta(), point.getFi(), point.getRou(), R1, 1.) +
//                            GradLocalizationNeuralSource.dFMy(expU, n, i, j, teta0, fi0, point.getTeta(), point.getFi(), point.getRou(), 1.)
//            );
//        };
//
//        Function<PointMinimization, Double> gradZ = (point3D) -> {
//            Point3D point = (Point3D)point3D;
//            return FunctionHelper.iterateByArrayFunction(0, area, 2 * area, step,
//                    (i, j) -> //GradDerivativeLocalizationNeuralSource.dFMz(n, i, j, teta0, fi0, point.getTeta(), point.getFi(), point.getRou(), R1, 1.) +
//                            GradLocalizationNeuralSource.dFMz(expU, n, i, j, teta0, fi0, point.getTeta(), point.getFi(), point.getRou(), 1.)
//            );
//        };
//
////        Function<PointMinimization, Double> gradW = (point3D) -> {
////            Point3D point = (Point3D)point3D;
////            return FunctionHelper.iterateByArrayFunction(0, area, 2 * area, step,
////                    (i, j) -> //GradDerivativeLocalizationNeuralSource.dFrd(n, i, j, teta0, fi0, point.getTeta(), point.getFi(), point.getRou(), R1, 1.) +
////                            GradLocalizationNeuralSource.dFRd(expU, n, i, j, teta0, fi0, point.getTeta(), point.getFi(), point.getRou(), 1.)
////            );
////        };
//
//        // dF/d^2(X)
//        Function<PointMinimization, Double> dfD2x = (point3D) -> {
//            Point3D point = (Point3D)point3D;
//            return FunctionHelper.iterateByArrayFunction(0, area, 2 * area, step,
//                    (i, j) -> //GradDerivativeLocalizationNeuralSource.d2FMx(n, i, j, teta0, fi0, R1, 1.) +
//                            GradLocalizationNeuralSource.d2FMx(n, i, j, teta0, fi0, 1.)
//            );
//        };
//
//        // dF/d^2(Y)
//        Function<PointMinimization, Double> dfD2y = (point3D) -> {
//            Point3D point = (Point3D)point3D;
//            return FunctionHelper.iterateByArrayFunction(0, area, 2 * area, step,
//                    (i, j) -> //GradDerivativeLocalizationNeuralSource.d2FMy(n, i, j, teta0, fi0, R1, 1.) +
//                            GradLocalizationNeuralSource.d2FMy(n, i, j, teta0, fi0, 1.)
//            );
//        };
//
//        // dF/d^2(Z)
//        Function<PointMinimization, Double> dfD2z = (point3D) -> {
//            Point3D point = (Point3D)point3D;
//            return FunctionHelper.iterateByArrayFunction(0, area, 2 * area, step,
//                    (i, j) -> //GradDerivativeLocalizationNeuralSource.d2FMz(n, i, j, teta0, fi0, R1, 1.) +
//                            GradLocalizationNeuralSource.d2FMz(n, i, j, teta0, fi0, 1.)
//            );
//        };
//
//        // dF/d^2(W)
////        Function<PointMinimization, Double> dfD2w = (point3D) -> {
////            Point3D point = (Point3D)point3D;
////            return FunctionHelper.iterateByArrayFunction(0, area, 2 * area, step,
////                    (i, j) -> //GradDerivativeLocalizationNeuralSource.d2FRd(n, i, j, teta0, fi0, R1,  point.getFi(), point.getRou(), 1., 1.) +
////                            GradLocalizationNeuralSource.d2FRd(expU, n, i, j, teta0, fi0, point.getTeta(), point.getFi(), point.getRou(), 1.)
////            );
////        };
//
//
//        // dF/dXdY
//        Function<PointMinimization, Double> dfDxDy = (point3D) -> {
//            Point3D point = (Point3D)point3D;
//            return FunctionHelper.iterateByArrayFunction(0, area, 2 * area, step,
//                    (i, j) -> //GradDerivativeLocalizationNeuralSource.dFMxMy(n, i, j, teta0, fi0, R1, 1.) +
//                            GradLocalizationNeuralSource.dFMxMy(n, i, j, teta0, fi0, 1.)
//            );
//        };
//
//        // dF/dYdX
//        Function<PointMinimization, Double> dfDyDx = (point3D) -> {
//            Point3D point = (Point3D)point3D;
//            return FunctionHelper.iterateByArrayFunction(0, area, 2 * area, step,
//                    (i, j) -> //GradDerivativeLocalizationNeuralSource.dFMyMx(n, i, j, teta0, fi0, R1, 1.) +
//                            GradLocalizationNeuralSource.dFMyMx(n, i, j, teta0, fi0, 1.)
//            );
//        };
//
//        // dF/dXdZ
//        Function<PointMinimization, Double> dfDxDz = (point3D) -> {
//            Point3D point = (Point3D)point3D;
//            return FunctionHelper.iterateByArrayFunction(0, area, 2 * area, step,
//                    (i, j) -> //GradDerivativeLocalizationNeuralSource.dFMxMz(n, i, j, teta0, fi0, R1, 1.) +
//                            GradLocalizationNeuralSource.dFMxMz(n, i, j, teta0, fi0, 1.)
//            );
//        };
//
//        // dF/dZdX
//        Function<PointMinimization, Double> dfDzDx = (point3D) -> {
//            Point3D point = (Point3D)point3D;
//            return FunctionHelper.iterateByArrayFunction(0, area, 2 * area, step,
//                    (i, j) -> //GradDerivativeLocalizationNeuralSource.dFMzMx(n, i, j, teta0, fi0, R1, 1.) +
//                            GradLocalizationNeuralSource.dFMzMx(n, i, j, teta0, fi0, 1.)
//            );
//        };
//
//        // dF/dYdZ
//        Function<PointMinimization, Double> dfDyDz = (point3D) -> {
//            Point3D point = (Point3D)point3D;
//            return FunctionHelper.iterateByArrayFunction(0, area, 2 * area, step,
//                    (i, j) -> //GradDerivativeLocalizationNeuralSource.dFMyMz(n, i, j, teta0, fi0, R1, 1.) +
//                            GradLocalizationNeuralSource.dFMyMz(n, i, j, teta0, fi0, 1.)
//            );
//        };
//
//        // dF/dZdY
//        Function<PointMinimization, Double> dfDzDy = (point3D) -> {
//            Point3D point = (Point3D)point3D;
//            return FunctionHelper.iterateByArrayFunction(0, area, 2 * area, step,
//                    (i, j) -> //GradDerivativeLocalizationNeuralSource.dFMzMy(n, i, j, teta0, fi0, R1, 1.) +
//                            GradLocalizationNeuralSource.dFMzMy(n, i, j, teta0, fi0, 1.)
//            );
//        };
//
//        // dF/dWdX
////        Function<PointMinimization, Double> dfDwDx = (point3D) -> {
////            Point3D point = (Point3D)point3D;
////            return FunctionHelper.iterateByArrayFunction(0, area, 2 * area, step,
////                    (i, j) -> //GradDerivativeLocalizationNeuralSource.dFRdMx(n, i, j, teta0, fi0, R1, 1.) +
////                            GradLocalizationNeuralSource.dFRdMx(expU, n, i, j, teta0, fi0, point.getTeta(), point.getFi(), point.getRou(), 1.)
////            );
////        };
////
////        // dF/dWdZ
////        Function<PointMinimization, Double> dfDwDz = (point3D) -> {
////            Point3D point = (Point3D)point3D;
////            return FunctionHelper.iterateByArrayFunction(0, area, 2 * area, step,
////                    (i, j) -> //GradDerivativeLocalizationNeuralSource.dFRdMz(n, i, j, teta0, fi0, R1, 1.) +
////                            GradLocalizationNeuralSource.dFRdMz(expU, n, i, j, teta0, fi0, point.getTeta(), point.getFi(), point.getRou(), 1.)
////            );
////        };
////
////        // dF/dWdY
////        Function<PointMinimization, Double> dfDwDy = (point3D) -> {
////            Point3D point = (Point3D)point3D;
////            return FunctionHelper.iterateByArrayFunction(0, area, 2 * area, step,
////                    (i, j) -> //GradDerivativeLocalizationNeuralSource.dFRdMy(n, i, j, teta0, fi0, R1, 1.) +
////                            GradLocalizationNeuralSource.dFRdMy(expU, n, i, j, teta0, fi0, point.getTeta(), point.getFi(), point.getRou(), 1.)
////            );
////        };
////
////        // dF/dXdW
////        Function<PointMinimization, Double> dfDxDw = (point3D) -> {
////            Point3D point = (Point3D)point3D;
////            return FunctionHelper.iterateByArrayFunction(0, area, 2 * area, step,
////                    (i, j) -> //GradDerivativeLocalizationNeuralSource.dFMxRd(n, i, j, teta0, fi0, R1, 1.) +
////                            GradLocalizationNeuralSource.dFMxRd(expU, n, i, j, teta0, fi0, point.getTeta(), point.getFi(), point.getRou(), 1.)
////            );
////        };
////
////        // dF/dYdW
////        Function<PointMinimization, Double> dfDyDw = (point3D) -> {
////            Point3D point = (Point3D)point3D;
////            return FunctionHelper.iterateByArrayFunction(0, area, 2 * area, step,
////                    (i, j) -> //GradDerivativeLocalizationNeuralSource.dFMyRd(expU, n, i, j, teta0, fi0, R1, 1.) +
////                            GradLocalizationNeuralSource.dFMyRd(expU, n, i, j, teta0, fi0, point.getTeta(), point.getFi(), point.getRou(), 1.)
////            );
////        };
////
////        // dF/dZdW
////        Function<PointMinimization, Double> dfDzDw = (point3D) -> {
////            Point3D point = (Point3D)point3D;
////            return FunctionHelper.iterateByArrayFunction(0, area, 2 * area, step,
////                    (i, j) -> //GradDerivativeLocalizationNeuralSource.dFMzRd(n, i, j, teta0, fi0, R1, 1.) +
////                            GradLocalizationNeuralSource.dFMzRd(expU, n, i, j, teta0, fi0, point.getTeta(), point.getFi(), point.getRou(), 1.)
////            );
////        };
//
//
//        List<List<Function<PointMinimization, Double>>> matrixGeese = Arrays.asList(
//                Arrays.asList(dfD2x, dfDxDy, dfDxDz),
//                Arrays.asList(dfDyDx, dfD2y, dfDyDz),
//                Arrays.asList(dfDzDx, dfDzDy, dfD2z)
////                Arrays.asList(dfDwDx, dfDwDy, dfDwDz, dfD2w)
//        );
//
//        Function<PointMinimization, Double> norma = (point3D) -> Math.sqrt(
//                Math.pow(gradX.apply(point3D), 2.) + Math.pow(gradY.apply(point3D), 2.) +
//                        Math.pow(gradZ.apply(point3D), 2.)
//                        //+ Math.pow(gradW.apply(point3D), 2.)
//        );
//
//
//        return new Newton(funk, norma, Arrays.asList(gradX, gradY, gradZ),
//                matrixGeese, eps);
//    }
//}
