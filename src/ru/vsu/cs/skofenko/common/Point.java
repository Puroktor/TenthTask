package ru.vsu.cs.skofenko.common;

public class Point {
    private double x;
    private double y;

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double distanceTo(Point other){
        return Math.sqrt(Math.pow(x-other.getX(),2)+Math.pow(y- other.getY(),2));
    }

    @Override
    public String toString() {
        return x+" "+y;
    }
}
