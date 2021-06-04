package com.brain.util.minimization.point;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Point4D implements PointMinimization {

    private double pointX;

    private double pointY;

    private double pointZ;

    private double pointW;

    @Override
    public List<? extends Number> getParamList() {
        return Arrays.asList(pointX, pointY, pointZ, pointW);
    }

    @Override
    public PointMinimization sum(List<? extends Number> arguments) {
        if (getParamList().size() != arguments.size()) throw new RuntimeException("Count field must be equals count arguments!");

        return new Point4D(
                this.pointX + arguments.get(0).doubleValue(),
                this.pointY + arguments.get(1).doubleValue(),
                this.pointZ + arguments.get(2).doubleValue(),
                this.pointW + arguments.get(3).doubleValue()
        );
    }
}
