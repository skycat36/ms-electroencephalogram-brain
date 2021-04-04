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

    private double teta;

    private double fi;

    private double rou;

    private double wi;

    @Override
    public List<? extends Number> getParamList() {
        return Arrays.asList(teta, fi, rou, wi);
    }

    @Override
    public PointMinimization sum(List<? extends Number> arguments) {
        if (getParamList().size() != arguments.size()) throw new RuntimeException("Count field must be equals count arguments!");

        return new Point4D(
                this.teta + arguments.get(0).doubleValue(),
                this.fi + arguments.get(1).doubleValue(),
                this.rou + arguments.get(2).doubleValue(),
                this.wi + arguments.get(3).doubleValue()
        );
    }
}
