package com.brain.util.minimization.point;

import java.util.List;

public interface PointMinimization {

    List<? extends Number> getParamList();

    PointMinimization sum(List<? extends Number> arguments);
}
