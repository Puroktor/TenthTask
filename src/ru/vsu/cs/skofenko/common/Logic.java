package ru.vsu.cs.skofenko.common;

import ru.vsu.cs.util.ArrayUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

public class Logic {

    public static List<Triangle> readListFromFile(String path) throws FileNotFoundException, NullPointerException,
            ArrayIndexOutOfBoundsException, NotTriangle, NumberFormatException {
        try (Scanner scanner = new Scanner(new File(path))) {
            List<Triangle> list = new LinkedList<>();
            while (scanner.hasNext()) {
                Point[] points = new Point[3];
                for (int i = 0; i < 3; i++) {
                    String line = scanner.nextLine();
                    String[] xy = line.split(" ");
                    points[i] = new Point(Double.parseDouble(xy[0]), Double.parseDouble(xy[1]));
                }
                list.add(new Triangle(points[0], points[1], points[2]));
            }
            return list;
        }
    }

    public static List<List<Triangle>> findSimilarTriangles(List<Triangle> list) {
        List<List<Triangle>> answer = new ArrayList<>();
        while (list.size() != 0) {
            List<Triangle> nowSimilar = new ArrayList<>();
            Triangle now = list.get(0);
            list.remove(0);
            nowSimilar.add(now);
            for (int i = 0; i < list.size(); i++) {
                double c1 = now.getL1() / list.get(i).getL1();
                double c2 = now.getL2() / list.get(i).getL2();
                double c3 = now.getL3() / list.get(i).getL3();
                if (Math.abs(c1 - c2) < 1e-7 && Math.abs(c2 - c3) < 1e-7) {
                    nowSimilar.add(list.get(i));
                    list.remove(i);
                    i--;
                }
            }
            answer.add(nowSimilar);
        }
        return answer;
    }

    public static void writeAnswerToFile(String outputFile, List<List<Triangle>> answer) throws FileNotFoundException {
        try (PrintWriter pw = new PrintWriter(outputFile)) {
            if (answer.size() > 0)
                pw.println("--------------------");
            for (List<Triangle> element : answer) {
                for (Triangle tr : element) {
                    pw.println(tr.toString());
                }
                pw.println("--------------------");
            }
        }
    }

    public static void writeMatrixToFile(String outputFile, double[][] mat) throws FileNotFoundException {
        try (PrintWriter pw = new PrintWriter(outputFile)) {
            if (mat != null && mat.length > 0 && mat[0].length == 2) {
                for (double[] doubles : mat) {
                    if (Double.isNaN(doubles[0]) && Double.isNaN(doubles[1])) {
                        pw.println("--------------------");
                    } else {
                        pw.println(doubles[0] + " " + doubles[1]);
                    }
                }
            }
        }
    }

    public static double[][] toDoubleArray(List<Triangle> list) {
        if (list.size() == 0) return null;
        double[][] mas = new double[list.size() * 3][2];
        for (int i = 0; i < list.size(); i++) {
            mas[i * 3][0] = list.get(i).getP1().getX();
            mas[i * 3][1] = list.get(i).getP1().getY();
            mas[i * 3 + 1][0] = list.get(i).getP2().getX();
            mas[i * 3 + 1][1] = list.get(i).getP2().getY();
            mas[i * 3 + 2][0] = list.get(i).getP3().getX();
            mas[i * 3 + 2][1] = list.get(i).getP3().getY();
        }
        return mas;
    }

    public static double[][] answToArray(List<List<Triangle>> answer) {
        List<Double[]> list = new ArrayList<>();
        for (List<Triangle> a : answer) {
            list.add(new Double[]{Double.NaN, Double.NaN});
            for (Triangle tr : a) {
                list.add(new Double[]{tr.getP1().getX(), tr.getP1().getY()});
                list.add(new Double[]{tr.getP2().getX(), tr.getP2().getY()});
                list.add(new Double[]{tr.getP3().getX(), tr.getP3().getY()});
            }
        }
        if (answer.size() > 0) {
            list.add(new Double[]{Double.NaN, Double.NaN});
        }
        double[][] matrix = new double[list.size()][];
        for (int i = 0; i < list.size(); i++) {
            matrix[i] = ArrayUtils.toPrimitive(list.get(i));
        }
        return matrix;
    }

    public static List<Triangle> toTriangleList(double[][] mas) throws NotTriangle {
        List<Triangle> list = new LinkedList<>();
        for (int i = 0; i < mas.length; i += 3) {
            Point p1 = new Point(mas[i][0], mas[i][1]);
            Point p2 = new Point(mas[i + 1][0], mas[i + 1][1]);
            Point p3 = new Point(mas[i + 2][0], mas[i + 2][1]);
            list.add(new Triangle(p1, p2, p3));
        }
        return list;
    }
}
