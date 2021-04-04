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

    private double teta;

    private double fi;

    private double rou;

    @Override
    public List<? extends Number> getParamList() {
        return Arrays.asList(this.teta, this.fi, this.rou);
    }

    @Override
    public PointMinimization sum(List<? extends Number> arguments) {
        if (getParamList().size() != arguments.size()) throw new RuntimeException("Count field must be equals count arguments!");

        return new Point3D(
                this.teta + arguments.get(0).doubleValue(),
                this.fi + arguments.get(1).doubleValue(),
                this.rou + arguments.get(2).doubleValue()
        );
    }
}
