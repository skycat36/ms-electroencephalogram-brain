package com.brain.service.layer.inverse;

import com.brain.util.function.CoefficientUtils;
import com.brain.util.function.FunctionHelper;
import com.brain.util.function.gradient.GradDerivativeLocalizationNeuralSource;
import com.brain.util.function.gradient.GradLocalizationNeuralSource;
import com.brain.util.minimization.multiple.BitStep;
import com.brain.util.minimization.multiple.MultipleMinimization;
import com.brain.util.minimization.point.Point4D;
import com.brain.util.minimization.point.PointMinimization;
import com.brain.util.minimization.point.ResultPoint;
import com.brain.util.minimization.single.GoldenRatioMinimizer;

import java.util.Arrays;
import java.util.function.Function;

public abstract class AbstractResolveInverseTask {

    /**
     * Решение обратной задачи
     * @param n количество слоев
     * @param R1 значение ограничивающие возникновение импульса (ед. изм. )
     * @return возвращает силу потенцала в точке
     */
    public abstract MultipleMinimization calculate(double expU, int n, double step, double area, double teta0, double fi0, double R1, double eps);

    protected static double localizationNeuralSource(double expU, int n, double teta, double fi, double teta0, double fi0, double mX, double mY, double mZ, double rD) {

        double result = (- mX * CoefficientUtils.coefficientSum(n, rD, (L) -> CoefficientUtils.coefficientAL(teta, fi, teta0, fi0, L)) -
                        mY * CoefficientUtils.coefficientSum(n, rD, (L) -> CoefficientUtils.coefficientCL(teta, fi, teta0, fi0, L)) -
                        mZ * CoefficientUtils.coefficientSum(n, rD, (L) -> L * CoefficientUtils.coefficientBL(teta, fi, teta0, fi0, L)));

        return Math.pow(expU + result, 2);
    }


    protected static double derivativeLocalizationNeuralSource(int n, double teta, double fi, double teta0, double fi0, double mX, double mY, double mZ, double R1, double rD) {

        Function<Integer, Double> subFunction = (L) -> (-L - 1) / R1;

        double result =  (mX * CoefficientUtils.coefficientSum(n, rD, (L) -> subFunction.apply(L) * CoefficientUtils.coefficientAL(teta, fi, teta0, fi0, L)) +
                mY * CoefficientUtils.coefficientSum(n, rD, (L) -> subFunction.apply(L) * CoefficientUtils.coefficientCL(teta, fi, teta0, fi0, L)) +
                mZ * CoefficientUtils.coefficientSum(n, rD, (L) -> subFunction.apply(L) * L * CoefficientUtils.coefficientBL(teta, fi, teta0, fi0, L)));
        return Math.pow(result, 2);
    }
}