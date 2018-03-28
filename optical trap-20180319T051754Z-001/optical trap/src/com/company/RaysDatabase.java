package com.company;

import java.util.ArrayList;

import static java.lang.Math.abs;

public class RaysDatabase {

    private ArrayList<Ray> RaysDatabase = new ArrayList<>(100);
    private double angle; // Начальный угол (отрицательный)
    private double b, x0; //Вертикальное и горизонтальное смещение

    public RaysDatabase(double angle, double b, double x0) {
        this.angle = angle;
        this.b = b;
        this.x0 = x0;
        createDatabase();
    }

    private void createDatabase(){
        double stepAngle = abs(angle*2)/100;
        for (int i=0; i<=100; i++) {
            RaysDatabase.add(new Ray(Math.PI/180*angle, b, x0));
            angle+=stepAngle;
        }
    }

    public ArrayList<Ray> getRaysDatabase() {return RaysDatabase;}
    public double getAngle() {return angle;}

}
