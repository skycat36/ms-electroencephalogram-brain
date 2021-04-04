package com.brain.util.minimization.point;

import lombok.Getter;

@Getter
public class ResultPoint {

    private double teta;

    private double fi;

    private double rou;

    private double wi;

    private double potential;

    private int iterations;

    private ResultPoint(Point2D point2D, int iterations, double result){
        this.teta = point2D.getTeta();
        this.fi = point2D.getFi();
        this.iterations = iterations;
        this.potential = result;
    }

    private ResultPoint(Point3D point3D, int iterations, double result){
        this.teta = point3D.getTeta();
        this.fi =   point3D.getFi();
        this.wi =   point3D.getRou();
        this.iterations = iterations;
        this.potential = result;
    }

    private ResultPoint(Point4D point4D, int iterations, double result){
        this.teta = point4D.getTeta();
        this.fi =   point4D.getFi();
        this.rou =  point4D.getRou();
        this.wi =   point4D.getWi();
        this.iterations = iterations;
        this.potential = result;
    }

    public static ResultPoint getResultPoint(PointMinimization pointMinimization, int iterations, double result){
        if (pointMinimization instanceof Point2D){
            return new ResultPoint((Point2D)pointMinimization, iterations, result);
        }

        if (pointMinimization instanceof Point3D){
            return new ResultPoint((Point3D)pointMinimization, iterations, result);
        }

        if (pointMinimization instanceof Point4D){
            return new ResultPoint((Point4D)pointMinimization, iterations, result);
        }

        throw new RuntimeException("Processing this point not possible!");
    }

}
