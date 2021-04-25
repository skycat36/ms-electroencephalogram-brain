package com.brain.service.layer;

import com.brain.service.layer.inverse.AbstractResolveInverseTask;
import com.brain.util.minimization.point.Point4D;
import com.brain.util.minimization.point.ResultPoint;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class SolvingInverseTask {

    private final AbstractResolveInverseTask solvingInverseProblem;

    public ResultPoint calculate(double expU, int n, double step, double area, double teta0, double fi0, double R1, double eps){
        // Start point
        Point4D point4D = new Point4D(1. , 2. , 1., 1.);

        return solvingInverseProblem.calculate(expU, n, step, area, teta0, fi0, R1, eps).minimization(point4D);
    }
}
