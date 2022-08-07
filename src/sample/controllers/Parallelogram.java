package sample.controllers;

public class Parallelogram {

    private Point a = new Point();      // точка А
    private Point b = new Point();      // точка B
    private Point c = new Point();      // точка C
    private Point d = new Point();      // точка D

    public Parallelogram() {            // конструктор без параметрів
    }

    public Parallelogram(Point a, Point b, Point c, Point d) {  // конструктор з параметрами
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    public Point getA() {
        return a;
    }

    public void setA(Point a) {
        this.a = a;
    }

    public Point getB() {
        return b;
    }

    public void setB(Point b) {
        this.b = b;
    }

    public Point getC() {
        return c;
    }

    public void setC(Point c) {
        this.c = c;
    }

    public Point getD() {
        return d;
    }

    public void setD(Point d) {
        this.d = d;
    }
}
