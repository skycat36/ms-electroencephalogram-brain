package com.brain.util.minimization.point;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Point3D implements PointMinimization {

    private double pointX;

    private double pointY;

    private double pointZ;

    @Override
    public List<? extends Number> getParamList() {
        return Arrays.asList(this.pointX, this.pointY, this.pointZ);
    }

    @Override
    public PointMinimization sum(List<? extends Number> arguments) {
        if (getParamList().size() != arguments.size()) throw new RuntimeException("Count pointYeld must be equals count arguments!");

        return new Point3D(
                this.pointX + arguments.get(0).doubleValue(),
                this.pointY + arguments.get(1).doubleValue(),
                this.pointZ + arguments.get(2).doubleValue()
        );
    }
}
