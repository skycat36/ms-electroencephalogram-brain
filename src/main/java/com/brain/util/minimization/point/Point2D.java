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

    private double teta;
    private double fi;

    public static Point2D sub(Point2D a, Point2D b) {
        return new Point2D(a.getTeta() - b.getTeta(), a.getFi() - b.getFi());
    }

    public static Point2D mul(Point2D a, double s) {
        return new Point2D(a.getTeta() * s, a.getFi() * s);
    }

    @Override
    public String toString() {
        return '{' + "teta = " + teta + ", y = " + fi + '}';
    }

    @Override
    public List<? extends Number> getParamList() {
        return Arrays.asList(teta, fi);
    }

    @Override
    public PointMinimization sum(List<? extends Number> arguments) {
        if (getParamList().size() != arguments.size()) throw new RuntimeException("Count field must be equals count arguments!");

        return new Point2D(
                this.teta + arguments.get(0).doubleValue(),
                this.fi + arguments.get(1).doubleValue());
    }
}
