package com.brain.util.minimization.point;

import lombok.Getter;

@Getter
public class ResultPoint {

    private double pointX;

    private double pointY;

    private double pointZ;

    private double pointW;

    private double potential;

    private int iterations;

    private ResultPoint(Point2D point2D, int iterations, double result){
        this.pointX = point2D.getPointX();
        this.pointY = point2D.getPointY();
        this.iterations = iterations;
        this.potential = result;
    }

    private ResultPoint(Point3D point3D, int iterations, double result){
        this.pointX = point3D.getPointX();
        this.pointY =   point3D.getPointY();
        this.pointZ =   point3D.getPointZ();
        this.iterations = iterations;
        this.potential = result;
    }

    private ResultPoint(Point4D point4D, int iterations, double result){
        this.pointX = point4D.getPointX();
        this.pointY =   point4D.getPointY();
        this.pointZ =  point4D.getPointZ();
        this.pointW =   point4D.getPointW();
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
