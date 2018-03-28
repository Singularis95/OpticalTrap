package com.company;
import java.awt.geom.Point2D;
import static java.lang.Math.*;

public class Ray {

    private double k1, k2, k3, b1, b2, b3, x0;
    private Point2D.Double roots1 = new Point2D.Double(); // Точка пересечения падающего луча и сферы
    private Point2D.Double roots2 = new Point2D.Double(); // Точка пересечения преломлённого луча и сферы
    private Point2D.Double[] ray1 = new Point2D.Double[2];
    private Point2D.Double[] ray2 = new Point2D.Double[2];
    private Point2D.Double[] ray3 = new Point2D.Double[2];

    public Ray (double angle, double b, double x0) {
        k1 = tan(angle);
        b1 = b;
        this.x0 = x0;
        CalcThis();
    }

    private void CalcThis() {
        Calc.calcRoots(k1, b1, 0, roots1, x0);
        k2 = Calc.calcK(k1, roots1.x, roots1.y, Sphere.getNe(), Sphere.getNs());
        b2 = Calc.calcB(k1, k2, b1, roots1.x, x0);
        Calc.calcRoots(k2, b2, 1, roots2, x0);
        k3 = Calc.calcK(k2, roots2.x, roots2.y, Sphere.getNs(), Sphere.getNe());
        b3 = Calc.calcB(k2, k3, b2, roots2.x, x0);
        Calc.calcLine(k1, b1, -150, roots1.x, ray1, x0);
        Calc.calcLine(k2, b2, roots1.x, roots2.x, ray2, x0);
        Calc.calcLine(k3, b3, roots2.x, 150, ray3, x0);
    }

    public double getRay1X (int i) {return ray1[i].x;}
    public double getRay1Y (int i) {return ray1[i].y;}
    public double getRay2X (int i) {return ray2[i].x;}
    public double getRay2Y (int i) {return ray2[i].y;}
    public double getRay3X (int i) {return ray3[i].x;}
    public double getRay3Y (int i) {return ray3[i].y;}
    public double getK1() {return k1;}
    public double getK3() {return k3;}
    public double getX0() {return x0;}
}
