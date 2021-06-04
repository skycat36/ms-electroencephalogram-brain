package com.brain.util.minimization.point;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Point2D implements PointMinimization {
    private static int COUNT_FIELD = Point2D.class.getDeclaredFields().length - 1;

    private double pointX;
    private double pointY;

    @Override
    public List<? extends Number> getParamList() {
        return Arrays.asList(pointX, pointY);
    }

    @Override
    public PointMinimization sum(List<? extends Number> arguments) {
        if (getParamList().size() != arguments.size()) throw new RuntimeException("Count field must be equals count arguments!");

        return new Point2D(
                this.pointX + arguments.get(0).doubleValue(),
                this.pointY + arguments.get(1).doubleValue());
    }
}
