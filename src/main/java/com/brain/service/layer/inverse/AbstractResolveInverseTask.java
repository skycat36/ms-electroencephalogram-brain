package com.brain.service.layer.inverse;

import com.brain.util.function.CoefficientUtils;
import com.brain.util.minimization.multiple.MultipleMinimization;
import com.brain.util.minimization.point.ExpPoint;

import java.util.List;
import java.util.function.Function;

public abstract class AbstractResolveInverseTask {

    /**
     * Решение обратной задачи
     * @param n количество слоев
     * @param R1 значение ограничивающие возникновение импульса (ед. изм. )
     * @return возвращает силу потенцала в точке
     */
    public abstract MultipleMinimization calculate(List<ExpPoint<Double>> expU, int n, double teta0, double fi0, double R1, double eps);

    protected static double localizationNeuralSource(List<ExpPoint<Double>> expU, int n, double teta0, double fi0, double mX, double mY, double mZ, double rD) {

        double result = expU.stream()
                .mapToDouble(tr ->
                        tr.getResult() - mX * CoefficientUtils.coefficientSum(n, rD, (L) -> CoefficientUtils.coefficientAL(tr.getPointX(), tr.getPointY(), teta0, fi0, L)) -
                                mY * CoefficientUtils.coefficientSum(n, rD, (L) -> CoefficientUtils.coefficientCL(tr.getPointX(), tr.getPointY(), teta0, fi0, L)) -
                                mZ * CoefficientUtils.coefficientSum(n, rD, (L) -> L * CoefficientUtils.coefficientBL(tr.getPointX(), tr.getPointY(), teta0, fi0, L)))
                .sum();

        return Math.pow(result, 2);
    }


    protected static double derivativeLocalizationNeuralSource(int n, double teta, double fi, double teta0, double fi0, double mX, double mY, double mZ, double R1, double rD) {

        Function<Integer, Double> subFunction = (L) -> (-L - 1) / R1;

        double result =  (mX * CoefficientUtils.coefficientSum(n, rD, (L) -> subFunction.apply(L) * CoefficientUtils.coefficientAL(teta, fi, teta0, fi0, L)) +
                mY * CoefficientUtils.coefficientSum(n, rD, (L) -> subFunction.apply(L) * CoefficientUtils.coefficientCL(teta, fi, teta0, fi0, L)) +
                mZ * CoefficientUtils.coefficientSum(n, rD, (L) -> subFunction.apply(L) * L * CoefficientUtils.coefficientBL(teta, fi, teta0, fi0, L)));
        return Math.pow(result, 2);
    }
}