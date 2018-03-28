package com.company;

import java.awt.geom.Point2D;
import static java.lang.Math.*;
import static java.lang.StrictMath.PI;
import static java.lang.StrictMath.exp;
import static java.lang.StrictMath.sqrt;

public class Calc {

    public static void calcRoots(double k, double b, int i, Point2D.Double  roots, double x0) {
        double r = Sphere.getR();
        double x, y;
        switch (i) {
            case 0: {
                //x = (-k * b - sqrt(k * k * r * r + r * r - b * b)) / (k * k + 1);
                //y = k * x + b;
                x = (-k * (k*x0+b) - sqrt((k*k+1)* r * r - (k*x0+b)*(k*x0+b))) / (k * k + 1);
                y = k *(x+x0) + b;
                roots.setLocation(x,y);
                break;
            }
            case 1: {
                //x = (-k * b + sqrt(k * k * r * r + r * r - b * b)) / (k * k + 1);
                //y = k * x + b;
                x = (-k * (k*x0+b) + sqrt((k*k+1)* r * r - (k*x0+b)*(k*x0+b))) / (k * k + 1);
                y = k *(x+x0) + b;
                roots.setLocation(x,y);
                break;
            }
            default: {
                System.out.println("Не верное значение");
                break;
            }
        }
    }

    public static double calcK (double k, double x, double y, double n1, double n2) {
        double kp = y/x;
        double k2;
        double angleA = atan(abs((kp-k)/(1+kp*k)));
        double angleB = asin(n1/n2*abs(sin(angleA)));
        {
            if (k>kp)
                k2 = tan(atan(kp)+angleB);
            else if (k<kp)
                k2 = tan(atan(kp)-angleB);
            else k2 = k;
            return k2;
        }
    }

    public static double calcB (double k1, double k2, double b1, double x, double x0) {
        double b2;
        //b2 = (x+x0)*(k1-k2)+b1;
        b2 = (x+x0)*(k1-k2)+b1;
        return b2;
    }

    public static void calcLine (double k, double b, double x1, double x2, Point2D.Double[] ray, double x0) {
        ray[0] = new Point2D.Double(x1, k*(x1+x0)+b);
        ray[1] = new Point2D.Double(x2, k*(x2+x0)+b);
    }

    public static double calcLengthImpulse(int i, double generalImpulse) {
        double mx = 50;
        double S = 36;
        double f = (1/(S*sqrt(2*PI)))*exp(-((i-mx)*(i-mx))/(2*S*S));
        double length =  generalImpulse*100*f;
        return length;
    }

    public static Point2D.Double calcCoordImpulse(double x1, double y1, double k, double lengthImpulse) {
        double x2, y2;
        x2 = x1 + lengthImpulse*cos(atan(k));
        y2 = y1 + lengthImpulse*sin(atan(k));
        return new Point2D.Double(x2, y2);
    }

    public static Point2D.Double summVectors(Point2D v11, Point2D v12, Point2D v21, Point2D v22) {
        double vx = (v12.getX() - v11.getX()) + (v22.getX() - v21.getX());
        double vy = (v12.getY() - v11.getY()) + (v22.getY() - v21.getY());
        Point2D.Double sv = new Point2D.Double(vx,vy);
        return sv;
    }
}