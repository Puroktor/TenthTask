package ru.vsu.cs.skofenko.common;

import java.util.Arrays;

public class Triangle {
    private Point p1;
    private Point p2;
    private Point p3;

    private double l1;
    private double l2;
    private double l3;

    public Triangle(Point p1, Point p2, Point p3) throws NotTriangle {
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        CheckIsTriangle();
    }

    private void CheckIsTriangle() throws NotTriangle {
        double a = getLength(p1, p2);
        double b = getLength(p1, p3);
        double c = getLength(p2, p3);
        if (a + b > c && a + c > b && b + c > a) {
            Double[] arr = {a, b, c};
            Arrays.sort(arr);
            l1 = arr[0];
            l2 = arr[1];
            l3 = arr[2];
        } else {
            throw new NotTriangle();
        }
    }

    private double getLength(Point point1, Point point2) {
        return Math.sqrt(Math.pow(point1.getX() - point2.getX(), 2) + Math.pow(point1.getY() - point2.getY(), 2));
    }

    public double getL1() {
        return l1;
    }

    public double getL2() {
        return l2;
    }

    public double getL3() {
        return l3;
    }

    public Point getP1() {
        return p1;
    }

    public Point getP2() {
        return p2;
    }

    public Point getP3() {
        return p3;
    }

    @Override
    public String toString() {
        return p1.toString() + "\n" + p2.toString() + "\n" + p3.toString();
    }
}
