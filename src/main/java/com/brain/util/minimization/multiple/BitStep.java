package com.brain.util.minimization.multiple;


import com.brain.util.function.CoefficientUtils;
import com.brain.util.function.FunctionHelper;
import com.brain.util.minimization.point.PointMinimization;
import com.brain.util.minimization.point.ResultPoint;
import com.brain.util.minimization.single.SingleArgumentFunctionMinimizer;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.function.Function;

@Data
@RequiredArgsConstructor
public class BitStep {

    private final Function<PointMinimization, Double> func;
    private final Function<PointMinimization, Double> norma;
    private final List<Function<PointMinimization, Double>> gradArg;

    public ResultPoint minimization(PointMinimization point, double eps, double alfa, SingleArgumentFunctionMinimizer singleArgumentFunctionMinimizer)
    {
        double nor = norma.apply(point);
        double f = func.apply(point);

        int iterations = 0;

        List<Double> arrGrad = FunctionHelper.calcFunkList(gradArg,point);
        PointMinimization newPoint = point.sum(CoefficientUtils.listMul(arrGrad, -alfa));

        while (nor > eps)
        {

            if (func.apply(newPoint) < f)
            {
                point = newPoint;
                f = func.apply(point);
                nor = norma.apply(point);
                arrGrad = FunctionHelper.calcFunkList(gradArg, point);
            }
            else {
                PointMinimization tempPoint = newPoint;
                List<Double> tempGrad = arrGrad;
                alfa = singleArgumentFunctionMinimizer.minimize((a) -> func.apply(tempPoint.sum(CoefficientUtils.listMul(tempGrad, a))), 0, 1, eps);
            }

            newPoint = newPoint.sum(CoefficientUtils.listMul(arrGrad, -alfa));

            iterations++;
        }

        return ResultPoint.getResultPoint(point, iterations, f);
    }

}
