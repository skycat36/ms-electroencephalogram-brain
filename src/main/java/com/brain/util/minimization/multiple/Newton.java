package com.brain.util.minimization.multiple;

import com.brain.converter.MatrixConverter;
import com.brain.util.function.CoefficientUtils;
import com.brain.util.function.FunctionHelper;
import com.brain.util.matrix.MatrixHelper;
import com.brain.util.minimization.point.PointMinimization;
import com.brain.util.minimization.point.ResultPoint;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Objects;
import java.util.function.Function;

@Data
@RequiredArgsConstructor
public class Newton implements MultipleMinimization {

    private final Function<PointMinimization, Double> func;
    private final Function<PointMinimization, Double> norma;
    private final List<Function<PointMinimization, Double>> gradArg;
    private final List<List<Function<PointMinimization, Double>>> matrixGeese;
    private final double eps;

    @Override
    public ResultPoint minimization(PointMinimization point) {
        double nor = norma.apply(point);
        List<Double> arrGrad = FunctionHelper.calcFunkList(gradArg, point);


        int iterations = 0;
        PointMinimization newPoint;

        while (nor > eps) {

            List<Double> mulGesse = Objects.requireNonNull(MatrixConverter.convertMatrixToVector(
                            MatrixConverter.convert(MatrixHelper.multiply(arrGrad, MatrixHelper.inverse(FunctionHelper.calcFunkMatrix(matrixGeese, point))).getData()))
            );

            newPoint = point.sum(CoefficientUtils.listMul(mulGesse, -1.));
            arrGrad = FunctionHelper.calcFunkList(gradArg, newPoint);
            nor = norma.apply(newPoint);

            point = newPoint;
            iterations++;
        }

        return ResultPoint.getResultPoint(point, iterations, func.apply(point));
    }

}
