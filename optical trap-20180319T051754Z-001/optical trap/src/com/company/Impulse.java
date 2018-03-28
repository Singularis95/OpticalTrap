package com.company;

import java.awt.geom.Point2D;
import java.util.ArrayList;

import static java.lang.StrictMath.*;

public class Impulse {

    ArrayList<Ray> RaysDatabase;
    Point2D.Double[][] coordImpulses1 = new Point2D.Double[101][2];
    Point2D.Double[][] coordImpulses3 = new Point2D.Double[101][2];
    Point2D.Double summVectorIn = new Point2D.Double(0,0),
             summVectorOut = new Point2D.Double(0,0), summVectorImpulse;
    double summImpulse;
    private double generalImpulse = 50;

    public Impulse(ArrayList<Ray> RaysDatabase) {
        this.RaysDatabase = RaysDatabase;

        for (int i=0; i<=RaysDatabase.size()-1; i++) {
            double lengthsImpulse = Calc.calcLengthImpulse(i, generalImpulse);
            coordImpulses1[i][1] = Calc.calcCoordImpulse(RaysDatabase.get(i).getRay1X(0), RaysDatabase.get(i).getRay1Y(0), RaysDatabase.get(i).getK1(), lengthsImpulse);
            coordImpulses1[i][0] = new Point2D.Double(RaysDatabase.get(i).getRay1X(0), RaysDatabase.get(i).getRay1Y(0));
        }
        for (int i=0; i<=RaysDatabase.size()-1; i++) {
            double lengthsImpulse = Calc.calcLengthImpulse(i, generalImpulse)*Sphere.getT()*Sphere.getT();
            coordImpulses3[i][1] = Calc.calcCoordImpulse(RaysDatabase.get(i).getRay3X(0), RaysDatabase.get(i).getRay3Y(0), RaysDatabase.get(i).getK3(), lengthsImpulse);
            coordImpulses3[i][0] = new Point2D.Double(RaysDatabase.get(i).getRay3X(0), RaysDatabase.get(i).getRay3Y(0));
        }
        {
            Point2D.Double tempVector;
            for (int i=0; i<=RaysDatabase.size()-2; i++){
                tempVector = Calc.summVectors(coordImpulses1[i][0], coordImpulses1[i][1],
                        coordImpulses1[i+1][0], coordImpulses1[i+1][1]);
                summVectorIn.x += tempVector.getX();
                summVectorIn.y += tempVector.getY();
            }
            for (int i=0; i<=RaysDatabase.size()-2; i++){
                tempVector = Calc.summVectors(coordImpulses3[i][0], coordImpulses3[i][1],
                        coordImpulses3[i+1][0], coordImpulses3[i+1][1]);
                summVectorOut.x += tempVector.getX();
                summVectorOut.y += tempVector.getY();
            }
            summVectorImpulse = Calc.summVectors(new Point2D.Double(0,0), new Point2D.Double(summVectorIn.getX(),
                            summVectorIn.getY()),
                    new Point2D.Double(0,0), new Point2D.Double(-summVectorOut.getX(), -summVectorOut.getY()));
            summImpulse = sqrt(summVectorImpulse.getX()*summVectorImpulse.getX()+summVectorImpulse.getY()*summVectorImpulse.getY());
            System.out.println(summImpulse);
            summVectorImpulse.x/=30;
            summVectorImpulse.y/=30;
        }
    }

    public Point2D.Double getCoordImpulse1(int i) {return coordImpulses1[i][1];}
    public Point2D.Double getCoordImpulse30(int i) {return coordImpulses3[i][0];}
    public Point2D.Double getCoordImpulse31(int i) {return coordImpulses3[i][1];}
    public void setGeneralImpulse(double n) {generalImpulse = n;}
    public Point2D.Double getSummVectorImpulse() {return summVectorImpulse;}
}
