package com.brain.util.minimization;

public class Point2D {
    private double x;
    private double y;

    public Point2D() {
        this.x = 0f;
        this.y = 0f;
    }

    public Point2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public static Point2D sub(Point2D a, Point2D b) {
        return new Point2D(a.getX() - b.getX(), a.getY() - b.getY());
    }

    public static Point2D mul(Point2D a, double s) {
        return new Point2D(a.getX() * s, a.getY() * s);
    }

    @Override
    public String toString() {
        return '{' + "x = " + x + ", y = " + y + '}';
    }
}
