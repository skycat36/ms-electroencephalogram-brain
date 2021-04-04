package com.brain.util.minimization.multiple;


import com.brain.util.function.CoefficientUtils;
import com.brain.util.minimization.point.PointMinimization;
import com.brain.util.minimization.point.ResultPoint;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Data
@RequiredArgsConstructor
public class BitStep {

    private final Function<PointMinimization, Double> func;
    private final Function<PointMinimization, Double> norma;
    private final List<Function<PointMinimization, Double>> gradArg;

    public ResultPoint minimization(PointMinimization point, double eps, double alfa)
    {
        double nor = norma.apply(point);
        double f = func.apply(point);

        int iterations = 0;

        List<Double> arrGrad = calcGradFunk(point);
        PointMinimization newPoint = point.sum(CoefficientUtils.listMul(arrGrad, -alfa));

        while (nor > eps)
        {

            if (func.apply(newPoint) < f)
            {
                point = newPoint;
                f = func.apply(point);
                nor = norma.apply(point);
                arrGrad = calcGradFunk(point);
            }
            else
                alfa /= 2.0;

            newPoint = newPoint.sum(CoefficientUtils.listMul(arrGrad, -alfa));

            iterations++;
        }

        return ResultPoint.getResultPoint(point, iterations, f);
    }

    private List<Double> calcGradFunk(PointMinimization point){
        return this.gradArg.stream().map(func -> func.apply(point)).collect(Collectors.toList());
    }

    private List<Double> getArrTPoint(PointMinimization point, double alfa){
        return point.getParamList().stream()
                .map(arg -> -(arg.doubleValue() * alfa))
                .collect(Collectors.toList());
    }
}
