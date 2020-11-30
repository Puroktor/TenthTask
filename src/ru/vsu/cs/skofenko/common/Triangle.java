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
        checkIsTriangle();
    }

    private void checkIsTriangle() throws NotTriangle {
        double a = p1.distanceTo(p2);
        double b = p1.distanceTo(p3);
        double c = p2.distanceTo(p3);
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

    public boolean isSimilarTo(Triangle other){
        double c1 = l1/other.getL1();
        double c2 = l2/other.getL2();
        double c3 = l3/other.getL3();
        return Math.abs(c1 - c2) < 1e-7 && Math.abs(c2 - c3) < 1e-7;
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
