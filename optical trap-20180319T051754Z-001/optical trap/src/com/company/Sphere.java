package com.company;

import java.awt.geom.Point2D;
import java.util.ArrayList;

import static java.lang.StrictMath.sqrt;

public class Sphere {
    private static double ns = 1.5, ne = 1, r = 50, t = 1;
    public static ArrayList<Point2D.Double> sphereCoord1 = new ArrayList<>();
    public static ArrayList<Point2D.Double> sphereCoord2 = new ArrayList<>();

    static {
        double x = -r, y;
        while(x<=r) {
            y = sqrt(r*r-x*x);
            x+=0.01;
            sphereCoord1.add(new Point2D.Double(x,y));
        }
        while(x>=-r) {
            y = -sqrt(r*r-x*x);
            x-=0.01;
            sphereCoord2.add(new Point2D.Double(x,y));
        }
    }

    public static double getNs() { return ns; }
    public static double getNe() { return ne; }
    public static double getR() { return r; }
    public static double getT() { return t; }
}
