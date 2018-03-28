package com.company;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.*;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.Range;
import org.jfree.data.xy.*;
import org.jfree.ui.*;
import org.jfree.chart.ChartFactory;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.awt.*;

public class MyChart extends ApplicationFrame {

    public MyChart(String title) {
        super(title);
        // Создаём новый график
        JFreeChart chart = createChart(createDataset());
        // На панели
        ChartPanel chartPanel = new ChartPanel(chart);
        // С размерами 1000*1000
        chartPanel.setPreferredSize(new Dimension(700, 700));
        setContentPane(chartPanel);
    }

    private static XYDataset createDataset() {
        ArrayList<XYSeries> series = new ArrayList<>(1);
        ArrayList<Ray> raysDatabase = new RaysDatabase(-30, 10, 20).getRaysDatabase();

        Impulse impulse = new Impulse(raysDatabase);
        {
            series.add(new XYSeries("SummVector"));
            series.get(series.size()-1).add(0,0);
            series.get(series.size()-1).add(impulse.getSummVectorImpulse().getX(), impulse.getSummVectorImpulse().getY());
        }
        for (int i = 0; i <=raysDatabase.size()-1; i++) {
            series.add(new XYSeries("Impulse1" + i));
            series.get(series.size()-1).add(raysDatabase.get(i).getRay1X(0), raysDatabase.get(i).getRay1Y(0));
            series.get(series.size()-1).add(impulse.getCoordImpulse1(i).getX(), impulse.getCoordImpulse1(i).getY());
        }
        for (int i = 0; i <=raysDatabase.size()-1; i++) {
            series.add(new XYSeries("Impulse3" + i));
            series.get(series.size()-1).add(impulse.getCoordImpulse30(i).getX(), impulse.getCoordImpulse30(i).getY());
            series.get(series.size()-1).add(impulse.getCoordImpulse31(i).getX(), impulse.getCoordImpulse31(i).getY());
        }
        {
            series.add(new XYSeries("Sphere1"));
            for (Point2D point: Sphere.sphereCoord1) {
                series.get(series.size()-1).add(point.getX(),point.getY());
            }
        }
        {
            series.add(new XYSeries("Sphere2"));
            for (Point2D point: Sphere.sphereCoord2) {
                series.get(series.size()-1).add(point.getX(),point.getY());
            }
        }
        for (int i = 0; i <=raysDatabase.size()-1; i++) {
            series.add(new XYSeries("Ray" + i));
            series.get(series.size()-1).add(raysDatabase.get(i).getRay1X(0), raysDatabase.get(i).getRay1Y(0));
            series.get(series.size()-1).add(raysDatabase.get(i).getRay1X(1), raysDatabase.get(i).getRay1Y(1));
            series.get(series.size()-1).add(raysDatabase.get(i).getRay2X(0), raysDatabase.get(i).getRay2Y(0));
            series.get(series.size()-1).add(raysDatabase.get(i).getRay2X(1), raysDatabase.get(i).getRay2Y(1));
            series.get(series.size()-1).add(raysDatabase.get(i).getRay3X(0), raysDatabase.get(i).getRay3Y(0));
            series.get(series.size()-1).add(raysDatabase.get(i).getRay3X(1), raysDatabase.get(i).getRay3Y(1));
        }

        final XYSeriesCollection dataset = new XYSeriesCollection();

        for (XYSeries s: series) {
            dataset.addSeries(s);
        }

        return dataset;
    }

    private static JFreeChart createChart(XYDataset dataset) {

        final JFreeChart chart = ChartFactory.createXYLineChart(
                "Optical trap",
                null,                        // x axis label
                null,                        // y axis label
                dataset,                     // data
                PlotOrientation.VERTICAL,
                false,                        // include legend
                false,                       // tooltips
                false                        // urls
        );

        chart.setBackgroundPaint(Color.white);

        final XYPlot plot = chart.getXYPlot();
        plot.setBackgroundPaint(new Color(232, 232, 232));

        plot.setDomainGridlinePaint(Color.gray);
        plot.setRangeGridlinePaint (Color.gray);

        // Определение отступа меток делений
        plot.setAxisOffset(new RectangleInsets (1.0, 1.0, 1.0, 1.0));


        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesPaint(0, Color.RED);
        for (int i = 1; i<=202; i++) {
            renderer.setSeriesPaint        (i, Color.RED);
            renderer.setSeriesShapesVisible(i, false);
            renderer.setSeriesStroke       (i, new BasicStroke(1));
        }
        for (int i = 205; i<=306; i++) {
            renderer.setSeriesPaint        (i, Color.BLUE);
            renderer.setSeriesShapesVisible(i, false);
            renderer.setSeriesStroke       (i, new BasicStroke(1));
        }
        for (int i = 203; i<=204; i++) {
            renderer.setSeriesPaint        (i, new Color(255,85,85));
            renderer.setSeriesShapesVisible(i, false);
            renderer.setSeriesStroke       (i, new BasicStroke(1));
        }

        plot.setRenderer(renderer);

        // Настройка NumberAxis
        final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();

        rangeAxis.setAxisLineVisible (true);
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

        final NumberAxis domainAxis = (NumberAxis) plot.getDomainAxis();

        //задаём размеры осей
        domainAxis.setRange(new Range(-200, 200));
        rangeAxis.setRange(new Range(-200, 200));
        plot.setDomainAxis(domainAxis);
        plot.setRangeAxis(rangeAxis);

        return chart;
    }

    public static void main(String[] args) {
        // Создаем новый фрейм
        MyChart demo = new MyChart("JFreeChart: MyChart");
        demo.pack();
        // И показываем
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}

