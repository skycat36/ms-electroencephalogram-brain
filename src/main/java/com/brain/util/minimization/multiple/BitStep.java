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
public class BitStep implements MultipleMinimization {

    private final Function<PointMinimization, Double> func;
    private final Function<PointMinimization, Double> norma;
    private final List<Function<PointMinimization, Double>> gradArg;
    private final SingleArgumentFunctionMinimizer singleArgumentFunctionMinimizer;
    private final double eps;

    @Override
    public ResultPoint minimization(PointMinimization point) {
        double alfa = 2;
        double nor = norma.apply(point);
        double f = func.apply(point);

        int iterations = 0;

        List<Double> arrGrad = FunctionHelper.calcFunkList(gradArg,point);
        PointMinimization newPoint = point.sum(CoefficientUtils.listMul(arrGrad, -alfa));
        double prevVal;
        int prevCh = 0;
        int zn = -1;

        while (Double.isNaN(nor) || nor > eps) {
            int finalZn = zn;
            if (func.apply(newPoint) < f) {
                point = newPoint;
                arrGrad = FunctionHelper.calcFunkList(gradArg, point);
            }
            else {
                PointMinimization tempPoint = newPoint;
                List<Double> tempGrad = arrGrad;
                alfa = singleArgumentFunctionMinimizer.minimize((a) -> func.apply(tempPoint.sum(CoefficientUtils.listMul(tempGrad, finalZn * a))), -5, 5, 0.5);
            }

            prevVal = nor;
            newPoint = newPoint.sum(CoefficientUtils.listMul(arrGrad, zn * alfa));
            nor = norma.apply(newPoint);

            if (prevCh > 3){
                zn *= -1;
                prevCh = 0;
            } else {
                prevCh += prevVal < nor ? 1 : 0;
            }
            iterations++;
        }

        return ResultPoint.getResultPoint(point, iterations, func.apply(point));
    }

}
