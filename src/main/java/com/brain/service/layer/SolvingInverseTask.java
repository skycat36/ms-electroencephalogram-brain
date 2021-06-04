package com.brain.service.layer;

import com.brain.service.layer.inverse.AbstractResolveInverseTask;
import com.brain.util.minimization.point.Point4D;
import com.brain.util.minimization.point.ResultPoint;
import com.brain.util.minimization.point.ExpPoint;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class SolvingInverseTask {

    private final AbstractResolveInverseTask solvingInverseProblem;

    public ResultPoint calculate(List<ExpPoint<Double>> expU, int n, double teta0, double fi0, double R1, double eps, Point4D point4D){
        return solvingInverseProblem.calculate(expU, n, teta0, fi0, R1, eps).minimization(point4D);
    }
}
